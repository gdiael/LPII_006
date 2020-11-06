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

	public int getHeght(){
		return this.height;
	}

	public void setHeght(int value){
		this.height = value;
	}

	public String subTreeString(String prefix){
		String val = this.toString();
		if(this.isLeaf()) return val + "\n";
		String valSpace = "";
		for(int i = 0; i < val.length(); i++){
			valSpace += " ";
		}
		String dStr = " \u252C\u2500> " + (this.hasRight() ? this.right.subTreeString(prefix + valSpace + " \u2502   "): "*\n");
		String eStr = prefix + valSpace + " \u2514\u2500> " + (this.hasLeft() ? this.left.subTreeString(prefix + valSpace + "     ") : "*\n");
		return val + dStr + eStr;
	}

	public String toString() {
		return Integer.toString(value);
	}
}
