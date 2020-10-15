import java.util.Arrays;

public class PrintMaze {

    public static String getRawMaze(int[][] maze) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int[] row : maze) {
            sb.append(i).append(" : ").append(Arrays.toString(row)).append(",\n");
            i++;
        }
        return sb.toString();
    }

    public static String getSymbolicMaze(int[][] maze, int[] dimension) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                functie(sb, i, j, maze);
            }
            sb.append("\n");
        }
        WrtiteFile.write("./out.txt", sb.toString());
        return sb.toString();
    }

    static void functie(StringBuilder sb, int i, int j, int[][] maze) {
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
            case MazeState.drum:
                sb.append(MazeGenerator.drum);
                break;
            case MazeState.pStart:
                sb.append(MazeGenerator.start);
                break;
            case MazeState.pFinal:
                sb.append(MazeGenerator.finish);
                break;
            default:
                System.out.println(maze[i][j]);
        }
    }

}
