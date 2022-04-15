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

/**
 *
 * @author Yohan Sandun
 */
public class Token {
    
    public enum TokenType {
        VARIABLE,
        AND,
        OR,
        NOT,
        LP,
        RP
    }
    
    private TokenType type;
    private char variable;

    public TokenType getType() {
        return type;
    }

    public Token(TokenType type, char variable) {
        this.type = type;
        this.variable = variable;
    }

    public Token(TokenType type) {
        this.type = type;
    }
    
    public void setType(TokenType type) {
        this.type = type;
    }

    public char getVariable() {
        return variable;
    }

    public void setVariable(char variable) {
        this.variable = variable;
    }
}
