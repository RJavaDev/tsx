package uz.tsx.service;

import uz.tsx.entity.UserEntity;
import uz.tsx.interfaces.UserInterface;

public interface UserService extends BaseInterface<UserInterface>{

    Boolean updateMe(UserEntity userUpdate, String attachId, Integer regionId);

    Boolean updateById(UserEntity userUpdate, Integer id);
}
