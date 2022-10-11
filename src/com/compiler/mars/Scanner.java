package com.compiler.mars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.compiler.mars.TokenType.*;
import static java.lang.Character.*;

/**
 * Eats source code，
 * excretes tokens.
 *
 * var number_demo1 = 12345;
 *     ^          ^
 *    start     current
 **/

public class Scanner {

    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    public Scanner(String source) {
        this.source = source;
    }

    public List<Token> scanTokens() {

        while (!isAtEnd()) {
            // We are at the beginning of the next lexeme.
            start = current;
            scanToken();
        }

        // Signature of the end of source code.
        addToken(EOF);
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(' -> addToken(LEFT_PAREN);
            case ')' -> addToken(RIGHT_PAREN);
            case '{' -> addToken(LEFT_BRACE);
            case '}' -> addToken(RIGHT_BRACE);
            case ',' -> addToken(COMMA);
            case '.' -> addToken(DOT);
            case '-' -> addToken(MINUS);
            case '+' -> addToken(PLUS);
            case ';' -> addToken(SEMICOLON);
            case '*' -> addToken(STAR);
            case '!' -> addToken(match('=') ? BANG_EQUAL : BANG);
            case '<' -> addToken(match('=') ? LESS_EQUAL : LESS);
            case '>' -> addToken(match('=') ? GREATER_EQUAL : GREATER);
            case '=' -> addToken(match('=') ? EQUAL_EQUAL : EQUAL);
            case '&' -> addToken(match('&') ? AND : AND_LOGIC);
            case '|' -> addToken(match('|') ? OR : OR_LOGIC);
            case '/' -> comment();

            case '\n' -> line++;
            case ' ', '\r', '\t' -> {
            }
            case '"' -> string();

            default -> {
                if (isDigit(c)) {
                    /** Number */
                    number();
                } else if (isAlphabet(c)) {
                    /** Keyword or identifier. */
                    identifier();
                } else {
                    Mars.error(line, "Unexpected character.");
                }
            }
        }
    }

    /**
     *  Two kinds of comment.
     * */
    private void comment() {
        if (match('/')) {
            // This line is a comment.
            advance();
            while (!isAtEnd() && peek() != '\n') {
                advance();
            }
        } else if (match('*')) {
            /* This block is comment.*/
            advance();
            while (!isAtEnd() && !(peek() == '*' && peekNext() == '/')) {
                if (peek() == '\n') {
                    line++;
                }
                advance();
            }
        } else {
            addToken(SLASH);
        }
    }


    /**
     *  "I love you,baby."
     *  ^                 ^
     * start           current
     * */
    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n')
                line++;
            advance();
        }

        if (isAtEnd()) {
            Mars.error(line, "Unterminated string.");
            return;
        }

        // The end of the string.
        advance();

        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    /**
     *  222222222.33333333333
     *  ^        ^           ^
     * start   current     current
     * */
    private void number() {
        while (isDigit(peek())) {
            advance();
        }
        if (peek() == '.' && isDigit(peekNext())) {
            advance();
            while (isDigit(peek())) {
                advance();
            }
        }
        addToken(NUMBER,
                Double.parseDouble(source.substring(start, current)));
    }

    /**
     * Every lexeme is a keyword or an identifier.
     * var number = 589.368
     * ^  ^
     * */
    private void identifier() {
        while (isAlphaNumeric(peek())) {
            advance();
        }
        String word = source.substring(start, current);
        TokenType type = keywords.get(word);
        if (type == null) {
            type = IDENTIFIER;
        }
        addToken(type);
    }


    /** To simplify using, we define 2 methods. */
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String lexeme = source.substring(start, current);
        tokens.add(new Token(type, lexeme, literal, line));
    }

    /** Consume a character and move forward. */
    private char advance() {
        current++;
        return source.charAt(current - 1);
    }

    /** Look ahead.*/
    private char peek() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current);
    }

    /** Look ahead.*/
    private char peekNext() {
        if (isAtEnd()) {
            return '\0';
        }
        return source.charAt(current + 1);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    /**
     * Solve cases like != , <= , >= , ==
     * @param expected Second character of the operator.
     * @return Whether matched or not.
     */
    private boolean match(char expected) {
        if (isAtEnd())
            return false;
        if (source.charAt(current) != expected)
            return false;

        current++;
        return true;
    }

    private boolean isAlphaNumeric(char c) {
        return isAlphabet(c) || isDigit(c);
    }

    private boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                (c == '_');
    }

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("if", IF);
        keywords.put("else", ELSE);
        keywords.put("for", FOR);
        keywords.put("while", WHILE);
        keywords.put("print", PRINT);
        keywords.put("return", RETURN);
        keywords.put("false", FALSE);
        keywords.put("true", TRUE);
        keywords.put("class", CLASS);
        keywords.put("super", SUPER);
        keywords.put("this", THIS);
        keywords.put("var", VAR);
        keywords.put("fun", FUN);
        keywords.put("nil", NIL);
    }
}
