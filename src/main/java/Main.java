
//        #(0.2) Alegeți o reprezentare a unei stări a problemei. Reprezentarea trebuie să fie suficient
//        # de explicită pentru a conține toate informaţiile necesare pentru continuarea găsirii unei soluții
//        # dar trebuie să fie și suficient de formalizată pentru a fi ușor de prelucrat/memorat.
//
//        # (0.2) Identificați stările speciale (inițială și finală) și implementați funcția de inițializare
//        # (primește ca parametrii instanța problemei, întoarce starea inițială) și funcția booleană care verifică
//        # dacă o stare primită ca parametru este finală.
//
//        # (0.2) Implementați tranziția ca o funcție care primește ca parametri o stare și parametrii tranziției
//        # și întoarce starea rezultată în urma aplicării tranziției. Validarea tranziției se face într-o funcție
//        #  booleană separată, cu aceeași parametrii.
//
//        # (0.2) Implementați strategia Backtracking.s
//        # (0.2) Implementați strategia BFS.
//        # (0.2) Implementați strategia Hillclimbing.


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//
//        MazeGenerator mazeGenerator = new MazeGenerator(20);
//        mazeGenerator.generateMaze();
//        mazeGenerator.getSymbolicMaze();

        MazeState mazeState = new MazeState();
//        new Backtracking(mazeState, mazeState.start);
        new BFS(mazeState, mazeState.start);
    }
}