package uz.tsx.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.OperationGroupConvert;
import uz.tsx.dto.announcement.option.OptionGroupDto;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.OptionGroupCreateDto;
import uz.tsx.dto.request.OptionGroupUpdate;
import uz.tsx.entity.announcement.option.OptionGroupEntity;
import uz.tsx.service.OptionGroupService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/option-group")
@RequiredArgsConstructor
@Tag(name = "Option Group Controller", description = "Operations related to option groups")
public class OptionGroupController {

    private final OptionGroupService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add")
    @Operation(summary = "Add new option group", description = "Add a new option group to the system.")
    public ApiResponse<Object> add(@RequestBody @Valid OptionGroupCreateDto dto){

        OptionGroupEntity entity = OperationGroupConvert.convertToEntity(dto);
        OptionGroupEntity added = service.add(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(added)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/get/{id}")
    @Operation(summary = "Get option group by ID", description = "Retrieve an option group from the system by its ID.")
    public ApiResponse<Object> getById(@PathVariable Long id){

        OptionGroupEntity entity = service.getById(id);
        OptionGroupDto optionGroupDto = OperationGroupConvert.convertToDto(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(optionGroupDto)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/get/all")
    @Operation(summary = "Get all option groups", description = "Retrieve all option groups from the system.")
    public ApiResponse<Object> getAll(){

        List<OptionGroupEntity> optionGroupEntityList = service.getAll();
        List<OptionGroupDto> optionGroupDtoList = OperationGroupConvert.convertToDto(optionGroupEntityList);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(optionGroupDtoList)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping("/update")
    @Operation(summary = "Update option group", description = "Update an existing option group in the system.")
    public ApiResponse<Object> update(@RequestBody OptionGroupUpdate dto){

        OptionGroupEntity entity = OperationGroupConvert.convertToDto(dto);
        boolean isUpdate = service.update(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdate)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete option group by ID", description = "Delete an option group from the system by its ID.")
    public ApiResponse<Object> delete(@PathVariable Long id){

        service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(true)
                .message(ResponseMessage.DELETE_SUCCESS_MESSAGE);

    }
}
