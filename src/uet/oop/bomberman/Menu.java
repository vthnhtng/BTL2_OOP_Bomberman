package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import uet.oop.bomberman.Map.Levels;
import uet.oop.bomberman.graphics.Sprite;

import javax.sound.sampled.Clip;

public class Menu {
    public static final double fontSize = 32;
    private static ImageView statusGame;
    private static ImageView statusGame1;
    private static ImageView statusGame2;
    private static ImageView statusGame3;
    private static ImageView statusGame4;
    private static ImageView back;
    private static ImageView settingbgr;
    private static ImageView authorView;
    private static ImageView sound;
    private static ImageView nosound;
    private static ImageView player1;
    private static ImageView player2;
    private static ImageView cheat;
    private static ImageView on;
    private static ImageView off;
    private static ImageView bgrsettingmo;
    private static ImageView character1;
    private static ImageView character2;
    private static ImageView character3;
    private static ImageView character11;
    private static ImageView character22;
    private static ImageView character33;
    private static ImageView character3scale;
    private static ImageView character2scale;
    private static ImageView character1scale;
    private static ImageView character333;
    private static ImageView character222;
    private static ImageView character111;
    private static ImageView save;
    private static ImageView next;
    private static ImageView previous;
    private static ImageView tutorial;
    private static ImageView restart;
    public static Text startText3 = new Text();
    public static Text startText4 = new Text();
    public static Text startText5 = new Text();
    public static int live = 3;


    public static void creatMenu(Group root) {
        Image single23 = new Image("textures/restartt.png");
        restart = new ImageView(single23);
        restart.setX(20);
        restart.setY(10);
        restart.setScaleX(1);
        restart.setScaleY(1);
        //char111
        Image single22 = new Image("textures/tutoriall.png");
        tutorial = new ImageView(single22);
        tutorial.setX(0);
        tutorial.setY(0);
        tutorial.setScaleX(1);
        tutorial.setScaleY(1);
        //char111
        Image single21 = new Image("textures/char33.png");
        character111 = new ImageView(single21);
        character111.setX(315);
        character111.setY(60);
        character111.setScaleX(0.8);
        character111.setScaleY(0.8);
        //char222
        Image single20 = new Image("textures/char22.png");
        character222 = new ImageView(single20);
        character222.setX(660);
        character222.setY(60);
        character222.setScaleX(0.8);
        character222.setScaleY(0.8);
        //char333
        Image single19 = new Image("textures/char11.png");
        character333 = new ImageView(single19);
        character333.setX(40);
        character333.setY(60);
        character333.setScaleX(0.8);
        character333.setScaleY(0.8);
        //char33
        Image single18 = new Image("textures/char11.png");
        character33 = new ImageView(single18);
        character33.setX(50);
        character33.setY(60);
        character33.setScaleX(0.8);
        character33.setScaleY(0.8);
        //char22
        Image single17 = new Image("textures/char22.png");
        character22 = new ImageView(single17);
        character22.setX(660);
        character22.setY(60);
        character22.setScaleX(0.8);
        character22.setScaleY(0.8);
        //char11
        Image single16 = new Image("textures/char33.png");
        character11 = new ImageView(single16);
        character11.setX(315);
        character11.setY(60);
        character11.setScaleX(0.8);
        character11.setScaleY(0.8);
        //previous
        Image single15 = new Image("textures/previouss.png");
        previous = new ImageView(single15);
        previous.setX(60);
        previous.setY(550);
        previous.setScaleX(1);
        previous.setScaleY(1);
        //next
        Image single14 = new Image("textures/nextt.png");
        next = new ImageView(single14);
        next.setX(900);
        next.setY(550);
        next.setScaleX(1);
        next.setScaleY(1);
        //save
        Image single12 = new Image("textures/savee.png");
        save = new ImageView(single12);
        save.setX(519);
        save.setY(550);
        save.setScaleX(1);
        save.setScaleY(1);
        //char1scale
        Image single11 = new Image("textures/char33.png");
        character1scale = new ImageView(single11);
        character1scale.setX(315);
        character1scale.setY(60);
        character1scale.setScaleX(0.9);
        character1scale.setScaleY(0.9);
        //char2scale
        Image single10 = new Image("textures/char22.png");
        character2scale = new ImageView(single10);
        character2scale.setX(660);
        character2scale.setY(60);
        character2scale.setScaleX(0.9);
        character2scale.setScaleY(0.9);
        //char3scale
        Image single9 = new Image("textures/char11.png");
        character3scale = new ImageView(single9);
        character3scale.setX(40);
        character3scale.setY(60);
        character3scale.setScaleX(0.9);
        character3scale.setScaleY(0.9);

        //char3
        Image single8 = new Image("textures/char11.png");
        character3 = new ImageView(single8);
        character3.setX(50);
        character3.setY(60);
        character3.setScaleX(0.8);
        character3.setScaleY(0.8);
        //char2
        Image single7 = new Image("textures/char22.png");
        character2 = new ImageView(single7);
        character2.setX(660);
        character2.setY(60);
        character2.setScaleX(0.8);
        character2.setScaleY(0.8);
        //char1
        Image single6 = new Image("textures/char33.png");
        character1 = new ImageView(single6);
        character1.setX(315);
        character1.setY(60);
        character1.setScaleX(0.8);
        character1.setScaleY(0.8);
        //bgrsetting2
        Image single5 = new Image("textures/bgr2.png");
        bgrsettingmo = new ImageView(single5);
        bgrsettingmo.setX(0);
        bgrsettingmo.setY(0);
        bgrsettingmo.setScaleX(1);
        bgrsettingmo.setScaleY(1);
        //cheat on
        Image single4 = new Image("textures/onn.png");
        on = new ImageView(single4);
        on.setX(555);
        on.setY(449);
        on.setScaleX(1);
        on.setScaleY(1);
        //cheat off
        Image single3 = new Image("textures/offf.png");
        off = new ImageView(single3);
        off.setX(555);
        off.setY(449);
        off.setScaleX(1);
        off.setScaleY(1);
        //cheat
        Image single2 = new Image("textures/cheating.png");
        cheat = new ImageView(single2);
        cheat.setX(464);
        cheat.setY(440);
        cheat.setScaleX(1);
        cheat.setScaleY(1);
        //2player
        Image single1 = new Image("textures/group.png");
        player2 = new ImageView(single1);
        player2.setX(504);
        player2.setY(360);
        player2.setScaleX(1);
        player2.setScaleY(1);
        //nosound
        Image sao2 = new Image("textures/No_sound.png");
        nosound = new ImageView(sao2);
        nosound.setX(504);
        nosound.setY(200);
        nosound.setScaleX(1);
        nosound.setScaleY(1);
        //Sound button
        Image sao = new Image("textures/sao.png");
        sound = new ImageView(sao);
        sound.setX(504);
        sound.setY(200);
        sound.setScaleX(1);
        sound.setScaleY(1);
        //1player
        Image single = new Image("textures/1player.png");
        player1 = new ImageView(single);
        player1.setX(504);
        player1.setY(280);
        player1.setScaleX(1);
        player1.setScaleY(1);
        //settingbgr
        Image stbgr = new Image("textures/setting.png");
        settingbgr = new ImageView(stbgr);
        settingbgr.setX(0);
        settingbgr.setY(0);
        settingbgr.setScaleX(1);
        settingbgr.setScaleY(1);
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

        //Button setting
        Image newGame3 = new Image("textures/button_setting.png");
        statusGame4 = new ImageView(newGame3);
        statusGame4.setX(419);
        statusGame4.setY(310);
        statusGame4.setScaleX(1);
        statusGame4.setScaleY(0.8);
        root.getChildren().add(statusGame4);

        //Button tutorial
        Image newGame1 = new Image("textures/button_tutorial.png");
        statusGame1 = new ImageView(newGame1);
        statusGame1.setX(419);
        statusGame1.setY(395);
        statusGame1.setScaleX(1);
        statusGame1.setScaleY(0.8);
        root.getChildren().add(statusGame1);

        //Button exit
        Image newGame2 = new Image("textures/button_exit.png");
        statusGame2 = new ImageView(newGame2);
        statusGame2.setX(419);
        statusGame2.setY(480);
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

        //Back
        Image Back = new Image("textures/back1.png");
        back = new ImageView(Back);
        back.setX(20);
        back.setY(20);
        back.setScaleX(1);
        back.setScaleY(1);


        //button pause
        startText3.setText("LEVELS: " + (BombermanGame.levels + 1));
        startText3.setFont(Font.font("Verdana", fontSize + 5));
        startText3.setX(320);
        startText3.setY(50);
        startText3.setFill(Color.LAVENDERBLUSH);
        startText4.setText("LIVES: " + (BombermanGame.bomberman1.getLive()));
        startText4.setFont(Font.font("Verdana", fontSize + 5));
        startText4.setX(700);
        startText4.setY(50);
        startText4.setFill(Color.LAVENDERBLUSH);


        //play game
        statusGame.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(BombermanGame.gc.getCanvas());
            root.getChildren().add(restart);
            if (!BombermanGame.PvPMode) {
                root.getChildren().add(startText3);
                root.getChildren().add(startText4);
            }
            BombermanGame.gameStart = true;

            Levels.createMap(Levels.Maps.get(BombermanGame.levels));
        });

        restart.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(authorView);
            root.getChildren().add(statusGame);
            root.getChildren().add(statusGame1);
            root.getChildren().add(statusGame2);
            root.getChildren().add(statusGame4);
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

        });

        //tutorial
        statusGame1.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(tutorial);
            root.getChildren().add(back);
        });
        //exit game
        statusGame2.setOnMouseClicked(event -> {
            root.getChildren().clear();
            System.exit(0);
        });
        //Back
        back.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(authorView);
            root.getChildren().add(statusGame);
            root.getChildren().add(statusGame1);
            root.getChildren().add(statusGame2);
            root.getChildren().add(statusGame4);
        });
        //setting
        statusGame4.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(settingbgr);
            root.getChildren().add(back);
            root.getChildren().add(sound);
            root.getChildren().add(player1);
            root.getChildren().add(player2);
            root.getChildren().add(cheat);
            root.getChildren().add(off);
        });
        //on cheat
        off.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(settingbgr);
            root.getChildren().add(back);
            root.getChildren().add(sound);
            root.getChildren().add(player1);
            root.getChildren().add(player2);
            root.getChildren().add(cheat);
            root.getChildren().add(on);
            BombermanGame.cheat = true;
        });
        //off  cheat
        on.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(settingbgr);
            root.getChildren().add(back);
            root.getChildren().add(sound);
            root.getChildren().add(player1);
            root.getChildren().add(player2);
            root.getChildren().add(cheat);
            root.getChildren().add(off);
            BombermanGame.cheat = false;
        });
        //soundsetting
        sound.setOnMouseClicked(event -> {

            root.getChildren().clear();
            root.getChildren().add(settingbgr);
            root.getChildren().add(back);
            root.getChildren().add(player1);
            root.getChildren().add(player2);
            root.getChildren().add(cheat);
            root.getChildren().add(off);
            root.getChildren().add(nosound);
            BombermanGame.clip.stop();

        });
        //soundsetting
        nosound.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(settingbgr);
            root.getChildren().add(back);
            root.getChildren().add(player1);
            root.getChildren().add(player2);
            root.getChildren().add(cheat);
            root.getChildren().add(off);
            root.getChildren().add(sound);
            BombermanGame.clip.start();
            BombermanGame.clip.loop(Clip.LOOP_CONTINUOUSLY);

        });
        player1.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character1);
            root.getChildren().add(character2);
            root.getChildren().add(character3);
            BombermanGame.PvPMode = false;
        });
        player2.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(authorView);
            root.getChildren().add(statusGame);
            root.getChildren().add(statusGame1);
            root.getChildren().add(statusGame2);
            root.getChildren().add(statusGame4);
            BombermanGame.PvPMode = true;
            BombermanGame.levels = 3;
            BombermanGame.movingEntities.clear();
        });

        character3.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character1);
            root.getChildren().add(character2);
            root.getChildren().add(character3scale);
            root.getChildren().add(save);
            BombermanGame.player3 = true;
            BombermanGame.player2 = false;
            BombermanGame.player1 = false;
        });
        character2.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character1);
            root.getChildren().add(character2scale);
            root.getChildren().add(character3);
            root.getChildren().add(save);
            BombermanGame.player1 = true;
            BombermanGame.player3 = false;
            BombermanGame.player2 = false;
        });
        character1.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character1scale);
            root.getChildren().add(character2);
            root.getChildren().add(character3);
            root.getChildren().add(save);
            BombermanGame.player2 = true;
            BombermanGame.player3 = false;
            BombermanGame.player1 = false;
        });


        save.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(settingbgr);
            root.getChildren().add(back);
            root.getChildren().add(sound);
            root.getChildren().add(player1);
            root.getChildren().add(player2);
            root.getChildren().add(cheat);
            root.getChildren().add(off);
        });


        character33.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character11);
            root.getChildren().add(character22);
            root.getChildren().add(character3scale);
            root.getChildren().add(next);
            BombermanGame.player3 = true;
            BombermanGame.player2 = false;
            BombermanGame.player1 = false;

        });

        character22.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character11);
            root.getChildren().add(character2scale);
            root.getChildren().add(character33);
            root.getChildren().add(next);
            BombermanGame.player1 = true;
            BombermanGame.player3 = false;
            BombermanGame.player2 = false;

        });

        character11.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character1scale);
            root.getChildren().add(character22);
            root.getChildren().add(character33);
            root.getChildren().add(next);
            BombermanGame.player2 = true;
            BombermanGame.player3 = false;
            BombermanGame.player1 = false;

        });
        next.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character111);
            root.getChildren().add(character222);
            root.getChildren().add(character333);
            root.getChildren().add(previous);
        });
        character333.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character111);
            root.getChildren().add(character222);
            root.getChildren().add(character3scale);
            root.getChildren().add(save);
            root.getChildren().add(previous);

        });

        character222.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character111);
            root.getChildren().add(character2scale);
            root.getChildren().add(character333);
            root.getChildren().add(save);
            root.getChildren().add(previous);
        });
        character111.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character1scale);
            root.getChildren().add(character222);
            root.getChildren().add(character333);
            root.getChildren().add(save);
            root.getChildren().add(previous);

        });
        previous.setOnMouseClicked(event -> {
            root.getChildren().clear();
            root.getChildren().add(bgrsettingmo);
            root.getChildren().add(character11);
            root.getChildren().add(character22);
            root.getChildren().add(character33);
            root.getChildren().add(next);
        });
    }
}
