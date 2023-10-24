/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thuattoan;

/**
 *
 * @author admin
 */
import java.io.BufferedReader;
import java.io.FileReader;
public class ClientModule {
    private static final int MAX = 50;
    private static final int VOCUNG = 10000000;
    
    private int s; // Start vertex.
    private int t; // End vertex.
	private int n;  // so dinh
	private int[][] Matrix = new int[MAX][MAX]; // ma tran trong so
	
	//dua vao ma tran trong so de noi cac dinh 
	public int[][] init(){
            try {
                FileReader inputFile = new FileReader("D:\\Hoc ki 5\\Pbl4\\client\\input\\DIJITRA.txt");
                BufferedReader br = new BufferedReader(inputFile);
                // đọc số đỉnh 
                n = Integer.parseInt(br.readLine());
                String line;

                for(int i = 1; i<= n;i++){
                    line = br.readLine();
                    String[] value = line.split(" ");
                    for(int j = 1;j <= n; j++){
                        Matrix[i][j] = Integer.parseInt(value[j-1]);
                        if(Matrix[i][j] == 0){
                            Matrix[i][j] = VOCUNG;  
                        }                
                    }
                }


                br.close();
            } catch (Exception e) {
                System.out.println("Loi doc file "+ e.toString());
            }
            return Matrix;
        }
	//tra ve cac dinh thuoc duong di de ve so do
	public String[] xu_li_duong_di() {
		String [] value = null; 
		try {
                    FileReader inputFile = new FileReader("D:\\Hoc ki 5\\Pbl4\\client\\output\\output.txt");
                    BufferedReader br = new BufferedReader(inputFile);
                    int kc = Integer.parseInt(br.readLine());
                    String line;
                    line = br.readLine();
                    value = line.split(" ");
                    s = Integer.parseInt(value[value.length-1]);
                    t = Integer.parseInt(value[0]);
            
		}catch (Exception e) {
			System.out.println("Loi doc file "+ e.toString());
		}
		return value;		
	}
	public int sodinh() {
		return n;
	}
	public int nguon() {
		return s;
	}
	public int dich() {
		return t;
	}
	public static void main(String[] args) {
		ClientModule clm = new ClientModule();
		clm.sodinh();
		clm.dich();
		clm.nguon();
	}
}

