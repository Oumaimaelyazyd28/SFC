package tools;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class LineChart_AWT extends ApplicationFrame {

    public LineChart_AWT( String applicationTitle , String chartTitle ) throws FileNotFoundException {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Temps en Seconde","Temperature en Degre",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.pack( );
        RefineryUtilities.centerFrameOnScreen( this );
        this.setVisible( true );
    }

    private DefaultCategoryDataset createDataset( ) throws FileNotFoundException {
        String fileName = "src/view/files/test.txt";
        String fileName1 = "src/view/files/test2.txt";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        Scanner scan = new Scanner(new File(fileName));
        Scanner scan1 = new Scanner(new File(fileName1));
       while (scan.hasNextLine() & scan1.hasNextLine()){
           float data = Float.parseFloat(scan.nextLine());
           float data1 = Float.parseFloat(scan1.nextLine());
           dataset.addValue(data, "Temperature", ""+data1);

       }
        return dataset;
    }

    public static void main( String[ ] args ) throws FileNotFoundException {
        LineChart_AWT chart = new LineChart_AWT(
                "Graph" ,
                "La temperature pendant chaque 5s");
    }
}