package com.mycompany.database;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class PrimaryController implements Initializable {

    @FXML 
    private TextField txtId;
    
    @FXML 
    private TextField txtFirstName;
    
    @FXML 
    private TextField txtMiddleName;
    
    @FXML 
    private TextField txtLastName;
    
    @FXML 
    private TextField txtEmail;
    
    @FXML 
    private TextField txtPhone;

    private ResultSet rs;
    private boolean isNewMode = false; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setEditable(false);
        loadData();
    }

    private void loadData() {
        try {
            Connection con = DAO.getConnection();
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            rs = st.executeQuery("SELECT * FROM CONTACT ORDER BY ID");

            if (rs.next()) {
                showData();
            } else {
                clearFields();
            }

        } catch (Exception e) {
            alert("Error loading data: " + e.getMessage());
        }
    }

    private void showData() throws Exception {
        txtId.setText(String.valueOf(rs.getInt("ID")));
        txtFirstName.setText(rs.getString("FNAME"));
        txtMiddleName.setText(rs.getString("MNAME"));
        txtLastName.setText(rs.getString("LNAME"));
        txtEmail.setText(rs.getString("EMAIL"));
        txtPhone.setText(rs.getString("PHONENUMBER"));
        isNewMode = false;
    }

    private void clearFields() {
        txtId.clear();
        txtFirstName.clear();
        txtMiddleName.clear();
        txtLastName.clear();
        txtEmail.clear();
        txtPhone.clear();
    }

    private int getNextId() {
        int id = 1;
        try (Connection con = DAO.getConnection();
             Statement st = con.createStatement();
             ResultSet r = st.executeQuery("SELECT MAX(ID) FROM CONTACT")) {

            if (r.next()) {
                id = r.getInt(1) + 1;
            }
        } catch (Exception e) {
            System.out.println("Error getting next ID: " + e.getMessage());
        }
        return id;
    }

    private boolean areFieldsFilled() {
        return !txtFirstName.getText().trim().isEmpty() && 
               !txtLastName.getText().trim().isEmpty();
    }

    private void saveCurrentRecord() {
        if (!areFieldsFilled()) {
            alert("Please fill at least First Name and Last Name");
            return;
        }

        if (txtId.getText().trim().isEmpty()) {
            alert("Error: ID is missing");
            return;
        }

        try (Connection con = DAO.getConnection()) {
            
            if (isNewMode) {
                // Insert new record
                String sql = "INSERT INTO CONTACT (ID, FNAME, MNAME, LNAME, EMAIL, PHONENUMBER) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setInt(1, Integer.parseInt(txtId.getText().trim()));
                    pst.setString(2, txtFirstName.getText().trim());
                    pst.setString(3, txtMiddleName.getText().trim());
                    pst.setString(4, txtLastName.getText().trim());
                    pst.setString(5, txtEmail.getText().trim());
                    pst.setString(6, txtPhone.getText().trim());
                    pst.executeUpdate();
                    alert("Saved successfully!");
                }
            } else {
                // Update existing record
                String sql = "UPDATE CONTACT SET FNAME=?, MNAME=?, LNAME=?, EMAIL=?, PHONENUMBER=? WHERE ID=?";
                try (PreparedStatement pst = con.prepareStatement(sql)) {
                    pst.setString(1, txtFirstName.getText().trim());
                    pst.setString(2, txtMiddleName.getText().trim());
                    pst.setString(3, txtLastName.getText().trim());
                    pst.setString(4, txtEmail.getText().trim());
                    pst.setString(5, txtPhone.getText().trim());
                    pst.setInt(6, Integer.parseInt(txtId.getText().trim()));
                    pst.executeUpdate();
                    alert("Updated successfully!");
                }
            }
            
            // Reset state after save
            isNewMode = false;
            loadData();

        } catch (NumberFormatException e) {
            alert("Error: Invalid ID format - " + e.getMessage());
        } catch (Exception e) {
            alert("Error saving record: " + e.getMessage());
            e.printStackTrace(); // For debugging
        }
    }

    @FXML
    private void newRecord(ActionEvent event) {
        // If in new mode with filled fields, save the record
        if (isNewMode && areFieldsFilled()) {
            saveCurrentRecord();
            return;
        }
        
        // Prepare for new record entry
        clearFields();
        int newId = getNextId();
        txtId.setText(String.valueOf(newId));
        isNewMode = true;
        txtFirstName.requestFocus();
        alert("Ready for new record\nFill the fields and press New again to save");
    }

    @FXML
    private void update(ActionEvent event) {
        // If fields are filled and we're in update mode, save changes
        if (!isNewMode && !txtId.getText().isEmpty() && areFieldsFilled()) {
            saveCurrentRecord();
            return;
        }
        
        // Ask for ID to update
        TextInputDialog d = new TextInputDialog();
        d.setTitle("Update Record");
        d.setHeaderText("Enter ID to update");
        d.setContentText("ID:");

        Optional<String> result = d.showAndWait();
        if (result.isEmpty()) return;

        try {
            int id = Integer.parseInt(result.get());
            String sql = "SELECT * FROM CONTACT WHERE ID=?";

            try (Connection con = DAO.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setInt(1, id);
                ResultSet rsUpdate = pst.executeQuery();

                if (!rsUpdate.next()) {
                    alert("ID not found");
                    return;
                }

                // Display data for editing
                txtId.setText(String.valueOf(rsUpdate.getInt("ID")));
                txtFirstName.setText(rsUpdate.getString("FNAME"));
                txtMiddleName.setText(rsUpdate.getString("MNAME"));
                txtLastName.setText(rsUpdate.getString("LNAME"));
                txtEmail.setText(rsUpdate.getString("EMAIL"));
                txtPhone.setText(rsUpdate.getString("PHONENUMBER"));
                isNewMode = false;
                
                alert("Modify the data and press Update again to save");
            }

        } catch (NumberFormatException e) {
            alert("Please enter a valid number");
        } catch (Exception e) {
            alert("Error: " + e.getMessage());
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        TextInputDialog d = new TextInputDialog();
        d.setTitle("Delete Record");
        d.setHeaderText("Enter ID to delete");
        d.setContentText("ID:");

        Optional<String> result = d.showAndWait();
        if (result.isEmpty()) return;

        try {
            int id = Integer.parseInt(result.get());
            String sql = "DELETE FROM CONTACT WHERE ID=?";

            try (Connection con = DAO.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setInt(1, id);
                int rows = pst.executeUpdate();

                if (rows > 0) {
                    alert("Deleted successfully!");
                    clearFields();
                    loadData();
                } else {
                    alert("ID not found");
                }
            }

        } catch (NumberFormatException e) {
            alert("Please enter a valid number");
        } catch (Exception e) {
            alert("Error: " + e.getMessage());
        }
    }

    @FXML 
    private void first(ActionEvent event) {
        try { 
            if (rs != null && rs.first()) {
                showData();
            }
        } catch (Exception e) { 
            alert("Error: " + e.getMessage()); 
        }
    }

    @FXML 
    private void last(ActionEvent event) {
        try { 
            if (rs != null && rs.last()) {
                showData();
            }
        } catch (Exception e) { 
            alert("Error: " + e.getMessage()); 
        }
    }

    @FXML 
    private void next(ActionEvent event) {
        try { 
            if (rs != null && rs.next()) {
                showData();
            }
        } catch (Exception e) { 
            alert("Error: " + e.getMessage()); 
        }
    }

    @FXML 
    private void previous(ActionEvent event) {
        try { 
            if (rs != null && rs.previous()) {
                showData();
            }
        } catch (Exception e) { 
            alert("Error: " + e.getMessage()); 
        }
    }

    private void alert(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(msg);
        a.show();
    }
}