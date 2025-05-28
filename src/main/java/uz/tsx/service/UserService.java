package uz.tsx.service;

import org.springframework.web.multipart.MultipartFile;
import uz.tsx.entity.UserEntity;
import uz.tsx.interfaces.UserInterface;

public interface UserService extends BaseInterface<UserInterface>{

    Boolean updateMe(UserEntity userUpdate, String attachId, Long regionId);

    Boolean updateById(UserEntity userUpdate, Long id);

    void userActive();

    UserInterface getMe(Long id);

    UserEntity userUpdateImage(MultipartFile image);
}
