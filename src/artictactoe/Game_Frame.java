/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artictactoe;
import java.sql.*;
import java.util.Random;


/**
 *
 * @author ATIF ADIB
 */
public class Game_Frame extends javax.swing.JFrame {
    public static int last_per;
    public static int count;
    public static int moveno=0;
    public static int players=0;
    public static Connection con=null;
    public static String query1="select * from ";
    public static String query2="insert into table ";
    public static String query3="(move_no,x,y,win,player) values ";
    public static String url="jdbc:mysql:///test";
    public static String username="root";
    public static String password="atifadib251194";
    public static String driver="com.mysql.jdbc.Driver";
    public static boolean turn=false;
    public static int[][] matrix= new int[3][3];
    public static int move_x;
    public static int move_y;
    public static int last_x;
    public static int last_y;
    public void filldatabase(int moveno,int x,int y,Boolean turn){
        int play;
        
        if(turn){
           play=1;
       }
        else {
            play=2;
        }
        
        String query="insert into game (slno,move_no,x,y,player,percentage)"
        + " values (?, ?, ?, ?, ?,?)";

       try{
           int percentage;
           Random ra=new Random();
           percentage=0+ra.nextInt(100);
           if(percentage+moveno<100)percentage+=moveno;
           else percentage-=moveno;
           Class.forName(driver).newInstance();
           con=DriverManager.getConnection(url,username,password);
          String q="Select count(*) from game";
          Statement st=con.createStatement();
           ResultSet r=st.executeQuery(q);
           while(r.next()){
           count=r.getInt(1);}
           
             PreparedStatement preparedStmt = con.prepareStatement(query);
             preparedStmt.setInt(1,count+1);
           preparedStmt.setInt (2,moveno);
           preparedStmt.setInt (3, x);
             preparedStmt.setInt   (4, y);
      preparedStmt.setInt    (5, play);
      preparedStmt.setInt    (6, percentage);
          preparedStmt.execute();
          con.close();
       }
       catch(SQLException s){
           System.out.println("Error is here in fill");
       }
       catch(Exception e){
           System.out.println("Error is here in fill");
       }
    }
    
    public void initiate(int[][] matrix){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                matrix[i][j]=0;
            }
        }  
    }
    public void set_by_database(int[][] matrix,int last_x,int last_y){
        int x,y;
        Boolean found=false;
        //search for x and y in database//
        {
               try{
                   Class.forName(driver).newInstance();
           con=DriverManager.getConnection(url,username,password);
                String xs=Integer.toString(last_x);
                String ys=Integer.toString(last_y);
                String find_move="select slno,percentage from game where "+"x="+xs+" AND "+"y="+ys+";";
                String get_move="select x,y,percentage from game where slno=";
                Statement newstmt=con.createStatement();
                ResultSet newset=newstmt.executeQuery(find_move);
                while(newset.next()){
                    int nextrow=newset.getInt(1)+1;
                    last_per=newset.getInt(2);
                    String row_no=Integer.toString(nextrow);
                    Statement this_stmt=con.createStatement();
                    ResultSet f=this_stmt.executeQuery(get_move+row_no+";");
                    while(f.next()){
                        int a=f.getInt(1);
                        int b=f.getInt(2);
                        int p=f.getInt(3);
                        System.out.println(a+"<>"+b);
                        if(matrix[a][b]!=1 && matrix[a][b]!=2){
                            x=a;
                            y=b;
        if(x==0 && y==0){
            jButton1.doClick();
        }
        if(x==0 && y==1){
            jButton3.doClick();
        }
        if(x==0 && y==2){
            jButton2.doClick();
        }
        if(x==1 && y==0){
            jButton7.doClick();
        }
        if(x==1 && y==1){
            jButton5.doClick();
        }
        if(x==1 && y==2){
            jButton4.doClick();
        }
        if(x==2 && y==0){
            jButton6.doClick();
        }
        if(x==2 && y==1){
            jButton8.doClick();
        }
        if(x==2 && y==2){
            jButton9.doClick();
        }
                            
                            found=true;
                            break;
                        }
                    }
                    if(found){
                        break;
                    }
                }
                con.close();
               }
               catch(SQLException s){
                   System.out.println("ERROR IN FINDING");
               }
               catch(Exception e){
                   System.out.println("ERROR IN FINDING");
               }
        }
        
        
    }
    public void set(int[][] matrix,int move_x,int move_y){
        
        
        if(turn==false){
            matrix[move_x][move_y]=1;
        }
        else{
            matrix[move_x][move_y]=2;
        }
        int win;
        win=check(matrix);
        if(win==1){
            Artictactoe obj=new Artictactoe();
            obj.start3();
        }
        else if(win==2){
            Artictactoe obj=new Artictactoe();
            obj.start5();
        }
        else{
            //do nothing//
            if(moveno==9){
                Artictactoe thisobject=new Artictactoe();
                thisobject.start_tie();
            }
        }
    }
    public int check(int[][] matrix){
        
        //check straights//
        boolean winflag=false;
        int player=0;
        for(int i=0;i<3;i++){
            if(matrix[0][i]==matrix[1][i] && matrix[1][i]==matrix[2][i] && winflag==false && matrix[0][i]!=0){
                winflag=true;
                player=matrix[0][i];
                break;
            }
        }
        for(int i=0;i<3;i++){
            if(matrix[i][0]==matrix[i][1] && matrix[i][1]==matrix[i][2] && winflag==false && matrix[i][0]!=0){
                winflag=true;
                player=matrix[i][0];
                break;
            }
        }
        //check daigonals//
        
            if(matrix[0][0]==matrix[1][1] && matrix[1][1]==matrix[2][2] && winflag==false && matrix[0][0]!=0){
                winflag=true;
                player=matrix[1][1];
            }
            if(matrix[0][2]==matrix[1][1] && matrix[1][1]==matrix[2][0] && winflag==false && matrix[1][1]!=0){
                winflag=true;
                player=matrix[1][1];
            }
        return player;  
    }
    
   /**
     * Creates new form Game_Frame
     */
    public Game_Frame() { 
       initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lb1.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("PLAYER 1");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("PLAYER 2");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("SCORE P1");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("SCORE P2");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton1.setIconTextGap(0);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/artictactoe/download.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jButton10.setText("EXIT");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jButton11.setText("START");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 61, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10)
                .addGap(87, 87, 87))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       move_x=0;move_y=0;
       moveno++;
       set(matrix,move_x,move_y);
        if(turn==false)
       {jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
        turn=true;
         if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
         }
         else{
             filldatabase(moveno,move_x,move_y,turn);
         }
       }
       else
       {
           
            jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
            turn=false;
            if(players==1){
             //database work
             
         }
         else{
             filldatabase(moveno,move_x,move_y,turn);
         }
       }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       move_x=1;move_y=2;
       moveno++;
       set(matrix,move_x,move_y);
        if(turn==false)
       {jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
        turn=true;
        if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
             
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       }
       else
       {
           turn=false;
            jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
          if(players==1){
             //database work
             
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        System.exit(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        move_x=0;move_y=1;
        moveno++;
        set(matrix,move_x,move_y);
        if(turn==false)
        {jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
        turn= true;
        if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
        }
        else
       {
           turn=false;
            jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
           if(players==1){
             //database work
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        move_x=0;move_y=2;
        moveno++;
        set(matrix,move_x,move_y);
        if(turn==false)
          {jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
       turn=true;
           if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn); 
           }
          }else
       {
           turn=false;
            jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
             if(players==1){
             //database work
         }
         else{
             // fill into database
               filldatabase(moveno,move_x,move_y,turn);
             }
       }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        move_x=1;move_y=0;
        moveno++;
        set(matrix,move_x,move_y);
        if(turn==false)
          {jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
           turn=true;
           if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
           
          }else
       {
           turn=false;
            jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
            if(players==1){
             //database work
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        move_x=1;move_y=1;
        moveno++;
        set(matrix,move_x,move_y);
        // TODO add your handling code here:
          if(turn==false)
          {jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
          turn=true;
          if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
          }
          else
       {
           turn=false;
            jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
            if(players==1){
             //database work
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        move_x=2;move_y=0;
        moveno++;
        set(matrix,move_x,move_y);
        // TODO add your handling code here:
          if(turn==false)
          {jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
          turn=true;
          if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
          
          }else
       {
           turn=false;
            jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
          if(players==1){
             //database work
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        move_x=2;move_y=1;
        moveno++;
        set(matrix,move_x,move_y);
        // TODO add your handling code here:
          if(turn==false)
          {jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
       turn=true;
       if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
          }else
       {
           turn=false;
            jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
           if(players==1){
             //database work
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        move_x=2;move_y=2;
        moveno++;
        set(matrix,move_x,move_y);
        // TODO add your handling code here:
          if(turn==false)
          {jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("x.png")));
       turn=true;
       if(players==1){
             //database work
             set_by_database(matrix,move_x,move_y);
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       
          }else
       {
           turn=false;
            jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("o.png")));
             if(players==1){
             //database work
         }
         else{
             // fill into database
             filldatabase(moveno,move_x,move_y,turn);
         }
       }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
      Artictactoe obj=new Artictactoe();
      initiate(matrix);
      moveno=0;
      players=obj.no_of_players;
      if(obj.no_of_players==1){
          lb1.setText("SINGLE PLAYER");
      }
      if(obj.no_of_players==2){
          lb1.setText("MULTIPLAYER");
      }
    }//GEN-LAST:event_jButton11ActionPerformed

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
            java.util.logging.Logger.getLogger(Game_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Game_Frame().setVisible(true);
                
            }
            
        });
                
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lb1;
    // End of variables declaration//GEN-END:variables
}
