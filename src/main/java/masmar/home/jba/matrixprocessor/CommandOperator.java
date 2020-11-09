package masmar.home.jba.matrixprocessor;

class CommandOperator {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
