package io.github.testCodeJava;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

abstract class Functions {
    private int mult;
    private float b;
    private float offset;
    private int[] sceneNeeded;
    public Functions(int mult, float b, float offset, int[] sceneNeeded) {
        this.mult = mult;
        this.b = b;
        this.offset = offset;
        this.sceneNeeded = sceneNeeded;
    }

    public int getMult() {
        return mult;
    }

    public void setMult(int mult) {
        this.mult = mult;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public int[] getSceneNeeded() {
        return sceneNeeded;
    }

    public void setSceneNeeded(int[] sceneNeeded) {
        this.sceneNeeded = sceneNeeded;
    }

    abstract float getFunctionY(float x, Vector2 max);
    abstract void draw(Vector2 max, SpriteBatch batch, Texture[] image2, int[] currentScene, boolean swapImage);
}
