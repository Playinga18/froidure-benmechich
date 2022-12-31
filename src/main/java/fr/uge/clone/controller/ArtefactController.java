package fr.uge.clone.controller;

import fr.uge.clone.service.BlockService;
import fr.uge.clone.message.ResponseFile;
import fr.uge.clone.message.ResponseMessage;
import fr.uge.clone.model.Artefact;
import fr.uge.clone.repository.ArtefactRepository;
import fr.uge.clone.service.ArtefactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ArtefactController {
    private final Object lock = new Object();
    @Autowired
    private ArtefactService storageService;

    @Autowired
    private ArtefactRepository artefactRepository;

    @Autowired
    private BlockService stock;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            synchronized (lock){
                //var artefact = storageService.store(file);

                stock.storeBlock(storageService.store(file));

            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get("src/main/uploads/" + file.getOriginalFilename());
            Files.write(path, bytes);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getNumVersion())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getNumVersion(),
                    fileDownloadUri,
                    dbFile.getDescription(),
                    dbFile.getMetaData().length());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<String> getFile(@PathVariable String id) {
        var artefactDB = storageService.getFile(Long.valueOf(id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + artefactDB.getNumVersion() + "\"")
                .body(artefactDB.toString());
    }

    @GetMapping("/artefact/{id}")
    public Artefact getArtefact(@PathVariable Long id) {
        return artefactRepository.findById(id).orElse(null);
    }
    public void Test(){
        System.out.println(storageService.mapListOfHashByIdArtefact());
    }
}
