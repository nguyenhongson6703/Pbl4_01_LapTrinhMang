/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverclientnhanguifile;

import java.awt.JobAttributes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author admin
 */
// client sẽ nhập vào tên file rồi đọc file rồi ghi vào datapacker rồi send cho server
// client chờ và nhận file từ server rồi view lên màn hình
public class Client {
    public static final int PORT = 9000;
    public void RunClient() {
     
        try {
            // gừi file lên cho server
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Scanner scn = new Scanner(System.in);
            //System.out.println("Nhap vao ten file: ");
            String nameFile = "input.txt";
           //nameFile = scn.nextLine();
            String source = "D:\\Hoc ki 5\\Pbl4\\client\\input\\";
            nameFile = source + nameFile;
            File fileIn = new File(nameFile);
            FileInputStream fis = new FileInputStream(fileIn);
            int lengFile = (int)fileIn.length();
            byte [] outputByte = new byte[lengFile];
            fis.read(outputByte);
            DatagramPacket packetOut = new DatagramPacket(outputByte, outputByte.length, serverAddress, PORT);
            socket.send(packetOut);
            
            // Nhận lại file từ server 
            String outputFile = "D:\\Hoc ki 5\\Pbl4\\client\\output\\output.txt";
            File fileOut = new File(outputFile);
            FileOutputStream fos = new FileOutputStream(fileOut);
            byte []inputByte = new byte[60000];
            DatagramPacket packetIn = new DatagramPacket(inputByte, inputByte.length);
            socket.receive(packetIn);
            fos.write(packetIn.getData(), 0, packetIn.getLength());
            System.out.println("Luu du lieu thanh cong");
            
          // đóng các luồng lại 
          fos.close();
          fis.close();
          
            
            
            
        } catch (Exception e) {
            System.out.println("Loi clien "+ e.toString());
            
        }
       
        
        
        
    
    }
    public static void main(String[] args) {
        Client client = new Client();
        client.RunClient();
    }
    
}
