package util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class StudentHandler
{
    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Table> tables = new ArrayList<>();

    Point tableSize = new Point(4, 4);

    StudentHandler()
    {
        addStudentsToArray(students, 16);
        addTables((int) tableSize.getX(), (int) tableSize.getY());
        addStudentsToTable();

        for(int i = 0; i < tables.size(); i++)
        {
//            System.out.println(tables.get(i).getStudents());
        }
//        shuffle();
        shuffleWithTables();
    }

    void renewAndShuffleStudents()
    {
        tables.clear();
        addTables((int) tableSize.getX(), (int) tableSize.getY());
        Collections.shuffle(students);
        addStudentsToTable();
    }


    void shuffleWithTables()
    {
        Table[] oldTables = new Table[tables.size()];

        System.out.print("P: ");
        for(int i = 0; i < oldTables.length; i++)
        {
            oldTables[i] = tables.get(i);
            System.out.print(oldTables[i].getStudents() + ", ");
        }

        System.out.println();

        renewAndShuffleStudents();

        System.out.print("P: ");

        for(int i = 0; i < oldTables.length; i++)
        {
            System.out.print(oldTables[i].getStudents() + ", ");
        }

        System.out.println();

        for(int i = 0; i < oldTables.length; i++)
        {
            System.out.print(oldTables[i].getGroupID() + ": ");
            System.out.println("Old table: " + oldTables[i].getStudents());
            System.out.println("New " + tables.get(i).getStudents());
            System.out.println();
        }

        for(int i = 0; i < tables.size(); i++)
        {
            for(int x = 0; x < tables.get(i).getStudents().size(); x++)
            {

            }
        }
    }


    void shuffle() //TODO: add tables to the shuffle
    {
        Student[] prevStudent = new Student[students.size()];

        renewAndShuffleStudents();

        for(int i = 0; i < students.size(); i++)
        {
            System.out.print("P: " + prevStudent[i] + " ");
        }

        System.out.println();
        System.out.println("A: " + students);

        for(int i = 0; i < prevStudent.length; i++)
        {
            if(prevStudent[i].equals(students.get(i)) && i + 1 < prevStudent.length)
            {
//                System.out.println(students.get(i) + " was put in the same seat and was swapped with " + students.get(i+1));
                Collections.swap(students, i, i + 1);
                for(int x = 0; x < i; x++)
                {
                    if(prevStudent[i].equals(students.get(i)) && i + 1 < prevStudent.length)
                    {
//                        System.out.println(students.get(i) + " was put in the same seat and was swapped with " + students.get(i+1));
                        Collections.swap(students, i, i + 1);
                    }
                    else if(prevStudent[i].equals(students.get(i)))
                    {
//                        System.out.println(students.get(i) + " was put in the same seat and was swapped with " + students.get(0));
                        Collections.swap(students, i, 0);
                    }
                }
            }
            else if(prevStudent[i].equals(students.get(i)))
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

        for(int i = 0; i < tables.size(); i++)
        {
            tables.get(i).clearStudents();
        }

        for(int x = 0; x < tables.size(); x++)
        {
            int start = x * tables.get(x).tableSize;
            int end = (start + tables.get(x).tableSize);
            ArrayList<Student> studentList = new ArrayList<>();
            for(int i = start; i < end; i++)
            {
                if(i < students.size())
                {
                    if(students.get(i) != null)
                    {
                        students.get(i).setTableGroup(tables.get(x).getGroupID());
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
                c = (char) (i + 71);
            }
            else
            {
                c = (char) (i + 65);
            }
            tables.add(new Table(tableSize, c));
        }
//        System.out.println(tables);
    }

    public static void main(String[] args)
    {
        new StudentHandler();
    }
}
