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

import booleanalgebrasimplifier.Node.NodeType;
import java.util.HashMap;

/**
 *
 * @author Yohan Sandun
 */
public class Interpreter {
    
    private HashMap<Character, Boolean> vars;

    public Interpreter(HashMap<Character, Boolean> vars) {
        this.vars = vars;
    }
    
    public boolean run(Node node) {
        if (node.getType() == NodeType.AND) {
            AndNode an = (AndNode)node;
            return run(an.getLeft()) && run(an.getRight());
        }
        else if (node.getType() == NodeType.OR) {
            OrNode on = (OrNode)node;
            return run(on.getLeft()) || run(on.getRight());
        }
        else if (node.getType() == NodeType.NOT) {
            NotNode nn = (NotNode)node;
            return !run(nn.getNode());
        } 
        else {
            VarNode vn = (VarNode)node;
            return vars.get(vn.getVar());
        } 
    }
    
}
