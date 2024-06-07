package uz.tsx.validation;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import uz.tsx.entity.*;
import uz.tsx.entity.announcement.AnnouncementContactEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionType;
import uz.tsx.entity.announcement.option.OptionEntity;
import uz.tsx.entity.announcement.option.OptionGroupEntity;
import uz.tsx.exception.*;
import uz.tsx.interfaces.UserInterface;
import uz.tsx.repository.*;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommonSchemaValidator {

    private final AttachRepository attachRepository;

    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    private final PasswordEncoder passwordEncoder;

    private final CategoryRepository categoryRepository;

    private final OptionGroupRepository optionGroupRepository;

    private final OptionRepository optionRepository;

    private final AnnounceAdditionGroupRepository additionGroupRepository;

    private final AnnounceAdditionComboValueRepository additionComboValueRepository;

    private final CurrencyRepository currencyRepository;

    private final AnnouncementRepository announcementRepository;

    private final AnnouncementContactRepository contactRepository;


    private void throwIdIsEmpty(String attachId) {
        if (attachId.isEmpty()) {
            throw new FileNotFoundException(attachId + " not null!");
        }
    }

    public void validateID(Long id) {
        Assert.notNull(id, "The given id must not be null");
        if (id <= 0) {
            throw new IllegalArgumentException(id + "-id does not meet the requirement!");
        }
    }

    public AttachEntity validateAttach(String attachId) {
        if (StringUtils.isNotEmpty(attachId)) {
            return attachRepository.findById(attachId).orElseThrow(() -> new FileNotFoundException(attachId + "-id not found!"));
        }
        return null;
    }

    public List<AttachEntity> validateAttach(List<String> attachIdList) {
        if (Objects.nonNull(attachIdList)) {

            List<AttachEntity> attachEntitiList = attachRepository.getAttachListById(attachIdList);

            if (!CollectionUtils.isEmpty(attachEntitiList)) {
                for (String id : attachIdList) {
                    for (int i = 0; i < attachEntitiList.size(); i++) {
                        if (id.equals(attachEntitiList.get(i).getId())) {
                            break;
                        } else if (i == attachEntitiList.size() - 1) {
                            throw new FileNotFoundException(id + "-attach id not found!");
                        }
                    }
                }
            }

            return attachEntitiList;
        }
        return null;
    }

    public void userPasswordAndPhoneNumberCheck(String username) {
        List<UserEntity> user = userRepository.findByUsernameOriginalDB(username);
        if (!CollectionUtils.isEmpty(user)) {

            user.forEach((u) -> {
                if (u.getUsername().equals(username)) {
                    throw new UserConflictData(username + " there is a user with this username");
                }
            });
        }
    }

    public UserInterface validateUser(Long userId) {
        return userRepository.getUserInformation(userId).orElseThrow(() -> new UsernameNotFoundException(userId + " user id not found!")
        );
    }

    public UserEntity validateUserEntity(String username) {
        return userRepository.getByUsername(username).orElseThrow(
                () -> new AuthenticationException(username + " username not found!")
        );
    }

    public UserInterface validateUser(String username) {
        return userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " username id not found!")
        );
    }


    public UserEntity validateUserUpdate(UserEntity userUpdate, UserEntity userOriginalDB, String attachId, Long regionId) {
        if (Objects.nonNull(userUpdate)) {

            String firstname = userUpdate.getFirstname();
            String username = userUpdate.getUsername();
            String password = userUpdate.getPassword();

            if (StringUtils.isNotEmpty(firstname)) {
                userOriginalDB.setFirstname(firstname);
            }
            if (StringUtils.isNotEmpty(username)) {
                userOriginalDB.setEmailOrPhone(username);
            }
            if (StringUtils.isNoneEmpty(password)) {
                userOriginalDB.setPassword(passwordEncoder.encode(password));
            }
            if (StringUtils.isNotEmpty(attachId)) {
                userOriginalDB.setAttach(validateAttach(attachId));
            }
            if(Objects.nonNull(regionId)){
                RegionEntity regionEntity = validateRegion(regionId);
                userOriginalDB.setRegion(regionEntity);
            }


        }
        return userOriginalDB;
    }

    public RegionEntity validateRegion(Long regionId) {
        return regionRepository.findByRegionId(regionId).orElseThrow(() -> new RegionNotFoundException(regionId + " - region id not found!"));
    }

    public void categoryParentAttachIdTogetherInspection(CategoryEntity category, String attachId) {

        if (Objects.nonNull(category.getParentId()) && Objects.nonNull(attachId)) {
            throw new CategoryNotFoundException("image and parent id cannot be together!");
        }
        if (Objects.isNull(category.getParentId()) && Objects.isNull(attachId)) {
            throw new IllegalArgumentException("You must send a picture or parent one!");
        }

        if (Objects.isNull(attachId)) {
            CategoryEntity categoryEntity = validateGetCategory(category.getParentId());
            category.setParentId(categoryEntity.getId());
        }else{
            AttachEntity attachEntity = validateAttach(attachId);
            category.setAttach(attachEntity);
        }

    }

    public void categoryUpdateParentAttachIdTogetherInspection(CategoryEntity updateEntity, String attachId) {
        if(Objects.isNull(attachId)){
            categoryParentAttachIdTogetherInspection(updateEntity, updateEntity.getAttach().getId());
        }else{
            categoryParentAttachIdTogetherInspection(updateEntity, attachId);
        }
    }

    public void validateCategory(Long categoryId) {
        validateID(categoryId);
        categoryRepository.findByCategoryId(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId + "- id not found!"));
    }

    public CategoryEntity validateCategoryUpdate(CategoryEntity newUpdateObject) {

        CategoryEntity categoryDB = validateGetCategory(newUpdateObject.getId());

        String nameEn = newUpdateObject.getNameEn();
        String nameUz = newUpdateObject.getNameUz();
        String nameRu = newUpdateObject.getNameRu();

        if(Objects.nonNull(nameEn)){
            categoryDB.setNameEn(nameEn);
        } else if (Objects.nonNull(nameRu)) {
            categoryDB.setNameRu(nameRu);
        }else if (Objects.nonNull(nameUz)){
            categoryDB.setNameUz(nameUz);
        }

        Long parentId = newUpdateObject.getParentId();
        if(Objects.nonNull(parentId)){
            categoryDB.setParentId(parentId);
        }

        return categoryDB;
    }

    public CategoryEntity validateGetCategory(Long id) {
        validateID(id);
        return categoryRepository.findByCategoryId(id).orElseThrow(() -> new CategoryNotFoundException(id + "- id not found!"));
    }

    public CategoryEntity validateGetParentCategory(Long id) {
        if (Objects.isNull(id)) return null;
        return categoryRepository.findByCategoryId(id).orElseThrow(() -> new CategoryNotFoundException(id + "- id not found!"));
    }

    public void doesCategoryExist(Long categoryId){
        validateID(categoryId);
        if(!categoryRepository.doesCategoryExistById(categoryId)){
            throw new CategoryNotFoundException(categoryId + "-id not found!");
        }
    }

    public void validateOptionGroupId(Long id) {
        validateID(id);
        if (!optionGroupRepository.existsByGroupId(id)) {
            throw new NotFoundException(id + "-id not found!");
        }
    }

    public OptionGroupEntity validateOptionGroup(Long id) {
        validateID(id);
        return optionGroupRepository.getOption(id).orElseThrow(() -> new NotFoundException(id + " option group not found"));
    }

    public OptionGroupEntity validateOptionGroupUpdate(OptionGroupEntity optionGroup){
        OptionGroupEntity optionGroupDb = validateOptionGroup(optionGroup.getId());
        if (Objects.nonNull(optionGroup.getNameEn())){
            optionGroupDb.setNameEn(optionGroup.getNameEn());
        }
        if (Objects.nonNull(optionGroup.getNameRu())){
            optionGroupDb.setNameRu(optionGroup.getNameRu());
        }
        if (Objects.nonNull(optionGroup.getNameUz())){
            optionGroupDb.setNameUz(optionGroup.getNameUz());
        }

        return optionGroupDb;
    }

    public OptionEntity validateOption(Long id) {
        validateID(id);
        return optionRepository.getOptionById(id);
    }

    public AdditionGroupEntity validateAdditionGroup(Long id) {

        validateID(id);
        return additionGroupRepository.getAdditionGroupById(id).orElseThrow(() -> new NotFoundException(id + "-id not found!"));
    }

    public void validateAdditionGroupId(Long id) {
        validateID(id);
        if (!additionGroupRepository.existsByAdditionGroupId(id)) {
            throw new NotFoundException(id + "-id not found");
        }
    }

    public void validateAdditionGroupByType(Long id, AdditionType type) {

        AdditionType entityDBType = validateAdditionGroup(id).getType();
        if (entityDBType != type) {
            throw new IllegalArgumentException(String.format("Addition to AdditionComboValue of type %s is not allowed.", entityDBType));
        }
    }

    public AdditionGroupEntity validateAdditionGroupUpdate(AdditionGroupEntity entity) {
        AdditionGroupEntity entityDB = validateAdditionGroup(entity.getId());

        String nameEn = entity.getNameEn();
        String nameRu = entity.getNameRu();
        String nameUz = entity.getNameUz();
        if (Objects.nonNull(nameEn)) {
            entityDB.setNameEn(nameEn);
        }
        if (Objects.nonNull(nameRu)){
            entityDB.setNameRu(nameRu);
        }
        if (Objects.nonNull(nameUz)){
            entityDB.setNameUz(nameUz);
        }

        Long categoryId = entity.getCategoryId();
        if (Objects.nonNull(categoryId)) {
            validateCategory(categoryId);
            entityDB.setCategoryId(categoryId);
        }

        AdditionType type = entity.getType();
        if (Objects.nonNull(type)) {
            entityDB.setType(type);
        }

        return entityDB;
    }

    public AdditionComboValueEntity validateAdditionComboValue(Long id) {
        validateID(id);
        return additionComboValueRepository.getAdditionComboValueById(id).orElseThrow(() -> new NotFoundException(id + "- id not found"));
    }

    public AdditionComboValueEntity validateAdditionComboValue(AdditionComboValueEntity updateEntity) {

        AdditionComboValueEntity entityDB = validateAdditionComboValue(updateEntity.getId());

        Long groupId = updateEntity.getGroupId();
        if (Objects.nonNull(groupId)) {
            validateAdditionGroupId(groupId);
            entityDB.setGroupId(groupId);
        }

        String nameUz = updateEntity.getNameUz();
        String nameRu = updateEntity.getNameRu();
        String nameEn = updateEntity.getNameEn();

        if (Objects.nonNull(nameEn)) {
            entityDB.setNameEn(nameEn);
        }
        if (Objects.nonNull(nameRu)){
            entityDB.setNameRu(nameRu);
        }
        if (Objects.nonNull(nameUz)){
            entityDB.setNameUz(nameUz);
        }


        return entityDB;
    }

    public void validateAdditionComboValueId(Long id) {
        validateID(id);
        if (!additionComboValueRepository.existsAdditionComboValue(id)) {
            throw new NotFoundException(id + "-id not found!");
        }
    }

    public void validateCurrencyId(Long currencyId) {
        validateID(currencyId);
        if (!currencyRepository.existsCurrencyId(currencyId)) {
            throw new NotFoundException(currencyId + "-id not found!");
        }
    }

    public AnnouncementEntity validateAnnouncementId(Long announceId) {
        validateID(announceId);
        return announcementRepository.getAnnouncementById(announceId).
                orElseThrow(() -> new IllegalStateException("Announce is not found"));
    }
    public AnnouncementContactEntity validateAnnouncementContactId(Long id){
        validateID(id);
        return contactRepository.findBy(id)
                .orElseThrow(()->new NotFoundException("Announce is not found"));
    }
}
