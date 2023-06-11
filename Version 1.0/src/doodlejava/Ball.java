package doodlejava;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import oneiros.games.OSprite;
import oneiros.physic.OVector2D;
import util.Resource;

public class Ball extends OSprite{
	
	private static final OVector2D GRAVITY_VECTOR = new OVector2D(0.2, 270);
    private static final double AIR_FRICTION = 0.02;

    public Ball() {
        Image ballImage = Resource.getImage("ball.png");

        this.setAcceleration(GRAVITY_VECTOR);
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void update(Graphics time) {
        super.update(time);

        double x = getVelocity().getX();
        if (Math.abs(x) < 0.1) {
            setVelocityX(0);
        } else {
            double friction = Math.abs(x) * AIR_FRICTION;
            if (x > 0) {
                setVelocityX(x - friction);
            } else if (x < 0) {
                setVelocityX(x + friction);
            }
        }
    }
	
	
	
	

}
