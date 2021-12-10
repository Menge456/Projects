class BTree
{
	    // Root of Binary Tree
	    BNode root;
	  
	    // Constructors
	    BTree(int key)
	    {
	        root = new BNode(key);
	    }
	  
	    BTree()
	    {
	        root = null;
	    }
	
	public BNode insert(BNode node, int data) {
		if(node == null) {
			root = new BNode(data);
			return new BNode(data);
		}
		
		
		if(data < node.data) {
			if(node.left== null) {
				node.left = new BNode(data);
				return new BNode(data);
			}
			else 
				return insert(node.left, data);
		}
		
		if(data > node.data) {
			if(node.right== null) {
				node.right = new BNode(data);
				return new BNode(data);
			}
			else 
				return insert(node.right, data);
		}
		
		
		return null;
	
	}
	public boolean find(BNode a, int value){
		
		if(value != a.data && a.left == null && a.right == null) {
			return false;
		}else {
			if(value == a.data) {
				return true;
			//left side
			}else if(value < a.data) {
				if(a.left == null)
					return false;
				return find(a.left, value);
				
			//right side
			}else if(value > a.data) {
				if(a.right == null) 
					return false;
				
				return find(a.right, value);
			}
		}
		
		
		return false;
		
		
	}
	
	public void showValues(BNode a){
		System.out.println(a.data);
		if(a.left != null) {
			System.out.println("Left");
			showValues(a.left);
		}
		System.out.println();
		if(a.right != null) {
			System.out.println("Right");
			showValues(a.right);
		}
	}
	
}
