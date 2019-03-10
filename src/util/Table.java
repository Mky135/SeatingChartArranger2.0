package util;

import java.util.ArrayList;
import java.util.List;

public class Table
{
    int tableSize;
    char groupChar;
    ArrayList<Student> students;

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

    @Override
    public String toString()
    {
      return String.valueOf(groupChar);
    }
}
