package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.config.token.JwtService;
import uz.tsx.controller.convert.TokenResponseConvert;
import uz.tsx.dto.request.LoginRequestDto;
import uz.tsx.dto.response.TokenResponseDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.role.RoleEnum;
import uz.tsx.exception.AuthenticationException;
import uz.tsx.exception.UserDataException;
import uz.tsx.exception.UserUnauthorizedAction;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.AuthenticationService;
import uz.tsx.validation.CommonSchemaValidator;

import java.util.List;

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

        UserEntity user = verifyUser(request.getUsername(),request.getPassword());
        String jwt = jwtService.generateToken(user);

        return TokenResponseConvert.from(jwt, user);
    }

    private UserEntity saveUser(@NotNull UserEntity user) throws UserDataException {

        commonSchemaValidator.userPasswordAndPhoneNumberCheck(user.getUsername(), user.getPhoneNumber());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userForCreate(user);

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
        UserEntity entity = repository.getByUsername(username).orElseThrow(
                () -> new AuthenticationException(username + " username not found!")
        );

        verifyPassword(password, entity.getPassword());

        return entity;
    }

    private void verifyPassword(String passwordDto, String userPassword) {
        if (!passwordEncoder.matches(passwordDto, userPassword)) {
            throw new AuthenticationException("Incorrect password");
        }
    }

}
