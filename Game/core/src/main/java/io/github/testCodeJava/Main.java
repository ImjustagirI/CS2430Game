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
    public Vector2 max = new Vector2(422, 737);
    public Vector2 position = new Vector2(0, 0);
    public Vector2 increment = new Vector2(16, 16);
    Sprite character;
    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        image1 = new Texture("bottle.png");
        character = new Sprite(image1);
        character.setScale(0.3f);
    }
    @Override
    public void render() {
        if(position.x >= 422 && increment.x > 0){
            increment.x *= -1;
        }
        if(position.y >= 737 && increment.y > 0){
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
        character.setY(800 - Gdx.input.getY() - image1.getHeight() / 2f);
        batch.draw(image, position.x, position.y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
