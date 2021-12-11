package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.entities.Item.x2Spd;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends StillEntity {
    public boolean exploded;
    int countToExplode = 0;
    int intervalToExplode = 4;
    boolean set;

    int frameExplosion = 0;
    int intervalExplode = 1;
    int indexExplosionAnimation = 0;

    public Bomb() {
    }

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void setSet(boolean set) {
        this.set = set;
    }

    @Override
    public void update() {
        if (set) {
            explosion();
        }
    }

    public void explosion() {
        frameBomb++;
        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) > 28) || (Math.abs(BombermanGame.bomberman.getY() - getY())) > 28) {
            BombermanGame.level1[getY() / 32][getX() / 32] = 9;
        }

        if (frameBomb >= intervalBomb) {
            frameBomb = 0;
            indexAnimation++;
            if (indexAnimation > 2) {  // chay het hoat anh 8 lai tro ve hoat anh 1
                indexAnimation = 0;
                countToExplode++;
            }
        }
        switch (indexAnimation) {
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

        if (countToExplode >= intervalToExplode) {
            exploded = true;
            countToExplode = 0;
            BombermanGame.countBomb--;
        }

        if (exploded) {
            frameExplosion++;
            if (frameExplosion >= intervalExplode) {
                frameExplosion = 0;
                indexExplosionAnimation++;
                if (indexExplosionAnimation > 3) {
                    indexExplosionAnimation = 0;
                }
            }
            switch (indexExplosionAnimation) {
                case 0:
                    this.setImg(Sprite.bomb_exploded.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 1 || BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX() + i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }

                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 1 || BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX() - i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 1 || BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX(), getY() - i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 1 || BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded.getFxImage(), getX(), getY() + i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead1.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical.getFxImage(), getX(), getY() + i * 32);
                        }
                    }


                    break;

                case 1:
                    this.setImg(Sprite.bomb_exploded1.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 1 || BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX() + i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last1.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 1 || BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX() - i * 32, getY());
                            break;
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last1.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal1.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 1 || BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX(), getY() - i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last1.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical1.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 1 || BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            BombermanGame.gc.drawImage(Sprite.brick_exploded1.getFxImage(), getX(), getY() + i * 32);
                            break;
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead2.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last1.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical1.getFxImage(), getX(), getY() + i * 32);
                        }
                    }

                    break;
                case 2:
                    this.setImg(Sprite.bomb_exploded1.getFxImage());
                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 1 || BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] == 4) {
                            BombermanGame.level1[(getY()) / 32][(getX() + i * 32) / 32] = 0;
                            if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 1) {
                                Item x2SpdBuff = new x2Spd((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_speed.getFxImage());
                                BombermanGame.itemsList.add(x2SpdBuff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 2) {
                                Item x2SpdBuff = new x2Spd((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_bombs.getFxImage());
                                BombermanGame.itemsList.add(x2SpdBuff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 3) {
                                Item x2SpdBuff = new x2Spd((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_flames.getFxImage());
                                BombermanGame.itemsList.add(x2SpdBuff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 4) {
                                Item x2SpdBuff = new x2Spd((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_flamepass.getFxImage());
                                BombermanGame.itemsList.add(x2SpdBuff);
                            } else if (BombermanGame.items[getY() / 32][(getX() + i * 32) / 32] == 5) {
                                Item x2SpdBuff = new x2Spd((getX() + i * 32) / 32, getY() / 32, Sprite.powerup_wallpass.getFxImage());
                                BombermanGame.itemsList.add(x2SpdBuff);
                            }
                            break;
                        }

                        if (Math.abs((getX() + i * 32) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX() + i * 32) - e.getX()) < 29 && Math.abs((getY()) - e.getY()) < 29) {
                                    e.dead();
                                    BombermanGame.movingEntities.remove(e);
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                    BombermanGame.movingEntities.remove(e);
                                }
                            }
                        }

                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_right_last2.getFxImage(), getX() + i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), getX() + i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 1 || BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                            if (BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] == 4) {
                                BombermanGame.level1[(getY()) / 32][(getX() - i * 32) / 32] = 0;
                                if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 1) {
                                    Item x2SpdBuff = new x2Spd((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 2) {
                                    Item x2SpdBuff = new x2Spd((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 3) {
                                    Item x2SpdBuff = new x2Spd((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                }  else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 4) {
                                    Item x2SpdBuff = new x2Spd((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[getY() / 32][(getX() - i * 32) / 32] == 5) {
                                    Item x2SpdBuff = new x2Spd((getX() - i * 32) / 32, getY() / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX() - i * 32) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY()) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX() - i * 32) - e.getX()) < 29 && Math.abs((getY()) - e.getY()) < 29) {
                                    e.dead();
                                    BombermanGame.movingEntities.remove(e);
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                    BombermanGame.movingEntities.remove(e);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal_left_last2.getFxImage(), getX() - i * 32, getY());
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_horizontal2.getFxImage(), getX() - i * 32, getY());
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 1 || BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                            if (BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] == 4) {
                                BombermanGame.level1[(getY() - i * 32) / 32][(getX()) / 32] = 0;
                                if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 1) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 2) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 3) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 4) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[(getY() - i * 32) / 32][getX() / 32] == 5) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() - i * 32) / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY() - i * 32) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX()) - e.getX()) <= 29 && Math.abs((getY() - i * 32) - e.getY()) <= 29) {
                                    e.dead();
                                    BombermanGame.movingEntities.remove(e);
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                    BombermanGame.movingEntities.remove(e);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_top_last2.getFxImage(), getX(), getY() - i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(), getX(), getY() - i * 32);
                        }
                    }

                    for (int i = 1; i <= BombermanGame.fireLevels; i++) {
                        if (BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 1 || BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 2) {
                            break;
                        }
                        if (BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                            if (BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] == 4) {
                                BombermanGame.level1[(getY() + i * 32) / 32][(getX()) / 32] = 0;
                                if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 1) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_speed.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 2) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_bombs.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 3) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_flames.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 4) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_flamepass.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                } else if (BombermanGame.items[(getY() + i * 32) / 32][getX() / 32] == 5) {
                                    Item x2SpdBuff = new x2Spd(getX() / 32, (getY() + i * 32) / 32, Sprite.powerup_wallpass.getFxImage());
                                    BombermanGame.itemsList.add(x2SpdBuff);
                                }
                                break;
                            }
                        }
                        if (Math.abs((getX()) - BombermanGame.bomberman.getX()) < 29 && Math.abs((getY() + i * 32) - BombermanGame.bomberman.getY()) < 29) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        if ((Math.abs(BombermanGame.bomberman.getX() - getX()) <= 4) && (Math.abs(BombermanGame.bomberman.getY()) - getY()) <= 4) {
                            if (!BombermanGame.bomberman.isInvisible()) {
                                BombermanGame.bomberman.setImg(Sprite.player_dead3.getFxImage());
                                BombermanGame.bomberman.setDead(true);
                            }
                        }
                        for (DynamicEntity e : BombermanGame.movingEntities) {
                            if (e instanceof Enemy) {
                                if (Math.abs((getX()) - e.getX()) < 29 && Math.abs((getY() + i * 32) - e.getY()) < 29) {
                                    e.dead();
                                    BombermanGame.movingEntities.remove(e);
                                }
                                if ((Math.abs(e.getX() - getX()) <= 2) && (Math.abs(e.getY()) - getY()) <= 2) {
                                    e.dead();
                                    BombermanGame.movingEntities.remove(e);
                                }
                            }
                        }
                        if (i == BombermanGame.fireLevels) {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical_down_last2.getFxImage(), getX(), getY() + i * 32);
                        } else {
                            BombermanGame.gc.drawImage(Sprite.explosion_vertical2.getFxImage(), getX(), getY() + i * 32);
                        }
                    }
                case 3:
                    if (BombermanGame.bomberman.isDead()) {
                        BombermanGame.bomberman.reborn();
                    }
                    exploded = false;
                    BombermanGame.bomberman.render(BombermanGame.gc);
                    BombermanGame.level1[getY() / 32][getX() / 32] = 0;
                    BombermanGame.updateStillObject();
                    set = false;
                    BombermanGame.bombs.remove(this);
                    break;
            }
        }
    }
    // bug tia lửa chèn bom do xung đột draw
    // tương tác hỏa lực với mọi object,
    //optional move cho quái và tương tác người chạm quái
}
