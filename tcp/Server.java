/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import thuattoan.Dijkstra;

/**
 *
 * @author admin
 */
public class Server {
    private static final int port = 9999;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket clientSocket = serverSocket.accept();
                ProcessHandle luong = new ProcessHandle(clientSocket);
                luong.start();
                //clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi la: "+e.toString());
        }

        
        
    }
 

}

    
