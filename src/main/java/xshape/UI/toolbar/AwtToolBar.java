package xshape.UI.toolbar;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import xshape.SaveLoad.Loader;
import xshape.SaveLoad.Saver;
import xshape.UI.XShape;
import xshape.UI.awt.AwtApp;
import xshape.command.Invoker;
import xshape.command.Test;

public class AwtToolBar implements IToolbar{

    private JToolBar verticalToolBar;
    private JToolBar horizontalToolBar;
    private Invoker invoker;

    public AwtToolBar(Invoker invoker){
        verticalToolBar = new JToolBar();
        horizontalToolBar = new JToolBar();
        this.invoker = invoker;
    }

    /* Méthode de constrcution de la barre d'outils */
    public void createToolBarH(){

        JButton btnLoad = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/load.png"));
        btnLoad.setToolTipText("Load");
        btnLoad.addActionListener(event -> {
            XShape.loadShapes();
        });
        horizontalToolBar.add(btnLoad);

        JButton btnSave = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/save.png"));
        btnSave.setToolTipText("Save");
        btnSave.addActionListener(event -> {
            Saver.saveShapes(XShape._shapes);
        });
        horizontalToolBar.add(btnSave);

        horizontalToolBar.addSeparator();

        JButton btnUndo = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/undo.png"));
        btnUndo.setToolTipText("Undo");
        btnUndo.addActionListener(event -> {
            invoker.undo();
            AwtApp.getCanvas().repaint();
        });
        horizontalToolBar.add(btnUndo);

        JButton btnRedo = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/redo.png"));
        btnRedo.setToolTipText("Redo");
        btnRedo.addActionListener(event -> {
            invoker.redo();
            AwtApp.getCanvas().repaint();
        });
        horizontalToolBar.add(btnRedo);

        horizontalToolBar.addSeparator();

        JButton btnTrash = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/trash.png"));
        btnTrash.setToolTipText("Trash");
        btnTrash.addActionListener((event) -> {
            System.out.println(XShape._shapes);
        });
        horizontalToolBar.add(btnTrash);

    }

    public void createToolBarV(){
    
        // La barre d'outils à proprement parler
        verticalToolBar.setOrientation(JToolBar.VERTICAL);
        verticalToolBar.setPreferredSize(new Dimension(100, verticalToolBar.getPreferredSize().height));
        verticalToolBar.setFloatable(false);

    }


    public JToolBar getHorizontalToolBar() {
        return horizontalToolBar;
    }

    public JToolBar getVerticalToolBar() {
        return verticalToolBar;
    }

}
