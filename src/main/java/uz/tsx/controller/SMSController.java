package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@Tag(name = "User Controller", description = "This Controller manages the user for Admin")
public class SMSController {

    private final SMSCodeService smsCodeService;

    private final UserService userService;

    @Operation(summary = "This method for get", description = "This send sms email or phone")
    @GetMapping(value = "/send-sms")
    public ApiResponse<Object> sendSms() {

        boolean save = smsCodeService.save();

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(save)
                .message(ResponseMessage.OK);

    }

    @Operation(summary = "This method for get", description = "This is method to activate the user by sms code")
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
