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
public class xuli implements Runnable{

    private Socket clientSocket;
    byte[] result;
    public xuli(Socket clienSocket){
        this.clientSocket = clientSocket;
        
    }

    @Override
    public void run() {
           try {
                    DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                    int fileSize = dis.readInt();
	            byte[] data = new byte[fileSize];
	            data = dis.readNBytes(fileSize);
	            
	            Dijkstra algorithm = new Dijkstra();
	            algorithm.inputFromByteArray(data);
	            algorithm.dijkstra();
	            algorithm.result();
	            result = algorithm.OutPutByte();
	            System.out.println("Xu li du lieu thanh cong");
	            
	            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
	            dos.writeInt(result.length);
	            dos.write(result);
	            dos.flush();
	            System.out.println("Gui file thanh cong");
	            clientSocket.close();
	          
			}catch (Exception ex) {
				ex.printStackTrace();
			}
    }
    
}
