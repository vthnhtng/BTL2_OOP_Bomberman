package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Levels;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends StillEntity {
    public boolean exploded1;
    public boolean exploded2;
    int countToExplode1 = 0;
    int intervalToExplode1 = 3;
    int countToExplode2 = 0;
    int intervalToExplode2 = 3;
    private boolean PlayerSet1;
    private boolean PlayerSet2;
    int frameExplosion1 = 0;
    int intervalExplode1 = 2;
    int frameExplosion2 = 0;
    int intervalExplode2 = 2;
    int indexExplosionAnimation = 0;

    public Bomb() {
    }

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    @Override
    public void update() {
        if (PlayerSet1) {
            explosion1();
        }
        if (PlayerSet2) {
            explosion2();
        }
    }

    public boolean isPlayerSet1() {
        return PlayerSet1;
    }

    public void setPlayerSet1(boolean playerSet1) {
        PlayerSet1 = playerSet1;
    }

    public boolean isPlayerSet2() {
        return PlayerSet2;
    }

    public void setPlayerSet2(boolean playerSet2) {
        PlayerSet2 = playerSet2;
    }

    public void explosion1() {
        frameBomb1++;
        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) > 28) || (Math.abs(BombermanGame.bomberman1.getY() - getY())) > 28) {
            Levels.Maps.get(BombermanGame.levels)[getY() / 32][getX() / 32] = 9;
        }

        if (frameBomb1 >= intervalBomb1) {
            frameBomb1 = 0;
            indexAnimation1++;
            if (indexAnimation1 > 2) {  // chay het hoat anh 8 lai tro ve hoat anh 1
                indexAnimation1 = 0;
                countToExplode1++;
            }
        }
        switch (indexAnimation1) {
            case 0:
                this.setImg(Sprite.bomb.getFxImage());
                break;
            case 1:
                this.setImg(Sprite.bomb_1.getFxImage());
                break;
            case 2:
                this.setImg(Sprite.bomb_2.getFxImage());
                break;
        }

        if (countToExplode1 > intervalToExplode1) {
            exploded1 = true;
            countToExplode1 = 0;
            BombermanGame.countBomb1--;
        }

        if (exploded1) {
            frameExplosion1 = frameExplosion1 + 1;
            if (frameExplosion1 > intervalExplode1) {
                frameExplosion1 = 0;
                indexExplosionAnimation++;
                if (indexExplosionAnimation > 3) {
                    indexExplosionAnimation = 0;
                }
            }
            switch (indexExplosionAnimation) {
                case 0:
                    this.setImg(Sprite.bomb_exploded.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX() + i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX() + i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }


                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX() - i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX() - i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX(), getY() - i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX(), getY() + i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical.getFxImage(), getX(), getY() + i * 32);
                        }
                    }


                    break;

                case 1:
                    this.setImg(Sprite.bomb_exploded1.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX() + i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX() + i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last1.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX() - i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX() - i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last1.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX(), getY() - i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last1.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical1.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX(), getY() + i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last1.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical1.getFxImage(), getX(), getY() + i * 32);
                        }
                    }

                    break;
                case 2:
                    this.setImg(Sprite.bomb_exploded1.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] = 0;
                            if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 1) {
                                Item Buff = new x2Spd((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_speed.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 2) {
                                Item Buff = new BombLevelsUp((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_bombs.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 3) {
                                Item Buff = new FireLevelsUp((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_flames.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 4) {
                                Item Buff = new SpdDecrease((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_flamepass.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 5) {
                                Item Buff = new Addlive((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_wallpass.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            }
                            break;
                        }

                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) < 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) < 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX() + i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX() + i * 32) - e.getX()) < 29 && Math.abs((getY()) - e.getY()) < 29) {
                                    e.dead();
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                }
                            }
                        }

                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last2.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                                Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] = 0;
                                if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 1) {
                                    Item Buff = new x2Spd((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 2) {
                                    Item Buff = new BombLevelsUp((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 3) {
                                    Item Buff = new FireLevelsUp((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 4) {
                                    Item Buff = new SpdDecrease((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 5) {
                                    Item Buff = new Addlive((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) < 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) < 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX() - i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX() - i * 32) - e.getX()) < 29 && Math.abs((getY()) - e.getY()) < 29) {
                                    e.dead();
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last2.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                                Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] = 0;
                                if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 1) {
                                    Item Buff = new x2Spd(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 2) {
                                    Item Buff = new BombLevelsUp(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 3) {
                                    Item Buff = new FireLevelsUp(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 4) {
                                    Item Buff = new SpdDecrease(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 5) {
                                    Item Buff = new Addlive(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) < 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) < 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX()) - e.getX()) <= 29 && Math.abs((getY() - i * 32) - e.getY()) <= 29) {
                                    e.dead();
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last2.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels1; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                                Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] = 0;
                                if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 1) {
                                    Item Buff = new x2Spd(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 2) {
                                    Item Buff = new BombLevelsUp(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 3) {
                                    Item Buff = new FireLevelsUp(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 4) {
                                    Item Buff = new SpdDecrease(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 5) {
                                    Item Buff = new Addlive(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) < 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) < 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (BombermanGame.PvPMode == true) {
                            if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                            if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                                if (!BombermanGame.bomberman2.isInvisible()) {
                                    BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                    BombermanGame.bomberman2.setDead(true);
                                }
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX()) - e.getX()) < 29 && Math.abs((getY() + i * 32) - e.getY()) < 29) {
                                    e.dead();
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels1) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last2.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(), getX(), getY() + i * 32);
                        }
                    }
                case 3:
                    if (BombermanGame.bomberman1.isDead()) {
                        BombermanGame.bomberman1.reborn();
                    }
                    if (BombermanGame.bomberman2.isDead()) {
                        BombermanGame.bomberman2.reborn();
                    }
                    exploded1 = false;
                    BombermanGame.bomberman1.render(BombermanGame.gc);
                //    BombermanGame.bomberman2.render(BombermanGame.gc);
                    Levels.Maps.get(BombermanGame.levels)[getY() / 32][getX() / 32] = 0;
                    Levels.updateStillObject(Levels.Maps.get(BombermanGame.levels));
                    PlayerSet1 = false;
                    BombermanGame.bombs.remove(this);
                    break;
            }
        }
    }

    public void explosion2() {
        frameBomb2++;
        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) > 28) || (Math.abs(BombermanGame.bomberman2.getY() - getY())) > 28) {
            Levels.Maps.get(BombermanGame.levels)[getY() / 32][getX() / 32] = 9;
        }

        if (frameBomb2 >= intervalBomb2) {
            frameBomb2 = 0;
            indexAnimation2++;
            if (indexAnimation2 > 2) {  // chay het hoat anh 8 lai tro ve hoat anh 1
                indexAnimation2 = 0;
                countToExplode2++;
            }
        }
        switch (indexAnimation2) {
            case 0:
                this.setImg(Sprite.bomb.getFxImage());
                break;
            case 1:
                this.setImg(Sprite.bomb_1.getFxImage());
                break;
            case 2:
                this.setImg(Sprite.bomb_2.getFxImage());
                break;
        }

        if (countToExplode2 > intervalToExplode2) {
            exploded2 = true;
            countToExplode2 = 0;
            BombermanGame.countBomb2--;
        }

        if (exploded2) {
            frameExplosion2 = frameExplosion2 + 1;
            if (frameExplosion2 > intervalExplode2) {
                frameExplosion2 = 0;
                indexExplosionAnimation++;
                if (indexExplosionAnimation > 3) {
                    indexExplosionAnimation = 0;
                }
            }
            switch (indexExplosionAnimation) {
                case 0:
                    this.setImg(Sprite.bomb_exploded.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX() + i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }

                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX() - i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX(), getY() - i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX(), getY() + i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical.getFxImage(), getX(), getY() + i * 32);
                        }
                    }


                    break;

                case 1:
                    this.setImg(Sprite.bomb_exploded1.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX() + i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last1.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX() - i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last1.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX(), getY() - i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last1.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical1.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX(), getY() + i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last1.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical1.getFxImage(), getX(), getY() + i * 32);
                        }
                    }

                    break;
                case 2:
                    this.setImg(Sprite.bomb_exploded1.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() + i * 32) / 32] = 0;
                            if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 1) {
                                Item Buff = new x2Spd((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_speed.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 2) {
                                Item Buff = new BombLevelsUp((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_bombs.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 3) {
                                Item Buff = new FireLevelsUp((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_flames.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 4) {
                                Item Buff = new SpdDecrease((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_flamepass.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 5) {
                                Item Buff = new Addlive((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_wallpass.getFxImage());
                                BombermanGame.itemsList.add(Buff);
                            }
                            break;
                        }

                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) < 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) < 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX() + i * 32) - e.getX()) < 29 && Math.abs((getY()) - e.getY()) < 29) {
                                    e.dead();
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                }
                            }
                        }

                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last2.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            if (Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                                Levels.Maps.get(BombermanGame.levels)[(getY()) / 32][(getX() - i * 32) / 32] = 0;
                                if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 1) {
                                    Item Buff = new x2Spd((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 2) {
                                    Item Buff = new BombLevelsUp((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 3) {
                                    Item Buff = new FireLevelsUp((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 4) {
                                    Item Buff = new SpdDecrease((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 5) {
                                    Item Buff = new Addlive((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) < 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) < 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX() - i * 32) - e.getX()) < 29 && Math.abs((getY()) - e.getY()) < 29) {
                                    e.dead();
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last2.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            if (Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                                Levels.Maps.get(BombermanGame.levels)[(getY() - i * 32) / 32][(getX()) / 32] = 0;
                                if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 1) {
                                    Item Buff = new x2Spd(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 2) {
                                    Item Buff = new BombLevelsUp(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 3) {
                                    Item Buff = new FireLevelsUp(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 4) {
                                    Item Buff = new SpdDecrease(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 5) {
                                    Item Buff = new Addlive(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) < 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) < 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX()) - e.getX()) <= 29 && Math.abs((getY() - i * 32) - e.getY()) <= 29) {
                                    e.dead();
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last2.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels2; i++) {
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 1 || Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            if (Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                                Levels.Maps.get(BombermanGame.levels)[(getY() + i * 32) / 32][(getX()) / 32] = 0;
                                if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 1) {
                                    Item Buff = new x2Spd(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 2) {
                                    Item Buff = new BombLevelsUp(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 3) {
                                    Item Buff = new FireLevelsUp(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 4) {
                                    Item Buff = new SpdDecrease(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 5) {
                                    Item Buff = new Addlive(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(Buff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman2.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman2.getY()) < 29) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman2.getX() - getX()) < 4) && (Math.abs(BombermanGame.bomberman2.getY()) - getY()) < 4) {
                            if (!BombermanGame.bomberman2.isInvisible()) {
                                BombermanGame.bomberman2.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman2.setDead(true);
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman1.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman1.getY()) < 29) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman1.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman1.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman1.isInvisible()) {
                                BombermanGame.bomberman1.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman1.setDead(true);
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX()) - e.getX()) < 29 && Math.abs((getY() + i * 32) - e.getY()) < 29) {
                                    e.dead();
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels2) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last2.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(), getX(), getY() + i * 32);
                        }
                    }
                case 3:
                    if (BombermanGame.bomberman2.isDead()) {
                        BombermanGame.bomberman2.reborn();
                    }
                    if (BombermanGame.bomberman1.isDead()) {
                        BombermanGame.bomberman1.reborn();
                    }
                    exploded2 = false;
                    BombermanGame.bomberman2.render(BombermanGame.gc);
                    Levels.Maps.get(BombermanGame.levels)[getY() / 32][getX() / 32] = 0;
                    Levels.updateStillObject(Levels.Maps.get(BombermanGame.levels));
                    PlayerSet2 = false;
                    BombermanGame.bombs.remove(this);
                    break;
            }
        }
    }


    // bug tia lửa chèn bom do xung đột draw
    // tương tác hỏa lực với mọi object,
    //optional move cho quái và tương tác người chạm quái
}
