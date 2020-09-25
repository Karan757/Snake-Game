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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Karan Kumar
 */
public class Level {
    
    private final int BRICK_WIDTH = 15;
    private final int BRICK_HEIGHT = 15;
    public ArrayList<Brick> bricks;

    public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }

    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        bricks = new ArrayList<>();
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int x = 0;
            for (char blockType : line.toCharArray()) {
                if (Character.isDigit(blockType)) {
                    Image image = new ImageIcon("data/images/brick0" + blockType + ".png").getImage();
                    bricks.add(new Brick(x * BRICK_WIDTH, y * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT, image));
                }
                x++;
            }
            y++;
        }
    }
    
    public boolean isOver(Snake snake) {
        boolean colide = false;
        for (Brick b : bricks) 
        {
            if (snake.snakebody.getFirst().collides(b))  //snake colides with bricks
            {
                    colide=true;
                    break;
            }
        }
        //snake colides with borders
        if(snake.getHead().getX()>800 || snake.getHead().getX()<0 || snake.getHead().getY()<0 || snake.getHead().getY()>600)
            colide = true;
        //snack colides with itself
        for(int i=3;i<snake.snakebody.size();i++)
        {
            if(snake.getHead().collides(snake.snakebody.get(i)))
                colide=true;
        }

            return colide;
    }

    public void draw(Graphics g) {
        for (Brick b : bricks) {
            b.draw(g);
        }
    }
    

    
}
