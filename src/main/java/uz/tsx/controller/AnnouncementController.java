package uz.tsx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public HttpResponse<AnnouncementDto> createAnnouncement(AnnouncementDto dto) {
        return HttpResponse.build(true, "OK", announcementService.createNewAnnouncement(dto));
    }

}