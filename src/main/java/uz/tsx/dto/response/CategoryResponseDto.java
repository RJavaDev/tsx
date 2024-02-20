package uz.tsx.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseDto {

    private String name;

    private Integer parentId;

    private List<CategoryResponseDto> child;

    private AttachUrlResponse attach;

}
