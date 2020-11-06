package tree;

public class Node {

	protected int value;

	protected Node left;
	protected Node right;
	
	protected int height;

	public Node(int value) {
		this.value = value;
	}

	public boolean isLeaf() {
		return !hasLeft() && !hasRight();
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public String toString() {
		return Integer.toString(value);
	}
}