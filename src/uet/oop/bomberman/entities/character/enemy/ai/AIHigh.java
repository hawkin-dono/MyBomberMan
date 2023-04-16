package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

import java.util.HashSet;
import java.util.PriorityQueue;

public class AIHigh extends AI{
    private PriorityQueue<Node> openList;
    private HashSet<Node> closedList;
    private Bomber bomber;
    private Enemy enemy;
    private Node startNode;
    private Node endNode;
    private Board board;

    public AIHigh(Bomber bomber, Enemy enemy, Board board) {
        this.bomber = bomber;
        this.enemy = enemy;
        this.board = board;
        Node.setBoard(board);
        openList = new PriorityQueue<>();
        closedList = new HashSet<>();
    }

    @Override
    public int calculateDirection() {
        startNode = new Node(enemy.getXTile(), enemy.getYTile());
        startNode.setE(enemy);
        endNode = new Node(bomber.getXTile(), bomber.getYTile());
        openList.clear();
        closedList.clear();
        startNode.setCostFromStart(0);
        startNode.setEstimateCostToGoal(startNode.caculateHvalue(endNode));
        while(!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedList.add(currentNode);
            if(currentNode.equals(endNode)) {
                return getPath(endNode);
            }
            for(Node neighbor : startNode.getNeighbors()) {
                if(closedList.contains(neighbor)) continue;
                int tentativeCost = currentNode.getCostFromStart() + 1 + neighbor.caculateHvalue(endNode);
                if(currentNode.getCostFromStart() == Integer.MAX_VALUE || currentNode.getTotalCost() > tentativeCost) {
                    neighbor.setPreviousNode(currentNode);
                    neighbor.setCostFromStart(currentNode.getCostFromStart() + 1) ;
                    neighbor.setEstimateCostToGoal(neighbor.caculateHvalue(endNode));
                    openList.add(neighbor);
                }
            }
        }
        return random.nextInt(4);
    }

    public int getPath(Node node) {
        if(node.getPreviousNode() == null) {
            return random.nextInt(4);
        }
        while(node.getPreviousNode().getPreviousNode() != null) {
            node = node.getPreviousNode();
        }
        int dx = node.getX() - startNode.getX();
        int dy = node.getY() - startNode.getY();
        if(dx > 0) {
            return 1;
        }
        else if(dx < 0) {
            //System.out.println("go go");
            return 3;

        }
        else if(dy > 0) {
            return 2;
        }
        else {
            return 0;
        }
    }
}
