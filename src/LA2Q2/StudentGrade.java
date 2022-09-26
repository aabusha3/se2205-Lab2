/*
 * Authors: Kevin Abdo & Ahmad Abu Shawarib
 * Date: November/26/2021
 * Description: StudentGrade object class with Comparable implementation
 * with private firstName, lastName, and grade of public data-fields, setters, and getters
 * and an empty args constructor to act as default, and a constructor with args firstName, lastName, and grade
 * with overridden toString and compareTo methods
 */

package LA2Q2;

public class StudentGrade implements Comparable<StudentGrade> {
    private String firstName, lastName;//holds the students' first and last names
    private Integer grade;//holds the students' grades


    //empty args constructor to act as a default constructor, initializes the private data-fields with my info
    public StudentGrade() {
        System.out.println("Default Constructor Used!");
        this.firstName = "Ahmad";
        this.lastName = "Abu";
        this.grade = 90;
    }

    //constructor with args firstName, lastName, and grade to initialize the private data-fields with info from the driver class
    public StudentGrade(String firstName, String lastName, Integer grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }


    //getters:
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Integer getGrade() {
        return this.grade;
    }

    //setters:
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }


    @Override
    //overridden toString prints the data-fields in a specific format
    public String toString() {
        return String.format("%10s %-11s%s%8d", this.firstName, this.lastName, ":", this.grade);
    }

    @Override
    //overridden compareTo returns the value of (x < y) ? -1 : ((x == y) ? 0 : 1)
    public int compareTo(StudentGrade anotherStudentGrade) {
        return this.grade.compareTo(anotherStudentGrade.grade);
    }
}
