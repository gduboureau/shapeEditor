package xshape.SaveLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import xshape.shape.Shape;

public class Saver {
    public static void saveShapes(ArrayList<Shape> shapes) {
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
