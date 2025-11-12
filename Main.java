import java.util.*;
import java.io.*;

// Base class
class Person {
    private String name;
    private int age;
    public Person(String name, int age) { this.name = name; this.age = age; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

// Derived class for Student
class Student extends Person {
    private int rollNo;
    private ArrayList<Integer> marks = new ArrayList<>();

    public Student(String name, int age, int rollNo) {
        super(name, age);
        this.rollNo = rollNo;
    }

    public void addMark(int mark) { marks.add(mark); }

    public double getAverage() {
        if (marks.isEmpty()) return 0;
        int total = 0;
        for (int m : marks) total += m;
        return (double) total / marks.size();
    }

    public String toString() {
        return String.format(
            "Name: %s\nAge: %d\nRoll No: %d\nMarks: %s\nAverage: %.2f\n\n",
            getName(), getAge(), rollNo, marks, getAverage()
        );
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=== Student Score & Average Calculator ===");
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = sc.nextInt();

            System.out.print("Roll No: ");
            int rollNo = sc.nextInt();

            Student s = new Student(name, age, rollNo);

            System.out.print("Enter number of subjects: ");
            int subjects = sc.nextInt();

            for (int j = 0; j < subjects; j++) {
                System.out.print("Enter mark for subject " + (j + 1) + ": ");
                int mark = sc.nextInt();
                s.addMark(mark);
            }

            sc.nextLine(); // clear buffer
            students.add(s);
        }

        sc.close();

        // Display results
        System.out.println("\n=== Results Summary ===");
        for (Student s : students) {
            System.out.println(s);
        }

        // Save to file
        try (FileWriter fw = new FileWriter("results.txt")) {
            for (Student s : students) fw.write(s.toString());
            System.out.println("✅ Results saved to results.txt");
        } catch (IOException e) {
            System.out.println("⚠️ Error writing to file: " + e.getMessage());
        }
    }
}
