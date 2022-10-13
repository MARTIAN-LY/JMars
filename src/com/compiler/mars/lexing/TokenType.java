package com.compiler.mars.lexing;

import java.util.HashMap;

/**
 *@Author Martian
 *
 **/

public enum TokenType {


    // Singe-character tokens.
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // One or two character tokens
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,
    AND, AND_LOGIC,
    OR, OR_LOGIC,



    // Literals
    IDENTIFIER, STRING, NUMBER,

    // Keywords.
    TRUE, FALSE, VAR, FUN, FOR, WHILE,
    IF, ELSE, NIL, CLASS,
    PRINT, RETURN, SUPER, THIS,

    EOF;

    public static final HashMap<TokenType,String> tokenTypes = new HashMap<>(){{
        put(LEFT_PAREN,"(");
        put(RIGHT_PAREN,")");
        put(LEFT_BRACE,"{");
        put(RIGHT_BRACE,"}");
        put(COMMA,",");
        put(DOT,".");
        put(MINUS,"-");
        put(PLUS,"+");
        put(SEMICOLON,";");
        put(SLASH,"/");
        put(STAR,"*");
        put(BANG,"!");
        put(EQUAL,"=");
        put(EQUAL_EQUAL,"==");
        put(GREATER,">");
        put(GREATER_EQUAL,">=");
        put(LESS,"<");
        put(LESS_EQUAL,"<=");
        put(AND,"&&");
        put(AND_LOGIC,"&&");
        put(OR,"||");
        put(OR_LOGIC,"|");
        put(IDENTIFIER,"");
        put(STRING,"");
        put(NUMBER,"");
        put(TRUE,"");
        put(FALSE,"");
        put(VAR,"");
        put(FUN,"");
        put(FOR,"");
        put(WHILE,"");
        put(IF,"");
        put(ELSE,"");
        put(NIL,"");
        put(CLASS,"");
        put(PRINT,"");
        put(RETURN,"");
        put(SUPER,"");
        put(THIS,"");
        put(EOF,"");
    }};

}
