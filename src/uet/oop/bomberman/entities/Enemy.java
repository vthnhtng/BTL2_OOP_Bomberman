package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
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

    private int count = 0;

    @Override
    public void update() {
        if(!isDead) {
            if (moveDOWN) {
                if (canMove_Down(getX(), getY()) || can_Move_Down_2(getX(), getY())) {
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
                if (canMove_Up(getX(), getY()) || can_Move_Up_2(getX(), getY())) {
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
                if (canMove_Left(getX(), getY()) || can_Move_Left_2(getX(), getY())) {
                    moveLEFT();
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
                if (canMove_Right(getX(), getY()) || can_Move_Right_2(getX(), getY())) {
                    moveRIGHT();
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
            if (Math.abs(getX() - BombermanGame.bomberman.getX()) < 31 && Math.abs(getY() - BombermanGame.bomberman.getY()) < 31) {
                if (!BombermanGame.bomberman.invisible) {
                    if (!this.isDead()) {
                        BombermanGame.bomberman.dead();
                    }
                }
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
        frame_dead++;
        if (frame_dead >= interval_dead) {
            index_animation_dead++;
            frame_dead = 0;
            if (index_animation_dead >= 3) {
                index_animation_dead = 0;
            }
        }
        switch (index_animation_dead) {
            case 0:
                this.setImg(Sprite.balloom_dead.getFxImage());
                break;

        }
    }

}
