/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artictactoe;

/**
 *
 * @author ATIF ADIB
 */
public class Artictactoe {
    public static int no_of_players;
    Login_Frame framelogin=new Login_Frame();
    Game_Frame framegame=new Game_Frame();
    Player_Win_Frame winframe=new Player_Win_Frame();
    Player_Win_Frame2 winframe2=new Player_Win_Frame2();
    Tie_Frame tie=new Tie_Frame();
    public void start_tie(){
        framegame.setVisible(false);
        tie.setVisible(true);
    }
    public void start1(){
        framelogin.setVisible(true);
    }
    public void start2(){
        framelogin.setVisible(false);
        framegame.setVisible(true);
    }
    public void start3(){
        framegame.setVisible(false);
        winframe.setVisible(true);
    }
    public void start4(){
        winframe.setVisible(false);
        framelogin.setVisible(true);
    }
    public void start5(){
        framegame.setVisible(false);
        winframe2.setVisible(true);
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Artictactoe obj=new Artictactoe();
        obj.start1();
    }
    
}
