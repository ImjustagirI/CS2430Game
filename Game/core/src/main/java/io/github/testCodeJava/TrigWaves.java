package io.github.testCodeJava;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class TrigWaves extends Functions{
    private float period;
    private float amp;
    private int mode;
    public TrigWaves(int mult, float b, float offset, float period, float amp, int mode, int[] sceneNeeded) {
        super(mult, b, offset, sceneNeeded);
        this.period = period;
        this.amp = amp;
        this.mode = mode;
    }

    public float getPeriod() {
        return period;
    }

    public void setPeriod(float period) {
        this.period = period;
    }

    public float getAmp() {
        return amp;
    }

    public void setAmp(float amp) {
        this.amp = amp;
    }
    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    @Override
    float getFunctionY(float x, Vector2 max) {
        float curOff = getOffset() + 225;
        float newB = getB() - 225;
        return (swap((float) ((x + curOff) * period / 180 * Math.PI))) * amp + newB + 1000 - max.y;
    }
    @Override
    void draw(Vector2 max, SpriteBatch batch, Texture[] image2, int[] currentScene, boolean swapImage){
        float curOff = getOffset() + 225;
        float newB = getB() - 225;
        for (int i = (int) (990 - max.x) * getMult(); i < (-max.x + 2010) * getMult(); i++) {
            batch.begin();
            if ((swap((float) (((float) i / getMult() + curOff) * period / 180 * Math.PI))) * amp + newB + 1000 - max.y < 1025 && (swap((float) (((float) i / getMult() + curOff) * period / 180 * Math.PI))) * amp + newB + 1000 - max.y > -25 && currentScene[0] == getSceneNeeded()[0] && currentScene[1] == getSceneNeeded()[1]){
                batch.draw((swapImage) ? image2[1] : image2[0], (float) i / getMult() + max.x - 1000, (swap((float) (((float) i / getMult() + curOff) * period / 180 * Math.PI))) * amp + newB + 1000 - max.y);
            }
            batch.end();
        }
    }
    float swap(float middle){
        if (mode == 0){
            return (float) Math.sin(middle);
        } else if (mode == 1)
        {
            return (float) Math.cos(middle);
        }
        else{
            return (float) Math.tan(middle);
        }
    }
}