/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatroomserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author ANTER
 */
public class ChatroomServer {
    ServerSocket serverSocket;
    public ChatroomServer(){
        try {
            serverSocket = new ServerSocket(5005);
            while(true){
                Socket s = serverSocket.accept();
                System.out.println("Client Connected");
                new ChatHandler(s);
            }
        } catch (IOException ex) {
            System.getLogger(ChatroomServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    class ChatHandler extends Thread{
        DataInputStream dis;
        DataOutputStream dos;
        static Vector<ChatHandler>clientsVector = new Vector<ChatHandler>();
        public ChatHandler(Socket cs){
            try {
                dis = new DataInputStream(cs.getInputStream()); 
                dos = new DataOutputStream(cs.getOutputStream());
                ChatHandler.clientsVector.add(this);
                start();
            } catch (IOException ex) {
                System.getLogger(ChatroomServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
        public void run (){
            while(true){
                try {
                    String str = dis.readUTF();
                     sendMessageToAll(str);
                } catch (IOException ex) {
                    System.getLogger(ChatroomServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
               
            }
        }
        void sendMessageToAll(String msg){
            for(int i=0;i<clientsVector.size();i++){
                try {
                    clientsVector.get(i).dos.writeUTF(msg);
                } catch (IOException ex) {
                    System.getLogger(ChatroomServer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new ChatroomServer();
    }
}
