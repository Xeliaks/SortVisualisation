package com.example.sortingvisualisation.sortingalgorithms;

import com.example.sortingvisualisation.AnimationController;
import com.example.sortingvisualisation.SNode;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSort {

    final Color START_COLOR = Color.CRIMSON;
    final Color SELECT_COLOR = Color.CYAN;
    final Color SORTED_COLOR = Color.DARKGREEN;

    static int DX;
    static {
        DX = AnimationController.WINDOW_WIDTH / AnimationController.NO_OF_SNODES;
    }

    ParallelTransition colorSNode(SNode[] arr, Color color, int...a) {
        ParallelTransition pt = new ParallelTransition();

        for (int i = 0; i < a.length; i++) {
            FillTransition ft = new FillTransition();
            ft.setShape(arr[a[i]]);
            ft.setToValue(color);
            ft.setDuration(Duration.millis(100));
            pt.getChildren().add(ft);
        }
        return pt;
    }

    ParallelTransition colorSNode(List<SNode> list, Color color) {
        ParallelTransition pt = new ParallelTransition();

        for (SNode c : list) {
            FillTransition ft = new FillTransition();
            ft.setShape(c);
            ft.setToValue(color);
            ft.setDuration(Duration.millis(100));
            pt.getChildren().add(ft);
        }

        return pt;
    }

    ParallelTransition swap(SNode[] arr, int i, int j) {
        ParallelTransition pt = new ParallelTransition();

        int dxFactor = j - i;

        pt.getChildren().addAll(arr[i].moveX(DX * dxFactor), arr[j].moveX(-DX * dxFactor));

        SNode tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

        return pt;
    }

    public abstract ArrayList<Transition> startSort(SNode[] arr);
}