package GeneRLs.storage;

import processing.core.PApplet;
import processing.core.PVector;
import GeneRLs.util.Mapper;
import GeneRLs.util.map.Interpolatable;
import GeneRLs.util.map.MapEase;
import GeneRLs.util.map.MapType;

import static GeneRLs.util.map.MapType.QUADRATIC;

public class Vector extends PVector implements Interpolatable<PVector> { // only dealing with 2D, 3D can fuck off
    private long incrementor = 0;
    private float uneasedX, uneasedY;
    public static Vector UP = new Vector(0,-500);
    public static Vector DOWN = new Vector(0,500);
    public static Vector LEFT = new Vector(-500,0);
    public static Vector RIGHT = new Vector(500,0);

    public Vector(float x, float y){
        super(x,y);
        uneasedX = this.x;
        uneasedY = this.y;
    }

    public Vector(double v, double v1) {
        this((float) v, (float) (v1));
    }

    public void setX(float x){ // abrupt change
        this.x = x;
        uneasedX = x;
    }

    public void setY(float y){ // abrupt change
        this.y = y;
        uneasedY = y;
    }

    public Vector(float x) {
        this(x,0);
    }

    public Vector(Vector o) {
        this.x = o.x;
        this.y = o.y;
    }

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public boolean interpolate(float o){
        return this.interpolate(new Vector(o), QUADRATIC,MapEase.EASE_IN_OUT, Math.sqrt(2)); // 1.4 seconds
    }

    public boolean interpolate(float o, MapType type, double time){
        return this.interpolate(new Vector(o), type, MapEase.EASE_IN_OUT, time); // 1.4 seconds
    }

    public boolean interpolate(PVector o, MapType type, MapEase ease, double time){ // for now, Quadratic Map, fix extra computation power for one dim easing
        long incFinal = (long) (time*60);
        if (this.equals(o) || incrementor == incFinal) {
            this.uneasedX = this.x;
            this.uneasedY = this.y;
            incrementor = 0;
            return true;
        }
        incrementor++;
        this.x = (float) Mapper.map2(incrementor,0,incFinal,this.uneasedX,o.x, type, ease);
        this.y = (float) Mapper.map2(incrementor,0,incFinal,this.uneasedY,o.y, type, ease);
        return false;
    }

    public Vector map(Vector start, Vector end, Vector startNew, Vector endNew){
        return new Vector(PApplet.map(x,start.x,end.x,startNew.x,endNew.x),PApplet.map(y,start.y,end.y,startNew.y,endNew.y));
    }


}
