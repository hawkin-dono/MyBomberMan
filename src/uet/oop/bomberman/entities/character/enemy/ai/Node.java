package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.level.Coordinates;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Math.abs;

public class Node implements Comparable<Node> {
    private int x, y;
    private int costFromStart;
    private int estimateCostToGoal;
    private Node previousNode;
    private ArrayList<Node> neighbors;
    private static Board board;
    private Enemy e;
    private static final int ROW = 13, COL = 31;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.costFromStart = Integer.MAX_VALUE;
        this.estimateCostToGoal = Integer.MAX_VALUE;
        this.previousNode = null;
        neighbors = new ArrayList<>();
    }

    public Node(int x, int y, Enemy e) {
        this.x = x;
        this.y = y;
        this.e = e;
        this.costFromStart = Integer.MAX_VALUE;
        this.estimateCostToGoal = Integer.MAX_VALUE;
        this.previousNode = null;
        neighbors = new ArrayList<>();
    }

    public boolean isValid(int x, int y) {
        return (x >= 0 && x < COL && y >= 0 && y < ROW);
    }

    public boolean isBlocked(int dx, int dy) {

        int xr = Coordinates.tileToPixel(x), yr = Coordinates.tileToPixel(y) - 16;
        if(dy < 0) {
            yr += 16 - 1 ; xr += 8;
        } else if(dx > 0) {
            yr += 8; xr += 1;
        } else if(dy > 0) {
            xr += 8; yr += 1;
        } else {
            xr += 15; yr += 8;
        }
        int xx = Coordinates.pixelToTile(xr) + dx;
        int yy = Coordinates.pixelToTile(yr) + dy;
        Entity a = board.getEntity(xx, yy, e);
        if(a == null) return true;
        if(!(a instanceof Grass) && !(a instanceof Enemy)) {
            return true;
        }
        return false;
    }
    public void addSuroundNeighbors() {
        if(!isBlocked(1, 0)) {
            neighbors.add(new Node(x + 1, y));
        }
        if(!isBlocked(-1, 0)) {
            neighbors.add(new Node(x - 1, y));
        }
        if(!isBlocked(0, 1)) {
            neighbors.add(new Node(x , y + 1));
        }
        if(!isBlocked(0, -1)) {
            neighbors.add(new Node(x, y - 1));
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCostFromStart() {
        return costFromStart;
    }

    public void setCostFromStart(int costFromStart) {
        this.costFromStart = costFromStart;
    }

    public int getEstimateCostToGoal() {
        return estimateCostToGoal;
    }

    public void setEstimateCostToGoal(int estimateCostToGoal) {
        this.estimateCostToGoal = estimateCostToGoal;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    public ArrayList<Node> getNeighbors() {
        addSuroundNeighbors();
        return neighbors;
    }

    public void setNeighbors(ArrayList<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public static Board getBoard() {
        return board;
    }

    public static void setBoard(Board board) {
        Node.board = board;
    }

    public Enemy getE() {
        return e;
    }

    public void setE(Enemy e) {
        this.e = e;
    }

    public int getTotalCost() {
        return costFromStart + estimateCostToGoal;
    }
    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }
    public int caculateHvalue(Node targetNode) {
        return abs(x - targetNode.getX()) + abs(y - targetNode.getY());
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node) {
            Node other = (Node)obj;
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }

    public int compareTo(Node other) {
        return Integer.compare(getTotalCost(), other.getTotalCost());
    }
}

