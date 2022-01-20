public class Stack<V> {
	
	LinkdList stack;
	
	public Stack(){
		stack = new LinkdList();
		
	}
	
	public void push(V j) {
		stack.addToFront(j);
	}// add to front
	
	public V pop() {
		return (V) stack.removeFront();
	}//remove from front
	
	public V get(int index) {
		return (V) stack.get(index);
	}
	
	public boolean isEmpty() {
		return stack.size() == 0;
	}
	
	public int size() {
		return stack.size();
	}


} 
