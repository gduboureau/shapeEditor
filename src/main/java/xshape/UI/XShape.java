package xshape.UI;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import xshape.UI.toolbar.IToolbar;
import xshape.command.Invoker;
import xshape.shape.Group;
import xshape.shape.Shape;
import xshape.shapeFactory.ShapeFactory;

public abstract class XShape {
    private ShapeFactory _factory = null;
    public static ArrayList<Shape> _shapes = null;
    Group group;
    protected Invoker invoker = new Invoker();
    protected IToolbar toolbar;
    protected abstract void createToolBar(); 
    //method factory to delegate instanciation of Shapefactory to subclass
    protected abstract ShapeFactory createFactory();
    //Handler to start the GUI
    public abstract void run();

    private void createScene() {

        Shape shape = _factory.createRectangle(480, 310, 130, 130);
        // Shape shape2 = _factory.createRectangle(250, 250, 50, 25);

        Shape shape3 = _factory.createPolygon(8, 150, 650, 200);
        Shape shape4 = _factory.createRectangle(650, 300, 150, 40);

        Shape shape7 = _factory.createPolygon(8, 40, 195, 380);
        Shape shape8 = _factory.createPolygon(8, 40, 325, 380);
        // shape.translate(new Point2D.Double(100, 50));

        ArrayList<Shape> tmp = new ArrayList<>();
        tmp.add(shape);
        tmp.add(shape3);
        tmp.add(shape4);
        tmp.add(shape7);
        tmp.add(shape8);

        _shapes = tmp;
        Shape shape5 = _factory.createRectangle(260, 320, 60, 110);
        Shape shape6 = _factory.createRectangle(260, 350, 45, 200);
        group = _factory.createGroup();
        group.add(shape6);
        group.add(shape5);
    }

    public void draw() {
        if (_shapes == null) {
            _factory = createFactory();
            createScene();
        }

        for (Shape s : _shapes){
            s.addMouseEvents(invoker, group);
            s.draw();
        }
        
        group.draw();
        group.addMouseEvents(invoker, group);
    }


    /*-------------------------------LOAD-------------------------- */
    
        public static void loadShapes() {
            File fileName;
            ArrayList<Shape> shapes = new ArrayList<>();
            JFileChooser fileChooser = new JFileChooser();
            // FileNameExtensionFilter filter = new FileNameExtensionFilter("Shape Files", "txt");
            // fileChooser.setFileFilter(filter);
            
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                fileName = fileChooser.getSelectedFile();
                
                try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))) {
                    shapes = (ArrayList<Shape>) is.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            _shapes.clear();
            _shapes.addAll(shapes);
            // for (Shape s : shapes){
            //     s.draw();
            //     System.out.println(s);
            // }
        }

    /*---------------------SAVE------------------------------------- */
    public void saveShapes(ArrayList<Shape> shapes) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                os.writeObject(shapes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
