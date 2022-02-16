package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import model.hometown;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.*;
import org.jfree.data.general.*;
import org.jfree.chart.plot.*;

import controller.ChartController;

import static view.Graphic.createImageIcon;

public class Chart implements ActionListener {
    private JButton btReload = new JButton("Reload", createImageIcon("../assets/reload.png"));

    private DefaultPieDataset pieDataset = new DefaultPieDataset();
    private DefaultCategoryDataset barDataset = new DefaultCategoryDataset();

    private JFreeChart pieChart;
    private JFreeChart barChart;

    private PiePlot3D plotPie;
    private CategoryPlot plotBar;

    private ChartPanel pnPieChart;
    private ChartPanel pnBarChart;

    private ChartController cc = new ChartController();

    private JPanel pnControl = new JPanel();
    private JPanel pnChart = new JPanel();
    private JPanel pnMain = new JPanel();

    public Chart() {
        try {
            btReload.addActionListener(this);

            loadPieDataset();
            loadBarDataset();

            pieChart = ChartFactory.createPieChart3D("Proportion between householders and dependents", pieDataset, true, true, true);
            barChart = ChartFactory.createBarChart("Population distribution structure", "City", "Population", barDataset, PlotOrientation.VERTICAL, true, true, true);

            plotPie = (PiePlot3D) pieChart.getPlot();
            plotPie.setSectionPaint("Householders", new Color(9, 80, 205));
            plotPie.setSectionPaint("Dependents", new Color(23, 233, 223));
            plotPie.setForegroundAlpha(0.60f);
            plotPie.setInteriorGap(0.02);
            plotPie.setBackgroundPaint(null);
            plotPie.setOutlinePaint(null);
            plotPie.setLabelGenerator(null);

            CategoryPlot plotBar = barChart.getCategoryPlot();
            BarRenderer rendererBar = (BarRenderer) plotBar.getRenderer();
            rendererBar.setMaximumBarWidth(.01); // set maximum width to 1% of chart
            rendererBar.setSeriesPaint(0, new Color(52, 152, 219));
            plotBar.setForegroundAlpha(0.8f);
            plotBar.setBackgroundPaint(null);
            plotBar.setOutlinePaint(null);
            pnPieChart = new ChartPanel(pieChart);
            pnBarChart = new ChartPanel(barChart);

            pnControl.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            pnControl.add(btReload);

            pnChart.setLayout(new GridLayout(2, 1, 20, 20));
            pnChart.add(pnPieChart);
            pnChart.add(pnBarChart);

            pnMain.setLayout(new BorderLayout());
            pnMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
            pnMain.add(pnControl, BorderLayout.NORTH);
            pnMain.add(pnChart, BorderLayout.CENTER);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadPieDataset() {
        try {
            pieDataset.setValue("Householders", cc.getHouseholdPercent());
            pieDataset.setValue("Dependents", cc.getDependentPercent());

            System.out.println("Householders Percent: " + cc.getHouseholdPercent() + "%");
            System.out.println("Dependents Percent: " + cc.getDependentPercent() + "%");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadBarDataset() {
        for (hometown ht : cc.getHometown()) {
            barDataset.addValue(ht.getPopulation(), "Population", ht.getCity());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Reload")) {
            loadPieDataset();
            loadBarDataset();
        }
    }

    public JPanel getChart() {
        return pnMain;
    }
}