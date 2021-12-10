public class Node<E>{
    E data; 
    Node next;
    Node(E d){
       data = d;
       next = null;
}
    public Node getNext() {
         return next;
    }

   public void setNext(Node nextValue) {
         next = nextValue;
    }
  
    public  void setValue(E value){
        data  = value;
    }
  
  
}