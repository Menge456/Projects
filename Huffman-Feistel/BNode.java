public class BNode {
	char c;
    int data;
    BNode left, right;
  
    BNode(int d) {
        data = d;
        left = right = null;
    }
    
    BNode(int d, char c) {
        data = d; 
        this.c = c;
        left = null;
        right = null;
    }
}
  
