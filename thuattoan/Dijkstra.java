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
    

    public void init() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("D:\\Hoc ki 5\\Pbl4\\server\\input\\DIJKSTRA.txt"));

        n = scanner.nextInt();

        System.out.println("So dinh: " + n);

        
        //Đọc ma trận trọng số 
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                Matrix[i][j] = scanner.nextInt();
                if (Matrix[i][j] == 0) {
                    Matrix[i][j] = VOCUNG;
                }
            }
        }
        s = scanner.nextInt();
        t = scanner.nextInt();

        scanner.close();
    }
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
    public void OutPut(){
        // ghi dữ liệu ra file output.txt
        // sau đó server sẽ gửi dữ liệu đi
        try{
             FileWriter fileOutput = new FileWriter("D:\\Hoc ki 5\\Pbl4\\server\\output\\output.txt");
             BufferedWriter bw = new BufferedWriter(fileOutput);
             bw.write(""+ this.d[t]);
             bw.newLine();
             int i = this.truoc[t];
             bw.write(t+" ");
             while(i != this.s){
                 bw.write(""+ i + " ");
                 i = truoc[i];
             }
             bw.write(s+" "); 
             bw.close();
             
             
        }catch(Exception e){
            System.out.println("Loi ghi file "+ e.toString());
            
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
//    public static void main(String[] args) throws FileNotFoundException {
//        
//        Dijkstra algorithm = new Dijkstra();
//
//        algorithm.Input();
//        algorithm.dijkstra();
//        algorithm.result();
//        algorithm.OutPut();
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
