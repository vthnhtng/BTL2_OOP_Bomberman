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
        BombermanGame.bomberman1.setLive(BombermanGame.bomberman1.getLive() + 1);
    }
    public void buff2() {
        if (BombermanGame.Player2 == true) {
            BombermanGame.bomberman2.setLive(BombermanGame.bomberman2.getLive() + 1);
        }
    }
    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman1.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman1.getY()) < 28) {
            if (BombermanGame.bomberman1.getLive() < 3) {
                buff();
            }
            BombermanGame.itemsList.remove(this);
        }
        if (BombermanGame.Player2 == true) {
            if (Math.abs(getX() - BombermanGame.bomberman2.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman2.getY()) < 28) {
                if (BombermanGame.bomberman2.getLive() < 3) {
                    buff2();
                }
                BombermanGame.itemsList.remove(this);
            }
        }
    }
}
