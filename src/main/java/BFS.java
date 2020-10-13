import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class BFS {
    public static Queue<Node> queue = new LinkedList<>();

    BFS(MazeState mazeState, Node curentPoint) {
        queue.add(curentPoint);
        do {
            curentPoint = queue.peek();
            System.out.println(curentPoint + "   " + mazeState.finish);
            queue.remove();
            if (curentPoint.equals(mazeState.finish)) {
                MazeState.maze[curentPoint.x][curentPoint.y] = MazeState.vizitat;
                mazeState.getSymbolicMaze();
                System.exit(1);
            }

            List<Node> neighbors = mazeState.findNeighbors(curentPoint);
            if (neighbors.contains(mazeState.finish)) {
                queue.clear();
                queue.add(mazeState.finish);
                MazeState.maze[curentPoint.x][curentPoint.y] = MazeState.vizitat;
            } else {
                MazeState.maze[curentPoint.x][curentPoint.y] = MazeState.vizitat;
                for( var vecin : neighbors){
                    if ( !queue.contains(vecin))
                        queue.add(vecin);
                }
            }
        } while (!queue.isEmpty());
    }

}
