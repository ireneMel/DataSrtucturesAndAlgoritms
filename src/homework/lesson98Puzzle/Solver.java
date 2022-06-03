package homework.lesson98Puzzle;

import princeton.lib.In;
import princeton.lib.StdOut;

import java.util.PriorityQueue;
import java.util.Stack;

public final class Solver {
    private final Stack<Board> solutionBoards;
    private boolean isSolvable;


    public Solver(Board initial) {
        if (initial == null) throw new NullPointerException();
        isSolvable = false;
        solutionBoards = new Stack<>();
        PriorityQueue<Node> searchNodes = new PriorityQueue<>();

        searchNodes.add(new Node(initial, null));

        int counter = 0;

        while (!searchNodes.peek().board.isGoal()) {
            counter++;
            Node node = searchNodes.poll();
            if (node.moves >= 32) {
                counter = -1;
                break;
            }
            for (Board board : node.board.neighbors()) {
                if (node.prevNode == null || node.prevNode != null && !node.prevNode.board.equals(board))
                    searchNodes.add(new Node(board, node));
                if (node.moves >= 32) {
                    counter = -1;
                    break;
                }
            }
        }

        Node current = searchNodes.peek();
        while (current.prevNode != null) {
            solutionBoards.push(current.board);
            current = current.prevNode;
        }
        solutionBoards.push(current.board);

        if (current.board.equals(initial) && counter != -1) isSolvable = true;

    }

    public int moves() {
        if (!isSolvable()) return -1;
        return solutionBoards.size() - 1;
    }


    public Iterable<Board> solution() {
        if (isSolvable()) {
            return solutionBoards;
        }
        return null;
    }

    public boolean isSolvable() {
        return isSolvable;
    }


    private static class Node implements Comparable<Node> {
        private final Board board;
        private final Node prevNode;
        private final int moves;
        private final int manhattan;

        public Node(Board board, Node prevNode) {
            this.board = board;
            this.prevNode = prevNode;
            this.manhattan = board.manhattan();
            if (prevNode != null) moves = prevNode.moves + 1;
            else moves = 0;
        }

        @Override
        public int compareTo(Node that) {
            int priorityDiff = (this.manhattan + this.moves) - (that.manhattan + that.moves);
            return priorityDiff == 0 ? this.manhattan - that.manhattan : priorityDiff;
        }
    }

    public static void main(String[] args) {
        In in =
                //new In("src/homework/lesson9/data_for_test/puzzle04.txt"); //+
                //new In("src/homework/lesson9/data_for_test/puzzle31.txt"); //+
                //new In("src/homework/lesson9/data_for_test/puzzle3x3-unsolvable.txt");//+
                //new In("src/homework/lesson9/data_for_test/mytest1_unsolv.txt");
                new In("src/homework/lesson9/data_for_test/mytest3.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}