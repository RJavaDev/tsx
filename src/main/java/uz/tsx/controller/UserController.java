package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.SecurityUtils;
import uz.tsx.controller.convert.UserConvert;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.dto.request.UserUpdateRequestDto;
import uz.tsx.entity.UserEntity;
import uz.tsx.service.UserService;

import java.util.Objects;

@RestController
@EnableMethodSecurity
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "This Controller manages the user for Admin")
public class UserController {

    private final UserService service;


    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    @Operation(summary = "This method for get", description = "This method is used to get how many points the admin user has scored")
    @GetMapping(value = "/info/{id}")
    public HttpResponse<Object> getUserInformation(@PathVariable Integer id) {

        UserDto userDto = UserConvert.from(service.getById(id));

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(userDto)
                .message(HttpResponse.Status.OK.name());

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @Operation(summary = "Get My User Information", description = "Retrieve all the information about the current authenticated user.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = HttpResponse.class)))
    @GetMapping(value = "/get-me")
    public HttpResponse<Object> getMe() {

        UserEntity me = SecurityUtils.getUser();

        UserDto meInformation = UserConvert.from(Objects.requireNonNull(me));

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(meInformation)
                .message(HttpStatus.OK.name());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @Operation(summary = "This method for update", description = "This method updates the user's data")
    @PatchMapping(value = "/update-me")
    public HttpResponse<Object> updateMe(@RequestBody UserUpdateRequestDto userUpdate) {

        UserEntity updateUser = UserConvert.convertToEntity(userUpdate);
        Boolean isUpdateUser = service.updateMe(updateUser, userUpdate.getAttachId());

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(isUpdateUser)
                .message(HttpStatus.OK.name());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @Operation(summary = "This method for update by id", description = "This method updates the user's data")
    @PutMapping("/update/{id}")
    public HttpResponse<Object> userUpdateId(@PathVariable Integer id, @RequestBody UserUpdateRequestDto userUpdate) {

        UserEntity updateUser = userUpdate.toEntity();
        Boolean isUpdateUser = service.updateById(updateUser, id);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(isUpdateUser)
                .message(HttpStatus.OK.name());

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "This user for delete", description = "This method is designed to delete a user by ID")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public HttpResponse<Object> userDelete(@PathVariable Integer id) {

        service.delete(id);
        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(true)
                .message("User deleted successfully");
    }


}
