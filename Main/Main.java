package Main;

public class Main {
    public static void main(String[] args) {
        System.out.println("[System] Initializing Chess Engine...");
        
        try {
            Game game = new Game();
            game.start();
        } catch (Exception e) {
            System.err.println("[Critical Error] Game terminated unexpectedly: " + e.getMessage());
        }
    }
}