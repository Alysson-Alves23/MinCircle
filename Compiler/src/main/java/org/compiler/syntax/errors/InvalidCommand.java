package org.compiler.syntax.errors;

public class InvalidCommand extends SyntaxError{

    public InvalidCommand(){
        super("O comando especificado não é válido! ");
    }
    public InvalidCommand(String msg){
        super(msg);
    }
}
