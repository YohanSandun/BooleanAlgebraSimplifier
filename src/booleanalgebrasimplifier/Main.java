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

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Yohan Sandun
 */
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String input = scanner.nextLine();
        
        Tokenizer tokenizer = new Tokenizer(input);
        Parser parser = new Parser(tokenizer.getTokens());
        Node node = parser.parse();
        
        VariableCounter counter = new VariableCounter(node);
        int varCount = counter.getVarCount();
        ArrayList<Character> vars = counter.getVars();
        
        TruthTable tt = new TruthTable(varCount, node, vars);
        tt.print();
        
    }
    
}
