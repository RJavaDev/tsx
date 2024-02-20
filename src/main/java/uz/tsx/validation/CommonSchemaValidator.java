package uz.tsx.validation;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.entity.*;
import uz.tsx.exception.*;
import uz.tsx.repository.*;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CommonSchemaValidator {

    private final AttachRepository attachRepository;

    private final UserRepository userRepository;


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

    public void userPasswordAndPhoneNumberCheck(String username, String phoneNumber) {
        List<UserEntity> user = userRepository.findByUsernameOriginalDB(username, phoneNumber);
        if (!CollectionUtils.isEmpty(user)) {

            user.forEach((u) -> {
                if (u.getUsername().equals(username)) {
                    throw new UserConflictData(username + " there is a user with this username");
                }
                if (u.getPhoneNumber().equals(phoneNumber)) {
                    throw new UserConflictData(phoneNumber + " there is a user with this phone number");
                }
            });
        }
    }


    public UserEntity validateUserUpdate(UserEntity userUpdate, UserEntity userOriginalDB, String attachId) {
        if (Objects.nonNull(userUpdate)) {

            String firstname = userUpdate.getFirstname();
            String username = userUpdate.getUsername();
            String password = userUpdate.getPassword();
            String phoneNumber = userUpdate.getPhoneNumber();

            if (StringUtils.isNotEmpty(firstname)) {
                userOriginalDB.setFirstname(firstname);
            }
            if (StringUtils.isNotEmpty(username)) {
                userOriginalDB.setUsername(username);
            }
            if (StringUtils.isNoneEmpty(password)){
                userOriginalDB.setPassword(passwordEncoder.encode(password));
            }
            if (StringUtils.isNotEmpty(phoneNumber)) {
                userOriginalDB.setPhoneNumber(phoneNumber);
            }
            if (StringUtils.isNotEmpty(attachId)) {
                userOriginalDB.setAttach(validateAttach(attachId));
            }

        }
        return userOriginalDB;
    }

    public void categoryStatusCheck(CategoryEntity getByCategoryNameOriginDB, CategoryEntity categoryentity) {

        if (Objects.nonNull(getByCategoryNameOriginDB)) {

            if (getByCategoryNameOriginDB.getStatus() == EntityStatus.DELETED) {
                Integer parentIdDTO = categoryentity.getParentId();

                if (Objects.nonNull(parentIdDTO)) {
                    categoryRepository.findByCategoryId(parentIdDTO).orElseThrow(() -> {
                        throw new CategoryNotFoundException(parentIdDTO + " parent id not found!");
                    });
                    getByCategoryNameOriginDB.setParentId(categoryentity.getParentId());
                }
                categoryentity.setId(getByCategoryNameOriginDB.getId());
            } else {
                throw new CategoryNotFoundException(categoryentity.getName() + " such a category exists!");
            }
        }
    }
}
