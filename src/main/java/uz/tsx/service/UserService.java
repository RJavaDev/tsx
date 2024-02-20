package uz.tsx.service;

import uz.tsx.entity.UserEntity;

public interface UserService {

    Boolean updateUser(UserEntity userUpdate, String attachId, Integer regionId);
    Boolean updateMe(UserEntity userUpdate, String attachId);

    Boolean updateUserById(UserEntity userUpdate, Integer id);

    void userDelete(Integer id);

    UserEntity getUserById(Integer id);
}
