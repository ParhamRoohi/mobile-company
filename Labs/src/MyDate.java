import java.io.Serializable;
import java.util.ArrayList;

public class MyDate implements Cloneable, Comparable<MyDate>, Serializable {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDate(MyDate myDate) {
        year = myDate.year;
        month = myDate.month;
        day = myDate.day;
    }

    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }

    public void print() {
        System.out.println(year + " / " + month + " / " + day);
    }

    public String toString() {
        return year + " / " + month + " / " + day;
    }

    public Boolean isExpired(MyDate expiryDate) {
        if (this.year < expiryDate.year) {
            return true;
        } else if (this.year == expiryDate.year) {
            if (this.month < expiryDate.month) {
                return true;
            }
        } else if (this.month == expiryDate.month) {
            if (this.day < expiryDate.day) {
                return true;
            }
        }
        return false;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int compareTo(MyDate otherExpiryDate) {
        boolean isExpired = isExpired(otherExpiryDate);
        if (isExpired(otherExpiryDate) == true) {
            return -1;
        } else if (isExpired(otherExpiryDate) == false) {
            return 1;
        }
        return 0;
    }

    // lab 6

    public String toDelimitedString() {
        return year + "," + month + "," + day;
    }

}
