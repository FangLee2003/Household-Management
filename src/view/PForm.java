package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PForm extends JFrame implements ActionListener {
    private  JLabel lbPName = new JLabel("Name");
    private JTextField tfPName;

    private JLabel lbPIdentity = new JLabel("Identity");
    private JTextField tfPIdentity;

    private JLabel lbHouseholderIdentity = new JLabel("Householder Identity");
    private JTextField tfHouseholderIdentity;

    private JLabel lbPRelationshipwithHouseholder = new JLabel("Relationship with Householder");
    private JTextField tfPRelationshipwithHouseholder;

    private JLabel lbPGender = new JLabel("Gender");
    private JTextField tfPGender;

    private JLabel lbPBirthday = new JLabel("Birthday");
    private JTextField tfPBirthday;

    private JLabel lbPHometown = new JLabel("Hometown");
    private JTextField tfPHometown;

    private JLabel lbPJob = new JLabel("Job");
    private JTextField tfPJob;

    private JLabel lbPEdu = new JLabel("Education");
    private JTextField tfPEdu;

    private JLabel lbPReligion = new JLabel("Religion");
    private JTextField tfPReligion;

    private JLabel lbError = new JLabel("");
    private JButton btSave = new JButton("Save");

    private JPanel pnControl = new JPanel();
    private JPanel pnMain = new JPanel();

    private People pp;
    private Statement sm;
    private Object pid;

    public PForm(String title, People pp, Object pid, Object pname, Object piden, Object hiden, Object prela, Object pgen, Object pbirth, Object phometown, Object pjob, Object pedu, Object preli) {
        super(title);

        this.pp = pp;
        this.pid = pid;

        tfPName = new JTextField(String.valueOf(pname));
        tfPIdentity = new JTextField(String.valueOf(piden));
        tfHouseholderIdentity = new JTextField(String.valueOf(hiden));
        tfPRelationshipwithHouseholder = new JTextField(String.valueOf(prela));
        tfPGender = new JTextField(String.valueOf(pgen));
        tfPBirthday = new JTextField(String.valueOf(pbirth));
        tfPHometown = new JTextField(String.valueOf(phometown));
        tfPJob = new JTextField(String.valueOf(pjob));
        tfPEdu = new JTextField(String.valueOf(pedu));
        tfPReligion = new JTextField(String.valueOf(preli));
        
        pnMain.setLayout(new GridLayout(11, 2, 5, 5));
        pnMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        pnMain.add(lbPName);
        pnMain.add(tfPName);

        pnMain.add(lbPIdentity);
        pnMain.add(tfPIdentity);

        pnMain.add(lbHouseholderIdentity);
        pnMain.add(tfHouseholderIdentity);

        pnMain.add(lbPRelationshipwithHouseholder);
        pnMain.add(tfPRelationshipwithHouseholder);

        pnMain.add(lbPGender);
        pnMain.add(tfPGender);

        pnMain.add(lbPBirthday);
        pnMain.add(tfPBirthday);

        pnMain.add(lbPHometown);
        pnMain.add(tfPHometown);

        pnMain.add(lbPJob);
        pnMain.add(tfPJob);

        pnMain.add(lbPEdu);
        pnMain.add(tfPEdu);

        pnMain.add(lbPReligion);
        pnMain.add(tfPReligion);

        lbError.setVisible(false);
        pnMain.add(lbError);

        btSave.setPreferredSize(new Dimension(120, 30));
        btSave.addActionListener(this);

        pnControl.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnControl.add(btSave);

        pnMain.add(pnControl);

        this.setContentPane(pnMain);
        this.setSize(900, 600);
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
                sql = "INSERT INTO People VALUES (N\'"
                        + tfPName.getText() + "\', "
                        + tfPIdentity.getText() + ", "
                        + tfHouseholderIdentity.getText() + ", N\'"
                        + tfPRelationshipwithHouseholder.getText() + "\', N\'"
                        + tfPGender.getText() + "\', N\'"
                        + tfPBirthday.getText() + "\', N\'"
                        + tfPHometown.getText() + "\', N\'"
                        + tfPJob.getText() + "\', N\'"
                        + tfPEdu.getText() + "\', N\'"
                        + tfPReligion.getText()
                        + "\')";

            } else {
                sql = "UPDATE People SET"
                        + " PName = N\'" + tfPName.getText()
                        + "\', PIdentity = " + tfPIdentity.getText()
                        + ", HouseholderIdentity = " + tfHouseholderIdentity.getText()
                        + ", PRelationshipwithHouseholder = N\'" + tfPRelationshipwithHouseholder.getText()
                        + "\', PGender = N\'" + tfPGender.getText()
                        + "\', PBirthday = N\'" + tfPBirthday.getText()
                        + "\', PHometown = N\'" + tfPHometown.getText()
                        + "\', PJob = N\'" + tfPJob.getText()
                        + "\', PEdu = N\'" + tfPEdu.getText()
                        + "\', PReligion = N\'" + tfPReligion.getText()
                        + "\' WHERE PID = " + String.valueOf(pid);
            }
            System.out.println(sql);
            pp.sm.executeUpdate(sql);

            pp.load();
            pp.model.fireTableDataChanged();

            this.dispose();

        } catch (Exception e) {
            lbError.setText(String.valueOf(e));
            lbError.setForeground(Color.RED);
            lbError.setVisible(true);
        }
    }
}