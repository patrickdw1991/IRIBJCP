/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Print;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.print.*;

/**
 *
 * @author patrick
 */
public class Printerhandler {

    public void Printerhandler() throws FileNotFoundException, IOException {
        File file = new File("printtest.txt");
        InputStream is = new BufferedInputStream(new FileInputStream(file));
  
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
    }
}
