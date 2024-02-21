package uz.tsx.service;

import uz.tsx.entity.UserEntity;

public interface UserService {

    Boolean updateMe(UserEntity userUpdate, String attachId);

    Boolean updateById(UserEntity userUpdate, Integer id);

    void delete(Integer id);

    UserEntity getById(Integer id);
}
