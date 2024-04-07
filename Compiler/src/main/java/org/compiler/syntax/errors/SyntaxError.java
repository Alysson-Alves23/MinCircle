package org.compiler.syntax.errors;

public class SyntaxError extends Error{

    SyntaxError(String msg){
        super(msg);
    }
    SyntaxError(){
        super("Sintaxe indaquada!");
    }
}
