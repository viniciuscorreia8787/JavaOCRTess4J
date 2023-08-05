package com.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import net.sourceforge.tess4j.*;


public class TestOCR {
	
	
    public static void main(String[] args) throws InvalidPasswordException, IOException {
    	
    	System.out.println("Process start...");
    	
        ITesseract instance = new Tesseract(); // JNA Interface Mapping
        instance.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata/"); // replace <parentPath> with path to parent directory of tessdata
        instance.setLanguage("por");
    	
    	//TIFF
        //File imageFile = new File("/home/vinic/Documents/development/java/ocr/tessdata/eurotext .tif");
    	
    	//Arquivos Exemplo
    	String filePath = "/home/vinic/Documents/development/java/ocr/images/4- Contrato Social/myfile.pdf";
    	
    	PDDocument document = PDDocument.load(new File(filePath));
		PDFRenderer pdfRenderer = new PDFRenderer(document);    	
    	
		for (int page = 0; page < document.getNumberOfPages(); page++) {
		    BufferedImage bufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

		    try {
		        String result = instance.doOCR(bufferedImage);
	            System.out.println(result);
		    } catch (TesseractException e) {
	            System.err.println(e.getMessage());
		    }
		}
		document.close();
    }

}
