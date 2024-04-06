package org.compiler.syntax;

public class NoTerminal {
    public byte kind;


    public final static byte
            PROGRAM = 0, BODY = 1, DECLARATIONS = 2,
            DECLARATION = 3, VARIABLEDECLARATION = 4,
            TYPE = 5, SIMPLE_TYPE = 6, COMPOUND_COMMAND = 7,
            COMMAND = 8, ASSIGNMENT = 9, CONDITIONAL = 10, ITERATIVE = 11,
            EXPRESSION = 12, SIMPLEEXPRESSION = 13, TERM = 14, FACTOR = 15,
            LITERAL = 16, VARIABLE = 17, BOOLLIT = 18;
    private final static String [] spellings ={
        "<programa>","<corpo>","<declarações>",
            "<declaração>","<declaração-de-variavel>",
            "<tipo>","<tipo-simples>","<comando-composto>",
            "<comando>","<atribuição>","<condicional>","<iterativo>",
            "<expressão>","<expressão-simples>","<termo>","<fator>",
            "<literal>","<variável>","<bool-lit>"
    };
    public NoTerminal(byte kind, NoTerminal [] childs) {
        this.kind = kind;
    }

}
