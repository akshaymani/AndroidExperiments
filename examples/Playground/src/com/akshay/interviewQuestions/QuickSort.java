package com.akshay.interviewQuestions;

import java.util.Random;

// Video Link - https://www.youtube.com/watch?v=aQiWF4E8flQ

public class QuickSort {

	public static void main(String[] args) throws Exception {
		int arraySize = new Integer(10);
		int array[] = new int[arraySize];
		generateRandomArray(array,arraySize);
		printArray(array,arraySize);
		quickSort(array,0,arraySize-1);
		printArray(array,arraySize);
	}
	
	private static void quickSort(int[] array, int left, int right) {
		if (left >= right) return;
		int pivot = partition(array,left,right);
		quickSort(array, left, pivot-1);
		quickSort(array, pivot+1, right);		
	}

	private static int partition(int[] array, int left, int right) {
		int pivotIndex = selectPivotIndex(array,left,right);
		int pivotVal = array[pivotIndex];
		swapElements(array, pivotIndex, right);
		int storeIndex = left;
		for (int i = left; i <= right-1; i++) {
			if (array[i] < pivotVal) {
				swapElements(array, i, storeIndex);
				storeIndex++;
			}
		}
		swapElements(array, storeIndex, right);
		return storeIndex;
	}
	
	private static void swapElements(int[] array,int indexOne,int indexTwo) {
		int temp = array[indexOne];
		array[indexOne] = array[indexTwo];
		array[indexTwo] = temp;
	}

	private static void printArray(int[] array, int arraySize) {
		for (int i = 0 ; i < arraySize; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();		
	}

	private static void generateRandomArray(int[] array, int arraySize) {
		Random r = new Random();
		for (int i = 0; i < arraySize; i++) {
			array[i] =  r.nextInt(32768);
		}
	}

	private static int selectPivotIndex(int[] array, int left, int right) {
		int a = array[left];
		int b = array[(left+right)/2];
		int c = array[right];
		int min = minimum(minimum(a,b),c);
		int max = maximum(maximum(a,b),c);
		int pivotVal = min^max^a^b^c;
		if(pivotVal == a) return left;
		else if (pivotVal == b) return (left+right)/2;
		else return right;
	}
	
	private static int maximum(int a, int b) {
		// TODO Auto-generated method stub
		return (a < b) ? a : b;
	}

	private static int minimum(int a, int b) {
		// TODO Auto-generated method stub
		return (a > b) ? a : b;
	}
	
}
