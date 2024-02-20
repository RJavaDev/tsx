package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tsx.controller.convert.UserConvert;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.dto.request.LoginRequestDto;
import uz.tsx.dto.request.UserCreateRequestDto;
import uz.tsx.dto.response.TokenResponseDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.service.AuthenticationService;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "This Controller for login and register")
public class AuthenticationController {

    private final AuthenticationService service;


    @Operation(summary = "User Registration", description = "This method is used for user registration")
    @PostMapping("/register")
    public HttpResponse<Object> register(@RequestBody UserCreateRequestDto userDto) {

        UserEntity userEntity = UserConvert.convertToEntity(userDto);
        TokenResponseDto register = service.register(userEntity);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(register)
                .message("successfully!!!");
    }

    @Operation(summary = "User Login", description = "This method is used for user authentication and login")
    @PostMapping("/login")
    public HttpResponse<Object> authenticate(@RequestBody LoginRequestDto request) {

        TokenResponseDto authenticate = service.authenticate(request);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(authenticate)
                .message("successfully!!!");

    }


}
