package GeneRLs.directions;

import GeneRLs.core.Applet;

public abstract class Scene {

    protected Applet window;
    protected boolean runScene = true;

    protected Timeline timeline = new Timeline();

    public Scene(Applet window) {
        this.window = window;
        setupTimeline();
    }

    protected abstract void setupTimeline();

   // public abstract boolean executeHelper();

    public abstract boolean execute();//{
        //window.println(timeline.getQueue());
       // return timeline.update(window.millis() / 1000f);
   // }


    public boolean runScene(){
        return runScene;
    }

}