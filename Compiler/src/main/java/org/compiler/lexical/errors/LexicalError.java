package org.compiler.lexical.errors;

public class LexicalError extends Error {

    static final String msg ="Erro na análise lexica";
    public LexicalError(){
        super(msg);
    }
    LexicalError(String msg){
        super(msg);
    }
}
