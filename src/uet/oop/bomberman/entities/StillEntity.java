package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class StillEntity extends Entity{
    int frameBomb1 = 0; // biến check tg khi giữ phím
    double intervalBomb1 = 7; // giữ phím trong khoảng 5 mil s thì chuyển animation
    int indexAnimation1 = 0;// lấy sprite từ 1 -> 2

    int frameBomb2 = 0; // biến check tg khi giữ phím
    double intervalBomb2 = 7; // giữ phím trong khoảng 5 mil s thì chuyển animation
    int indexAnimation2 = 0;

    public StillEntity() {

    }
    public StillEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public abstract void update();
}
