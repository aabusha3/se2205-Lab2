/*
 * Authors: Kevin Abdo & Ahmad Abu Shawarib
 * Date: November/26/2021
 * Description: comparing, in practice, the time complexities of different sorting methods
 */

package LA2Q1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class KevinAhmadTestingSortingMethods {
    //header method
    public static void myHeader() {//printHeader;  prints a header
        System.out.println();
        System.out.println("*****************************************************************");
        System.out.print("""
                Names: Kevin & Ahmad.
                Student Numbers: 251171763 & 251149713.
                This Code Uses Various Sorting Methods To Sort An Array Of
                50000 Random Numbers And Gives The Time Tt Takes In Milliseconds.
                """);
        System.out.println("*****************************************************************\n");
    }

    //footer method
    public static void myFooter() {//printFooter;  prints a footer
        System.out.println("\n*****************************************************************");
        //formats the date and time to match the desired output
        DateTimeFormatter time = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter date = DateTimeFormatter.ofPattern("MMMM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();

        //prints out the date and time
        System.out.printf("This Is %s On %s\n", time.format(now), date.format(now));
        System.out.print(""" 
                Completion Of Lab Assignment 2 Q1 Is Successful!
                Goodbye from Kevin Abdo & Ahmad Abu Shawarib.
                """);
        System.out.println("*****************************************************************");
    }


    //the main method begins here
    public static void main(String[] args) {

        //calls the header method
        myHeader();

        //initializing our main 50,000 sized array and backup array
        Integer[] backupArray = new Integer[50000];
        for (int i = 0; i < backupArray.length; i++)
            backupArray[i] = (int) (Math.random() * 99999);

        Integer[] mainArray = Arrays.copyOf(backupArray, backupArray.length);
        // copying values from backupArray to the mainArray, the backup will serve as a snapshot of the initial order of the values


        System.out.println("Testing Execution Time Of Different Sorting Algorithms For Sorting 50000 Random Numbers:");

        List<Integer> mainArrayList = new ArrayList<>(Arrays.asList(mainArray));//turning the mainArray into an array list
        long startTimne = System.nanoTime();//starting the time for collection sort
        Collections.sort(mainArrayList);//sorting via Collections' sort method
        System.out.printf("Collections' Sorting Time: %.2f Milliseconds%n", (System.nanoTime() - startTimne) * 1E-6);


        //here we print out all the times for the various sorting methods
        //we have to recopy the backup array to inorder to pass the same array to all the sorts and allow us to compare their execution times
        mainArray = Arrays.copyOf(backupArray, backupArray.length);
        System.out.printf("Our Selection-Sort Time: %.2f Milliseconds%n", (selectionSort(mainArray) * 1E-6));

        mainArray = Arrays.copyOf(backupArray, backupArray.length);
        System.out.printf("Our Bubble-Sort Time: %.2f Milliseconds%n", (bubbleSort(mainArray) * 1E-6));

        mainArray = Arrays.copyOf(backupArray, backupArray.length);
        System.out.printf("Our Insertion-Sort Time: %.2f Milliseconds%n", (insertionSort(mainArray) * 1E-6));

        mainArray = Arrays.copyOf(backupArray, backupArray.length);
        System.out.printf("Our Merge-Sort Time: %.2f Milliseconds%n", (mergeSort(mainArray) * 1E-6));

        mainArray = Arrays.copyOf(backupArray, backupArray.length);
        System.out.printf("Our Quick-Sort Time: %.2f Milliseconds%n", (quickSort(mainArray, 0, mainArray.length) * 1E-6));

        mainArray = Arrays.copyOf(backupArray, backupArray.length);
        System.out.printf("Our Bucket-Sort Time: %.2f Milliseconds%n", (bucketSort(mainArray, 0, mainArray.length, 5) * 1E-6));

        //calls the footer method
        myFooter();
    }


    //selection sort:
    public static <T extends Comparable<? super T>> long selectionSort(T[] list) {
        long startTime = System.nanoTime();//starting time for selectionSort
        for (int outerIndex = 0; outerIndex < list.length - 1; outerIndex++) {
            //the loops through with array with the next smallest being integer n
            int indexOfNextSmallest = outerIndex;

            for (int innerIndex = outerIndex + 1; innerIndex < list.length; innerIndex++)
                if (list[innerIndex].compareTo(list[indexOfNextSmallest]) < 0)  //the compareTo method returns -1 if less than
                    indexOfNextSmallest = innerIndex;//Swap indices if the item is smaller than the previous item

            //swap items according the
            T temp = list[outerIndex];//temporary placeholder for the outerIndex of the array
            list[outerIndex] = list[indexOfNextSmallest]; //this replaces the current index with the next smallest number
            list[indexOfNextSmallest] = temp;
        }
        //The array should be sorted now
        return System.nanoTime() - startTime;
    }


    //bubble sort:
    public static <T extends Comparable<? super T>> long bubbleSort(T[] list) {
        long startTime = System.nanoTime(); //starting time for bubbleSort
        for (int repeat = 1; repeat < list.length - 1; repeat++)
            for (int index = 0; index < list.length - repeat; index++)
                //loops through twice

                //if the next index has list value less than the current one we swap their places
                if (list[index].compareTo(list[index + 1]) > 0) { //the compareTo method gives us 1 if greater than (same as writing ==1)
                    T temp = list[index]; //temporary placeholder variable
                    list[index] = list[index + 1];//
                    list[index + 1] = temp;
                }
        //the array should be sorted now
        return System.nanoTime() - startTime;//how long it took
    }

    //insertion sort:
    public static <T extends Comparable<? super T>> long insertionSort(T[] list) {
        long startTime = System.nanoTime(); //starting time for insertionSort

        //we start with the first element and loop forward
        //we shift the array and replace the current element such that the elements to the right of it are greater and to the left are smaller
        for (int repeat = 1; repeat < list.length; repeat++) {
            T key = list[repeat];
            for (int index = repeat - 1; (index >= 0) && (list[index].compareTo(key) > 0); index--) { //1 is given if greater than in compareTo method
                //swap
                list[index + 1] = list[index];
                list[index] = key;
            }
        }
        //the array should be sorted now
        return System.nanoTime() - startTime;
    }


    //merge sort:
    public static <T extends Comparable<? super T>> long mergeSort(T[] list) {
        long startTime = System.nanoTime();//starting time

        if (list.length < 2) //if the array is less than size 2 no need to any sorting as it will already be sorted
            return System.nanoTime() - startTime; // returns the time


        int mid = list.length / 2; //divide the array in half
        T[] partition1 = Arrays.copyOf(list, mid); //copy the first half to another array (partition1)
        T[] partition2 = Arrays.copyOfRange(list, mid, list.length); //copy the second half to another array (partition2)

        mergeSort(partition1);
        mergeSort(partition2); //we then further divide up the sections into even smaller sections


        int partition1Index = 0, partition2Index = 0;
        //after sectioning is complete we start comparing and changing the indices of the elements
        while (partition1Index + partition2Index < list.length)
            //comparisons
            if (partition2Index == partition2.length || (partition1Index < partition1.length && partition1[partition1Index].compareTo(partition2[partition2Index]) < 0))
                list[partition1Index + partition2Index] = partition1[partition1Index++]; //sort section 1 if section 1 has smaller element
            else
                list[partition1Index + partition2Index] = partition2[partition2Index++]; //sort section 2 if section 2 has smaller element

        //The array should be sorted by this point
        return System.nanoTime() - startTime;
    }


    //quick sort :
    public static <T extends Comparable<? super T>> long quickSort(T[] list, int first, int last) {
        long startTime = System.nanoTime();//starting time
        if (first >= last)//checks if the array is already sorted (1 element)
            return System.nanoTime() - startTime; //prints out the time for the trivial solution

        if (last == list.length)
            last--;//in-case the wrong length is entered from main

        int left = first;
        int right = last;
        T pivot = list[(left + right) / 2];//the pivot is the middle (mean value of the array)

        while (left <= right) {//while loop that continues as long as the left side is smaller or equal to the right
            while (list[left].compareTo(pivot) < 0)
                left++; //loop through the array to find values on the left less than pivot

            while (list[right].compareTo(pivot) > 0)
                right--;//loop through the array to find values on the right greater than pivot

            if (left <= right) {//if indices didn't cross we have to swap and decrement the range
                if (left != right) {//swap
                    T temp = list[left]; //temporary placeholder used for swapping indices and such
                    list[left] = list[right];
                    list[right] = temp;
                }
                left++;
                right--;//move up from left side and down from side (range is smaller now)
            }
        }
        //self-call to method to repeat the process until the array is sorted
        if (first < right)
            quickSort(list, first, right);
        if (left < last)
            quickSort(list, left, last);
        //sorted
        return System.nanoTime() - startTime;//how long it took
    }


    //bucket sort:
    public static long bucketSort(Integer[] list, int first, int last, int maxDigits) {
        long startTime = System.nanoTime();//starting time

        if (last == list.length)
            last--;//in-case the wrong length is entered from main

        Vector<Integer>[] buckets = new Vector[10];

        for (int index = 0; index < buckets.length; index++)
            buckets[index] = new Vector<>(); //instantiate each bucket

        for (int count = 0; count < maxDigits; count++) {
            for (Vector<Integer> bucket : buckets)
                bucket.removeAllElements();//clear the Vector buckets

            for (int index = first; index <= last; index++)
                buckets[findDigit(list[index], count)].add(list[index]); //Placing list[index] at end of bucket[findDigit]

            int index = 0;
            for (Vector<Integer> bucket : buckets)
                for (Integer digit : bucket)
                    list[index++] = digit; //placing all the buckets back into the array
        }
        return System.nanoTime() - startTime;//how long it took
    }

    //The following method extracts the ith digit (digitPlace) from a number
    public static int findDigit(int number, int digitPlace) {
        return ((int) (number / Math.pow(10, digitPlace))) % 10;
    }
}