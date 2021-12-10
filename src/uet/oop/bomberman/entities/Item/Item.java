package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.StillEntity;

public abstract class Item extends StillEntity {

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public abstract void update();

    public abstract void buff();
}
