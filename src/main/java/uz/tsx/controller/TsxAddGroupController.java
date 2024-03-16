package uz.tsx.controller;

import org.springframework.web.bind.annotation.*;
import uz.tsx.dto.announcement.additionInfo.AdditionComboValueDto;
import uz.tsx.dto.announcement.additionInfo.AnnounceAdditionGroupDto;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.service.AnnounceAdditionGroupService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tsx_add_group")
public class TsxAddGroupController {

    private final AnnounceAdditionGroupService announceAdditionGroupService;

    public TsxAddGroupController(AnnounceAdditionGroupService announceAdditionGroupService) {
        this.announceAdditionGroupService = announceAdditionGroupService;
    }

    @GetMapping("/find/{id}")
    public HttpResponse<AnnounceAdditionGroupDto> findById(@PathVariable("id") Long id) {
        return null;
    }

    @GetMapping("/list")
    public HttpResponse<List<AdditionComboValueDto>> listAnnounceGroupAdds(@RequestParam(value = "groupId") Long groupId) {
        return HttpResponse.build(true, "", announceAdditionGroupService.listAnnounceAdditionGroup(groupId), HttpResponse.Status.OK.ordinal());
    }

}
