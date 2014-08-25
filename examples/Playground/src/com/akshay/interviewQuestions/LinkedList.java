package com.akshay.interviewQuestions;

public class LinkedList {

	private String val;
	private LinkedList next;

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public LinkedList getNext() {
		return next;
	}

	public void setNext(LinkedList next) {
		this.next = next;
	}

	public LinkedList(String val, LinkedList next) {
		super();
		this.val = val;
		this.next = next;
	}

	public static LinkedList constructLinkedList(LinkedList l, String[] vals) {
		for (int i = 0 ; i < vals.length ; i++) {
			if (null == l) {
				l = new LinkedList(vals[i],null);
			}
			else {
				addAtEnd(l,vals[i]);
			}
		}
		return l;
	}

	private static void addAtEnd(LinkedList l, String val) {
		while (null != l.next) {
			l = l.next;
		}
		l.next = new LinkedList(val,null);
	}

	public static void printList(LinkedList l) {
		while (null != l) {
			System.out.print(l.val + " -> ");
			l = l.next;
		}
		System.out.println("null");
	}

	public static void printReverseList(LinkedList l) {
		if (null == l) {
			return;
		}
		printReverseList(l.next);
		System.out.print(l.val + " ");
	}

	/**
	 * This method uses recursive method to reverse a singly linked list.
	 */
	public static LinkedList recursiveReverse(LinkedList linkedList) {

		// check for empty or size 1 linked list. This is a base condition to
		// terminate recursion.
		if (linkedList == null || linkedList.next == null) {
			return linkedList;
		}

		LinkedList remainingReverse = recursiveReverse(linkedList.next);

		// update the tail as beginning
		LinkedList current = remainingReverse;
		while (current.next != null) {
			current = current.next;

		}
		// assign the head as a tail
		current.next = linkedList;
		linkedList.next = null;

		return remainingReverse;
	}

	/**
	 * This method uses iterative approach to reverse a singly linked list.
	 */
	public static LinkedList iterativeReverse(LinkedList linkedList) {

		if (linkedList == null || linkedList.next == null) {
			return linkedList;
		}

		LinkedList prevNode, currNode, nextNode;
		prevNode = null;
		nextNode = null;
		currNode = linkedList;

		while (currNode != null) {
			nextNode = currNode.next;
			currNode.next = prevNode;
			prevNode = currNode;
			currNode = nextNode;
		}

		return prevNode;
	}

	public static void main(String[] args) {
		String[] vals = {"Akshay", "Nikhil", "Jaspal", "Sikkim", "Prabhjot", "Makhi", "Butcha"};
		LinkedList l = null;
		l = constructLinkedList(l,vals);
		printList(l);
		printReverseList(l);
		System.out.println();

		l = recursiveReverse(l);
		printList(l);
		
		l = iterativeReverse(l);
		printList(l);
	}

}
