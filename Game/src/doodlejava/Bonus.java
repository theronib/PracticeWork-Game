package doodlejava;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import oneiros.games.OSprite;

public class Bonus extends OSprite{
	
	private int points;
	

    public Bonus(int points) {
        this.points = points;
        // Завантаження зображення бонусу (наприклад, "20.png" для 20 балів)
        try {
            Image bonusImage = ImageIO.read(getClass().getResource("bonus/" + points + ".png"));
//            setImage(bonusImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPoints() {
        return points;
    }

    public void updatePosition(int offset) {
        setLocation(getX(), getY() + offset);
        if (getY() > getParent().getHeight()) {
            // Якщо бонус випав з екрану, його позиція генерується заново
            setLocation((int) (Math.random() * (getParent().getWidth() - getWidth())),
                    ((int) (Math.random() * 50) - 50));
        }
    }
}


