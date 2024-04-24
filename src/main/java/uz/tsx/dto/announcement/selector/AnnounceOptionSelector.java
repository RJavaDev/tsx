package uz.tsx.dto.announcement.selector;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import uz.tsx.dto.announcement.option.AnnounceOptionDto;
import uz.tsx.dto.announcement.option.OptionDto;

@Getter
@Setter
public class AnnounceOptionSelector extends AnnounceOptionDto {
    private OptionDto option;

    public String getOptionListUrl() {
        String url = "";
        if(getOptionGroupId() != null) {
            url += "/api/v1/tsx-option/list?groupId=" + getOptionGroupId();
        }
        return url;
    }

    public AnnounceOptionDto toDto() {
        AnnounceOptionDto dto = new AnnounceOptionDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }
}
