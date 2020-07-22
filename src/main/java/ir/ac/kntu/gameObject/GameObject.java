package ir.ac.kntu.gameObject;

import javafx.scene.image.Image;

public class GameObject {
    private Image image;
    private int rowIndex ;
    private int columnIndex;
    private boolean alive=true;
    private boolean visible=true;

    public GameObject(int rowIndex, int columnIndex){
        this.rowIndex=rowIndex;
        this.columnIndex=columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public void setImage(Image image){
        this.image=image;
    }

    public Image getImage(){
        return this.image;
    }

    public boolean isColliding(GameObject gameObject){
        return (rowIndex==gameObject.rowIndex)&&(columnIndex==gameObject.columnIndex);
    }

    public void collide(GameObject gameObject){

    }

    public void die(){
        alive=false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setVisible(boolean visible){
        this.visible=visible;
    }

    public boolean isVisible() {
        return visible;
    }
}