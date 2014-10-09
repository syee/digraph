import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Digraph{

	protected int size = 0;
	// protected int capacity = 10;
	protected CompNode[] nodeGraph;
	protected CompNode[] computerList;

	public Digraph(int n, int m){

		nodeGraph = new CompNode[2*m];
		computerList = new CompNode[n+1];
		// capacity = 2 * m;

	}

	public void add (CompNode node){
		// if (size == capacity){
		// 	capacity *= 2;
		// 	CompNode[] temp = new CompNode[capacity];
		// 	for (int i = 0; i < size; i++){
		// 		temp[i] = nodeGraph[i];
		// 	}
		// 	nodeGraph = temp;
		// }
		System.out.println("size is " + size);



		nodeGraph[size] = node;
		node.setLocation(nodeGraph[size]);
		System.out.println("This is the first node at nodeGraph[0] " + nodeGraph[0].getTime() + " I have location " + nodeGraph[0]);
		nodeGraph[0].printChain();

		size++;
	}


	public void processTriple(int comp1, int comp2, int time){

		CompNode first = new CompNode(comp1, time);
		CompNode second = new CompNode(comp2, time);

		add(first);
		add(second);

		// System.out.println("first computer is "+ comp1 + " time " + time);
		// first.printChain();
		// System.out.println("second computer is "+ comp2 + " time " + time);
		// second.printChain();

		CompNode firstClone = first.clone();
		CompNode secondClone = second.clone();

		first.add(secondClone);

		System.out.println("I am now printing for the first node");
		first.printChain();

		second.add(firstClone);

		if (computerList[comp1] == null){
			computerList[comp1] = firstClone;
			// System.out.println("first computer is "+ comp1 + " time " + time);
			// computerList[comp1].printChain();
		}
		else{
			CompNode tempOld = computerList[comp1];
			CompNode tempNew = firstClone.clone();
			computerList[comp1] = tempNew;

			// System.out.println("computer list is "+ comp1 + " time " + time);
			// computerList[comp1].printChain();

			tempOld = tempOld.getLocation();

			System.out.println("Temp Old has location " + tempOld.getLocation());

			tempOld.add(firstClone);

			// System.out.println("old instance of computer "+ comp1 + " time " + tempOld.getTime());
			// tempOld.printChain();



			// System.out.println("original computer value for computer 1 is");
			// nodeGraph[0].printChain();
			// System.out.println("ENDINGINGINGINGING original computer value for computer 1 is");
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
		start.printInfo();

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

