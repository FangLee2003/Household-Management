package view;

import java.awt.*;
import javax.swing.*;

public class Home extends JFrame {
    private People pnPeople = new People();
    private Household pnHousehold = new Household();
    private TempResidence_Absence pnTRA = new TempResidence_Absence();
    private Chart pnAnalysis = new Chart();
    private JTabbedPane tp = new JTabbedPane();

    public Home() {
        tp.addTab("People", null, pnPeople.getPeoplePanel(), "Click to change tab");
        tp.addTab("Household", null, pnHousehold.getHouseholdPanel(), "Click to change tab");
        tp.addTab("TempResidence_Absence", null, pnTRA.getTempResidence_AbsencePanel(), "Click to change tab");
        tp.addTab("Data Analysis", null, pnAnalysis.getChart(), "Click to see analysis");
        add(tp);

        setTitle("People Management - Fang Lee");
        setSize(1000, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
