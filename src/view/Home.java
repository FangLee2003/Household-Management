package view;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private People pnPeople = new People();
    private Household pnHousehold = new Household();
    private TempResidence_Absence pnTRA = new TempResidence_Absence();
    private Chart pnAnalysis = new Chart();

    private JTabbedPane tp = new JTabbedPane(JTabbedPane.LEFT);

    public Home() {
        try {
            tp.addTab("People", null, pnPeople.getPeoplePanel(), "Click to change tab");
            tp.addTab("Household", null, pnHousehold.getHouseholdPanel(), "Click to change tab");
            tp.addTab("TempResidence_Absence", null, pnTRA.getTempResidence_AbsencePanel(), "Click to change tab");
            tp.addTab("Data Analysis", null, pnAnalysis.getChart(), "Click to see analysis");
            add(tp);

            setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("./assets/logo.png")));

            setTitle("People Management - Fang Lee");
            setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
            setResizable(false);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
