/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.notepad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author ANTER
 */
public class PrimaryController implements Initializable {


    @FXML
    private MenuItem newMenuItem;
    @FXML
    private MenuItem openMenuItem;
    @FXML
    private MenuItem saveMenuItem;
    @FXML
    private MenuItem exitMenuItem;
    @FXML
    private MenuItem selectAllMenuItem;
    @FXML
    private MenuItem pasteMenuItem;
    @FXML
    private MenuItem copyMenuItem;
    @FXML
    private MenuItem cutMenuItem;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem aboutMenuItem;
    @FXML
    private TextArea textArea;
    
    private boolean isModified = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       textArea.textProperty().addListener((obs, oldText, newText) -> {
        isModified = true;
       });    
    }    
    
    @FXML
    private void newItem(ActionEvent event) {
       if (isModified) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unsaved Changes");
        alert.setHeaderText("You have unsaved changes");
        alert.setContentText("Do you want to save before creating a new file?");

        ButtonType save = new ButtonType("Save");
        ButtonType dontSave = new ButtonType("Don't Save");
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(save, dontSave, cancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == save) {
                save(null);     // ننادي save
                textArea.clear();
                isModified = false;
            } else if (result.get() == dontSave) {
                textArea.clear();
                isModified = false;
            }
        }

       } else {
        textArea.clear();
      }
    }

    @FXML
    private void open(ActionEvent event) {
       FileChooser fc = new FileChooser();
       File file = fc.showOpenDialog(null);
       //هل رجع فعلا فايل بالفعل ؟
       if(file !=null){
           FileInputStream fis;
            try {
                fis = new FileInputStream(file);
                 // بنشوف فيه مساحه قد اي ف الفايل 
                 int size = fis.available();
                 byte[] b = new byte[size];
                 fis.read(b);
                 textArea.setText(new String(b));
                 fis.close();
                 isModified= false;
            } catch (FileNotFoundException ex) {
                System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            } catch (IOException ex) {
                System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
       }
    }

    @FXML
    private void save(ActionEvent event) {
       FileChooser fc = new FileChooser();
       File file = fc.showSaveDialog(null);
       // هل المستخدم اختار فايل؟
       if(file!=null){
           FileOutputStream fos;
           try {
               fos = new FileOutputStream(file);
               byte[] b = textArea.getText().getBytes();
               fos.write(b);
               fos.close();
               isModified= false;
           } catch (FileNotFoundException ex) {
               System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
           } catch (IOException ex) {
               System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
           }
       }
    }

    @FXML
    private void close(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void selectAll(ActionEvent event) {
        textArea.selectAll();
    }

    @FXML
    private void paste(ActionEvent event) {
        textArea.paste();
    }

    @FXML
    private void copy(ActionEvent event) {
        textArea.copy();
    }

    @FXML
    private void cut(ActionEvent event) {
        textArea.cut();
    }

    @FXML
    private void delete(ActionEvent event) {
        textArea.replaceSelection("");
    }

    @FXML
    private void about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Notepad");
        alert.setHeaderText("Simple JavaFX Notepad");
        alert.setContentText("Created using JavaFX & FXML");
        alert.showAndWait();
    }

}
