package org.compiler.syntax.errors;

public class UnexpectedTokenError extends SyntaxError{

    public UnexpectedTokenError(String msg){
        super(msg);
    }
    public UnexpectedTokenError(){
        super("Token não esperado nessa posição!");
    }
}
