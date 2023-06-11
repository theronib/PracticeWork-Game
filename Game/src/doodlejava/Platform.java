package doodlejava;

import java.awt.Rectangle;
import oneiros.games.OSprite;
import util.Resource;

public class Platform extends OSprite {
    private int direction;

    public Platform() {
        super(Resource.getImage("platform2.png"));
        this.direction = Math.random() < 0.5 ? -1 : 1;
    }

    public Rectangle getBase() {
        return new Rectangle(getX(), getY() + getHeight(), getWidth(), 2);
    }

    public void update() {
        int newX = getX() + direction;
        if (newX <= 0 || newX >= getParent().getWidth() - getWidth()) {
            reverseDirection();
        }
        setLocation(newX, getY());
    }
    
    public void reverseDirection() {
        direction *= -1;
    }
}
