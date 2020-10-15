import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Backtracking {

    public static Stack<Node> path = new Stack<>();
    public  static  Boolean stop = false;

    Backtracking(MazeState mazeState) {
        if (MazeState.curentLocation.equals(MazeState.finish)) {
            Backtracking.stop = true;

            for ( var a : path){
                MazeState.maze[a.x][a.y] = MazeState.drum ;
            }

            MazeState.maze[MazeState.start.x][MazeState.start.y] = MazeState.pStart;
            MazeState.maze[MazeState.finish.x][MazeState.finish.y] = MazeState.pFinal;
            PrintMaze.getSymbolicMaze(MazeState.maze, MazeState.dimension);
        }

        List<Node> neighbors = mazeState.findNeighbors(MazeState.curentLocation);

        if (neighbors.contains(MazeState.finish)) {
            mazeState.tranzition(MazeState.finish);
            path.add(MazeState.finish);
            new Backtracking(mazeState);
        }
        Node fixPoint = MazeState.curentLocation;
        for (var vecin : neighbors) {
            MazeState.curentLocation = fixPoint;
            if (mazeState.tranzition(vecin) != null) {
                path.add(vecin);
                new Backtracking(mazeState);

                if(stop){

                    break ;
                }
                path.pop();

            }
        }
    }


}
