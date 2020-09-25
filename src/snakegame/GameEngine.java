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

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

/**
 *
 * @author Karan Kumar
 */
public class GameEngine extends JPanel {
    
     private final int FPS = 10;
    private final int SNAKE_WIDTH = 15;
    private final int SNAKE_HEIGHT = 15;
    private String direction = "UP";
    private int score=0;
    private String name;
    HighScores highScores;
    

    
    

    private boolean paused = false;
    private final Image background;
    private int levelNum = 0;
    private Level level;
    Image foodImage = new ImageIcon("data/images/food.png").getImage();
    private Food food = new Food(230,500,15,15,foodImage);
    private int snake_lenght=2;
    Snake snake;
    
    private Timer newFrameTimer;

    public GameEngine() {
        super();
        background = new ImageIcon("data/images/background.jpg").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("A"), "pressed left");
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!"RIGHT".equals(direction))
                {
                    direction="LEFT";
                }
                    
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("D"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!"LEFT".equals(direction))
                {
                    direction="RIGHT";
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("S"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!"UP".equals(direction))
                {
                    direction="DOWN";
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("W"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!"DOWN".equals(direction))
                {
                    direction="UP";
                }
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
        
        

    }
        public void generateFood(Food f){
		
		Random r = new Random();
		int rx = r.nextInt(750);
		int ry = r.nextInt(550);
                f.setX(rx);
                f.setY(ry);
	}
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
    public void endGame() 
    {
       name = JOptionPane.showInputDialog(this, "Name: "+ "Score " +score);
       
       
     try {
              HighScores highScores = new HighScores(10);
            highScores.putHighScore(name, score);
           System.out.println(highScores.getHighScores());
        } catch (SQLException ex1) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex1);
        }
       
    }
    
        
    public void restart() {
        try {
            level = new Level("data/levels/level0" + levelNum + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        snake = initSnake();
        score=0;
    }
    private Snake initSnake() {                                 
        snake = new Snake();
        for (int i = 0; i < 2; i++) {
            snake.addTail(new SnakeBody(300-(15*i) , 200));
        }
        
        return snake;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 800, 600, null);
        level.draw(grphcs);
        for(int i=0;i<snake.snakebody.size();i++)
        {
            snake.snakebody.get(i).draw(grphcs);
        }
        
        food.draw(grphcs);
    }

    class NewFrameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                SnakeBody last = snake.move(direction);
                for(Brick b : level.bricks)
                {
                   while(food.collides(b))              //food colides with bricks
                        generateFood(food);             //create new food
                }
                
                if(food.collides(snake.getHead()))
                {
                    generateFood(food);                 //food colides with snake
                    score++;                            //increase score
                    snake.addTail(last);                //add food in tail
                }   
            }
            if (level.isOver(snake)) {
                levelNum = (levelNum+1) % 6;
                System.out.println(score);
                endGame();
                restart();
            }
            repaint();
        }

    }
    
}
