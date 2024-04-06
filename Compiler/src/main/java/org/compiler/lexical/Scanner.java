package org.compiler.lexical;

import org.compiler.lexical.errors.UnrecognizedSymbolError;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private char currentChar = ' ';
    // private byte srcIndex = 0;
    private final StringBuffer src;
    private int row;
    private int column;
    private  StringBuffer currentSpeeling;
    public Scanner(StringBuffer src){
        this.src = src;
    }
    public Scanner(String src){
        this.src = new StringBuffer(src);
    }

    /*
        Token :: Identifier | Integer-Literal | Operator |
                    ; | : | := | | (|) | eot
    */
    private byte scanToken(){


//      Identifier ::= Letter(Letter|Digit)
        if( isLetter(currentChar) ) { //Letter

            takeIt();//Letter
            while ((isLetter(currentChar)||isDigit(currentChar))) // (Letter|Digit)]
            {


                takeIt();

            }
            return Token.IDENTIFIER;
        }
//      Integer-Literal :: = Digit(Digit)*
        if(isDigit(currentChar)){ //Digit
            takeIt();
            while (isDigit(currentChar))// (Digit)*
                takeIt();
            return Token.INTLITERAL;
        }


//      Operator :: = +|-|*|/|<|>|=\

        switch (currentChar) {
                case '+': case '-':
                    takeIt();
                    return Token.OPAD;
                case '*':case'/':
                    takeIt();
                    return Token.OPMUL;
                case'<':case'>':case '=':
                    takeIt();
                    return Token.OPREL;


        }



//       ; | : | := | | (|) | eot
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
                    //  takeIt();
                    return Token.COLON;
                }

            case '~' :
                takeIt();
                return Token.IS;

            case '(' :
                takeIt();
                return Token.LPAREN;

            case ')' :
                takeIt();
                return Token.RPAREN;
            case '.':
                takeIt();
                return Token.DOT;
            case '\000' :
                return Token.EOT;

            default :
                //Lexical Error
                return 21;

        }


    }

    private void scanSeparator(){
        switch (currentChar){
            case '!':
                takeIt();
                while (isGraphic(currentChar))
                    takeIt();
                take('\n');

            case ' ': case '\n':
                takeIt();
                break;
        }

    }

    private boolean isGraphic(char currentChar) {
        return (32 <= currentChar && currentChar <= 126);
    }

    private void takeIt(){
        column++;
        currentSpeeling.append(currentChar);
        currentChar = src.charAt(0);
        if(src.length()>1)
            src.deleteCharAt(0);


    }

    private void take(char c){
        if(currentChar == c) {
            row++;
            currentChar = src.charAt(1);
            src.deleteCharAt(1);
        }else{
            throw new UnrecognizedSymbolError("O simbolo "+currentChar+" Não é reconhecido!");
        }

    }
    public List<Token> scan(){
        List<Token> result = new ArrayList<Token>();

        while (src.length()>1) {
            currentSpeeling = new StringBuffer();
            while (currentChar == '!' || currentChar == ' ' || currentChar == '\n')
                scanSeparator();
            currentSpeeling = new StringBuffer();
            byte currentKind = scanToken();

            result.add(new Token(currentKind, row,column-currentSpeeling.toString().length(), currentSpeeling.toString()));
        }
        return result;
    }
    private boolean isDigit(char c){
        return(c >= 48 && c <= 57);
    }
    private boolean isLetter(char c){

        return ( (65<= c && c <=90 ) || (97<= c && c <=122 )  );

    }

}
