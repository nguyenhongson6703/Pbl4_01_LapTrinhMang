/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
       
        Server server = new Server();
     
        server.RunServerTcp(9999);
        
    }
    public void RunServerTcp(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket clientSocket = serverSocket.accept();
                 System.out.println("Chap nhan ket noi");
                 Thread thServer = new Thread (new handleProcess(clientSocket));
                 thServer.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi la: "+e.toString());
        }
        
    }
    private static class handleProcess implements Runnable{
        private Socket clientSocket;
    	byte[] result;
        public handleProcess(Socket client){
            this.clientSocket = client;
        }
        @Override
        public void run() {
            try {
                    DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                    while(true){
                        int fileSize = dis.readInt();
                        
                        if(fileSize > 0){
                            byte[] data = new byte[fileSize];
                            data = dis.readNBytes(fileSize);
                            try {
                                Dijkstra algorithm = new Dijkstra();
                                algorithm.inputFromByteArray(data);
                                algorithm.dijkstra();
                                algorithm.result();
                                result = algorithm.OutPutByte();
                                System.out.println("Xu li du lieu thanh cong");


                                dos.writeInt(result.length);
                                dos.write(result);
                                dos.flush();
                                System.out.println("Gui file thanh cong");
                            } catch (Exception e) {
                                System.out.println("Xu li du lieu that bai");
                                dos.writeInt(0);
                                dos.flush();
                            }
                            
                        }else{
                            dis.close();
                            dos.close();
                            this.clientSocket.close();
                            break;
                            
                        }
                        
                    }
                    
	            
	          
                }catch (Exception ex) {
                    ex.printStackTrace();
		}
        }
        
    }
    
 

}

    
