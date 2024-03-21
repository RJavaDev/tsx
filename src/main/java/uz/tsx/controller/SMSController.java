package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.tsx.dto.dtoUtil.HttpResponse;
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
    public HttpResponse<Object> sendSms() {

        boolean save = smsCodeService.save();

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(save)
                .message(HttpResponse.Status.OK.name());

    }

    @Operation(summary = "This method for get", description = "This is method to activate the user by sms code")
    @PostMapping(value = "/activate")
    public HttpResponse<Object> isValidSMSCode(@RequestBody SMSCodeDto smsCode) {

        boolean valid = smsCodeService.isValid(smsCode.getSmsCode());

        if (valid) {
            userService.userActive();
        }

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(valid)
                .body(true)
                .message(HttpResponse.Status.OK.name());

    }
}
