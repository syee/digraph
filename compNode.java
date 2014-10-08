public class compNode<E>{

	protected int computer;
	protected int time;
	protected compNode<E> location;		
	protected compNode<E> next;

	public compNode(){
		this(null, null, null, null);
	}

	public compNode(int c, int t){
		computer = c;
		time = t;
		location = null;
		next = null;
	}

	public compNode(int c, int t, compNode<E> l){
		computer = c;
		time = t;
		location = l;
		next = null;
	}

	public compNode(int c, int t, compNode<E> l, compNode<E> n){
		computer = c;
		time = t;
		location = l;
		next = n;
	}

	/** Returns the computer of this node */
	public int getComputer(){ return computer; }

	/** Returns the time of this node */
	public int getTime() { return time; }

	/** Returns the location of the node in the adjacency list */
	public compNode<E> getLocation(){ return location; }

	/** Returns the next node of this node */
	public compNode<E> getNext() { return next; }

	/** Sets the next node of this node */
	public void setNext(compNode<E> n) { next = n; }

	/** Sets the location of the node in the adjacency list */
	public void setLocation(compNode<E> l) { location = l; }

}