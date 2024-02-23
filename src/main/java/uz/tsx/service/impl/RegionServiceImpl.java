package uz.tsx.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tsx.common.util.SecurityUtils;
import uz.tsx.constants.EntityStatus;
import uz.tsx.entity.RegionEntity;
import uz.tsx.exception.CategoryNotFoundException;
import uz.tsx.exception.RegionNotFoundException;
import uz.tsx.repository.RegionRepository;
import uz.tsx.service.RegionService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository repository;

    @Override
    public boolean add(RegionEntity regionEntity) {
        Integer userId = SecurityUtils.getUserId();
        RegionEntity byCreatedByName = repository.findByRegionName(regionEntity.getName());
        if (byCreatedByName != null) {
             regionStatusCheckAndSave(byCreatedByName, regionEntity, userId);
             return true;
        }
        regionEntity.forCreate(userId);
        repository.save(regionEntity);
        return true;
    }

    @Override
    public RegionEntity getById(Integer id) {
        if (id == null) return null;

        return repository.getRegionId(id).orElseThrow(
                () -> new RegionNotFoundException(id + "-id not found!!!")
        );

    }

    @Override
    public RegionEntity getByIdTree(Integer id) {
        if (id == null) return null;

        return repository.findById(id).orElseThrow(
                () -> new RegionNotFoundException(id + "-id not found")
        );
    }

    @Override
    public List<RegionEntity> getAll() {
        return repository.findAllRegion();
    }

    @Override
    public List<RegionEntity> getAllTree() {
        return repository.getRegionAllTree();}

    @Override
    public boolean update(RegionEntity newUpdateObject, Integer regionId) {

        RegionEntity entity = childIdAndParentIdVerify(newUpdateObject,regionId);

        entity.setParentId(newUpdateObject.getParentId());
        String updateObjectName = newUpdateObject.getName();
        if(!updateObjectName.isEmpty()){
            deletedObjectOriginDataBase(updateObjectName);
            entity.setName(updateObjectName);
        }
        entity.forUpdate(SecurityUtils.getUserId());

        repository.save(entity);
        return true;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (id != null) {
            repository.findByRegionId(id).orElseThrow(
                    () -> new RegionNotFoundException(id + " id not found!!!"));
        }
        repository.regionDelete(id);
    }

    private void regionStatusCheckAndSave(RegionEntity byCreatedByName, RegionEntity regionEntity, Integer userId) {

        if (byCreatedByName.getStatus() == EntityStatus.DELETED) {

            byCreatedByName.setName(regionEntity.getName());

            if (regionEntity.getParentId() != null) {
                repository.findByRegionId(byCreatedByName.getParentId()).orElseThrow(() -> {
                    throw new RegionNotFoundException(regionEntity.getParentId() + " parent id not found!");
                });
                byCreatedByName.setParentId(regionEntity.getParentId());
            }

            byCreatedByName.setStatus(EntityStatus.CREATED);
            byCreatedByName.forCreate(userId);
            repository.save(byCreatedByName);
        } else {
            throw new RegionNotFoundException(regionEntity.getName() + " such a region exists!");
        }
    }

    private RegionEntity childIdAndParentIdVerify(RegionEntity region, Integer regionId) {

        RegionEntity entity = null;
        if (region.getParentId() != null) {

            entity = parentIdVerify(region,regionId);

        } else {
            entity = repository.findByRegionId(regionId).orElseThrow(
                    () -> new RegionNotFoundException(regionId + " id not found!!!"));
        }
        return entity;
    }

    private RegionEntity parentIdVerify(RegionEntity region, Integer regionId) {

        RegionEntity entity = null;
        List<RegionEntity> parentAndChild = repository.getRegionIdParentAndChild(regionId, region.getParentId());

        if (parentAndChild.size() == 2) {
            for (RegionEntity regionDB : parentAndChild) {

                if (Objects.equals(regionDB.getId(), regionId)) {
                    entity = regionDB;

                }
            }

        } else {
            throw new RegionNotFoundException("id not found!!!");
        }
        return entity;
    }

    private void deletedObjectOriginDataBase(String updateObjectName) {

        RegionEntity originDBObject = repository.findByRegionName(updateObjectName);
        if(originDBObject.getStatus()==EntityStatus.DELETED){
            repository.delete(originDBObject);
        }else {
            throw new CategoryNotFoundException(updateObjectName + " the region with this name already exists");
        }
    }
}
