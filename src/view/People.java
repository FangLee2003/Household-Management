package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;

import controller.*;

public class People implements ActionListener, MouseListener {
    //private Icon iconSearch = new ImageIcon("./assets/search.png");

    private JLabel lbSearchP = new JLabel("Search people: ");
    private JTextField tfSearchP = new JTextField(20);

    private JButton btAddP = new JButton("Add people");
    private  JButton btEditP = new JButton("Edit people");
    private JButton btDeleteP = new JButton("Delete people");

    DefaultTableModel model = new DefaultTableModel(new String[]{"PID", "PName", "PIdentity", "Householder Identity", "PRelationship with Householder", "PGender", "PBirthday", "PHometown", "PJob", "PEdu", "PReligion"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private TableRowSorter sorter;
    private JTable table;
    private JPanel pnControl = new JPanel();
    private JPanel pnP = new JPanel();

    Connection con;
    Statement sm;

    int selectedRow = 0;

    public People() {
        try {
            con = ConnectionSQL.getConnection();
            sm = con.createStatement();

            load();

            sorter = new TableRowSorter<>(model);
            table = new JTable(model);
            table.setRowSorter(sorter);

            table.addMouseListener(this);

            btAddP.addActionListener(this);
            btEditP.addActionListener(this);
            btDeleteP.addActionListener(this);

            tfSearchP.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    search(tfSearchP.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    search(tfSearchP.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    search(tfSearchP.getText());
                }

                public void search(String str) {
                    if (str.length() == 0) {
                        sorter.setRowFilter(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                    }
                }
            });

            pnControl.add(lbSearchP, BorderLayout.NORTH);
            pnControl.add(tfSearchP, BorderLayout.NORTH);
            pnControl.add(btAddP, BorderLayout.NORTH);
            pnControl.add(btEditP, BorderLayout.NORTH);
            pnControl.add(btDeleteP, BorderLayout.NORTH);

            pnP.setLayout(new BorderLayout());
            pnP.add(pnControl, BorderLayout.NORTH);
            pnP.add(new JScrollPane(table), BorderLayout.CENTER);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void load() {
        try {
            model.setRowCount(0); //Clear table

            ResultSet rs = sm.executeQuery("SELECT * FROM People");
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
        new PForm("Add Form", this, "", "", "", "", "", "", "", "", "", "", "");
    }

    public void edit() {
        new PForm("Edit Form", this,
                table.getValueAt(selectedRow, 0),
                table.getValueAt(selectedRow, 1),
                table.getValueAt(selectedRow, 2),
                table.getValueAt(selectedRow, 3),
                table.getValueAt(selectedRow, 4),
                table.getValueAt(selectedRow, 5),
                table.getValueAt(selectedRow, 6),
                table.getValueAt(selectedRow, 7),
                table.getValueAt(selectedRow, 8),
                table.getValueAt(selectedRow, 9),
                table.getValueAt(selectedRow, 10));
    }

    public void delete() {
        try {
            Object id = table.getValueAt(selectedRow, 0);
            model.removeRow(selectedRow);
            String sql = "DELETE FROM People WHERE PID = " + id;
            sm.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public JPanel getPeoplePanel() {
        return pnP;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Add people")) {
            add();
        }
        if (ae.getActionCommand().equals("Edit people")) {
            edit();
        }
        if (ae.getActionCommand().equals("Delete people")) {
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