package com.snowofsunflower.android.image;

/**
 * Created by zhouztashin on 2018/10/31.
 */

public class ImageRound implements IImageShape {
    private int round;
    public ImageRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
