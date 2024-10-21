package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.controller.convert.AnnouncementConvert;
import uz.tsx.dto.announcement.AnnouncementDto;
import uz.tsx.dto.announcement.AnnouncementMiniInformation;
import uz.tsx.dto.announcement.announcementCreated.AnnouncementCreatedDto;
import uz.tsx.dto.dtoUtil.*;
import uz.tsx.entity.announcement.AnnouncementEntity;
import uz.tsx.interfaces.AnnouncementInterface;
import uz.tsx.service.AnnouncementService;

import java.util.*;

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
//        service.iSaw(id,httpServletRequest);
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
    public ApiResponse<Object> getHomePageAnnouncement(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                                                       @RequestParam(value = "size", defaultValue = "6", required = false) Integer size) {

        BigDataTable<AnnouncementInterface> pageHomeData = service.getPageHomeData(page, size);
        BigDataTable<AnnouncementMiniInformation> dtoList = AnnouncementConvert.convertInterfaceToMiniDto(pageHomeData);

        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(dtoList)
                .code(ResponseCode.OK);
    }
    @GetMapping("/get/list/{categoryId}")
    public ApiResponse<Object>getAnnouncementList(@PathVariable Long categoryId,@RequestBody(required = false) PageParam pageParam){
        if (pageParam == null) {
            pageParam = new PageParam();
        }
        BigDataTable<AnnouncementInterface> pageAnnouncementData = service.getAnnouncementListByCategory(categoryId,pageParam);
        BigDataTable<AnnouncementMiniInformation> dtoList = AnnouncementConvert.convertInterfaceToMiniDto(pageAnnouncementData);

        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(dtoList)
                .code(ResponseCode.OK);
    }

    @GetMapping("/get/by-category/{categoryId}")
    public ApiResponse<Object>getAnnouncementListByCategoryId(@PathVariable Long categoryId){

        PageParam pageParam = new PageParam();
        pageParam.setSize(12);

        BigDataTable<AnnouncementInterface> pageAnnouncementData = service.getAnnouncementListByCategory(categoryId,pageParam);
        List<AnnouncementInterface> rows = new ArrayList<>(pageAnnouncementData.getRows());
        Collections.shuffle(rows);
        pageAnnouncementData.setRows(rows);
        BigDataTable<AnnouncementMiniInformation> dtoList = AnnouncementConvert.convertInterfaceToMiniDto(pageAnnouncementData);

        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(dtoList)
                .code(ResponseCode.OK);
    }

    @PostMapping("/search")
    public ApiResponse<Object> searchAnnouncementAndFilter(@RequestBody FilterForm filter){
        BigDataTable<AnnouncementInterface> announcementInterfaceBigDataTable = service.searchAnnouncementAndFilter(filter);
        BigDataTable<AnnouncementMiniInformation> dtoList = AnnouncementConvert.convertInterfaceToMiniDto(announcementInterfaceBigDataTable);
        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(dtoList)
                .code(ResponseCode.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-me")
    @Operation(summary = "Get me announcement", description = "List for users to see their announcement")
    public ApiResponse<Object>getMeAnnouncementList(){

        List<AnnouncementInterface> pageAnnouncementData = service.getMeAnnouncementList();
        List<AnnouncementMiniInformation> dtoList = AnnouncementConvert.convertToDto(pageAnnouncementData);

        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body(dtoList)
                .code(ResponseCode.OK);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/reverse-active/{id}")
    @Operation(summary = "Update announcement is Active Reverse", description = "Deactivates ad if active API to activate if de active")
    public ApiResponse<Object>getIsActive(@PathVariable Long id){

        service.activeReverseUpdate(id);
        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body("Successfully")
                .code(ResponseCode.OK);
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleted announcement by id", description = "Serves to delete announcement by id")
    public ApiResponse<Object> delete(@PathVariable Long id){

        service.deleteAnnouncement(id);
        return ApiResponse.build()
                .message(ResponseMessage.OK)
                .body("Successfully")
                .code(ResponseCode.OK);
    }

}