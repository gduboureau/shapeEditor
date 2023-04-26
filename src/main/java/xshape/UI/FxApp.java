package xshape.UI;

import xshape.UI.tollbar.FxToolBar;
import xshape.shapeFactory.ShapeFactory;
import xshape.shapeFactory.ShapeFactoryFx;

public class FxApp extends XShape {

    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryFx(FxApplication._root);
    }
    @Override
    public void run() {
        draw();
        // createToolBar();
        FxApplication.launch(FxApplication.class);
        // createToolBar();
    }
    @Override
    protected void createToolBar() {
        toolbar = new FxToolBar(invoker);
        FxApplication._root.getChildren().add(((FxToolBar) toolbar).getVerticalToolBar());
        FxApplication._root.getChildren().add(((FxToolBar) toolbar).getHorizontalToolBar());
    }
}