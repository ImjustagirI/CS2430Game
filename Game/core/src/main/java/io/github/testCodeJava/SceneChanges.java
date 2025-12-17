package io.github.testCodeJava;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Arrays;

class SceneChanges extends Interactables {
    private int[] newScene;
    public SceneChanges(Vector2 location, Vector2 size, int[] sceneReq, int[] newScene) {
        super(location, size, sceneReq);
        this.newScene = newScene;
    }
    @Override
    public void draw(TextureRegion[] objects, SpriteBatch batch, Vector2 max, float i, int[] sceneNum, boolean[] collected, boolean[] completed){
        if(getLocation().y - max.y + 1000 < 560 && getLocation().y - max.y + 1000 + 5 > 440 - getSize().y && getLocation().x - 1000 + max.x < 560 && getLocation().x - 1000 + max.x > 440 - getSize().x){
            sceneNum[5] = 1;
        }
        if(getLocation().y - max.y + 1000 < 1025 && getLocation().y - max.y + 1000 > -25 && getLocation().x - 1000 + max.x < 1025 && getLocation().x - 1000 + max.x > -25 && sceneNum[0] == getSceneReq()[0] && sceneNum[1] == getSceneReq()[1]){
            batch.begin();
            batch.draw((completed[newScene[0]]) ? objects[3] : objects[0], getLocation().x - 1000 + max.x, (getLocation().y - max.y + 1000), getSize().x, getSize().y);
            batch.end();
            if(getLocation().y - max.y + 1000 < 560 && getLocation().y - max.y + 1000 + 5 > 440 - getSize().y && getLocation().x - 1000 + max.x < 560 && getLocation().x - 1000 + max.x > 440 - getSize().x && sceneNum[6] == 0 && !completed[newScene[0]]){
                sceneNum[2] = 1;
                sceneNum[6] = 1;
                sceneNum[3] = newScene[0];
                sceneNum[4] = newScene[1];
            }
        }
    }

}