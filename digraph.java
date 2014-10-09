import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Digraph<E>{

	protected int size = 0;
	protected int capacity = 10;
	protected ArrayList<E> nodeGraph = new ArrayList<E>(capacity);
	protected ArrayList<E> computerList;
	computerList = new ArrayList<E>(SIZE);

	public void add (<E> node){
		if (size == capacity){
			capacity *= 2;
			ArrayList<E> temp = new ArrayList<E>(capacity);
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

		E first = new E(comp1, time);
		E second = new E(comp2, time);

		add(first);
		add(second);

		E firstClone = first.clone();
		E secondClone = second.clone();

		//I want this to add like it's a linked list
		first.add(secondClone);
		second.add(firstClone);

		if (computerList[comp1] == null){
			computerList[comp1] = firstClone;
		}
		else{
			E tempOld = computerList[comp1];
			E tempNew = firstClone;
			computerList[comp1] = tempNew;
			tempOld = tempOld.getLocation();
			tempOld.add(firstClone);
		}

		if (computerList[comp2] == null){
			computerList[comp2] = secondClone;
		}
		else{
			E tempOld = computerList[comp2];
			E tempNew = secondClone;
			computerList[comp2] = tempNew;
			tempOld = tempOld.getLocation();
			tempOld.add(secondClone);
		}
	}


	public boolean checkVirus(int compStart, int compEnd, int timeStart, int timeEnd){
		E start = findStart(compStart, timeStart);
		return BFS(start, compEnd, timeEnd);
	}

	public E findStart(int comp, int time){
		E start;

		for (int i = 0; i < size; i++){
			E temp = nodeGraph[i];
			if (temp.getComputer() == comp){
				if (temp.getTime() >= time){
					start = nodeGraph[i];
					return start;
				}
			}
		}
	}


	public boolean BFS(E start, int compEnd, int timeEnd){
		start.makeDiscovered();

		//How do I make this queue sizeless?
		Queue<E> BFSQueue = new LinkedList();

		BFSQueue.add(start);
		while (!BFSQueue.isEmpty()){
			E temp = BFSQueue.remove();
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
					BFSQueue.add(temp)

				}
			}
		}
		return false;
	}
};

