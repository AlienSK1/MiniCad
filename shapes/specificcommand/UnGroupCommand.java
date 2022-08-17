package is.shapes.specificcommand;

import is.command.Command;

public class UnGroupCommand implements Command {
    @Override
    public boolean doIt() {
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
