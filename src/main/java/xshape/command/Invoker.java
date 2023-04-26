package xshape.command;

import java.util.Stack;

public class Invoker {

    private Stack<ICommand> undoStack;
    private Stack<ICommand> redoStack;

    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void apply(ICommand command) {
        command.apply();
        undoStack.push(command);
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            ICommand command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            ICommand command = redoStack.pop();
            command.apply();
            undoStack.push(command);
        }
    }
    
}
