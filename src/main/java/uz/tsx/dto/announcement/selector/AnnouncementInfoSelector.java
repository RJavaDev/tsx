package uz.tsx.dto.announcement.selector;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionGroupDto;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionInfoDto;
import uz.tsx.entity.announcement.additionInfo.AdditionType;

@Getter
@Setter
public class AnnouncementInfoSelector extends AnnounceAdditionInfoDto {
    private AnnounceAdditionGroupDto additionGroup;

    public String getComboUrl() {
        String url = "";

        if (getAdditionType() == AdditionType.COMBOBOX) {
            url += "/tsx_add_group/list";

            if(getAdditionGroupId() != null) {
                url += "?groupId=" + getAdditionGroupId();
            }
        }

        return url;
    }
}
