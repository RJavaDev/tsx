package uz.tsx.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.common.util.DateUtil;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.controller.convert.AttachConvert;
import uz.tsx.dto.CurrencyDto;
import uz.tsx.dto.announcement.AnnouncementContactDto;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.announcement.AnnouncementPriceDto;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionGroupDto;
import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.dto.announcement.selector.AnnounceOptionSelector;
import uz.tsx.dto.announcement.selector.AnnouncementInfoSelector;
import uz.tsx.dto.dtoUtil.BigDataTable;
import uz.tsx.dto.dtoUtil.DataTable;
import uz.tsx.dto.dtoUtil.FilterForm;
import uz.tsx.dto.dtoUtil.PageParam;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.AttachEntity;
import uz.tsx.entity.announcement.AnnouncementContactEntity;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.announcement.AnnouncementPriceEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;
import uz.tsx.entity.announcement.option.OptionEntity;
import uz.tsx.exception.NotFoundException;
import uz.tsx.interfaces.AnnouncementInterface;
import uz.tsx.repository.AnnounceAdditionGroupRepository;
import uz.tsx.repository.AnnouncementRepository;
import uz.tsx.repository.OptionRepository;
import uz.tsx.service.*;
import uz.tsx.validation.CommonSchemaValidator;
import uz.tsx.validation.Validation;

import java.text.ParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    @PersistenceContext
    private EntityManager entityManager;

    private final AnnouncementRepository repository;
    private final AnnounceAdditionGroupRepository announceAdditionGroupRepository;
    private final OptionRepository optionRepository;
    private final AnnouncementContactService announcementContactService;
    private final AnnouncementPriceService announcementPriceService;
    private final CategoryService categoryService;
    private final AttachService attachService;
    private final AnnouncementContactService contactService;
    private final AnnouncementPriceService priceService;

    private final Map<Long,List<Long>> userCount=new HashMap<>();

    private final CommonSchemaValidator commonValidator;
    private final AnnouncementRepository announcementRepository;

    @Override
    @Transactional
    public void update(AnnouncementEntity editableAnnouncement) {
        repository.save(editableAnnouncement);
    }

    @Override
    public AnnouncementEntity createNewAnnouncement(AnnouncementEntity entity) {

        AnnouncementContactEntity contactInfoEntity = announcementContactService.addNewAnnounceContact(entity.getContactInfo());
        AnnouncementPriceEntity priceEntity = announcementPriceService.addNewAnnouncementPrice(entity.getPriceTag());
        entity.setContactInfoId(contactInfoEntity.getId());
        entity.setPriceTagId(priceEntity.getId());
        entity.setPriceTag(priceEntity);

        entity.setContactInfo(contactInfoEntity);
        entity.setISaw(1);

        List<Long>user=new ArrayList<>();

        if(entity.getUserId() != null) {
            entity.forCreate(entity.getUserId());
            user.add(entity.getUserId());
        } else {
            Long userId = SecurityUtils.getUserId();
            entity.forCreate(userId);
            entity.setUserId(userId);
            user.add(userId);
        }

        AnnouncementEntity save = repository.save(entity);

        userCount.put(save.getId(), user);
        return save;
    }

    @Override
    public void changeActiveStatus(Long announcementId, Boolean isActive) {
        repository.changeActiveStatus(announcementId, isActive);
    }

    @Override
    public void deleteAnnouncement(Long announcementId) {
        if(repository.existsById(announcementId)){
            repository.delete(announcementId);
        }else{
            throw new NotFoundException("Object not found!");
        }
    }

    @Override
    public List<AnnouncementEntity> getAnnouncementListByUserEntity(Long userId) {
        return announcementRepository.getAnnouncementListByUserId(userId);
    }

    @Override
    public List<AnnouncementDto> findAllAnnouncements() {
        List<AnnouncementEntity> list = repository.findAllBy();
        List<AnnouncementDto> announcementDtos = list.stream().map(AnnouncementEntity::toDto).toList();

         for (AnnouncementDto dto:announcementDtos){
             if (Objects.nonNull(dto)){
                 AnnouncementEntity announcement1 = repository.findById(dto.getId()).get();
                 List<AttachUrlResponse> attachUrlResponses = AttachConvert.convertToAttachUrlDto(announcement1.getAttachPhotos());
                 dto.setAttachUrlResponses(attachUrlResponses);
                 dto.setContactInfo(contactService.getById(announcement1.getContactInfoId()));
                 dto.setPriceTag(priceService.getById(announcement1.getPriceTagId()));
             }
         }

        List<Long> groupIds = new ArrayList<>();
        List<Long> optionIds = new ArrayList<>();
        for(AnnouncementDto dto : announcementDtos) {
            if(dto.getAdditionalInfos() != null) {
                dto.getAdditionalInfos().forEach(additionGroup -> groupIds.add(additionGroup.getAdditionGroupId()));
            }

            if(dto.getAdditionalOptions() != null) {
                dto.getAdditionalOptions().forEach(additionOption -> optionIds.add(additionOption.getOptionId()));
            }
        }

        Map<Long, AnnounceAdditionGroupDto> groupIdToValueMap = findAdditionInfoGroups(groupIds);
        fillAnnounceAdditionGroup(announcementDtos, groupIdToValueMap);

        Map<Long, OptionDto> optionIdToValueMap = findAdditionOptions(optionIds);
        fillAnnounceAdditionOptions(announcementDtos, optionIdToValueMap);
        return announcementDtos;
    }
    @Override
    public AnnouncementDto findAnnouncementByIdAbout(Long announceId) {
        if(!Validation.checkId(announceId)) return null;

        Optional<AnnouncementEntity> entityOpt = repository.findById(announceId);
        AnnouncementDto announcementDto = entityOpt.map(AnnouncementEntity::toDto).
                orElseThrow(() -> new IllegalStateException("Announce is not found"));

        announcementDto.setCategory(categoryService.findTreeFromBottom(announcementDto.getCategoryId()));
        announcementDto.setPriceTag(announcementPriceService.getById(announcementDto.getPriceTagId()));
        announcementDto.setContactInfo(announcementContactService.getById(announcementDto.getContactInfoId()));

        List<String> attachImages = repository.getAttachImages(entityOpt.get().getId());
        attachImages.forEach(originName -> {
            announcementDto.getAttachPhotosUrl().add("/attach/file/" + originName);
            announcementDto.getAttachMiniPhotosUrl().add("/attach/file/" + attachService.getMinAttachImgName(originName));
        });

        List<Long> groupIds = new ArrayList<>();
        List<Long> optionIds = new ArrayList<>();
        if(announcementDto.getAdditionalInfos() != null) {
            announcementDto.getAdditionalInfos().forEach(additionGroup -> groupIds.add(additionGroup.getAdditionGroupId()));
        }

        if(announcementDto.getAdditionalOptions() != null) {
            announcementDto.getAdditionalOptions().forEach(additionOption -> optionIds.add(additionOption.getOptionId()));
        }

        Map<Long, AnnounceAdditionGroupDto> groupIdToValueMap = findAdditionInfoGroups(groupIds);
        fillAnnounceAdditionGroup(Collections.singletonList(announcementDto), groupIdToValueMap);

        Map<Long, OptionDto> optionIdToValueMap = findAdditionOptions(optionIds);
        fillAnnounceAdditionOptions(Collections.singletonList(announcementDto), optionIdToValueMap);
        return announcementDto;
    }


    private Map<Long, AnnounceAdditionGroupDto> findAdditionInfoGroups(List<Long> additionGroupIds) {
        Map<Long, AnnounceAdditionGroupDto> map = new HashMap<>();
        if(additionGroupIds == null || additionGroupIds.isEmpty())
            return map;

        List<AdditionGroupEntity> additionGroupEntities = announceAdditionGroupRepository.getAdditionGroupEntitiesByIds(additionGroupIds);
        for(AdditionGroupEntity entity : additionGroupEntities) {
            map.put(entity.getId(), entity.toDto(entity, new AnnounceAdditionGroupDto()));
        }

        return map;
    }

    private void fillAnnounceAdditionGroup(List<AnnouncementDto> announcementDtos, Map<Long, AnnounceAdditionGroupDto> groupIdToValueMap) {
        for(AnnouncementDto dto : announcementDtos) {
            if(dto.getAdditionalInfos() != null) {
                for(AnnouncementInfoSelector info : dto.getAdditionalInfos())
                    info.setAdditionGroup(groupIdToValueMap.get(info.getAdditionGroupId()));
            }
        }
    }

    private Map<Long, OptionDto> findAdditionOptions(List<Long> optionIds) {
        Map<Long, OptionDto> map = new HashMap<>();
        if(optionIds == null || optionIds.isEmpty())
            return map;

        List<OptionEntity> optionEntities = optionRepository.findAllOptionByIds(optionIds);
        for(OptionEntity entity : optionEntities) {
            map.put(entity.getId(), entity.toDto(entity, new OptionDto()));
        }

        return map;
    }

    private void fillAnnounceAdditionOptions(List<AnnouncementDto> announcementDtos, Map<Long, OptionDto> groupIdToValueMap) {
        for(AnnouncementDto dto : announcementDtos) {
            if(dto.getAdditionalOptions() != null) {
                for(AnnounceOptionSelector option : dto.getAdditionalOptions())
                    option.setOption(groupIdToValueMap.get(option.getOptionId()));
            }
        }
    }

//    @Override
//    public AnnouncementDto createNewAnnouncement(AnnouncementDto dto) {
//        if(dto == null) return null;
//
//        if(StringUtils.isEmpty(dto.getTitle())) throw new IllegalStateException("Announce title can't be empty");
//        if(dto.getCategoryId() == null) throw new IllegalStateException("CategoryId is null");
//
//        // CHECK contactInfo
//        if(dto.getContactInfo() == null || (StringUtils.isEmpty(dto.getContactInfo().getGmail()) && StringUtils.isEmpty(dto.getContactInfo().getPhone())))
//            throw new IllegalStateException("Contact info is not full");
//
//        // CHECK priceTag
//        if(dto.getPriceTag() == null || dto.getPriceTag().getCurrencyId() == null || dto.getPriceTag().getPrice() == null)
//            throw new IllegalStateException("PriceTag is not full");
//
//        AnnouncementContactDto createdContactInfo = announcementContactService.createAnnounceContact(dto.getContactInfo());
//        AnnouncementPriceDto createdPriceTag = announcementPriceService.createAnnouncePrice(dto.getPriceTag());
//
//        AnnouncementEntity entity = new AnnouncementEntity();
//        BeanUtils.copyProperties(dto, entity);
//        entity.setPriceTagId(createdPriceTag.getId());
//        entity.setContactInfoId(createdContactInfo.getId());
//        entity.forCreate();
//
//        if(dto.getAdditionalInfos() != null) {
//            Set<AnnounceAdditionInfoDto> announceAdditionInfos = new HashSet<>();
//            for(AnnouncementInfoSelector sInfo : dto.getAdditionalInfos())
//                announceAdditionInfos.add(sInfo.toDto());
//
//            entity.setAdditionalInfos(announceAdditionInfos);
//        }
//
//        if(dto.getAdditionalOptions() != null) {
//            Set<AnnounceOptionDto> announceOptionDtos = new HashSet<>();
//            for(AnnounceOptionSelector sOption : dto.getAdditionalOptions())
//                announceOptionDtos.add(sOption.toDto());
//
//            entity.setAdditionalOptions(announceOptionDtos);
//        }
//
//        repository.save(entity);
//        AnnouncementDto createdAnnounce = entity.toDto();
//        createdAnnounce.setContactInfo(createdContactInfo);
//        createdAnnounce.setPriceTag(createdPriceTag);
//
//        return createdAnnounce;
//    }

    @Override
    public DataTable<AnnouncementDto> table1(Map<String, Object> filter) {
        Integer page = 1;
        Integer size = 20;

        if(filter != null) {
            page = MapUtils.getInteger(filter, "page", 1);
            size = MapUtils.getInteger(filter, "size", 20);
        }

        Page<AnnouncementEntity> pageAnnouncement = repository.findPage(PageRequest.of(page - 1, size));
        List<AnnouncementDto> dtos = pageAnnouncement.stream().map(AnnouncementEntity::toDto).toList();

        DataTable<AnnouncementDto> datatable = new DataTable<>();
        datatable.setTotal((int) pageAnnouncement.getTotalElements());
        datatable.setRows(dtos);

        return datatable;
    }

    @Override
    public DataTable<AnnouncementDto> table2(Map<String, Object> filter) {
        Integer page = 1;
        Integer size = 20;

        if(filter != null) {
            page = MapUtils.getInteger(filter, "page", 1);
            size = MapUtils.getInteger(filter, "size", 20);
        }

        Page<AnnouncementInterface> pageAnnouncement = repository.findPageInterface(PageRequest.of(page - 1, size));
        List<AnnouncementDto> dtos = pageAnnouncement.stream().map(aInterface -> {
            AnnouncementDto dto = new AnnouncementDto();
            AnnouncementPriceDto priceDto = new AnnouncementPriceDto();
            AnnouncementContactDto contactDto = new AnnouncementContactDto();
            List<AttachUrlResponse> attachUrlResponses=new ArrayList<>();
            List<String> originDto =new ArrayList<>();
            List<String> urlMiniDto =new ArrayList<>();
            if (Objects.nonNull(aInterface.getAttachId())){
                AttachEntity attach = attachService.getById(aInterface.getAttachId());
                List<AttachUrlResponse> attachUrlResponses1 = AttachConvert.convertToAttachUrlDto(List.of(attach));
                List<String> originDto1 = AttachConvert.convertToAttachUrlOriginDto(attachUrlResponses);
                List<String> urlMiniDto2 = AttachConvert.convertToAttachUrlMiniDto(attachUrlResponses);
               originDto=originDto1;
               urlMiniDto=urlMiniDto2;
                attachUrlResponses=attachUrlResponses1;
            }



            BeanUtils.copyProperties(aInterface, dto);
            BeanUtils.copyProperties(aInterface, priceDto, "id");
            BeanUtils.copyProperties(aInterface, contactDto, "id");
            BeanUtils.copyProperties(aInterface, attachUrlResponses);


            if(Validation.checkId(aInterface.getCurrencyId())) {
                CurrencyDto cDto = new CurrencyDto();
                cDto.setId(aInterface.getCurrencyId());
                cDto.setCode(aInterface.getCurrencyCode());
                priceDto.setCurrencyId(aInterface.getCurrencyId());
                priceDto.setCurrency(cDto);
            }
            dto.setAttachPhotosUrl(originDto);
            dto.setAttachMiniPhotosUrl(urlMiniDto);
            dto.setAttachUrlResponses(attachUrlResponses);
            dto.setPriceTag(priceDto);
            dto.setContactInfo(contactDto);
            return dto;
        }).toList();

        DataTable<AnnouncementDto> datatable = new DataTable<>();
        datatable.setTotal((int) pageAnnouncement.getTotalElements());
        datatable.setRows(dtos);

        return datatable;
    }

    @Override
    public AnnouncementEntity saveAnnounceImages(Long announceId, MultipartFile[] imgFiles) {

        if(Objects.isNull(imgFiles))
            throw new IllegalStateException("Bad request");

        AnnouncementEntity entityDB = commonValidator.validateAnnouncementId(announceId);

        List<AttachEntity> attachEntityList = attachService.saveAttach(imgFiles);

        entityDB.setAttachPhotos(attachEntityList);

        repository.save(entityDB);


        return entityDB;
    }

    @Override
    public AnnouncementEntity getById(Long id) {
        return commonValidator.validateAnnouncementId(id);
    }

    @Override
    public BigDataTable<AnnouncementInterface> getPageHomeData(Integer page, Integer size){

        Page<AnnouncementInterface> announcementInterfaceHome = repository.getAnnouncementPage(PageRequest.of(page - 1, size));

        BigDataTable<AnnouncementInterface> interfaceDataTable = new BigDataTable<>();

        interfaceDataTable.setTotal(announcementInterfaceHome.getTotalElements());
        interfaceDataTable.setRows(announcementInterfaceHome.getContent());
        interfaceDataTable.setTotalPage(announcementInterfaceHome.getTotalPages());

        return interfaceDataTable;
    }

    @Override
    public BigDataTable<AnnouncementInterface> getAnnouncementListByCategory(Long categoryId, PageParam pageParam) {

        Page<AnnouncementInterface> announcementByCategoryId = repository.getAnnouncementByCategoryId(categoryId, PageRequest.of(pageParam.getPage() - 1, pageParam.getSize()));
        BigDataTable<AnnouncementInterface> datatable = new BigDataTable<>();

        datatable.setTotal(announcementByCategoryId.getTotalElements());
        datatable.setRows(announcementByCategoryId.getContent());
        datatable.setTotalPage(announcementByCategoryId.getTotalPages());

        return datatable;
    }

    @Override
    public BigDataTable<AnnouncementInterface> searchAnnouncementAndFilter(FilterForm filter) {

        Pageable pageable = PageRequest.of(filter.getPage() - 1, filter.getSize());
        Map<String, Object> filterMap = filter.getFilter();

        String title = null;
        Long categoryId = null;
        Long regionId = null;
        Date startDate = null;
        Date endDate = null;
        if(Objects.nonNull(filterMap)){

            if(filterMap.containsKey("title")){
                title = MapUtils.getString(filterMap, "title");
                if(title.isEmpty()) title = null;
            }
            if(filterMap.containsKey("regionId")){
                regionId = MapUtils.getLong(filterMap, "regionId");
            }
            if(filterMap.containsKey("categoryId")){
                categoryId = MapUtils.getLong(filterMap, "categoryId");
            }

            if (filterMap.containsKey("startDate")) {
                try {
                    startDate = DateUtils.parseDate((MapUtils.getString(filterMap, "startDate")), DateUtil.PATTERN3);
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            if (filterMap.containsKey("endDate")) {
                try {
                    endDate = DateUtils.parseDate((MapUtils.getString(filterMap, "endDate")), DateUtil.PATTERN3);
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }

        Page<AnnouncementInterface> announcementInterfaceList = repository.searchAnnouncementAndFilter(title, regionId, categoryId, startDate, endDate, pageable);
        List<AnnouncementInterface> announcementInterfaceListContent = announcementInterfaceList.getContent();

        announcementInterfaceList.getTotalPages();

        BigDataTable<AnnouncementInterface> dataTable = new BigDataTable<>();

        dataTable.setRows(announcementInterfaceListContent);
        dataTable.setTotal(announcementInterfaceList.getTotalElements());
        dataTable.setTotalPage(announcementInterfaceList.getTotalPages());

        return dataTable;

    }

    @Override
    public List<AnnouncementInterface> getMeAnnouncementList() {
        Long userId = SecurityUtils.getUserId();
        return repository.getMeAnnouncement(userId);
    }

    @Override
    public void activeReverseUpdate(Long id) {
        if(repository.existsById(id)){
            repository.reverseActive(id);
        }else{
            throw new NotFoundException("Object not found!");
        }
    }

    @Override
    public Integer iSaw(Long announcementId, HttpServletRequest httpServletRequest){
        UserEntity user = SecurityUtils.getUser();
        Long id=1L;
        boolean yes=true;
        HttpSession session = httpServletRequest.getSession();
        UserEntity user1 = (UserEntity) session.getAttribute("currentSessionUser");
        Optional<AnnouncementEntity> announcement = repository.findById(announcementId);

        if (userCount.size()>0){
            List<Long> longs = userCount.get(announcement.get().getId());
            try {
                if (user != null && announcement.isPresent()) {
                    List<Long> longs1 = userCount.get(announcementId);


                    for (Long userId : longs1) {
                        if (Objects.equals(userId, user.getId())) {
                            yes = false;
                        }
                    }
                    if (yes) {
                        longs.add(user.getId());
                        userCount.put(announcement.get().getId(), longs);
                    }
                } else {
                    longs.add(1L);
                    userCount.put(announcement.get().getId(), longs);
                }
            }
           catch (NullPointerException e){
                List<Long>u=new ArrayList<>();
                u.addAll(Collections.singleton(Long.valueOf(announcement.get().getISaw())));
                u.add(user.getId());
                userCount.put(announcementId,u);
            }
        }

        else {
                if (Objects.nonNull(user)){id=user.getId();}

            List<Long>u=new ArrayList<>();
                u.add(id);
            userCount.put(announcementId,u);
            AnnouncementEntity announcement1 = announcement.get();
            List<Long> longs = userCount.get(announcementId);
            announcement1.setISaw(longs.size()+announcement1.getISaw());
            repository.save(announcement1);
            return longs.size();
        }

        AnnouncementEntity announcement1 = announcement.get();
        List<Long> longs = userCount.get(announcementId);
        announcement1.setISaw(longs.size());
        repository.save(announcement1);

        return longs.size();
    }

    private String[] searchProductNameToArray(String productName) {
        String[] categoryNameList = productName.split(" ");

        for (byte i = 0; i < categoryNameList.length; i++) {
            categoryNameList[i] = "%" + categoryNameList[i] + "%";
        }
        return categoryNameList;
    }

    public Sort orderSortField(String field) {
        return Sort.by(Sort.Order.by(field));
    }
}
