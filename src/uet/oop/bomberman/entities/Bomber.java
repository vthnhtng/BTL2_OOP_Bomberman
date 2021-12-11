package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Levels;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomber extends DynamicEntity {
    // viet them buff


    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        speed = 2;
        interval = 3;

    }

    @Override
    public void update() {
        if (!isDead()) {
            this.moving = false;
            if (moveDOWN && (canMove_Down(getX(), getY(), Levels.Maps.get(BombermanGame.levels)) || can_Move_Down_2(getX(), getY()))) {
                this.moveDOWN();
                this.moving = true;
            }
            if (moveLEFT && (canMove_Left(getX(), getY(), Levels.Maps.get(BombermanGame.levels)) || can_Move_Left_2(getX(), getY()))) {
                this.moveLEFT();
                this.moving = true;
            }
            if (moveUP && (canMove_Up(getX(), getY(), Levels.Maps.get(BombermanGame.levels)) || can_Move_Up_2(getX(), getY()))) {

                this.moveUP();
                this.moving = true;
            }
            if (moveRIGHT && (canMove_Right(getX(), getY(), Levels.Maps.get(BombermanGame.levels)) || can_Move_Right_2(getX(), getY()))) {
                this.moveRIGHT();
                this.moving = true;
            }

            if (moving) {
                frame++; // check giu phim di du lau de chuyen hieu ung
                if (frame > interval) {
                    frame = 0;
                    indexAnimation++;
                    if (indexAnimation > 7) {  // chay het hoat anh 8 lai tro ve hoat anh 1
                        indexAnimation = 0;
                    }
                }

                if (moveRIGHT) {
                    switch (indexAnimation) {
                        case 0:
                            this.setImg(Sprite.bomman_right.getFxImage());
                            break;
                        case 1:
                            this.setImg(Sprite.bomman_right_1.getFxImage());
                            break;
                        case 2:
                            this.setImg(Sprite.bomman_right_2.getFxImage());
                            break;
                        case 3:
                            this.setImg(Sprite.bomman_right_3.getFxImage());
                            break;
                        case 4:
                            this.setImg(Sprite.bomman_right_4.getFxImage());
                            break;
                        case 5:
                            this.setImg(Sprite.bomman_right_5.getFxImage());
                            break;
                        case 6:
                            this.setImg(Sprite.bomman_right_6.getFxImage());
                            break;
                        case 7:
                            this.setImg(Sprite.bomman_right_7.getFxImage());
                            break;

                    }
                } else if (moveLEFT) {
                    switch (indexAnimation) {
                        case 0:
                            this.setImg(Sprite.bomman_left.getFxImage());
                            break;
                        case 1:
                            this.setImg(Sprite.bomman_left_1.getFxImage());
                            break;
                        case 2:
                            this.setImg(Sprite.bomman_left_2.getFxImage());
                            break;
                        case 3:
                            this.setImg(Sprite.bomman_left_3.getFxImage());
                            break;
                        case 4:
                            this.setImg(Sprite.bomman_left_4.getFxImage());
                            break;
                        case 5:
                            this.setImg(Sprite.bomman_left_5.getFxImage());
                            break;
                        case 6:
                            this.setImg(Sprite.bomman_left_6.getFxImage());
                            break;
                        case 7:
                            this.setImg(Sprite.bomman_left_7.getFxImage());
                            break;
                    }
                } else if (moveUP) {
                    switch (indexAnimation) {
                        case 0:
                            this.setImg(Sprite.bomman_up.getFxImage());
                            break;
                        case 1:
                            this.setImg(Sprite.bomman_up_1.getFxImage());
                            break;
                        case 2:
                            this.setImg(Sprite.bomman_up_2.getFxImage());
                            break;
                        case 3:
                            this.setImg(Sprite.bomman_up_3.getFxImage());
                            break;
                        case 4:
                            this.setImg(Sprite.bomman_up_4.getFxImage());
                            break;
                        case 5:
                            this.setImg(Sprite.bomman_up_5.getFxImage());
                            break;
                        case 6:
                            this.setImg(Sprite.bomman_up_6.getFxImage());
                            break;
                        case 7:
                            this.setImg(Sprite.bomman_up_7.getFxImage());
                            break;
                    }
                } else if (moveDOWN) {
                    switch (indexAnimation) {
                        case 0:
                            this.setImg(Sprite.bomman_down.getFxImage());
                            break;
                        case 1:
                            this.setImg(Sprite.bomman_down_1.getFxImage());
                            break;
                        case 2:
                            this.setImg(Sprite.bomman_down_2.getFxImage());
                            break;
                        case 3:
                            this.setImg(Sprite.bomman_down_3.getFxImage());
                            break;
                        case 4:
                            this.setImg(Sprite.bomman_down_4.getFxImage());
                            break;
                        case 5:
                            this.setImg(Sprite.bomman_down_5.getFxImage());
                            break;
                        case 6:
                            this.setImg(Sprite.bomman_down_6.getFxImage());
                            break;
                        case 7:
                            this.setImg(Sprite.bomman_down_7.getFxImage());
                            break;
                    }
                }
            } else {
                this.setImg(Sprite.bomman_down.getFxImage());
            }
            // get buff and dead
        }
    }

    private int rebornFrame = 0;
    private int intervalToReborn = 4;
    private boolean isReborn;

    @Override
    public void dead() {

    }
    public void reborn() {
        live--;
        if (live > 0) {
            this.setDead(false);
            this.setX(32);
            this.setY(160);
            this.setImg(Sprite.bomman_down.getFxImage());
        } else {
            //hien menu
        }
    }


    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

}
