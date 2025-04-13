
public class Fabric {
	private Node[][] nodes;
	private int width;
	private int height;
	private int spacing;
	private double stiffness;
	
	public Fabric(int x, int y, int width, int height, int spacing, double stiffness) {
		super();
		this.nodes = new Node[height][width];
		this.width = width;
		this.height = height;
		this.setSpacing(spacing);
		this.setStiffness(stiffness);
		for(int i=0; i<height;i++) {
			for(int j=0; j<width; j++) {
				nodes[i][j]=new Node(new Vector(x + (j*spacing),y + (i*spacing)));
			}
		}
	}

	public Fabric() {
		super();
		this.nodes = new Node[10][10];
	}
	
	public Node[][] getNodes() {
		return nodes;
	}

	public void setNodes(Node[][] nodes) {
		this.nodes = nodes;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpacing() {
		return spacing;
	}

	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}

	public double getStiffness() {
		return stiffness;
	}

	public void setStiffness(double stiffness) {
		this.stiffness = stiffness;
	}
	
}
