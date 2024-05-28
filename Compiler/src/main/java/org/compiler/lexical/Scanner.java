package org.compiler.lexical;

import org.compiler.lexical.errors.UnrecognizedSymbolError;

public class Scanner {
    private char currentChar = ' ';
    private final StringBuffer src;
    private int row = 1;
    private int column = 0;
    private StringBuffer currentSpelling = new StringBuffer();

    public Scanner(StringBuffer src) {
        this.src = src;
        advance();  // Initialize currentChar with the first character
    }

    public Scanner(String src) {
        this.src = new StringBuffer(src);
        advance();  // Initialize currentChar with the first character
    }

    /*
        Token :: Identifier | Integer-Literal | Operator |
                 ; | : | := | | (|) | eot
    */
    private byte scanToken() {
        if (currentChar == '\000') {
            currentSpelling = new StringBuffer(Token.spellings[Token.EOF]);
            return Token.EOF;
        }

        // Identifier ::= Letter(Letter|Digit)
        if (isLetter(currentChar)) {  // Letter
            takeIt();  // Letter
            while (isLetter(currentChar) || isDigit(currentChar)) {  // (Letter|Digit)
                takeIt();
            }
            return Token.IDENTIFIER;
        }

        // Integer-Literal ::= Digit(Digit)*
        if (isDigit(currentChar)) {  // Digit
            takeIt();
            while (isDigit(currentChar)) {  // (Digit)*
                takeIt();
            }
            return Token.INTLITERAL;
        }

        // Operator ::= +|-|*|/|<|>|=\
        switch (currentChar) {
            case '+': case '-':
                takeIt();
                return Token.OPAD;
            case '*': case '/':
                takeIt();
                return Token.OPMUL;
            case '<': case '>': case '=':
                takeIt();
                return Token.OPREL;
        }

        // ; | : | := | | (|) | eot
        switch (currentChar) {
            case ';':
                takeIt();
                return Token.SEMICOLON;
            case ':':
                takeIt();
                if (currentChar == '=') {
                    takeIt();
                    return Token.BECOMES;
                } else {
                    return Token.COLON;
                }
            case '(':
                takeIt();
                return Token.LPAREN;
            case ')':
                takeIt();
                return Token.RPAREN;
            case '.':
                takeIt();
                return Token.DOT;
            default:
                throw new UnrecognizedSymbolError("O símbolo " + currentChar + " não é reconhecido!");
        }
    }

    private void scanSeparator() {
        switch (currentChar) {
            case '!':
                takeIt();
                while (isGraphic(currentChar)) {
                    takeIt();
                }
                take('\n');
                break;
            case ' ': case '\n':
                takeIt();
                break;
            default:
                break;
        }
    }

    private boolean isGraphic(char currentChar) {
        return (32 <= currentChar && currentChar <= 126);
    }

    private void takeIt() {
        currentSpelling.append(currentChar);
        if (currentChar == '\n') {
            row++;
            column = 0;
        } else {
            column++;
        }
        advance();
    }

    private void take(char c) {
        if (currentChar == c) {
            advance();
        } else {
            throw new UnrecognizedSymbolError("O símbolo " + currentChar + " não é reconhecido!");
        }
    }

    private void advance() {
        if (src.length() > 0) {
            currentChar = src.charAt(0);

            src.deleteCharAt(0);
        } else {

            currentChar = '\000';  // EOF
        }
    }

    public Token scan() {
        while (currentChar == '!' || currentChar == ' ' || currentChar == '\n') {
            scanSeparator();
        }

        currentSpelling = new StringBuffer();
        byte currentKind = scanToken();
        return new Token(currentKind, row, column - currentSpelling.toString().length(), currentSpelling.toString());
    }

    private boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    private boolean isLetter(char c) {
        return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
    }
}
