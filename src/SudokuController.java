
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class SudokuController implements Initializable {

    SetInitialValue setInitialValue = new SetInitialValue();
    SolveRec sudokuSolver = new SolveRec();
    Random generator = new Random();


    @FXML
    private Button button_1;
    @FXML
    private Button button_2;
    @FXML
    private Button button_3;
    @FXML
    private Button button_4;
    @FXML
    private Button button_5;
    @FXML
    private Button button_6;
    @FXML
    private Button button_7;
    @FXML
    private Button button_8;
    @FXML
    private Button button_9;
    @FXML
    private Button button_new_game;
    @FXML
    private Button button_prompt;
    @FXML
    private Button button_remove;
    @FXML
    private Button solutionButton;
    @FXML
    private Label labelSolution;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_1.setOnMouseClicked(getMouseEventEventHandler(button_1));
        button_2.setOnMouseClicked(getMouseEventEventHandler(button_2));
        button_3.setOnMouseClicked(getMouseEventEventHandler(button_3));
        button_4.setOnMouseClicked(getMouseEventEventHandler(button_4));
        button_5.setOnMouseClicked(getMouseEventEventHandler(button_5));
        button_6.setOnMouseClicked(getMouseEventEventHandler(button_6));
        button_7.setOnMouseClicked(getMouseEventEventHandler(button_7));
        button_8.setOnMouseClicked(getMouseEventEventHandler(button_8));
        button_9.setOnMouseClicked(getMouseEventEventHandler(button_9));


        button_remove.setOnMouseClicked(event -> {
            try {
                if (CollectionOfTiles.tiles.get(CollectionOfTiles.getLastClicked()).isEditable()) {
                    CollectionOfTiles.setTextInTile(null);
                }
            } catch (NullPointerException e) {

            }
        });


        button_new_game.setOnMouseClicked(event -> {
            setInitialValue.clearBoard();
            setInitialValue.setInitialValue();
        });

        button_prompt.setOnMouseClicked(event -> {
            sudokuSolver.runSolver();
            try {
                if (CollectionOfTiles.tiles.get(CollectionOfTiles.getLastClicked()).isEditable()) {
                    CollectionOfTiles.setTextInTile(sudokuSolver.filledBoard.get(CollectionOfTiles.getLastClicked()));
                    CollectionOfTiles.tiles.get(CollectionOfTiles.getLastClicked()).changeFontToBlack();
                }
            } catch (NullPointerException e) {
            }
        });

        solutionButton.setOnMouseClicked(event1 -> {
            try {
                sudokuSolver.runSolver();

                if (CollectionOfTiles.isEveryTileFilled()) {

                    if (sudokuSolver.isSolvedCorectly()) {
                        labelSolution.setText("Correctly");
                    } else {
                        labelSolution.setText("Mistake!");
                    }

                } else labelSolution.setText("Fill every fields");
            } catch (NullPointerException e) {
            }

        });
    }

    /**
     * this method handling button 1-9 pressing
     *
     * @param button
     * @return if checked tile is editiable, text from the bottom is putting in the tile. If value is available
     * -that means value is unique in column, row and big rectangle the font color is black, otherwise font color is red
     * Important thing- available value does not mean correct value
     */
    private EventHandler<MouseEvent> getMouseEventEventHandler(Button button) {
        return event -> {
            try {
                if (CollectionOfTiles.tiles.get(CollectionOfTiles.getLastClicked()).isEditable()) {
                    CollectionOfTiles.setTextInTile(button.getText());
                    CollectionOfTiles.tiles.get(CollectionOfTiles.getLastClicked()).changeFontToBlack();
                    sudokuSolver.filledBoard.put(CollectionOfTiles.getLastClicked(), button.getText());
                    if (!sudokuSolver.checkValueAvailable(CollectionOfTiles.getLastClicked(), button.getText())) {
                        CollectionOfTiles.tiles.get(CollectionOfTiles.getLastClicked()).changeFontToRed();
                    } else CollectionOfTiles.tiles.get(CollectionOfTiles.tiles).changeFontToBlack();
                }
            } catch (NullPointerException e) {
            }
        };
    }
}
