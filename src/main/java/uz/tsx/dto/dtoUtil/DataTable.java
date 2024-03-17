package uz.tsx.dto.dtoUtil;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DataTable<T> {
    private int total;

    private List<T> rows;

    private Map<String, Object> footer;

}
