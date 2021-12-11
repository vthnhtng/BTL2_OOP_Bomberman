package uet.oop.bomberman.entities;

import javafx.animation.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class DynamicEntity extends Entity {
    public boolean moving;

    //public final double default speed = 0.05;

    int frame = 0; // biến check tg khi giữ phím
    double interval = 6; // giữ phím trong khoảng 5 mil s thì chuyển animation
    int indexAnimation = 0;// lấy sprite từ 1 -> 3;

    protected int frame_dead = 0;
    protected int interval_dead = 10;
    protected int index_animation_dead = 0;
    protected boolean isDead = false;

    public boolean isDead() {
        return isDead;
    }

    public int getLive() {
        return live;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    protected boolean invisible = false;
    protected int live = 3;
    protected int live_enemy = 1;
    public boolean moveRIGHT;
    public boolean moveLEFT;
    public boolean moveUP;
    public boolean moveDOWN;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int speed = 2;
    public int start_X = 32;
    public int start_Y = 96;

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public abstract void dead();

    //di chuyển qua trái
    public boolean canMove_Left(int x, int y, int[][] level1) {
        //lấy pixel 1 ô
        int scaleSize = Sprite.SCALED_SIZE;
        // lấy index vị trí hiện tại theo j , i
        int x1 = x / scaleSize; // 1
        int y1 = y / scaleSize; // 3

        // lấy index ô bên trái theo j, i
        int x2 = (x - scaleSize) / scaleSize; // 0
        int y2 = y / scaleSize;   // 3
        // lấy index ô bên trái dưới theo j, i
        int x3 = (x - scaleSize) / scaleSize;  // 0
        int y3 = (y + scaleSize) / scaleSize;  // 4

        if (y % scaleSize == 0) {
            return !((level1[y2][x2] == 1 || level1[y2][x2] == 2 || level1[y2][x2] == 4 || level1[y2][x2] == 9) ||
                    (level1[y1][x1] == 1 || level1[y1][x1] == 2 || level1[y1][x1] == 4 || level1[y1][x1] == 9));

        } else {
            return !((level1[y2][x2] == 1 || level1[y2][x2] == 2 || level1[y2][x2] == 4 || level1[y2][x2] == 9) ||
                    (level1[y1][x1] == 1 || level1[y1][x1] == 2 || level1[y1][x1] == 4 || level1[y1][x1] == 9) ||
                    (level1[y3][x3] == 1 || level1[y3][x3] == 2 || level1[y3][x3] == 4 || level1[y3][x3] == 9));
        }
    }

    public boolean can_Move_Left_2(int x, int y) {
        int scaleSize = Sprite.SCALED_SIZE;

        int x1 = x / scaleSize;
        int x2 = x1 * scaleSize;
        return ((x - x2) <= 36 && (x - x2) > 0);
    }

    //di chuyển qua phải
    public boolean canMove_Right(int x, int y, int[][] level1) {
        //lấy pixel 1 ô
        int scaleSize = Sprite.SCALED_SIZE;
        // lấy index vị trí hiện tại theo j ,
        int x1 = x / scaleSize;
        int y1 = y / scaleSize;

        // lấy index ô bên phải theo j, i
        int x2 = (x + scaleSize) / scaleSize;
        int y2 = y / scaleSize;
        // lấy index ô bên trái dưới theo j, i
        int x3 = (x + scaleSize) / scaleSize;
        int y3 = (y + scaleSize) / scaleSize;

        if (y % scaleSize == 0) {
            return !((level1[y2][x2] == 1 || level1[y2][x2] == 2 || level1[y2][x2] == 4 || level1[y2][x2] == 9) ||
                    (level1[y1][x1] == 1 || level1[y1][x1] == 2 || level1[y1][x1] == 4 || level1[y1][x1] == 9));

        } else {
            return !((level1[y2][x2] == 1 || level1[y2][x2] == 2 || level1[y2][x2] == 4 ||level1[y2][x2] == 9) ||
                    (level1[y1][x1] == 1 || level1[y1][x1] == 2 || level1[y1][x1] == 4 || level1[y1][x1] == 9) ||
                    (level1[y3][x3] == 1 ||level1[y3][x3] == 2 || level1[y3][x3] == 4 || level1[y3][x3] == 9));
        }
    }

    public boolean can_Move_Right_2(int x, int y) {
        int scaleSize = Sprite.SCALED_SIZE;

        int x1 = x / scaleSize;
        int x2 = x1 * scaleSize;
        return ((x - x2) <= 36 && (x - x2) > 0);
    }

    //di chuyển lên trên
    public boolean canMove_Up(int x, int y, int[][] level1) {
        //lấy pixel 1 ô
        int scaleSize = Sprite.SCALED_SIZE;
        // lấy index vị trí hiện tại theo j , i
        int x1 = x / scaleSize; //1
        int y1 = y / scaleSize;  //3

        // lấy index ô bên phải trên theo j,
        int x3 = (x + scaleSize) / scaleSize; //2
        int y3 = (y - scaleSize) / scaleSize; //2
        // lấy index ô bên trên theo j, i
        int x2 = x / scaleSize;
        int y2 = (y - scaleSize) / scaleSize;

        if (x % scaleSize == 0) {
            return !((level1[y2][x2] == 1 || level1[y2][x2] == 2 || level1[y2][x2] == 4 || level1[y2][x2] == 9) ||
                    (level1[y1][x1] == 1 || level1[y1][x1] == 2 || level1[y1][x1] == 4 || level1[y1][x1] == 9));

        } else {
            return !((level1[y2][x2] == 1 || level1[y2][x2] == 2 || level1[y2][x2] == 4 || level1[y2][x2] == 9) ||
                    (level1[y1][x1] == 1 || level1[y1][x1] == 2 || level1[y1][x1] == 4 || level1[y1][x1] == 9) ||
                    (level1[y3][x3] == 1 || level1[y3][x3] == 2 || level1[y3][x3] == 4 || level1[y3][x3] == 9));
        }
    }

    public boolean can_Move_Up_2(int x, int y) {
        int scaleSize = Sprite.SCALED_SIZE;

        int y1 = y / scaleSize;
        int y2 = y1 * scaleSize;
        return ((y - y2) <= 36 && (y - y2) > 0);
    }

    //di chuyển xuống dưới
    public boolean canMove_Down(int x, int y, int[][] level1) {
        //lấy pixel 1 ô
        int scaleSize = Sprite.SCALED_SIZE;
        // lấy index vị trí hiện tại theo j , i
        int x1 = x / scaleSize; // 1
        int y1 = y / scaleSize; // 3

        // lấy index ô bên phải dưới theo j, i
        int x2 = x / scaleSize; //
        int y2 = (y + scaleSize) / scaleSize;
        // lấy index ô bên dưới theo j, i
        int x3 = (x + scaleSize) / scaleSize;
        int y3 = (y + scaleSize) / scaleSize;

        if (x % scaleSize == 0) {
            return !((level1[y2][x2] == 1 || level1[y2][x2] == 2 || level1[y2][x2] == 4 || level1[y2][x2] == 9) ||
                    (level1[y1][x1] == 1 || level1[y1][x1] == 2 || level1[y1][x1] == 4 || level1[y1][x1] == 9));

        } else {
            return !((level1[y2][x2] == 1 || level1[y2][x2] == 2 || level1[y2][x2] == 4 || level1[y2][x2] == 9) ||
                    (level1[y1][x1] == 1 || level1[y1][x1] == 2 || level1[y1][x1] == 4 || level1[y1][x1] == 9) ||
                    (level1[y3][x3] == 1 || level1[y3][x3] == 2 || level1[y3][x3] == 4 || level1[y3][x3] == 9));
        }
    }

    @Override
    public abstract void update();

    public abstract void reborn();

    private boolean isReborn;

    public boolean isReborn() {
        return isReborn;
    }

    public void setReborn(boolean reborn) {
        isReborn = reborn;
    }

    public boolean can_Move_Down_2(int x, int y) {
        int scaleSize = Sprite.SCALED_SIZE;

        int y1 = y / scaleSize;
        int y2 = y1 * scaleSize;
        return ((y - y2) <= 36 && (y - y2) > 0);
    }

    public DynamicEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void moveDOWN() {
        y += speed;
    }

    public void moveUP() {
        y -= speed;
    }

    public void moveRIGHT() {
        x += speed;
    }

    public void moveLEFT() {
        x -= speed;
    }


    // neu dc buff thi toa doa += defaultspeed*2 * Sprite.SCALED_SIZE;
}