// Karan Kumar
//
// RK1LBI
//
// SnakeGame
//
// 2019/01/14 07:25:38
//
// This solution was submitted and prepared by Karan Kumar, RK1LBI for the
// SnakeGame assignment of the Practical software engineering I. course.
//
// I declare that this solution is my own work.
//
// I have not copied or used third party solutions.
//
// I have not passed my solution to my classmates, neither  made it public.
//
// Students’ regulation of Eötvös Loránd University (ELTE Regulations
// Vol. II. 74/C. § ) states that as long as a student presents another
// student’s work - or at least the significant part of it - as his/her own
// performance, it will count as a disciplinary fault. The most serious
// consequence of a disciplinary fault can be dismissal of the student from
// the University.

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Karan Kumar
 */
public class SnakeGameGUI {
    
    private JFrame frame;
    private GameEngine gameArea;
   

    public SnakeGameGUI() {
        frame = new JFrame("Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenuItem restartMenuItem = new JMenuItem("Restart");
        gameMenu.add(restartMenuItem);
        restartMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                gameArea.restart();
            }
        });
        
        JMenuItem highscoreMenuItem = new JMenuItem("Highscores");
        gameMenu.add(highscoreMenuItem);
                highscoreMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame= new JFrame();
                DefaultListModel<String> l1 = new DefaultListModel<>();                         
                try{
                HighScores highScores=new HighScores(10);
                   if(highScores.siz>=10)
                       {
            
                        for(int i=0;i<10;i++)
                            {
                                l1.addElement(highScores.getHighScores().get(i).toString());
                                //System.out.println(highScores.getHighScores().get(i).toString());
                            }
                        }
                     else
                        {
                            for(int i=0;i<highScores.siz;i++)
                                 {
                                    l1.addElement(highScores.getHighScores().get(i).toString());
                                    //System.out.println(highScores.getHighScores().get(i).toString());
                                 }
                        }
            
                    }
                catch (SQLException exl)
                    {
                        Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE,null,exl);
                    }
                
                
                
                JList<String> list = new JList<>(l1);  
                list.setBounds(100,100, 75,75);  
                frame.add(list);  
                frame.setSize(400,400);                 
                frame.setVisible(true);
            }
        });
        
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    
        
        gameArea = new GameEngine();
        frame.getContentPane().add(gameArea);
        
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
        
    
}
