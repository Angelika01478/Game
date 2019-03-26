import java.util.*;
import java.util.stream.Collectors;

public class SudokuSolver {

    List<List<Coordinates>> nakedPairPosition = new ArrayList<>();

    public boolean checkRowAvailable(Coordinates coordinates, String value) {

        List<String> rowList = CollectionOfTiles.tiles.entrySet().stream()
                .filter(tile -> tile.getKey().getY() == coordinates.getY())
                .map(tiles -> tiles.getValue().getTextFromTile().getText())
                .collect(Collectors.toList());
        return !rowList.contains(value);
    }

    public boolean checkColAvailable(Coordinates coordinates, String value) {

        List<String> colList = CollectionOfTiles.tiles.entrySet().stream()
                .filter(tile -> tile.getKey().getX() == coordinates.getX())
                .map(tiles -> tiles.getValue().getTextFromTile().getText())
                .collect(Collectors.toList());
        return !colList.contains(value);
    }

    public boolean checkBigRectAvailable(Coordinates coordinates, String value) {

        List<String> bigRectList = CollectionOfTiles.tiles.entrySet().stream()
                .filter(tile -> tile.getKey().getX() / 3 == coordinates.getX() / 3 && tile.getKey().getY() / 3 == coordinates.getY() / 3)
                .map(tiles -> tiles.getValue().getTextFromTile().getText())
                .collect(Collectors.toList());
        return !bigRectList.contains(value);
    }


    public boolean checkValueAvailable(Coordinates coordinates, String value) {
        return checkRowAvailable(coordinates, value) && checkColAvailable(coordinates, value) && checkBigRectAvailable(coordinates, value);
    }

    public HashMap<Coordinates, List<String>> candidates() {
        HashMap<Coordinates, List<String>> mapOfCandidates = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                List<String> listOfCandidatesForOneTile = new ArrayList<>();
                if (CollectionOfTiles.tiles.get(new Coordinates(i, j)).isEditable()) {
                    for (int k = 1; k < 10; k++) {
                        if (checkValueAvailable(new Coordinates(i, j), Integer.toString(k))) {
                            listOfCandidatesForOneTile.add(Integer.toString(k));
                        }
                    }
                }
                Coordinates coordinates = new Coordinates(i, j);
                coordinates.setSolved(false);
                mapOfCandidates.put(coordinates, listOfCandidatesForOneTile);
            }

        }

        while (findNakedSingle(mapOfCandidates) || findNakedPair(mapOfCandidates)) {
            List<Coordinates> listOfNakedSinglePosition = mapOfCandidates.entrySet().stream()
                    .filter(mapOfCandidates1 -> mapOfCandidates1.getValue().size() == 1)
                    .filter(mapOfCandidates2 -> mapOfCandidates2.getKey().isSolved() == false)
                    .map(mapOfCandidates1 -> mapOfCandidates1.getKey())
                    .collect(Collectors.toList());

            for (Coordinates c : listOfNakedSinglePosition) {
                for (Coordinates coordinates : c.neighbours()) {
                    if (CollectionOfTiles.tiles.get(coordinates).isEmpty() && mapOfCandidates.get(c).size() != 0) {
                        mapOfCandidates.get(coordinates).remove(mapOfCandidates.get(c).get(0));
                    }
                }
            }

            for (Coordinates c : listOfNakedSinglePosition) {
                c.setSolved(true);
            }


            List<List<Coordinates>> nakedPairPosition = listOfCoordinatesNakedPairs(mapOfCandidates);

            int i = 0;
            for (List<Coordinates> list : nakedPairPosition) {

                System.out.print("para nr " + i + " ");
                for (Coordinates cor : list) {
                    System.out.print("(" + cor.getX() + "," + cor.getY() + "), ");
                }
                i++;
                System.out.println(" ");
            }


            for (List<Coordinates> listCoordinates : nakedPairPosition) {
                Set<Coordinates> setOfNeigbourNakedPairs = new HashSet<>();
                Coordinates pair = new Coordinates(9, 9);
                for (Coordinates coordinates : listCoordinates) {
                    setOfNeigbourNakedPairs.addAll(coordinates.neighbours());
                    pair = coordinates;
                }
                for (Coordinates coordinates : listCoordinates) {
                    if (setOfNeigbourNakedPairs.contains(coordinates)) {
                        setOfNeigbourNakedPairs.remove(coordinates);
                    }
                }


                System.out.println(pair.getX() + "," + pair.getY());
                for (Coordinates cor : setOfNeigbourNakedPairs) {
                    System.out.print("(" + cor.getX() + "," + cor.getY() + ")" + ", ");
                }


                for (Coordinates c : setOfNeigbourNakedPairs) {
                    if (!c.isSolved() && mapOfCandidates.get(c).size() > 1 && CollectionOfTiles.tiles.get(c).isEditable && mapOfCandidates.get(pair).size() == 2) {
                        mapOfCandidates.get(c).remove(mapOfCandidates.get(pair).get(0));
                        System.out.println("usunięto " + mapOfCandidates.get(pair).get(0) + " z poz " + "(" + c.getX() + "," + c.getY() + ")");

                        mapOfCandidates.get(c).remove(mapOfCandidates.get(pair).get(1));
                        System.out.println("usunięto " + mapOfCandidates.get(pair).get(1) + " z poz " + "(" + c.getX() + "," + c.getY() + ")");
                    }
                }

            }


            System.out.println("================================================================");
            for (Map.Entry<Coordinates, List<String>> entry : mapOfCandidates.entrySet()) {
                System.out.print("(" + entry.getKey().getX() + "," + entry.getKey().getY() + ")");
                System.out.println(entry.getValue().toString());
            }
        }
        return mapOfCandidates;
    }


    public boolean findNakedSingle(HashMap<Coordinates, List<String>> mapOfCandidates) {
        List<Coordinates> listOfNakedSinglePosition = mapOfCandidates.entrySet().stream()
                .filter(mapOfCandidates1 -> mapOfCandidates1.getValue().size() == 1)
                .filter(mapOfCandidates2 -> mapOfCandidates2.getKey().isSolved() == false)
                .map(mapOfCandidates1 -> mapOfCandidates1.getKey())
                .collect(Collectors.toList());
        return listOfNakedSinglePosition.size() > 0;
    }


    public List<List<Coordinates>> listOfCoordinatesNakedPairs(HashMap<Coordinates, List<String>> mapOfCandidates) {

        List<Coordinates> listOfCoordinatesTwoSizedCandidates = mapOfCandidates.entrySet().stream()
                .filter(mapOfCandidates1 -> mapOfCandidates1.getValue().size() == 2)
                .map(mapOfCandidates1 -> mapOfCandidates1.getKey())
                .collect(Collectors.toList());


        for (int i = 0; i < listOfCoordinatesTwoSizedCandidates.size(); i++) {
            for (int j = i + 1; j < listOfCoordinatesTwoSizedCandidates.size(); j++) {
                if (listOfCoordinatesTwoSizedCandidates.get(i).isNeighbour(listOfCoordinatesTwoSizedCandidates.get(j))) {
                    if (mapOfCandidates.get(listOfCoordinatesTwoSizedCandidates.get(i)).containsAll(mapOfCandidates.get(listOfCoordinatesTwoSizedCandidates.get(j))) &&
                            mapOfCandidates.get(listOfCoordinatesTwoSizedCandidates.get(i)).containsAll(mapOfCandidates.get(listOfCoordinatesTwoSizedCandidates.get(j)))) {
                        List<Coordinates> listOfOnePair = new ArrayList<>();
                        listOfOnePair.add(listOfCoordinatesTwoSizedCandidates.get(i));
                        listOfOnePair.add(listOfCoordinatesTwoSizedCandidates.get(j));
                        nakedPairPosition.add(listOfOnePair);
                    }
                }
            }
        }
        return nakedPairPosition;
    }


    public boolean findNakedPair(HashMap<Coordinates, List<String>> mapOfCandidates) {


        List<List<Coordinates>> tempList = nakedPairPosition;
        listOfCoordinatesNakedPairs(mapOfCandidates);


        return nakedPairPosition.size() > tempList.size();
    }


}
