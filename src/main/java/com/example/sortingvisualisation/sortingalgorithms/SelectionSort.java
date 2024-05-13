package com.example.sortingvisualisation.sortingalgorithms;

import com.example.sortingvisualisation.SNode;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort extends AbstractSort {

    private static final Color MININDX_COLOR = Color.ORANGE;
    private static final Color NEW_MININDX_COLOR = Color.LIGHTGREEN;

    private ParallelTransition colorSNode(SNode[] arr, int a, int b, Color colorA, Color colorB) {
        ParallelTransition pt = new ParallelTransition();

        pt.getChildren().addAll(colorSNode(arr, colorA, a), colorSNode(arr, colorB, b));

        return pt;
    }

    @Override
    public ArrayList<Transition> startSort(SNode[] arr) {
        ArrayList<Transition> transitions = new ArrayList<>();
        int minIndx;

        for (int i = 0; i < arr.length - 1; i++) {
            minIndx = i;
            transitions.add(colorSNode(arr, NEW_MININDX_COLOR, minIndx));

            for (int j = i + 1; j < arr.length; j++) {
                transitions.add(colorSNode(arr, SELECT_COLOR, j));
                if (arr[j].getValue() < arr[minIndx].getValue()) {
                    if (minIndx == i) {
                        transitions.add(colorSNode(arr, minIndx, j, MININDX_COLOR, NEW_MININDX_COLOR));
                    } else {
                        transitions.add(colorSNode(arr, minIndx, j, Color.CRIMSON, NEW_MININDX_COLOR));
                    }
                    minIndx = j;
                } else {
                    transitions.add(colorSNode(arr, START_COLOR, j));
                }
            }

            if (minIndx != i) {
                transitions.add(swap(arr, i, minIndx));
            }

            transitions.add(colorSNode(arr, START_COLOR, i, minIndx));
        }

        transitions.add(colorSNode(Arrays.asList(arr), SORTED_COLOR));

        return transitions;
    }
}
