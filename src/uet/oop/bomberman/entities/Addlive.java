package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Menu;

public class Addlive extends Item {
    public Addlive (int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void buff() {
        BombermanGame.bomberman1.setLive(BombermanGame.bomberman1.getLive() + 1);
        uet.oop.bomberman.Menu.startText4.setText("LIVES: " + (BombermanGame.bomberman1.getLive()));
        uet.oop.bomberman.Menu.startText4.setFont(Font.font("Verdana", 32 + 5));
        uet.oop.bomberman.Menu.startText4.setX(700);
        uet.oop.bomberman.Menu.startText4.setY(50);
        uet.oop.bomberman.Menu.startText4.setFill(Color.LAVENDERBLUSH);
        BombermanGame.root.getChildren().add(Menu.startText4);
    }
    public void buff2() {
        if (BombermanGame.PvPMode == true) {
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
        if (BombermanGame.PvPMode == true) {
            if (Math.abs(getX() - BombermanGame.bomberman2.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman2.getY()) < 28) {
                if (BombermanGame.bomberman2.getLive() < 3) {
                    buff2();
                }
                BombermanGame.itemsList.remove(this);
            }
        }
    }
}
