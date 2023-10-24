/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverclientnhanguifile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import thuattoan.Dijkstra;

/**
 *
 * @author admin
 */
// luôn chờ đợi một lệnh gửi file từ client 
// nhận dữ liệu từ datapacket rồi lưu vào file 
// xử lí dữ liệu rồi in ra file sau đó gửi file đó cho client

public class Server {
    public static final int  PORT = 9000;
    public void RunServer() {
        
        try {
            DatagramSocket socket = new  DatagramSocket(PORT);
            byte []inputByte = new byte[60000];
            while (true) {
                
                // nhận file từ client
                DatagramPacket inputPack = new DatagramPacket(inputByte, inputByte.length);
                socket.receive(inputPack);
                String NameFileIn = "D:\\Hoc ki 5\\Pbl4\\server\\input\\input.txt";
                File FileIn = new File(NameFileIn);
                FileOutputStream fos = new FileOutputStream(FileIn);
                fos.write(inputPack.getData(), 0, inputPack.getLength());
                
                System.out.println("Nhan file tu client thanh cong");
                // xử lí file cho chương trình rồi ghi vào file output.txt
                XuLiDuLieu();
                // gửi lại file cho client 
                File FileOut = new File("D:\\Hoc ki 5\\Pbl4\\server\\output\\output.txt");
                int length = (int)FileOut.length();
                byte []outputByte = new byte[length];
                FileInputStream fis = new FileInputStream(FileOut);
                fis.read(outputByte);
                DatagramPacket outputPack = new DatagramPacket(outputByte  , outputByte.length, inputPack.getAddress(), inputPack.getPort());
                socket.send(outputPack);
                System.out.println("Gui file thanh cong");
                
                // đóng các luồng ghi và đọc dữ liệu lại
                fos.close();
                fis.close();
                
                
                
            }
            
            
        } catch (Exception e) {
            
        }
    }
    private static void XuLiDuLieu(){
        Dijkstra algorithm = new Dijkstra();
        algorithm.Input();
        algorithm.dijkstra();
        algorithm.result();
        algorithm.OutPut();
    }
    public static void main(String[] args) {
        Server server = new Server();
        server.RunServer();
    }
    
    
}
