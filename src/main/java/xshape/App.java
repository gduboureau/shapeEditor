package xshape;

import xshape.UI.AwtApp;
import xshape.UI.FxApp;
import xshape.UI.XShape;

public class App {

    public static void main(String[] args) {
        XShape appAwt = new AwtApp();
        appAwt.run();
        XShape appFx = new FxApp();
        appFx.run();

    }

}
