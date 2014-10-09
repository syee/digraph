public class CompNode{

	protected int computer;
	protected int time;
	protected boolean discovered = false;
	protected CompNode location;		
	protected CompNode next = null;
	protected CompNode last = null;

	// public CompNode(){
	// 	this(null, null, null, null);
	// }

	public CompNode(int c, int t){
		computer = c;
		time = t;
		location = null;
		next = null;
	}

	public CompNode(int c, int t, CompNode l){
		computer = c;
		time = t;
		location = l;
		next = null;
	}

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

	public CompNode clone(){
		CompNode copy = new CompNode(computer, time, location);
		return copy;
	}

	public void add(CompNode node){
		if (last == null){
			last = node;
			setNext(last);
		}
		else{
			last.setNext(node);
			last = node;
		}
	}

	public void printInfo(){

		System.out.println("computer is " + computer);
		System.out.println("time is " + time);
		System.out.println("status is " + discovered);

	}

	public void printChain(){
		System.out.println("Start chain computer is " + computer);
		printInfo();
		while (next != null){
			CompNode temp = next;
			next.printInfo();
			next = next.getNext();
		}
		System.out.println("End chain");

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
	public CompNode getLocation(){ return location; }

	/** Returns the next node of this node */
	public CompNode getNext() { return next; }

	/** Sets the next node of this node */
	public void setNext(CompNode n) { next = n; }

	/** Sets the next node of this node */
	public void setLast(CompNode l) { last = l; }

	/** Sets the location of the node in the adjacency list */
	public void setLocation(CompNode l) { location = l; }

	public boolean checkDiscovered() { return discovered; }

}