package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.dto.dtoUtil.FilterForm;
import uz.tsx.entity.UserEntity;
import uz.tsx.repository.UserRepository;
import uz.tsx.validation.CommonSchemaValidator;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;

    private final CommonSchemaValidator commonSchemaValidator;
    private final Logger log = LoggerFactory.getLogger(getClass().getName());


    @Transactional
    public Boolean updateMe(UserEntity userUpdate, String attachId, Integer regionId) {

        UserEntity userOriginalDB = SecurityUtils.getUser();

        UserEntity userEntity = commonSchemaValidator.validateUserUpdate(userUpdate, userOriginalDB, attachId, regionId);
        userEntity.forUpdate();
        repository.save(userEntity);
        return true;
    }

    @Transactional
    public Boolean updateUserById(UserEntity userUpdate, Integer id) {

        UserEntity userOriginalDB = repository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("user username not found!")
        );

        updateUserSave(userUpdate, userOriginalDB);

        return true;
    }

    @Transactional
    public Boolean userDelete(Integer id) {
        Integer userDeleteIsSuccess = repository.userDelete(id);
        return userDeleteIsSuccess > 0;
    }


    private void updateUserSave(UserEntity userUpdate, UserEntity userOriginalDB) {
        userVerifyAndSetProperty(userUpdate, userOriginalDB);

        log.atInfo().log("user update");

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

    public Sort orderSortField(String field) {
        return Sort.by(Sort.Order.by(field));
    }

    public Pageable pageable(Sort sort, FilterForm filterForm) {
        return PageRequest.of(filterForm.getStart() / filterForm.getLength(), filterForm.getLength(), sort);
    }


}
