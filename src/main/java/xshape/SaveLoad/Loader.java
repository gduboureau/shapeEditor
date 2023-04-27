package xshape.SaveLoad;
import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import xshape.shape.Shape;

public class Loader {
    public void loadShapes() {
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
        // XShape._shapes.clear();
        // XShape._shapes.addAll(shapes);
        for (Shape s : shapes){
            s.draw();
            System.out.println(s);
        }
    }
}
