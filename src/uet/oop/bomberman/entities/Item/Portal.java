package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Levels;

public class Portal extends Item{
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman.getY()) < 28) {
            if (BombermanGame.movingEntities.size() - Levels.lvEnemy[BombermanGame.levels] == 1 ) {
                buff();
            }
        }
    }

    @Override
    public void buff() {

    }
}
