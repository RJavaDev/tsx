package uz.tsx.service;

import uz.tsx.entity.UserEntity;

public interface UserService {
    Boolean updateUser(UserEntity userUpdate, String attachId, Integer regionId);
    Boolean updateUserById(UserEntity userUpdate, Integer id);
    Boolean userDelete(Integer id);
    UserEntity getUserById(Integer id);
}
