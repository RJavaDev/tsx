package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.tsx.controller.convert.AttachConvert;
import uz.tsx.dto.dtoUtil.HttpResponse;
import uz.tsx.dto.response.AttachDownloadDTO;
import uz.tsx.dto.response.AttachResponseDto;
import uz.tsx.service.AttachService;

import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/attach")
@RequiredArgsConstructor
public class AttachController {

    private final AttachService service;

    @Operation(summary = "Upload Image", description = "This method is used to upload an image")
    @PostMapping("/upload")
    public HttpResponse<Object> upload(@RequestParam MultipartFile file){

         AttachResponseDto attach = AttachConvert.from(service.saveAttach(file));

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(attach)
                .message(HttpStatus.OK.name());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','CONTEND_MANAGER')")
    @Operation(summary = "Upload Images List", description = "This method is used to upload an images List")
    @PostMapping("/uploads")
    public HttpResponse<Object> uploadAttachList(@RequestParam List<MultipartFile> files){

        List<AttachResponseDto> attachDtoList = AttachConvert.from(service.saveAttach(files));

        return HttpResponse.build()
                .code(HttpResponse.Status.OK)
                .success(true)
                .body(attachDtoList)
                .message(HttpStatus.OK.name());
    }



    @Operation(summary = "Download Image", description = "This method is used to download an image")
    @GetMapping("/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {

        AttachDownloadDTO result = service.download(fileName);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(result.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + result.getResource().getFilename() + "\"").body(result.getResource());
    }


    @Operation(summary = "Get Attachments with Pagination", description = "This method retrieves attachments with pagination")
    @GetMapping("/get")
    public ResponseEntity<?> getWithPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<AttachResponseDto> result = service.getWithPage(page, size);
        return ResponseEntity.ok(result);
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','CONTEND_MANAGER')")
    @Operation(summary = "Delete Attachment by ID", description = "This method is used to delete an attachment by its fileName")
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<?> deleteById(@PathVariable("fileName") String fileName) {
        String result = service.deleteById(fileName);
        return ResponseEntity.ok(result);
    }

}
