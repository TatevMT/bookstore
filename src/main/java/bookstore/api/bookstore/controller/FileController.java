package bookstore.api.bookstore.controller;

import bookstore.api.bookstore.exceptions.FileStorageException;
import bookstore.api.bookstore.persistence.entity.FileEntity;
import bookstore.api.bookstore.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Tatevik Mirzoyan
 * Created on 04-Apr-21
 */

@RestController
@RequestMapping("files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id,
                                                 HttpServletRequest request) throws FileNotFoundException {
        FileEntity file = fileService.getById(id);
        Resource resource = fileService.loadFileAsResource(file.getName());
        try {
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException ex) {
            throw new FileStorageException("Could not determine file type of " + file.getName()
                    + " file, or something else went wrong. ", ex);
        }
    }

}
