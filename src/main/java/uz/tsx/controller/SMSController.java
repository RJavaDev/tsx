package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.SMSCodeDto;
import uz.tsx.service.UserService;
import uz.tsx.SMS.service.SMSCodeService;

@RestController
@EnableMethodSecurity
@RequestMapping("api/v1/sms")
@RequiredArgsConstructor
@Tag(name = "SMS Controller", description = "This Controller manages SMS operations")
public class SMSController {

    private final SMSCodeService smsCodeService;

    private final UserService userService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @Operation(summary = "Send SMS", description = "This endpoint sends an SMS to the user")
    @GetMapping(value = "/send-sms")
    public ApiResponse<Object> sendSms() {

        boolean save = smsCodeService.save();

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(save)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @Operation(summary = "Activate User with SMS Code", description = "This endpoint activates the user using an SMS code")
    @PostMapping(value = "/activate")
    public ApiResponse<Object> isValidSMSCode(@RequestBody SMSCodeDto smsCode) {

        boolean valid = smsCodeService.isValid(smsCode.getSmsCode());

        if (valid) {
            userService.userActive();
        }

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(valid)
                .message(ResponseMessage.OK);

    }
}
