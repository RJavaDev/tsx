package uz.tsx.controller;

import org.springframework.web.bind.annotation.*;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.service.AnnouncementService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping("/all")
    public List<AnnouncementDto> all() {
        return announcementService.findAllAnnouncements();
    }

    @PostMapping("/create")
    public HttpResponse<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementDto dto) {
        return HttpResponse.build(true, "OK", announcementService.createNewAnnouncement(dto));
    }

}