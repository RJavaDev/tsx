package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.RegionConvert;
import uz.tsx.dto.RegionDto;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.dto.request.RegionCreateRequestDto;
import uz.tsx.dto.request.RegionUpdateRequestDto;
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
    public HttpResponse<Object> addRegion(@RequestBody RegionCreateRequestDto regionDto) {

        RegionEntity region = RegionConvert.convertToEntity(regionDto);
        boolean regionSave = regionService.add(region);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(regionSave)
                .message(HttpResponse.Status.OK.name());

    }

    @Operation(summary = "This method for getId", description = "This method Region getId")
    @GetMapping("/get/tree/{id}")
    public HttpResponse<Object> getRegionIdTree(@PathVariable Long id) {

        RegionEntity region = regionService.getByIdTree(id);
        RegionDto fromTree = RegionConvert.fromTree(region);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(fromTree)
                .message(HttpResponse.Status.OK.name());

    }

    @Operation(summary = "This method for getId", description = "This method Region getId")
    @GetMapping("/get/{id}")
    public HttpResponse<Object> getRegionId(@PathVariable Long id) {

        RegionEntity region = regionService.getById(id);
        RegionDto responseRegionDto = RegionConvert.fromNoTree(region);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(responseRegionDto)
                .message(HttpResponse.Status.OK.name());
    }

    @Operation(summary = "This method for getAll", description = "This method user getAll")
    @GetMapping("/get/all")
    public HttpResponse<Object> getAllRegion() {

        List<RegionEntity> allRegion = regionService.getAll();
        List<RegionDto> regionList = RegionConvert.fromNoTree(allRegion);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(regionList)
                .message(HttpResponse.Status.OK.name());
    }

    @Operation(summary = "This method for getAll", description = "This method user getAll")
    @GetMapping("/get/all-tree")
    public HttpResponse<Object> getAllTreeRegion() {

        List<RegionEntity> allRegionTree = regionService.getAllTree();
        List<RegionDto> regionTreeList = RegionConvert.fromTree(allRegionTree);
        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(regionTreeList)
                .message(HttpResponse.Status.OK.name());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method for Post", description = "This method user update")
    @PatchMapping("/update/{id}")
    public HttpResponse<Object> update(@RequestBody RegionUpdateRequestDto regionDto, @PathVariable Long id) {

        RegionEntity regionEntity = RegionConvert.convertToEntity(regionDto);
        boolean isUpdate = regionService.update(regionEntity, id);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(isUpdate)
                .message(HttpResponse.Status.OK.name());

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method for Delete", description = "This method user delete")
    @DeleteMapping("/delete/{id}")
    public HttpResponse<Object> deleteRegion(@PathVariable Long id) {

        regionService.delete(id);

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(true)
                .message(HttpResponse.Status.OK.name());
    }

}
