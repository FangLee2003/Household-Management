package view;

import java.util.*;
import javax.swing.*;

import controller.ConnectionSQL;
import org.jfree.chart.*;
import org.jfree.data.general.*;
import org.jfree.chart.plot.*;
import controller.*;

import java.sql.*;

public class Chart {
    private DefaultPieDataset dataset = new DefaultPieDataset();
    private JFreeChart pieChart;
    private ChartPanel pnChart;
    private PiePlot3D plot;

    Connection con;
    Statement smP;
    Statement smH;

    public Chart() {
        try {
            con = ConnectionSQL.getConnection();
            smP = con.createStatement();
            smH = con.createStatement();

            loadDataset();

            pieChart = ChartFactory.createPieChart3D("Household structure", dataset, true, true, true);

            plot = (PiePlot3D) pieChart.getPlot();
            plot.setForegroundAlpha(0.60f);
            plot.setInteriorGap(0.02);

            pnChart = new ChartPanel(pieChart);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadDataset() {
        try {
            ResultSet rsP = smP.executeQuery("SELECT COUNT(*) FROM People");
            int people_num = 1;
            if (rsP.next()) {
                people_num = rsP.getInt(1);
            }
            int household_num = 1;
            ResultSet rsH = smH.executeQuery("SELECT COUNT(*) FROM Household");
            if (rsP.next()) {
                household_num = rsP.getInt(1);
            }

            double household_percent = household_num * 100 / people_num;
            double dependent_percent = 100 - household_percent;

            System.out.println(people_num + "\n" + household_num + "\n" + household_percent + "\n" + dependent_percent);

            dataset.setValue("Householders", household_percent);
            dataset.setValue("Dependents", dependent_percent);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ChartPanel getChart() {
        return pnChart;
    }
}