package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import controller.PeopleController;
import model.people;

import static view.Graphic.*;

public class People implements ActionListener, MouseListener {
    //private Icon iconSearch = new ImageIcon("./assets/search.png");

    private JLabel lbSearchP = new JLabel("Search people: ",getSearchImageIcon(), JLabel.RIGHT);
    private JTextField tfSearchP = new JTextField(20);

    private JButton btAddP = new JButton("Add people", getAddImageIcon());
    private JButton btEditP = new JButton("Edit people", getEditImageIcon());
    private JButton btDeleteP = new JButton("Delete people", getDeleteImageIcon());

    DefaultTableModel modelP = new DefaultTableModel(new String[]{"PID", "PName", "PIdentity", "Householder Identity", "PRelationship with Householder", "PGender", "PBirthday", "PHometown", "PJob", "PEdu", "PReligion"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private TableRowSorter sorter;
    private JTable table;
    private JPanel pnControl = new JPanel();
    private JPanel pnP = new JPanel();

    private PeopleController pc = new PeopleController();

    private int selectedRow = 0;

    public People() {
        try {
            load();

            sorter = new TableRowSorter<>(modelP);
            table = new JTable(modelP);
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

            pnControl.add(lbSearchP);
            pnControl.add(tfSearchP);
            pnControl.add(btAddP);
            pnControl.add(btEditP);
            pnControl.add(btDeleteP);

            pnP.setLayout(new BorderLayout());
            pnP.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));

            pnP.add(pnControl, BorderLayout.NORTH);
            pnP.add(new JScrollPane(table), BorderLayout.CENTER);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void load() {
        try {
            modelP.setRowCount(0); //Clear table

            for (people person : pc.getPeople()) {
                modelP.addRow(new Object[]{
                        person.getPid(),
                        person.getPname(),
                        person.getPiden(),
                        person.getHiden(),
                        person.getPrela(),
                        person.getPgen(),
                        person.getPbirth(),
                        person.getPhometown(),
                        person.getPjob(),
                        person.getPedu(),
                        person.getPreli()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void add() {
        new PForm("Add Form", this, "", "", "", "", "", "", "", "", "", "", "");
    }

    public void edit() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete() {
        try {
            Object id = table.getValueAt(selectedRow, 0);
            pc.deletePeople(id);
            modelP.removeRow(selectedRow);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
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