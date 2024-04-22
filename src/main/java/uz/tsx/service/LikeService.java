package uz.tsx.service;

import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.entity.LikeEntity;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.UserInterface;

import java.util.List;

public interface LikeService extends BaseInterface<LikeEntity>{

   Boolean add(Long  announcementId);

   List<AnnouncementDto> getMyLike();

   List<UserInterface> myGetUsers(Long announcementId);
}
