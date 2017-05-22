
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;

import java.awt.print.PrinterAbortException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Szymon on 2017-05-19.
 */
public class GenerateBarcode {


    public void createPDF(String code, int type) {

        Document document = new Document();

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream("code.pdf"));

            document.addAuthor("author");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("sampleCreator");
            document.addTitle("EAN barcode");
            document.setPageSize(PageSize.LETTER);

            document.open();
            PdfContentByte pdfContentByte = pdfWriter.getDirectContent();

            if (type == 0 || code == null) {
                throw new PrinterAbortException("Type or code is empty. Write file is fail.");
            } else if (type == 1) {
                BarcodeEAN codeEAN8 = new BarcodeEAN();
                codeEAN8.setCodeType(BarcodeEAN.EAN8);
                codeEAN8.setCode("12345678");
                Image codeEAN8Image = codeEAN8.createImageWithBarcode(pdfContentByte, null, null);
                codeEAN8Image.setAbsolutePosition(20, 600);
                codeEAN8Image.scalePercent(125);
                document.add(codeEAN8Image);
            } else if (type == 2){
                BarcodeEAN codeEAN13 = new BarcodeEAN();
                codeEAN13.setCode(code.trim());
                codeEAN13.setCodeType(BarcodeEAN.EAN13);
                codeEAN13.setCode(code);
                Image codeEAN13Image = codeEAN13.createImageWithBarcode(pdfContentByte, null, null);
                codeEAN13Image.setAbsolutePosition(20, 600);
                codeEAN13Image.scalePercent(125);
                document.add(codeEAN13Image);
            }
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (PrinterAbortException e) {
            e.printStackTrace();
        }
    }
}
