package uz.tsx.dto.dtoUtil;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BigDataTable<T> {

    private long total;

    private int totalPage;

    private List<T> rows;

    private Map<String, Object> footer;

}
