package com.example.sortingvisualisation.sortingalgorithms;

import com.example.sortingvisualisation.SNode;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertionSort extends AbstractSort {

    private ArrayList<Transition> transitions;

    public InsertionSort() {
        this.transitions = new ArrayList<>();
    }

    @Override
    public ArrayList<Transition> startSort(SNode[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            SNode key = arr[i];
            int keyIndx = i;

            ParallelTransition pt = new ParallelTransition();

            transitions.add(colorSNode(arr, SELECT_COLOR, i));

            while(j >= 0 && arr[j].getValue() > key.getValue()) {
                pt.getChildren().add(arr[j].moveX(DX));
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;

            pt.getChildren().add(key.moveX(DX * (j + 1 - i)));
            transitions.add(pt);
            transitions.add(colorSNode(arr, START_COLOR, j + 1));

        }

        transitions.add(colorSNode(Arrays.asList(arr), SORTED_COLOR));

        return transitions;

    }

}
