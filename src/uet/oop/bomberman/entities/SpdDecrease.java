package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Item;

public class SpdDecrease extends Item {
    public SpdDecrease(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void buff() {
        if (BombermanGame.bomberman1.getX() % 4 == 0 && BombermanGame.bomberman1.getY() % 4 == 0 && BombermanGame.bomberman1.getSpeed() == 2) {
            BombermanGame.bomberman1.setSpeed(BombermanGame.bomberman1.getSpeed() / 2);
        } else if (BombermanGame.bomberman1.getX() % 8 == 0 && BombermanGame.bomberman1.getY() % 8 == 0 && BombermanGame.bomberman1.getSpeed() == 4) {
            BombermanGame.bomberman1.setSpeed(BombermanGame.bomberman1.getSpeed() / 2);
        }
    }

    public void buff2() {
        if (BombermanGame.PvPMode == true) {
            if (BombermanGame.bomberman2.getX() % 4 == 0 && BombermanGame.bomberman2.getY() % 4 == 0 && BombermanGame.bomberman2.getSpeed() == 2) {
                BombermanGame.bomberman2.setSpeed(BombermanGame.bomberman2.getSpeed() / 2);
            } else if (BombermanGame.bomberman2.getX() % 8 == 0 && BombermanGame.bomberman2.getY() % 8 == 0 && BombermanGame.bomberman2.getSpeed() == 4) {
                BombermanGame.bomberman2.setSpeed(BombermanGame.bomberman2.getSpeed() / 2);
            }
        }
    }

    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman1.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman1.getY()) < 28) {
            if (BombermanGame.bomberman1.getSpeed() > 1) {
                buff();
                BombermanGame.itemsList.remove(this);
            }
        }
        if (BombermanGame.PvPMode == true) {
            if (Math.abs(getX() - BombermanGame.bomberman2.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman2.getY()) < 28) {
                if (BombermanGame.bomberman2.getSpeed() > 1) {
                    buff2();
                    BombermanGame.itemsList.remove(this);
                }
            }
        }
    }
}
