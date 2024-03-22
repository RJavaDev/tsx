package uz.tsx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.service.OptionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tsx_option")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping("/list")
    public ApiResponse<Object> getOptionsByGroup(@RequestParam(value = "groupId", required = false) Long groupId) {
        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(optionService.findOptionsByGroupId(groupId))
                .message(ResponseMessage.OK);
    }
}
