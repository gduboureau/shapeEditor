package xshape;

import xshape.UI.XShape;
import xshape.UI.awt.AwtApp;
import xshape.UI.jfx.FxApp;

public class App {

    public static void main(String[] args) {
        XShape appAwt = new AwtApp();
        appAwt.run();
        XShape appFx = new FxApp();
        appFx.run();
    }

}
