package tn.esprit.ocr_service.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
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

    public String extractTextFromImage(File file) throws Exception {
        String fileName = file.getName().toLowerCase();

        if (fileName.endsWith(".pdf")) {
            return extractTextFromPdf(file);
        } else {
            try {
                BufferedImage image = ImageIO.read(file);
                return tesseract.doOCR(image);
            } catch (IOException e) {
                throw new Exception("Erreur lors du chargement de l'image", e);
            } catch (Exception e) {
                throw new Exception("Erreur lors de l'extraction du texte", e);
            }
        }
    }

    public String extractTextFromPdf(File pdfFile) throws Exception {
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } catch (IOException e) {
            throw new Exception("Erreur lors de la lecture du PDF", e);
        }
    }

    public String extractTextFromImagePath(String imagePath) throws Exception {
        File imageFile = new File(imagePath);
        return extractTextFromImage(imageFile);
    }
}
