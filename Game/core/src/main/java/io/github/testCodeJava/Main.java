package io.github.testCodeJava;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import javax.swing.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Texture image1;
    private Texture image2;
    public Vector2 max = new Vector2(622, 937);
    public Vector2 position = new Vector2(0, 0);
    public Vector2 increment = new Vector2(16, 16);
    Sprite character;
    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        image1 = new Texture("bottle.png");
        image2 = new Texture("ircle.png");
        character = new Sprite(image1);
        character.setScale(0.3f);

    }
    @Override
    public void render() {
        if(position.x >= 622 && increment.x > 0){
            increment.x *= -1;
        }
        if(position.y >= 937 && increment.y > 0){
            increment.y *= -1;
        }
        if(position.x <= 0 && increment.x < 0){
            increment.x *= -1;
        }
        if(position.y <= 0 && increment.y < 0){
            increment.y *= -1;
        }
        position.x += increment.x;
        position.y += increment.y;
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        character.draw(batch);
        character.setX(Gdx.input.getX() - image1.getWidth() / 2f);
        character.setY(1000 - Gdx.input.getY() - image1.getHeight() / 2f);
        batch.draw(image, position.x, position.y);
        batch.end();
        //sine(2, 1.0f, 475.0f, 500f);
        //cosine(2, 1.0f, 475.0f, 500f);
        //tangent(20, 1.0f, 475.0f, 500f);
        //linear(2, 2, 20);
        //quadratic(1, 0.125f, 0, 0, -200);
        //cubic(1, 0.001f, 0.1f, 0,800, -200);
        exponential(4, 1f, 0.7f, -200, 50);
    }
    public void linear(int mult, float m, float b){
        for (int i = 0; i < 1000 * mult; i++) {
            batch.begin();
            batch.draw(image2, (float) i / mult, m * i / mult + b);
            batch.end();
            if(m * i / mult + b > 1000 || m * i / mult + b < -1000){
                return;
            }
        }
    }
    public void exponential(int mult, float m, float b, float offset, float moveUp){
        for (int i = 0; i < 1000 * mult; i++) {
            batch.begin();
            float value = (float) Math.pow(m * i / mult + offset, b);
            batch.draw(image2, (float) i / mult, value + moveUp);
            batch.end();
            if(value + moveUp > 1000 || value + moveUp < -1000){
                return;
            }
        }
    }
    public void quadratic(int mult, float m1, float m2, float b, float offset){
        boolean done = false;
        for (int i = 0; i < 1000 * mult; i++) {
            batch.begin();
            batch.draw(image2, (float) i / mult, m1 * (float)Math.pow(((double) i / mult + offset), 2) + m2 * i / mult + offset + b);
            batch.end();
            if (m1 * (float)Math.pow(((double) i / mult + offset), 2) + m2 * i / mult + offset + b > 1000 || m1 * (float)Math.pow(((double) i / mult + offset), 2) + m2 * i / mult + offset + b < -1000){
                if (done) return;
            }
            else{
                done = true;
            }
        }
    }
    public void cubic(int mult, float m1, float m2, float m3, float b, float offset){
        boolean done = false;
        for (int i = 0; i < 1000 * mult; i++) {
            batch.begin();
            batch.draw(image2, (float) i / mult, m1 * (float)Math.pow(((double) i / mult + offset), 3) + m2 * (float)Math.pow(((double) i / mult + offset), 2) + m3 * i / mult + offset + b);
            batch.end();
            if (m1 * (float)Math.pow(((double) i / mult + offset), 3) + m2 * (float)Math.pow(((double) i / mult + offset), 2) + m3 * i / mult + offset + b > 1000 || m1 * (float)Math.pow(((double) i / mult + offset), 3) + m2 * (float)Math.pow(((double) i / mult + offset), 2) + m3 * i / mult + offset + b < -1000){
                if (done) return;
            }
            else{
                done = true;
            }
        }
    }
    public void sine(int mult, float period, float amp, float offset){
        for (int i = 0; i < 1000 * mult; i++) {
            batch.begin();
            batch.draw(image2, (float) i / mult, (float) (Math.sin((float) i / mult * period / 180 * Math.PI)) * amp + offset);
            batch.end();
        }
    }
    public void cosine(int mult, float period, float amp, float offset){
        for (int i = 0; i < 1000 * mult; i++) {
            batch.begin();
            batch.draw(image2, (float) i / mult, (float) (Math.sin((float) i / mult * period / 180 * Math.PI + (Math.PI / 2))) * amp + offset);
            batch.end();
        }
    }
    public void tangent(int mult, float period, float amp, float offset){
        for (int i = 0; i < 1000 * mult; i++) {
            batch.begin();
            if ((float) (Math.tan((float) i / mult * period / 180 * Math.PI + (Math.PI / 2))) * amp + offset < 1000 && (float) (Math.tan((float) i / mult * period / 180 * Math.PI + (Math.PI / 2))) * amp + offset > -1000){
                batch.draw(image2, (float) i / mult, (float) (Math.tan((float) i / mult * period / 180 * Math.PI + (Math.PI / 2))) * amp + offset);
            }
            batch.end();
        }
    }
    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
