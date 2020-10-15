import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MazeState {
    public static final int spatiu = -1;
    //    public static final int start = 0;
    public static final int perete = 1;
    public static final int curent = 2;
    public static final int vizitat = 3;
    public static final int drum = 4;
    public static final int pStart = 5;
    public static final int pFinal = 6;


    public static Node start;
    public static Node finish;

    public static Node curentLocation;

    public static int dimension[] = new int[2];

    public static int[][] maze;

    public void setStart(Node start) {
        MazeState.start = start;
        MazeState.curentLocation = start;
    }

    public void setFinish(Node finish) {
        MazeState.finish = finish;
    }

    MazeState() {

    }

    MazeState(int[][] a) {
        maze = a;
    }

    public void initFromFile() {
        List<int[]> lab = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("maze.txt"));
            String st;
            while ((st = reader.readLine()) != null) {
                int[] linie = new int[st.length()];
                for (int i = 0; i < st.length(); i++) {
                    switch (st.charAt(i)) {
                        case MazeGenerator.perete:
                            linie[i] = MazeState.perete;
                            break;
                        case MazeGenerator.spatiu:
                            linie[i] = MazeState.spatiu;
                            break;
                        case MazeGenerator.start:
                            linie[i] = MazeState.spatiu ;
                            this.setStart(new Node(lab.size(), i));
                            break;
                        case MazeGenerator.finish:
                            linie[i] = MazeState.spatiu ;
                            this.setFinish(new Node(lab.size(), i));
                            break;
                        default:
                    }
                }
                lab.add(linie);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        maze = new int[lab.size()][lab.get(0).length];

        for (int i = 0; i < lab.size(); i++)
            maze[i] = lab.get(i);

        dimension[0] = maze.length;
        dimension[1] = maze[0].length;

        curentLocation = start;
    }

    public ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            if (freeSpace(node.x + i, node.y)) {
                neighbors.add(new Node(node.x + i, node.y));
            }
            if (freeSpace(node.x, node.y + i)) {
                neighbors.add(new Node(node.x, node.y + i));
            }
        }
        return neighbors;
    }

    public MazeState tranzition(Node goTo) {
        if (validateTransition(goTo)) {
            maze[curentLocation.x][curentLocation.y] = MazeState.vizitat;
            MazeState.curentLocation = goTo;
            return this;
        }
        return null;
    }

    public static Boolean validateTransition(Node goTo) {
        return freeSpace(goTo.x, goTo.y);
    }

    public static Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension[0] && y < dimension[1];
    }

    public static Boolean freeSpace(int x, int y) {
        return pointOnGrid(x, y) && maze[x][y] == MazeState.spatiu;
    }


}
