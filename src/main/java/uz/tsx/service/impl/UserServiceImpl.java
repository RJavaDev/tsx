package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.entity.UserEntity;
import uz.tsx.interfaces.UserInterface;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.UserService;
import uz.tsx.validation.CommonSchemaValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    private final CommonSchemaValidator commonSchemaValidator;

    @Override
    @Transactional
    public Boolean updateMe(UserEntity userUpdate, String attachId) {

        UserEntity userOriginalDB = SecurityUtils.getUser();

        UserEntity userEntity = commonSchemaValidator.validateUserUpdate(userUpdate, userOriginalDB, attachId);
        userEntity.forUpdate();
        repository.save(userEntity);
        return true;
    }

    @Override
    public Boolean updateById(UserEntity userUpdate, Integer id) {

        UserEntity userOriginalDB = repository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("user username not found!")
        );

        updateUserSave(userUpdate, userOriginalDB);

        return true;
    }
    @Override
    public UserInterface getById(Integer id) {
        return commonSchemaValidator.validateUser(id);
    }

    @Override
    public List<UserInterface> getAll() {
        return repository.getAllUser();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        repository.userDelete(id);
    }


    private void updateUserSave(UserEntity userUpdate, UserEntity userOriginalDB) {

        userVerifyAndSetProperty(userUpdate, userOriginalDB);

        userOriginalDB.forUpdate(SecurityUtils.getUserId());

        repository.save(userOriginalDB);
    }

    private void userVerifyAndSetProperty(UserEntity userUpdate, UserEntity userOriginalDB) {
        if (!StringUtils.isEmpty(userUpdate.getFirstname())) userOriginalDB.setFirstname(userUpdate.getFirstname());
        if (!StringUtils.isEmpty(userUpdate.getUsername())) userOriginalDB.setUsername(userUpdate.getUsername());
        if (!StringUtils.isEmpty(userUpdate.getPhoneNumber()))
            userOriginalDB.setPhoneNumber(userUpdate.getPhoneNumber());
        if (!StringUtils.isEmpty(userUpdate.getPassword())) {
            userOriginalDB.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        }

    }

}
