package com.mycompany.chatroomclient;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.*;
import java.net.Socket;

public class ChatController {
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private TextField textField;
    
    @FXML
    private Button sendButton;
    
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    
    @FXML
    public void initialize() {
        // Auto-focus on text field when the window loads
        textField.requestFocus();
        
        // Connect to server
        connectToServer();
    }
    
    void connectToServer() {
        try {
            socket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            
            Platform.runLater(() ->
                textArea.appendText("Connected to server\n\n")
            );
            
            // Thread to receive messages from server
            new Thread(() -> {
                try {
                    while (true) {
                        String msg = dis.readUTF();
                        Platform.runLater(() ->
                            textArea.appendText(msg + "\n")
                        );
                    }
                } catch (IOException e) {
                    Platform.runLater(() ->
                        textArea.appendText("\n Disconnected from server \n")
                    );
                }
            }).start();
            
        } catch (IOException e) {
            Platform.runLater(() ->
                textArea.appendText("Could not connect to server\n")
            );
            e.printStackTrace();
        }
    }
    
    @FXML
    void send() {
        try {
            String msg = textField.getText().trim();
            
            if (!msg.isEmpty()) {
                // Send message to server
                dos.writeUTF(msg);
                dos.flush();
                textField.clear();
                textField.requestFocus();
            }
        } catch (IOException e) {
            Platform.runLater(() ->
                textArea.appendText("Error sending message\n")
            );
            textField.clear();
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleKeyPress(KeyEvent event) {
        // Send message when Enter is pressed
        if (event.getCode() == KeyCode.ENTER) {
            send();
            event.consume();
        }
    }
    public void cleanup() {
        try {
            if (dis != null) dis.close();
            if (dos != null) dos.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}