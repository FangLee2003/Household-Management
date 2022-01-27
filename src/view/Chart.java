package view;

import org.jfree.chart.*;
import org.jfree.data.general.*;
import org.jfree.chart.plot.*;

import controller.*;


public class Chart {
    private DefaultPieDataset dataset = new DefaultPieDataset();
    private JFreeChart pieChart;
    private ChartPanel pnChart;
    private PiePlot3D plot;

    private ChartController cc = new ChartController();

    public Chart() {
        try{
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
            dataset.setValue("Householders", cc.getHouseholdPercent());
            dataset.setValue("Dependents", cc.getDependentPercent());

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ChartPanel getChart() {
        return pnChart;
    }
}