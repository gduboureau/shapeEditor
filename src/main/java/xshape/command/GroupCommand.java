package xshape.command;

import xshape.shape.Group;
import xshape.shape.Shape;

public class GroupCommand implements ICommand{
    private final Shape shape;
    Group group;

    public GroupCommand(Shape shape, Group g){
        this.shape = shape;
        this.group = g;
    }

    @Override
    public void apply() {
        group.add(shape);
    }

    @Override
    public void undo() {
        group.remove(shape);
    }
    
}
