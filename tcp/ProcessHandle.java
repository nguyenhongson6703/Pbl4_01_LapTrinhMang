/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import thuattoan.Dijkstra;

/**
 *
 * @author admin
 */
public class ProcessHandle extends Thread{

    private Socket clientSocket;
    public ProcessHandle(Socket clienSocket){
        this.clientSocket = clientSocket;
        
    }

    @Override
    public void run() {
       try {
                System.out.println("Chap nhan ket noi");
                // mở luồng đọc từ socket 
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                // mở luồng ghi file
                
                
                int fileSize = dis.readInt();
                //byte[] buffer = new byte[1024];
                byte[] data = new byte[fileSize];
                data = dis.readNBytes(fileSize);
                System.out.println("Nhan du lieu thanh cong");
                Dijkstra algorithm = new Dijkstra();
                algorithm.inputFromByteArray(data);
                algorithm.dijkstra();
                algorithm.result();
                //algorithm.OutPut();
                byte[] result = algorithm.OutPutByte();
                //FileOutputStream fos = new FileOutputStream("D:\\Hoc ki 5\\Pbl4\\truyenfile\\server\\result.txt");
                //fos.write(result, 0, result.length);
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
                int resultSize = result.length;
                output.writeInt(resultSize);
                output.write(result);
                output.flush();
                
                System.out.println("Gui file thanh cong");
                //fos.close();
                //clientSocket.close();
            } catch (Exception e) {
            }
    }
    
}
