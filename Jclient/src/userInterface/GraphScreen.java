/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import StringInterpreter.StringParser;
import chart.LineChart;
import java.awt.BorderLayout;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import sensorData.Sensor;
import sensorData.SensorList;

/**
 *
 * @author Bart
 */
public class GraphScreen extends javax.swing.JFrame {

    private LineChart chart;
    private SensorList sensorList;

    /**
     * Creates new form graphScreen
     */
    public GraphScreen(SensorList sensorList) {
        this.sensorList = sensorList;
        initComponents();
        jList1.setListData(sensorList.getSensorList(1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        graphPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String dbString = "S,sensor1,12342,sensor2,123123,E";
            StringParser parse = new StringParser();
            String[] strings = parse.getSensorNames(dbString);
            //String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jList1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Binary", "Digital 10", "Digital 100" }));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Binary", "Digital 10", "Digital 100" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBox1MouseReleased(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jLabel1.setText("Current Alarms");

        graphPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout graphPanelLayout = new javax.swing.GroupLayout(graphPanel);
        graphPanel.setLayout(graphPanelLayout);
        graphPanelLayout.setHorizontalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );
        graphPanelLayout.setVerticalGroup(
            graphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                .addGap(37, 37, 37)
                .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(graphPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseReleased


        //GetData, init graph

        test(jList1.getSelectedValue().toString());

    }//GEN-LAST:event_jList1MouseReleased

    private void jComboBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseReleased
        


    }//GEN-LAST:event_jComboBox1MouseReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String temp = jComboBox1.getSelectedItem().toString();
        if (temp.equals("Binary")) {
            jList1.setListData(sensorList.getSensorList(1));
        } else if (temp.equals("Digital 10")) {
            jList1.setListData(sensorList.getSensorList(2));
        } else if (temp.equals("Digital 100")) {
            jList1.setListData(sensorList.getSensorList(3));
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void test(String sensorName) {
        Sensor sensor = sensorList.getSensor(sensorName);

        final XYDataset dataset = createDataset(sensor);
        final JFreeChart chart = createChart(dataset, sensorName, "", "");
        final ChartPanel chartPanel = new ChartPanel(chart);
        graphPanel.setLayout(new java.awt.BorderLayout());
        graphPanel.add(chartPanel, BorderLayout.CENTER);
        graphPanel.validate();
    }

    private JFreeChart createChart(XYDataset dataset, String sensorName, String timeUnit, String sensorUnit) {


        final JFreeChart chart = ChartFactory.createXYLineChart(
                sensorName, // chart title
                timeUnit, // x axis label
                sensorUnit, // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                false, // include legend
                false, // tooltips
                false // urls
                );

        return chart;

    }

    private XYDataset createDataset(Sensor sensor) {
        List<Integer> values = sensor.getValues();

        final XYSeries series1 = new XYSeries("First");
        for (int i = 0; i < values.size(); i++) {
            series1.add(i, values.get(i));
        }


//        final XYSeries series2 = new XYSeries("Second");
//        series2.add(1.0, 5.0);
//        series2.add(2.0, 7.0);
//        series2.add(3.0, 6.0);
//        series2.add(4.0, 8.0);
//        series2.add(5.0, 4.0);
//        series2.add(6.0, 4.0);
//        series2.add(7.0, 2.0);
//        series2.add(8.0, 1.0);
//
//        final XYSeries series3 = new XYSeries("Third");
//        series3.add(3.0, 4.0);
//        series3.add(4.0, 3.0);
//        series3.add(5.0, 2.0);
//        series3.add(6.0, 3.0);
//        series3.add(7.0, 6.0);
//        series3.add(8.0, 3.0);
//        series3.add(9.0, 4.0);
//        series3.add(10.0, 3.0);

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
//        dataset.addSeries(series2);
//        dataset.addSeries(series3);

        return dataset;

    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /*
//         * Set the Nimbus look and feel
//         */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /*
//         * If Nimbus (introduced in Java SE 6) is not available, stay with the
//         * default look and feel. For details see
//         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GraphScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GraphScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GraphScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GraphScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /*
//         * Create and display the form
//         */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                new GraphScreen(null).setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel graphPanel;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
