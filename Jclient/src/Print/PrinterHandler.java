/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Print;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Sides;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import sensorData.SensorList;

/**
 *
 * @author Patrick
 * @author Darth Vader
 * @author Han Solo
 */
public class PrinterHandler {

    public PrinterHandler(String fileName) {
        FileInputStream textStream;
        try {
            textStream = new FileInputStream(fileName);

            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new Copies(1));
            aset.add(Sides.ONE_SIDED);

            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc mydoc = new SimpleDoc(textStream, flavor, null);

            PrintService[] services = PrintServiceLookup.lookupPrintServices(
                    flavor, aset);
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

            if (services.length == 0) {
                if (defaultService == null) {
                    System.out.println("No printer found");
                } else {
                    //print using default
                    DocPrintJob job = defaultService.createPrintJob();
                    job.print(mydoc, aset);

                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exist");
        } catch (PrintException ex) {
            System.out.println("Print exception");
        }
    }
}
