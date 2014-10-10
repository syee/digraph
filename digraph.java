

import java.util.LinkedList;
import java.util.Queue;

public class Digraph{

	protected int size = 0;
	protected CompNode[] nodeGraph;
	protected CompNode[] computerList;

	public Digraph(int n, int m){
		nodeGraph = new CompNode[2*m];
		computerList = new CompNode[n+1];

		for (int i = 0; i < n+1; i++){
			computerList[i] = null;
		}
	}

	public void add (CompNode node){
		nodeGraph[size] = node;
		node.setLocation(size);
		size++;
	}

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

	public void printMine(int i){
		System.out.println("                                         Printing info for computer " + i);
		nodeGraph[i].printChain();
	}

	public void printAll(){
		System.out.println("Printing everything");
		for (int i = 0; i < size; i++){
			printMine(i);
		}
	}

	public boolean checkVirus(int compStart, int compEnd, int timeStart, int timeEnd){
		CompNode start = findStart(compStart, timeStart);
		if (compStart == compEnd){
			return true;
		}
		return BFS(start, compEnd, timeEnd);
	}

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


	public boolean BFS(CompNode start, int compEnd, int timeEnd){
		start = nodeGraph[start.getLocation()];
		start.makeDiscovered();

		if (start.getTime() >= timeEnd){
			return true;
		}


		Queue<CompNode> BFSQueue = new LinkedList();

		BFSQueue.add(start);
		while (!BFSQueue.isEmpty()){
			CompNode temp = BFSQueue.remove();
			while (temp.getNext() != null){
				temp = temp.getNext();	
				if (!nodeGraph[temp.getLocation()].checkDiscovered()){
					if (temp.getComputer() == compEnd){
						if (temp.getTime() <= timeEnd){
							return true;
						}
					}
					nodeGraph[temp.getLocation()].makeDiscovered();
					BFSQueue.add(nodeGraph[temp.getLocation()]);

				}
				
			}
		}
		return false;
	}
};

