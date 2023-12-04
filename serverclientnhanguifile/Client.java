/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverclientnhanguifile;

import java.awt.JobAttributes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author admin
 */
// client sẽ nhập vào tên file rồi đọc file rồi ghi vào datapacker rồi send cho server
// client chờ và nhận file từ server rồi view lên màn hình
public class Client {
    public static final int PORT = 9999;
    private Socket clientSocket;
    private DataOutputStream dos;
    private DataInputStream input;
    private FileOutputStream fos;
    
    public Client(String ip , int port){
        try {
            this.clientSocket = new Socket(ip, port);
        } catch (Exception e) {
        }
    }
    
    public void RunClient() throws Exception{
     
        

            //this.clientSocket = new Socket("localhost", PORT);
            // mở luồng ghi dữ liệu
            this.dos = new DataOutputStream(clientSocket.getOutputStream());
            // mở file đê đọc dữ liệu
            String path = "D:\\Hoc ki 5\\Pbl4\\client\\input\\input.txt";
            File file = new File(path);
           
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));
                    
            this.dos.writeInt(fileBytes.length);
	    this.dos.write(fileBytes);  // Sau đó gửi nội dung file
           
            this.dos.flush();
            System.out.println("Gui file thanh cong");
            
            // đọc dữ liệu gửi về từ server 
            int fileSize;
            this.input = new DataInputStream(clientSocket.getInputStream());
            fileSize = this.input.readInt();
            if(fileSize == 0){
                System.out.println("Server thực hiện thất bại");
                this.input.close();
                this.dos.close();
                throw new Exception("Lỗi thực hiện xử lí server");
            }else{
                byte[] data= new byte[fileSize];
                data = this.input.readNBytes(fileSize);
                this.fos = new FileOutputStream("D:\\Hoc ki 5\\Pbl4\\client\\output\\output.txt");
                this.fos.write(data  , 0, fileSize);
                this.fos.close();
            }
            
            
       
        
        
        
    
    }
   
    public void shutDownClient()throws Exception{
        this.dos = new DataOutputStream(clientSocket.getOutputStream());
        this.dos.writeInt(0);
        this.dos.close();
        this.input.close();
        this.fos.close();
        this.clientSocket.close();
    }
   
    
    
}
