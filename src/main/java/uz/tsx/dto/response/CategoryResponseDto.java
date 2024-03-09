package uz.tsx.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseDto {

    private String name_uz;
    private String name_ru;
    private String name_en;

    private Long parentId;

    private List<CategoryResponseDto> child;

    private AttachUrlResponse attach;

}
