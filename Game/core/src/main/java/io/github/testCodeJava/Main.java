package io.github.testCodeJava;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
//12:52 AM 746 Lines
/** {@link ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Texture image1;
    private Texture[] image2 = new Texture[2];
    private Texture image3;
    private Texture image4;
    private Texture image5;
    private Texture image6;
    private Texture image7;
    private Texture image8;
    private Texture image9;
    private Texture image10;
    TextureRegion[] shapes = new TextureRegion[21];
    TextureRegion[] sceneChangers = new TextureRegion[17];
    TextureRegion[] walls = new TextureRegion[47];
    public float timeNum = 0;
    public int[] currentScene = new int[]{0, 0, 0, 0, 0, 0, 0};
    public Vector2 max = new Vector2(1000, 1000);
    public Vector2 graphMax = new Vector2(1000, 1000);
    public Vector2 position = new Vector2(0, 0);
    public Vector2 increment = new Vector2(16, 16);
    boolean isChange = false;
    Sprite character;
    int timeInterval1 = 0;
    boolean timeSwap1 = false;
    int timeInterval2 = 0;
    boolean timeSwap2 = false;
    float timer1 = 0.10f;
    float timer2 = 0.10f;
    Functions[] allFunctions = new Functions[21];
    boolean[] completed = new boolean[22];
    boolean[] collected = new boolean[21];
    boolean graphMode = false;
    boolean isSwitching = false;
    Problems[] pro = new Problems[21];
    public List<Vector2> spots = new ArrayList<>();
    public List<Vector2> whereWalls = new ArrayList<>();
    public int maxKeep = 100;
    public int waiting = 0;
    @Override
    public void create() {
        completed[21] = true;
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        image1 = new Texture("bottle.png");
        image2[0] = new Texture("ircle.png");
        image2[1] = new Texture("yelIrcle.png");
        image3 = new Texture("calc.png");
        image4 = new Texture("fullCalc.png");
        image5 = new Texture("altCalc.png");
        image6 = new Texture("bg.png");
        image7 = new Texture("bg1.png");
        image8 = new Texture("badcalc.png");
        image9 = new Texture("walls.png");
        image10 = new Texture("wallMore.png");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                walls[i * 5 + j] = new TextureRegion(image9, 30 * i + 4, 30 * j + 10, 26, 26);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                walls[i * 2 + j + 25] = new TextureRegion(image5, 30 * i + 94, 30 * j + 10, 26, 26);
            }
        }
        walls[29] = new TextureRegion(image5, 124, 70, 26, 26);
        walls[30] = new TextureRegion(image5, 130, 100, 26, 26);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                walls[i * 4 + j + 31] = new TextureRegion(image10, 30 * i + 4, 30 * j + 40, 26, 26);
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                shapes[i * 4 + j] = new TextureRegion(image4, 30 * i, 30 * j + 36, 34, 34);
            }
            shapes[16 + i] = new TextureRegion(image4, 120, 24 * i + 36, 40, 28);
        }
        shapes[20] = new TextureRegion(image4, 120, 132, 40, 28);
        character = new Sprite(image1);
        character.setScale(0.3f);
        sceneChangers[0] = new TextureRegion(image5, 0, 126, 34, 34);
        sceneChangers[1] = new TextureRegion(image5, 0, 126, 34, 34);
        sceneChangers[2] = new TextureRegion(image5, 30, 126, 34, 34);
        sceneChangers[3] = new TextureRegion(image5, 60, 126, 34, 34);
        sceneChangers[4] = new TextureRegion(image5, 90, 126, 34, 34);
        sceneChangers[5] = new TextureRegion(image5, 0, 96, 34, 34);
        sceneChangers[6] = new TextureRegion(image5, 30, 96, 34, 34);
        sceneChangers[7] = new TextureRegion(image5, 60, 96, 34, 34);
        sceneChangers[8] = new TextureRegion(image5, 0, 66, 34, 34);
        sceneChangers[9] = new TextureRegion(image5, 30, 66, 34, 34);
        sceneChangers[10] = new TextureRegion(image5, 60, 66, 34, 34);
        sceneChangers[11] = new TextureRegion(image5, 90, 66, 34, 34);
        sceneChangers[12] = new TextureRegion(image5, 0, 36, 34, 34);
        sceneChangers[13] = new TextureRegion(image5, 30, 36, 34, 34);
        sceneChangers[14] = new TextureRegion(image5, 64, 40, 26, 26);
        sceneChangers[15] = new TextureRegion(image5, 90, 96, 34, 34);
        sceneChangers[16] = new TextureRegion(image5, 94, 114, 27, 1);
        pro[0] = new Problems(new boolean[]{true, false, true, true, true, true, true, true, true, true, true, true, true, true}, new int[]{26, 33, 9, 27, 17, 10, 18, 6, 13, 0, 17, 10, 9, 7}, 14, new int[]{0, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(325, -75)});
        pro[1] = new Problems(new boolean[]{true, false, true, true, true, true, false, false, true, true, true, true, true, true, true, true, false, true}, new int[]{26, 33, 14, 29, 17, 13, 33, 33, 12, 16, 14, 29, 17, 6, 8, 16, 33, 13}, 18, new int[]{1, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(25, 555)});
        pro[2] = new Problems(new boolean[]{true, true, false, true, true, true, false, true, false, true, true, true, true, true, true, true, true}, new int[]{29, 19, 33, 9, 7, 7, 33, 26, 33, 10, 9, 17, 15,10, 7, 19, 29}, 17, new int[]{2, 1}, new Vector2[]{new Vector2(375, -125), new Vector2(600, 100)});
        pro[3] = new Problems(new boolean[]{true, false, true, true, true, true, true, false, false, true, true, false, true, true, true, true, true, true}, new int[]{26, 33, 1, 31, 29, 32, 17, 33, 33, 7, 19, 33, 10, 9, 7, 17, 15, 6}, 18, new int[]{3, 1}, new Vector2[]{new Vector2(-75, 175), new Vector2(325, 515)});
        pro[4] = new Problems(new boolean[]{true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, false, true, false, false}, new int[]{26, 20, 33, 31, 28, 19, 10, 27, 32, 17, 12, 19, 10, 29, 17, 33, 30, 33, 33}, 19, new int[]{4, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(-15, 525)});
        pro[5] = new Problems(new boolean[]{true, true, true, false, false, false, true, true, false, false, false, true, true, true, false, false, false}, new int[]{26, 20, 31, 33, 33, 33, 32, 31, 33, 33, 33, 32, 29, 19, 33, 33, 33}, 17, new int[]{5, 1}, new Vector2[]{new Vector2(-75, 425), new Vector2(-25, -25)});
        pro[6] = new Problems(new boolean[]{true, true, true, false, true}, new int[]{26, 20, 7, 33, 29}, 5, new int[]{6, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(525, 525)});
        pro[7] = new Problems(new boolean[]{true, true, true, false, true, true, true, false, true, false, true, true, false, true, true, true, false, true, true, true}, new int[]{31, 1, 31, 33, 32, 1, 31, 33, 32, 33, 2, 31, 33, 32, 2, 31, 33, 32, 32, 29}, 20, new int[]{7, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(525, 525)});
        pro[8] = new Problems(new boolean[]{true, true, false, true, true, true, true, true, true, false, false, true, true, false, false, true, false, false, false, false}, new int[]{26, 20, 33, 12, 7, 7, 7, 29, 18, 33, 33, 6, 17, 33, 33, 29, 33, 33, 33, 33}, 20, new int[]{8, 1}, new Vector2[]{new Vector2(-78, 515), new Vector2(-55, -175)});
        pro[9] = new Problems(new boolean[]{true, true, false, true, true, false, false, false, true, true, false, true, true, true}, new int[]{26, 20, 33, 27, 19, 33, 33, 33, 29, 31, 33, 16, 29, 32}, 14, new int[]{9, 1}, new Vector2[]{new Vector2(-75, -5), new Vector2(-60, 445)});
        pro[10] = new Problems(new boolean[]{true, true, false, true, true, true}, new int[]{26, 20, 33, 31, 29, 32}, 6, new int[]{10, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(425, -75)});
        pro[11] = new Problems(new boolean[]{true, true, false, false, false, false, true, true, false, false, true, true, false, false, true, true, false}, new int[]{26, 20, 33, 33, 33, 33, 28, 18, 33, 33, 27,19, 33, 33, 29, 17, 33}, 17, new int[]{11, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(38, 325)});
        pro[12] = new Problems(new boolean[]{true, true, false, true, true, true, false, true, true, true, true, true, true, true, true}, new int[]{26, 20, 33, 31, 29, 32, 33, 31, 29, 32, 20, 1, 31, 29, 32}, 15, new int[]{12, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(525, -75)});
        pro[13] = new Problems(new boolean[]{true, true, true, true, true, true, true, false, false, false, false, false, false, false}, new int[]{26, 20, 1, 31, 29, 32, 17, 33, 33, 33, 33, 33, 33, 33}, 14, new int[]{13, 1}, new Vector2[]{new Vector2(-75, 125), new Vector2(375, 425)});
        pro[14] = new Problems(new boolean[]{true, true, false, false, false, false, false, false, false}, new int[]{26, 20, 33, 33, 33, 33, 33, 33, 33}, 9, new int[]{14, 1}, new Vector2[]{new Vector2(-75, 575), new Vector2(575, 575)});
        pro[15] = new Problems(new boolean[]{true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true}, new int[]{26, 20, 1, 31, 29, 32, 17, 9, 7, 7, 16, 33, 19, 10, 7, 7}, 16, new int[]{15, 1}, new Vector2[]{new Vector2(-75, 125), new Vector2(425, 470)});
        pro[16] = new Problems(new boolean[]{true, true, true, false, false, false, true, true, true, false, false, true, true, true, true}, new int[]{29, 20, 31, 33, 33, 33, 32, 26, 17, 33, 33, 19, 9, 7, 7}, 15, new int[]{16, 1}, new Vector2[]{new Vector2(325, -175), new Vector2(525, 25)});
        pro[17] = new Problems(new boolean[]{true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true}, new int[]{29, 31, 31, 33, 18, 4, 32, 19, 31, 33, 18, 12, 32, 19, 31, 33, 18, 10, 32, 32}, 20, new int[]{17, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(525, 525)});
        pro[18] = new Problems(new boolean[]{true, true, true, true, true, true, true, true, true, false, true, false, true, true, true, false, true, true, true}, new int[]{26, 20, 9, 29, 19, 12, 7, 17, 5, 33, 14, 33, 10, 5, 7, 33, 13, 7, 7}, 19, new int[]{18, 1}, new Vector2[]{new Vector2(-27, -75), new Vector2(75, 435)});
        pro[19] = new Problems(new boolean[]{true, false, true, true, false, true, true, true, true, true, false, false, true, false, false, false, true, true}, new int[]{31, 33,29, 19, 33, 32, 31, 29, 32, 16, 33, 33, 19, 33, 33, 33, 20, 26}, 18, new int[]{19, 1}, new Vector2[]{new Vector2(-75, -125), new Vector2(0, 441)});
        pro[20] = new Problems(new boolean[]{true, true, false}, new int[]{26, 20, 33}, 3, new int[]{20, 1}, new Vector2[]{new Vector2(-75, -75), new Vector2(525, 525)});
    }
    @Override
    public void render() {
        currentScene[5] = 0;
        if (currentScene[2] == 1 && !isChange){
            currentScene[2] = 0;
            isChange = true;
        }
        if (timeNum >= 359) {
            timeNum = 0;
        }
        else {
            timeNum += 4;
        }
        if (currentScene[2] == 0 && !isChange) {
            if (currentScene[1] == 0) {
                if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP))
                {
                    if (canMove(new Vector2(max.x, max.y + 10))){
                        max.y += 10;
                    }
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT))
                {
                    if (canMove(new Vector2(max.x + 10, max.y))) {
                        max.x += 10;
                    }
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN))
                {
                    if (canMove(new Vector2(max.x, max.y - 10))) {
                        max.y -= 10;
                    }
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                {
                    if (canMove(new Vector2(max.x - 10, max.y))) {
                        max.x -= 10;
                    }
                }
            }
        }
        /*
        spots.add(0, new Vector2(max.x, max.y));
        if (spots.size() > 200){
            spots.remove(spots.size() - 1);
        }

         */
        ScreenUtils.clear(0, 0, 0, 1f);
        batch.begin();
        makeWallFloor();
/*
        if (spots.size() > 49) {
            batch.draw(image8, 440 + (max.x - spots.get(49).x), 440 + (spots.get(49).y - max.y), 120, 120);
        }
        if (spots.size() > 99) {
            batch.draw(image8, 440 + (max.x - spots.get(99).x), 440 + (spots.get(99).y - max.y), 120, 120);
        }
        if (spots.size() > 199) {
            batch.draw(image8, 440 + (max.x - spots.get(199).x), 440 + (spots.get(199).y - max.y), 120, 120);
        }
        if (currentScene[1] == 1) {
            batch.draw(image6, 0, 0);
        }

 */
        batch.end();
        TextureRegion[] allTextures = new TextureRegion[shapes.length + sceneChangers.length];
        for (int i = 0; i < allTextures.length; i++) {
            allTextures[i] = (i < 21) ? shapes[i] : sceneChangers[i - 21];
            if (i == 25 || i == 34){
                allTextures[i] = new TextureRegion(allTextures[i], 0, 0, allTextures[i].getRegionWidth(), allTextures[i].getRegionHeight() - 1);
            }
            else {
                allTextures[i] = new TextureRegion(allTextures[i], 4, 4, allTextures[i].getRegionWidth() - 8, allTextures[i].getRegionHeight() - 9);
            }
        }
        allFunctions = new Functions[21];
        for (int i = 0; i < pro.length; i++) {
            allFunctions[i] = pro[i].turn(currentScene);
        }
        if (currentScene[1] == 1) {
            for (int i = 0; i < allFunctions.length; i++) {
                Vector2[] batteries = pro[i].getBatteries();
                boolean swapImage1 = false;
                boolean swapImage2 = false;
                for (int j = 0; j < 300; j++) {
                    float yVal = allFunctions[i].getFunctionY(batteries[0].x + 225 + (float) j / 2, graphMax);
                    if (yVal > batteries[0].y + 225 && yVal < batteries[0].y + 225 + 150){
                        swapImage1 = true;
                    }
                    yVal = allFunctions[i].getFunctionY(batteries[1].x + 225 + (float) j / 2, graphMax);
                    if (yVal > batteries[1].y + 25 && yVal < batteries[1].y + 150 + 225){
                        swapImage2 = true;
                    }
                }
                if (swapImage1 && swapImage2){
                    completed[i] = true;
                }
                /*
                if (!swapImage1 || !swapImage2){
                    completed[i] = false;
                }

                 */
                allFunctions[i].draw((currentScene[1] == 1) ? graphMax : max, batch, image2, currentScene, swapImage1 || swapImage2);
            }
        }

        Pickups[] p = new Pickups[21];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                p[i * 4 + j] = new Pickups(i * 4 + j, new Vector2(900 + 100 * i, 900 + 100 * j), new Vector2(shapes[i * 4 + j].getRegionWidth() * 1.5f, shapes[i * 4 + j].getRegionHeight() * 1.5f), 0, 20);
            }
            p[16 + i] = new Pickups(16 + i, new Vector2(900 + 400, 900 + 100 * i), new Vector2(shapes[16 + i].getRegionWidth() * 1.5f, shapes[16 + i].getRegionHeight() * 1.5f), 0, 20);
        }
        p[20] = new Pickups(20, new Vector2(1300, 1300), new Vector2(shapes[20].getRegionWidth() * 1.5f, shapes[20].getRegionHeight() * 1.5f), 0, 20);
        p[20].setNeed(21);
        p[15].setNeed(0);
        p[14].setNeed(1);
        p[9].setNeed(2);
        p[7].setNeed(3);
        p[11].setNeed(4);
        p[19].setNeed(5);
        p[8].setNeed(6);
        p[3].setNeed(7);
        p[10].setNeed(8);
        p[2].setNeed(9);
        p[5].setNeed(10);
        p[12].setNeed(11);
        p[13].setNeed(12);
        p[18].setNeed(13);
        p[6].setNeed(14);
        p[1].setNeed(15);
        p[4].setNeed(16);
        p[16].setNeed(17);
        p[17].setNeed(18);
        p[0].setNeed(19);
        if (currentScene[2] == 0 && !isChange) {
            SceneChanges[] sc = new SceneChanges[21];
            sc[0] = new SceneChanges(new Vector2(620, 140), new Vector2(80, 80), new int[]{0, 0}, new int[]{0, 1});
            p[20].setLocation(new Vector2(620, 260));
            p[15].setLocation(new Vector2(740, 140));
            sc[1] = new SceneChanges(new Vector2(740, 1580), new Vector2(80, 80), new int[]{0, 0}, new int[]{1, 1});
            p[14].setLocation(new Vector2(740, 1700));
            sc[2] = new SceneChanges(new Vector2(-1180, 740), new Vector2(80, 80), new int[]{0, 0}, new int[]{2, 1});
            p[9].setLocation(new Vector2(-1180, 860));
            sc[3] = new SceneChanges(new Vector2(-220, -1060), new Vector2(80, 80), new int[]{0, 0}, new int[]{3, 1});
            p[7].setLocation(new Vector2(-340, -1060));
            sc[4] = new SceneChanges(new Vector2(1940, -1060), new Vector2(80, 80), new int[]{0, 0}, new int[]{4, 1});
            p[11].setLocation(new Vector2(2060, -1060));
            sc[5] = new SceneChanges(new Vector2(2300, -700), new Vector2(80, 80), new int[]{0, 0}, new int[]{5, 1});
            p[19].setLocation(new Vector2(2300, -820));
            sc[6] = new SceneChanges(new Vector2(140, -2860), new Vector2(80, 80), new int[]{0, 0}, new int[]{6, 1});
            p[8].setLocation(new Vector2(140, -2980));
            sc[7] = new SceneChanges(new Vector2(3980, -940), new Vector2(80, 80), new int[]{0, 0}, new int[]{7, 1});
            p[3].setLocation(new Vector2(3860, -940));
            sc[8] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{8, 1});
            sc[9] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{9, 1});
            sc[10] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{10, 1});
            sc[11] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{11, 1});
            sc[12] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{12, 1});
            sc[13] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{13, 1});
            sc[14] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{14, 1});
            sc[15] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{15, 1});
            sc[16] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{16, 1});
            sc[17] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{17, 1});
            sc[18] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{18, 1});
            sc[19] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{19, 1});
            sc[20] = new SceneChanges(new Vector2(9000, 0), new Vector2(80, 80), new int[]{0, 0}, new int[]{20, 1});
            for (SceneChanges sceneChanges : sc) {
                if (sceneChanges.getSceneReq()[1] == 1) {
                    sceneChangers[0] = sceneChangers[1];
                } else {
                    sceneChangers[0] = sceneChangers[2];
                }
                sceneChangers[3] = new TextureRegion(image5,30, 36, 34, 34);
                sceneChanges.draw(sceneChangers, batch, (currentScene[1] == 1) ? graphMax : max, timeNum, currentScene, collected, completed);
            }
        }
        for (int i = 0; i < 21; i++) {
            p[i].draw(shapes, batch, max, timeNum, currentScene, collected, completed);
        }
        batch.begin();
        if (currentScene[1] == 0) {
            batch.draw(image3, 440, 440, 120, 120);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (collected[i * 4 + j]) {
                        batch.draw(new TextureRegion(shapes[i * 4 + j], 0, 0, 34, 33), 440 + 22.5f * i, 440 + 22.5f * (3 - j), 25.5f, 25.5f);
                    }
                }
                if (collected[16 + i]) {
                    batch.draw(shapes[16 + i], 530, 440 + 18 * (4 - i), 30, 21);
                }
            }
            if (collected[20]) {
                batch.draw(shapes[20], 530, 440, 30, 21);
            }
        }
        else {
            if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && !isSwitching && !isChange){
                currentScene[2] = 1;
                currentScene[3] = 0;
                currentScene[4] = 0;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !isSwitching && !isChange){
                isSwitching = true;
            }
            if (!graphMode){
                batch.draw(image3, 0, 0, 1000, 1000);
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (collected[i * 4 + j]) {
                            batch.draw(new TextureRegion(shapes[i * 4 + j], 0, 0, 34, 33), 187.5f * i, 187.5f * (3 - j), 212.5f, 212.5f);
                        }
                    }
                    if (collected[16 + i]) {
                        batch.draw(shapes[16 + i], 750, 150 * (4 - i), 250, 175);
                    }
                }
                if (collected[20]) {
                    batch.draw(shapes[20], 750, 0, 250, 175);
                }
            }
        }
        batch.end();
        for (int i = 0; i < pro.length; i++) {
            pro[i].setFlashNum((int) timeNum);
            pro[i].lookForChange(currentScene, graphMode, collected);
            pro[i].draw(batch, allTextures, currentScene, graphMode);
        }

        if (isSwitching){
            batch.begin();
            for (int i = 0; i < timeInterval2 * 4; i++) {
                batch.draw(image7, 0, 0, 1000, 1000);
            }
            batch.end();
            timer2 -= Gdx.graphics.getDeltaTime();
            if (timer2 <= 0f) {
                timer2 = 0.10f;
                if (!timeSwap2) {
                    timeInterval2 += 1;
                    if (timeInterval2 == 4) {
                        timeSwap2 = true;
                        graphMode = !graphMode;
                    }
                } else {
                    timeInterval2 -= 1;
                    if (timeInterval2 == 0) {
                        timeSwap2 = false;
                        isSwitching = false;
                    }
                }
            }
        }
        if (currentScene[2] == 0 && isChange)
        {
            batch.begin();
            for (int i = 0; i < timeInterval1 * 4; i++) {
                batch.draw(image7, 0, 0, 1000, 1000);
            }
            batch.end();
            timer1 -= Gdx.graphics.getDeltaTime();
            if (timer1 <= 0f){
                timer1 = 0.10f;
                if (!timeSwap1){
                    timeInterval1 += 1;
                    if (timeInterval1 == 10){
                        timeSwap1 = true;
                        currentScene[0] = currentScene[3];
                        currentScene[1] = currentScene[4];
                    }
                }
                else{
                    timeInterval1 -= 1;
                    if (timeInterval1 == 0){
                        isChange = false;
                        timeSwap1 = false;
                    }
                }
            }
        }
        if (currentScene[5] == 0 && currentScene[2] == 0 && !isChange && !isSwitching){
            waiting += 1;
            if (waiting == 20){
                currentScene[6] = 0;
                waiting = 0;
            }

        }
        else{
            waiting = 0;
        }
    }
    public boolean canMove(Vector2 max){
        for (int i = 0; i < whereWalls.size(); i++) {
            if (whereWalls.get(i).y * 120 - max.y + 1000 < 560 && whereWalls.get(i).y * 120 - max.y + 1000 > 440 - 120 && whereWalls.get(i).x * 120 - 1000 + max.x < 560 && whereWalls.get(i).x * 120 - 1000 + max.x > 440 - 120){
                return false;
            }
        }
        return true;
    }
    public void floors(int width, int height, int x, int y){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                batch.draw(sceneChangers[14], 120 * (i + x) + max.x - 1000,120 * (j + y) + 1000 - max.y, 120, 120);
            }
        }
    }
    public void walls(int imageNum, int width, int height, int x, int y){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                whereWalls.add(new Vector2(i + x, j + y));
                batch.draw(walls[imageNum], 120 * (i + x) + max.x - 1000,120 * (j + y) + 1000 - max.y, 120, 120);
            }
        }
    }
    public void makeWallFloor(){
        floors(8, 8, 0, 0);
        floors(1, 3, 8, 5);
        floors(3, 1, 0, -1);
        floors(27, 3, -5, -4);
        floors(6, 12, -11, -4);
        floors(3, 10, -4, -1);
        floors(19, 3, -11, 9);
        floors(3, 3, 5, 12);
        floors(3, 4, 1, 12);
        floors(29, 3, -7, 16);
        floors(3, 17, 9, -1);
        floors(3, 6, 18, 10);
        floors(3, 9, 18, 0);
        floors(1, 3, 21, 0);
        floors(9, 9, 22, 0);
        floors(1, 3, 31, 0);
        floors(3, 10, 32, 0);
        floors(9, 9, 22, 10);
        floors(5, 3, 31, 10);
        floors(3, 18, 36, 4);
        floors(1, 4, 36, 0);
        floors(1, 2, 38, 2);
        floors(8, 1, 36, -1);
        floors(4, 1, 38, 1);
        floors(1, 12, 44, -1);
        floors(1, 12, 42, 1);
        floors(12, 1, 44, 11);
        floors(14, 1, 42, 13);
        floors(3, 3, 56, 11);
        floors(3, 9, 0, -13);
        floors(3, 3, -3, -10);
        floors(9, 9, -3, -22);
        floors(3, 3, 0, -25);
        floors(6, 3, 3, -10);
        floors(3, 9, 9, -13);
        floors(9, 9, 9, -22);
        floors(6, 3, 12, -10);
        floors(3, 3, 18, -7);
        floors(9, 9, 22, -10);
        floors(22, 3, 18, -13);
        floors(8, 7, 32, -9);
        floors(6, 3, 40, -5);
        floors(3, 1, 37, -10);
        floors(3, 10, 25, -23);
        floors(5, 9, 19, -23);
        floors(1, 3, 24, -23);
        floors(2, 6, 19, -29);
        floors(4, 3, 21, -29);
        floors(3, 3, 31, -16);
        floors(9, 9, 31, -25);
        floors(3, 3, 37, -28);
        floors(9, 3, 34, -31);
        floors(3, 3, -18, -28);
        floors(9, 3, -21, -31);
        floors(6, 44, -18, -25);
        floors(3, 7, -11, 12);
        floors(1, 3, -12, 16);
        walls(4, 1, 46, -19, -27);
        walls(18, 1, 1, -19, -28);
        walls(14, 2, 1, -21, -28);
        walls(0, 1, 1, -22, -28);
        walls(4, 1, 3, -22, -31);
        walls(10, 1, 1, -22, -32);
        walls(19, 9, 1, -21, -32);
        walls(5, 1, 1, -12, -32);
        walls(9, 1, 3, -12, -31);
        walls(16, 1, 1, -12, -28);
        walls(14, 2, 1, -14, -28);
        walls(8, 1, 1, -15, -28);
        walls(9, 1, 1, -15, -27);
        walls(13, 1, 1, -15, -26);
        walls(19, 2, 1, -14, -26);
        walls(5, 1, 1, -12, -26);
        walls(9, 1, 20, -12, -25);
        walls(37, 1, 1, -12, -5);
        walls(17, 1, 12, -12, -4);
        walls(43, 1, 1, -12, 8);
        walls(17, 1, 7, -12, 9);
        walls(1, 1, 1, -12, 16);
        walls(12, 6, 1, -11, 8);
        walls(36, 1, 1, -5, 8);
        walls(17, 1, 8, -5, 0);
        walls(2, 1, 1, -5, -1);
        walls(19, 10, 1, -11, -5);
        walls(3, 1, 1, -1, -5);
        walls(4, 1, 1, -1, -6);
        walls(18, 1, 1, -1, -7);
        walls(14, 2, 1, -3, -7);
        walls(0, 1, 1, -4, -7);
        walls(4, 1, 3, -4, -10);
        walls(10, 1, 1, -4, -11);
        walls(19, 2, 1, -3, -11);
        walls(3, 1, 1, -1, -11);
        walls(4, 1, 1, -1, -12);
        walls(18, 1, 1, -1, -13);
        walls(14, 2, 1, -3, -13);
        walls(0, 1, 1, -4, -13);
        walls(4, 1, 9, -4, -22);
        walls(10, 1, 1, -4, -23);
        walls(19, 2, 1, -3, -23);
        walls(3, 1, 1, -1, -23);
        walls(4, 1, 2, -1, -25);
        walls(10, 1, 1, -1, -26);
        walls(19, 3, 1, 0, -26);
        walls(5, 1, 1, 3, -26);
        walls(9, 1, 2, 3, -25);
        walls(13, 1, 1, 3, -23);
        walls(19, 2, 1, 4, -23);
        walls(5, 1, 1, 6, -23);
        walls(9, 1, 9, 6, -22);
        walls(16, 1, 1, 6, -13);
        walls(14, 2, 1, 4, -13);
        walls(8, 1, 1, 3, -13);
        walls(9, 1, 1, 3, -12);
        walls(13, 1, 1, 3, -11);
        walls(19, 4, 1, 4, -11);
        walls(3, 1, 1, 8, -11);
        walls(4, 1, 11, 8, -22);
        walls(10, 1, 1, 8, -23);
        walls(19, 9, 1, 9, -23);
        walls(46, 1, 1, 18, -23);
        walls(17, 1, 8, 18, -22);
        walls(32, 1, 1, 18, -14);
        walls(12, 5, 1, 19, -14);
        walls(36, 1, 1, 24, -14);
        walls(17, 1, 5, 24, -19);
        walls(2, 1, 1, 24, -20);
        walls(4, 1, 6, 18, -29);
        walls(10, 1, 1, 18, -30);
        walls(19, 6, 1, 19, -30);
        walls(5, 1, 1, 25, -30);
        walls(9, 1, 3, 25, -29);
        walls(16, 1, 1, 25, -26);
        walls(14, 3, 1, 22, -26);
        walls(8, 1, 1, 21, -26);
        walls(9, 1, 1, 21, -25);
        walls(13, 1, 1, 21, -24);
        walls(19, 6, 1, 22, -24);
        walls(5, 1, 1, 28, -24);
        walls(9, 1, 9, 28, -23);
        walls(13, 1, 1, 28, -14);
        walls(19, 1, 1, 29, -14);
        walls(3, 1, 1, 30, -14);
        walls(4, 1, 11, 30, -25);
        walls(10, 1, 1, 30, -26);
        walls(19, 5, 1, 31, -26);
        walls(3, 1, 1, 36, -26);
        walls(4, 1, 1, 36, -27);
        walls(18, 1, 1, 36, -28);
        walls(14, 2, 1, 34, -28);
        walls(0, 1, 1, 33, -28);
        walls(4, 1, 3, 33, -31);
        walls(10, 1, 1, 33, -32);
        walls(19, 9, 1, 34, -32);
        walls(5, 1, 1, 43, -32);
        walls(9, 1, 3, 43, -31);
        walls(16, 1, 1, 43, -28);
        walls(14, 2, 1, 41, -28);
        walls(8, 1, 1, 40, -28);
        walls(9, 1, 11, 40, -27);
        walls(16, 1, 1, 40, -16);
        walls(14, 5, 1, 35, -16);
        walls(8, 1, 1, 34, -16);
        walls(9, 1, 1, 34, -15);
        walls(13, 1, 1, 34, -14);
        walls(19, 5, 1, 35, -14);
        walls(5, 1, 1, 40, -14);
        walls(9, 1, 7, 40, -13);
        walls(13, 1, 1, 40, -6);
        walls(19, 5, 1, 41, -6);
        walls(5, 1, 1, 46, -6);
        walls(9, 1, 3, 46, -5);
        walls(16, 1, 1, 46, -2);
        walls(42, 1, 1, 45, -2);
        walls(12, 9, 1, 36, -2);
        walls(33, 1, 1, 35, -2);
        walls(14, 3, 1, 32, -2);
        walls(41, 1, 1, 31, -2);
        walls(17, 1, 7, 31, -9);
        walls(44, 1, 1, 31, -10);
        walls(12, 4, 1, 32, -10);
        walls(7, 1, 1, 36, -10);
        walls(38, 1, 1, 31, -1);
        walls(12, 13, 1, 18, -1);
        walls(33, 1, 1, 17, -1);
        walls(14, 4, 1, 13, -1);
        walls(8, 1, 1, 12, -1);
        walls(9, 1, 15, 12, 0);
        walls(13, 1, 1, 12, 15);
        walls(19, 4, 1, 13, 15);
        walls(3, 1, 1, 17, 15);
        walls(4, 1, 5, 17, 10);
        walls(25, 1, 1, 17, 9);
        walls(12, 3, 1, 18, 9);
        walls(30, 1, 1, 21, 9);
        walls(17, 1, 5, 21, 10);
        walls(1, 1, 1, 21, 15);
        walls(12, 9, 1, 22, 9);
        walls(36, 1, 1, 31, 9);
        walls(17, 1, 5, 31, 4);
        walls(2, 1, 1, 31, 3);
        walls(17, 1, 5, 21, 4);
        walls(2, 1, 1, 21, 3);
        walls(4, 1, 9, 17, 0);
        walls(19, 3, 1, 32, -1);
        walls(46, 1, 1, 35, -1);
        walls(17, 1, 9, 35, 0);
        walls(1, 1, 1, 35, 9);
        walls(9, 1, 11, 45, -1);
        walls(13, 1, 1, 45, 10);
        walls(19, 13, 1, 46, 10);
        walls(5, 1, 1, 59, 10);
        walls(9, 1, 3, 59, 11);
        walls(16, 1, 1, 59, 14);
        walls(14, 17, 1, 42, 14);
        walls(0, 1, 1, 41, 14);
        walls(4, 1, 11, 41, 3);
        walls(18, 1, 1, 41, 2);
        walls(14, 1, 1, 40, 2);
        walls(8, 1, 1, 39, 2);
        walls(9, 1, 19, 39, 3);
        walls(16, 1, 1, 39, 22);
        walls(14, 3, 1, 36, 22);
        walls(0, 1, 1, 35, 22);
        walls(4, 1, 8, 35, 14);
        walls(18, 1, 1, 35, 13);
        walls(14, 3, 1, 32, 13);
        walls(8, 1, 1, 31, 13);
        walls(9, 1, 5, 31, 14);
        walls(16, 1, 1, 31, 19);
        walls(14, 38, 1, -7, 19);
        walls(22, 1, 1, -8, 19);
        walls(17, 1, 3, -8, 16);
        walls(37, 1, 1, -8, 15);
        walls(19, 7, 1, -7, 15);
        walls(3, 1, 1, 0, 15);
        walls(4, 1, 2, 0, 13);
        walls(18, 1, 1, 0, 12);
        walls(14, 7, 1, -7, 12);
        walls(8, 1, 1, -8, 12);
        walls(9, 1, 2, -8, 13);
        walls(14, 10, 1, -18, 19);
        walls(0, 1, 1, -19, 19);
        walls(2, 1, 1, -1, -1);
        walls(17, 1, 8, -1, 0);
        walls(32, 1, 1, -1, 8);
        walls(12, 8, 1, 0, 8);
        walls(40, 1, 1, 8, 8);
        walls(17, 1, 6, 8, 9);
        walls(36, 1, 1, 8, 15);
        walls(12, 3, 1, 5, 15);
        walls(32, 1, 1, 4, 15);
        walls(17, 1, 2, 4, 13);
        walls(2, 1, 1, 4, 12);
        walls(6, 1, 1, 3, -1);
        walls(12, 4, 1, 4, -1);
        walls(40, 1, 1, 8, -1);
        walls(17, 1, 4, 8, 0);
        walls(1, 1, 1, 8, 4);
        walls(13, 1, 1, 3, -5);
        walls(19, 4, 1, 4, -5);
        walls(3, 1, 1, 8, -5);
        walls(4, 1, 1, 8, -6);
        walls(18, 1, 1, 8, -7);
        walls(14, 4, 1, 4, -7);
        walls(8, 1, 1, 3, -7);
        walls(9, 1, 1, 3, -6);
        walls(13, 1, 1, 12, -5);
        walls(19, 4, 1, 13, -5);
        walls(3, 1, 1, 17, -5);
        walls(4, 1, 1, 17, -6);
        walls(18, 1, 1, 17, -7);
        walls(14, 4, 1, 13, -7);
        walls(8, 1, 1, 12, -7);
        walls(9, 1, 1, 12, -6);
        walls(13, 1, 1, 12, -11);
        walls(19, 4, 1, 13, -11);
        walls(3, 1, 1, 17, -11);
        walls(4, 1, 1, 17, -12);
        walls(18, 1, 1, 17, -13);
        walls(14, 4, 1, 13, -13);
        walls(8, 1, 1, 12, -13);
        walls(9, 1, 1, 12, -12);
        walls(8, 1, 1, 18, -10);
        walls(9, 1, 1, 18, -9);
        walls(13, 1, 1, 18, -8);
        walls(19, 2, 1, 19, -8);
        walls(46, 1, 1, 21, -8);
        walls(17, 1, 2, 21, -7);
        walls(1, 1, 1, 21, -5);
        walls(4, 1, 1, 21, -9);
        walls(18, 1, 1, 21, -10);
        walls(14, 2, 1, 19, -10);
        walls(1, 1, 1, 37, 3);
        walls(17, 1, 2, 37, 1);
        walls(44, 1, 1, 37, 0);
        walls(44, 1, 1, 37, 0);
        walls(12, 5, 1, 38, 0);
        walls(40, 1, 1, 43, 0);
        walls(17, 1, 11, 43, 1);
        walls(32, 1, 1, 43, 12);
        walls(12, 11, 1, 44, 12);
        walls(7, 1, 1, 55, 12);
    }
    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
