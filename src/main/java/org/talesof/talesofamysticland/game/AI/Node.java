package org.talesof.talesofamysticland.game.AI;

public class Node {

    Node parent;
    public int col;
    public int row;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean checked;
    boolean open;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
