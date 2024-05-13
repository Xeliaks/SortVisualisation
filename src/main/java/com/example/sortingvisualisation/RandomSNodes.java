package com.example.sortingvisualisation;

import javafx.scene.paint.Color;

import java.util.Random;

public class RandomSNodes {

    public RandomSNodes() {

    }

    public static SNode[] randomNodes(int n) {
        SNode[] arr = new SNode[n];
        Random r = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new SNode(1 + r.nextInt(arr.length));
            arr[i].setX(i * (AnimationController.WINDOW_WIDTH / arr.length));
            arr[i].setFill(Color.CRIMSON);
            setNodeDim(arr[i], arr.length);
        }
        return arr;

    }

    private static void setNodeDim(SNode cnode, int n) {
        cnode.setWidth(AnimationController.WINDOW_WIDTH / n -
                AnimationController.XGAP);
        cnode.setHeight(((AnimationController.WINDOW_HEIGHT - AnimationController.BUTTONROW_BOUNDARY)
                / n) *
                cnode.getValue());
    }
}