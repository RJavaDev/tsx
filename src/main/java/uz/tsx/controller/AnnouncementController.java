package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.controller.convert.AnnouncementConvert;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.announcement.announcementCreated.AnnouncementCreatedDto;
import uz.tsx.dto.dtoUtil.*;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.AnnouncementInterface;
import uz.tsx.service.AnnouncementService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/announcement")
@RequiredArgsConstructor
@Tag(name = "Announcement Controller", description = "Operations related to announcements")
public class AnnouncementController {

    private final AnnouncementService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @PostMapping("/add")
    @Operation(summary = "Create new announcement", description = "Create a new announcement.")
    public ApiResponse<Object> addNewAnnouncement(@RequestBody @Valid AnnouncementCreatedDto dto) {

        AnnouncementEntity entity = AnnouncementConvert.convertToEntity(dto);

        AnnouncementEntity newAnnouncementEntity = service.createNewAnnouncement(entity);

        AnnouncementDto announcementDto = AnnouncementConvert.convertToDto(newAnnouncementEntity);

        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(announcementDto)
                .code(ResponseCode.OK);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("permitAll()")
    @PostMapping("/save/images")
    @Operation(summary = "Save announcement images", description = "Save images for a specific announcement.")
    public ApiResponse<Object> saveAnnouncementImages(@RequestParam("announceId") Long announceId, @RequestParam("images") MultipartFile[] images) {

        AnnouncementEntity entity = service.saveAnnounceImages(announceId, images);
        AnnouncementDto announcementDto = AnnouncementConvert.convertToDto(entity);

        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(announcementDto)
                .code(ResponseCode.OK);
    }

//    @PostMapping("/save/images")
//    @Operation(summary = "Save announcement images", description = "Save images for a specific announcement.")
//    public HttpResponse<Object> saveImages(@RequestParam("announceId") Long announceId,
//                                           @RequestParam("images") MultipartFile[] images) {
//        return HttpResponse.build(true, "OK", announcementService.saveAnnounceImages(announceId, images));
//    }

    @GetMapping("/get/{id}")
    public ApiResponse<Object> getById(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        service.iSaw(id,httpServletRequest);
        AnnouncementEntity entity = service.getById(id);
        AnnouncementDto announcementDto = AnnouncementConvert.convertToDto(entity);

        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(announcementDto)
                .code(ResponseCode.OK);
    }

    @GetMapping("/all/about")
    @Operation(summary = "Get all announcements", description = "Retrieve all announcements.")
    public List<AnnouncementDto> all() {
        return service.findAllAnnouncements();
    }

    @GetMapping("/find/{id}/about")
    @Operation(summary = "Find announcement by ID", description = "Retrieve an announcement by its ID.")
    public AnnouncementDto findByIdAbout(@PathVariable("id") Long announcementId) {
        return service.findAnnouncementByIdAbout(announcementId);
    }

    @GetMapping("/datatable")
    @Operation(summary = "Get announcements as datatable", description = "Retrieve announcements in a datatable format.")
    public HttpResponse<DataTable<AnnouncementDto>> stable(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("page", page);
        filterMap.put("size", size);
        return HttpResponse.build(true, "OK", service.table2(filterMap), HttpResponse.Status.OK.getCode());
    }

    @GetMapping("/home")
    public ApiResponse<Object> getHomePageAnnouncement(@RequestBody(required = false) PageParam pageParam) {
        if (pageParam == null) {
            pageParam = new PageParam();
        }
        Page<AnnouncementEntity> pageHomeData = service.getPageHomeData(pageParam);
        List<AnnouncementDto> dtoList = AnnouncementConvert.convertToDto(pageHomeData);

        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(dtoList)
                .code(ResponseCode.OK);
    }
    @PostMapping("/getAnnouncementList/{categoryId}")
    public HttpResponse<DataTable<AnnouncementDto>>getAnnouncementList(@PathVariable Long categoryId,@RequestBody(required = false) PageParam pageParam){
        if (pageParam == null) {
            pageParam = new PageParam();
        }
        DataTable<AnnouncementEntity> pageAnnouncementData = service.getAnnouncementListByCategory(categoryId,pageParam);
        DataTable<AnnouncementDto> dtoList = AnnouncementConvert.convertToDto(pageAnnouncementData);

        return HttpResponse.build(true, "OK",dtoList,HttpResponse.Status.OK.getCode());
    }

    @PostMapping("/search")
    public ApiResponse<Object> searchAnnouncementAndFilter(@RequestBody FilterForm filter){
        BigDataTable<AnnouncementInterface> announcementInterfaceBigDataTable = service.searchAnnouncementAndFilter(filter);
        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(announcementInterfaceBigDataTable)
                .code(ResponseCode.OK);
    }


}