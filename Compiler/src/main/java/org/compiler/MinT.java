package org.compiler;


import org.compiler.lexical.Scanner;
import org.compiler.lexical.Token;
import org.compiler.syntax.Node;
import org.compiler.syntax.Parser;

public class MinT {

    private final String src;
    private String obj;
    public MinT(String src){
        this.src = src;
    }
    public String generateObject(){
    //Análise Lexica
        Token[] tokenList = new Scanner(src).scan();
        Node ast = new Parser(tokenList).parse();
//        for(Token token: tokenList)
//            System.out.println(token.spelling);


        return obj;
    }


}
