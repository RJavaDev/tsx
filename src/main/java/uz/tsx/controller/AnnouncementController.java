package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.dtoUtil.DataTable;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.service.AnnouncementService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/announcement")
@Tag(name = "Announcement Controller", description = "Operations related to announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping("/save/images")
    @Operation(summary = "Save announcement images", description = "Save images for a specific announcement.")
    public HttpResponse<AnnouncementDto> saveImages(@RequestParam("announceId") Long announceId,
                                           @RequestParam("images") MultipartFile[] images) {
        return HttpResponse.build(true, "OK", announcementService.saveAnnounceImages(announceId, images));
    }

    @GetMapping("/all/about")
    @Operation(summary = "Get all announcements", description = "Retrieve all announcements.")
    public List<AnnouncementDto> all() {
        return announcementService.findAllAnnouncements();
    }

    @GetMapping("/find/{id}/about")
    @Operation(summary = "Find announcement by ID", description = "Retrieve an announcement by its ID.")
    public AnnouncementDto findByIdAbout(@PathVariable("id") Long announcementId) {
        return announcementService.findAnnouncementByIdAbout(announcementId);
    }

    @GetMapping("/datatable")
    @Operation(summary = "Get announcements as datatable", description = "Retrieve announcements in a datatable format.")
    public HttpResponse<DataTable<AnnouncementDto>> table(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                                          @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("page", page);
        filterMap.put("size", size);
        return HttpResponse.build(true, "OK", announcementService.table2(filterMap), HttpResponse.Status.OK.getCode());
    }

    @PostMapping("/create")
    @Operation(summary = "Create new announcement", description = "Create a new announcement.")
    public HttpResponse<AnnouncementDto> createAnnouncement(@RequestBody AnnouncementDto dto) {
        return HttpResponse.build(true, "OK", announcementService.createNewAnnouncement(dto), HttpResponse.Status.OK.getCode());
    }

}