package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.SecurityUtils;
import uz.tsx.controller.convert.UserConvert;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.UserUpdateRequestDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.interfaces.UserInterface;
import uz.tsx.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@EnableMethodSecurity
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "This Controller manages the user for Admin")
public class UserController {

    private final UserService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "This method for get", description = "This method is used to get how many points the admin user has scored")
    @GetMapping(value = "/info/{id}")
    public ApiResponse<Object> getUserInformation(@PathVariable Long id) {

        UserInterface userInformation = service.getById(id);
        UserDto userDto = UserConvert.from(userInformation);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(userDto)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @Operation(summary = "Get My User Information", description = "Retrieve all the information about the current authenticated user.")
    @GetMapping(value = "/get-me")
    public ApiResponse<Object> getMe() {

        UserEntity me = SecurityUtils.getUser();

        UserDto meInformation = UserConvert.from(Objects.requireNonNull(me));

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(meInformation)
                .message(ResponseMessage.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method for get", description = "This method provides information about all users to the administrator")
    @GetMapping(value = "/get/all")
    public ApiResponse<Object> getUserAll() {

        List<UserInterface> userInformation = service.getAll();
        List<UserDto> userDto = UserConvert.from(userInformation);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(userDto)
                .message(ResponseMessage.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @Operation(summary = "This method for update", description = "This method updates the user's data")
    @PatchMapping(value = "/update-me")
    public ApiResponse<Object> updateMe(@RequestBody UserUpdateRequestDto userUpdate) {

        UserEntity updateUser = UserConvert.convertToEntity(userUpdate);
        Boolean isUpdateUser = service.updateMe(updateUser, userUpdate.getAttachId());

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdateUser)
                .message(ResponseMessage.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method for update by id", description = "This method updates the user's data")
    @PutMapping("/update/{id}")
    public ApiResponse<Object> userUpdateId(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdate) {

        UserEntity updateUser = userUpdate.toEntity();
        Boolean isUpdateUser = service.updateById(updateUser, id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdateUser)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "This user for delete", description = "This method is designed to delete a user by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> userDelete(@PathVariable Long id) {

        service.delete(id);
        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(true)
                .message("User deleted successfully");
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @Operation(summary = "Delete My User ", description = "Delete all information about the current authenticated user.")
    @DeleteMapping(value = "/deleteMe")
    public ApiResponse<Object>deleteMe(){

       service.delete(SecurityUtils.getUserId());

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(true)
                .message(ResponseMessage.OK);
    }



}
