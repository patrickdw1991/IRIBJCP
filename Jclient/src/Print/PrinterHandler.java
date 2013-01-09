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

    public static void printList(SensorList list) {
        makePDF(list);
        startPrint();
        
    }

    public static void startPrint() {
        InputStream is = null;
        try {
            File file = new File("tmp.pdf");
            //FileWriter fstream = new FileWriter("printtest.txt");

            is = new BufferedInputStream(new FileInputStream(file));
            System.out.println("test");
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            System.out.println("Number of print services: " + printServices.length);
            for (PrintService printer : printServices) {
                System.out.println("Printer: " + printer.getName());
            }
            PrintService service = PrintServiceLookup.lookupDefaultPrintService();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(is, flavor, null);
            DocPrintJob job = service.createPrintJob();
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            aset.add(new Copies(1));
            aset.add(Sides.ONE_SIDED);
            try {
                job.print(doc, aset);
            } catch (PrintException e) {
                e.printStackTrace();
            }
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(PrinterHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(PrinterHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void makePDF(SensorList list){
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream;
            contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            PDFont font = PDType1Font.TIMES_ROMAN;
            contentStream.setFont(font, 12);
            contentStream.moveTextPositionByAmount(100, 700);
            String[] sensorNames = list.getSensorNames(3);
            for(int x= 0; x<sensorNames.length;x++){
                contentStream.drawString(sensorNames[x]+"\n");
            }
            contentStream.endText();
            contentStream.close();
            document.save("tmp.pdf");
            document.close();
        } catch (IOException ex) {
            
        } catch (COSVisitorException ex2) {
            
        }

    }

}
