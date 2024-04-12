package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.AnnounceAdditionComboValueConvert;
import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.AdditionComboValueCreate;
import uz.tsx.dto.request.AdditionComboValueUpdate;
import uz.tsx.entity.announcement.additionInfo.AdditionComboValueEntity;
import uz.tsx.service.AnnounceAdditionComboValueService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addition-combo-value")
@Tag(name = "Announce Addition Combo Value Controller", description = "Operations related to announcing addition combo values")
@RequiredArgsConstructor
public class AnnounceAdditionComboValueController {

    private final AnnounceAdditionComboValueService service;

    @PostMapping("/add")
    @Operation(summary = "Add new addition combo value", description = "Add a new addition combo value to the system.")
    public ApiResponse<Object> add(@RequestBody @Valid AdditionComboValueCreate dto) {

        AdditionComboValueEntity addedEntity = AnnounceAdditionComboValueConvert.convertToEntity(dto);
        boolean added = service.add(addedEntity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(added)
                .message(ResponseMessage.OK);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get addition combo value by ID", description = "Retrieve an addition combo value from the system by its ID.")
    public ApiResponse<Object> getById(@PathVariable Long id) {

        AdditionComboValueEntity comboValueById = service.getById(id);
        AdditionComboValueDto dto = AnnounceAdditionComboValueConvert.convertToDto(comboValueById);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);
    }

    @GetMapping("/get/group/{id}")
    @Operation(summary = "Get combo value by addition group ID", description = "Retrieve combo values from Addition Combo Value by the group ID.")
    public ApiResponse<Object> getComboValueByAdditionGroupId(@PathVariable Long id) {

        List<AdditionComboValueEntity> comboValueById = service.getComboValueByGroupId(id);
        List<AdditionComboValueDto> dto = AnnounceAdditionComboValueConvert.convertToDto(comboValueById);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);
    }



    @PatchMapping("/update")
    @Operation(summary = "Update addition combo value", description = "Update an existing addition combo value in the system.")
    public ApiResponse<Object> update(@RequestBody AdditionComboValueUpdate updateDto) {

        AdditionComboValueEntity updateEntity = AnnounceAdditionComboValueConvert.convertToDto(updateDto);
        boolean isUpdate = service.update(updateEntity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdate)
                .message(ResponseMessage.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete addition combo value by ID", description = "Delete an addition combo value from the system by its ID.")
    public ApiResponse<Object> delete(@PathVariable Long id) {

        service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(Boolean.TRUE)
                .message(ResponseMessage.OK);
    }
}
