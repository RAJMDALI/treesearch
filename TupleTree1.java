/**
 * 
 */
package com.molecular.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author RAJMDALI
 *
 */
public class TupleTree {
	Node headNode;
	Map<String, Integer> nodesLevel;
	List<String> longestPath;
	String sortestPath;
	int nodeLevel = 2;
	
	public List<String> getPath(){
		List<String> pathList = new ArrayList<>();
		longestPath = new ArrayList<>();
		nodesLevel = new HashMap<>();
		longestPath.add(headNode.getValue());
		Set<Node> childrenNodeList = headNode.getChildren();
		if(childrenNodeList.size()>0){
			traverseNextNode(1, childrenNodeList);
		}		
		return pathList;
	}
	
	public void traverseNextNode(int currentLevel, Set<Node> nodeList){		
		Iterator<Node> setIterator = nodeList.iterator();
		while(setIterator.hasNext()){
			Node node = setIterator.next();
			String nodeName = node.getValue();			
			Set<Node> childrenNodeList = node.getChildren();			
			longestPath.add(nodeName);
			//nodesLevel.put(nodeName, value);
			if(childrenNodeList.size()>0){	
				currentLevel +=1;
				traverseNextNode(currentLevel, childrenNodeList);
			}else{
				if(currentLevel!=nodeLevel){
					longestPath.remove(nodeName);
				}
				
				nodeLevel -=1;
			}
			nodeLevel +=1;
			currentLevel = 1;				
        }
	}
	
	public void convertIntoTreeNodes(List<String> tupleList) {
		Map<String, Node> refMap = new HashMap<>();

		for (String input : tupleList) {
			String[] inputs = input.split("#");

			String parent = inputs[0];
			String child = inputs[1];

			if (refMap.containsKey(parent)) {
				if (refMap.containsKey(child)) {
					refMap.get(parent).getChildren().add(refMap.get(child));
				} else {
					Node childNode = new Node(child, new HashSet<>());
					refMap.put(child, childNode);
					refMap.get(parent).getChildren().add(childNode);
				}

			} else {
				Set<Node> children = new HashSet<>();
				Node childNode = new Node(child, new HashSet<>());
				children.add(childNode);
				refMap.put(child, childNode);

				Node parentNode = new Node(parent, children);
				refMap.put(parent, parentNode);

				headNode = parentNode;

			}
		}

	}

	@Override
	public String toString() {
		return "TupleTree [headNode=" + headNode + "]";
	}

}

class Node implements Comparable<Node> {
	private String value;
	private Set<Node> children;

	Node(String value, Set<Node> children) {
		this.value = value;
		this.children = children;
	}

	public String getValue() {
		return value;
	}

	public Set<Node> getChildren() {
		return children;
	}

	@Override
	public int compareTo(Node o) {
		return this.value.compareTo(o.value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", children=" + children + "]";
	}
}
