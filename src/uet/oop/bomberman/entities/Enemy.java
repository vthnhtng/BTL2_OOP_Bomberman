package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Levels;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends DynamicEntity {
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Enemy() {
    }

    public void randomMoving() {

    }

    public abstract void update();


    public abstract void reborn();

    public abstract void dead();

}
