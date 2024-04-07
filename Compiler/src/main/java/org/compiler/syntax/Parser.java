package org.compiler.syntax;

import org.compiler.lexical.Token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Parser {
    private final Stack<Token> tokens;

    public Parser(Token[] tokens) {
        this.tokens = new Stack<Token>();
        this.tokens.addAll(Arrays.asList(tokens));

    }
    private Token acceptIt() {
        if (!tokens.isEmpty()) {
            return tokens.pop();
        } else {
            return new Token(Token.EOT, -1, -1, "<eot>");
        }
    }

    private Token accept(byte kind){
        if(tokens.peek().kind == kind)
            return acceptIt();
        throw new Error();
    };

    private Node parseProgram(){

        return new Node(Node.PROGRAM,new Node[]
                {
                        new Node(Node.TOKEN,accept(Token.PROGRAM)),
                        parseIdeintifier(),
                        new Node(Node.TOKEN,accept(Token.COLON)),
                        parseBody(),
                        new Node(Node.TOKEN,accept(Token.DOT)),
                }
                );
    }
    private Node parseIdeintifier(){
        return new Node(Node.TOKEN, accept(Token.IDENTIFIER));
    }

    private Node parseBody(){
       return  new Node(Node.BODY,new Node[]
               {
                       parseDeclarations(),
                       parseCompoundCommand()
               }
       );
    }
    private Node parseDeclarations() {
        if(tokens.peek().kind == Token.BEGIN)
            return new Node(Node.DECLARATIONS,new Token(Token.EOT,0,0,"<eot>"));
        List<Node> childs = new ArrayList<Node>();
        childs.add(parseDeclaration());
        while (tokens.peek().kind == Token.SEMICOLON){
            childs.add(new Node(Node.TOKEN, acceptIt()));
            parseDeclaration();
        };
        return new Node(Node.DECLARATIONS,(Node[])childs.toArray());
    }

    private Node parseDeclaration() {
        return new Node(Node.DECLARATION,new Node[]
                {
                        parseVarDeclaration()
                }
        );
    }

    private Node parseVarDeclaration() {
        return new Node(Node.VARIABLEDECLARATION,new Node[]
                {
                        parseIdeintifier(),
                        parseType(),
                }
        );
    }

    private Node parseType() {
        return new Node(Node.TYPE, new Node[]{
                parseSimpleType()
        });
    }

    private Node parseSimpleType() {
        Token token = acceptIt();
        if((token.kind != Token.INTEGER )&&(token.kind != Token.BOOL ))
            throw new Error();
        return new Node(Node.SIMPLE_TYPE, new Node[]{
                new Node(Node.TOKEN,token)
        });
    }

    private Node parseCompoundCommand() {
        return new Node(Node.COMPOUND_COMMAND, new Node[]{
                new Node(Node.TOKEN,accept(Token.BEGIN)),
                parseCommandList(),
                new Node(Node.TOKEN,accept(Token.END))
        });
    }

    private Node parseCommandList() {
        if(tokens.peek().kind == Token.END)
            return new Node(Node.COMMAND_LIST,new Token(Token.EOT,0,0,"<eot>"));
        List<Node> childs = new ArrayList<Node>();
        childs.add(parseCommand());
        while (tokens.peek().kind == Token.SEMICOLON){
            childs.add(new Node(Node.TOKEN, acceptIt()));
            parseCommand();
        };
        return new Node(Node.COMMAND_LIST,(Node[])childs.toArray());



    }

    private Node parseCommand() {
        switch (tokens.peek().kind){
            case Token.IDENTIFIER:
                return new Node(Node.COMMAND,new Node[]{
                    parseAssignment()
                });
            case Token.IF:
                return new Node(Node.COMMAND,new Node[]{
                        parseConditional()
                });
            case Token.WHILE:
                return new Node(Node.COMMAND,new Node[]{
                        parseInteractive()
                });
            case Token.BEGIN:
                return new Node(Node.COMMAND,new Node[]{
                        parseCompoundCommand()
                });
            default:
                throw new Error();
        }
    }


    private Node parseAssignment() {
        return new Node(Node.COMMAND,new Node[]{
                parseIdeintifier(),
                new Node(Node.TOKEN, accept(Token.BECOMES)),
                parseExpression()
        });

    }

    private Node parseExpression() {
        Node simple_expression = parseSimpleExpression();
       if(tokens.peek().kind == Token.OPREL){
           return new Node(Node.EXPRESSION,new Node[]{
                   simple_expression,
                   new Node(Node.TOKEN, acceptIt()),
                   parseSimpleExpression()
           });
       }
        return new Node(Node.EXPRESSION,new Node[]{
                simple_expression
        });
    }

    private Node parseSimpleExpression() {
            List<Node> childs = new ArrayList<Node>();
            childs.add(parseTerm());
            while (tokens.peek().kind == Token.OPAD){
                childs.add(new Node(Node.TOKEN, acceptIt()));
                childs.add(parseTerm());
            }
            return new Node(Node.SIMPLEEXPRESSION, (Node[]) childs.toArray());
    }

    private Node parseTerm() {
        List<Node> childs = new ArrayList<Node>();
        childs.add(parseFator());
        while (tokens.peek().kind == Token.OPMUL){
            childs.add(new Node(Node.TOKEN, acceptIt()));
            childs.add(parseFator());
        }
        return new Node(Node.SIMPLEEXPRESSION, (Node[]) childs.toArray());
    }

    private Node parseFator() {
        switch (tokens.peek().kind){
            case Token.IDENTIFIER:
                return new Node(Node.FACTOR,new Node[]{parseVariable()});
            case Token.INTLITERAL: case Token.FALSE: case Token.TRUE:
                return new Node(Node.FACTOR,new Node[]{parseLiteral()});
            case Token.LPAREN:
                return new Node(Node.FACTOR,new Node[]{
                        new Node(Node.TOKEN,acceptIt()),
                        parseExpression(),
                        new Node(Node.TOKEN,accept(Token.RPAREN))
                });
            default:
                throw new Error();
        }

    }
    private Node parseVariable() {
        return new Node(Node.VARIABLE, accept(Token.IDENTIFIER));
    }
    private Node parseLiteral() {
        return new Node(Node.LITERAL,acceptIt());
    }



    private Node parseConditional() {


            List<Node> childs = Arrays.asList(new Node[]{
                    new Node(Node.TOKEN, accept(Token.IF)),
                    parseExpression(),
                    new Node(Node.TOKEN, accept(Token.THEN)),
                    parseCommand()
            });
            if( tokens.peek().kind == Token.ELSE){
                childs.add(new Node(Node.TOKEN,acceptIt()));
                childs.add(parseCommand());
            }

            return new Node(Node.CONDITIONAL, (Node[]) childs.toArray());


    }
    private Node parseInteractive() {

        List<Node> childs = Arrays.asList(
                new Node(Node.TOKEN, accept(Token.WHILE)),
                parseExpression(),
                new Node(Node.TOKEN, accept(Token.DO)),
                parseCommand());


        return new Node(Node.ITERATIVE, (Node[]) childs.toArray());

    }




}
