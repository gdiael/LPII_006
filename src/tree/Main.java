package tree;

class PrintVisitor <E> implements Visitor <E> {
	@Override
	public void visit(E element) {
		System.out.format(" " + element + " ");
	}
}

public class Main {	

	public static void main(String[] args) {
		Visitor<Integer> visitor = new PrintVisitor<>();

		BinarySearchTree tree = new BinarySearchTree();

		
		//{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        //int arvore2[] = { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        //int arvore3[] = { 1, 15, 2, 14, 3, 13, 4, 12, 5, 11, 6, 10, 7, 9, 8 };
        //int arvore4[] = { 15, 1, 14, 2, 13, 3, 12, 4, 11, 5, 10, 6, 9, 7, 8 };
        //int arvore5[] = { 1, 15, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
       // int arvore6[] = { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15 }
		
		int elements [] ={8,4,12,2,6,10,14,1,3,5,7,9,11,13,15};
		tree = new BinarySearchTree();
		for (int x : elements) {
			tree.add(x);
		}
			
		System.out.println(tree.height()); //TODO print
		tree.preOrder(visitor);
		tree.inOrder(visitor);
	}
}
