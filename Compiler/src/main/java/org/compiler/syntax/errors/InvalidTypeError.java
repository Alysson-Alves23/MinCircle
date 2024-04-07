package org.compiler.syntax.errors;

public class InvalidTypeError extends SyntaxError {

    public InvalidTypeError(){
        super("O Tipo especificado não existe!");
    }
    public InvalidTypeError(String msg){
        super(msg);
    }
}
