import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Digraph{

	protected int size = 0;
	protected int capacity = 10;
	protected CompNode[] nodeGraph;
	protected CompNode[] computerList;

	public Digraph(int n, int m){

		nodeGraph = new CompNode[2*m];
		computerList = new CompNode[n];

	}

	public void add (CompNode node){
		if (size == capacity){
			capacity *= 2;
			CompNode[] temp = new CompNode[capacity];
			for (int i = 0; i < size; i++){
				temp[i] = nodeGraph[i];
			}
			nodeGraph = temp;
		}
		nodeGraph[size] = node;
		node.setLocation(nodeGraph[size]);
		size++;
	}


	public void processTriple(int comp1, int comp2, int time){

		CompNode first = new CompNode(comp1, time);
		CompNode second = new CompNode(comp2, time);

		add(first);
		add(second);

		CompNode firstClone = first.clone();
		CompNode secondClone = second.clone();

		//I want this to add like it's a linked list
		first.add(secondClone);
		second.add(firstClone);

		if (computerList[comp1] == null){
			computerList[comp1] = firstClone;
		}
		else{
			CompNode tempOld = computerList[comp1];
			CompNode tempNew = firstClone;
			computerList[comp1] = tempNew;
			tempOld = tempOld.getLocation();
			tempOld.add(firstClone);
		}

		if (computerList[comp2] == null){
			computerList[comp2] = secondClone;
		}
		else{
			CompNode tempOld = computerList[comp2];
			CompNode tempNew = secondClone;
			computerList[comp2] = tempNew;
			tempOld = tempOld.getLocation();
			tempOld.add(secondClone);
		}
	}


	public boolean checkVirus(int compStart, int compEnd, int timeStart, int timeEnd){
		CompNode start = findStart(compStart, timeStart);
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
		start.makeDiscovered();

		//How do I make this queue sizeless?
		Queue<CompNode> BFSQueue = new LinkedList();

		BFSQueue.add(start);
		while (!BFSQueue.isEmpty()){
			CompNode temp = BFSQueue.remove();
			while (temp.getNext() != null){
				temp = temp.getNext().getLocation();
				if (!temp.checkDiscovered()){
					if (temp.getComputer() == compEnd){
						if (temp.getTime() <= timeEnd){
							return true;
						}
						else{
							return false;
						}
					}
					temp.makeDiscovered();
					BFSQueue.add(temp);

				}
			}
		}
		return false;
	}
};

