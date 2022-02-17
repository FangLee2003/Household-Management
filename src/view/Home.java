package view;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    private People pnPeople = new People();
    private Household pnHousehold = new Household();
    private Absence_TempResidence pnTRA = new Absence_TempResidence();
    private Chart pnAnalysis = new Chart();

    private Icon iconPeople = Graphic.createImageIcon("../assets/people.png");
    private Icon iconHouse = Graphic.createImageIcon("../assets/household.png");
    private Icon iconMove = Graphic.createImageIcon("../assets/move.png");
    private Icon iconData = Graphic.createImageIcon("../assets/data.png");

    final String PRE_HTML = "<html><p style=\"text-align: left; width: 100px\">";
    final String POST_HTML = "</p></html>";

    private JTabbedPane tp = new JTabbedPane(JTabbedPane.LEFT);

    public Home() {
        try {
            tp.addTab(null, iconPeople, pnPeople.getPeoplePanel(), "Click to see people");
            tp.addTab(null, iconHouse, pnHousehold.getHouseholdPanel(), "Click to see household");
            tp.addTab(null, iconMove, pnTRA.getTempResidence_AbsencePanel(), "Click to see temp residence & absence");
            tp.addTab(null, iconData, pnAnalysis.getChart(), "Click to see statistics");

            tp.setTitleAt(0, PRE_HTML + "People" + POST_HTML);
            tp.setTitleAt(1, PRE_HTML + "Household" + POST_HTML);
            tp.setTitleAt(2, PRE_HTML + "Absence & Temp Residence" + POST_HTML);
            tp.setTitleAt(3, PRE_HTML + "Data Statistics" + POST_HTML);

            add(tp);

            pack();

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
