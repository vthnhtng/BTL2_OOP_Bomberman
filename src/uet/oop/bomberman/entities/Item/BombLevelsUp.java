package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.DynamicEntity;

public class BombLevelsUp extends Item {

    public BombLevelsUp(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void buff() {
        BombermanGame.bombLevels++;
    }

    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman.getY()) < 28) {
            if (BombermanGame.bombLevels < 10) {
                buff();
            }
            BombermanGame.itemsList.remove(this);
        }
    }
}
