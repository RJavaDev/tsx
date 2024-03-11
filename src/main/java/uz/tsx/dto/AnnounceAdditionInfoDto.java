package uz.tsx.dto;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.entity.announcement.additionInfo.AdditionType;

@Getter
@Setter
public class AnnounceAdditionInfoDto {
    private Integer additionGroupId;

    private AdditionType additionType;

    private Object value;               // if additionType is COMBOBOX, then set value (Integer) comboId
}
