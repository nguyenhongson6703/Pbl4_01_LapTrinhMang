/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import serverclientnhanguifile.Client;
import java.util.Random;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import thuattoan.ClientModule;



/**
 *
 * @author admin
 */
public class GiaoDien extends javax.swing.JFrame {
    private static final float  VOCUNG = 10000000;
    private Client client;
    myPanel dothi;
    private static final int MAX = 50;
    static boolean check = false;
    public String inputPath = "";

    /**
     * Creates new form GiaoDien
     */
    public GiaoDien() {
        
        initComponents();
        pnlPaint.setLayout(new BorderLayout());
        pnlPaint.setPreferredSize(new Dimension(347, 182));
        dothi = new myPanel();
        pnlPaint.add(dothi,BorderLayout.CENTER);
       
    }
    public  void ConnectServer(String ipAddress, int port){
        try {
             client = new Client(ipAddress, port);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Kết nối đã thất bại hãy kiểm tra lại địa chỉ và cổng của server");
        }
    }
    private class myPanel extends JPanel {

        ArrayList<Point> points;
            ClientModule d = new ClientModule();
            float [][] Matrix = d.init(inputPath);
            int n = d.sodinh();
            
            Random generator = new Random();
            public myPanel() {
                    points = new ArrayList<>();
                    int x,y;
                    for (int i=0; i<n+1;i++ ) {
                            if (i==0) {
                                    x = 50;
                                    y= 75;
                            }else {
                                    x = 50 + i*30;
                                    if ( i%2 == 0) y =120 + generator.nextInt(25) + 1;
                                    else y = 25 + generator.nextInt(25) + 1;
                            }
                            points.add(new Point(x,y));
                    }
            }

            @Override
            protected void paintComponent(Graphics g) {
                    String [] value = d.xu_li_duong_di();
                    int s = d.nguon();
                    int t = d.dich();
                    
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D)g;
                    float lineWidth = 2.0f;
                    g2.setStroke(new BasicStroke(lineWidth));

                    for (int i = 0; i < points.size()-1; i++) {
                        g2.setColor(Color.YELLOW);
                        Point p = points.get(i);
                        g2.fillOval(p.x - 6, p.y - 6, 12, 12);
                        if (i%2==1) {
                                g2.setColor(Color.BLACK);  // Đặt màu chữ
                                g2.drawString(Integer.toString(i+1), p.x - 3, p.y - 10);
                        }else if (i%2==0) {
                                g2.setColor(Color.BLACK);  // Đặt màu chữ
                                g2.drawString(Integer.toString(i+1), p.x - 3, p.y + 20);
                        }
                    }
                    g2.setColor(Color.RED);
                    for (int i=1; i<=n; i++) {
                            for (int j=1; j<=n; j++) {
                                    if (Matrix[i][j]!= VOCUNG) {
                                            Point p1 = points.get(i-1);
                                            Point p2 = points.get(j-1);
                                            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                                    }
                            }
                    }
                    
                    if(check==true){
                        if (value != null) {
                            for (int i=0; i< value.length - 1 ; i++) {
                                    Point p = points.get(Integer.parseInt(value[i])-1);
                                    for (int j=0; j< points.size();j++) {
                                            if (j == Integer.parseInt(value[i])) {
                                                    Point nextPoint = points.get(Integer.parseInt(value[i+1])-1);
                                                    g2.setColor(Color.BLUE);
                                                    g2.drawLine(p.x, p.y, nextPoint.x, nextPoint.y);
                                            }
                                    }
                            }

                            for (int i=0; i< value.length ; i++) {
                                    if (Integer.parseInt(value[i]) == s || Integer.parseInt(value[i]) == t) {
                                            Point p = points.get(Integer.parseInt(value[i])-1);
                                            g2.setColor(Color.GREEN);
                                            g2.fillOval(p.x - 6, p.y - 6, 12, 12);
                                    }
                                    else {
                                          Point p = points.get(Integer.parseInt(value[i])-1);
                                            g2.setColor(Color.BLUE);
                                            g2.fillOval(p.x - 6, p.y - 6, 12, 12);
                                    }
                            }
                        }
                    }
	}
    }
    public void HienThi() {
        try {
            txtDistance.setText("Khoảng cách: ");
            txtPathToEnd.setText("Đường đi: ");
            FileReader fileIn = new FileReader("D:\\Hoc ki 5\\Pbl4\\client\\output\\output.txt");
            BufferedReader br = new BufferedReader(fileIn);
            String distance = br.readLine();
            String Path = br.readLine();
            txtDistance.setText(txtDistance.getText() + distance);
            txtPathToEnd.setText(txtPathToEnd.getText() + Path);
            String line;
            StringBuilder fileContents = new StringBuilder();
            while ((line = br.readLine()) != null) {
                // Đọc từng dòng và nối nó vào chuỗi
                fileContents.append(line);
                fileContents.append("\n"); // Thêm ký tự xuống dòng sau mỗi dòng nếu cần

                // Hoặc nếu bạn không muốn thêm ký tự xuống dòng:
                // fileContents.append(line);
            }
            br.close();
            fileIn.close();
            txtPaths.setText(fileContents.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hiển thị dữ liệu");
            
        }
        
        
    }
    public void matrixhh(float[][] matrix, int n){
        System.out.println("Matrix : ");
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.println(matrix[i][j]);
            }
        }
        String fileName;
        
        if(inputPath.trim().length()>0){
            fileName = inputPath;
        }else{
            fileName = "D:\\Hoc ki 5\\Pbl4\\client\\input\\DIJITRA.txt";
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(n+"");
            writer.newLine();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    writer.write(matrix[i][j] + " ");

                    
                }
                writer.newLine(); // Xuống dòng sau mỗi hàng
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public boolean KiemTraDuLieu(){ 
        // số đỉnh ma trận
        int n;
        float[][] Matrix = new float[MAX][MAX];
        // đỉnh bắt đầu
        int s;
        // đỉnh kết thúc
        int t;
        try {
            String inputFileName;
            if(inputPath.trim().length()> 0){
                inputFileName = inputPath;
            }else{
                inputFileName = "D:\\Hoc ki 5\\Pbl4\\client\\input\\DIJITRA.txt";
            }
            FileReader inputFile = new FileReader(inputFileName);
            BufferedReader br = new BufferedReader(inputFile);
            // đọc số đỉnh 
            n = Integer.parseInt(br.readLine());
            String line;
            
            for(int i = 1; i<= n;i++){
                line = br.readLine();
                String[] value = line.split(" ");
                for(int j = 1 ;j <= n;j++){
                    Matrix[i][j] = Float.parseFloat(value[j-1]);
                    
                }
            }
            s = Integer.parseInt(txtSource.getText());
            t = Integer.parseInt(txtDestination.getText());
            br.close();
            inputFile.close();
            // kiểm tra dữ liệu của ma trận 
            //vuông, đối xứng, có đường chéo chính bằng 0 , các trọng số dương
            for(int i = 1;i<=n;i++){
                for(int j = 1;j<=n;j++){
                    
                    if(i == j){
                        if(Matrix[i][i] != 0){
                            JOptionPane.showMessageDialog(this, "Ma trận trọng số phải có đường chéo chính bằng 0");
                            return false;
                        }
                    }else{
                        if(Matrix[i][j] != Matrix[j][i]){
                            JOptionPane.showMessageDialog(this, "Ma trận trọng số phải là ma trận đối xứng");
                            return false;
                        }
                        if(Matrix[i][j] < 0){
                            JOptionPane.showMessageDialog(this, "Các trọng số trong ma trận phải dương");
                            return false;
                        }
                        
                    }
                }
            }
            if((s<1 || s>n)||(t<1||t>n)){
                JOptionPane.showMessageDialog(this, "Các đỉnh và nguồn phải lớn hơn 0 và không vượt quá số đỉnh");
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void GhiFile(int source , int destination){
        String sourceFile;
        if(inputPath.trim().length()>0){
            sourceFile = inputPath;
        }else{
            sourceFile = "D:\\Hoc ki 5\\Pbl4\\client\\input\\DIJITRA.txt";
        }
      
        String desFile = "D:\\Hoc ki 5\\Pbl4\\client\\input\\input.txt";
        try {
            FileReader FileIn = new FileReader(sourceFile);
            FileWriter FileOut = new FileWriter(desFile);
            BufferedReader reader = new BufferedReader(FileIn);
            BufferedWriter writer = new BufferedWriter(FileOut);
            
            String line;
            while((line = reader.readLine() ) != null){
                
                writer.write(line);
                writer.newLine();
            }
            writer.write(source + "");
            writer.newLine();
            writer.write(destination + "");
            reader.close();
            writer.close();
            FileIn.close();
            FileOut.close();
            JOptionPane.showMessageDialog(this, "Bạn đã lấy dữ liệu thành công");
            
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Copy file bị lỗi");
            
        }
       
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSource = new javax.swing.JTextField();
        txtDestination = new javax.swing.JTextField();
        btnSelection = new javax.swing.JButton();
        pnlPaint = new javax.swing.JPanel();
        txtPathToEnd = new javax.swing.JTextField();
        txtDistance = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnEndControl = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPaths = new javax.swing.JTextArea();
        btnHandInput = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnChooseFile = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnConnect = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn trạm trên mạng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 153));
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setLabelFor(txtSource);
        jLabel1.setText("Nguồn");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setLabelFor(txtDestination);
        jLabel2.setText("Đích");

        txtSource.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtDestination.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnSelection.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnSelection.setText("Chọn");
        btnSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtSource, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btnSelection, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSource, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtDestination))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pnlPaint.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 2));

        javax.swing.GroupLayout pnlPaintLayout = new javax.swing.GroupLayout(pnlPaint);
        pnlPaint.setLayout(pnlPaintLayout);
        pnlPaintLayout.setHorizontalGroup(
            pnlPaintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlPaintLayout.setVerticalGroup(
            pnlPaintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtPathToEnd.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPathToEnd.setText("Đường đi: ");

        txtDistance.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtDistance.setText("Khoảng cách: ");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng điều khiển", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12))); // NOI18N

        btnEndControl.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnEndControl.setText("Kết thúc");
        btnEndControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEndControlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(btnEndControl, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEndControl, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setText("Bảng chỉ đường");

        txtPaths.setColumns(20);
        txtPaths.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtPaths.setLineWrap(true);
        txtPaths.setRows(5);
        jScrollPane1.setViewportView(txtPaths);

        btnHandInput.setText("Nhập tay");
        btnHandInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHandInputActionPerformed(evt);
            }
        });

        jLabel4.setText("Nhập dữ liệu");

        btnChooseFile.setText("Chọn File");
        btnChooseFile.setActionCommand("");
        btnChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseFileActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Kết nối"));

        btnConnect.setText("Kết nối");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setBackground(java.awt.Color.white);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnHandInput, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnChooseFile)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDistance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPathToEnd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlPaint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHandInput, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(pnlPaint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPathToEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void btnSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectionActionPerformed
        // TODO add your handling code here:
        if(KiemTraDuLieu()){
                try {
                int source = Integer.parseInt(txtSource.getText());
                int destination = Integer.parseInt(txtDestination.getText());
                GhiFile(source, destination);
                client.RunClient();
                HienThi();
                check = true;
                dothi.repaint();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage());

            }
        }else{
                JOptionPane.showMessageDialog(this, "Hãy nhập đúng dữ liệu kiểu dữ liệu vào gồm đỉnh ma trận trọng số và nguồn , đích.Chú ý ma trận trọng số là ma trận dương");
        }
        
        
        
        
    }//GEN-LAST:event_btnSelectionActionPerformed

    private void btnEndControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEndControlActionPerformed
        try {
            this.client.shutDownClient();
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Không thể đóng client");
        }
        
    }//GEN-LAST:event_btnEndControlActionPerformed

    private void btnHandInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHandInputActionPerformed
            InputMatrix inputmt = new InputMatrix(this);
            inputmt.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnHandInputActionPerformed

    private void btnChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseFileActionPerformed
             JFileChooser filechooser = new JFileChooser();
             filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
             int result = filechooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION){
             String path = filechooser.getSelectedFile().getAbsolutePath();
             this.inputPath = path;
            
        }
                    // TODO add your handling code here:
    }//GEN-LAST:event_btnChooseFileActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
            ConnectionView connectionView = new ConnectionView(this);
            connectionView.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnConnectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btnChooseFile;
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnEndControl;
    private javax.swing.JButton btnHandInput;
    private javax.swing.JButton btnSelection;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JPanel pnlPaint;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JTextField txtDestination;
    private javax.swing.JTextField txtDistance;
    private javax.swing.JTextField txtPathToEnd;
    private javax.swing.JTextArea txtPaths;
    private javax.swing.JTextField txtSource;
    // End of variables declaration//GEN-END:variables

}
