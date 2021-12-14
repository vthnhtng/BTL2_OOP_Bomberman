package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Map.Levels;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class BombermanGame extends Application {
    public static Group root = new Group();
    public static boolean pause = false;
    public static boolean gameStart = false;
    public static final int WIDTH = 34;
    public static final int HEIGHT = 22;
    public static GraphicsContext gc;
    private Canvas canvas;
    public static int levels = 0;
    public static List<Bomb> bombs = new ArrayList<>();
    public static List<DynamicEntity> movingEntities = new ArrayList<>(); // lưu các entities động
    public static List<StillEntity> stillObjects = new ArrayList<>(); // lưu các entities tĩnh khi khởi tạo map
    public static List<Item> itemsList = new ArrayList<>();

    public static int enemyCount = 0;
    //chon nvat
    public static boolean player1 = false;
    public static boolean player2 = true;
    public static boolean player3 = false;


    public static boolean player11 = false;
    public static boolean player22 = false;
    public static boolean player33 = false;

    public static boolean PvPMode = false;
    public static boolean cheat = false;
    public static DynamicEntity bomberman1 = new Bomber();
    public static DynamicEntity bomberman2 = new Bomber();
    public static int[][] items = new int[40][40];


    public static int countBomb1 = 0;
    public static int bombLevels1 = 1; // max 5
    public static int fireLevels1 = 1; // max 5

    public static int countBomb2 = 0;
    public static int bombLevels2 = 1; // max 5
    public static int fireLevels2 = 1; // max 5

    public static Clip clip;
    @Override
    public void start(Stage stage) throws IOException, RuntimeException, LineUnavailableException, UnsupportedAudioFileException {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Menu.creatMenu(root);
        Scene scene = new Scene(root);
        File file = new File("res\\Music.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        Levels.Maps.add(Levels.level1);
        Levels.Maps.add(Levels.level2);
        Levels.Maps.add(Levels.level3);
        Levels.Maps.add(Levels.PvPMode);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (gameStart) {
                    if (!pause) {
                        switch (event.getCode()) {
                            case UP:
                                if (PvPMode == true) {
                                    bomberman2.moveUP = true;
                                }
                                break;
                            case DOWN:
                                if (PvPMode == true) {
                                    bomberman2.moveDOWN = true;
                                }
                                break;
                            case LEFT:
                                if (PvPMode == true) {
                                    bomberman2.moveLEFT = true;
                                }
                                break;
                            case RIGHT:
                                if (PvPMode == true) {
                                    bomberman2.moveRIGHT = true;
                                }
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
                                if (!BombermanGame.bomberman1.isInvisible()) {
                                    BombermanGame.bomberman1.setInvisible(true);
                                } else {
                                    BombermanGame.bomberman1.setInvisible(false);
                                }
                                if (bombLevels1 < 10) {
                                    bombLevels1 = 10;
                                }
                                if (fireLevels1 < 10) {
                                    fireLevels1 = 10;
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
                                bomberman2.moveUP = false;
                                break;
                            case DOWN:
                                bomberman2.moveDOWN = false;
                                break;
                            case LEFT:
                                bomberman2.moveLEFT = false;
                                break;
                            case RIGHT:
                                bomberman2.moveRIGHT = false;
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
                                if (countBomb1 < bombLevels1) {
                                    if (!bomberman1.isDead()) {
                                        Bomb bomb1 = new Bomb();
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
                                        bomb1.setPlayerSet1(true);
                                        bomb1.setX(bombX);
                                        bomb1.setY(bombY);
                                        bomb1.setImg(Sprite.bomb.getFxImage());
                                        bombs.add(bomb1);
                                        countBomb1++;
                                    }
                                }
                                break;
                            case ENTER:
                                if (PvPMode == true) {
                                    if (countBomb2 < bombLevels2) {
                                        if (!bomberman2.isDead()) {
                                            Bomb bomb2 = new Bomb();
                                            int bombX = bomberman2.getX() / 32;
                                            int bombY = bomberman2.getY() / 32;
                                            if (bomberman2.getY() % 32 >= 16) {
                                                bombY += 1;
                                                bombY *= 32;
                                            } else {
                                                bombY *= 32;
                                            }
                                            if (bomberman2.getX() % 32 >= 16) {
                                                bombX += 1;
                                                bombX *= 32;
                                            } else {
                                                bombX *= 32;
                                            }
                                            bomb2.setPlayerSet2(true);
                                            bomb2.setX(bombX);
                                            bomb2.setY(bombY);
                                            bombs.add(bomb2);
                                            countBomb2++;
                                        }
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
