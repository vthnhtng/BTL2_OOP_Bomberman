package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu {
    public static final double fontSize = 32;
    private static ImageView statusGame;
    private static ImageView statusGame1;
    private static ImageView statusGame2;
    private static ImageView statusGame3;

    private static ImageView authorView;
    public static void creatMenu(Group root) {
        //Background
        Image author = new Image("textures/MainMenu.png");
        authorView = new ImageView(author);
        authorView.setX(0);
        authorView.setY(0);
        authorView.setScaleX(1);
        authorView.setScaleY(1);
        root.getChildren().add(authorView);
        //Button play
        Image newGame = new Image("textures/PLay.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(419);
        statusGame.setY(225);
        statusGame.setScaleX(1);
        statusGame.setScaleY(0.8);
        root.getChildren().add(statusGame);

        //Button play
        Image newGame1 = new Image("textures/Tutorial.png");
        statusGame1 = new ImageView(newGame1);
        statusGame1.setX(419);
        statusGame1.setY(310);
        statusGame1.setScaleX(1);
        statusGame1.setScaleY(0.8);
        root.getChildren().add(statusGame1);

        //Button play
        Image newGame2 = new Image("textures/button_exit.png");
        statusGame2 = new ImageView(newGame2);
        statusGame2.setX(419);
        statusGame2.setY(395);
        statusGame2.setScaleX(1);
        statusGame2.setScaleY(0.8);
        root.getChildren().add(statusGame2);
        //color
        Image newColor = new Image("textures/pause.png");
        statusGame3 = new ImageView(newColor);
        statusGame3.setX(0);
        statusGame3.setY(0);
        statusGame3.setScaleX(1);
        statusGame3.setScaleY(1);
        //button pause
        Text startText = new Text();
        startText.setText("PAUSE");
        startText.setFont(Font.font("Verdana", fontSize+5));
        startText.setX(40);
        startText.setY(45);
        startText.setFill(Color.LAVENDERBLUSH);

        //button time
        Text startText2 = new Text();
        startText2.setText("TIME: ");
        startText2.setFont(Font.font("Verdana", fontSize - 5));
        startText2.setX(400);
        startText2.setY(30);
        startText2.setFill(Color.LAVENDERBLUSH);

        //button pause
        Text startText3 = new Text();
        startText3.setText("ALIVE: ");
        startText3.setFont(Font.font("Verdana", fontSize-5));
        startText3.setX(700);
        startText3.setY(30);
        startText3.setFill(Color.LAVENDERBLUSH);

        //button continue
        Text startText1 = new Text();
        startText1.setText("CONTINUE");
        startText1.setFont(Font.font("Verdana", fontSize+5));
        startText1.setX(20);
        startText1.setY(45);
        startText1.setFill(Color.LAVENDERBLUSH);
        //play game
        statusGame.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(BombermanGame.gc.getCanvas());
            root.getChildren().add(startText);
            root.getChildren().add(startText2);
            root.getChildren().add(startText3);
            BombermanGame.gameStart = true;
        });

        //continue game
        startText.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(BombermanGame.gc.getCanvas());
            root.getChildren().add(startText1);
            root.getChildren().add(startText2);
            root.getChildren().add(startText3);
            BombermanGame.pause = true;
        });

        //pause game
        startText1.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(BombermanGame.gc.getCanvas());
            root.getChildren().add(startText);
            root.getChildren().add(startText2);
            root.getChildren().add(startText3);
            BombermanGame.pause = false;
        });
        //tutorial
        statusGame1.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(authorView);
        });
        //exit game
        statusGame2.setOnMouseClicked(event -> {
            root.getChildren().clear();
           System.exit(0);
        });

//        if(BombermanGame.bomberman.isDead()){
//            System.out.println(BombermanGame.bomberman.isDead());
//            root.getChildren().add(authorView);
//            root.getChildren().add(statusGame);
//            root.getChildren().add(statusGame1);
//            root.getChildren().add(statusGame2);
//        }

    }
}
