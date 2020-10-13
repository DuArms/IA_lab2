import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MazeState {
    public static final int perete = 1;
    public static final int spatiu = 0;
    public static final int vizitat = 3;

    public static int[][] maze;

    public Node start;
    public Node finish;
    public int dimension;

    public void setStart(Node start) {
        this.start = start;
    }

    public void setFinish(Node finish) {
        this.finish = finish;
    }

    MazeState() {
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
                            this.setStart(new Node(lab.size(), i));
                            break;
                        case MazeGenerator.finish:
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

        maze = new int[lab.size()][lab.size()];

        for (int i = 0; i < lab.size(); i++)
            maze[i] = lab.get(i);

        this.dimension = maze.length;
    }

    public String getRawMaze() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int[] row : maze) {
            sb.append(i + " : " + Arrays.toString(row) + ",\n");
            i++;
        }
        return sb.toString();
    }

    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                switch (maze[i][j]) {
                    case MazeState.vizitat:
                        sb.append(MazeGenerator.vizitat);
                        break;
                    case MazeState.perete:
                        sb.append(MazeGenerator.perete);
                        break;
                    case MazeState.spatiu:
                        sb.append(MazeGenerator.spatiu);
                        break;
                    default:
                }
            }
            sb.append("\n");
        }
        WrtiteFile.write("./out.txt", sb.toString());
        return sb.toString();
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


    public Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    public Boolean freeSpace(int x, int y) {
        return pointOnGrid(x, y) && maze[x][y] == 0;
    }


}
