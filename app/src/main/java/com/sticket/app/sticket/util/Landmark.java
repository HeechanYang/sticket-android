package com.sticket.app.sticket.util;

import java.io.Serializable;
import java.util.Map;

import static com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark.LEFT_CHEEK;
import static com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark.LEFT_EAR;
import static com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark.LEFT_EYE;
import static com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark.MOUTH_BOTTOM;
import static com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark.NOSE_BASE;
import static com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark.RIGHT_CHEEK;
import static com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark.RIGHT_EAR;
import static com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark.RIGHT_EYE;

public enum Landmark implements Serializable {
    EYE_LEFT(62.01901140f, 42.8571428f, LEFT_EYE),
    EYE_RIGHT(33.038022813f, 42.8571428f, RIGHT_EYE),
    GLASSES(47.5f, 42.8571428f, -1),
    NOSE(47.6f, 52.9f, NOSE_BASE),
    MOUTH(49.4f, 66.1f, MOUTH_BOTTOM),
    CHEEK_LEFT(71.2f, 58.2f, LEFT_CHEEK),
    CHEEK_RIGHT(27.7f, 58.2f, RIGHT_CHEEK),
    EAR_LEFT(90.49430f, 47.61905f, LEFT_EAR),
    EAR_RIGHT(11.95455f, 47.61905f, RIGHT_EAR);

    private final float x;
    private final float y;
    private final int no;


    Landmark(float x, float y, int no) {
        this.x = x;
        this.y = y;
        this.no = no;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getNo() {
        return no;
    }
}
