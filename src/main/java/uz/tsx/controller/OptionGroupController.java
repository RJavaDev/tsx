package uz.tsx.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.OperationGroupConvert;
import uz.tsx.dto.announcement.option.OptionGroupDto;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.OptionGroupCreateDto;
import uz.tsx.entity.announcement.option.OptionGroupEntity;
import uz.tsx.service.OptionGroupService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/option-group")
@RequiredArgsConstructor
public class OptionGroupController {

    private final OptionGroupService service;

    @PostMapping("/add")
    public ApiResponse<Object> add(@RequestBody @Validated OptionGroupCreateDto dto){

        OptionGroupEntity entity = OperationGroupConvert.convertToEntity(dto);
        OptionGroupEntity added = service.add(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(added)
                .message(ResponseMessage.OK);

    }

    @GetMapping("/get/{id}")
    public ApiResponse<Object> getById(@PathVariable Long id){

        OptionGroupEntity entity = service.getById(id);
        OptionGroupDto optionGroupDto = OperationGroupConvert.convertToDto(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(optionGroupDto)
                .message(ResponseMessage.OK);

    }

    @GetMapping("/get/all")
    public ApiResponse<Object> getAll(){

        List<OptionGroupEntity> optionGroupEntityList = service.getAll();
        List<OptionGroupDto> optionGroupDtoList = OperationGroupConvert.convertToDto(optionGroupEntityList);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(optionGroupDtoList)
                .message(ResponseMessage.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id){

        service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(true)
                .message(ResponseMessage.DELETE_SUCCESS_MESSAGE);

    }
}
