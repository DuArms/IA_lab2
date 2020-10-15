import java.awt.desktop.PreferencesEvent;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BFS {
    private static Queue<Node> queue = new LinkedList<>();
    public static List<Node> path = new ArrayList<>();

    private static void function() {

        List<Integer> vecini = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            int x = MazeState.curentLocation.x + i;
            int y = MazeState.curentLocation.y;
            if (MazeState.pointOnGrid(x, y)) {
                vecini.add(auxiliar[x][y]);
            }
            x = MazeState.curentLocation.x;
            y = MazeState.curentLocation.y + i;
            if (MazeState.pointOnGrid(x, y)) {
                vecini.add(auxiliar[x][y]);
            }
        }
        auxiliar[MazeState.curentLocation.x][MazeState.curentLocation.y] = Collections.min(vecini) - 1;
    }

    public static int[][] auxiliar = null;

    BFS(MazeState mazeState) {
        auxiliar = new int[MazeState.dimension[0]][MazeState.dimension[1]];
        boolean stop = false;
        queue.add(MazeState.curentLocation);
        do {

            if (mazeState.tranzition(queue.poll()) != null) {
                function();
                if (MazeState.curentLocation.equals(MazeState.finish)) {
                    stop = true;
                } else {

                    List<Node> neighbors = mazeState.findNeighbors(MazeState.curentLocation);

                    if (neighbors.contains(MazeState.finish)) {
                        queue.clear();
                        queue.add(MazeState.finish);
                        mazeState.tranzition(MazeState.finish);
                    } else {
                        for (var vecin : neighbors) {
                            if (!queue.contains(vecin))
                                queue.add(vecin);
                        }
                    }
                }
            }
        } while (!queue.isEmpty() && !stop);

        path.add(MazeState.finish);
        Node node = path.get(path.size() - 1);
        while (!node.equals(MazeState.start)) {
            int min = getValue(node);
            Node newNode = null;
            for (int i = -1; i <= 1; i += 2) {
                if (MazeState.pointOnGrid(node.x + i, node.y) && min < auxiliar[node.x + i][node.y] && 0 != auxiliar[node.x + i][node.y]) {
                    newNode = new Node(node.x + i, node.y);
                    min = auxiliar[node.x + i][node.y];
                }
                if (MazeState.pointOnGrid(node.x, node.y + i) && min < auxiliar[node.x][node.y + i] && 0 != auxiliar[node.x][node.y + i]) {
                    newNode = new Node(node.x, node.y + i);
                    min = auxiliar[node.x][node.y + i];
                }
            }

            if(newNode == null){
                path.clear();
                break;
            }
            node = newNode;
            path.add(node);
        }

        aplicaDrum();
    }

    private void aplicaDrum() {
        for (var a : path){
            MazeState.maze[a.x][a.y] = MazeState.drum ;
        }
        MazeState.maze[MazeState.start.x][MazeState.start.y] = MazeState.pStart;
        MazeState.maze[MazeState.finish.x][MazeState.finish.y] = MazeState.pFinal;

        PrintMaze.getSymbolicMaze(MazeState.maze, MazeState.dimension);
    }

    private static int getValue(Node node) {
        return auxiliar[node.x][node.y];
    }
}
