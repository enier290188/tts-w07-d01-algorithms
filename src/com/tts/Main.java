package com.tts;

import java.util.Arrays;

public final class Main {
    public static void main(String[] args) {
        int n = 15000;
        int[] array = new int[n];
        initialArray(array);
        printArray(array);

        // Quick Sort.
        int[] arrayQuickSort = Arrays.copyOf(array, n);
        quickSort(arrayQuickSort);
        printArray(arrayQuickSort);

        // Merge Sort.
        int[] arrayMergeSort = Arrays.copyOf(array, n);
        mergeSort(arrayMergeSort);
        printArray(arrayMergeSort);

        // Insertion Sort.
        int[] arrayInsertionSort = Arrays.copyOf(array, n);
        insertionSort(arrayInsertionSort);
        printArray(arrayInsertionSort);

        // Bubble Sort.
        int[] arrayBubbleSort = Arrays.copyOf(array, n);
        bubbleSort(arrayBubbleSort);
        printArray(arrayBubbleSort);
    }

    public static void initialArray(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            do {
                boolean keepTrying = false;
                int randomNumber = (int) (Math.random() * n);
                for (int j = 0; j < i; j++) {
                    if (array[j] == randomNumber) {
                        keepTrying = true;
                        break;
                    }
                }
                if (!keepTrying) {
                    array[i] = randomNumber;
                    break;
                }
            } while (true);
        }
    }

    public static void printArray(int[] array) {
        int n = array.length;
        System.out.print("[");
        for (int i = 0; i < n; i++) {
            System.out.print(array[i] + ((i + 1 == n) ? "" : ", "));
        }
        System.out.println("]");
    }

    public static void bubbleSort(int[] array) {
        System.out.println();
        System.out.print(":::BUBBLE SORT:::");
        long startTime = System.currentTimeMillis();

        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time:::" + (endTime - startTime) + "MS:::");
    }

    public static void insertionSort(int[] array) {
        System.out.println();
        System.out.print(":::INSERTION SORT:::");
        long startTime = System.currentTimeMillis();

        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            // Move elements of array[0..i-1], that are greater than key, to one position ahead of their current position.
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time:::" + (endTime - startTime) + "MS:::");
    }

    public static void mergeSort(int[] array) {
        System.out.println();
        System.out.print(":::MERGE SORT:::");
        long startTime = System.currentTimeMillis();

        int n = array.length;
        mergeSort(array, 0, n - 1);

        long endTime = System.currentTimeMillis();
        System.out.println("Time:::" + (endTime - startTime) + "MS:::");
    }

    private static void mergeSort(int[] array, int l, int r) {
        if (l < r) {
            // Find the middle point.
            int m = (l + r) / 2;
            // Sort first and second halves.
            mergeSort(array, l, m);
            mergeSort(array, m + 1, r);
            // Merge the sorted halves.
            mergeSortMerge(array, l, m, r);
        }
    }

    private static void mergeSortMerge(int[] array, int l, int m, int r) {
        // Find sizes of two sub-arrays to be merged.
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays.
        int[] arrayL = new int[n1];
        int[] arrayR = new int[n2];

        // Copy data to temp arrays.
        for (int i = 0; i < n1; i++) {
            arrayL[i] = array[l + i];
        }
        for (int j = 0; j < n2; j++) {
            arrayR[j] = array[m + 1 + j];
        }

        /* Merge the temp arrays */

        // Initial indexes of first and second sub-arrays.
        int i = 0, j = 0;

        // Initial index of merged sub-array array.
        int k = l;
        while (i < n1 && j < n2) {
            if (arrayL[i] <= arrayR[j]) {
                array[k] = arrayL[i];
                i++;
            } else {
                array[k] = arrayR[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of arrayL[] if any.
        while (i < n1) {
            array[k] = arrayL[i];
            i++;
            k++;
        }

        // Copy remaining elements of arrayR[] if any.
        while (j < n2) {
            array[k] = arrayR[j];
            j++;
            k++;
        }
    }

    public static void quickSort(int[] array) {
        System.out.println();
        System.out.print(":::QUICK SORT:::");
        long startTime = System.currentTimeMillis();

        int n = array.length;
        quickSort(array, 0, n - 1);

        long endTime = System.currentTimeMillis();
        System.out.println("Time:::" + (endTime - startTime) + "MS:::");
    }

    private static void quickSort(int[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = quickSortPartition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static int quickSortPartition(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;
        return i + 1;
    }
}
