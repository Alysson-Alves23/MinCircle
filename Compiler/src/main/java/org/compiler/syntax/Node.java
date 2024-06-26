package org.compiler.syntax;

import org.compiler.lexical.Token;

public class Node {

    public byte rule;
    Token token;
    public  final Node[] childs;

    public final static byte
            PROGRAM = 0, BODY = 1, DECLARATIONS = 2,
            DECLARATION = 3, VARIABLEDECLARATION = 4,
            TYPE = 5, SIMPLE_TYPE = 6, COMPOUND_COMMAND = 7,
            COMMAND = 8, ASSIGNMENT = 9, CONDITIONAL = 10, ITERATIVE = 11,
            EXPRESSION = 12, SIMPLEEXPRESSION = 13, TERM = 14, FACTOR = 15,
            LITERAL = 16, VARIABLE = 17, BOOLLIT = 18, INTLITERAL  = 19, COMMAND_LIST = 20,TOKEN = 21;
    private final static String [] rules ={
        "<programa>","<corpo>","<declarações>",
            "<declaração>","<declaração-de-variavel>",
            "<tipo>","<tipo-simples>","<comando-composto>",
            "<comando>","<atribuição>","<condicional>","<iterativo>",
            "<expressão>","<expressão-simples>","<termo>","<fator>",
            "<literal>","<variável>","<bool-lit>","<int-lit>","<lista-de-comandos>","<token>"
    };

    public Node(byte rule, Token token) {
        this.token = token;
        this.childs = new Node[]{};
        this.rule = rule;
    }
    public Node(byte rule, Node[] childs) {
        this.rule = rule;
        this.childs = childs;
    }

    public String getRule() {
        return rules[rule];
    }

    public void printTree(String prefix, boolean isTail) {
        System.out.print(prefix + (isTail ? "└── " : "├── ") + ((token == null)?getRule():""));
        if ((token != null)) {
            System.out.println(" " + token.spelling);
        } else {
            System.out.println();
        }
        for (int i = 0; i < childs.length - 1; i++) {
            childs[i].printTree(prefix + (isTail ? "    " : "│   "), false);
        }
        if (childs.length > 0) {
            childs[childs.length - 1].printTree(prefix + (isTail ?"    " : "│   "), true);
        }
    }
}
