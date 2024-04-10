package uz.tsx.validation;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.entity.*;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;
import uz.tsx.entity.announcement.additionInfo.AdditionType;
import uz.tsx.entity.announcement.option.OptionEntity;
import uz.tsx.entity.announcement.option.OptionGroupEntity;
import uz.tsx.exception.*;
import uz.tsx.exception.interfaces.UserInterface;
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


    public UserEntity validateUserUpdate(UserEntity userUpdate, UserEntity userOriginalDB, String attachId) {
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


        }
        return userOriginalDB;
    }

    public RegionEntity validateRegion(Long regionId) {
        return regionRepository.findByRegionId(regionId).orElseThrow(() -> new RegionNotFoundException(regionId + " - region id not found!"));
    }

    public void categoryStatusCheck(CategoryEntity categoryentity, String attachId) {
        CategoryEntity getByCategoryNameOriginDB = categoryRepository.findByCategoryName(categoryentity.getNameEn());

        if (Objects.nonNull(getByCategoryNameOriginDB)) {

            if (getByCategoryNameOriginDB.getStatus() == EntityStatus.DELETED) {
                Long parentIdDTO = categoryentity.getParentId();

                if (Objects.nonNull(parentIdDTO)) {
                    categoryRepository.findByCategoryId(parentIdDTO).orElseThrow(() -> new CategoryNotFoundException(parentIdDTO + " parent id not found!"));
                    getByCategoryNameOriginDB.setParentId(categoryentity.getParentId());
                }
                categoryentity.setId(getByCategoryNameOriginDB.getId());
            } else {
                throw new CategoryNotFoundException(categoryentity.getNameEn() + " such a category exists!");
            }
        }
        categoryAttachId(categoryentity.getParentId(), attachId);
    }

    public void categoryAttachId(Long parentId, String attachId) {

        if (Objects.nonNull(parentId) && Objects.nonNull(attachId)) {
            throw new CategoryNotFoundException("image and parent id cannot be together!");
        }

        if (Objects.isNull(attachId) && Objects.isNull(parentId)) {
            throw new CategoryNotFoundException("imageId and parentId must not be null together!");
        }
    }

    public void validateCategory(Long categoryId) {
        validateID(categoryId);
        categoryRepository.findByCategoryId(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId + "- id not found!"));
    }

    public CategoryEntity validateGetCategory(Long id) {
        if (Objects.isNull(id)) throw new CategoryNotFoundException("category id cannot be null");
        return categoryRepository.findByCategoryId(id).orElseThrow(() -> new CategoryNotFoundException(id + "- id not found!"));
    }

    public CategoryEntity validateGetParentCategory(Long id) {
        if (Objects.isNull(id)) return null;
        return categoryRepository.findByCategoryId(id).orElseThrow(() -> new CategoryNotFoundException(id + "- id not found!"));
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
        if(entityDBType!=type){
            throw new IllegalArgumentException(String.format("Addition to AdditionComboValue of type %s is not allowed.", entityDBType));
        }
    }

    public AdditionGroupEntity validateAdditionGroupUpdate(AdditionGroupEntity entity) {
        AdditionGroupEntity entityDB = validateAdditionGroup(entity.getId());

        String nameEn = entity.getNameEn();
        String nameRu = entity.getNameRu();
        String nameUz = entity.getNameUz();
        if (Objects.nonNull(nameEn) && Objects.nonNull(nameRu) && Objects.nonNull(nameUz)) {
            entityDB.setNameEn(nameEn);
            entityDB.setNameRu(nameRu);
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

        if(Objects.nonNull(nameEn) && Objects.nonNull(nameRu) && Objects.nonNull(nameUz)){
            entityDB.setNameEn(nameEn);
            entityDB.setNameRu(nameRu);
            entityDB.setNameUz(nameUz);
        }


        return entityDB;
    }

    public void validateAdditionComboValueId(Long id) {
        validateID(id);
        if(!additionComboValueRepository.existsAdditionComboValue(id)){
            throw new NotFoundException(id+"-id not found!");
        }
    }

    public void validateCurrencyId(Long currencyId) {
        validateID(currencyId);
        if(!currencyRepository.existsCurrencyId(currencyId)){
            throw new NotFoundException(currencyId+"-id not found!");
        }
    }

    public AnnouncementEntity validateAnnouncementId(Long announceId) {
        validateID(announceId);
        return announcementRepository.findById(announceId).
                orElseThrow(() -> new IllegalStateException("Announce is not found"));
    }
}
