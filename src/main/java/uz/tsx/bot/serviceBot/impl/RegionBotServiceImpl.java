package uz.tsx.bot.serviceBot.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.tsx.bot.dto.RegionDto;
import uz.tsx.repository.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionBotServiceImpl {

    private final RegionRepository repository;

    public List<RegionDto>getRegionList(){
       return repository.getRegionAllTree().stream().map(region -> {
            RegionDto regionDto=new RegionDto();
            regionDto.setId(region.getId());
            regionDto.setName(region.getNameUz());
            return regionDto;
        }).toList();
    }
    public List<RegionDto> getRegionListById(Long regionId){
        return repository.getChildListById(regionId).stream().map(region -> {
            RegionDto regionDto=new RegionDto();
            regionDto.setId(region.getId());
            regionDto.setName(region.getNameUz());
            return regionDto;
        }).toList();
    }

}
