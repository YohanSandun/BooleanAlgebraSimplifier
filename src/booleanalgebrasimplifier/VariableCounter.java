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
import java.util.ArrayList;

/**
 *
 * @author Yohan Sandun
 */
public class VariableCounter {
 
    private ArrayList<Character> vars;
    private Node node;

    public VariableCounter(Node node) {
        this.node = node;
        vars = new ArrayList<Character>();
    }
   
    public int getVarCount() {
        count(node);
        return vars.size();
    }
    
    private void count(Node node) {
        if (node.getType() == NodeType.VARIABLE) {
            VarNode vn = (VarNode)node;
            if (!vars.contains(vn.getVar()))
                vars.add(vn.getVar());
        }
        else if (node.getType() == NodeType.AND) {
            AndNode an = (AndNode)node;
            count(an.getLeft());
            count(an.getRight());
        }
        else if (node.getType() == NodeType.OR) {
            OrNode on = (OrNode)node;
            count(on.getLeft());
            count(on.getRight());
        }
        else if (node.getType() == NodeType.NOT) {
            NotNode nn = (NotNode)node;
            count(nn.getNode());
        }
    }
}
