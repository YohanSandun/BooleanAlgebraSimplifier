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
import java.util.HashMap;

/**
 *
 * @author Yohan Sandun
 */
public class TruthTable {

    private boolean[] outputs;
    private boolean[][] inputs;
    private ArrayList<Character> vars;
    
    public TruthTable(int varCount, Node node, ArrayList<Character> vars) {
        this.vars = vars;
        
        if (varCount == 1) 
            prepareTable1(node);
    }
    
    public void print() {
        for (int i = 0; i < vars.size(); i++) 
            System.out.print("| " + vars.get(i) + " ");
        System.out.println("| F | ");
        
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length; j++) {
                System.out.print("| "+(inputs[i][j] ? "1" : "0") );
            }
            System.out.println(" | " + (outputs[i] ? "1" : "0") + " | ");
        }
    }
    
    // For one variable
    private void prepareTable1(Node node) {
        outputs = new boolean[2];
        inputs = new boolean[2][1];
        
        HashMap<Character, Boolean> map = new HashMap<Character, Boolean>();
        Interpreter ip = new Interpreter(map);
        
        inputs[0][0] = false;
        map.put(vars.get(0), false);
        outputs[0] = ip.run(node);
        map.clear();
        
        inputs[1][0] = true;
        map.put(vars.get(0), true);
        outputs[1] = ip.run(node);
        map.clear();
    }
    
}
