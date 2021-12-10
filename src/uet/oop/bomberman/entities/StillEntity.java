package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class StillEntity extends Entity{
    int frameBomb = 0; // biến check tg khi giữ phím
    double intervalBomb = 7; // giữ phím trong khoảng 5 mil s thì chuyển animation
    int indexAnimation = 0;// lấy sprite từ 1 -> 2

    public StillEntity() {

    }
    public StillEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public abstract void update();
}
