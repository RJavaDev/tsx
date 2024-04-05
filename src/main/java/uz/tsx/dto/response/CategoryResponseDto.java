package uz.tsx.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseDto {

    private String nameUz;
    private String nameEn;
    private String nameRu;

    private Long parentId;

    private List<CategoryResponseDto> child;

    private AttachUrlResponse attach;

}
