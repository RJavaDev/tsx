package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.controller.convert.AnnouncementConvert;
import uz.tsx.controller.convert.SecurityUtils;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.entity.LikeEntity;
import uz.tsx.entity.UserEntity;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.UserInterface;
import uz.tsx.repository.LikeRepository;
import uz.tsx.repository.UserRepository;
import uz.tsx.service.LikeService;
import uz.tsx.validation.CommonSchemaValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository repository;

    private final CommonSchemaValidator validator;

    private final UserServiceImpl userServiceImpl;

    @Override
    public List<AnnouncementDto> getMyLike() {
    return repository.getMyLike(SecurityUtils.getUserId()).stream().map(
                id -> AnnouncementConvert.convertToDto(validator.validateAnnouncementId(id))).toList();


    }

    @Override
    public List<UserInterface> myGetUsers(Long announcementId) {

        return repository.myGetUsers(SecurityUtils.getUserId(), announcementId)
                    .stream().map(userServiceImpl::getById).toList();
    }

    @Override
    public LikeEntity getById(Long id) {
        return null;
    }

    @Override
    public Boolean add(Long announcementId) {
        LikeEntity likeEntity = new LikeEntity();
        UserEntity user = SecurityUtils.getUser();
        AnnouncementEntity announcement = validator.validateAnnouncementId(announcementId);
        if (disLike(announcementId, user.getId())){
                return false;
        }

            likeEntity.setAnnouncement(announcement);
            likeEntity.setLikeStatus(LikeEntity.LikeStatus.LIKE);
            likeEntity.forCreate(user.getId());
            repository.save(likeEntity);

        return true;
    }


    private boolean  disLike(Long announcementId, Long userId){
        try {
            if (repository.satatus(announcementId, userId)>0){
                return false;
            }else {
                repository.disLike(announcementId, userId);
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }

        @Override
        public List<LikeEntity> getAll () {
            return null;
        }

        @Override
        @Transactional
        public void delete (Long id){
        }

}
