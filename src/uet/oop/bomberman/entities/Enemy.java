package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Levels;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Enemy extends DynamicEntity {
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.speed = 1;
        moveDOWN = true;
        moveRIGHT = false;
        moveLEFT = false;
        moveUP = false;

    }

    public void bombsCheck() {

    }

    public void randomMoving() {

    }

    @Override
    public void update() {
        if (!isDead) {
            if (Math.abs(getX() - BombermanGame.bomberman1.getX()) < 31 && Math.abs(getY() - BombermanGame.bomberman1.getY()) < 31) {
                if (!BombermanGame.bomberman1.invisible) {
                    if (!this.isDead()) {
                        BombermanGame.bomberman1.dead();
                    }
                }
            }
            if (BombermanGame.Player2 == true) {
                if (Math.abs(getX() - BombermanGame.bomberman2.getX()) < 31 && Math.abs(getY() - BombermanGame.bomberman2.getY()) < 31) {
                    if (!BombermanGame.bomberman2.invisible) {
                        if (!this.isDead()) {
                            BombermanGame.bomberman2.dead();
                        }
                    }
                }
            }
            if (moveDOWN) {
                if (canMove_Down(getX(), getY(), Levels.Maps.get(BombermanGame.levels)) || can_Move_Down_2(getX(), getY())) {
                    moveDOWN();
                } else {
                    moveDOWN = false;
                    switch (new Random().nextInt(3)) {
                        case 0:
                            moveUP = true;
                            moveLEFT = false;
                            moveRIGHT = false;
                            break;
                        case 1:
                            moveUP = false;
                            moveLEFT = true;
                            moveRIGHT = false;
                            break;
                        case 2:
                            moveUP = false;
                            moveLEFT = false;
                            moveRIGHT = true;
                            break;
                    }
                }
            }
            if (moveUP) {
                if (canMove_Up(getX(), getY(), Levels.Maps.get(BombermanGame.levels)) || can_Move_Up_2(getX(), getY())) {
                    moveUP();
                } else {
                    moveUP = false;
                    switch (new Random().nextInt(3)) {
                        case 0:
                            moveDOWN = true;
                            moveLEFT = false;
                            moveRIGHT = false;
                            break;
                        case 1:
                            moveDOWN = false;
                            moveLEFT = true;
                            moveRIGHT = false;
                            break;
                        case 2:
                            moveDOWN = false;
                            moveLEFT = false;
                            moveRIGHT = true;
                            break;
                    }
                }
            }
            if (moveLEFT) {
                if (canMove_Left(getX(), getY(), Levels.Maps.get(BombermanGame.levels)) || can_Move_Left_2(getX(), getY())) {
                    moveLEFT();
                    this.setImg(Sprite.balloom_left1.getFxImage());
                } else {
                    moveLEFT = false;
                    switch (new Random().nextInt(3)) {
                        case 0:
                            moveDOWN = true;
                            moveUP = false;
                            moveRIGHT = false;
                            break;
                        case 1:
                            moveDOWN = false;
                            moveUP = true;
                            moveRIGHT = false;
                            break;
                        case 2:
                            moveDOWN = false;
                            moveUP = false;
                            moveRIGHT = true;
                            break;
                    }
                }
            }
            if (moveRIGHT) {
                if (canMove_Right(getX(), getY(), Levels.Maps.get(BombermanGame.levels)) || can_Move_Right_2(getX(), getY())) {
                    moveRIGHT();
                    this.setImg(Sprite.balloom_right1.getFxImage());
                } else {
                    moveRIGHT = false;
                    switch (new Random().nextInt(3)) {
                        case 0:
                            moveDOWN = true;
                            moveLEFT = false;
                            moveUP = false;
                            break;
                        case 1:
                            moveDOWN = false;
                            moveLEFT = true;
                            moveUP = false;
                            break;
                        case 2:
                            moveDOWN = false;
                            moveLEFT = false;
                            moveUP = true;
                            break;
                    }
                }
            }

        } else {
            this.setImg(Sprite.balloom_dead.getFxImage());
            frame++;
            if (frame > interval_dead) {
                BombermanGame.movingEntities.remove(this);
            }
        }
    }


    @Override
    public void reborn() {

    }

    @Override
    public void dead() {
        live_enemy--;
        if (live_enemy == 0) {
            isDead = true;
        }

    }

}
