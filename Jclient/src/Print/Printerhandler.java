/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Print;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.*;
import sensorData.SensorList;

/**
 *
 * @author patrick
 */
public class Printerhandler {

    public static void printList(SensorList list) {
        InputStream is = null;
        try {
            File file = new File("printtest.txt");
            FileWriter fstream = new FileWriter("printtest.txt");
            
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
            try {
                job.print(doc, null);
            } catch (PrintException e) {
                e.printStackTrace();
            }
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(Printerhandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(Printerhandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
