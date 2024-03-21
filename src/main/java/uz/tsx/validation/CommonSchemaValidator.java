package uz.tsx.validation;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.entity.*;
import uz.tsx.exception.*;
import uz.tsx.exception.interfaces.UserInterface;
import uz.tsx.repository.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CommonSchemaValidator {

    private final AttachRepository attachRepository;

    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    private final PasswordEncoder passwordEncoder;

    private final CategoryRepository categoryRepository;



    private void throwIdIsEmpty(String attachId) {
        if (attachId.isEmpty()) {
            throw new FileNotFoundException(attachId + " not null!");
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

            if(!CollectionUtils.isEmpty(attachEntitiList)){
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
            if (StringUtils.isNoneEmpty(password)){
                userOriginalDB.setPassword(passwordEncoder.encode(password));
            }
            if (StringUtils.isNotEmpty(attachId)) {
                userOriginalDB.setAttach(validateAttach(attachId));
            }


        }
        return userOriginalDB;
    }

    public RegionEntity validateRegion(Long regionId) {
        return regionRepository.findByRegionId(regionId).orElseThrow(()-> new RegionNotFoundException(regionId + " - region id not found!"));
    }

    public void categoryStatusCheck(CategoryEntity categoryentity, String attachId) {
        CategoryEntity getByCategoryNameOriginDB = categoryRepository.findByCategoryName(categoryentity.getName_en());

        if (Objects.nonNull(getByCategoryNameOriginDB)) {

            if (getByCategoryNameOriginDB.getStatus() == EntityStatus.DELETED) {
                Long parentIdDTO = categoryentity.getParentId();

                if (Objects.nonNull(parentIdDTO)) {
                    categoryRepository.findByCategoryId(parentIdDTO).orElseThrow(() -> new CategoryNotFoundException(parentIdDTO + " parent id not found!"));
                    getByCategoryNameOriginDB.setParentId(categoryentity.getParentId());
                }
                categoryentity.setId(getByCategoryNameOriginDB.getId());
            } else {
                throw new CategoryNotFoundException(categoryentity.getName_en() + " such a category exists!");
            }
        }
        categoryAttachId(categoryentity.getParentId(), attachId);
    }

    public void categoryAttachId(Long parentId, String attachId) {

        if(Objects.nonNull(parentId) && Objects.nonNull(attachId)){
            throw new CategoryNotFoundException("image and parent id cannot be together!");
        }

        if(Objects.isNull(attachId) && Objects.isNull(parentId)){
            throw new CategoryNotFoundException("imageId and parentId must not be null together!");
        }
    }

    public void validateCategory(Long categoryId){
        categoryRepository.findByCategoryId(categoryId).orElseThrow(()-> new CategoryNotFoundException(categoryId + "- id not found!"));
    }

    public CategoryEntity validateGetCategory(Long id){
        if (Objects.isNull(id)) throw new CategoryNotFoundException("category id cannot be null");
        return categoryRepository.findByCategoryId(id).orElseThrow(()-> new CategoryNotFoundException(id + "- id not found!"));
    }

    public CategoryEntity validateGetParentCategory(Long id){
        if (Objects.isNull(id)) return null;
        return categoryRepository.findByCategoryId(id).orElseThrow(()-> new CategoryNotFoundException(id + "- id not found!"));
    }

}
