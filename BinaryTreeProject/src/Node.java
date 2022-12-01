
public class Node {
	int iData; 
	double dData; 
	Node leftChild; 
	Node rightChild; 
	
	Node(int id,double dd){
		iData = id; 
		dData = dd;
	}
	
	public void displayNode() {
		System.out.println("{"); 
		System.out.println(iData);
		System.out.println(",  "); 
		System.out.println(dData);
		System.out.println("}  "); 
	}
	
	
}
