package uz.tsx.service;

import uz.tsx.entity.UserEntity;
import uz.tsx.interfaces.UserInterface;

public interface UserService extends BaseInterface<UserInterface>{

    Boolean updateMe(UserEntity userUpdate, String attachId, Long regionId);

    Boolean updateById(UserEntity userUpdate, Long id);

    void userActive();

    UserInterface getMe(Long id);
}
