package io.github.testCodeJava;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class ExtendedLinear extends Functions{
    private float[] m;

    public ExtendedLinear(int mult, float b, float offset, float[] m, int[] sceneNeeded) {
        super(mult, b, offset, sceneNeeded);
        this.m = m;
    }

    public float[] getM() {
        return m;
    }

    public void setM(float[] m) {
        this.m = m;
    }

    @Override
    float getFunctionY(float x, Vector2 max) {
        float curOff = getOffset() - 225;
        float newB = getB() + 225;
        return m[0] * (float)Math.pow((x - (max.x - 1000) + curOff), 3) + m[1] * (float)Math.pow((x - (max.x - 1000) + curOff), 2) + m[2] * ( x - (max.x - 1000) + curOff) + newB + 1000 - max.y;
    }
    @Override
    void draw(Vector2 max, SpriteBatch batch, Texture[] image2, int[] currentScene, boolean swapImage){
        float curOff = getOffset() - 225;
        float newB = getB() + 225;
        for (int i = (int) (990 - max.x) * getMult(); i < (-max.x + 2010) * getMult(); i++) {
            batch.begin();
            if (m[0] * (float)Math.pow(((double) i / getMult() + curOff), 3) + m[1] * (float)Math.pow(((double) i / getMult() + curOff), 2) + m[2] * ((float) i / getMult() + curOff) + newB + 1000 - max.y < 1025 && m[0] * (float)Math.pow(((double) i / getMult() + curOff), 3) + m[1] * (float)Math.pow(((double) i / getMult() + curOff), 2) + m[2] * ((float) i / getMult() + curOff) + newB + 1000 - max.y > -25 && currentScene[0] == getSceneNeeded()[0] && currentScene[1] == getSceneNeeded()[1]){
                batch.draw((swapImage) ? image2[1] : image2[0], (float) i / getMult() + max.x - 1000, m[0] * (float)Math.pow(((double) i / getMult() + curOff), 3) + m[1] * (float)Math.pow(((double) i / getMult() + curOff), 2) + m[2] * ((float) i / getMult() + curOff) + newB + 1000 - max.y);}
            batch.end();
        }
    }
}
