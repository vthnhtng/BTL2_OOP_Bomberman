package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class BombLevelsUp extends Item {

    public BombLevelsUp(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void buff() {
        BombermanGame.bombLevels1++;
    }
    public void buff2() {
        if (BombermanGame.PvPMode == true) {
            BombermanGame.bombLevels2++;
        }
    }

    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman1.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman1.getY()) < 28) {
            if (BombermanGame.bombLevels1 < 5) {
                buff();
            }
            BombermanGame.itemsList.remove(this);
        }
        if (BombermanGame.PvPMode == true) {
            if (Math.abs(getX() - BombermanGame.bomberman2.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman2.getY()) < 28) {
                if (BombermanGame.bombLevels2 < 5) {
                    buff2();
                }
                BombermanGame.itemsList.remove(this);
            }
        }
    }
}
