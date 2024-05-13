package com.example.sortingvisualisation.sortingalgorithms;

import com.example.sortingvisualisation.SNode;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort extends AbstractSort {

    private static final Color PIVOT_COLOR = Color.DARKMAGENTA;
    private ArrayList<Transition> transitions;

    public QuickSort() {
        this.transitions = new ArrayList<>();
    }

    private void quickSort(SNode[] arr, int lo, int hi) {
        if (lo < hi) {
            int q = partition(arr, lo, hi);
            quickSort(arr, lo, q - 1);
            quickSort(arr, q + 1, hi);
        }
    }

    //last elt of array chosen as pivot
    private int partition(SNode[] arr, int lo, int hi) {
        int i = lo;

        transitions.add(colorSNode(arr, PIVOT_COLOR, hi));

        for (int j = lo; j < hi; j++) {
            transitions.add(colorSNode(arr, SELECT_COLOR, j));
            if (arr[j].getValue() < arr[hi].getValue()) {
                transitions.add(swap(arr, i, j));
                transitions.add(colorSNode(arr, START_COLOR, i));
                i++;
            } else {
                transitions.add(colorSNode(arr, START_COLOR, j));
            }
        }
        transitions.add(swap(arr, i, hi));
        transitions.add(colorSNode(arr, START_COLOR, i));

        return i;
    }

    @Override
    public ArrayList<Transition> startSort(SNode[] arr) {
        quickSort(arr, 0, arr.length - 1);
        transitions.add(colorSNode(Arrays.asList(arr), SORTED_COLOR));

        return transitions;
    }
}