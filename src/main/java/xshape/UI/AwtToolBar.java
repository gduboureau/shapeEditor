package xshape.UI;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import xshape.command.Test;

public class AwtToolBar extends JFrame { //nouvel element fenetre

    /* Méthode de constrcution de la barre d'outils */
    public JToolBar createToolBarH(){
        
        // La barre d'outils à proprement parler
        JToolBar toolBar = new JToolBar();

        JButton btnLoad = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/load.png"));
        btnLoad.setToolTipText("Load");
        btnLoad.addActionListener((event) -> {
            Test test = new Test(event, "Load OK");
        });
        toolBar.add(btnLoad);

        JButton btnSave = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/save.png"));
        btnSave.setToolTipText("Save");
        btnSave.addActionListener((event) -> {
            Test test = new Test(event, "Save OK");
        });
        toolBar.add(btnSave);

        toolBar.addSeparator();

        JButton btnUndo = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/undo.png"));
        btnUndo.setToolTipText("Undo");
        btnUndo.addActionListener((event) -> {
            Test test = new Test(event, "Undo OK");
        });
        toolBar.add(btnUndo);


        JButton btnRedo = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/redo.png"));
        btnRedo.setToolTipText("Redo");
        btnRedo.addActionListener((event) -> {
            Test test = new Test(event, "Redo OK");
        });
        toolBar.add(btnRedo);

        return toolBar;
    }

    public JToolBar createToolBarV(){
    
        // La barre d'outils à proprement parler
        JToolBar toolBar = new JToolBar();

        JButton btnTrash = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/trash.png"));
        btnTrash.setToolTipText("Trash");
        btnTrash.addActionListener((event) -> {
            Test test = new Test(event, "Trash OK");
        });
        toolBar.add(btnTrash);

        return toolBar;
    }

}
