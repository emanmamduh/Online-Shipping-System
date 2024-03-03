package com.example.myproject;

public class ShoppingCartCommandInvoker {
    private ShoppingCartCommand command;

    public void setCommand(ShoppingCartCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
