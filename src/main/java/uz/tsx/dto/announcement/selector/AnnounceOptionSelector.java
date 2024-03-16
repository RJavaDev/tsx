package uz.tsx.dto.announcement.selector;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.announcement.option.AnnounceOptionDto;
import uz.tsx.dto.announcement.option.OptionDto;

@Getter
@Setter
public class AnnounceOptionSelector extends AnnounceOptionDto {
    private OptionDto option;

    public String getOptionListUrl() {
        String url = "";
        if(getOptionGroupId() != null) {
            url += "/tsx_option/list?groupId=" + getOptionGroupId();
        }
        return url;
    }
}
