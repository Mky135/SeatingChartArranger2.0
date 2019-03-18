package util;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Student
{
    private char tableGroup;
    private String name;
    private Point2D seat;
    private Image image;
    private Dimension2D maxImageSize = new Dimension2D(100, 100);

    public Student(String name, Point2D seat, String image, char tableGroup)
    {
        this.name = name;
        this.seat = seat;
        this.tableGroup = tableGroup;

        if(image != null)
        {
            this.image = new Image(image);
            Dimension2D imageDimension = new Dimension2D(this.image.getWidth(), this.image.getHeight());

            if(imageDimension.getHeight() > maxImageSize.getHeight() || imageDimension.getWidth() > maxImageSize.getWidth())
            {
                this.image = new Image(image, maxImageSize.getWidth(), maxImageSize.getHeight(), true, true);
            }
        }

    }

    public Student(String name, Point2D seat, char tableGroup)
    {
        this(name, seat, null, tableGroup);
    }

    public Student(String name, double x, double y, char tableGroup)
    {
        this(name, new Point2D(x, y), tableGroup);
    }

    public Student(String name, char tableGroup)
    {
        this(name, -1, - 1, tableGroup);
    }

    public Student(String name)
    {
        this(name, -1, -1, '0');
    }

    public char getTableGroup()
    {
        return tableGroup;
    }

    public void setTableGroup(char tableGroup)
    {
        this.tableGroup = tableGroup;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);

        if(!seat.equals(new Point2D(-1, -1)))
        {
            stringBuilder.append(":").append(" Seat: ").append(seat);
        }
        if(tableGroup != '0')
        {
//            stringBuilder.append(" Group: ").append(tableGroup);
            stringBuilder.append(" ").append(tableGroup);
        }
        if(image != null)
        {
            stringBuilder.append(" Image: ").append(image);
        }

        return stringBuilder.toString();
    }

    public String toStringSimp()
    {
        return name;
    }
}
