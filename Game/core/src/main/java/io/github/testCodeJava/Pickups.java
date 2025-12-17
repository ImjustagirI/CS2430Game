package io.github.testCodeJava;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
class Pickups extends Interactables{
    private int objectNum;
    private int need;

    public Pickups(int objectNum, Vector2 location, Vector2 size, int sceneReq, int need) {
        super(location, size, new int[]{sceneReq, 0});
        this.objectNum = objectNum;
        this.need = need;
    }

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    @Override
    public void draw(TextureRegion[] objects, SpriteBatch batch, Vector2 max, float i, int[] sceneNum, boolean[] collected, boolean[] completed){
        if (completed[need]){
            if(getLocation().y - max.y + 1000 < 1025 && getLocation().y - max.y + 1000 > -25 && getLocation().x - 1000 + max.x < 1025 && getLocation().x - 1000 + max.x > -25 && sceneNum[0] == getSceneReq()[0] && sceneNum[1] == 0 && !collected[objectNum]) {
                batch.begin();
                batch.draw(objects[objectNum], getLocation().x - 1000 + max.x, (float) (getLocation().y - max.y + 1000 + 5 * Math.sin(Math.toRadians(i))), getSize().x, getSize().y);
                batch.end();
                if (getLocation().y - max.y + 1000 + 5 * Math.sin(Math.toRadians(i)) < 560 && getLocation().y - max.y + 1000 + 5 * Math.sin(Math.toRadians(i)) > 440 - getSize().y && getLocation().x - 1000 + max.x < 560 && getLocation().x - 1000 + max.x > 440 - getSize().x) {
                    collected[objectNum] = true;
                }
            }
        }
    }

}