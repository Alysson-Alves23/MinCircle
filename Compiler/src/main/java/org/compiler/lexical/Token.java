package org.compiler.lexical;

public class Token {
    public byte kind;

    public int row;
    public int column;
    public String spelling;

    public final static byte
            IDENTIFIER = 0, INTLITERAL = 1, OPAD = 2, OPMUL = 3,
            OPREL = 4, BEGIN = 5, PROGRAM = 6, DO = 7, ELSE = 8, END = 9,
            IF = 10, IN = 11, BOOL = 12, THEN = 13, VAR = 14,
            WHILE = 15, SEMICOLON = 16, COLON = 17,
            BECOMES = 18, INTEGER = 19, LPAREN = 20,
            RPAREN = 21, DOT = 22, EOT = 23,EOF = 24, TRUE = 25,FALSE = 26;
    private final static String[] spellings = {
            "<id>", "<int-lit>", "<op-ad>", "<op-mul>", "<op-rel>",
            "begin", "program", "do", "else", "end", "if", "in","bool", "let", "then",
            ";", ":", ":=", "integer", "(", ")",".","<eot>","<eof>","or","and","true","false"
    };
    public Token(byte kind, int row,int column,String spelling){
        this.kind = kind;
        this.row = row;
        this.column = column;
        this.spelling = spelling;

    }

    public  String getSpellings() {
        return spellings[this.kind];
    }


}
