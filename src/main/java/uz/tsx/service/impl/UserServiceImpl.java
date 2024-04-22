package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.constants.EntityStatus;
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
    public Boolean updateMe(UserEntity userUpdate, String attachId, Long regionId) {

        UserEntity userOriginalDB = SecurityUtils.getUser();

        UserEntity userEntity = commonSchemaValidator.validateUserUpdate(userUpdate, userOriginalDB, attachId, regionId);
        userEntity.forUpdate();
        repository.save(userEntity);
        return true;
    }

    @Override
    public Boolean updateById(UserEntity userUpdate, Long id) {

        UserEntity userOriginalDB = repository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("user username not found!")
        );

        updateUserSave(userUpdate, userOriginalDB);

        return true;
    }

    @Override
    public void userActive() {
        UserEntity user = SecurityUtils.getUser();
        user.setStatus(EntityStatus.CREATED);
        repository.save(user);
    }

    @Override
    public UserInterface getMe(Long id) {
        commonSchemaValidator.validateID(id);
        return repository.getMe(id);
    }

    @Override
    public UserInterface getById(Long id) {
        return commonSchemaValidator.validateUser(id);
    }

    @Override
    public List<UserInterface> getAll() {
        return repository.getAllUser();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.userDelete(id);
    }


    private void updateUserSave(UserEntity userUpdate, UserEntity userOriginalDB) {

        userVerifyAndSetProperty(userUpdate, userOriginalDB);

        userOriginalDB.forUpdate(SecurityUtils.getUserId());

        repository.save(userOriginalDB);
    }

    private void userVerifyAndSetProperty(UserEntity userUpdate, UserEntity userOriginalDB) {

        String firstname = userUpdate.getFirstname();
        String username = userUpdate.getUsername();
        String password = userUpdate.getPassword();

        if (!StringUtils.isEmpty(firstname)) userOriginalDB.setFirstname(firstname);

        if (!StringUtils.isEmpty(username)) userOriginalDB.setEmailOrPhone(username);

        if (!StringUtils.isEmpty(password)) {
            userOriginalDB.setPassword(passwordEncoder.encode(password));
        }

    }

}
