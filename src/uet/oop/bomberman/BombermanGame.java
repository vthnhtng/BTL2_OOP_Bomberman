package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Item.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Map.Levels;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class BombermanGame extends Application {
    public static boolean pause = false;
    public static boolean gameStart = false;
    public static final int WIDTH = 34;
    public static final int HEIGHT = 22;
    public static GraphicsContext gc;
    private Canvas canvas;
    public static int levels = 3;
    public static List<Bomb> bombs = new ArrayList<>();
    public static List<DynamicEntity> movingEntities = new ArrayList<>(); // lưu các entities động
    public static List<StillEntity> stillObjects = new ArrayList<>(); // lưu các entities tĩnh khi khởi tạo map
    public static List<Item> itemsList = new ArrayList<>();

    double x, y;
    public static int countBomb = 0;
    public static int bombLevels = 1; // max 5
    public static int fireLevels = 1; // max 5

    public static DynamicEntity bomberman = new Bomber(1, 5, Sprite.bomman_down.getFxImage());
    public static DynamicEntity bomberman1 = new Bomber(1, 2, Sprite.bomman2_down.getFxImage());
    public static int[][] items = new int[34][20];

    @Override
    public void start(Stage stage) throws Exception {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        Menu.creatMenu(root);
        Scene scene = new Scene(root);

        movingEntities.add(bomberman);
        movingEntities.add(bomberman1);
        Levels.Maps.add(Levels.level1);
        Levels.Maps.add(Levels.level2);
        Levels.Maps.add(Levels.level3);
        Levels.Maps.add(Levels.PVPMode);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (gameStart) {
                    if (!pause) {
                        switch (event.getCode()) {
                            case UP:
                                bomberman.moveUP = true;
                                break;
                            case DOWN:
                                bomberman.moveDOWN = true;
                                break;
                            case LEFT:
                                bomberman.moveLEFT = true;
                                break;
                            case RIGHT:
                                bomberman.moveRIGHT = true;
                                break;
                            case W:
                                bomberman1.moveUP = true;
                                break;
                            case S:
                                bomberman1.moveDOWN = true;
                                break;
                            case A:
                                bomberman1.moveLEFT = true;
                                break;
                            case D:
                                bomberman1.moveRIGHT = true;
                                break;
                            case TAB:
                                if (!BombermanGame.bomberman.isInvisible()) {
                                    BombermanGame.bomberman.setInvisible(true);
                                } else {
                                    BombermanGame.bomberman.setInvisible(false);
                                }
                                if (bombLevels < 10) {
                                    bombLevels = 10;
                                }
                                if (fireLevels < 10) {
                                    fireLevels = 10;
                                }
                        }

                    }
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (gameStart) {
                    if (!pause) {
                        switch (event.getCode()) {
                            case UP:
                                bomberman.moveUP = false;
                                break;
                            case DOWN:
                                bomberman.moveDOWN = false;
                                break;
                            case LEFT:
                                bomberman.moveLEFT = false;
                                break;
                            case RIGHT:
                                bomberman.moveRIGHT = false;
                                break;
                            case W:
                                bomberman1.moveUP = false;
                                break;
                            case S:
                                bomberman1.moveDOWN = false;
                                break;
                            case A:
                                bomberman1.moveLEFT = false;
                                break;
                            case D:
                                bomberman1.moveRIGHT = false;
                                break;
                            case SPACE:
                                if (countBomb < bombLevels) {
                                    if (!bomberman1.isDead()) {
                                        Bomb bomb = new Bomb();
                                        int bombX = bomberman1.getX() / 32;
                                        int bombY = bomberman1.getY() / 32;
                                        if (bomberman1.getY() % 32 >= 16) {
                                            bombY += 1;
                                            bombY *= 32;
                                        } else {
                                            bombY *= 32;
                                        }
                                        if (bomberman1.getX() % 32 >= 16) {
                                            bombX += 1;
                                            bombX *= 32;
                                        } else {
                                            bombX *= 32;
                                        }
                                        bomb.setSet(true);
                                        bomb.setX(bombX);
                                        bomb.setY(bombY);
                                        bomb.setImg(Sprite.bomb.getFxImage());
                                        bombs.add(bomb);
                                        countBomb++;
                                    }
                                }
                                break;
                            case ENTER:
                                if (countBomb < bombLevels) {
                                    if (!bomberman.isDead()) {
                                        Bomb bomb = new Bomb();
                                        int bombX = bomberman.getX() / 32;
                                        int bombY = bomberman.getY() / 32;
                                        if (bomberman.getY() % 32 >= 16) {
                                            bombY += 1;
                                            bombY *= 32;
                                        } else {
                                            bombY *= 32;
                                        }
                                        if (bomberman.getX() % 32 >= 16) {
                                            bombX += 1;
                                            bombX *= 32;
                                        } else {
                                            bombX *= 32;
                                        }
                                        bomb.setSet(true);
                                        bomb.setX(bombX);
                                        bomb.setY(bombY);
                                        bombs.add(bomb);
                                        countBomb++;
                                    }
                                }
                                break;
                        }
                    }
                }
            }

        });


        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() { // chay tung mili s mot
            @Override
            public void handle(long now) {
                try {
                    if (!pause) {
                        render();
                        update();

                    }
                } catch (ConcurrentModificationException e) {
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        };
        timer.start();

        Levels.createLv1(Levels.Maps.get(levels));

    }


    public void update() {
        movingEntities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);
        bombs.forEach(Bomb::update);
        itemsList.forEach(Item::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        movingEntities.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        itemsList.forEach(g -> g.render(gc));
    }


}
