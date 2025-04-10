package tn.esprit.ocr_service.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class OcrService {
    private final ITesseract tesseract;

    public OcrService() {
        tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
    }

    public String extractTextFromImage(File imageFile) throws Exception {
        try {
            // Utilisation de ImageIO pour charger l'image
            BufferedImage image = ImageIO.read(imageFile);
            return tesseract.doOCR(image);
        } catch (IOException e) {
            throw new Exception("Erreur lors du chargement de l'image", e);
        } catch (Exception e) {
            throw new Exception("Erreur lors de l'extraction du texte", e);
        }
    }

    public String extractTextFromImagePath(String imagePath) throws Exception {
        File imageFile = new File(imagePath);
        return extractTextFromImage(imageFile);
    }
}