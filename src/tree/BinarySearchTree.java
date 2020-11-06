package tree;

import java.util.Deque;
import java.util.LinkedList;

public class BinarySearchTree {

	protected Node root;

	private Node getNode(Node node, int value) {
		if (value < node.value && node.hasLeft()) {
			return getNode(node.left, value);
		} else if (value > node.value && node.hasRight()) {
			return getNode(node.right, value);
		} else {
			return node;
		}
	}

	private int nodeHeghtByChildren(Node node){
		int heghtE = node.hasLeft() ? node.left.height : 0;
		int heghtD = node.hasRight() ? node.right.height : 0;
		return 1 + Math.max(heghtE, heghtD);
	}

	public boolean add(int value) {
		if (isEmpty()) {
			this.root = new Node(value);
			this.root.height = 1;
			return true;
		} else {
			return this.add(this.root, value);
		}
	}

	private boolean add(Node node, int value){
		boolean wasAdd = false;
		if (value < node.value) {
			if(node.hasLeft()){
				wasAdd = add(node.left, value);
			} else {
				node.left = new Node(value);
				node.left.height = 1;
				wasAdd = true;
			}
		} else if(value > node.value) {
			if(node.hasRight()){
				wasAdd = add(node.right, value);
			} else {
				node.right = new Node(value);
				node.right.height = 1;
				wasAdd = true;
			}
		}
		if(wasAdd){
			node.height = nodeHeghtByChildren(node);
		}
		return wasAdd;
	}

	private boolean isAVL(Node node){
		if(node == null) return true;
		int heightE = node.hasLeft() ? node.left.height : 0;
		int heightD = node.hasRight() ? node.right.height : 0;
		if(node.height != 1 + Math.max(heightD, heightE)) return false;
		if(Math.abs(heightE - heightD) > 1) return false;
		return (node.hasLeft() ? isAVL(node.left) : true) && (node.hasRight() ? isAVL(node.right) : true);
	}

	public boolean isAVL(){
		return this.isAVL(this.root);
	}

	private int updateHeight(Node node){
		if(node == null){
			return 0;
		}
		
		int l = updateHeight(node.left);
		int r = updateHeight(node.right);
		node.height = 1 + Math.max(l, r);
		return node.height;
	}

	public boolean contains(int value) {
		Node node = getNode(root, value);
		return node == null ? false : node.value == value;
	}

	public boolean isFull(Node node, int level, int lastLevel) {
		//return size() == Math.pow(2, height()) - 1;
		
		if (node.hasLeft() && node.hasRight()) {
			return isFull(node.left, level + 1, lastLevel) && isFull(node.right, level + 1, lastLevel);
		} else if (!node.hasLeft() && node.hasRight()) {
			return level == lastLevel && isFull(node.right, level + 1, lastLevel);
		} else if (node.hasLeft() && !node.hasRight()) {
			return level == lastLevel && isFull(node.left, level + 1, lastLevel);
		} else {
			return level == lastLevel;
		}
	}

	public boolean isFull() {
		if (root == null) {
			return true;
		}
		return isFull(root, 1, height());
	}

	private boolean isComplete(Node node, int index, int size) {
		if (node == null) {
			return true;
		}
		if (index >= size) {
			return false;
		}
		int count = 2 * index + 1;
		return isComplete(node.left, count, size) && isComplete(node.right, count + 1, size);
	}

	public boolean isComplete() {
		return isComplete(root, 0, size());
	}

	private int height(Node node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public int height() {
		return height(root);
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + size(node.left) + size(node.right);
		}
	}

	public int size() {
		return size(root);
	}

	public boolean isEmpty() {
		return root == null;
	}

	private void inOrder(Node node, Visitor<Integer> visitor) {
		if (node != null) {
			inOrder(node.left, visitor);
			visitor.visit(node.value);
			inOrder(node.right, visitor);
		}
	}

	public void inOrder(Visitor<Integer> visitor) {
		inOrder(root, visitor);
	}

	public void preOrder(Visitor<Integer> visitor) {
		preOrder(root, visitor);
	}

	private void posOrder(Node node, Visitor<Integer> visitor) {
		if (node != null) {
			posOrder(node.left, visitor);
			posOrder(node.right, visitor);
			visitor.visit(node.value);
		}
	}

	public void posOrder(Visitor<Integer> visitor) {
		posOrder(root, visitor);
	}
	
	
	private void preOrder(Node node, Visitor<Integer> visitor) {
		if (node != null) {
			System.out.print("( " + node.height + ", ");
			
			visitor.visit(node.value);
			preOrder(node.left, visitor);
			preOrder(node.right, visitor);
			System.out.print(")");
		}
	}

	public void levelOrder(Visitor<Integer> visitor) {
		Deque<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.removeFirst();
			visitor.visit(node.value);
			if (node.hasLeft()) {
				queue.addLast(node.left);
			}
			if (node.hasRight()) {
				queue.addLast(node.right);
			}
		}
	}

	// Converte os valores da lista para string
	public String toString(){
		if(this.isEmpty()) return "Arvore vazia!";
        return root.subTreeString("");
	}
}
