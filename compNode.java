public class compNode implements Comparable{

	protected int computer;
	protected int time;
	protected boolean discovered = false;
	protected compNode location;		
	protected compNode next;
	protected compNode last = null;

	public compNode(){
		this(null, null, null, null);
	}

	public compNode(int c, int t){
		computer = c;
		time = t;
		location = null;
		next = null;
	}

	// public compNode(int c, int t, compNode l){
	// 	computer = c;
	// 	time = t;
	// 	location = l;
	// 	next = null;
	// }

	// public compNode(int c, int t, compNode l, compNode n){
	// 	computer = c;
	// 	time = t;
	// 	location = l;
	// 	next = n;
	// }

	// public boolean compareTo(compNode<E> other){
	// 	boolean sameComp = (computer == other.getComputer());
	// 	boolean sameTime = (time == other.getTime());
	// 	return (sameCompt && sameTime);
	// }

	public compNode clone(){
		compNode copy = new compNode(computer, time, location);
		return copy;
	}

	public void add(compNode node){
		if (last == null){
			last = node;
		}
		else{
			last.setNext(node);
			last = node;
		}
	}

	/** Mark a node as discovered */
	public void makeDiscovered(){ discovered = true; }

	/** Mark a node as not discovered */
	public void makeHidden(){ discovered = false; }

	/** Returns the computer of this node */
	public int getComputer(){ return computer; }

	/** Returns the time of this node */
	public int getTime() { return time; }

	/** Returns the location of the node in the adjacency list */
	public compNode getLocation(){ return location; }

	/** Returns the next node of this node */
	public compNode getNext() { return next; }

	/** Sets the next node of this node */
	public void setNext(compNode n) { next = n; }

	/** Sets the next node of this node */
	public void setLast(compNode l) { last = l; }

	/** Sets the location of the node in the adjacency list */
	public void setLocation(compNode l) { location = l; }

	public boolean checkDiscovered() { return discovered; }

}