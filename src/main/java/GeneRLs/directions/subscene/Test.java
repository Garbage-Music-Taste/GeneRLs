package GeneRLs.directions.subscene;

import GeneRLs.core.Applet;
import GeneRLs.directions.Scene;
import GeneRLs.storage.Vector;
import GeneRLs.util.map.MapType;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sin;
import static processing.core.PApplet.pow;
import static processing.core.PApplet.println;


public class Test extends Scene {

    List<Vector> coeff;
    List<Float> col;
    PImage inputImage;
    Vector inc;


    public Test(Applet window) {
        super(window);
    }

    @Override
    protected void setupTimeline() {
        coeff = new ArrayList<>();
        col = new ArrayList<>();
        inc = new Vector(0);

        String commonPath = window.sketchPath("src/main/java/letterdraw/data/");
        inputImage = window.loadImage(commonPath + "Lhu1.png");
        
        // videoExport.startMovie();

        coeff.add(new Vector(0.23585618952283277,-0.12373039706372935));
        coeff.add(new Vector(-94.96630529677374,5.66810251649436));
        coeff.add(new Vector(-55.245280333831545,13.04139273108045));
        coeff.add(new Vector(-25.66657289504737,77.37698153051164));
        coeff.add(new Vector(-18.87423246278361,44.7982635490496));
        coeff.add(new Vector(6.121428814351683,-14.054410730472547));
        coeff.add(new Vector(21.65300805111946,4.008525938845795));
        coeff.add(new Vector(-18.332306993434635,11.446412029802595));
        coeff.add(new Vector(-7.39322078026784,-6.46231799623615));
        coeff.add(new Vector(-11.045580190880587,-10.643318230607415));
        coeff.add(new Vector(-13.813095329513535,3.017062291364188));
        coeff.add(new Vector(-6.918676536683053,0.6755888250406079));
        coeff.add(new Vector(-0.11457558224275011,-4.953575071544117));
        coeff.add(new Vector(-5.993887145836914,-4.564949668142589));
        coeff.add(new Vector(-0.5443446885680356,-0.3291090701366778));
        coeff.add(new Vector(-0.32207834349636544,5.952648155836776));
        coeff.add(new Vector(0.17248966265873616,0.7462460798691342));
        coeff.add(new Vector(-2.88063158218127,0.6487880816613549));
        coeff.add(new Vector(2.453873766500994,-0.5063493877696048));

        int n = this.coeff.size();
        for (int i = 0; i < n; i++){
            float hue = (200.0f * i) / n;  // Maps from 0 (red) to 200 (blue-ish)
            this.col.add(hue);
        }

    }

    float f(float x){
        return (float) (0.66 + 15*pow((float) (0.5*(sin((x-1.9)/1.3) + 1)), (float) (1.3*1.3)));
    }

    float scal = 1;
    Vector last = new Vector(0,0);
    void arrow(float x1, float y1, float x2, float y2) {
        float nprop = 5.0f/100 * window.dist(x1,y1,x2,y2);
        window.strokeWeight(nprop);
        window.line(x1, y1, x2, y2);
        window.pushMatrix();
        window.translate(x2, y2);
        float a = window.atan2(x1-x2, y2-y1);
        float prop = 1.0f/100 * window.dist(x1,y1,x2,y2);
        window.rotate(a);
        //println(a);
        //line(0, 0, -12, -16);
        //line(0, 0, 12, -16);

        window.triangle(0,0,prop*-12,prop*-16,prop*12,prop*-16);
        window.popMatrix();
        window.strokeWeight(5);
    }

    Vector arrowinc = new Vector(0);

    List<PVector> pts = new ArrayList<>();


    @Override
    public boolean execute() {
        if (arrowinc.interpolate(this.coeff.size(),MapType.QUADRATIC,1.0f)){
            inc.x += 0.005F;
        }
        //println(arrowinc);

        List<PVector> vecs = new ArrayList<>();

        PVector head = new Vector(0,0);
        PVector tail = new Vector(0,0);
        vecs.add(tail.copy());
        int m = 0;
        int bruh = 0;
        for (Vector c: this.coeff){
            float offset = window.atan2(c.y,c.x);
            float mag = c.mag();
            head = tail.copy();
            tail.add(new Vector(mag*window.cos(m*inc.x + offset),-mag*sin(m*inc.x + offset)));
            vecs.add(tail.copy());
            if (m <= 0)
                m = 1-m;
            else
                m *= -1;
        }


        scal = f(inc.x);

        window.translate(-scal*tail.x,-scal*tail.y);
        window.scale(scal);
       // window.image(inputImage,0,0);
        window.stroke(120,255,255,160);
        for (int i = 0; i < pts.size()-1; ++i){
            PVector p1 = pts.get(i);
            PVector p2 = pts.get(i+1);
            window.stroke(i/2.8f,255,255,255);
            window.line(p1.x,p1.y,p2.x,p2.y);
        }

        for (int i = 0; i < arrowinc.x; ++i){
            float alph = Math.min(255,255*(arrowinc.x-i));
            window.stroke(col.get(bruh),255,255,alph);
            window.fill(col.get(bruh++),255,255,alph);
            PVector hed = vecs.get(i);
            PVector tal = vecs.get(i+1);
            arrow(hed.x,hed.y,tal.x,tal.y);
        }

        if (inc.x > 0)
            pts.add(new PVector(tail.x,tail.y));
     //   println(pts.size());
        //stroke(col.get(bruh),255,255);
        // fill(col.get(bruh++),255,255);
        // arrow(head.x,head.y,tail.x,tail.y);




        return false;
    }
}
