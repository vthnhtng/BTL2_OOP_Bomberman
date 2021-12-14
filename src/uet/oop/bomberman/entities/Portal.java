package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Levels;
import uet.oop.bomberman.Menu;
import uet.oop.bomberman.entities.Item;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Item {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (Math.abs(getX() - BombermanGame.bomberman1.getX()) < 28 && Math.abs(getY() - BombermanGame.bomberman1.getY()) < 28) {
            if (BombermanGame.enemyCount <= 0 ) {
                buff();
            }
        }
    }

    @Override
    public void buff() {
        try {
            if (BombermanGame.levels < 2) {
                for (int i = 0; i < 35; i++) {
                    for (int j = 0; j < 35; j++) {
                        BombermanGame.items[i][j] = 0;
                    }
                }
                BombermanGame.itemsList.clear();
                BombermanGame.levels++;
                Levels.firstTime = false;
                Levels.createMap(Levels.Maps.get(BombermanGame.levels));
                Levels.updateStillObject(Levels.Maps.get(BombermanGame.levels));
                BombermanGame.bomberman1.setX(32);
                BombermanGame.bomberman1.setY(160);
                BombermanGame.bomberman1.setLive(BombermanGame.bomberman1.getLive() + 1);
                Menu.startText3.setText("LEVELS: " + (BombermanGame.levels + 1));
                Menu.startText3.setFont(Font.font("Verdana", 32 + 5));
                Menu.startText3.setX(320);
                Menu.startText3.setY(50);
                Menu.startText3.setFill(Color.LAVENDERBLUSH);
                uet.oop.bomberman.Menu.startText4.setText("LIVES: " + (BombermanGame.bomberman1.getLive()));
                uet.oop.bomberman.Menu.startText4.setFont(Font.font("Verdana", 32 + 5));
                uet.oop.bomberman.Menu.startText4.setX(700);
                uet.oop.bomberman.Menu.startText4.setY(50);
                uet.oop.bomberman.Menu.startText4.setFill(Color.LAVENDERBLUSH);
                BombermanGame.root.getChildren().add(Menu.startText3);
                BombermanGame.root.getChildren().add(Menu.startText4);

                if (BombermanGame.levels == 2) {
                    uet.oop.bomberman.Menu.creatMenu(BombermanGame.root);
                    BombermanGame.enemyCount = 0;
                    BombermanGame.levels = 0;
                    BombermanGame.movingEntities.clear();
                    BombermanGame.stillObjects.clear();
                    BombermanGame.bombLevels1 = 1;
                    BombermanGame.bombLevels2 = 1;
                    BombermanGame.fireLevels1 = 1;
                    BombermanGame.fireLevels2 = 1;
                    BombermanGame.itemsList.clear();
                    Levels.clearMaps(Levels.Maps.get(0));
                    Levels.clearMaps(Levels.Maps.get(1));
                    Levels.clearMaps(Levels.Maps.get(2));
                    Levels.clearMaps(Levels.Maps.get(3));
                    BombermanGame.PvPMode = false;
                    BombermanGame.bomberman2.setImg(Sprite.Grass_default.getFxImage());
                    BombermanGame.bomberman2.setX(0);
                    BombermanGame.bomberman2.setY(0);
                    BombermanGame.gameStart = false;
                }

            }
        }catch (IllegalArgumentException e) {}


    }
}
