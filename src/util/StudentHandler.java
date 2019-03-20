package util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class StudentHandler
{
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();

    private Point tableSize = new Point(4, 4);

    StudentHandler()
    {
        addStudentsToArray(students, 16);
        addTables((int) tableSize.getX(), (int) tableSize.getY());
        addStudentsToTable();

        shuffleWithTables();
    }

    private void renewAndShuffleStudents()
    {
        tables.clear();
        addTables((int) tableSize.getX(), (int) tableSize.getY());
        Collections.shuffle(students);
        addStudentsToTable();
    }


    void shuffleWithTables()
    {
        Table[] oldTables = new Table[tables.size()];

        for(int i = 0; i < oldTables.length; i++)
        {
            oldTables[i] = tables.get(i);
        }

        System.out.println();

        renewAndShuffleStudents();

        System.out.print("P: ");

        for(int i = 0; i < oldTables.length; i++)
        {
            System.out.print(oldTables[i].getStudents() + ", ");
        }
        System.out.println();

        for(int i = 0; i < tables.size(); i++)
        {
            System.out.print(tables.get(i).getStudents() + ", ");
        }
        System.out.println();

        for(int i = 0; i < oldTables.length; i++)
        {
            System.out.print(oldTables[i].getGroupID() + ": ");
            System.out.println("Old table: " + oldTables[i].getStudents());
            System.out.println("New " + tables.get(i).getStudents());
            System.out.println();
        }

        Student a;
        Student b;

        for(int i = 0; i < tables.size(); i++)
        {
            for(int x = 0; x < tables.get(i).getStudents().size(); x++)
            {
                for(int r = 0; r < oldTables[i].getStudents().size(); r++)
                {
                    if(tables.get(i).getStudents().get(x).getName().equals(oldTables[i].getStudents().get(r).getName()))
                    {
                        boolean swapped = false;
                        for(int j = 0; j < tables.get(0).getStudents().size(); j++)
                        {
                            for(int k = 0; k < oldTables.length; k++)
                            {
                                for(int l = 0; l < oldTables[j].getStudents().size(); l++)
                                {
                                    if(i < tables.size()-1)
                                    {
                                        if(tables.get(i + 1).getStudents().get(j).getName().equals(oldTables[k].getStudents().get(l).getName()))
                                        {
                                            if( oldTables[k].getStudents().get(l).getTableGroup() != tables.get(i).getGroupID())
                                            {
                                                a = tables.get(i + 1).getStudents().get(j);
                                                b = tables.get(i).getStudents().get(x);
                                                swapStudent(a, b);
                                                swapped = true;
                                                break;
                                            }
                                        }
                                    }
                                    else
                                    {
                                        if(tables.get(0).getStudents().get(j).getName().equals(oldTables[k].getStudents().get(l).getName()))
                                        {
                                            if( oldTables[k].getStudents().get(l).getTableGroup() != tables.get(i).getGroupID())
                                            {
                                                a = tables.get(0).getStudents().get(j);
                                                b = tables.get(i).getStudents().get(x);
                                                swapStudent(a, b);
                                                swapped = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                                if(swapped)
                                {
                                    break;
                                }
                            }
                            if(swapped)
                            {
                                break;
                            }
                        }
                    }

                }
            }
        }

        for(int i = 0; i < tables.size(); i++)
        {
            System.out.print(tables.get(i).getStudents());
        }
    }

    void swapStudent(Student a , Student b)
    {
        Student temp = new Student("temp");

        temp.setStudent(a);
        a.setStudent(b);
        b.setStudent(temp);
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
    }

    public static void main(String[] args)
    {
        new StudentHandler();
    }
}
