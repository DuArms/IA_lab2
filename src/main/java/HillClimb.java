import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HillClimb{

    private List<se_node> list;
    private static Queue<Node> queue = new LinkedList<>();
    public static int[][] auxiliar = null;
    private static List<Node> path= new ArrayList<>();

    int g(Node n) {
        return Math.abs(MazeState.start.y - n.y) + Math.abs(MazeState.start.x - n.x);
    }

    int h(Node n) {
        return Math.abs(MazeState.finish.y - n.y) + Math.abs(MazeState.finish.x - n.x);
    }

    HillClimb(MazeState mazeState) {
        list = new ArrayList<>();
        int n = MazeState.dimension[0];
        int m = MazeState.dimension[1];
        Node[][] last = new Node[n][m];
        auxiliar = new int[n][m];
        list.add(new se_node(MazeState.start,0, g(MazeState.start), h(MazeState.start)) );
        last[MazeState.start.x][MazeState.start.y] = MazeState.start;
        last[MazeState.finish.x][MazeState.finish.y] =null;

        Node curentNode = MazeState.start;
        MazeState.curentLocation = MazeState.start;

        while( !curentNode.equals( MazeState.finish ) && ! list.isEmpty()){
            List<Node> vecini = mazeState.findNeighbors(curentNode);

            for(Node vecin : vecini){
                list.add(new se_node(vecin,g(vecin) + h(vecin), g(vecin) , h(vecin)));
                mazeState.tranzition(vecin);
                if(  last[vecin.x][vecin.y]  == null  ) {
                    last[vecin.x][vecin.y] = curentNode;
                }
            }

            curentNode = getNextNode(mazeState, curentNode);
            MazeState.curentLocation = curentNode;
        }

        Node s = MazeState.finish;

        while (!s.equals(MazeState.start)) {
            System.out.println(s);
            MazeState.maze[s.x][s.y] = MazeState.drum;
            s = last[s.x][s.y];

        }

        PrintMaze.getSymbolicMaze(MazeState.maze, MazeState.dimension);

    }

    private Node getNextNode(MazeState mazeState, Node curentNode) {
        int min = 1 << 30;
        int poz = -1 ;
        int i=0;
        for(se_node potentialNode : list){
            if(min > potentialNode.f){
                curentNode = potentialNode.n;
                min = potentialNode.f;
                poz = i;
            }
            i++;
        }
        if(poz != -1){
            list.remove(poz);
            mazeState.tranzition(curentNode);
            System.out.println(list);
        }
        return curentNode;
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
