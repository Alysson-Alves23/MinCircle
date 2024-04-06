package org.compiler;


import org.compiler.lexical.Scanner;
import org.compiler.lexical.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class MinT {

    private String src = "";
    private String obj = "";
    public MinT(String src){
        this.src = src;
    }
    public String generateObject(){
    //Análise Lexica
        List<Token> tokenList = new Scanner(src).scan();
        for(Token token: tokenList)
            System.out.println(token.spelling);

        return obj;
    }




}
