
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        SimulationPanel simulation = new SimulationPanel();
        JFrame frame = new JFrame("Fabric Simulation");
        frame.add(simulation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        double dt= 0.005; // fixed timestamp becouse the simulation is too unstable with variable deltatimes.
        
        int frames = 0;
        long timer = System.currentTimeMillis();
        
        while (true) {

            simulation.update(dt); // dt in seconds
            simulation.repaint();

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
    }
}
