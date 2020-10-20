import java.util.*;

class se_node {
    public Node n;
    int f;
    int g;
    int h;

    public se_node(Node n, int f, int g, int h) {
        this.n = n;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    @Override
    public String toString() {
        return "{" +
                "n=" + n +
                ", f=" + f +
                ", g=" + g +
                ", h=" + h +
                '}';
    }
}

public class SA {
    private static double sansa = 90;
    private List<se_node> list;

    int g(Node n) {
        return Math.abs(MazeState.start.y - n.y) + Math.abs(MazeState.start.x - n.x);
    }

    int h(Node n) {
        return Math.abs(MazeState.finish.y - n.y) + Math.abs(MazeState.finish.x - n.x);
    }

    SA(MazeState mazeState) {
        list = new ArrayList<>();
        int n = MazeState.dimension[0];
        int m = MazeState.dimension[1];
        Node[][] last = new Node[n][m];

        list.add(new se_node(MazeState.start, 0, 0, 0));
        last[MazeState.start.x][MazeState.start.y] = MazeState.start;
        last[MazeState.finish.x][MazeState.finish.y] = MazeState.finish;

        Node curentNode = MazeState.start;

        while (!curentNode.equals(MazeState.finish) && !list.isEmpty()) {
            List<Node> vecini = mazeState.findNeighbors(curentNode);

            for (Node vecin : vecini) {
                list.add(new se_node(vecin, g(vecin) + h(vecin), g(vecin), h(vecin)));
                mazeState.tranzition(vecin);
            }

            curentNode = getNextNode(mazeState, curentNode);
            SA.sansa *= 0.70;
        }

        System.out.println(PrintMaze.getSymbolicMaze(MazeState.maze, MazeState.dimension));
        for (var k : last) {
            for (var j : k) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

    }


    private Node getNextNode(MazeState mazeState, Node curentNode) {
        int min = 1 << 30;
        int poz = -1;
        int i = 0;
        Random random = new Random();
        for (se_node potentialNode : list) {
            if (min > potentialNode.f || random.nextInt(100) < SA.sansa ) {
                curentNode = potentialNode.n;
                min = potentialNode.f;
                poz = i;

            }
            i++;
        }
        if (poz != -1) {
            list.remove(poz);
            mazeState.tranzition(curentNode);
            System.out.println(list);
        }
        return curentNode;
    }
}
