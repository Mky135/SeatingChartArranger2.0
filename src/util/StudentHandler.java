package util;

import java.util.ArrayList;
import java.util.Collections;

public class StudentHandler
{
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Table> tables = new ArrayList<>();

    StudentHandler()
    {
        addStudentsToArray(students, 21);
        addTables(3, 10);
        addStudentsToTable();
        System.out.println(students);
        shuffle();
    }

    void shuffle() //TODO: add tables to the shuffle
    {
        ArrayList<Student> prevStudent = students;
        Collections.shuffle(students);
        System.out.println("P: " + students);

        for(int i = 0; i < prevStudent.size(); i++)
        {
            if(prevStudent.get(i).equals(students.get(i)) && i+1<prevStudent.size())
            {
//                System.out.println(students.get(i) + " was put in the same seat and was swapped with " + students.get(i+1));
                Collections.swap(students, i, i+1);
                for(int x=0; x < i; x++)
                {
                    if(prevStudent.get(i).equals(students.get(i)) && i+1<prevStudent.size())
                    {
//                        System.out.println(students.get(i) + " was put in the same seat and was swapped with " + students.get(i+1));
                        Collections.swap(students, i, i+1);
                    }
                    else if(prevStudent.get(i).equals(students.get(i)))
                    {
//                        System.out.println(students.get(i) + " was put in the same seat and was swapped with " + students.get(0));
                        Collections.swap(students, i, 0);
                    }
                }
            }
            else if(prevStudent.get(i).equals(students.get(i)))
            {
//                System.out.println(students.get(i) + " was put in the same seat and was swapped with " + students.get(0));
                Collections.swap(students, i, 0);
            }
        }
        System.out.println("A: " + students);
    }

    void addStudentsToArray(ArrayList<Student> students, int limit)
    {
        FileHandler fileHandler = new FileHandler("src/Names.txt");
        for(int i = 0; i < limit; i++)
        {
            students.add(new Student(fileHandler.getLine(i)));
        }
    }

    void addStudentsToTable()
    {
        for(int x = 0; x < tables.size(); x++)
        {
            int start = x * tables.get(x).tableSize;
            int end = (start + tables.get(x).tableSize);
//            System.out.println("Range: " + start + " - " + end);
            ArrayList<Student> studentList = new ArrayList<>();
            for(int i = start; i < end; i++ )
            {
                if(i < students.size())
                {
                    if(students.get(i) != null)
                    {
                        studentList.add(students.get(i));
                    }
                }
            }
            tables.get(x).setStudents(studentList);
        }
    }

    void addTables(int amountOfTables, int tableSize)
    {
        for(int i = 0; i < amountOfTables; i++)
        {
            char c;
            if(i >= 26)
            {
                c = (char) (i+71);
            }
            else
            {
                c = (char) (i+65);
            }
            tables.add(new Table(tableSize, c));
        }
        System.out.println(tables);
    }

    public static void main(String[] args)
    {
        new StudentHandler();
    }
}
