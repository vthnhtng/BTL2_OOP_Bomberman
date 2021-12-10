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
import uet.oop.bomberman.entities.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

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
    boolean running, moveUP, moveDOWN, moveLEFT, moveRIGHT;
    boolean moving;
    public static boolean gameStart = false;
    public static final int WIDTH = 34;
    public static final int HEIGHT = 22;
    public static final int rows = 20;
    public static final int columns = 34;
    public static GraphicsContext gc;
    private Canvas canvas;

    public static List<Bomb> bombs = new ArrayList<>();
    public static List<DynamicEntity> movingEntities = new ArrayList<>(); // lưu các entities động
    public static List<StillEntity> stillObjects = new ArrayList<>(); // lưu các entities tĩnh khi khởi tạo map
    public static List<Item> itemsList = new ArrayList<>();
    //public static lis

    double x, y;
    public static int countBomb = 0;
    public static int bombLevels = 5; // max 5
    public static int fireLevels = 30; // max 5

    public static DynamicEntity bomberman = new Bomber(1, 5, Sprite.bomman_down.getFxImage());
    public static DynamicEntity bomberman1 = new Bomber(1, 2, Sprite.bomman2_down.getFxImage());
    //public static DynamicEntity enemy = new Enemy(8, 6, Sprite.balloom_left3.getFxImage());

    public static int[][] level1 = new int[][]{ // nang cap lay tu file
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
            {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 2, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 2, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 2, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 2, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 2, 0, 0, 1, 1, 1, 1, 1, 2, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 3, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public static int[][] items = new int[34][20];

    @Override
    public void start(Stage stage) throws Exception {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();

        // Tao root container
        // Group root = new Group();
        //  root.getChildren().add(canvas);
        // Tao scene
        //  root.getChildren().add(authorView);
        Menu.creatMenu(root);
        Scene scene = new Scene(root);
        //  root.getChildren().add(statusGame);

        movingEntities.add(bomberman);
        //movingEntities.add(bomberman1);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (gameStart) {
                    if (!pause){
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

                            case SPACE:
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
                                        bomb.setImg(Sprite.bomb.getFxImage());
                                        bombs.add(bomb);
                                        countBomb++;
                                    }
                                }
                                break;
                            case TAB:
                                if (!BombermanGame.bomberman.isInvisible()) {
                                    BombermanGame.bomberman.setInvisible(true);
                                } else {
                                    BombermanGame.bomberman.setInvisible(false);
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

        createMap();

    }

    public void Random_Brick(int[][] level1) {
        for (int i = 0; i < rows; i++) { // cap nhat check dieu kien, spawn quai
            for (int j = 0; j < columns; j++) {
                if (level1[i][j] == 0) {
                    if (new Random().nextInt(10) < 4) {
                        level1[i][j] = 4;
                    }
                }
            }
        }
        level1[5][1] = 0;
        level1[5][2] = 0;
        level1[4][1] = 0;
        level1[4][2] = 0;
    }

    public static int numOfMobs = 6;
    public void Random_Enemy(int[][] level1, int index){
        int area = index * columns/numOfMobs;
        int a;
        int b;
        while(true){
            a = (int)Math.floor(Math.random()*((area+columns/numOfMobs)-area+1)+area);
            b = (int)Math.floor(Math.random()*(18-0+1)+0);
            if(level1[b][a] == 0){
                DynamicEntity balloom7 = new Enemy(a, b, Sprite.balloom_left1.getFxImage());
                movingEntities.add(balloom7);
                break;
            }
        }
    }

    public void Random_items(int[][] levels) {
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; i < columns; j ++) {
                if (levels[j][i] == 4) {
                    if (new Random().nextInt(10) > 3) {
                        switch (new Random().nextInt(5)) {
                            case 1: items[j][i] = 1; break;
                            case 2: items[j][i] = 2; break;
                            case 3: items[j][i] = 3; break;
                            case 4: items[j][i] = 4; break;
                            case 5: items[j][i] = 5; break;
                        }
                    }
                }
            }
        }
    }

    public void createMap() {
        try {
            Random_Brick(level1);
            for (int i = 0; i < numOfMobs; i ++) {
                Random_Enemy(level1, i);
            }
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    StillEntity object;
                    if (level1[j][i] == 1) {
                        object = new Wall(i, j, Sprite.Wall_Stone.getFxImage());
                    } else if (level1[j][i] == 2) {
                        object = new Wall(i, j, Sprite.Wood_blue.getFxImage());
                    } else if (level1[j][i] == 3) {
                        object = new Wall(i, j, Sprite.Wall_Wood.getFxImage());
                    } else if (level1[j][i] == 4) {
                        object = new Grass(i, j, Sprite.Brick_1.getFxImage());
                    } else if (level1[j][j] == 7) {
                        object = new Grass(i, j, Sprite.Brick_2.getFxImage());
                    } else {
                        object = new Grass(i, j, Sprite.Grass_default.getFxImage());
                    }

                    stillObjects.add(object);
                }
            }
            Random_items(level1);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }

    public static void updateStillObject() {
        List<StillEntity> newStillObjects = new ArrayList<>();
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                StillEntity object;
                if (level1[j][i] == 1) {
                    object = new Wall(i, j, Sprite.Wall_Stone.getFxImage());
                } else if (level1[j][i] == 2) {
                    object = new Wall(i, j, Sprite.Wood_blue.getFxImage());
                } else if (level1[j][i] == 3) {
                    object = new Wall(i, j, Sprite.Wall_Wood.getFxImage());
                } else if (level1[j][i] == 4) {
                    object = new Grass(i, j, Sprite.Brick_1.getFxImage());
                } else {
                    object = new Grass(i, j, Sprite.Grass_default.getFxImage());
                }

                newStillObjects.add(object);
            }
        }
        stillObjects = newStillObjects;
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
