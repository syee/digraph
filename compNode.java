/**
 * File: CompNode.java
 * 
 * This is my definition of the class CompNode. It has fields to identify the computer it represents and the capability to be in a linked list. 
 *@author Steven Yee
 *@version 1.0 10/10/14
 */


public class CompNode{

	protected int computer;
	protected int time;
	protected boolean discovered = false;
	protected int location;		
	protected CompNode next = null;
	protected CompNode last = null;

	/*
	 * This is the constructor for the class CompNode.
	 *@param c where c is the computer represented by the node.
	 *@param t where t is the time of the representation.
	 */
	public CompNode(int c, int t){
		computer = c;
		time = t;
		next = null;
	}

	/*
	 * This is the constructor for the class CompNode.
	 *@param c where c is the computer represented by the node.
	 *@param t where t is the time of the representation.
	 *@param l where l is the location of the node in the digraph.
	 */
	public CompNode(int c, int t, int l){
		computer = c;
		time = t;
		location = l;
		next = null;
		last = null;
	}

	/*
	 * This is the constructor for the class CompNode.
	 *@param c where c is the computer represented by the node.
	 *@param t where t is the time of the representation.
	 *@param l where l is the location of the node in the digraph.
	 *@param n where n is the node this node points to.
	 *@param end where end is the node at the end of the linked list for this node.
	 */
	public CompNode(int c, int t, int l, CompNode n, CompNode end){
		computer = c;
		time = t;
		location = l;
		next = n;
		last = end;
	}

	/*
	 *This method clones this node so its information can be used elsewhere.
	 *@return copy where copy is a clone of this node.
	 */
	public CompNode clone(){
		CompNode copy = new CompNode(computer, time, location, null, null);
		return copy;
	}

	/*
	 * This method adds a node to the linked list of this node.
	 *@param node where node is the node to be added.
	 */
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

	/*
	 * This method prints out the information of the node.
	 */
	public void printInfo(){
		System.out.println("computer is " + computer);
		System.out.println("time is " + time);
		System.out.println("status is " + discovered);
		System.out.println("location is " + location);
	}

	/*
	 * This method prints out the information of the node and the node's linked list.
	 */
	public void printChain(){
		System.out.println("Start chain computer is " + computer);
		printInfo();
		CompNode temp = next;
		while (temp != null){
			temp.printInfo();
			temp = temp.getNext();
		}
		System.out.println("End chain");
	}

	/*
	 * This method marks a node as discovered 
	 */
	public void makeDiscovered(){ discovered = true; }

	/*
	 * This method returns the computer of this node.
	 *@return computer where computer is the computer of this node.
	 */
	public int getComputer(){ return computer; }

	/*
	 * This method returns the time of this node.
	 *@return time where time is the time of this node.
	 */
	public int getTime() { return time; }

	/*
	 * This method returns the location of the node in the adjacency list.
	 *@return location where location is the location of the node in the adjacency list.
	 */
	public int getLocation(){ return location; }

	/*
	 * This method returns the location of the next node in the linked list.
	 *@return next where next is the location of the next node in the linked list.
	 */
	public CompNode getNext() { return next; }

	/*
	 * This method sets the identity of the next node in the linked list
	 *@param n where n is the node to be set as the next value of this node in the linked list.
	 */
	public void setNext(CompNode n) { next = n; }

	/*
	 * This method sets the identity of the last node in the linked list
	 *@param n where n is the node to be set as the last value of this node in the linked list.
	 */
	public void setLast(CompNode l) { last = l; }

	/*
	 * This method sets the location of the node in the adjacency list.
	 *@param l where l is the index of the node in the adjacency list.
	 */
	public void setLocation(int l) { location = l; }

	/*
	 * This method returns whether or not a node is discovered.
	 *@param discovered where discovered is whether or not a node has been visited in the BFS yet.
	 */
	public boolean checkDiscovered() { return discovered; }

}