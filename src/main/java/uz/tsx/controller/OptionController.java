package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.OptionConvert;
import uz.tsx.dto.announcement.option.OptionDto;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.OptionCreateDto;
import uz.tsx.entity.announcement.option.OptionEntity;
import uz.tsx.service.OptionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tsx-option")
public class OptionController {

    private final OptionService service;

    public OptionController(OptionService optionService) {
        this.service = optionService;
    }

    @PostMapping("/add")
    @Operation(summary = "Add new option", description = "Add a new option to the system.")
    public ApiResponse<Object> addOption(@RequestBody @Validated OptionCreateDto dto){

        OptionEntity entity = OptionConvert.convertToEntity(dto);
        boolean isAdd = service.add(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isAdd)
                .message(ResponseMessage.OK);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get option by ID", description = "Retrieve an option from the system by its ID.")
    public ApiResponse<Object> getById(@PathVariable Long id){

        OptionEntity optionEntity = service.getById(id);
        OptionDto dto = OptionConvert.convertToDto(optionEntity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all options", description = "Retrieve all options from the system.")
    public ApiResponse<Object> getALL(){

        List<OptionEntity> optionEntityList = service.getAll();
        List<OptionDto> dto = OptionConvert.convertToDto(optionEntityList);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);
    }

    @GetMapping("/list")
    @Operation(summary = "Get options by group", description = "Retrieve a list of options belonging to a specific group.")
    public ApiResponse<Object> getOptionsByGroup(@RequestParam(value = "groupId", required = false) Long groupId) {

        List<OptionEntity> optionsByGroupId = service.findOptionsByGroupId(groupId);
        List<OptionDto> optionList = OptionConvert.convertToDto(optionsByGroupId);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(optionList)
                .message(ResponseMessage.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete option by ID", description = "Delete an option from the system by its ID.")
    public ApiResponse<Object> delete(@PathVariable Long id){

       service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(Boolean.TRUE)
                .message(ResponseMessage.OK);
    }
}