package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Absence_TempResidenceController;

import static view.Graphic.*;

public class TForm extends JFrame implements ActionListener {
    private JLabel lbTName = new JLabel("Name", getNameImageIcon(), JLabel.LEFT);
    private JTextField tfTName;

    private JLabel lbTIdentity = new JLabel("Identity", createImageIcon("../assets/pidentity.png"), JLabel.LEFT);
    private JTextField tfTIdentity;

    private JLabel lbTDate = new JLabel("Date", createImageIcon("../assets/date.png"), JLabel.LEFT);
    private JTextField tfTDate;

    private JLabel lbAbsenceLocation = new JLabel("Absence Location", createImageIcon("../assets/address.png"), JLabel.LEFT);
    private JTextField tfAbsenceLocation;

    private JLabel lbTempResidenceLocation = new JLabel("Temp Residence Location", createImageIcon("../assets/finish.png"), JLabel.LEFT);
    private JTextField tfTempResidenceLocation;

    private JLabel lbTReason = new JLabel("Reason", createImageIcon("../assets/reason.png"), JLabel.LEFT);
    private JTextField tfTReason;

    private JButton btSave = new JButton("Save", getSaveImageIcon());

    private JPanel pnControl = new JPanel();
    private JPanel pnMain = new JPanel();

    private Absence_TempResidence ta;
    private Object tid;

    private Absence_TempResidenceController tc = new Absence_TempResidenceController();

    public TForm(String title, Absence_TempResidence ta, Object tid, Object tname, Object tiden, Object tdate, Object tabsence, Object ttemp, Object treason) {
        super(title);
        try {
            this.ta = ta;
            this.tid = tid;

            pnMain.setLayout(new GridLayout(7, 2, 5, 5));
            pnMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            tfTName = new JTextField(String.valueOf(tname));
            tfTIdentity = new JTextField(String.valueOf(tiden));
            tfTDate = new JTextField(String.valueOf(tdate));
            tfAbsenceLocation = new JTextField(String.valueOf(tabsence));
            tfTempResidenceLocation = new JTextField(String.valueOf(ttemp));
            tfTReason = new JTextField(String.valueOf(treason));

            pnMain.add(lbTName);
            pnMain.add(tfTName);

            pnMain.add(lbTIdentity);
            pnMain.add(tfTIdentity);

            pnMain.add(lbTDate);
            pnMain.add(tfTDate);

            pnMain.add(lbAbsenceLocation);
            pnMain.add(tfAbsenceLocation);

            pnMain.add(lbTempResidenceLocation);
            pnMain.add(tfTempResidenceLocation);

            pnMain.add(lbTReason);
            pnMain.add(tfTReason);

            pnMain.add(new JLabel(""));

            btSave.setPreferredSize(new Dimension(90, 30));
            btSave.addActionListener(this);

            pnControl.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            pnControl.add(btSave);

            pnMain.add(pnControl);

            setContentPane(pnMain);
            setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("./assets/" + (getTitle().equals("Add Form") ? "add" : "edit") + ".png")));
            setSize(640, 360);
            setResizable(false);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            updateDB();
        }
    }

    public void updateDB() {

        try {
            tc.updateAbsence_TempResidence(getTitle().equals("Add Form") ? "Add Form" : "Edit Form",
                    String.valueOf(tid),
                    tfTName.getText(),
                    tfTIdentity.getText(),
                    tfTDate.getText(),
                    tfAbsenceLocation.getText(),
                    tfTempResidenceLocation.getText(),
                    tfTReason.getText()
            );

            ta.load();
            ta.modelT.fireTableDataChanged();

            if (tc.validateAbsence_TempResidence()) {
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
