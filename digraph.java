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
		computerList = new CompNode[n+1];
		capacity = 2 * m;

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

		// first.printInfo();
		// second.printInfo();

		//I want this to add like it's a linked list
		first.add(secondClone);
		second.add(firstClone);
		System.out.println("this is the first thing for computer 1. It hsould have computer 2");
		first.printChain();

		if (computerList[comp1] == null){
			computerList[comp1] = firstClone;
		}
		else{
			CompNode tempOld = computerList[comp1];
			CompNode tempNew = firstClone.clone();
			computerList[comp1] = tempNew;
			tempOld = tempOld.getLocation();
			tempOld.add(firstClone);
			System.out.println("original computer value for computer 1 is");
			nodeGraph[1].printChain();
			System.out.println("ENDINGINGINGINGING original computer value for computer 1 is");
		}

		if (computerList[comp2] == null){
			computerList[comp2] = secondClone;
		}
		else{
			CompNode tempOld = computerList[comp2];
			CompNode tempNew = secondClone.clone();
			computerList[comp2] = tempNew;
			tempOld = tempOld.getLocation();
			tempOld.add(secondClone);
		}
	}


	public boolean checkVirus(int compStart, int compEnd, int timeStart, int timeEnd){
		CompNode start = findStart(compStart, timeStart);

		System.out.println("start's next is ");
		start.getNext().printInfo();

		return BFS(start, compEnd, timeEnd);
	}

	public CompNode findStart(int comp, int time){
		CompNode start;

		for (int i = 0; i < size; i++){
			CompNode temp = nodeGraph[i];
			if (temp.getComputer() == comp){
				if (temp.getTime() >= time){
					start = nodeGraph[i];
					System.out.println("start node found is " + start.getComputer() + " " + start.getTime());
					return start;
				}
			}
		}

		return null;
	}


	public boolean BFS(CompNode start, int compEnd, int timeEnd){
		System.out.println("end comp is " + compEnd + " time is " + timeEnd);
		start = start.getLocation();
		start.makeDiscovered();
		start.printInfo();

		//How do I make this queue sizeless?
		Queue<CompNode> BFSQueue = new LinkedList();

		BFSQueue.add(start);
		while (!BFSQueue.isEmpty()){
			CompNode temp = BFSQueue.remove();

			System.out.println("outside queues is ");
			temp.printInfo();
			temp.printChain();

			while (temp.getNext() != null){
				
				temp = temp.getNext();
				// System.out.println("inside queues is ");
				// temp.printInfo();
				if (!temp.getLocation().checkDiscovered()){
					if (temp.getComputer() == compEnd){
						if (temp.getTime() <= timeEnd){
							System.out.println("computer is " + temp.getComputer() + " time is " + temp.getTime());
							return true;
						}
					}
					temp.getLocation().makeDiscovered();
					BFSQueue.add(temp);

				}
			}
			// temp.printInfo();
		}
		return false;
	}
};

