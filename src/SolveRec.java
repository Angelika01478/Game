import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SolveRec {

    public static HashMap<Coordinates, String> filledBoard = new HashMap<>();
    public static HashMap<Coordinates, List<String>> mapOfcandidates = new HashMap<>();
    private List<Coordinates> listOfNakedSinglePosition;
    private List<Coordinates> listOfPositionFillWithNakedSingle = new ArrayList<>();
    int p = 0;


    /**
     * this method return available candidates for every tile in tiles collection
     *
     * @return HashMap that stores the coordinates of each empty tile and a list of values available to it
     */
    public HashMap<Coordinates, List<String>> candidates() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (filledBoard.get(coordinates).equals("0")) {
                    List<String> tempCandidates = new ArrayList<>();
                    for (int k = 1; k < 10; k++) {
                        if (checkValueAvailable(coordinates, Integer.toString(k))) {
                            tempCandidates.add(Integer.toString(k));
                        }
                    }
                    mapOfcandidates.put(coordinates, tempCandidates);
                } else {
                    if (mapOfcandidates.containsKey(coordinates)) {
                        mapOfcandidates.remove(coordinates);
                    }
                }
            }
        }
        return mapOfcandidates;
    }


    /**
     * this method returns collection of coordinates that have only one available value
     *
     * @return list of coordinates that have only one available value
     */
    private List<Coordinates> findNakedSinglePosition() {
        candidates();
        listOfNakedSinglePosition = mapOfcandidates.entrySet().stream()
                .filter(c -> c.getValue().size() == 1)
                .map(c -> c.getKey())
                .collect(Collectors.toList());

        try {
            listOfPositionFillWithNakedSingle.addAll(listOfNakedSinglePosition);
        } catch (NullPointerException e) {

        }

        return listOfNakedSinglePosition;
    }


    /**
     * this method fill naked single position in solution
     */
    public void fillNakedSingle() {

        while (findNakedSinglePosition().size() > 0) {

            if (listOfNakedSinglePosition.size() > 0) {
                for (Coordinates c : listOfNakedSinglePosition) {
                    if (mapOfcandidates.get(c).size() > 0) {
                        filledBoard.put(c, mapOfcandidates.get(c).get(0));
                    }
                }
            }
        }
        showSolution();
    }

    /**
     * this method check if value is unique in whole row
     *
     * @param coordinates coordinates of checked value
     * @param value       the value of which uniqueness we check
     * @return return true if value is unique in row and false otherwise
     */
    public boolean checkRowAvailable(Coordinates coordinates, String value) {

        List<String> rowList = filledBoard.entrySet().stream()
                .filter(filledBoardC -> filledBoardC.getKey().getY() == coordinates.getY())
                .filter(filledBoardC -> !filledBoardC.getKey().equals(coordinates))
                .map(filledBoardC -> filledBoardC.getValue())
                .collect(Collectors.toList());
        return !rowList.contains(value);
    }

    /**
     * this method check if value is unique in whole column
     *
     * @param coordinates coordinates of checked value
     * @param value       the value of which uniqueness we check
     * @return return true if value is unique in column and false otherwise
     */
    public boolean checkColAvailable(Coordinates coordinates, String value) {

        List<String> colList = filledBoard.entrySet().stream()
                .filter(filledBoardC -> filledBoardC.getKey().getX() == coordinates.getX())
                .filter(filledBoardC -> !filledBoardC.getKey().equals(coordinates))
                .map(filledBoardC -> filledBoardC.getValue())
                .collect(Collectors.toList());
        return !colList.contains(value);
    }

    /**
     * this method check if value is unique in big rectangle
     *
     * @param coordinates coordinates of checked value
     * @param value       the value of which uniqueness we check
     * @return return true if value is unique in big rectangle and false otherwise
     */
    public boolean checkBigRectAvailable(Coordinates coordinates, String value) {

        List<String> bigRectList = filledBoard.entrySet().stream()
                .filter(filledBoardC -> filledBoardC.getKey().getX() / 3 == coordinates.getX() / 3 && filledBoardC.getKey().getY() / 3 == coordinates.getY() / 3)
                .filter(filledBoardC -> !filledBoardC.getKey().equals(coordinates))
                .map(filledBoardC -> filledBoardC.getValue())
                .collect(Collectors.toList());
        return !bigRectList.contains(value);
    }


    /**
     * this method check if value is unique in column, row and big rectangle
     *
     * @param coordinates coordinates of checked value
     * @param value       the value of which uniqueness we check
     * @return return true if value is unique in column and row and big rectangle and false otherwise
     */

    public boolean checkValueAvailable(Coordinates coordinates, String value) {
        return checkRowAvailable(coordinates, value) && checkColAvailable(coordinates, value) && checkBigRectAvailable(coordinates, value);
    }

    /**
     * to increase the efficiency of the recursive algorithm first are filled every naked single position
     *
     * @return true if value for every tiles in filledBoard collection is different from zero and false otherwise
     */
    public boolean runSolver() {

        fillNakedSingle();
        runRec();

        List<Coordinates> checkList = filledBoard.entrySet().stream()
                .filter(v -> v.getValue().equals("0"))
                .map(filledBoardC -> filledBoardC.getKey())
                .collect(Collectors.toList());
        return checkList.size() == 0;
    }

    /**
     * recursive method of solving sudoku
     * First checked if current tile is empty and find first available value for it.
     * Process is repeated for the next tiles until is found the tile where none of 1-9 value is available.
     * In that case return false and return to previous tile (backtracking algorithm).
     *
     * @return true if every tiles is filled with available values and false otherwise
     */
    public boolean runRec() {
        p++;
        if (p > 1000) return false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                coordinates.setX(i);
                coordinates.setY(j);
                if (filledBoard.get(coordinates).equals("0")) {
                    for (int k = 1; k < 10; k++) {
                        if (checkValueAvailable(coordinates, Integer.toString(k))) {
                            filledBoard.put(coordinates, Integer.toString(k));
                            showSolution();
                            if (runRec()) {
                                return true;
                            } else {
                                filledBoard.put(coordinates, "0");
                            }
                        }
                    }
                    return false;
                }
            }
        }

        showSolution();
        return true;
    }

    /**
     * this method checked if in solved filledBoard collection every value is different from zero and every value is available
     *
     * @return true if every value in filledBoard collection is different from zero and every value is available and false otherwise
     */
    public boolean isSolvedCorectly() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                System.out.println(checkValueAvailable(coordinates, filledBoard.get(coordinates)));
                if (filledBoard.get(coordinates).equals("0") || !(checkValueAvailable(coordinates, filledBoard.get(coordinates)))) {
                    return false;
                }
            }
        }
        return true;
    }


    public void showSolution() {

        System.out.println("=======================================");
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                Coordinates coordinates = new Coordinates(i, j);

                if (i != 8) {
                    System.out.print(filledBoard.get(coordinates) + " ");

                } else {
                    System.out.println(filledBoard.get(coordinates));
                }
            }
        }
    }


}
