package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class FireLevelsUp extends Item {
    public FireLevelsUp(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    @Override
    public void buff() {
        BombermanGame.fireLevels1++;
    }

    public void buff2() {
        if (BombermanGame.PvPMode == true) {
            BombermanGame.fireLevels2 ++;
        }
    }

    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman1.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman1.getY()) < 28) {
            if (BombermanGame.fireLevels1 < 5) {
                buff();
            }
            BombermanGame.itemsList.remove(this);
        }
        if (BombermanGame.PvPMode == true) {
            if (Math.abs(getX() - BombermanGame.bomberman2.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman2.getY()) < 28) {
                if (BombermanGame.fireLevels2 < 5) {
                    buff();
                }
                BombermanGame.itemsList.remove(this);
            }
        }
    }
}
