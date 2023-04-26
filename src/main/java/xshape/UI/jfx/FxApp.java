package xshape.UI.jfx;

import javafx.embed.swing.JFXPanel;
import xshape.UI.XShape;
import xshape.UI.toolbar.FxToolBar;
import xshape.shapeFactory.ShapeFactory;
import xshape.shapeFactory.ShapeFactoryFx;

public class FxApp extends XShape {

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(FxApplication._root);
    }

    @Override
    public void run() {
        new JFXPanel();
        draw();
        createToolBar();
        FxApplication.launch(FxApplication.class);       
    }

    @Override
    protected void createToolBar() {
        toolbar = new FxToolBar(invoker);
        toolbar.createToolBarH();
        toolbar.createToolBarV();
        FxApplication._root.getChildren().add(((FxToolBar) toolbar).getVerticalToolBar());
        FxApplication._root.getChildren().add(((FxToolBar) toolbar).getHorizontalToolBar());
    }
}