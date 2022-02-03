package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import controller.HouseholdController;
import model.household;

import static view.Graphic.*;

public class Household implements ActionListener, MouseListener {
    private JLabel lbSearchH = new JLabel("Search household: ",getSearchImageIcon(), JLabel.RIGHT);
    private JTextField tfSearchH = new JTextField(20);
    private JButton btAddH = new JButton("Add household", getAddImageIcon());
    private JButton btEditH = new JButton("Edit household", getEditImageIcon());
    private JButton btDeleteH = new JButton("Delete household", getDeleteImageIcon());

    DefaultTableModel modelH = new DefaultTableModel(new String[]{"HouseholdID", "HouseholderName", "HouseholderIdentity", "HouseholdAddress"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private TableRowSorter sorter;
    private JTable table;
    private JPanel pnControl = new JPanel();
    private JPanel pnH = new JPanel();

    private HouseholdController hc = new HouseholdController();

    private int selectedRow = 0;

    public Household() {
        try {
            load();

            sorter = new TableRowSorter<>(modelH);
            table = new JTable(modelH);
            table.setRowSorter(sorter);

            table.addMouseListener(this);

            btAddH.addActionListener(this);
            btEditH.addActionListener(this);
            btDeleteH.addActionListener(this);

            tfSearchH.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tfSearchH.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tfSearchH.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tfSearchH.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                    }
                }
            });

            pnControl.add(lbSearchH);
            pnControl.add(tfSearchH);
            pnControl.add(btAddH);
            pnControl.add(btEditH);
            pnControl.add(btDeleteH);

            pnH.setLayout(new BorderLayout());
            pnH.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

            pnH.add(pnControl, BorderLayout.NORTH);
            pnH.add(new JScrollPane(table), BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void load() {
        try {
            modelH.setRowCount(0); //Clear table

            for (household house : hc.getHousehold()) {
                modelH.addRow(new Object[]{
                        house.getHid(),
                        house.getHname(),
                        house.getHiden(),
                        house.getHadd()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void add() {
        new HForm("Add Form", this, "", "", "", "");
    }

    public void edit() {
        try {
            new HForm("Edit Form", this,
                    table.getValueAt(selectedRow, 0),
                    table.getValueAt(selectedRow, 1),
                    table.getValueAt(selectedRow, 2),
                    table.getValueAt(selectedRow, 3));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete() {
        try {
            Object id = table.getValueAt(selectedRow, 0);
            hc.deleteHousehold(id);
            modelH.removeRow(selectedRow);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getHouseholdPanel() {
        return pnH;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Add household")) {
            add();
        }
        if (ae.getActionCommand().equals("Edit household")) {
            edit();
        }
        if (ae.getActionCommand().equals("Delete household")) {
            delete();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        selectedRow = table.getSelectedRow();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}