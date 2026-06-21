/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatroomclienttest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ANTER
 */
/*
public class ChatroomClienttest {
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;
    public ChatroomClienttest(){
        try {
            s = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Hello Server!");
            dos.flush();
            String replyMsg = dis.readUTF();
            System.out.println("Server says: " + replyMsg);
        } catch (IOException ex) {
            System.getLogger(ChatroomClienttest.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        finally{
            try {
                dos.close();
                s.close();
            } catch (IOException ex) {
                System.getLogger(ChatroomClienttest.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
    }
    public static void main(String[] args) {
        new ChatroomClienttest();
    }
}
*/