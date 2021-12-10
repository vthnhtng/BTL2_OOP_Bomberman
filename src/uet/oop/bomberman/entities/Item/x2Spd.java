package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.DynamicEntity;

public class x2Spd extends Item {


    public x2Spd(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void buff() {
        if (BombermanGame.bomberman.getX() % 4 == 0 && BombermanGame.bomberman.getY() % 4 == 0 && BombermanGame.bomberman.getSpeed() == 2) {
            BombermanGame.bomberman.setSpeed(BombermanGame.bomberman.getSpeed() * 2);
            BombermanGame.itemsList.remove(this);
        } else if (BombermanGame.bomberman.getX() % 8 == 0 && BombermanGame.bomberman.getY() % 8 == 0 && BombermanGame.bomberman.getSpeed() == 4) {

            BombermanGame.bomberman.setSpeed(BombermanGame.bomberman.getSpeed() * 2);
            BombermanGame.itemsList.remove(this);
        }
    }

    @Override
    public void update() {
        System.out.println(BombermanGame.bomberman.getSpeed());
        if (Math.abs(getX() - BombermanGame.bomberman.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman.getY()) < 28) {
            if (BombermanGame.bomberman.getSpeed() <= 8) {
                buff();
                System.out.println(BombermanGame.bomberman.getSpeed());
            }
        }
    }
}
