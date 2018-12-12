/**
 * 
 */
package com.molecular.connection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RAJMDALI
 *
 */
public class TupleTreeTest {
	public static void main(String[] args) {

		List<String> input = new ArrayList<>();

		input.add("Elemental#Nitroge");
		input.add("Entry#Substance");
		input.add("Substance#Elemental");
		input.add("Substance#Compound");
		input.add("Compound#Phosphate");
		input.add("Compound#Pigments");
		input.add("Substance#Unclassified");
		input.add("Pigments#Chlorophyll");

		TupleTree tupleTree = new TupleTree();

		tupleTree.convertIntoTreeNodes(input);

		List<String> pathList =  tupleTree.getPath();
		System.out.println("Root: " + tupleTree.headNode.getValue());
		//System.out.println("Longest Path: " + tupleTree.longestPath);
		for(String nodeName : tupleTree.longestPath){
			System.out.print("->"+nodeName);
		}
//		System.out.println("Shorted Path: " + pathList.get(1));


	}
}
