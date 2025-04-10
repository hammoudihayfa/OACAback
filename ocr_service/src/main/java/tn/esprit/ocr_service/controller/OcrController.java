package tn.esprit.ocr_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.ocr_service.services.OcrService;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    @Autowired
    private OcrService ocrService;

    @PostMapping("/extract-text")
    public String extractTextFromImage(@RequestParam("file") MultipartFile file) {
        try {
            // Convertir MultipartFile en File
            File convertedFile = convertMultipartFileToFile(file);
            // Appeler le service OCR avec le fichier converti
            return ocrService.extractTextFromImage(convertedFile);
        } catch (Exception e) {
            return "Erreur lors de l'extraction du texte: " + e.getMessage();
        }
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        // Créer un fichier temporaire à partir du MultipartFile
        File tempFile = File.createTempFile("ocr_", ".tmp");
        multipartFile.transferTo(tempFile);  // Copier le contenu du MultipartFile dans le fichier temporaire
        return tempFile;
    }
}

