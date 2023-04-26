package xshape.command;

import xshape.UI.jfx.FxApp;
import xshape.shape.Shape;

public class GroupCommand implements ICommand{
    private final Shape shape;


    public GroupCommand(Shape shape){
        this.shape = shape;
    }

    @Override
    public void apply() {
       FxApp.group.add(shape);
    }

    @Override
    public void undo() {
        FxApp.group.remove(shape);
    }
    
}
