package com.example.sortingvisualisation;

import com.example.sortingvisualisation.sortingalgorithms.*;
import com.example.sortingvisualisation.sortingalgorithms.AbstractSort;
import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimationController extends BorderPane {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 500;
    public static final int XGAP = 10;
    public static final int BUTTONROW_BOUNDARY = 100;

    public static int NO_OF_SNODES = 40;

    private static AbstractSort abstractSort;

    private Pane display;
    private HBox buttonRow;

    private Button sortButton;
    private Button randomButton;
    private ChoiceBox<AbstractSort> choiceBox;

    private SNode[] snodes;

    public AnimationController() {
        this.display = new Pane();
        this.buttonRow = new HBox();

        this.setCenter(display);
        this.setBottom(buttonRow);

        this.sortButton = new Button("Sort");
        this.randomButton = new Button("Random");
        this.choiceBox = new ChoiceBox<>();

        this.snodes = RandomSNodes.randomNodes(NO_OF_SNODES);

        buttonRow.getChildren().add(sortButton);
        buttonRow.getChildren().add(randomButton);
        buttonRow.getChildren().add(choiceBox);

        buttonRow.setAlignment(Pos.CENTER);

        for (Node b : buttonRow.getChildren()) {
            buttonRow.setMargin(b, new Insets(5, 5, 20, 5));
        }


        List<AbstractSort> abstractSortList = new ArrayList<>();
        abstractSortList.add(new SelectionSort());
        abstractSortList.add(new InsertionSort());
        abstractSortList.add(new MergeSort());
        abstractSortList.add(new QuickSort());


        display.getChildren().addAll(Arrays.asList(snodes));

        sortButton.setOnAction(event -> {
            sortButton.setDisable(true);
            randomButton.setDisable(true);

            abstractSort = choiceBox.getSelectionModel().getSelectedItem();

            SequentialTransition sq = new SequentialTransition();

            sq.getChildren().addAll(abstractSort.startSort(snodes));

            sq.setOnFinished(e -> {
                randomButton.setDisable(false);
            });

            sq.play();

        });

        randomButton.setOnAction(event -> {
            sortButton.setDisable(false);
            display.getChildren().clear();

            snodes = RandomSNodes.randomNodes(NO_OF_SNODES);

            display.getChildren().addAll(Arrays.asList(snodes));
        });

        choiceBox.setItems(FXCollections.observableArrayList(
                abstractSortList
        ));

        choiceBox.getSelectionModel().select(5);

        choiceBox.setConverter(new StringConverter<AbstractSort>() {
            @Override
            public String toString(AbstractSort abstractSort) {
                if(abstractSort == null) {
                    return "";
                } else {
                    return abstractSort.getClass().getSimpleName();
                }
            }

            @Override
            public AbstractSort fromString(String s) {
                return null;
            }
        });

    }

}
