import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class SimulationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Fabric fabric; 
	Node grabbedNode;
	Boolean rightClick;
	public static final int NODE_SIZE = 10;
	
	public SimulationPanel() {
        setFocusable(true);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1000, 1000));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Vector mouseP = new Vector(x,y); 
                Node[][] nodes =fabric.getNodes();
                double minDist=nodes[0][0].getP().distance(mouseP);
                for(int i=0; i < fabric.getHeight(); i++) {
        	        for(int j=0; j < fabric.getWidth(); j++) {
        	        	Node curN = nodes[i][j];
        	        	double curDist=curN.getP().distance(mouseP);
        	        	if (curDist<=minDist) {
        	        		minDist=curDist;
        	        		grabbedNode=curN;
        	        	}
        	        }
                }
                if (SwingUtilities.isLeftMouseButton(e)) {
                    // Left click
                	rightClick=false;
                    grabbedNode.setCurMovable(false);
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    // Right click
                	rightClick=true;
                    if(minDist<=NODE_SIZE*10) {
                    	grabbedNode.setLinked(false);
                    }
                }
                
                
                
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                grabbedNode.setCurMovable(true);

            }
        });
        addMouseMotionListener((MouseMotionListener) new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	int x = e.getX();
                int y = e.getY();
                Vector mouseP = new Vector(x,y); 
                if (grabbedNode != null) {
                	if(!rightClick) {
                        grabbedNode.setP(mouseP);
                	}else {
                		Node[][] nodes =fabric.getNodes();
                        double minDist=nodes[0][0].getP().distance(mouseP);
                        
                        for(int i=0; i < fabric.getHeight(); i++) {
                	        for(int j=0; j < fabric.getWidth(); j++) {
                	        	Node curN = nodes[i][j];
                	        	double curDist=curN.getP().distance(mouseP);
                	        	if (curDist<=minDist) {
                	        		minDist=curDist;
                	        		grabbedNode=curN;
                	        	}
                	        }
                        }
                        if(minDist<=NODE_SIZE*10) {
                        	grabbedNode.setLinked(false);
                        }
                	}
                }
            }
        });
        
        
    	fabric = new Fabric(200, 100, 50, 50, 15, 120);
    	
    	// set the upper corners to not be movable.
    	fabric.getNodes()[0][0].setMovable(false);
    	fabric.getNodes()[0][(int)(fabric.getWidth()-1)/2].setMovable(false);
    	fabric.getNodes()[0][fabric.getWidth()-1].setMovable(false);

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        Node[][] nodes = fabric.getNodes();
        for(int i=0; i < fabric.getHeight(); i++) {
	        for(int j=0; j < fabric.getWidth(); j++) {
	        	Node curN = nodes[i][j];
	        	if(curN.getLinked()) {
		        	g2d.fillOval((int)curN.getP().getX()-NODE_SIZE/2, (int)curN.getP().getY()-NODE_SIZE/2, NODE_SIZE, NODE_SIZE);
	        	}
	        	
	        	if(i < fabric.getHeight()-1 && (curN.getLinked() && nodes[i+1][j].getLinked())) {
	        		g2d.drawLine((int)curN.getP().getX(), (int)curN.getP().getY(), (int)nodes[i+1][j].getP().getX(), (int)nodes[i+1][j].getP().getY());
	        	}   
	        	
	        	if(j < fabric.getWidth()-1 && (curN.getLinked() && nodes[i][j+1].getLinked())) {
	        		g2d.drawLine((int)curN.getP().getX(), (int)curN.getP().getY(), (int)nodes[i][j+1].getP().getX(), (int)nodes[i][j+1].getP().getY());
	        	}
	        	
	        }
        }
    }
    
    
    public void update(double dt) {
        Vector gravity = new Vector(0, 9.81);
    	//Vector gravity = new Vector(0, 100);
        Node[][] nodes = fabric.getNodes();
        double dampingCoefficient = 0.1;

        for (int i = 0; i < fabric.getHeight(); i++) {
            for (int j = 0; j < fabric.getWidth(); j++) {
                Node curN = nodes[i][j];

                // Apply gravity
                curN.applyForce(Vector.mult(gravity, curN.getMass()));
                
                // Calculate and apply spring forces
                if (i < fabric.getHeight() - 1 && (curN.getLinked() && nodes[i + 1][j].getLinked())) {
                    Vector springForce = calculateSpringForce(curN, nodes[i + 1][j]);
                    curN.applyForce(springForce);
                    nodes[i + 1][j].applyForce(Vector.neg(springForce));
                }
                if (j < fabric.getWidth() - 1 && (curN.getLinked() && nodes[i][j+1].getLinked())) {
                    Vector springForce = calculateSpringForce(curN, nodes[i][j + 1]);
                    curN.applyForce(springForce);
                    nodes[i][j + 1].applyForce(Vector.neg(springForce));
                }
                
                // Calculate and apply damping
                Vector velocity = curN.getVelocity();
                Vector dampingForce = Vector.mult(velocity, -dampingCoefficient);
                curN.applyForce(dampingForce);
            }
        }

        // Now integrate all nodes
        for (int i = 0; i < fabric.getHeight(); i++) {
            for (int j = 0; j < fabric.getWidth(); j++) {
                nodes[i][j].integrate(dt);
            }
        }
    }

    
    // F=−k⋅Δx
    public Vector calculateSpringForce(Node a, Node b) {
    	double distance = a.getP().distance(b.getP());
    	// Calculate magnitude of spring force using Hooke's Law
        double restLength = fabric.getSpacing(); // Rest length of the spring
        double displacement = distance - restLength; // Displacement from rest length
        double magnitude = fabric.getStiffness() * displacement; // Hooke's Law
        
        // Calculate the direction of the force vector
    	double direction = a.getP().points(b.getP());
    	
    	Vector F = new Vector();
    	F.set(direction, magnitude);
    	
    	return F;
    }
}
