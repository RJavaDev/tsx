package uz.tsx.dto.announcement.selector;

import lombok.Getter;
import lombok.Setter;
import uz.tsx.dto.announcement.AnnounceAdditionInfoDto;
import uz.tsx.entity.announcement.additionInfo.AdditionType;

@Getter
@Setter
public class AnnouncementInfoSelector extends AnnounceAdditionInfoDto {
    public String getComboUrl() {
        String url = "";

        if (getAdditionType() == AdditionType.COMBOBOX) {
            url += "/tsx_add_group";

            if(getAdditionGroupId() != null) {
                url += "?groupId=" + getAdditionGroupId() + "&categoryId=" + getCategoryId();
            }

            else if(getCategoryId() != null) {
                url += "?categoryId=" + getCategoryId();
            }
        }

        return url;
    }
}
