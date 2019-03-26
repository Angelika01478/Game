
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class SudokuGame extends Application {

    CollectionOfTiles collectionOfTiles = new CollectionOfTiles();

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sudokuLayout.fxml"));
        Pane pane = loader.load();

        StackPane stackPane = CollectionOfTiles.createBoard();
        pane.getChildren().add(stackPane);

        SudokuController sudokuController = loader.getController();
        CollectionOfTiles.setEvent(stackPane);
        CollectionOfTiles.drawLines(pane);
        Scene scene = new Scene(pane);
        CollectionOfTiles.setKeyEvent(scene);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sudoku Game");
        primaryStage.show();

        System.out.println(pane.getChildren().toString());

    }

}
