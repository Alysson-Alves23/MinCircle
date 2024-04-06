package org.compiler.lexical.errors;

public class UnrecognizedSymbolError extends LexicalError{

    private static final String msg = "Token não reconhecido!";
    public UnrecognizedSymbolError() {
        super(msg);
    }

    public UnrecognizedSymbolError(String msg){
        super(msg);
    }

}
