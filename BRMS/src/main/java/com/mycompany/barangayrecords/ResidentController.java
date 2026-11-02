package com.mycompany.barangayrecords;

import java.awt.event.*;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

public class ResidentController {

    private final ResidentView view;

    public ResidentController(ResidentView view) {
        this.view = view;
        attachListeners();
        loadResidents();
    }

    private void attachListeners() {
        view.btnAdd.addActionListener(e -> addResident());
        view.btnUpdate.addActionListener(e -> updateResident());
        view.btnDelete.addActionListener(e -> deleteResident());
        view.btnClear.addActionListener(e -> view.clearFields());
        view.btnSearch.addActionListener(e -> searchResidents());
        view.btnShowAll.addActionListener(e -> {
            view.txtSearch.setText("");
            loadResidents();
        });
        view.txtSearch.addActionListener(e -> searchResidents());

        view.tblResidents.getSelectionModel().addListSelectionListener(
                (ListSelectionEvent e) -> {
                    if (!e.getValueIsAdjusting()
                            && view.tblResidents.getSelectedRow() != -1) {
                        loadSelectedResident();
                    }
                }
        );
    }

    private Resident readForm() {
        String first = view.txtFirstName.getText().trim();
        String last = view.txtLastName.getText().trim();
        String sex = (String) view.cmbSex.getSelectedItem();
        String birth = view.txtBirthDate.getText().trim();
        String address = view.txtAddress.getText().trim();
        String contact = view.txtContact.getText().trim();
        String civil = (String) view.cmbCivilStatus.getSelectedItem();

        if (first.isEmpty() || last.isEmpty() || address.isEmpty()
                || birth.isEmpty() || "Select".equals(sex)
                || "Select".equals(civil)) {
            JOptionPane.showMessageDialog(view,
                    "Please complete all required fields.",
                    "Missing Information", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            Resident r = new Resident();
            r.setFirstName(first);
            r.setLastName(last);
            r.setSex(sex);
            r.setBirthDate(LocalDate.parse(birth));
            r.setAddress(address);
            r.setContactNo(contact);
            r.setCivilStatus(civil);
            return r;
        } catch (java.time.format.DateTimeParseException ex) {
            JOptionPane.showMessageDialog(view,
                    "Enter the birth date in YYYY-MM-DD format (example: 2002-05-18).",
                    "Invalid Date", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    private void addResident() {
        Resident r = readForm();
        if (r == null) return;

        try {
            DAO.save(r);
            JOptionPane.showMessageDialog(view, "Resident added successfully!");
            view.clearFields();
            loadResidents();
        } catch (SQLException ex) {
            DB.showConnectionError(ex);
        }
    }

    private void updateResident() {
        if (view.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Select a resident from the table first.");
            return;
        }

        Resident r = readForm();
        if (r == null) return;
        r.setResidentId(Integer.parseInt(view.txtId.getText()));

        try {
            DAO.update(r);
            JOptionPane.showMessageDialog(view, "Resident updated successfully!");
            view.clearFields();
            loadResidents();
        } catch (SQLException ex) {
            DB.showConnectionError(ex);
        }
    }

    private void deleteResident() {
        if (view.txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Select a resident from the table first.");
            return;
        }

        int answer = JOptionPane.showConfirmDialog(view,
                "Are you sure you want to delete this resident record?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (answer != JOptionPane.YES_OPTION) return;

        try {
            DAO.delete(Integer.parseInt(view.txtId.getText()));
            JOptionPane.showMessageDialog(view, "Resident deleted successfully!");
            view.clearFields();
            loadResidents();
        } catch (SQLException ex) {
            DB.showConnectionError(ex);
        }
    }

    private void loadResidents() {
        try {
            displayRows(DAO.getAll());
        } catch (SQLException ex) {
            DB.showConnectionError(ex);
        }
    }

    private void searchResidents() {
        try {
            displayRows(DAO.search(view.txtSearch.getText().trim()));
        } catch (SQLException ex) {
            DB.showConnectionError(ex);
        }
    }

    private void displayRows(java.util.List<Resident> residents) {
        view.tableModel.setRowCount(0);
        for (Resident r : residents) {
            view.tableModel.addRow(new Object[]{
                r.getResidentId(), r.getFirstName(), r.getLastName(),
                r.getSex(), r.getBirthDate(), r.getAddress(),
                r.getContactNo(), r.getCivilStatus()
            });
        }
    }

    private void loadSelectedResident() {
        int row = view.tblResidents.getSelectedRow();
        if (row < 0) return;

        view.txtId.setText(view.tableModel.getValueAt(row, 0).toString());
        view.txtFirstName.setText(view.tableModel.getValueAt(row, 1).toString());
        view.txtLastName.setText(view.tableModel.getValueAt(row, 2).toString());
        view.cmbSex.setSelectedItem(view.tableModel.getValueAt(row, 3).toString());
        view.txtBirthDate.setText(view.tableModel.getValueAt(row, 4).toString());
        view.txtAddress.setText(view.tableModel.getValueAt(row, 5).toString());
        Object contact = view.tableModel.getValueAt(row, 6);
        view.txtContact.setText(contact == null ? "" : contact.toString());
        view.cmbCivilStatus.setSelectedItem(view.tableModel.getValueAt(row, 7).toString());
    }
}
