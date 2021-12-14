package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Levels;
import uet.oop.bomberman.Menu;
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

    public Bomber() {
        img = Sprite.Grass_default.getFxImage();
        setX(0);
        setY(0);
    }

    public void move1() {
        if (!isDead()) {
            this.setImg(Sprite.bomman1_down.getFxImage());
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
                            this.setImg(Sprite.bomman1_right.getFxImage());
                            break;
                        case 1:
                            this.setImg(Sprite.bomman1_right_1.getFxImage());
                            break;
                        case 2:
                            this.setImg(Sprite.bomman1_right_2.getFxImage());
                            break;
                        case 3:
                            this.setImg(Sprite.bomman1_right_3.getFxImage());
                            break;
                        case 4:
                            this.setImg(Sprite.bomman1_right_4.getFxImage());
                            break;
                        case 5:
                            this.setImg(Sprite.bomman1_right_5.getFxImage());
                            break;
                        case 6:
                            this.setImg(Sprite.bomman1_right_6.getFxImage());
                            break;
                        case 7:
                            this.setImg(Sprite.bomman1_right_7.getFxImage());
                            break;

                    }
                } else if (moveLEFT) {
                    switch (indexAnimation) {
                        case 0:
                            this.setImg(Sprite.bomman1_left.getFxImage());
                            break;
                        case 1:
                            this.setImg(Sprite.bomman1_left_1.getFxImage());
                            break;
                        case 2:
                            this.setImg(Sprite.bomman1_left_2.getFxImage());
                            break;
                        case 3:
                            this.setImg(Sprite.bomman1_left_3.getFxImage());
                            break;
                        case 4:
                            this.setImg(Sprite.bomman1_left_4.getFxImage());
                            break;
                        case 5:
                            this.setImg(Sprite.bomman1_left_5.getFxImage());
                            break;
                        case 6:
                            this.setImg(Sprite.bomman1_left_6.getFxImage());
                            break;
                        case 7:
                            this.setImg(Sprite.bomman1_left_7.getFxImage());
                            break;
                    }
                } else if (moveUP) {
                    switch (indexAnimation) {
                        case 0:
                            this.setImg(Sprite.bomman1_up.getFxImage());
                            break;
                        case 1:
                            this.setImg(Sprite.bomman1_up_1.getFxImage());
                            break;
                        case 2:
                            this.setImg(Sprite.bomman1_up_2.getFxImage());
                            break;
                        case 3:
                            this.setImg(Sprite.bomman1_up_3.getFxImage());
                            break;
                        case 4:
                            this.setImg(Sprite.bomman1_up_4.getFxImage());
                            break;
                        case 5:
                            this.setImg(Sprite.bomman1_up_5.getFxImage());
                            break;
                        case 6:
                            this.setImg(Sprite.bomman1_up_6.getFxImage());
                            break;
                        case 7:
                            this.setImg(Sprite.bomman1_up_7.getFxImage());
                            break;
                    }
                } else if (moveDOWN) {
                    switch (indexAnimation) {
                        case 0:
                            this.setImg(Sprite.bomman1_down.getFxImage());
                            break;
                        case 1:
                            this.setImg(Sprite.bomman1_down_1.getFxImage());
                            break;
                        case 2:
                            this.setImg(Sprite.bomman1_down_2.getFxImage());
                            break;
                        case 3:
                            this.setImg(Sprite.bomman1_down_3.getFxImage());
                            break;
                        case 4:
                            this.setImg(Sprite.bomman1_down_4.getFxImage());
                            break;
                        case 5:
                            this.setImg(Sprite.bomman1_down_5.getFxImage());
                            break;
                        case 6:
                            this.setImg(Sprite.bomman1_down_6.getFxImage());
                            break;
                        case 7:
                            this.setImg(Sprite.bomman1_down_7.getFxImage());
                            break;
                    }
                } else {
                    this.setImg(Sprite.bomman1_down.getFxImage());
                }
            }
        }
    }

    @Override
    public void update() {
        if (!isDead()) {
            if (BombermanGame.player2) {
                this.setImg(Sprite.bomman1_down.getFxImage());
            } else if (BombermanGame.player3) {
                this.setImg(Sprite.bomman2_down.getFxImage());
            } else {
                this.setImg(Sprite.bomman_down.getFxImage());
            }
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
                if (BombermanGame.player1 == true) {
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
                    } else {
                        this.setImg(Sprite.bomman_down.getFxImage());
                    }
                }


                //player2
                else if (BombermanGame.player2 == true) {
                    if (moveRIGHT) {
                        switch (indexAnimation) {
                            case 0:
                                this.setImg(Sprite.bomman1_right.getFxImage());
                                break;
                            case 1:
                                this.setImg(Sprite.bomman1_right_1.getFxImage());
                                break;
                            case 2:
                                this.setImg(Sprite.bomman1_right_2.getFxImage());
                                break;
                            case 3:
                                this.setImg(Sprite.bomman1_right_3.getFxImage());
                                break;
                            case 4:
                                this.setImg(Sprite.bomman1_right_4.getFxImage());
                                break;
                            case 5:
                                this.setImg(Sprite.bomman1_right_5.getFxImage());
                                break;
                            case 6:
                                this.setImg(Sprite.bomman1_right_6.getFxImage());
                                break;
                            case 7:
                                this.setImg(Sprite.bomman1_right_7.getFxImage());
                                break;

                        }
                    } else if (moveLEFT) {
                        switch (indexAnimation) {
                            case 0:
                                this.setImg(Sprite.bomman1_left.getFxImage());
                                break;
                            case 1:
                                this.setImg(Sprite.bomman1_left_1.getFxImage());
                                break;
                            case 2:
                                this.setImg(Sprite.bomman1_left_2.getFxImage());
                                break;
                            case 3:
                                this.setImg(Sprite.bomman1_left_3.getFxImage());
                                break;
                            case 4:
                                this.setImg(Sprite.bomman1_left_4.getFxImage());
                                break;
                            case 5:
                                this.setImg(Sprite.bomman1_left_5.getFxImage());
                                break;
                            case 6:
                                this.setImg(Sprite.bomman1_left_6.getFxImage());
                                break;
                            case 7:
                                this.setImg(Sprite.bomman1_left_7.getFxImage());
                                break;
                        }
                    } else if (moveUP) {
                        switch (indexAnimation) {
                            case 0:
                                this.setImg(Sprite.bomman1_up.getFxImage());
                                break;
                            case 1:
                                this.setImg(Sprite.bomman1_up_1.getFxImage());
                                break;
                            case 2:
                                this.setImg(Sprite.bomman1_up_2.getFxImage());
                                break;
                            case 3:
                                this.setImg(Sprite.bomman1_up_3.getFxImage());
                                break;
                            case 4:
                                this.setImg(Sprite.bomman1_up_4.getFxImage());
                                break;
                            case 5:
                                this.setImg(Sprite.bomman1_up_5.getFxImage());
                                break;
                            case 6:
                                this.setImg(Sprite.bomman1_up_6.getFxImage());
                                break;
                            case 7:
                                this.setImg(Sprite.bomman1_up_7.getFxImage());
                                break;
                        }
                    } else if (moveDOWN) {
                        switch (indexAnimation) {
                            case 0:
                                this.setImg(Sprite.bomman1_down.getFxImage());
                                break;
                            case 1:
                                this.setImg(Sprite.bomman1_down_1.getFxImage());
                                break;
                            case 2:
                                this.setImg(Sprite.bomman1_down_2.getFxImage());
                                break;
                            case 3:
                                this.setImg(Sprite.bomman1_down_3.getFxImage());
                                break;
                            case 4:
                                this.setImg(Sprite.bomman1_down_4.getFxImage());
                                break;
                            case 5:
                                this.setImg(Sprite.bomman1_down_5.getFxImage());
                                break;
                            case 6:
                                this.setImg(Sprite.bomman1_down_6.getFxImage());
                                break;
                            case 7:
                                this.setImg(Sprite.bomman1_down_7.getFxImage());
                                break;
                        }
                    } else {
                        this.setImg(Sprite.bomman1_down.getFxImage());
                    }
                }

                //player3
                else if (BombermanGame.player3 == true) {
                    if (moveRIGHT) {
                        switch (indexAnimation) {
                            case 0:
                                this.setImg(Sprite.bomman2_right.getFxImage());
                                break;
                            case 1:
                                this.setImg(Sprite.bomman2_right_1.getFxImage());
                                break;
                            case 2:
                                this.setImg(Sprite.bomman2_right_2.getFxImage());
                                break;
                            case 3:
                                this.setImg(Sprite.bomman2_right_3.getFxImage());
                                break;
                            case 4:
                                this.setImg(Sprite.bomman2_right_4.getFxImage());
                                break;
                            case 5:
                                this.setImg(Sprite.bomman2_right_5.getFxImage());
                                break;
                            case 6:
                                this.setImg(Sprite.bomman2_right_6.getFxImage());
                                break;
                            case 7:
                                this.setImg(Sprite.bomman2_right_7.getFxImage());
                                break;

                        }
                    } else if (moveLEFT) {
                        switch (indexAnimation) {
                            case 0:
                                this.setImg(Sprite.bomman2_left.getFxImage());
                                break;
                            case 1:
                                this.setImg(Sprite.bomman2_left_1.getFxImage());
                                break;
                            case 2:
                                this.setImg(Sprite.bomman2_left_2.getFxImage());
                                break;
                            case 3:
                                this.setImg(Sprite.bomman2_left_3.getFxImage());
                                break;
                            case 4:
                                this.setImg(Sprite.bomman2_left_4.getFxImage());
                                break;
                            case 5:
                                this.setImg(Sprite.bomman2_left_5.getFxImage());
                                break;
                            case 6:
                                this.setImg(Sprite.bomman2_left_6.getFxImage());
                                break;
                            case 7:
                                this.setImg(Sprite.bomman2_left_7.getFxImage());
                                break;
                        }
                    } else if (moveUP) {
                        switch (indexAnimation) {
                            case 0:
                                this.setImg(Sprite.bomman2_up.getFxImage());
                                break;
                            case 1:
                                this.setImg(Sprite.bomman2_up_1.getFxImage());
                                break;
                            case 2:
                                this.setImg(Sprite.bomman2_up_2.getFxImage());
                                break;
                            case 3:
                                this.setImg(Sprite.bomman2_up_3.getFxImage());
                                break;
                            case 4:
                                this.setImg(Sprite.bomman2_up_4.getFxImage());
                                break;
                            case 5:
                                this.setImg(Sprite.bomman2_up_5.getFxImage());
                                break;
                            case 6:
                                this.setImg(Sprite.bomman2_up_6.getFxImage());
                                break;
                            case 7:
                                this.setImg(Sprite.bomman2_up_7.getFxImage());
                                break;
                        }
                    } else if (moveDOWN) {
                        switch (indexAnimation) {
                            case 0:
                                this.setImg(Sprite.bomman2_down.getFxImage());
                                break;
                            case 1:
                                this.setImg(Sprite.bomman2_down_1.getFxImage());
                                break;
                            case 2:
                                this.setImg(Sprite.bomman2_down_2.getFxImage());
                                break;
                            case 3:
                                this.setImg(Sprite.bomman2_down_3.getFxImage());
                                break;
                            case 4:
                                this.setImg(Sprite.bomman2_down_4.getFxImage());
                                break;
                            case 5:
                                this.setImg(Sprite.bomman2_down_5.getFxImage());
                                break;
                            case 6:
                                this.setImg(Sprite.bomman2_down_6.getFxImage());
                                break;
                            case 7:
                                this.setImg(Sprite.bomman2_down_7.getFxImage());
                                break;
                        }
                    } else {
                        this.setImg(Sprite.bomman2_down.getFxImage());
                    }
                }
            }
        }
        if (isRival) {
            move1();
        }
        // get buff and dead

    }

    private boolean isReborn;

    @Override
    public void dead() {
        frame_dead++;
        if (frame_dead > interval_dead) {
            index_animation_dead++;
            if (index_animation_dead > 3) {
                index_animation_dead = 0;
            }
        }
        if (this.live > 0) {
            switch (index_animation_dead) {
                case 0:
                    this.setDead(true);
                    this.setImg(Sprite.player_dead1.getFxImage());
                    break;
                case 1:
                    this.setDead(true);
                    this.setImg(Sprite.player_dead2.getFxImage());
                    break;
                case 2:
                    this.setDead(true);
                    this.setImg(Sprite.player_dead3.getFxImage());
                    break;
                case 3:
                    if (BombermanGame.bomberman1.isDead()) {
                        BombermanGame.bomberman1.reborn();
                    }
                    if (BombermanGame.bomberman2.isDead()) {
                        BombermanGame.bomberman2.reborn();
                    }
                    break;
            }
        }
    }

    public void reborn() {
        try {

            live--;
            if (live > 0) {
                this.setDead(false);
                if (BombermanGame.PvPMode == true) {
                    if (isRival == true) {
                        setX(32 * 25);
                        setY(32 * 17);
                        this.setImg(Sprite.bomman1_down.getFxImage());
                    } else {
                        setX(32 * 9);
                        setY(32 * 3);
                        this.setImg(Sprite.bomman_down.getFxImage());
                    }
                } else {
                    setX(32);
                    setY(32 * 5);
                    if (BombermanGame.player3) {
                        this.setImg(Sprite.bomman2_down.getFxImage());
                    } else if (BombermanGame.player2) {
                        this.setImg(Sprite.bomman1_down.getFxImage());
                    } else {
                        this.setImg(Sprite.bomman_down.getFxImage());
                    }
                }
                uet.oop.bomberman.Menu.startText4.setText("LIVES: " + (BombermanGame.bomberman1.getLive()));
                uet.oop.bomberman.Menu.startText4.setFont(Font.font("Verdana", 32 + 5));
                uet.oop.bomberman.Menu.startText4.setX(700);
                uet.oop.bomberman.Menu.startText4.setY(50);
                uet.oop.bomberman.Menu.startText4.setFill(Color.LAVENDERBLUSH);
                BombermanGame.root.getChildren().add(Menu.startText4);
            } else {
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
        } catch (IllegalArgumentException e) {
        }

    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

}
