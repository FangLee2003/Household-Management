package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class TForm extends JFrame implements ActionListener {
    private JLabel lbTName = new JLabel("Name");
    private JTextField tfTName;

    private JLabel lbTIdentity = new JLabel("Identity");
    private JTextField tfTIdentity;

    private JLabel lbTDate = new JLabel("Date");
    private JTextField tfTDate;

    private JLabel lbTempResidenceLocation = new JLabel("Temp Residence Location");
    private JTextField tfTempResidenceLocation;

    private JLabel lbAbsenceLocation = new JLabel("Absence Location");
    private JTextField tfAbsenceLocation;

    private JLabel lbTReason = new JLabel("Reason");
    private JTextField tfTReason;

    private JLabel lbError = new JLabel("");

    private JButton btSave = new JButton("Save");

    private JPanel pnControl = new JPanel();
    private JPanel pnMain = new JPanel();

    private TempResidence_Absence ta;
    private Statement sm;
    private Object tid;

    public TForm(String title, TempResidence_Absence ta, Object tid, Object tname, Object tiden, Object tdate, Object ttemp, Object tabsence, Object treason) {
        super(title);

        this.ta = ta;
        this.tid = tid;

        pnMain.setLayout(new GridLayout(7, 2, 5, 5));
        pnMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        tfTName = new JTextField(String.valueOf(tname));
        tfTIdentity = new JTextField(String.valueOf(tiden));
        tfTDate = new JTextField(String.valueOf(tdate));
        tfTempResidenceLocation = new JTextField(String.valueOf(ttemp));
        tfAbsenceLocation = new JTextField(String.valueOf(tabsence));
        tfTReason = new JTextField(String.valueOf(treason));

        pnMain.add(lbTName);
        pnMain.add(tfTName);

        pnMain.add(lbTIdentity);
        pnMain.add(tfTIdentity);

        pnMain.add(lbTDate);
        pnMain.add(tfTDate);

        pnMain.add(lbTempResidenceLocation);
        pnMain.add(tfTempResidenceLocation);

        pnMain.add(lbAbsenceLocation);
        pnMain.add(tfAbsenceLocation);

        pnMain.add(lbTReason);
        pnMain.add(tfTReason);

        lbError.setVisible(false);
        pnMain.add(lbError);

        btSave.setPreferredSize(new Dimension(100, 25));
        btSave.addActionListener(this);

        pnControl.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnControl.add(btSave);

        pnMain.add(pnControl);

        this.setContentPane(pnMain);
        this.setSize(640, 360);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            updateDB();
        }
    }

    public void updateDB() {

        try {
            String sql;
            if (this.getTitle().equals("Add Form")) {
                sql = "INSERT INTO TempResidence_Absence VALUES (N\'"
                        + tfTName.getText() + "\', "
                        + tfTIdentity.getText() + ", N\'"
                        + tfTDate.getText() + "\', N\'"
                        + tfTempResidenceLocation.getText() + "\', N\'"
                        + tfAbsenceLocation.getText() + "\', N\'"
                        + tfTReason.getText() + "\')";

            } else {
                sql = "UPDATE TempResidence_Absence SET"
                        + " TName = N\'" + tfTName.getText()
                        + "\', TIdentity = " + tfTIdentity.getText()
                        + ", TDate = N\'" + tfTDate.getText()
                        + "\', TempResidenceLocation = N\'" + tfTempResidenceLocation.getText()
                        + "\', AbsenceLocation = N\'" + tfAbsenceLocation.getText()
                        + "\', TReason = N\'" + tfTReason.getText()
                        + "\' WHERE TID = " + String.valueOf(tid);
            }
            System.out.println(sql);
            ta.sm.executeUpdate(sql);

            ta.load();
            ta.model.fireTableDataChanged();

            this.dispose();

        } catch (Exception e) {
            lbError.setText(String.valueOf(e));
            lbError.setForeground(Color.RED);
            lbError.setVisible(true);
        }
    }
}
