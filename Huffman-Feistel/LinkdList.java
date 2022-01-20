
public class LinkdList <E> {
	Node head;
	int size = 0;
	Node last;
	public LinkdList() {
		head = null;
		last = null;
	}
	
	public void add(E data) {
		//System.out.println(data + " stuff");
		size++;
		Node nnode = new Node(data);
		
		if(head == null) {
			head = nnode;
			last = nnode;
		}else {
			if(last == null)
				last = new Node(this.get(size));
			last.setNext(nnode);
			last = last.getNext();
		}
	}
	public E get(int index) {
		Node f = head;
		if(index == 0)
			return (E) head.data;
		else {
			for(int i = 0; i < index; i++) {
				if(f.next != null) 
					f = f.getNext();
				else 
					return null;
				
			}
			return (E) f.data;
		}
			
	}
	public int size() {
		return size;
	}
	public void addToFront(E f) {
		size++;
		Node e = new Node(f);
		e.setNext(head);
		head = e;
		
	}
	public int contains(E e) {
		Node a = head;
		for(int i = 0; i < size; i++) {
			if(a.data == e)
				return i;
			else
				a = a.getNext();
		}
		return -1;
		
	}
	public E remove(int index) {
		
		size--;
		Node e =head;
		for(int i = 0; i < index-1;i++) {
			e = e.getNext();
		}
		
		Node rem = e.getNext();
		System.out.println(rem.data);
		System.out.println(e.data);
		if(rem != null)
			e.setNext(rem.getNext());
		
		
		return (E) rem.data;
	}
	
	public void set(int index, E e) {
		Node f = head;
		for(int i = 0; i < index ;i++) {
			f = f.getNext();
		}
		f.setValue(e);
		
	}
	public E removeFront() {
		size--;
		E ret = (E) head.data;
		head = head.getNext();
		return ret;
	}
	public void add(int index, E e) {
		size++;
		Node f =head;
		for(int i = 0; i < index-1;i++) {
			f = f.getNext();
		}
		
		Node nex = f.getNext();
		Node add = new Node(e);
		f.setNext(add);
		add.setNext(nex);
	}
}
