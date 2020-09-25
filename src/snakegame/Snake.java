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


import java.util.LinkedList;

/**
 *
 * @author Karan Kumar
 */
public class Snake {
    
    public LinkedList<SnakeBody> snakebody = new LinkedList<>();
    public SnakeBody move(String direction) {
        SnakeBody newbody = null;
        int headX = this.snakebody.getFirst().getX();
        int headY = this.snakebody.getFirst().getY();
        switch(direction) {
            case "RIGHT" :
                newbody = new SnakeBody(headX + 15, headY);
                break;
            case "LEFT" :
                newbody = new SnakeBody(headX - 15, headY);
                break;
            case "UP" :
                newbody = new SnakeBody(headX, headY - 15);
                break;            
            case "DOWN" :
                newbody = new SnakeBody(headX, headY + 15);
                break;
            
        }
        this.snakebody.addFirst(newbody);
        return snakebody.removeLast();
    }
    
    public SnakeBody getHead() {
        return snakebody.getFirst();
    }
    public SnakeBody getTail() {
        return snakebody.getLast();
    }

    public void addTail(SnakeBody a) {
        this.snakebody.addLast(a);
        
    }

    public LinkedList<SnakeBody> getBody() {
        return snakebody;
    }


    public void setBody(LinkedList<SnakeBody> snakebody) {
        this.snakebody = snakebody;
    }
    
    
}
