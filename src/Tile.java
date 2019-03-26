import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tile extends StackPane {


    private Text text = new Text();
    Rectangle border = new Rectangle(50, 50);
    private Coordinates tileId;
    boolean isEditable;


    public Tile() {

        border.setFill(null);
        border.setStroke(Color.GRAY);

        setAlignment(Pos.CENTER);
        text.setFont(Font.font(30));
        getChildren().addAll(border, text);

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CollectionOfTiles.setLastClicked(tileId);
            }
        });
    }

    /**
     * setter for text attribute
     *
     * @param t
     */
    public void setTextValue(String t) {
        text.setText(t);
    }

    /**
     * getter for text attribute
     *
     * @return
     */
    public Text getTextFromTile() {
        return text;
    }


    public void changeTileFillToPink() {
        if (isEditable) {
            border.setFill(Color.PINK);
        }
    }

    /**
     * this method change tile fill to null if text attribute is an empty string
     */
    public void changeTileFillToNull() {
        if (isEditable) {
            border.setFill(null);
        }
    }

    /**
     * this method change tile fill to light pink colour if that tile is active
     */
    public void changeTileFillToLightPink() {
        if (isEditable) {
            border.setFill(Color.BISQUE);
        }
    }

    /**
     * This method change tile fill to salomon colour if that tile is not editable.
     * Tile is not editable if value comes from initial generator
     */
    public void changeTileFillToSalomon() {
        border.setFill(Color.SALMON);
    }

    /**
     * This method change font colour in tile to red.
     * Tile has red font colour if value in this tile is not available.
     * Value is not available if is not unique in row or column or big rectangle.
     */
    public void changeFontToRed() {
        text.setFill(Color.RED);
    }

    /**
     * This method change font colour in tile to black.
     * Tile has black font colour if value in this tile is available.
     * Value is available if is unique in row and column and big rectangle.
     */
    public void changeFontToBlack() {
        text.setFill(Color.BLACK);
    }

    /**
     * getter for tileId attribute
     *
     * @return
     */
    public Coordinates getTileId() {
        return tileId;
    }

    /**
     * setter for tileId attribute
     *
     * @param tileId
     */
    public void setTileId(Coordinates tileId) {
        this.tileId = tileId;
    }


    public boolean isEmpty() {
        return this.getTextFromTile().getText().isEmpty();
    }

    /**
     * setter for isEditable attribute
     *
     * @param editable
     */
    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    /**
     * getter for isEditable attribute
     *
     * @return
     */
    public boolean isEditable() {
        return isEditable;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        return tileId != null ? tileId.equals(tile.tileId) : tile.tileId == null;
    }

    @Override
    public int hashCode() {
        return tileId != null ? tileId.hashCode() : 0;
    }
}


