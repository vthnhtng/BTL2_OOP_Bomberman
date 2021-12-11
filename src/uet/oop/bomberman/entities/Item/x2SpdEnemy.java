package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.Enemy;

public class x2SpdEnemy extends Item {
    public x2SpdEnemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    private int baseSpd = 1;
    @Override
    public void buff() {

    }

    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman.getY()) < 28) {
            buff();
        }
    }
}
