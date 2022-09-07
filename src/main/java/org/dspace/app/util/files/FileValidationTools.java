/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.app.util.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.tika.exception.TikaException;

/**
 * @author Kim Shepherd
 */
public class FileValidationTools {

    public static void main(String[] args) {

        FileTypeDetector detector;
        PDFValidator validator;
        try {
            detector = new FileTypeDetector();
            validator = new PDFValidator();
        } catch (TikaException |IOException e) {
            throw new RuntimeException(e);
        }

        List<String> pdfTypes = List.of(new String[]{"application/pdf", "application/pdfa"});

        String filename = "test3.pdf";
        if (args.length > 0) {
            filename = args[0];
        }
        File file = new File(filename);
        try (InputStream inputStream = new FileInputStream(file)) {
            String mimeType = detector.detectMediaType(inputStream, filename).toString();
            System.out.println(filename + ": " + inputStream + ": " + mimeType);
            if (pdfTypes.contains(mimeType)) {
               ValidationResult result = validator.validatePdf(file);
                if (result.isValid()) {
                    System.out.println(filename + " is a valid PDF/A-1b file");
                }
                else {
                    System.out.println(filename + " is not valid, error(s) :");
                    for (ValidationResult.ValidationError error : result.getErrorsList()) {
                        System.out.println(error.getErrorCode() + " : " + error.getDetails());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
