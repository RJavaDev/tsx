package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.AnnouncementConvert;
import uz.tsx.controller.convert.CategoryConvert;
import uz.tsx.controller.convert.UserConvert;
import uz.tsx.dto.UserDto;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.CategoryCreateRequestDto;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.UserInterface;
import uz.tsx.service.AnnouncementService;
import uz.tsx.service.LikeService;

import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
@Tag(name = "Like Controller", description = "This controller manages the like.")
public class LikeController {

    private final LikeService likeService;


    @Operation(summary = "Add Like", description = "This method adds (on,of) a new Like.")
    @PostMapping("/add")
    public ApiResponse<Object> addCategory(@RequestParam("announcementId")  Long announcementId) {

        Boolean add = likeService.add(announcementId);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(add)
                .message(ResponseMessage.OK);

    }
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get My Like", description = "the ones i like")
    @GetMapping("/getMyLike")
    public ApiResponse<Object> getMyLike(){

        List<AnnouncementDto> myLike = likeService.getMyLike();



        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(myLike)
                .message(ResponseMessage.OK);

    }
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get My Like users All", description = "Get my like users")
    @GetMapping("/MyGetUsers")
    public ApiResponse<Object> getMyLikeAnnouncementAll(@RequestParam("announcementId")  Long announcementId) {

       List<UserInterface> userEntities=likeService.myGetUsers(announcementId);
        List<UserDto> from = UserConvert.from(userEntities);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(from)
                .message(ResponseMessage.OK);

    }



}
