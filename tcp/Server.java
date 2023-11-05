/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;
import java.io.DataInputStream;
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
                System.out.println("Chap nhan ket noi");
                // mở luồng đọc từ socket 
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                // mở luồng ghi file
                
                
                int fileSize = dis.readInt();
                //byte[] buffer = new byte[1024];
                byte[] data = new byte[fileSize];
                data = dis.readNBytes(fileSize);
                Dijkstra algorithm = new Dijkstra();
                algorithm.inputFromByteArray(data);
                algorithm.dijkstra();
                algorithm.result();
                algorithm.OutPut();

                //int byteRead;
                //int totalbyteRecieved = 0;
                
                //System.out.println("So byte nhan duoc la "+ fileSize);
                FileOutputStream fos = new FileOutputStream("D:\\Hoc ki 5\\Pbl4\\truyenfile\\server\\input.txt");
                fos.write(data, 0, data.length);
//                while((byteRead = dis.read(buffer))!= -1){
//                    totalbyteRecieved += byteRead;
//                    fos.write(buffer, 0, byteRead);
//                    System.out.println(buffer.toString());
//                    
//                    if(totalbyteRecieved > fileSize){
//                        break;
//                    }
//                    
//                }
                System.out.println("Gui file thanh cong");
                fos.close();
                clientSocket.close();
              
                //fos.close();
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi la: "+e.toString());
        }

        
        
    }

}
    //                while((byteRead = dis.read(buffer)) != -1){
//                    totalbyteRecieved += byteRead;
//                    fos.write(buffer, 0, byteRead);
//                    if(totalbyteRecieved >= fileSize){
//                        break;
//                    }
//                    
//                        for (int i = 0; i < buffer.length; i++) {
//                            buffer[i] = 0;
//                        }
//                }
    
