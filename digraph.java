import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Digraph<E>{

	int size = 0;
	int capacity = 10;
	protected ArrayList<E> nodeGraph = new ArrayList<E>(capacity);


	public void add (compNode<E> node){

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

		compNode<E> first = new compNode<E>(comp1, time);
		compNode<E> second = new compNode<E>(comp2, time);

		add(first);
		add(second);

		compNode<E> firstClone = first.clone();
		compNode<E> secondClone = second.clone();

		//I want this to add like it's a linked list
		first.add(secondClone);
		second.add(firstClone);

		if (computerList[comp1] == null){
			compNode<E> temp = firstClone;
			computerList[comp1] = temp;
		}
		else{
			compNode<E> tempOld = computerList[comp1];
			compNode<E> tempNew = firstClone;
			computerList[comp1] = tempNew;
			tempOld = tempOld.getLocation();
			tempOld.add(firstClone);
		}

		if (computerList[comp2] == null){
			compNode<E> temp = secondClone;
			computerList[comp2] = temp;
		}
		else{
			compNode<E> tempOld = computerList[comp2];
			compNode<E> tempNew = firstClone;
			computerList[comp2] = tempNew;
			tempOld = tempOld.getLocation();
			tempOld.add(secondClone);
		}

	}


	public boolean checkVirus(int compStart, int compEnd, int timeStart, int timeEnd){

		compNode<E> start = findStart(compStart, timeStart);
		return BFS(start, compEnd, timeEnd);

	}

	public compNode<E> findStart(int comp, int time){

		compNode<E> start;
		for (int i = 0; i < size; i++){

			compNode<E> temp = nodeGraph[i];
			if (temp.getComputer() == comp){
				if (temp.getTime() >= time){
					start = nodeGraph[i];
					return start;
				}
			}

		}

	}


	public boolean BFS(compNode<E> start, int compEnd, int timeEnd){
		start.makeDiscovered();

		//How do I make this queue sizeless?
		Queue<E> BFSQueue = new LinkedList();

		BFSQueue.add(start);
		while (!BFSQueue.isEmpty()){
			compNode<E> temp = BFSQueue.remove();
			while (temp.getNext() != null){
				temp = temp.getNext();
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

public class Computers <E> {

	protected ArrayList<E> computerList;

	public Computers(int n){

		computerList = new ArrayList<E>(n);

	}

}

