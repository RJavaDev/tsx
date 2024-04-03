package uz.tsx.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.activation.MimetypesFileTypeMap;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
import uz.tsx.dto.dtoUtil.ApiResponse;
import uz.tsx.dto.dtoUtil.ResponseCode;
import uz.tsx.dto.response.AttachDownloadDTO;
import uz.tsx.dto.response.AttachResponseDto;
import uz.tsx.dto.response.AttachUrlResponse;
import uz.tsx.entity.AttachEntity;
import uz.tsx.service.AttachService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/attach")
@RequiredArgsConstructor
public class AttachController {

    private final AttachService service;


    @Value("${attach.upload.folder}")
    private String ATTACH_UPLOAD_FOLDER;

    @PostMapping("/img-upl")
    public List<AttachUrlResponse> upload(@RequestParam MultipartFile[] files){
        return AttachConvert.convertToAttachUrlDto(service.saveFile(files));
    }


    @Operation(summary = "Upload Image", description = "This method is used to upload an image")
    @PostMapping("/upload")
    public ApiResponse<Object> upload(@RequestParam MultipartFile file){

         AttachResponseDto attach = AttachConvert.from(service.saveAttach(file));

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(attach)
                .message(HttpStatus.OK.name());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasAnyRole('ADMIN','CONTEND_MANAGER')")
    @Operation(summary = "Upload Images List", description = "This method is used to upload an images List")
    @PostMapping("/uploads")
    public ApiResponse<Object> uploadAttachList(@RequestParam List<MultipartFile> files){

        List<AttachResponseDto> attachDtoList = AttachConvert.from(service.saveAttach(files));

        return ApiResponse.build()
                .code(ResponseCode.OK)
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

    @GetMapping("/file/{filename}")
    public void file(HttpServletResponse response,
                     @PathVariable("filename") String fileName) throws FileNotFoundException {

        File file = new File(ATTACH_UPLOAD_FOLDER, fileName);
        if(!file.exists()) throw new FileNotFoundException();

        /*Finding MIME type for explicitly setting MIME */
        String mimeType = new MimetypesFileTypeMap().getContentType(file);
        response.setContentType(mimeType);

        //Browser tries to open it
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        try{
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(Files.readAllBytes(file.toPath()));
        } catch(IOException ignore){}
    }

}
