package uz.tsx.service;

import uz.tsx.entity.RegionEntity;

import java.util.List;

public interface RegionService extends BaseInterface<RegionEntity>{

    boolean add(RegionEntity regionEntity);

    RegionEntity getByIdTree(Long id);

    List<RegionEntity> getAllTree();

    boolean update(RegionEntity newUpdateObject);

}
