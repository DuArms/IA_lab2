import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class BFS {
    public static Queue<Node> queue = new LinkedList<>();

    BFS(MazeState mazeState, Node curentPoint) {
        queue.add(curentPoint);
        do {
            System.out.println(curentPoint + "   " + mazeState.finish);
            curentPoint = queue.peek();
            queue.remove();
            if (curentPoint.equals(mazeState.finish)) {
                MazeState.maze[curentPoint.x][curentPoint.y] = MazeState.vizitat;
                mazeState.getSymbolicMaze();
                System.exit(1);
            }

            List<Node> neighbors = mazeState.findNeighbors(curentPoint);
            MazeState.maze[curentPoint.x][curentPoint.y] = MazeState.vizitat;
            queue.addAll(neighbors);

        } while (!queue.isEmpty());
    }

}
