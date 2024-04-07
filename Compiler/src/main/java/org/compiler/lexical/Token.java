package org.compiler.lexical;

public class Token {
    public byte kind;

    public int row;
    public int column;
    public String spelling;

    public final static byte
            IDENTIFIER = 0, INTLITERAL = 1, OPAD = 2, OPMUL = 3,
            OPREL = 4, BEGIN = 5, PROGRAM = 6, DO = 7, ELSE = 8, END = 9,
            IF = 10, VAR = 11, BOOL = 12, THEN = 13,
            WHILE = 14, SEMICOLON = 15, COLON = 16,
            BECOMES = 17, INTEGER = 18, LPAREN = 19,
            RPAREN = 20, DOT = 21, EOT = 22, EOF = 23, OR = 24, AND = 25, TRUE = 26, FALSE = 27;
    public final static String[] spellings = {
            "<id>", "<int-lit>", "<op-ad>", "<op-mul>", "<op-rel>",
            "begin", "program", "do", "else", "end", "if", "var", "bool", "then", "while",
            ";", ":", ":=", "integer", "(", ")",".","<eot>","<eof>","or","and","true","false"
    };
    public Token(byte kind, int row,int column,String spelling){
        if (kind == IDENTIFIER) {
            byte i = 0;
            for (String s : spellings) {
                if (s.equals(spelling)) {
                    kind = i;
                    break;
                }
                i++;
            }
        }
        this.kind = kind;
        this.row = row;
        this.column = column;
        this.spelling = spelling;

    }

    public  String getSpellings() {
        return spellings[this.kind];
    }


}
