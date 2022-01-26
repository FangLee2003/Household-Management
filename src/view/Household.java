package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;

import controller.*;

public class Household implements ActionListener, MouseListener {
    private JLabel lbSearchH = new JLabel("Search household: ");
    private JTextField tfSearchH = new JTextField(20);
    private JButton btAddH = new JButton("Add household");
    private JButton btEditH = new JButton("Edit household");
    private JButton btDeleteH = new JButton("Delete household");

    DefaultTableModel model = new DefaultTableModel(new String[]{"HouseholdID", "HouseholderName", "HouseholderIdentity", "HouseholdAddress"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private TableRowSorter sorter;
    private JTable table;
    private JPanel pnControl = new JPanel();
    private JPanel pnH = new JPanel();

    Connection con;
    Statement sm;

    int selectedRow = 0;

    public Household() {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();

            load();

            sorter = new TableRowSorter<>(model);
            table = new JTable(model);
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

            pnControl.add(lbSearchH, BorderLayout.NORTH);
            pnControl.add(tfSearchH, BorderLayout.NORTH);
            pnControl.add(btAddH, BorderLayout.NORTH);
            pnControl.add(btEditH, BorderLayout.NORTH);
            pnControl.add(btDeleteH, BorderLayout.NORTH);

            pnH.setLayout(new BorderLayout());
            pnH.add(pnControl, BorderLayout.NORTH);
            pnH.add(new JScrollPane(table), BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void load() {
        try {
            model.setRowCount(0); //Clear table

            ResultSet rs = sm.executeQuery("SELECT * FROM Household");
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
        new HForm("Add Form", this, "", "", "", "");
    }

    public void edit() {
        new HForm("Edit Form", this,
                table.getValueAt(selectedRow, 0),
                table.getValueAt(selectedRow, 1),
                table.getValueAt(selectedRow, 2),
                table.getValueAt(selectedRow, 3));
    }

    public void delete() {
        try {
            Object id = table.getValueAt(selectedRow, 0);
            model.removeRow(selectedRow);
            String sql = "DELETE FROM Household WHERE HouseholdID = " + id;
            sm.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
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