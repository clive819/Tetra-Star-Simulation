package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class TetraUIDrawingPanel extends JPanel {

    // The reference max width and height:
    // for example, if an object is defined to have a width of 200 pixels and the WIDTH value is 2000,
    // the object should take up 1/10 of the width after all scaling calculations are completed
    public static final int WIDTH = 2000;
    public static final int HEIGHT = 1200;

    public RenderComparator comparator = new RenderComparator();
    public ArrayList<TRenderable> renderables = new ArrayList<>();

    // render call
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(TRenderable t: renderables){
            t.render(g, this);
        }
    }

    // all renderable objects need to be added,
    // in the future, should be more accessible in a higher layer
    public void addRenderable(TRenderable renderable){
        renderables.add(renderable);
        renderables.sort(comparator);
        repaint();
    }

    class RenderComparator implements Comparator<TRenderable> {
        @Override
        public int compare(TRenderable s1, TRenderable s2)
        {
            if (s1.getRenderLayer() == s2.getRenderLayer())
                return 0;
            else if (s1.getRenderLayer() > s2.getRenderLayer())
                return 1;
            else
                return -1;
        }
    }

    public double getXScale(){
        return (double)getWidth()/WIDTH;
    }

    public double getYScale(){
        return (double)getHeight()/HEIGHT;
    }

    //given an x/y value, convert it to the proper x/y relative to the max WIDTH and HEIGHT
    public int xScale(int x){
        return (int)((double)x*getWidth()/WIDTH);
    }

    public int yScale(int y){
        return (int)((double)y*getHeight()/HEIGHT);
    }
}
