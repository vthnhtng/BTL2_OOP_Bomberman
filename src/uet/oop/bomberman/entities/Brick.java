package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends StillEntity{
    private boolean destroyed = false;
    private int frameDestruction = 0;
    private int intervalBrick = 7;
    private int indexAnimation = 0;


    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }


}
