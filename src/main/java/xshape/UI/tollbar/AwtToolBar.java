package xshape.UI.tollbar;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

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
        btnLoad.addActionListener((event) -> {
            Test test = new Test(event, "Implements Load");
        });
        horizontalToolBar.add(btnLoad);

        JButton btnSave = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/save.png"));
        btnSave.setToolTipText("Save");
        btnSave.addActionListener((event) -> {
            Test test = new Test(event, "Implemetns Save");
        });
        horizontalToolBar.add(btnSave);

        horizontalToolBar.addSeparator();

        JButton btnUndo = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/undo.png"));
        btnUndo.setToolTipText("Undo");
        btnUndo.addActionListener(event -> {
            invoker.undo();
        });
        horizontalToolBar.add(btnUndo);

        JButton btnRedo = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/redo.png"));
        btnRedo.setToolTipText("Redo");
        btnRedo.addActionListener(event -> {
            invoker.redo();
        });
        horizontalToolBar.add(btnRedo);

        horizontalToolBar.addSeparator();

        JButton btnTrash = new JButton(new ImageIcon("src/main/java/xshape/UI/icons/trash.png"));
        btnTrash.setToolTipText("Trash");
        btnTrash.addActionListener((event) -> {
            Test test = new Test(event, "Implements Trash");
        });
        horizontalToolBar.add(btnTrash);

    }

    public void createToolBarV(){
    
        // La barre d'outils à proprement parler
        verticalToolBar.setOrientation(JToolBar.VERTICAL);
        verticalToolBar.setPreferredSize(new Dimension(80, verticalToolBar.getPreferredSize().height));
        verticalToolBar.setFloatable(false);

    }


    public JToolBar getHorizontalToolBar() {
        return horizontalToolBar;
    }

    public JToolBar getVerticalToolBar() {
        return verticalToolBar;
    }

}
