package util;

import java.util.ArrayList;
import java.util.List;

public class Table
{
    int tableSize;
    private char groupChar;
    private ArrayList<Student> students;

    Table(int tableSize, char groupChar)
    {
        this.tableSize = tableSize;
        this.groupChar = groupChar;
        students = new ArrayList<>();
    }

    public void setStudents(List<Student> students)
    {
        this.students.addAll(students);
        for(Student student: students)
        {
            student.setTableGroup(groupChar);
        }
    }

    public ArrayList<Student> getStudents()
    {
        return students;
    }

    public void setStudents(ArrayList<Student> students)
    {
        this.students = students;
    }

    public void addStudent(Student student)
    {
        this.students.add(student);
    }

    public void clearStudents()
    {
        this.students.clear();
    }

    public char getGroupID()
    {
        return groupChar;
    }

//    @Override
//    public String toString()
//    {
//      return String.valueOf(groupChar);
//    }
}
