package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class HForm extends JFrame implements ActionListener {
    private JLabel lbHName = new JLabel("Name");
    private JTextField tfHName;

    private JLabel lbHIdentity = new JLabel("Identity");
    private JTextField tfHIdentity;

    private JLabel lbHAddress = new JLabel("Household Address");
    private JTextField tfHAddress;

    private JLabel lbError = new JLabel("");
    private JButton btSave = new JButton("Save");

    private JPanel pnControl = new JPanel();
    private JPanel pnMain = new JPanel();

    private Household hh;
    private Statement sm;
    private Object hid;

    public HForm(String title, Household hh, Object hid, Object hname, Object hiden, Object hadd) {
        super(title);

        this.hh = hh;
        this.hid = hid;

        pnMain.setLayout(new GridLayout(4, 2, 5, 5));
        pnMain.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        tfHName = new JTextField(String.valueOf(hname));
        tfHIdentity = new JTextField(String.valueOf(hiden));
        tfHAddress = new JTextField(String.valueOf(hadd));

        pnMain.add(lbHName);
        pnMain.add(tfHName);

        pnMain.add(lbHIdentity);
        pnMain.add(tfHIdentity);

        pnMain.add(lbHAddress);
        pnMain.add(tfHAddress);

        lbError.setVisible(false);
        pnMain.add(lbError);

        btSave.setPreferredSize(new Dimension(90, 30));
        btSave.addActionListener(this);

        pnControl.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        pnControl.add(btSave);

        pnMain.add(pnControl);

        this.setContentPane(pnMain);
        this.setSize(500, 250);
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
                sql = "INSERT INTO Household VALUES (N\'"
                        + tfHName.getText() + "\', "
                        + tfHIdentity.getText() + ", N\'"
                        + tfHAddress.getText() + "\')";
            } else {
                sql = "UPDATE Household SET"
                        + " HouseholderName = N\'" + tfHName.getText()
                        + "\', HouseholderIdentity = " + tfHIdentity.getText()
                        + ", HouseholdAddress = N\'" + tfHAddress.getText()
                        +"\' WHERE HouseholdID = "+ String.valueOf(hid);
            }
            System.out.println(sql);
            hh.sm.executeUpdate(sql);

            hh.load();
            hh.model.fireTableDataChanged();

            this.dispose();

        } catch (Exception e) {
            lbError.setText(String.valueOf(e));
            lbError.setForeground(Color.RED);
            lbError.setVisible(true);
        }
    }
}
