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

/**
 *
 * @author Yohan Sandun
 */
public class Parser {
    
    private Token currentToken;
    private int index;
    private final ArrayList<Token> tokens;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
        index = -1;
        advance();
    }
    
    private void advance() {
        index++;
        if (index < tokens.size())
            currentToken = tokens.get(index);
    }
    
    public Node parse() {
        return parseOr();
    }
    
    private Node parseOr() {
        Node left = parseAnd();
        while (currentToken.getType() == TokenType.OR) {
            advance();
            Node right = parseAnd();
            left = new OrNode(left, right);
        }
        return left;
    }
    
    private Node parseAnd() {
        Node left = parseAtom();
        while (currentToken.getType() == TokenType.AND) {
            advance();
            Node right = parseAtom();
            left = new AndNode(left, right);
        }
        return left;
    }
    
    private Node parseAtom() {
        if (currentToken.getType() == TokenType.VARIABLE) {
            Token token = currentToken;
            advance();
            return new VarNode(token.getVariable());
        }
        else if (currentToken.getType() == TokenType.LP) {
            advance();
            Node node = parseOr();
            advance(); // closing paranthesis
            return node;
        }
        else if (currentToken.getType() == TokenType.NOT) {
            advance();
            return new NotNode(parseAtom());
        }
        return null;
    }
}
