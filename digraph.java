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



	}




};

public class Computers <E> {

	protected ArrayList<E> computerList;

	public Computers(int n){

		computerList = new ArrayList<E>(n);

	}

}

