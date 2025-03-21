package GeneRLs.core;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;
import GeneRLs.storage.Color;
import GeneRLs.storage.ColorType;
import GeneRLs.storage.Vector;

import static GeneRLs.Main.*;


public class Applet extends PApplet {
    public float zoom = 1.0f;

    private void init(){
        colorMode(HSB, 360, 100, 100,100);
        translate(width/2f,height/2f);
        scale(1,-1);
        scale(zoom);
        translate(offsetX, offsetY);

        background(0);
        shapeMode(CENTER);
        rectMode(CORNERS);
        strokeCap(ROUND);
        ellipseMode(CENTER);
        textFont(myFont);
        textMode(SHAPE);
    }

    @Override
    public void setup() {
        super.setup();
        init();
    }

    public void update() {

    }

    public void draw() {
        init();
        update();
    }


    public float getMouseX() {
        return mouseX - width / 2f;  // Shift origin to center
    }

    public float getMouseY() {
        return -(mouseY - height / 2f); // Shift and invert Y-axis
    }

    public void stroke(Color color){
        this.stroke(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), color.getAlpha().getValue());
    }

    public void fill(Color color) {
        this.fill(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), color.getAlpha().getValue());
    }

    public int color(Color color) {
        return this.color(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), color.getAlpha().getValue());
    }

    public void tint(Color color) {
        this.tint(color.getHue().getValue(), color.getSaturation().getValue(), color.getBrightness().getValue(), color.getAlpha().getValue());
    }

    public void scale(double d){
        this.scale((float) d);
    }

    public void translate(PVector mult) {
        this.translate(mult.x,mult.y);
    }

    public void fill(ColorType c) {
        this.fill(new Color(c));
    }

    public void stroke(ColorType c) {
        this.stroke(new Color(c));
    }


    @Override
    public void shape(PShape shape, float x, float y) {
        pushMatrix();
        scale(1,-1);
        super.shape(shape,x,-y);
        popMatrix();
    }

    @Override
    public void shape(PShape shape, float a, float b, float c, float d) {
        pushMatrix();
        scale(1,-1);
        super.shape(shape,a,-b,c,d);
        popMatrix();
    }

    @Override
    public void text(String text, float x, float y) {
        pushMatrix();
        scale(1,-1);
        super.text(text,x,-y);
        popMatrix();
    }

    @Override
    public void text(char character, float x, float y) {
        pushMatrix();
        scale(1,-1);
        super.text(character,x,-y);
        popMatrix();
    }

    @Override
    public void text(int integer, float x, float y) {
        pushMatrix();
        scale(1,-1);
        super.text(integer,x,-y);
        popMatrix();
    }

    @Override
    public void text(float fp, float x, float y) {
        pushMatrix();
        scale(1,-1);
        super.text(fp,x,-y);
        popMatrix();
    }


    public void text(Object o, float x, float y) {
        pushMatrix();
        scale(1,-1);
        super.text(o.toString(),x,-y);
        popMatrix();
    }

    public void shape(PShape latex, Vector pos) {
        this.shape(latex,pos.x,pos.y);
    }

    public void rect(Vector pos, Vector pos1) {
        this.rect(pos.x,pos.y,pos1.x,pos1.y);
    }

    public void scale(Vector scale){
        this.scale(scale.x,scale.y);
    }

    /*public Shape createShape() {
        try {
            return new Shape(g.createShape());
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } return null;
    }


    public Shape createShape(int type) {
        try {
            return new Shape(g.createShape(type));
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } return null;
    }


    /**
     * @param kind either POINT, LINE, TRIANGLE, QUAD, RECT, ELLIPSE, ARC, BOX, SPHERE
     * @param p parameters that match the kind of shape
     *
    public Shape createShape(int kind, float... p) {
        try {
            return new Shape(g.createShape(kind,p));
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } return null;
    } */
}