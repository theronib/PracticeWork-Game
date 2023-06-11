package doodlejava;

import java.awt.Rectangle;
import oneiros.games.OSprite;
import util.Resource;

public class Platform extends OSprite {
	private int direction;
    private boolean canMove;

    public Platform() {
        super(Resource.getImage("platform2.png"));
        this.direction = Math.random() < 0.5 ? -1 : 1;
        this.canMove = false; // Початково дошка не може рухатись
    }

    public Rectangle getBase() {
        return new Rectangle(getX(), getY() + getHeight(), getWidth(), 2);
    }

    public void update(int score) {
        if (score >= 1000) {
            canMove = true; // Якщо користувач набрав 1000 балів, дошка може рухатись
        }

        if (canMove) {
            int newX = getX() + (direction * getSpeedMultiplier(score));
            if (newX <= 0 || newX >= getParent().getWidth() - getWidth()) {
                reverseDirection();
            }
            setLocation(newX, getY());
        }
    }

    public void reverseDirection() {
        direction *= -1;
    }

    private int getSpeedMultiplier(int score) {
        if (score >= 3000) {
            return 3; // Швидкість утричі збільшується при досягненні 3000 балів
        } else if (score >= 2000) {
            return 2; // Швидкість подвоюється при досягненні 2000 балів
        } else {
            return 1; // Швидкість залишається незмінною до досягнення 2000 балів
        }
    }
}
