import java.util.List;

public class Backtracking {


    Backtracking(MazeState mazeState, Node curentPoint) {
        System.out.println(curentPoint + "   " + mazeState.finish);

        if (curentPoint.equals(mazeState.finish)) {
            MazeState.maze[curentPoint.x][curentPoint.y] = MazeState.vizitat;
            mazeState.getSymbolicMaze();
            System.exit(1);
        }

        List<Node> neighbors = mazeState.findNeighbors(curentPoint);
        MazeState.maze[curentPoint.x][curentPoint.y] = MazeState.vizitat;

        if(neighbors.contains(mazeState.finish)){
            new Backtracking(mazeState, mazeState.finish);
        }

        for (var vecin : neighbors) {
            new Backtracking(mazeState, vecin);
        }

    }


}
