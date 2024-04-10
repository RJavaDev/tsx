package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.AdditionGroupConvert;
import uz.tsx.dto.announcement.additionInfo.AdditionGroupDto;
import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.AdditionGroupCreate;
import uz.tsx.dto.request.AdditionGroupUpdate;
import uz.tsx.entity.announcement.additionInfo.AdditionGroupEntity;
import uz.tsx.service.AnnounceAdditionGroupService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announce-addition-group")
@Tag(name = "Announce Addition Group Controller", description = "Operations related to announcing addition groups")
@RequiredArgsConstructor
public class AnnounceAdditionGroupController {

    private final AnnounceAdditionGroupService service;

    @PostMapping("/add")
    @Operation(summary = "Add new option group", description = "Add a new option group to the system.")
    public ApiResponse<Object> add(@RequestBody @Valid AdditionGroupCreate dto){

        AdditionGroupEntity entity = AdditionGroupConvert.convertToEntity(dto);
        boolean added = service.add(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(added)
                .message(ResponseMessage.OK);

    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get option group by ID", description = "Retrieve an option group from the system by its ID.")
    public ApiResponse<Object> getById(@PathVariable Long id){

        AdditionGroupEntity entity = service.getById(id);
        AdditionGroupDto dto = AdditionGroupConvert.convertToDto(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);

    }

    @GetMapping("/list")
    public HttpResponse<List<AdditionComboValueDto>> listAnnounceGroupAdds(@RequestParam(value = "groupId") Long groupId) {
        return HttpResponse.build(true, "", service.listAnnounceAdditionGroup(groupId), HttpResponse.Status.OK.ordinal());
    }

    @GetMapping("/get/all")
    @Operation(summary = "Get all option groups", description = "Retrieve all option groups from the system.")
    public ApiResponse<Object> getAll(){

        List<AdditionGroupEntity> optionGroupEntityList = service.getAll();
        List<AdditionGroupDto> optionGroupDtoList = AdditionGroupConvert.convertToDto(optionGroupEntityList);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(optionGroupDtoList)
                .message(ResponseMessage.OK);

    }

    @PatchMapping("/update")
    @Operation(summary = "Update option group", description = "Update an existing option group in the system.")
    public ApiResponse<Object> update(@RequestBody AdditionGroupUpdate dto){

        AdditionGroupEntity entity = AdditionGroupConvert.convertToEntity(dto);
        boolean update = service.update(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(update)
                .message(ResponseMessage.OK);

    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete option group by ID", description = "Delete an option group from the system by its ID.")
    public ApiResponse<Object> delete(@PathVariable Long id){

        service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(Boolean.TRUE)
                .message(ResponseMessage.DELETE_SUCCESS_MESSAGE);

    }
}
