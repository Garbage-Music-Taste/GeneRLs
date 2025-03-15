package GeneRLs.core;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;
import GeneRLs.storage.Color;
import GeneRLs.storage.ColorType;
import GeneRLs.storage.Vector;


public class Applet extends PApplet {
    public float zoom = 1.0f;

    private void init(){
        colorMode(HSB, 360, 100, 100,100);
        translate(width/2f,height/2f);
        scale(1,-1);
        scale(zoom);
        background(0);
        shapeMode(CENTER);
        rectMode(CORNERS);
        strokeCap(ROUND);
        ellipseMode(CENTER);
    }

    @Override
    public void setup() {
        super.setup();
        init();
    }

    public void draw() {
        init();
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