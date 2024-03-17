package uz.tsx.controller;

import org.springframework.web.bind.annotation.*;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.DataTable;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.service.AnnouncementService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/all/about")
    public List<AnnouncementDto> all() {
        return announcementService.findAllAnnouncements();
    }

    @GetMapping("/find/{id}/about")
    public AnnouncementDto findByIdAbout(@PathVariable("id") Long announcementId) {
        return announcementService.findAnnouncementByIdAbout(announcementId);
    }

    @GetMapping("/datatable")
    public HttpResponse<DataTable<AnnouncementDto>> table(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("page", page);
        filterMap.put("size", size);
        return HttpResponse.build(true, "OK", announcementService.table2(filterMap), HttpResponse.Status.OK.getCode());
    }

    @PostMapping("/create")
    public HttpResponse<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementDto dto) {
        return HttpResponse.build(true, "OK", announcementService.createNewAnnouncement(dto), HttpResponse.Status.OK.getCode());
    }

}