package uz.tsx.dto.dtoUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageParam {

    private Integer page = 1;

    private Integer size = 6;

    public PageParam() {
    }

    public PageParam(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
