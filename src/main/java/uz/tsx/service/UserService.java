package uz.tsx.service;

import uz.tsx.entity.UserEntity;

public interface UserService extends BaseInterface<UserEntity>{

    Boolean updateMe(UserEntity userUpdate, String attachId);

    Boolean updateById(UserEntity userUpdate, Integer id);
}
