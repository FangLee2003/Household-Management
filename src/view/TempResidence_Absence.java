package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;

import controller.*;

public class TempResidence_Absence implements ActionListener, MouseListener {
    private JLabel lbSearchT = new JLabel("Search TempResidence_Absence: ");
    private JTextField tfSearchT = new JTextField(20);
    private JButton btAddT = new JButton("Add TempResidence_Absence");
    private JButton btEditT = new JButton("Edit TempResidence_Absence");
    private JButton btDeleteT = new JButton("Delete TempResidence_Absence");

    DefaultTableModel model = new DefaultTableModel(new String[]{"TID", "TName", "TIdentity", "TDate", "TempResidenceLocation", "AbsenceLocation", "TReason"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private TableRowSorter sorter;
    private JTable table;
    private JPanel pnControl = new JPanel();
    private JPanel pnT = new JPanel();

    Connection con;
    Statement sm;

    int selectedRow = 0;

    public TempResidence_Absence() {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();

            load();

            sorter = new TableRowSorter<>(model);
            table = new JTable(model);
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

            pnControl.add(lbSearchT, BorderLayout.NORTH);
            pnControl.add(tfSearchT, BorderLayout.NORTH);
            pnControl.add(btAddT, BorderLayout.NORTH);
            pnControl.add(btEditT, BorderLayout.NORTH);
            pnControl.add(btDeleteT, BorderLayout.NORTH);

            pnT.setLayout(new BorderLayout());
            pnT.add(pnControl, BorderLayout.NORTH);
            pnT.add(new JScrollPane(table), BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void load() {
        try {
            model.setRowCount(0); //Clear table

            ResultSet rs = sm.executeQuery("SELECT * FROM TempResidence_Absence");
            ResultSetMetaData rsmd = rs.getMetaData();
            int num_column = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] row = new Object[num_column];
                for (int i = 0; i < num_column; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void add() {
        new TForm("Add Form", this, "", "", "", "", "", "", "");
    }

    public void edit() {
        new TForm("Edit Form", this,
                table.getValueAt(selectedRow, 0),
                table.getValueAt(selectedRow, 1),
                table.getValueAt(selectedRow, 2),
                table.getValueAt(selectedRow, 3),
                table.getValueAt(selectedRow, 4),
                table.getValueAt(selectedRow, 5),
                table.getValueAt(selectedRow, 6));
    }

    public void delete() {
        try {
            Object id = table.getValueAt(selectedRow, 0);
            model.removeRow(selectedRow);
            String sql = "DELETE FROM TempResidence_Absence WHERE TID = " + id;
            sm.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
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

