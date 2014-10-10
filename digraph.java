/**
 * File: Digraph.java
 * 
 * This is my implementation of a digraph representing a computer network. It uses an array of linked lists as the underlying implementation. 
 *@author Steven Yee
 *@version 1.0 10/10/14
 */

import java.util.LinkedList;
import java.util.Queue;

public class Digraph{

	protected int size = 0;
	protected CompNode[] nodeGraph;
	protected CompNode[] computerList;


	/*
	 * This is the constructor for the class Digraph.
	 *@param n where n is the number of computers in the system.
	 *@param m where m is the number of triples of information.
	 */
	public Digraph(int n, int m){
		nodeGraph = new CompNode[2*m];
		computerList = new CompNode[n+1];

		for (int i = 0; i < n+1; i++){
			computerList[i] = null;
		}
	}

	/*
	 * This method inserts an element at the least element in the array that is empty.
	 *@param node where node is the element to be inserted into the array
	 */
	public void add (CompNode node){
		nodeGraph[size] = node;
		node.setLocation(size);
		size++;
	}

	/*
	 * This method takes a triple of data and adds that information to the digraph.
	 *@param comp1 where comp1 is the first computer in the triple.
	 *@param comp2 where comp2 is the second computer in the triple.
	 *@param time where time is when the two computers interacted.
	 */
	public void processTriple(int comp1, int comp2, int time){

		CompNode first = new CompNode(comp1, time);
		CompNode second = new CompNode(comp2, time);
		CompNode firstClone;
		CompNode secondClone;

		if ( (computerList[comp1] == null) || (computerList[comp1].getTime() != time) ){
			add(first);
		}
		else{
			first = nodeGraph[computerList[comp1].getLocation()];
		}

		if ( (computerList[comp2] == null) || (computerList[comp2].getTime() != time)){
			add(second);
		}
		else{
			second = nodeGraph[computerList[comp2].getLocation()];
		}

		firstClone = first.clone();
		secondClone = second.clone();

		first.add(secondClone);
		second.add(firstClone);

		if (computerList[comp1] == null){
			computerList[comp1] = firstClone.clone();
		}
		else{
			CompNode temp = nodeGraph[computerList[comp1].getLocation()];
			computerList[comp1] = firstClone.clone();
			temp.add(firstClone.clone());
		}

		if (computerList[comp2] == null){
			computerList[comp2] = secondClone.clone();
		}
		else{
			CompNode temp = nodeGraph[computerList[comp2].getLocation()];
			computerList[comp2] = secondClone.clone();
			temp.add(secondClone.clone());
		}
	}

	/*
	 * This method prints out the information contained by a node as well as the edges between this node and other nodes.
	 *@param i where i is the index in nodeGraph where the node is contained.
	 */
	public void printMine(int i){
		System.out.println("                                         Printing info for computer " + i);
		nodeGraph[i].printChain();
	}
	/*
	 * This method prints all the information for all the nodes, including the edges of the node.
	 */
	public void printAll(){
		System.out.println("Printing everything");
		for (int i = 0; i < size; i++){
			printMine(i);
		}
	}

	/*
	 * This method calls a function that prints out a statement regarding whether or not a computer is infected by a certain time.
	 *@param compStart where compStart is the computer initially infected.
	 *@param compEnd where compEnd is the computer in question.
	 *@param timeStart where timeStart is when the first computer is initially infected.
	 *@param timeEnd where timeEnd is the deadline for the second computer to be infected.
	 */
	public void checkVirus(int compStart, int compEnd, int timeStart, int timeEnd){
		CompNode start = findStart(compStart, timeStart);
		if (compStart == compEnd){
			System.out.println("Computer " + compStart + " will be infected by time " + timeEnd);
		}
		else{
			BFS(start, compEnd, timeEnd);
		}
	}

	/*
	 * This method finds the first instance where the first computer that is infected interacts with another computer.
	 *@param comp where comp is the computer that is infected.
	 *@param time where time is when the computer was first infected.
	 *@return the node indicating such an instance or null if no such node is found.
	 */
	public CompNode findStart(int comp, int time){
		CompNode start;
		for (int i = 0; i < size; i++){
			CompNode temp = nodeGraph[i];
			if (temp.getComputer() == comp){
				if (temp.getTime() >= time){
					start = nodeGraph[i];
					return start;
				}
			}
		}
		return null;
	}

	/*
	 * This method determines whether or not a computer could be infected by a specific time given the origin of the virus.
	 *@param start where start is the first instance of the infected computer interacting with another computer.
	 *@param compEnd where compEnd is the computer that could be infected later.
	 *@param timeEnd where timeEnd is the deadline for the computer to be infected.
	 */
	public void BFS(CompNode start, int compEnd, int timeEnd){
		if (start == null){
			System.out.println("Computer " + compEnd + " will not infected by time " + timeEnd);
		}
		else{
			start = nodeGraph[start.getLocation()];
			start.makeDiscovered();
			boolean foundFlag = false;

			if (start.getTime() > timeEnd){
				System.out.println("Computer " + compEnd + " will not be infected by time " + timeEnd);
			}
			else{

				Queue<CompNode> BFSQueue = new LinkedList();

				BFSQueue.add(start);
				while ((!BFSQueue.isEmpty()) && (!foundFlag)){
					CompNode temp = BFSQueue.remove();
					while ((temp.getNext() != null) && (!foundFlag)){
						temp = temp.getNext();	
						if (!nodeGraph[temp.getLocation()].checkDiscovered()){
							if (temp.getComputer() == compEnd){
								if (temp.getTime() <= timeEnd){
									System.out.println("Computer " + compEnd + " will be infected by time " + temp.getTime());
									foundFlag = true;
								}
							}
							nodeGraph[temp.getLocation()].makeDiscovered();
							BFSQueue.add(nodeGraph[temp.getLocation()]);

						}
						
					}
				}
				if (!foundFlag){
					System.out.println("Computer " + compEnd + " will not infected by time " + timeEnd);
				}
			}
		}
	}
};

