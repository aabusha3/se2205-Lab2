/*
 * Authors: Kevin Abdo & Ahmad Abu Shawarib
 * Date: November/26/2021
 * Description: sorting custom objects
 */

package LA2Q2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Vector;

public class KevinAhmad_SortNameAndGrade {
    //printHeader;  prints a header
    public static void myHeader() {
        System.out.println();
        System.out.println("*****************************************************************");
        System.out.print("""
                Names: Kevin & Ahmad.
                Student Numbers: 251171763 & 251149713.
                This Code Orders The Private Data-Fields Of The StudentGrade
                Class In An Ascending Fashion.
                """);
        System.out.println("*****************************************************************\n");
    }

    //printFooter;  prints a footer
    public static void myFooter() {
        System.out.println("\n*****************************************************************");
        //formats the date and time to match the desired output
        DateTimeFormatter time = DateTimeFormatter.ofPattern("hh:mm");
        DateTimeFormatter date = DateTimeFormatter.ofPattern("MMMM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();

        //prints out the date and time
        System.out.printf("This Is %s On %s\n", time.format(now), date.format(now));
        System.out.print(""" 
                Completion Of Lab Assignment 2 Q2 Is Successful!
                Goodbye from Kevin Abdo & Ahmad Abu Shawarib.
                """);
        System.out.println("*****************************************************************");
    }


    public static void main(String[] args) {

        //calls the header method
        myHeader();


        String[] fnArray = {"Hermione", "Ron", "Harry", "Luna", "Ginny", "Draco", "Dean", "Fred"};//the students' first names
        String[] lnArray = {"Granger", "Weasley", "Potter", "Lovegood", "Weasley", "Malfoy", "Thomas", "Weasley"};//the students' last names
        Integer[] grd = {(int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26)
                , (int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26)
                , (int) (60 + Math.random() * 26), (int) (60 + Math.random() * 26)};//the students' randomized grades, random number is between 60 and 85 inclusive


        Vector<StudentGrade> sg = new Vector<>(fnArray.length);
        for (int index = 0; index < fnArray.length; index++)//initializing 8 StudentGrade objects to be stored inside a Vector list
            sg.insertElementAt(new StudentGrade(fnArray[index], lnArray[index], grd[index]), index);
        sg.trimToSize();//ensures the size of the vector is 8


        System.out.println("The Unsorted Array ................");
        for (StudentGrade oneSG : sg)
            System.out.println(oneSG);//overridden toString format in StudentGrade class


        System.out.println("\n\nSorted By Grades......................");
        Vector<StudentGrade> sgCLone = (Vector<StudentGrade>) sg.clone();//grabbing a deep clone
        Collections.sort(sgCLone);//sorted using the included sort method
        for (StudentGrade oneSG : sgCLone)
            System.out.println(oneSG);//overridden toString format in StudentGrade class


        StudentGrade[] sgArray = new StudentGrade[sg.size()];
        sg.copyInto(sgArray);//copies the vector into an array

        System.out.println("\n\nSorted By First Names......................");
        insertionSort(sgArray, 1);//sorted using a modified insertionSort from q1
        for (StudentGrade oneSG : sgArray)
            System.out.println(oneSG);//overridden toString format in StudentGrade class


        System.out.println("\n\nSorted By Last Names......................");
        sg.copyInto(sgArray);
        insertionSort(sgArray, 2);//sorted using a modified insertionSort from q1
        for (StudentGrade oneSG : sgArray)
            System.out.println(oneSG);//overridden toString format in StudentGrade class


        //calls the footer method
        myFooter();
    }


    public static void insertionSort(StudentGrade[] sgArray, int key) {
        if (key == 1) {//sorts by first name
            //we start with the first element and loop forward
            //we shift the array and replace the current element such that the elements to the right of it are greater and to the left are smaller
            for (int repeat = 1; repeat < sgArray.length; repeat++) {
                StudentGrade temp = sgArray[repeat];
                for (int index = repeat - 1; index >= 0 && (sgArray[index].getFirstName().compareTo(temp.getFirstName()) > 0); index--) {//>0, not necessarily 1, is given if greater than in compareTo method
                    //swap
                    sgArray[index + 1] = sgArray[index];
                    sgArray[index] = temp;
                }
            }
        } else if (key == 2) {//sorts by last name
            //we start with the first element and loop forward
            //we shift the array and replace the current element such that the elements to the right of it are greater and to the left are smaller
            for (int repeat = 1; repeat < sgArray.length; repeat++) {
                StudentGrade temp = sgArray[repeat];
                for (int index = repeat - 1; index >= 0 && (sgArray[index].getLastName().compareTo(temp.getLastName()) > 0); index--) {//>0, not necessarily 1, is given if greater than in compareTo method
                    //swap
                    sgArray[index + 1] = sgArray[index];
                    sgArray[index] = temp;
                }
            }
        } else//prints error message to why the array wasn't sorted
            System.out.println("Make Sure To Enter In Only '1' Or '2' For The Key");
    }
}
