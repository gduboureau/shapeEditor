package xshape.UI;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import xshape.command.Test;

public class AwtToolBar {

    private JToolBar toolBarH;
    private JToolBar toolBarV;

    public AwtToolBar(){
        toolBarH = new JToolBar();
        toolBarV = new JToolBar();
    }

    /* Méthode de constrcution de la barre d'outils */
    public JToolBar createToolBarH(){

        JButton btnLoad = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/load.png"));
        btnLoad.setToolTipText("Load");
        btnLoad.addActionListener((event) -> {
            Test test = new Test(event, "Load OK");
        });
        toolBarH.add(btnLoad);

        JButton btnSave = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/save.png"));
        btnSave.setToolTipText("Save");
        btnSave.addActionListener((event) -> {
            Test test = new Test(event, "Save OK");
        });
        toolBarH.add(btnSave);

        toolBarH.addSeparator();

        JButton btnUndo = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/undo.png"));
        btnUndo.setToolTipText("Undo");
        btnUndo.addActionListener((event) -> {
            Test test = new Test(event, "Undo OK");
        });
        toolBarH.add(btnUndo);

        JButton btnRedo = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/redo.png"));
        btnRedo.setToolTipText("Redo");
        btnRedo.addActionListener((event) -> {
            Test test = new Test(event, "Redo OK");
        });
        toolBarH.add(btnRedo);

        toolBarH.addSeparator();

        JButton btnTrash = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/trash.png"));
        btnTrash.setToolTipText("Trash");
        btnTrash.addActionListener((event) -> {
            Test test = new Test(event, "Trash OK");
        });
        toolBarV.add(btnTrash);

        return toolBarH;
    }

    public JToolBar createToolBarV(){
    
        // La barre d'outils à proprement parler
        toolBarV.setOrientation(JToolBar.VERTICAL);
        toolBarV.setPreferredSize(new Dimension(80, toolBarV.getPreferredSize().height));
        toolBar.setFloatable(false);


        return toolBarV;
    }

}
