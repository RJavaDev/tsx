package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.config.token.JwtService;
import uz.tsx.constants.Characters;
import uz.tsx.constants.EntityStatus;
import uz.tsx.controller.convert.TokenResponseConvert;
import uz.tsx.dto.request.LoginRequestDto;
import uz.tsx.dto.response.TokenResponseDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.role.RoleEnum;
import uz.tsx.exception.AuthenticationException;
import uz.tsx.exception.UserDataException;
import uz.tsx.exception.UserUnauthorizedAction;
import uz.tsx.interfaces.UserInterface;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.AuthenticationService;
import uz.tsx.validation.CommonSchemaValidator;
import uz.tsx.validation.UserValidation;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final CommonSchemaValidator commonSchemaValidator;


    @Override
    public TokenResponseDto register(UserEntity request) {

        UserEntity userEntity = saveUser(request);
        String jwtToken = jwtService.generateToken(userEntity);

        return TokenResponseConvert.from(jwtToken, userEntity);

    }

    @Override
    public TokenResponseDto authenticate(LoginRequestDto request) {

        String emailOrPhone = request.getEmailOrPhone();

        if(!UserValidation.isUserEmailAddress(emailOrPhone)){
            String phoneNumberByPattern = UserValidation.getPhoneNumberByPattern(emailOrPhone);
            if(Objects.nonNull(phoneNumberByPattern)){
                emailOrPhone = Characters.PHONE_NUMBER_PREFIX + phoneNumberByPattern;
            }
        }

        UserInterface userDB = verifyUserInterface(emailOrPhone, request.getPassword());
        String jwt = jwtService.generateToken(userDB);

        return TokenResponseConvert.from(jwt, userDB);
    }

    private UserEntity saveUser(UserEntity user) throws UserDataException {

        UserValidation.validateUsername(user);

        commonSchemaValidator.userPasswordAndPhoneNumberCheck(user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userForCreate(user);

        user.setStatus(EntityStatus.CREATED);

        return repository.save(user);

    }

    private void userForCreate(UserEntity user) {

        var userEntity = SecurityUtils.getUser();

        if (userEntity != null) {
            if (userRoleAdminVerify(userEntity.getRoleEnumList())) {
                user.forCreate(userEntity.getId());
            } else {
                throw new UserUnauthorizedAction(userEntity.getId() + " User Unauthorized action!!!");
            }
        } else
            user.forCreate();
    }

    private boolean userRoleAdminVerify(List<RoleEnum> roleEnumList) {
        for (RoleEnum e : roleEnumList) {
            if (e == RoleEnum.ADMIN)
                return true;
        }
        return false;
    }

    private UserEntity verifyUser(String username, String password) {

        UserEntity user = commonSchemaValidator.validateUserEntity(username);

        verifyPassword(password, user.getPassword());

        return user;
    }

    private UserInterface verifyUserInterface(String username, String password) {

        UserInterface userDB = commonSchemaValidator.validateUser(username);

        verifyPassword(password, userDB.getPassword());

        return userDB;
    }

    private void verifyPassword(String passwordDto, String userPassword) {
        if (!passwordEncoder.matches(passwordDto, userPassword)) {
            throw new AuthenticationException("Incorrect password");
        }
    }

}
