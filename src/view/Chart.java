package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.jfree.chart.*;
import org.jfree.data.general.*;
import org.jfree.chart.plot.*;

import controller.ChartController;

import static view.Graphic.createImageIcon;

public class Chart implements ActionListener {
    private DefaultPieDataset dataset = new DefaultPieDataset();
    private JFreeChart pieChart;
    private ChartPanel pnChart;
    private PiePlot3D plot;

    private ChartController cc = new ChartController();

    private JButton btReload = new JButton("Reload", createImageIcon("../assets/reload.png"));

    private JPanel pnControl = new JPanel();
    private JPanel pnMain = new JPanel();

    public Chart() {
        try {
            btReload.addActionListener(this);

            loadDataset();

            pieChart = ChartFactory.createPieChart3D("Household structure", dataset, true, true, true);

            plot = (PiePlot3D) pieChart.getPlot();
            plot.setForegroundAlpha(0.60f);
            plot.setInteriorGap(0.02);
            plot.setBackgroundPaint(null);
            plot.setOutlinePaint(null);
            plot.setLabelGenerator(null);

            pnChart = new ChartPanel(pieChart);

            pnControl.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            pnControl.add(btReload);

            pnMain.setLayout(new BorderLayout());
            pnMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

            pnMain.add(pnControl, BorderLayout.NORTH);
            pnMain.add(pnChart, BorderLayout.CENTER);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadDataset() {
        try {
            dataset.setValue("Householders", cc.getHouseholdPercent());
            dataset.setValue("Dependents", cc.getDependentPercent());

            System.out.println("Householders Percent: " + cc.getHouseholdPercent() + "%");
            System.out.println("Dependents Percent: " + cc.getDependentPercent() + "%");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Reload")) {
            loadDataset();
        }
    }

    public JPanel getChart() {
        return pnMain;
    }
}