package uz.tsx.service;

import uz.tsx.entity.UserEntity;

public interface UserService {

    Boolean updateMe(UserEntity userUpdate, String attachId);

    Boolean updateUserById(UserEntity userUpdate, Integer id);

    void userDelete(Integer id);

    UserEntity getUserById(Integer id);
}
