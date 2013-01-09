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
import sensorData.SensorList;

/**
 *
 * @author patrick
 */
public class PrinterHandler {

    public static void printList(SensorList list) {
        InputStream is = null;
        try {
            File file = new File("testpage.pdf");
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
}
