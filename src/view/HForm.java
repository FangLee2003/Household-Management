package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.HouseholdController;

import static view.Graphic.*;

public class HForm extends JFrame implements ActionListener {
    private JLabel lbHName = new JLabel("Name", getNameImageIcon(), JLabel.LEFT);
    private JTextField tfHName;

    private JLabel lbHIdentity = new JLabel("Identity", createImageIcon("../assets/hidentity.png"), JLabel.LEFT);
    private JTextField tfHIdentity;

    private JLabel lbHAddress = new JLabel("Household Address", createImageIcon("../assets/address.png"), JLabel.LEFT);
    private JTextField tfHAddress;

    private JButton btSave = new JButton("Save", getSaveImageIcon());

    private JPanel pnControl = new JPanel();
    private JPanel pnMain = new JPanel();

    private Household hh;
    private Object hid;

    private HouseholdController hc = new HouseholdController();

    public HForm(String title, Household hh, Object hid, Object hname, Object hiden, Object hadd) {
        super(title);

        try {
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

            pnMain.add(new JLabel(""));

            btSave.setPreferredSize(new Dimension(90, 30));
            btSave.addActionListener(this);

            pnControl.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            pnControl.add(btSave);

            pnMain.add(pnControl);

            setContentPane(pnMain);
            setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("./assets/" + (getTitle().equals("Add Form") ? "add" : "edit") + ".png")));
            setSize(500, 250);
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
            hc.updateHousehold(getTitle().equals("Add Form") ? "Add Form" : "Edit Form",
                    String.valueOf(hid),
                    tfHName.getText(),
                    tfHIdentity.getText(),
                    tfHAddress.getText()
            );

            hh.load();
            hh.modelH.fireTableDataChanged();

            if (hc.validateHousehold()) {
                dispose();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
