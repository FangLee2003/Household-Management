package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import controller.Absence_TempResidenceController;
import model.absence_tempresidence;

import static view.Graphic.*;

public class Absence_TempResidence implements ActionListener, MouseListener {
    private JLabel lbSearchT = new JLabel("Search TempResidence_Absence: ", getSearchImageIcon(), JLabel.RIGHT);
    private JTextField tfSearchT = new JTextField(20);
    private JButton btAddT = new JButton("Add TempResidence_Absence", getAddImageIcon());
    private JButton btEditT = new JButton("Edit TempResidence_Absence", getEditImageIcon());
    private JButton btDeleteT = new JButton("Delete TempResidence_Absence", getDeleteImageIcon());

    DefaultTableModel modelT = new DefaultTableModel(new String[]{"TID", "TName", "TIdentity", "TDate", "AbsenceLocation", "TempResidenceLocation", "TReason"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private TableRowSorter sorter;
    private JTable table;
    private JPanel pnControl = new JPanel();
    private JPanel pnT = new JPanel();

    private Absence_TempResidenceController tc = new Absence_TempResidenceController();

    private int selectedRow = 0;

    public Absence_TempResidence() {
        try {
            load();

            sorter = new TableRowSorter<>(modelT);
            table = new JTable(modelT);
            table.setRowSorter(sorter);

            table.addMouseListener(this);

            btAddT.addActionListener(this);
            btEditT.addActionListener(this);
            btDeleteT.addActionListener(this);

            tfSearchT.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tfSearchT.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tfSearchT.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tfSearchT.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                    }
                }
            });

            pnControl.add(lbSearchT);
            pnControl.add(tfSearchT);
            pnControl.add(btAddT);
            pnControl.add(btEditT);
            pnControl.add(btDeleteT);

            pnT.setLayout(new BorderLayout());
            pnT.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

            pnT.add(pnControl, BorderLayout.NORTH);
            pnT.add(new JScrollPane(table), BorderLayout.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void load() {
        try {
            modelT.setRowCount(0); //Clear table

            for (absence_tempresidence temp : tc.getAbsence_TempResidence()) {
                modelT.addRow(new Object[]{
                        temp.getTid(),
                        temp.getTname(),
                        temp.getTiden(),
                        temp.getTdate(),
                        temp.getTtemp(),
                        temp.getTabsence(),
                        temp.getTreason()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void add() {
        new TForm("Add Form", this, "", "", "", "", "", "", "");
    }

    public void edit() {
        try {
            new TForm("Edit Form", this,
                    table.getValueAt(selectedRow, 0),
                    table.getValueAt(selectedRow, 1),
                    table.getValueAt(selectedRow, 2),
                    table.getValueAt(selectedRow, 3),
                    table.getValueAt(selectedRow, 4),
                    table.getValueAt(selectedRow, 5),
                    table.getValueAt(selectedRow, 6));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete() {
        try {
            Object id = table.getValueAt(selectedRow, 0);
            tc.getAbsence_TempResidence();
            modelT.removeRow(selectedRow);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getTempResidence_AbsencePanel() {
        return pnT;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Add TempResidence_Absence")) {
            add();
        }
        if (ae.getActionCommand().equals("Edit TempResidence_Absence")) {
            edit();
        }
        if (ae.getActionCommand().equals("Delete TempResidence_Absence")) {
            delete();
        }

    }

    @Override
    public void mouseClicked(MouseEvent me) {
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

