/*
 * Copyright 2022 Yohan Sandun.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package booleanalgebrasimplifier;

import booleanalgebrasimplifier.Token.TokenType;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Yohan Sandun
 */
public class Tokenizer {
    
    private int index;
    private String expression;
    private char currentChar;
    private Stack<Integer> notOperatorPositions;
    private boolean previousVar;

    public Tokenizer(String expression) {
        this.expression = expression;
        index = -1;
        currentChar = '\0';
        notOperatorPositions = new Stack<Integer>();
        notOperatorPositions.push(0);
        advance();
    }
    
    private void advance() {
        index ++;
        if (index < expression.length())
            currentChar = expression.charAt(index);
        else
            currentChar = '\0';
    }
    
    public ArrayList<Token> getTokens() {
        ArrayList<Token> tokens = new ArrayList<Token>();
        
        while (currentChar != '\0') {
            if (currentChar == ' ') {
                advance();
            }
            else if (currentChar >= 'A' && currentChar <= 'E') {
                if (previousVar)
                    tokens.add(new Token(TokenType.AND));
                notOperatorPositions.pop();
                notOperatorPositions.push(tokens.size());
                tokens.add(new Token(TokenType.VARIABLE, currentChar));
                previousVar = true;
                advance();
            }
            else if (currentChar == '+') {
                tokens.add(new Token(TokenType.OR));
                previousVar = false;
                advance();
            }
            else if (currentChar == '.') {
                tokens.add(new Token(TokenType.AND));
                previousVar = false;
                advance();
            }
            else if (currentChar == '\'') {
                tokens.add(notOperatorPositions.peek(), new Token(TokenType.NOT));
                previousVar = true;
                advance();
            }
            else if (currentChar == '(') {
                if (previousVar)
                    tokens.add(new Token(TokenType.AND));
                notOperatorPositions.push(tokens.size());
                notOperatorPositions.push(tokens.size());
                tokens.add(new Token(TokenType.LP));
                previousVar = false;
                advance();
            }
            else if (currentChar == ')') {
                notOperatorPositions.pop();
                tokens.add(new Token(TokenType.RP));
                previousVar = true;
                advance();
            }
        }
        
        return tokens;
    }
    
}
