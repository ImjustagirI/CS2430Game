package io.github.testCodeJava;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Expos extends Functions{
    private float m;
    private float pow;

    public Expos(int mult, float b, float offset, float m1, float pow, int[] sceneNeeded) {
        super(mult, b, offset, sceneNeeded);
        this.m = m1;
        this.pow = pow;
    }

    public float getM() {
        return m;
    }

    public void setM(float m) {
        this.m = m;
    }

    public float getPow() {
        return pow;
    }

    public void setPow(float pow) {
        this.pow = pow;
    }

    @Override
    float getFunctionY(float x, Vector2 max) {
        float curOff = getOffset() + 225;
        float newB = getB() - 225;
        return (float) Math.pow(m * x - (max.x - 1000) + curOff, pow) + newB + 1000 - max.y;
    }
    @Override
    void draw(Vector2 max, SpriteBatch batch, Texture[] image2, int[] currentScene, boolean swapImage){
        float curOff = getOffset() + 225;
        float newB = getB() - 225;
        for (int i = (int) (990 - max.x) * getMult(); i < (-max.x + 2010) * getMult(); i++) {
            batch.begin();
            float value = (float) Math.pow(m * i / getMult() + curOff, pow);
            if(value + newB + 1000 - max.y < 1025 && value + newB + 1000 - max.y > -25 && currentScene[0] == getSceneNeeded()[0] && currentScene[1] == getSceneNeeded()[1]){
                batch.draw((swapImage) ? image2[1] : image2[0], (float) i / getMult() + max.x - 1000, value + newB + 1000 - max.y);
            }
            batch.end();

        }
    }
}
