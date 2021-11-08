/*
 * Course: CS1021
 * Winter 2021
 * Lab Game Of Life
 * Name: Benjamin Fouch
 * Created 1/14/2021
 */

package msoe.cs1021.lab5;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * this class will display all of the functionality of the lifeguard, and will help update the
 * cell status when a user clicks on it
 */
public class Lab5 extends Application {

    private static final int SPACING_VALUE = 10;
    private static final int LIFE_GRID_WIDTH = 400;
    private static final int LIFE_GRID_HEIGHT = 400;
    private static final Insets INSETS = new Insets(10);

    Pane lifeGridPane = new Pane();
    LifeGrid lifeGrid = new LifeGrid(lifeGridPane, LIFE_GRID_WIDTH, LIFE_GRID_HEIGHT);
    private Text statsText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button randomize = new Button("Randomize");
        randomize.setOnAction(this::randomizeHandler);

        Button iterate = new Button("Iterate");
        iterate.setOnAction(this::iterate);

        statsText = new Text(lifeGrid.getNumOfCellsAliveAndDead());

        lifeGridPane.setOnMouseClicked(this::clickCells);

        Pane pane = new Pane();
        HBox hBoxTop = new HBox();
        HBox hBoxBottom = new HBox();
        VBox vBoxR = new VBox();
        VBox vBoxL = new VBox();
        HBox master = new HBox();

        hBoxBottom.setSpacing(SPACING_VALUE);
        vBoxL.setSpacing(SPACING_VALUE);
        vBoxL.setPadding(INSETS);

        hBoxTop.getChildren().addAll(statsText);
        hBoxBottom.getChildren().addAll(randomize, iterate);
        vBoxL.getChildren().addAll(hBoxTop, hBoxBottom);
        vBoxR.getChildren().addAll(lifeGridPane);
        master.getChildren().addAll(vBoxR, vBoxL);
        pane.getChildren().addAll(master);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * tells the life grid to randomize when the random button is clicked
     * @param e The action even from the random button being clicked
     */
    private void randomizeHandler(ActionEvent e){
        lifeGrid.randomize();
        statsText.setText(lifeGrid.getNumOfCellsAliveAndDead());
    }

    /**
     * tells the life grid to move a step forward when the iterate button is clicked
     * @param e The action even from the iterate button being clicked
     */
    private void iterate(ActionEvent e){
        lifeGrid.iterate();
        statsText.setText(lifeGrid.getNumOfCellsAliveAndDead());
    }

    /**
     * tells the life grid what cell needs to be updated based of the click location of the mouse
     * @param e the event from the pane being clicked
     */
    private void clickCells(MouseEvent e){
        lifeGrid.toggleCellStatus((int)(e.getSceneX()/Cell.SCALE),
                (int)(e.getSceneY()/Cell.SCALE));
        statsText.setText(lifeGrid.getNumOfCellsAliveAndDead());

    }

}
