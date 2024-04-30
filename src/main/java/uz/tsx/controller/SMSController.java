package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.PassiveUser;
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

    @Operation(summary = "Send SMS", description = "This endpoint sends an SMS to the user")
    @PostMapping(value = "/send-sms")
    public ApiResponse<Object> sendSms(@RequestBody @Valid PassiveUser passiveUser) {

        boolean isSend = smsCodeService.send(passiveUser.getEmailOrPhone());

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isSend)
                .message(ResponseMessage.OK);

    }

    @Operation(summary = "Activate User with SMS Code", description = "This endpoint activates the user using an SMS code")
    @PostMapping(value = "/activate")
    public ApiResponse<Object> isValidSMSCode(@RequestBody @Valid SMSCodeDto smsCode) {

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
