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

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Karan Kumar
 */
public class SnakeBody extends Sprite{
    
      public static Image snakeimage = new ImageIcon("data/images/snake.png").getImage();
    
    public SnakeBody(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }
    public SnakeBody(int x, int y) {
        super(x, y, 15, 15, snakeimage); 
    }
    
}
