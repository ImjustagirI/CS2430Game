package io.github.testCodeJava;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

abstract class Interactables {
    private Vector2 location;
    private Vector2 size;
    private int[] sceneReq;

    public Interactables(Vector2 location, Vector2 size, int[] sceneReq) {
        this.location = location;
        this.size = size;
        this.sceneReq = sceneReq;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public int[] getSceneReq() {
        return sceneReq;
    }

    public void setSceneReq(int[] sceneReq) {
        this.sceneReq = sceneReq;
    }

    abstract void draw(TextureRegion[] objects, SpriteBatch batch, Vector2 max, float i, int[] sceneNum, boolean[] collected, boolean[] completed);
}