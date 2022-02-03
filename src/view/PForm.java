package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.PeopleController;

import static view.Graphic.*;

public class PForm extends JFrame implements ActionListener {
    private JLabel lbPName = new JLabel("Name", getNameImageIcon(), JLabel.LEFT);
    private JTextField tfPName;

    private JLabel lbPIdentity = new JLabel("Identity", createImageIcon("../assets/pidentity.png"), JLabel.LEFT);
    private JTextField tfPIdentity;

    private JLabel lbHouseholderIdentity = new JLabel("Householder Identity", createImageIcon("../assets/hidentity.png"), JLabel.LEFT);
    private JTextField tfHouseholderIdentity;

    private JLabel lbPRelationshipwithHouseholder = new JLabel("Relationship with Householder", createImageIcon("../assets/relationship.png"), JLabel.LEFT);
    private JTextField tfPRelationshipwithHouseholder;

    private JLabel lbPGender = new JLabel("Gender", createImageIcon("../assets/gender.png"), JLabel.LEFT);
    private JTextField tfPGender;

    private JLabel lbPBirthday = new JLabel("Birthday", createImageIcon("../assets/birthday.png"), JLabel.LEFT);
    private JTextField tfPBirthday;

    private JLabel lbPHometown = new JLabel("Hometown", createImageIcon("../assets/hometown.png"), JLabel.LEFT);
    private JTextField tfPHometown;

    private JLabel lbPJob = new JLabel("Job", createImageIcon("../assets/job.png"), JLabel.LEFT);
    private JTextField tfPJob;

    private JLabel lbPEdu = new JLabel("Education", createImageIcon("../assets/education.png"), JLabel.LEFT);
    private JTextField tfPEdu;

    private JLabel lbPReligion = new JLabel("Religion", createImageIcon("../assets/religion.png"), JLabel.LEFT);
    private JTextField tfPReligion;

    private JButton btSave = new JButton("Save", getSaveImageIcon());

    private JPanel pnControl = new JPanel();
    private JPanel pnMain = new JPanel();

    private People pp;
    private Object pid;

    private PeopleController pc = new PeopleController();

    public PForm(String title, People pp, Object pid, Object pname, Object piden, Object hiden, Object prela, Object pgen, Object pbirth, Object phometown, Object pjob, Object pedu, Object preli) {
        super(title);
        try {
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

            pnMain.add(new JLabel(""));

            btSave.setPreferredSize(new Dimension(120, 30));
            btSave.addActionListener(this);

            pnControl.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            pnControl.add(btSave);

            pnMain.add(pnControl);

            setContentPane(pnMain);
            setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("./assets/" + (getTitle().equals("Add Form") ? "add" : "edit") + ".png")));
            setSize(900, 600);
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
            pc.updatePeople(getTitle().equals("Add Form") ? "Add Form" : "Edit Form",
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
            pp.modelP.fireTableDataChanged();

            if (pc.validatePeople()) {
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}