package org.compiler;


import org.compiler.lexical.Scanner;
import org.compiler.lexical.Token;
import org.compiler.syntax.Node;
import org.compiler.syntax.Parser;

import java.util.ArrayList;

public class MinT {

    private final String src;
    private String obj;
    public MinT(String src){
        this.src = src;
    }
    public String generateObject(){
    //Análise Lexica
        Scanner s = new Scanner(src);
        ArrayList<Token> tokens = new ArrayList<>();
        Token currentToken;
        do{
           currentToken = s.scan();
           // System.out.println("token"+s.scan().spelling);
            tokens.add(currentToken);
        }while (currentToken.kind != 23);
        for(Token token: tokens)
            System.out.println(token.spelling);


        return obj;
    }


}
