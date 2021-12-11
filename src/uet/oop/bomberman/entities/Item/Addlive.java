package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.Enemy;

public class Addlive extends Item {
    public Addlive (int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void buff() {
        BombermanGame.bomberman.setLive(BombermanGame.bomberman.getLive() + 1);
    }

    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman.getY()) < 28) {
            if (BombermanGame.bomberman.getLive() < 3) {
                buff();
            }
            BombermanGame.itemsList.remove(this);
        }
    }
}