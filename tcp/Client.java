/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcp;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author admin
 */
public class Client {
    private static final int port = 9999;
    
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", port);
            // mở luồng ghi dữ liệu
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            // mở file đê đọc dữ liệu
            String path = "D:\\Hoc ki 5\\Pbl4\\truyenfile\\client\\input.txt";
            File file = new File(path);
           
            byte[] fileBytes = Files.readAllBytes(Paths.get(path));
                    
            dos.writeInt(fileBytes.length);
	    dos.write(fileBytes);  // Sau đó gửi nội dung file
           
            dos.flush();
            //System.out.println(fis.readAllBytes().toString());
            clientSocket.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
