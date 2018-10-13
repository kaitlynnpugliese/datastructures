import java.util.*;

public class KBestCounter<T extends Comparable<? super T>> {
	
	PriorityQueue heap;
	int s;
	
	public KBestCounter(int k){
		heap = new PriorityQueue < Integer > ();
		s = k;
	}
	
	//inserts element into heap
	public void count(T x){
		//if the heap has less than k elements, then add
		if (heap.size() < s){
			heap.add(x);
		}
		//else, compare new 
		//element with min
		//if it is greater than the min, replace
		else if (x.compareTo((T) heap.peek()) > 0){
			heap.remove();
			heap.add(x);
		}
	}
	
	//returns k largest elements 
	public List kbest(){
		List largest = new ArrayList();
		int heapLength = heap.size();
		//add the elements from the heap to the list to print
		for (int i = 0; i < heapLength; i++){
			largest.add(heap.poll());
		}
		
		//poll removes the elements from the heap,
		//so we need to add them back into the heap
		for (int x = 0; x < largest.size(); x++){
			heap.add(largest.get(x));
		}
		
		return largest;
		
	}

}
