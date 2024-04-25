package GraphicsTest;
import java.awt.*;

public class wordleRectangle {
    Color fillColor = Color.WHITE;
    int xCoordinate = 0;
    int yCoordinate = 0;
    int width = 0;
    int height = 0;
    String text = "";
    Color textColor = Color.WHITE;
    int textSize = 0;
    String textFont = "ariel";
    int outLineThickness = 0;
    Color outLineColor = Color.WHITE;

    public Color getFillColor() {
        return this.fillColor;
    }

    public int getX() {
        return this.xCoordinate;
    }

    public int getY() {
        return this.yCoordinate;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getText() {
        return this.text;
    }

    public Color getTextColor() {
        return this.textColor;
    }

    public String getTextFont() {
        return this.textFont;
    }

    public int getTextSize(){
        return this.textSize;
    }

    public int getOutLineThickness() {
        return this.outLineThickness;
    }

    public Color getOutLineColor() {
        return this.outLineColor;
    }

    wordleRectangle(){}

    wordleRectangle( int widthIn, int heightIn, Color fillColorIn){
        this.width = widthIn;
        this.height = heightIn; 
        this.fillColor = fillColorIn;
    }

<<<<<<< HEAD
    wordleRectangle(int xIn, int yIn, Color fillColorIn, String textIn, Color textColorIn, Font fontIn){
        this.xCoordinate = xIn;
        this.yCoordinate = yIn;
        this.fillColor = fillColorIn;
        this.text = textIn;
        this.textColor = textColorIn;
        this.textFont = fontIn;
    }

    wordleRectangle(int xIn, int yIn, int widthIn, int heightIn, String textIn, Color textColorIn, Font fontIn){
=======
    wordleRectangle(int xIn, int yIn, int widthIn, int heightIn, String textIn, Color textColorIn, String fontIn, int textSizeIn){
>>>>>>> d34404a9b96794c6b93aa8871e7bfdae93e6a4d2
        this.xCoordinate = xIn;
        this.yCoordinate = yIn;
        this.width = widthIn;
        this.height = heightIn;
        this.text = textIn;
        this.textColor = textColorIn;
        this.textFont = fontIn;
        this.textSize = textSizeIn;
    }

    wordleRectangle(int xIn, int yIn, int widthIn, int heightIn,Color fillColorIn, String textIn, Color textColorIn, String fontIn, int textSizeIn){
        this.xCoordinate = xIn;
        this.yCoordinate = yIn;
        this.width = widthIn;
        this.height = heightIn;
        this.fillColor = fillColorIn;
        this.text = textIn;
        this.textColor = textColorIn;
        this.textFont = fontIn;
        this.textSize = textSizeIn;
    }

    wordleRectangle(int xIn, int yIn, int widthIn, int heightIn, int outLineThicknessIn, Color outColorIn, String textIn, Color textColorIn, String fontIn, int textSizeIn){
        this.xCoordinate = xIn;
        this.yCoordinate = yIn;
        this.width = widthIn;
        this.height = heightIn;
        this.text = textIn;
        this.textColor = textColorIn;
        this.textFont = fontIn;
        this.textSize = textSizeIn;
        this.outLineThickness = outLineThicknessIn;
        this.outLineColor = outColorIn;
    }

    wordleRectangle(int xIn, int yIn, int widthIn, int heightIn,Color fillColorIn, int outLineThicknessIn, Color outLineColorIn, String textIn, Color textColorIn, String fontIn, int textSizeIn){
        this.xCoordinate = xIn;
        this.yCoordinate = yIn;
        this.width = widthIn;
        this.height = heightIn;
        this.fillColor = fillColorIn;
        this.text = textIn;
        this.textColor = textColorIn;
        this.textFont = fontIn;
        this.textSize = textSizeIn;
        this.outLineThickness = outLineThicknessIn;
        this.outLineColor = outLineColorIn;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setTextFont(String textFont) {
        this.textFont = textFont;
    }

    public void setOutLineThickness(int outLineThickness) {
        this.outLineThickness = outLineThickness;
    }

    public void setOutLineColor(Color outLineColor) {
        this.outLineColor = outLineColor;
    }

}
