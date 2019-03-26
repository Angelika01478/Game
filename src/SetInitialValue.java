import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetInitialValue {

    Random generator = new Random();
    SolveRec solveRec = new SolveRec();


    public void setOneValue(int x, int y, String value) {
        if (!value.equals("0")) {
            Coordinates coordinates = new Coordinates(x, y);
            solveRec.filledBoard.put(coordinates, value);
            CollectionOfTiles.tiles.get(coordinates).setTextValue(value);
            CollectionOfTiles.tiles.get(coordinates).changeTileFillToSalomon();
            CollectionOfTiles.tiles.get(coordinates).changeFontToBlack();
            CollectionOfTiles.tiles.get(coordinates).setEditable(false);
            coordinates.setSolved(true);
        }
    }

    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                solveRec.filledBoard.put(coordinates, "0");
                CollectionOfTiles.tiles.get(coordinates).setTextValue(null);
                CollectionOfTiles.tiles.get(coordinates).setEditable(true);
                CollectionOfTiles.tiles.get(coordinates).changeTileFillToNull();
            }
        }
    }


    private void createEmptyFillBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                solveRec.filledBoard.put(coordinates, "0");
            }
        }
    }


    public void setInitialValue() {
        createEmptyFillBoard();
        List<String> listOfNamesInitialValueFile = new ArrayList<>();
        listOfNamesInitialValueFile.add("1.txt");
        listOfNamesInitialValueFile.add("2.txt");
        listOfNamesInitialValueFile.add("3.txt");
        listOfNamesInitialValueFile.add("4.txt");
        listOfNamesInitialValueFile.add("5.txt");
        listOfNamesInitialValueFile.add("6.txt");
        listOfNamesInitialValueFile.add("7.txt");

        int value = generator.nextInt(listOfNamesInitialValueFile.size());

        ReadDataFromFile readDataFromFile = new ReadDataFromFile();
        try {
            readDataFromFile.readFile(listOfNamesInitialValueFile.get(value));
        } catch (FileNotFoundException e) {
            System.out.println("there is no initial value file");
        }

    }


}



