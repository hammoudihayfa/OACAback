package tn.esprit.ocr_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.ocr_service.services.OcrService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/ocr")
@CrossOrigin(origins = "*")

public class OcrController {

    @Autowired
    private OcrService ocrService;

    @PostMapping("/extract-text")
    public String extractTextFromImage(@RequestParam("file") MultipartFile file) {
        try {
            File convertedFile = convertMultipartFileToFile(file);
            return ocrService.extractTextFromImage(convertedFile);
        } catch (Exception e) {
            return "Erreur lors de l'extraction du texte: " + e.getMessage();
        }
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("ocr_", ".tmp");
        multipartFile.transferTo(tempFile);
        return tempFile;
    }
}

