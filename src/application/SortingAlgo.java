package application;

import java.util.Arrays;

public class SortingAlgo {

	//BUBBLE SORT
	public static String[] bubbleSort(String[] txt, int[] deadlinetxt) {
		System.out.println("BubbleSorted");
		int n = deadlinetxt.length;
	    boolean swapped;
	    
	    long startTime = System.nanoTime();
	    
	    
	    
	    do {
	        swapped = false;
	        for (int i = 1; i < n; i++) {
	            if (deadlinetxt[i - 1] > deadlinetxt[i]) {
	          
	                int temp = deadlinetxt[i - 1];
	                deadlinetxt[i - 1] = deadlinetxt[i];
	                deadlinetxt[i] = temp;
	                
	                String tempStr = txt[i - 1];
	                txt[i - 1] = txt[i];
	                txt[i] = tempStr;

	                swapped = true;
	            }
	        }
	        n--;
	    } while (swapped);
	    long endTime   = System.nanoTime();
	    long  totalTime = endTime - startTime;
	    System.out.println("Runtime : "+totalTime+" NanoSeconds");
	    System.out.println("Time Complexity : O(n²)");
		return txt;
		
	}
	
	//SELECTION SORT
	public static String[] selectionSort(String[] txt, int[] deadlinetxt) {
		System.out.println("SelectionSort");
		long startTime = System.nanoTime();
		int n = deadlinetxt.length;
	    
	    for (int i = 0; i < n - 1; i++) {
	        int minIndex = i;
	        for (int j = i + 1; j < n; j++) {
	            if (deadlinetxt[j] < deadlinetxt[minIndex]) {
	                minIndex = j;
	            }
	        }

	        int temp = deadlinetxt[minIndex];
	        deadlinetxt[minIndex] = deadlinetxt[i];
	        deadlinetxt[i] = temp;

	        String tempStr = txt[minIndex];
	        txt[minIndex] = txt[i];
	        txt[i] = tempStr;
	    }
	    long endTime   = System.nanoTime();
	    long  totalTime = endTime - startTime;
	    System.out.println("Runtime : "+totalTime+" NanoSeconds.");
	    return txt;
	}
	
	//INSERTION SORT
	public static String[] insertionSort(String[] txt, int[] deadlinetxt) {
		System.out.println("InsertionSort");
		long startTime = System.nanoTime();
		int n = deadlinetxt.length;

	    for (int i = 1; i < n; i++) {
	        int key = deadlinetxt[i];
	        String keyStr = txt[i];
	        int j = i - 1;

	        while (j >= 0 && deadlinetxt[j] > key) {
	            deadlinetxt[j + 1] = deadlinetxt[j];
	            txt[j + 1] = txt[j];
	            j = j - 1;
	        }

	        deadlinetxt[j + 1] = key;
	        txt[j + 1] = keyStr;
	    }
	    long endTime   = System.nanoTime();
	    long  totalTime = endTime - startTime;
	    System.out.println("Runtime : "+totalTime);
	    return txt;
	}

	//MERGESORT
	public static String[] mergeSort(String[] txt, int[] deadlinetxt) {
		
		int n = deadlinetxt.length;

	    if (n > 1) {
	        int mid = n / 2;
	        
	        int[] leftDeadline = Arrays.copyOfRange(deadlinetxt, 0, mid);
	        int[] rightDeadline = Arrays.copyOfRange(deadlinetxt, mid, n);
	        String[] leftTxt = Arrays.copyOfRange(txt, 0, mid);
	        String[] rightTxt = Arrays.copyOfRange(txt, mid, n);

	        mergeSort(leftTxt, leftDeadline);
	        mergeSort(rightTxt, rightDeadline);

	        int i = 0, j = 0, k = 0;
	        while (i < leftDeadline.length && j < rightDeadline.length) {
	            if (leftDeadline[i] <= rightDeadline[j]) {
	                deadlinetxt[k] = leftDeadline[i];
	                txt[k] = leftTxt[i];
	                i++;
	            } else {
	                deadlinetxt[k] = rightDeadline[j];
	                txt[k] = rightTxt[j];
	                j++;
	            }
	            k++;
	        }

	        while (i < leftDeadline.length) {
	            deadlinetxt[k] = leftDeadline[i];
	            txt[k] = leftTxt[i];
	            i++;
	            k++;
	        }

	        while (j < rightDeadline.length) {
	            deadlinetxt[k] = rightDeadline[j];
	            txt[k] = rightTxt[j];
	            j++;
	            k++;
	        }
	    }
	    
	    
	    return txt;
	}
	
	static String[] callMergeSort(String[] txt, int[] deadlinetxt) {
		System.out.println("MergeSortCalled");
		long startTime = System.nanoTime();
		
		txt = mergeSort(txt, deadlinetxt);
		long endTime   = System.nanoTime();
	    long  totalTime = endTime - startTime;
	    System.out.println("Time Complexity: nlogn");
	    System.out.println("Runtime : "+totalTime+" NanoSeconds.");
	    
	    return txt;
	}
	
}