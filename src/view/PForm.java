package view;

import controller.PeopleController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PForm extends JFrame implements ActionListener {
    private JLabel lbPName = new JLabel("Name");
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
    private Object pid;

    private PeopleController pc = new PeopleController();

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
            pc.editPeople(this.getTitle().equals("Add Form") ? "Add Form" : "Edit Form",
                    String.valueOf(pid),
                    tfPName.getText(),
                    tfPIdentity.getText(),
                    tfHouseholderIdentity.getText(),
                    tfPGender.getText(),
                    tfPRelationshipwithHouseholder.getText(),
                    tfPBirthday.getText(),
                    tfPHometown.getText(),
                    tfPJob.getText(),
                    tfPEdu.getText(),
                    tfPReligion.getText()
            );

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