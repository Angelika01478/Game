
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.HashMap;


public class CollectionOfTiles {

    static SolveRec solveRec = new SolveRec();

    /**
     * coordinates of last selected tile
     */
    private static Coordinates lastClicked;

    /**
     * HashMap storing 81 tiles in witch key are coordinates of every tile.
     */
    static HashMap<Coordinates, Tile> tiles = new HashMap<>();

    /**
     * this method create empty board, with 81 tiles (9 row times 9 column)
     *
     * @return StackPane with this tiles.
     */

    public static StackPane createBoard() {
        StackPane stackPane = new StackPane();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Tile tile = new Tile();
                Coordinates coordinates = new Coordinates(i, j);
                tile.setTextValue("");
                tile.setTranslateX(i * 50);
                tile.setTranslateY(j * 50);
                tile.setEditable(true);
                tile.setTileId(coordinates);
                stackPane.getChildren().add(tile);
                tiles.put(coordinates, tile);
            }
        }
        return stackPane;
    }

    /**
     * this method assigns the position of the click inside StackPane to coordinates
     *
     * @param stackPane where we have board
     */
    public static void setEvent(StackPane stackPane) {
        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {

                                            tiles.entrySet().stream().map(oTile -> oTile.getValue())
                                                    .filter(tile -> tile.isEmpty())
                                                    .forEach(tile -> tile.changeTileFillToNull());

                                            tiles.entrySet().stream().map(oTile -> oTile.getValue())
                                                    .filter(tile -> !tile.isEmpty())
                                                    .forEach(tile -> tile.changeTileFillToLightPink());

                                            int posX = (int) (event.getX() / 50);
                                            int posY = (int) (event.getY() / 50);
                                            Coordinates coordinates = new Coordinates(posX, posY);
                                            tiles.get(coordinates).changeTileFillToPink();
                                            setLastClicked(coordinates);
                                        }
                                    }
        );
    }

    /**
     * this method checked if the value entered into the box is unique in the column, row and in the big rectangle
     *
     * @param lastClicked
     * @param value
     */

    public static void checker(Coordinates lastClicked, String value) {
        if (!solveRec.checkValueAvailable(lastClicked, value)) {
            tiles.get(lastClicked).changeFontToRed();
        } else tiles.get(lastClicked).changeFontToBlack();
    }

    /**
     * this method handles keyboard events: numeric buttons from 1-9, bottom delete and arrows
     *
     * @param scene
     */

    public static void setKeyEvent(Scene scene) {
        scene.setOnKeyPressed(event -> {

            switch (event.getCode()) {
                case NUMPAD1:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("1");
                        solveRec.filledBoard.put(lastClicked, "1");
                        checker(lastClicked, "1");

                    }
                    break;
                case DIGIT1:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("1");
                        solveRec.filledBoard.put(lastClicked, "1");
                        checker(lastClicked, "1");
                    }
                    break;
                case NUMPAD2:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("2");
                        solveRec.filledBoard.put(lastClicked, "2");
                        checker(lastClicked, "2");
                    }
                    break;
                case DIGIT2:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("2");
                        solveRec.filledBoard.put(lastClicked, "2");
                        checker(lastClicked, "2");
                    }
                    break;
                case NUMPAD3:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("3");
                        solveRec.filledBoard.put(lastClicked, "3");
                        checker(lastClicked, "3");
                    }
                    break;
                case DIGIT3:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("3");
                        solveRec.filledBoard.put(lastClicked, "3");
                        checker(lastClicked, "3");
                    }
                    break;
                case NUMPAD4:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("4");
                        solveRec.filledBoard.put(lastClicked, "4");
                        checker(lastClicked, "4");
                    }
                    break;
                case DIGIT4:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("4");
                        solveRec.filledBoard.put(lastClicked, "4");
                        checker(lastClicked, "4");
                    }
                    break;
                case NUMPAD5:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("5");
                        solveRec.filledBoard.put(lastClicked, "5");
                        checker(lastClicked, "5");
                    }
                    break;
                case DIGIT5:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("5");
                        solveRec.filledBoard.put(lastClicked, "5");
                        checker(lastClicked, "5");
                    }
                    break;
                case NUMPAD6:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("6");
                        solveRec.filledBoard.put(lastClicked, "6");
                        checker(lastClicked, "6");
                    }
                    break;
                case DIGIT6:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("6");
                        solveRec.filledBoard.put(lastClicked, "6");
                        checker(lastClicked, "6");
                    }
                    break;
                case NUMPAD7:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("7");
                        solveRec.filledBoard.put(lastClicked, "7");
                        checker(lastClicked, "7");
                    }
                    break;
                case DIGIT7:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("7");
                        solveRec.filledBoard.put(lastClicked, "7");
                        checker(lastClicked, "7");
                    }
                    break;
                case NUMPAD8:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("8");
                        solveRec.filledBoard.put(lastClicked, "8");
                        checker(lastClicked, "8");
                    }
                    break;
                case DIGIT8:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("8");
                        solveRec.filledBoard.put(lastClicked, "8");
                        checker(lastClicked, "8");
                    }
                    break;
                case NUMPAD9:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("9");
                        solveRec.filledBoard.put(lastClicked, "9");
                        checker(lastClicked, "9");
                    }
                    break;
                case DIGIT9:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("9");
                        solveRec.filledBoard.put(lastClicked, "9");
                        checker(lastClicked, "9");
                    }
                    break;
                case DELETE:
                    if (tiles.get(lastClicked).isEditable) {
                        tiles.get(lastClicked).setTextValue("");
                        solveRec.filledBoard.put(lastClicked, "0");
                    }
                    break;
                case UP: {
                    if (tiles.get(lastClicked).isEmpty()) tiles.get(lastClicked).changeTileFillToNull();
                    if (!tiles.get(lastClicked).isEmpty()) tiles.get(lastClicked).changeTileFillToLightPink();
                    setLastClicked(new Coordinates(lastClicked.getX(), lastClicked.getY() - 1 < 0 ? 8 : lastClicked.getY() - 1));
                    tiles.get(lastClicked).changeTileFillToPink();
                }
                break;
                case DOWN: {
                    if (tiles.get(lastClicked).isEmpty()) tiles.get(lastClicked).changeTileFillToNull();
                    if (!tiles.get(lastClicked).isEmpty()) tiles.get(lastClicked).changeTileFillToLightPink();
                    setLastClicked(new Coordinates(lastClicked.getX(), lastClicked.getY() + 1 > 8 ? 0 : lastClicked.getY() + 1));
                    tiles.get(lastClicked).changeTileFillToPink();

                }
                break;
                case LEFT: {
                    if (tiles.get(lastClicked).isEmpty()) tiles.get(lastClicked).changeTileFillToNull();
                    if (!tiles.get(lastClicked).isEmpty()) tiles.get(lastClicked).changeTileFillToLightPink();
                    setLastClicked(new Coordinates(lastClicked.getX() - 1 < 0 ? 8 : lastClicked.getX() - 1, lastClicked.getY()));
                    tiles.get(lastClicked).changeTileFillToPink();
                }
                break;
                case RIGHT: {
                    if (tiles.get(lastClicked).isEmpty()) tiles.get(lastClicked).changeTileFillToNull();
                    if (!tiles.get(lastClicked).isEmpty()) tiles.get(lastClicked).changeTileFillToLightPink();
                    setLastClicked(new Coordinates(lastClicked.getX() + 1 > 8 ? 0 : lastClicked.getX() + 1, lastClicked.getY()));
                    tiles.get(lastClicked).changeTileFillToPink();
                }
                break;
            }
        });
    }

    /**
     * this method puts the String value passed in the argument in a tile
     *
     * @param s this is the value put in the tile
     */

    public static void setTextInTile(String s) {
        tiles.get(lastClicked).setTextValue(s);
    }


    /**
     * setter for lastClicked variable
     *
     * @param coordinates
     */

    public static void setLastClicked(Coordinates coordinates) {
        lastClicked = coordinates;
    }

    /**
     * getter for lastClicked variable
     *
     * @return
     */
    public static Coordinates getLastClicked() {
        return lastClicked;
    }

    /**
     * this method is responsible for drawing the lines around big rectangle
     *
     * @param pane
     */
    public static void drawLines(Pane pane) {

        for (int i = 0; i < 4; i++) {
            Line lineH = new Line(0, i * 150, 450, i * 150);
            lineH.setStroke(Color.DARKGRAY);
            lineH.setStrokeWidth(3);
            pane.getChildren().add(lineH);

            Line lineV = new Line(i * 150, 0, i * 150, 450);
            lineV.setStroke(Color.DARKGRAY);
            lineV.setStrokeWidth(3);
            pane.getChildren().add(lineV);
        }
    }


    /**
     * method checked if the tiles collection contains a tile with empty string in tekst attribute
     *
     * @return true if every tile in tiles collection is filled and return false is there at least one tile with empty String in text attribute
     */
    public static boolean isEveryTileFilled() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tiles.get(new Coordinates(i, j)).getTextFromTile().getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

}
