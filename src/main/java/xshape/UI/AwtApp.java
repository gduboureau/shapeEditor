package xshape.UI;

import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

import xshape.UI.tollbar.AwtToolBar;
import xshape.UI.tollbar.IToolbar;
import xshape.shapeFactory.ShapeFactory;
import xshape.shapeFactory.ShapeFactoryAwt;

class GUIHelper {
    public static void showOnFrame(JComponent component, String frameName) {
        JFrame frame = new JFrame(frameName);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
}

public class AwtApp extends XShape {
    static JCanvas jc;
    public class JCanvas extends JPanel {
        XShape _xshape = null;
        public JCanvas(XShape xs) {
            _xshape = xs;
        }
        public void paint(Graphics g) {
            super.paint(g);
            AwtContext.instance().graphics(g);
            _xshape.draw();
        }
    }
    
    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryAwt();
    }

    public static JCanvas getCanvas(){
        return jc;
    }

    public static void addListener(MouseListener mouseListener){
        jc.addMouseListener(mouseListener);
    }

    public static void addListener(MouseMotionAdapter mouseMotionAdapter){
        jc.addMouseMotionListener(mouseMotionAdapter);
    }

    @Override
    public void run() {
        /* Création de la fenetre */
        jc = new JCanvas(this);
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");

        // /* Creation des toolBar Horizontal et Vertical */
        // AwtToolBar toolBar = new AwtToolBar();
        // jc.setLayout(new BorderLayout());
        // jc.add(toolBar.createToolBarH(), BorderLayout.NORTH);
        // jc.add(toolBar.createToolBarV(), BorderLayout.WEST);
    }

    @Override
    protected void createToolBar() {
        jc.setLayout(new BorderLayout());
        toolbar = new AwtToolBar(invoker);
        jc.add(((AwtToolBar) toolbar).getVerticalToolBar(), BorderLayout.NORTH);
        jc.add(((AwtToolBar) toolbar).getHorizontalToolBar(), BorderLayout.WEST);
    }
}

