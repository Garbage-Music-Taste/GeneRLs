package GeneRLs.directions;

import GeneRLs.core.Applet;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.util.Set;

import java.util.*;

public class Directions {
    public static List<Scene> allScenes = new ArrayList<>(); // Order must be preserved!


    public static void init(Applet window) {
        Reflections reflections = new Reflections("GeneRLs.directions.subscene", new SubTypesScanner(false));

        Set<Class<? extends Scene>> classes = reflections.getSubTypesOf(Scene.class);

        for (Class<? extends Scene> c : classes) {
            try {
                Scene scene = c.getDeclaredConstructor(Applet.class).newInstance(window);
                if (scene.runScene()) {
                    allScenes.add(scene);
                }
            } catch (Exception e) {
                System.out.println("Failed to load scene: " + c.getName());
                e.printStackTrace();
            }
        }
        System.out.println("Loaded scenes: " + allScenes);
    }


    public static boolean directions() {
        //call the scenes here
        for (Scene s : allScenes) {
            if (!s.execute())
                return false;
        }
        return true;
    }
}
