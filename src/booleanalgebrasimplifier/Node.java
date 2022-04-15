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
public class Node {
    
    public enum NodeType {
        VARIABLE,
        AND,
        OR,
        NOT
    }
    
    private final NodeType type;

    public Node(NodeType type) {
        this.type = type;
    }

    public NodeType getType() {
        return type;
    }
    
}

class VarNode extends Node {

    private final char var;

    public VarNode(char var) {
        super(NodeType.VARIABLE);
        this.var = var;
    }

    public char getVar() {
        return var;
    }
    
    @Override
    public String toString() {
        return String.valueOf(var);
    }

}

class AndNode extends Node {
    private final Node left;
    private final Node right;

    public AndNode(Node left, Node right) {
        super(NodeType.AND);
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
    
    @Override
    public String toString() {
        return "(" + left.toString() + "." + right.toString() + ")";
    }
}

class OrNode extends Node {
    private final Node left;
    private final Node right;

    public OrNode(Node left, Node right) {
        super(NodeType.OR);
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
    
    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }
}

class NotNode extends Node {
    private final Node node;

    public NotNode(Node node) {
        super(NodeType.NOT);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }
    
    @Override
    public String toString() {
        return node.toString() + "'";
    }
}
