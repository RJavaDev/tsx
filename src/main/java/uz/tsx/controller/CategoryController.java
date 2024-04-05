package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.tsx.controller.convert.CategoryConvert;
import uz.tsx.dto.CategoryDto;
import uz.tsx.dto.dtoUtil.*;
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.request.CategoryCreateRequestDto;
import uz.tsx.dto.request.CategoryUpdateRequestDto;
import uz.tsx.entity.CategoryEntity;
import uz.tsx.service.CategoryService;

import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "This controller manages the categories.")
public class CategoryController {

    private final CategoryService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Add Category", description = "This method adds a new category. If no parentId is provided, the added category will be considered as a parent category.")
    @PostMapping("/add")
    public ApiResponse<Object> addCategory(@RequestBody @Validated CategoryCreateRequestDto categoryDto) {

        CategoryEntity category = CategoryConvert.convertToEntity(categoryDto);
        boolean categorySave = service.add(category, categoryDto.getAttachId());

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(categorySave)
                .message(ResponseMessage.OK);

    }


    @Operation(summary = "Get Category Tree", description = "This method retrieves the category along with its descendants in a tree structure based on the provided ID.")
    @GetMapping("/get/tree/{id}")
    public ApiResponse<Object> getCategoryIdTree(@PathVariable Long id) {

        CategoryEntity category = service.getById(id);
        CategoryDto dto = CategoryConvert.fromTree(category);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);

    }

    @Operation(summary = "Get Category by ID", description = "This method retrieves a category based on the provided ID without any tree structure.")
    @GetMapping("/get/{id}")
    public ApiResponse<Object> getCategoryId(@PathVariable Long id) {

        CategoryEntity category = service.getById(id);
        CategoryDto categoryDto = CategoryConvert.fromNoTree(category);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(categoryDto)
                .message(ResponseMessage.OK);

    }

    @Operation(summary = "Get All Categories", description = "This method retrieves all categories without any tree structure.")
    @GetMapping("/get/all")
    public ApiResponse<Object> getAllCategory() {

        List<CategoryEntity> allCategory = service.getAll();
        List<CategoryDto> categoryNoTreeList = CategoryConvert.fromNoTree(allCategory);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(categoryNoTreeList)
                .message(ResponseMessage.OK);
    }

    @Operation(summary = "Get All Categories with Tree Structure", description = "This method retrieves all categories in a tree structure, starting from the root category and including all its descendants.")
    @GetMapping("/get/all-tree")
    public ApiResponse<Object> getAllTreeCategory() {

        List<CategoryEntity> allCategoryTree = service.getAllTree();
        List<CategoryDto> categoryTreeList = CategoryConvert.fromTree(allCategoryTree);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(categoryTreeList)
                .message(ResponseMessage.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Update Category by ID", description = "This method updates a category based on the provided ID.")
    @PatchMapping("/update/{id}")
    public ApiResponse<Object> update(@RequestBody CategoryUpdateRequestDto categoryDto, @PathVariable Long id) {

        CategoryEntity category = CategoryConvert.convertToEntity(categoryDto);
        boolean isUpdate = service.update(category, id, categoryDto.getAttachId());

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdate)
                .message(ResponseMessage.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @Operation(summary = "Delete Category by ID", description = "This method deletes a category by its ID. If the category is a parent, its children are automatically deleted.")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> deleteCategory(@PathVariable Long id) {

        service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(true)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/tree/top/ancestors")
    public HttpResponse<CategoryDto> findTreeFromBottom(@RequestParam("childId") Long childId) {
        return HttpResponse.build(false, "OK", service.findTreeFromBottom(childId));
    }
}
