package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.CategoryConvert;
import uz.tsx.controller.convert.RegionConvert;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.RegionDto;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.dtoUtil.ResponseMessage;
import uz.tsx.dto.request.RegionCreateRequestDto;
import uz.tsx.dto.request.RegionUpdateRequestDto;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.entity.RegionEntity;
import uz.tsx.service.RegionService;

import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/region")
@RequiredArgsConstructor
@Tag(name = "Region Controller", description = "API for managing regions and locations.")
public class RegionController {

    private final RegionService regionService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method for post", description = "This method Region add")
    @PostMapping("/add")
    public ApiResponse<Object> addRegion(@RequestBody @Valid RegionCreateRequestDto regionDto) {

        RegionEntity region = RegionConvert.convertToEntity(regionDto);
        boolean regionSave = regionService.add(region);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(regionSave)
                .message(ResponseMessage.OK);

    }

    @Operation(summary = "Get Region Child", description = "This method retrieves the regin along with its descendants in a tree structure based on the provided ID.")
    @GetMapping("/get/child/{id}")
    public ApiResponse<Object> getCategoryChildId(@PathVariable Long id) {

        RegionEntity getRegionDB = regionService.getById(id);
        RegionDto dto = RegionConvert.fromOneLevelChild(getRegionDB);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);
    }

    @Operation(summary = "This method for getId", description = "This method Region getId")
    @GetMapping("/get/tree/{id}")
    public ApiResponse<Object> getRegionIdTree(@PathVariable Long id) {

        RegionEntity region = regionService.getByIdTree(id);
        RegionDto fromTree = RegionConvert.fromTree(region);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(fromTree)
                .message(ResponseMessage.OK);

    }

    @Operation(summary = "This method for getId", description = "This method Region getId")
    @GetMapping("/get/{id}")
    public ApiResponse<Object> getRegionId(@PathVariable Long id) {

        RegionEntity region = regionService.getById(id);
        RegionDto responseRegionDto = RegionConvert.fromNoTree(region);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(responseRegionDto)
                .message(ResponseMessage.OK);
    }

    @Operation(summary = "This method for getAll", description = "This method user getAll")
    @GetMapping("/get/all")
    public ApiResponse<Object> getAllRegion() {

        List<RegionEntity> allRegion = regionService.getAll();
        List<RegionDto> regionList = RegionConvert.fromNoTree(allRegion);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(regionList)
                .message(ResponseMessage.OK);
    }

    @Operation(summary = "This method for getAll", description = "This method user getAll")
    @GetMapping("/get/all-tree")
    public ApiResponse<Object> getAllTreeRegion() {

        List<RegionEntity> allRegionTree = regionService.getAllTree();
        List<RegionDto> regionTreeList = RegionConvert.fromTree(allRegionTree);
        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(regionTreeList)
                .message(ResponseMessage.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method for Post", description = "This method user update")
    @PatchMapping("/update/{id}")
    public ApiResponse<Object> update(@RequestBody RegionUpdateRequestDto regionDto, @PathVariable Long id) {

        RegionEntity regionEntity = RegionConvert.convertToEntity(regionDto);
        boolean isUpdate = regionService.update(regionEntity, id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdate)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method for Delete", description = "This method user delete")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> deleteRegion(@PathVariable Long id) {

        regionService.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(true)
                .message(ResponseMessage.OK);
    }

}
