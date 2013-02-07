/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Print;


import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Collections;
import java.util.List;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.Sides;
import sensorData.Sensor;
import sensorData.SensorList;


/**
 *
 * @author Patrick
 * @author Darth Vader
 * @author Han Solo
 */
public class PrinterHandler {

    public PrinterHandler(SensorList sensorList) {
        FileInputStream textStream;
        createPrinttxt(sensorList);
        try {
            textStream = new FileInputStream("print.txt");

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

    private void createPrinttxt(SensorList list) {
        String[] analogNames = list.getSensorNames(0);  //analog
        String[] binaryNames = list.getSensorNames(1);  //binary



        try {
            // Create file 
            FileWriter fstream = new FileWriter("print.txt");
            BufferedWriter out = new BufferedWriter(fstream);

            out.write("Sensor Data \n");

            for (String i : analogNames) {
                int min, max, average = 0;
                Sensor sensor = list.getSensor(i, 0);
                List<Integer> values = sensor.getValues();
                min = Collections.min(values);
                max = Collections.max(values);
                for (int j = 0; j < values.size(); j++) {
                    average += values.get(j);
                }
                average /= values.size();

                out.write(i + "\tmin: " + min + "\tmax: " + max + "\taverage: " + average + "\n");
            }


            for (String i : binaryNames) {
                int min, max, average = 0;
                Sensor sensor = list.getSensor(i, 1);
                List<Integer> values = sensor.getValues();
                min = Collections.min(values);
                max = Collections.max(values);
                for (int j = 0; j < values.size(); j++) {
                    average += values.get(j);
                }
                average /= values.size();

                out.write(i + "\tmin: " + min + "\tmax: " + max + "\taverage: " + average + "\n");
            }
            //Close the output stream
            out.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }



    }
}
