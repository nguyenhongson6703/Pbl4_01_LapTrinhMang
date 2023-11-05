/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thuattoan;
import java.util.*;
import java.io.*;
import view.GiaoDien;

/**
 *
 * @author admin
 */
public class Dijkstra {
    
    private static final int MAX = 50;
    private static final int VOCUNG = 10000000;

    private int n; // số đỉnh ma trận 
    private int s; // đỉnh bắt đầu
    private int t; // đỉnh kết thúc

    private int[] truoc = new int[MAX]; // lưu các đỉnh trước của đường đi
    private  int[] d = new int[MAX]; // lưu khoảng cách
    private int[][] Matrix = new int[MAX][MAX]; // lưu ma trận trọng số
    private boolean[] chuaxet = new boolean[MAX]; // xét đỉnh đã được xét hay chưa
    

    
    public void Input(){
        try {
            FileReader inputFile = new FileReader("D:\\Hoc ki 5\\Pbl4\\server\\input\\input.txt");
            BufferedReader br = new BufferedReader(inputFile);
            // đọc số đỉnh 
            n = Integer.parseInt(br.readLine());
            String line;
            
            for(int i = 1; i<= n;i++){
                line = br.readLine();
                String[] value = line.split(" ");
                for(int j = 1 ;j <= n;j++){
                    Matrix[i][j] = Integer.parseInt(value[j-1]);
                    if(Matrix[i][j] == 0){
                        Matrix[i][j] = VOCUNG;
                        
                    }
                    
                }
            }
            s = Integer.parseInt(br.readLine());
            t = Integer.parseInt(br.readLine());
            br.close();
                
           
            
        } catch (Exception e) {
            System.out.println("Loi doc file "+ e.toString());
        }
    }
    public void inputFromByteArray(byte[] data) {
    try {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BufferedReader br = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        
        // Đọc số đỉnh
        n = Integer.parseInt(br.readLine());
        String line;
        
        for (int i = 1; i <= n; i++) {
            line = br.readLine();
            String[] values = line.split(" ");
            for (int j = 1; j <= n; j++) {
                Matrix[i][j] = Integer.parseInt(values[j - 1]);
                if (Matrix[i][j] == 0) {
                    Matrix[i][j] = VOCUNG;
                }
            }
        }
        
        s = Integer.parseInt(br.readLine());
        t = Integer.parseInt(br.readLine());
        br.close();
    } catch (Exception e) {
        System.out.println("Lỗi đọc dữ liệu từ mảng byte: " + e.toString());
    }
    }
    public void OutPut(){
        int[] dataPath = new int[MAX];
        int j = 0;
        // ghi dữ liệu ra file output.txt
        // sau đó server sẽ gửi dữ liệu đi
        try{
             String filename = "D:\\Hoc ki 5\\Pbl4\\truyenfile\\server\\output.txt";
             BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false));
             bw.write(""+ this.d[t]);
             bw.newLine();
             int i = this.truoc[t];
             dataPath[j++] = t;
             //bw.write(t+" ");
             while(i != this.s){
                 //bw.write(""+ i + " ");
                 dataPath[j++] = i;
                 i = truoc[i];
             }
             dataPath[j++] = s;
             bw.write(dataPath[j-1]+ " ");
             for(int k = j -2;k >= 1; k--){
                 bw.write(dataPath[k] + " ");
                 
             }
             bw.write(dataPath[0]+ "");
             bw.newLine();
             
             bw.close();
             
             
        }catch(Exception e){
            System.out.println("Loi ghi file "+ e.toString());
            
        }
        for(int i = n; i>=1 ;i--){
            if(i != s && i!= t){
                GhiCacDinhConLai(i);
            }
        }
       
    }

    public void result() {
        System.out.println("Duong di ngan nhat tu " + (char)(s + 'A' - 1) + " den " + (char)(t + 'A' - 1) + " la");

        System.out.print((char)(t + 'A' - 1) + "<=");

        int i = truoc[t];
        while (i != s) {
            System.out.print((char)(i + 'A' - 1) + "<=");
            i = truoc[i];
        }

        System.out.println((char)(s + 'A' - 1));
        System.out.println("Do dai duong di la: " + d[t]);
    }

    public void dijkstra() {
        int u, minp;
        
        for (int v = 1; v <= n; v++) {
            d[v] = Matrix[s][v];
            truoc[v] = s;
            chuaxet[v] = false;
        }

        truoc[s] = 0;
        d[s] = 0;
        chuaxet[s] = true;

        while (!chuaxet[t]) {
            minp = VOCUNG;
            u = -1;  // Initialize u
            // Tìm đỉnh u sao cho d[u] là ngắn nhất
            for (int v = 1; v <= n; v++) {
                if (!chuaxet[v] && minp > d[v]) {
                    u = v;
                    minp = d[v];
                }
            }
            
            if (u == -1) break;  // No path exists
            
            chuaxet[u] = true;
            //so sanh d[u] + matrix[u][v] voi d[v]
            if (!chuaxet[t]) {
                for (int v = 1; v <= n; v++) {
                    if (!chuaxet[v] && d[u] + Matrix[u][v] < d[v]) {
                        d[v] = d[u] + Matrix[u][v];
                        truoc[v] = u;
                    }
                }
            }
        }
    }
    public static void setArrayToZero(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }
    public void GhiCacDinhConLai(int t){
       
        // reset lại các giá trị cần có bao gồm 
        setArrayToZero(d);
        setArrayToZero(truoc);
        boolean[] chuaxet = new boolean[MAX];
         // thực thi thuật toán dijitra
        int u, minp;
        
        for (int v = 1; v <= n; v++) {
            d[v] = Matrix[s][v];
            truoc[v] = s;
            chuaxet[v] = false;
        }

        truoc[s] = 0;
        d[s] = 0;
        chuaxet[s] = true;

        while (!chuaxet[t]) {
            minp = VOCUNG;
            u = -1;  // Initialize u
            // Tìm đỉnh u sao cho d[u] là ngắn nhất
            for (int v = 1; v <= n; v++) {
                if (!chuaxet[v] && minp > d[v]) {
                    u = v;
                    minp = d[v];
                }
            }
            
            if (u == -1) break;  // No path exists
            
            chuaxet[u] = true;
            //so sanh d[u] + matrix[u][v] voi d[v]
            if (!chuaxet[t]) {
                for (int v = 1; v <= n; v++) {
                    if (!chuaxet[v] && d[u] + Matrix[u][v] < d[v]) {
                        d[v] = d[u] + Matrix[u][v];
                        truoc[v] = u;
                    }
                }
            }
        }  
         // ghi dữ liệu đỉnh nguồn tới tất cả các đỉnh còn lại vào file 
        int[] dataPath = new int[MAX];
        int j = 0;
        // ghi dữ liệu ra file output.txt
        // sau đó server sẽ gửi dữ liệu đi
        try{
             String fileName = "D:\\Hoc ki 5\\Pbl4\\truyenfile\\server\\output.txt";
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
             bw.write(t+ ": ");
             int i = this.truoc[t];
             dataPath[j++] = t;
             while(i != this.s){
                 
                 dataPath[j++] = i;
                 i = truoc[i];
             }
             dataPath[j++] = s;
             bw.write(dataPath[j-1]+ " ");
             for(int k = j -2;k >= 1; k--){
                 bw.write(dataPath[k] + " ");
                 
             }
             bw.write(dataPath[0]+ "");
             bw.newLine();
             
             bw.close();
             
             
        }catch(Exception e){
            System.out.println("Loi ghi file "+ e.toString());
            
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        
        Dijkstra algorithm = new Dijkstra();

        algorithm.Input();
        algorithm.dijkstra();
        algorithm.result();
        algorithm.OutPut();
    }
//          //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        new GiaoDien().setVisible(true);
//    }

    
}
