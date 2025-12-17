package io.github.testCodeJava;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

class Problems{
    private boolean[] used;
    private int[] current;
    private int lengths;
    private int[] sceneReq;
    private int selected = -1;
    public int flashNum = 0;
    public Vector2[] batteries;

    public Problems(boolean[] used, int[] current, int lengths, int[] sceneReq, Vector2[] batteries) {
        this.used = used;
        this.current = current;
        this.lengths = lengths;
        this.sceneReq = sceneReq;
        this.batteries = batteries;
    }

    public Vector2[] getBatteries() {
        return batteries;
    }

    public void setBatteries(Vector2[] batteries) {
        this.batteries = batteries;
    }

    public boolean[] getUsed() {
        return used;
    }

    public void setUsed(boolean[] used) {
        this.used = used;
    }

    public int[] getCurrent() {
        return current;
    }

    public void setCurrent(int[] current) {
        this.current = current;
    }

    public int getLengths() {
        return lengths;
    }

    public void setLengths(int lengths) {
        this.lengths = lengths;
    }

    public int[] getSceneReq() {
        return sceneReq;
    }

    public void setSceneReq(int[] sceneReq) {
        this.sceneReq = sceneReq;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getFlashNum() {
        return flashNum;
    }

    public void setFlashNum(int flashNum) {
        this.flashNum = flashNum;
    }

    public void draw(SpriteBatch batch, TextureRegion[] fullImages, int[] currentScene, boolean graphMode){
        int flashNums = getFlashNum();
        if (!graphMode && currentScene[0] == sceneReq[0] && currentScene[1] == sceneReq[1]) {
            batch.begin();
            for (int i = 0; i < lengths; i++) {
                if (!used[i]){
                    batch.draw(fullImages[fullImages.length - 1], 25 + (i % 10) * 92f,1000 - ((i > 9) ? 212.5f : 118.75f), fullImages[current[i]].getRegionWidth() * 3.125f, fullImages[fullImages.length - 1].getRegionHeight() * 1.5625f);
                }
                batch.draw((flashNums > 180 && selected == i) ? fullImages[fullImages.length - 2] : fullImages[current[i]], 25 + (i % 10) * 92f,975 - 0.6f * fullImages[current[i]].getRegionHeight() * 3.125f - ((i > 9) ? 125 : 31.25f), fullImages[current[i]].getRegionWidth() * 3.125f, fullImages[current[i]].getRegionHeight() * 3.125f);
            }
            batch.end();
        }
        if (graphMode && currentScene[0] == sceneReq[0] && currentScene[1] == sceneReq[1]) {
            batch.begin();
            batch.draw(fullImages[25], batteries[0].x + 225, batteries[0].y + 225, 150, 150);
            batch.draw(fullImages[25], batteries[1].x + 225, batteries[1].y + 225, 150, 150);
            batch.end();
        }
    }
    public Functions turn(int[] currentScene){
        if (currentScene[0] == sceneReq[0] && currentScene[1] == sceneReq[1]) {
            if (sceneReq[0] == 0) {
                if (current[1] == 20){
                    return new ExtendedLinear(500, 0, 0, new float[]{0, 10, -4000}, new int[]{0, 1});
                }
            }
            if (sceneReq[0] == 1) {
                if (current[1] == 20 && current[6] == 20 && current[7] == 15 && current[16] == 15){
                    return new ExtendedLinear(20, 0, 0, new float[]{0, 0, 18}, new int[]{1, 1});
                }
            }
            if (sceneReq[0] == 2) {
                if (current[2] == 15 && current[6] == 20 && current[8] == 20){
                    return new ExtendedLinear(20, -500, 0, new float[]{0, 0, 1}, new int[]{2, 1});
                }
            }
            if (sceneReq[0] == 3) {
                if (current[1] == 20 && isNumberOr(current[7]) && isNumberOr(current[8]) && isNumberOr(current[9])){
                    for (int j : current) {
                        if (j == 33) {
                            return new Expos(0, 0, 2, 0, 1, new int[]{1, 1});
                        }
                    }
                    return new TrigWaves(20, toNumber(11, 4) * -2, -90, 1, toNumber(7, 3), 0, new int[]{3, 1});
                }
            }
            if (sceneReq[0] == 4) {
                for (int i = 0; i < current.length; i++) {
                    if (!used[i]){
                        if (!isNumberOr(current[i])){
                            return new Expos(0, 0, 2, 0, 1, new int[]{1, 1});
                        }
                    }
                }
                return new ExtendedLinear(200, 0, 0, new float[]{9 * Float.parseFloat(toChar(current[2])), 18 * Float.parseFloat(toChar(current[2])), 2 * toNumber(15, 4)}, new int[]{4, 1});
            }
            if (sceneReq[0] == 5) {
                for (int i = 0; i < current.length; i++) {
                    if (!used[i]){
                        if (!isNumberOr(current[i]) && !(current[i] == 15) && !(current[i] == 30)){
                            return new Expos(0, 0, 2, 0, 1, new int[]{1, 1});
                        }
                    }
                }
                if (current[4] == 15 || current[5] == 15 || current[9] == 15 || current[10] == 15 || current[15] == 15 || current[16] == 15 || current[3] == 30 || current[5] == 30 || current[8] == 30 || current[10] == 30 || current[14] == 30 || current[16] == 30){
                    return new Expos(0, 0, 2, 0, 1, new int[]{1, 1});
                }
                return new ExtendedLinear(20, toNumber(14, 3), 0, new float[]{0, 0, toNumber(3,3) * toNumber(8, 3)}, new int[]{5, 1});
            }
            if (sceneReq[0] == 6) {
                if (current[3] == 19){
                    return new ExtendedLinear(5, 0, 0, new float[]{0, 0, 1}, new int[]{6, 1});
                }
            }
            if (sceneReq[0] == 7) {
                if (isNumber(current[3])){
                    if (current[3] == current[7] && current[7] == current[12] && current[12] == current[16] && current[9] == 19){
                        return new ExtendedLinear(5, 0, 0, new float[]{0, 0, 1}, new int[]{7, 1});
                    }
                }
            }
            if (sceneReq[0] == 8) {
                for (int i = 0; i < current.length; i++) {
                    if (!used[i]){
                        if (!isNumberOr(current[i]) && !(current[i] == 15) && !(current[i] == 30) && !(current[i] == 19)){
                            return new Expos(0, 0, 2, 0, 1, new int[]{1, 1});
                        }
                    }
                }
                if (current[10] == 19 && current[18] != 19){
                    if (valid(2, 5) && valid(9, 3) && valid(13,2) && valid(17, 1) && valid(19, 1) && current[16] == 19){
                        return new ExtendedLinear(5, toNumber(17, 1) + toNumber(19, 1), 0, new float[]{0, 0, (toNumber(2, 5)) - (toNumber(9, 3) * toNumber(13, 2))}, new int[]{8, 1});
                    }
                }
                if (current[10] != 19 && current[18] != 19){
                    if (valid(2, 5) && valid(9, 3) && valid(13,2) && valid(17, 3) && current[16] == 19){
                        return new ExtendedLinear(5, toNumber(17, 3), 0, new float[]{0, 0, (toNumber(2, 5)) - (toNumber(9, 3) * toNumber(13, 2))}, new int[]{8, 1});
                    }
                }
            }
            if (sceneReq[0] == 9) {
                for (int i = 0; i < current.length; i++) {
                    if (!used[i]){
                        if (!isNumberOr(current[i]) && !(current[i] == 15) && !(current[i] == 30) && !(current[i] == 19)){
                            return new Expos(0, 0, 2, 0, 1, new int[]{1, 1});
                        }
                    }
                }
                if (current[6] != 19 && current[7] != 19){
                    if (valid(2, 1) && valid(5, 3) && valid(10,1)){
                        return new ExtendedLinear(20, toNumber(5, 3) * toNumber(10, 1), 0, new float[]{0, toNumber(2, 1), 0}, new int[]{9, 1});
                    }
                }
                if (current[6] == 19 && current[7] != 19){
                    if (valid(2, 1) && valid(5, 3) && valid(10,1)){
                        return new ExtendedLinear(20, toNumber(5, 1) + toNumber(7, 1) * toNumber(10, 1), 0, new float[]{0, toNumber(2, 1), 0}, new int[]{9, 1});
                    }
                }
                if (current[6] != 19 && current[7] == 19){
                    if (valid(2, 1) && valid(5, 3) && valid(10,1)){
                        return new ExtendedLinear(20, toNumber(5, 2) + toNumber(10, 1), 0, new float[]{0, toNumber(2, 1), 0}, new int[]{9, 1});
                    }
                }
            }
            if (sceneReq[0] == 10) {
                if (current[2] == 1 || current[2] == 2 || current[2] == 3){
                    return new TrigWaves(30, 450, -90, 1, 1, current[2] - 1, new int[]{10, 1});
                }
            }
            if (sceneReq[0] == 11) {
                for (int i = 0; i < current.length; i++) {
                    if (!used[i]){
                        if (!isNumberOr(current[i]) && !(current[i] == 15) && !(current[i] == 30) && !(current[i] == 19)){
                            return new Expos(0, 0, 2, 0, 1, new int[]{1, 1});
                        }
                    }
                }
                if (valid(2,4) && valid(8, 2) && valid(12, 2) && valid(16,1)){
                    return new ExtendedLinear(60, 0, 0, new float[]{toNumber(2, 4), -1 * toNumber(8, 2), toNumber(12, 2) * toNumber(16, 1)}, new int[]{11, 1});
                }
            }
            if (sceneReq[0] == 12) {
                if ((current[2] == 2 && current[6] == 3) || (current[2] == 3 && current[6] == 2)){
                    return new TrigWaves(30, 500, -90, 1, 1, 0, new int[]{12, 1});
                }
            }
        }
        return new Expos(0, 0, 2, 0, 1, new int[]{1, 1});
    }
    public boolean valid(int start, int length){
        boolean done = false;
        for (int i = start; i < start + length; i++) {
            if(!isNumberOr(current[i]) && !(current[i] == 30)){
                done = true;
            }
        }
        return (!done && count(30, start, length, 1) && count(15, start + 1, length - 1, 0) && current[start] != 30 && current[start + length - 1] != 30);
    }
    public boolean count(int num, int start, int length, int countMax){
        int count = 0;
        for (int i = start; i < start + length; i++) {
            if (current[i] == num){
                count += 1;
            }
        }
        return (count <= countMax);
    }
    public boolean isNumber(int code){
        return (code > 3 && code < 15 && code != 11);
    }
    public boolean isNumberOr(int code){
        return (code > 3 && code < 15);
    }
    public String toChar(int code){
        if (code == 4){
            return "7";
        }
        if (code == 5){
            return "4";
        }
        if (code == 6){
            return "1";
        }
        if (code == 7){
            return "0";
        }
        if (code == 8){
            return "8";
        }
        if (code == 9){
            return "5";
        }
        if (code == 10){
            return "2";
        }
        if (code == 11){
            return ".";
        }
        if (code == 12){
            return "9";
        }
        if (code == 13){
            return "6";
        }
        if (code == 14){
            return "3";
        }
        if (code == 15){
            return "-";
        }
        return "";
    }
    public float toNumber(int start, int length){
        StringBuilder total = new StringBuilder();
        boolean neg = false;
        if (Objects.equals(toChar(current[start]), "-")){
            neg = true;
        }
        else
        {
            total.append(toChar(current[start]));
        }
        if (length > 1){
            for (int i = start + 1; i < start + length; i++) {
                total.append(toChar(current[i]));
            }
        }
        if (neg)
        {
            return -1 * Float.parseFloat(total.toString());
        }
        else
        {
            return Float.parseFloat(total.toString());
        }
    }
    public void lookForChange(int[] currentScene, boolean graphMode, boolean[] collected){
        if (currentScene[0] == sceneReq[0] && currentScene[1] == sceneReq[1] && !graphMode){
            if (Gdx.input.justTouched()){
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (Gdx.input.getX() >= (25 + i * 92f) && Gdx.input.getX() <= (125 + i * 92f) && 1000 - Gdx.input.getY() >= 1000 - (119.75f + 93.75 * j) && 1000 - Gdx.input.getY() <= 1000 - (38.5f + 93.75 * j) && j * 10 + i < lengths){
                            if (!used[j * 10 + i]){
                                setSelected(j * 10 + i);
                            }
                        }
                    }
                }
                if (selected != -1) {
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (Gdx.input.getX() >= 25 + 187.5f * i && Gdx.input.getX() <= 187.5f + 187.5f * i && 1000 - Gdx.input.getY() >= 1000 - (225 + 187.5f * (j + 1)) && 1000 - Gdx.input.getY() <= 1000 - (62.5f + 187.5f * (j + 1)) && collected[i * 4 + j]) {
                                current[selected] = (i * 4 + j == 0) ? 29 : (i * 4 + j == 11) ? 30 : i * 4 + j;
                            }
                        }
                        if (Gdx.input.getX() >= 775 && Gdx.input.getX() <= 975 && 1000 - Gdx.input.getY() >= 1000 - (375 + 150 * i) && 1000 - Gdx.input.getY() <= 1000 - (250 + 150 * i + 1) && collected[16 + i]) {
                            current[selected] = 16 + i;
                        }
                    }
                    if (Gdx.input.getX() >= 775 && Gdx.input.getX() <= 975 && 1000 - Gdx.input.getY() <= 150 && 1000 - Gdx.input.getY() >= 25 && collected[20]) {
                        current[selected] = 20;
                    }
                }
            }
        }
    }
}
