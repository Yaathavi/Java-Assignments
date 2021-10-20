/* Yaathavi Theevannan, Date: April 12, 2021

This program constructs 2 Date objects and runs methods on the objects to provide various information regarding those dates.

Input: 2 Dates
Output: days between 2 dates, if the date's year is a leap year or not, the day the date falls on, and a new date after adding a certain number of days. */

public class Date {
    int year;
    int month;
    int day;

    //constructor method that constructs a new Date object to represent the given date
    public Date(int year, int month, int day){
        if(year < 0 || month < 0 || day < 0){
            throw new IllegalArgumentException();  //throws an exception on a negative day, month, or year.
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // method that moves the Date object forward in time by the given number of days
    public void addDays(int days){
        int currDays = this.day; // assigns the days in given month to currDays
        int year = this.year; 

        for (int i = 1; i < this.month; ++i) {
            currDays += daysInMonth(i, year); // calculates # of days in the year up to the given month and day 
        }

        int totalDays = currDays + days; // gets the new date in number of days format
        int month = 1; // sets month to 1

        // while loop that runs until total number of days is less than or equal to the total days in the final month
        while (totalDays > daysInMonth(month, year)) {
            totalDays -= daysInMonth(month, year); 
            month += 1;
            if (month > 12) {
                month = 1;
                year += 1;
            }
        }
        this.month = month;
        this.year = year;
        this.day = totalDays;
    }

    // additional method to assign the months with the corresponding days 
    public int daysInMonth(int month, int year) {
        if (month == 1) {
            return 31;
        } else if (month == 2) {
            if (checkLeapYear(year)) {
                return 29;
            }
            return 28;
        } else if (month == 3) {
            return 31;
        } else if (month == 4) {
            return 30;
        } else if (month == 5) {
            return 31;
        } else if (month == 6) {
            return 30;
        } else if (month == 7) {
            return 31;
        } else if (month == 8) {
            return 31;
        } else if (month == 9) {
            return 30;
        } else if (month == 10) {
            return 31;
        } else if (month == 11) {
            return 30;
        }
        return 31;
    }

    // additional method that accepts a year as a parameter and returns true if it's a leap year
    public boolean checkLeapYear(int year){
        if(year % 4 == 0){
            if(year % 100 == 0 && year % 400 > 0){
                return false;
            } else{
            return true;
            }
        } else {
            return false;
        }
    }

    // method to move the Date object forward in time by the given number of weeks
    public void addWeeks(int weeks){
        int numDays = weeks * 7;
        addDays(numDays);
    }

    // method to return the # of days the Date must be adjusted to make it equal to the second given Date
    public int daysTo(Date other){
        int currDays = 0;
        int startDay = this.day;
        int startYear = this.year;
        int startMonth = this.month;
        int desiredDay = other.day;
        int desiredYear = other.year;
        int desiredMonth = other.month;
        boolean backwards = false; // keep track of if the second given date is earlier than the first given date
        
        // if the second given date is earlier than the first given date, swap them
        if(this.year > other.year || (this.year == other.year && this.month > other.month)){
            backwards = true; // set backwards boolean to true

            //swap the values
            startDay = other.day;
            startYear = other.year;
            startMonth = other.month;
            desiredDay = this.day;
            desiredYear = this.year;
            desiredMonth = this.month;
        }

        // while loop that adds days until month of desired date is reached
        while(!(startMonth >= desiredMonth && startYear >= desiredYear)){
            currDays += daysInMonth(startMonth, startYear);
            startMonth += 1;
            if (startMonth > 12) {
                startMonth = 1;
                startYear += 1;
            }
        }
        currDays += (desiredDay - startDay);

        // if the second given date is earlier than the first given date, return a negative number of days
        if(backwards){
            currDays *= -1;
        }
        return currDays;
    }

    // method that returns the day value of this date
    public int getDay()
    {
        return this.day;
    }

    // method that returns the month value of this date
    public int getMonth()
    {
        return this.month;
    }

    // method that returns the year value of this date
    public int getYear()
    {
        return this.year;
    }

    // method to return a String representing what day of the week this Date falls on
    public String getDayOfWeek(){
        Date base = new Date(1753, 1, 1); // This date falls on a Monday
        int days = base.daysTo(this); // calculates number of days past the base date of given date

        // calculates remainder of days/7 to get the corresponding weekday 
        int weekday = days % 7;
        if (weekday < 0){
            weekday += 7;
        }

        // returns the correct weekday based on the weekday value 
        if(weekday == 0){
            return "Monday";
        } else if (weekday == 1){
            return "Tuesday";
        } else if (weekday == 2){
            return "Wednesday";
        } else if (weekday == 3){
            return "Thursday";
        } else if (weekday == 4){
            return "Friday";
        } else if (weekday == 5){
            return "Saturday";
        } 
        return "Sunday"; 
    }

    // method that checks if the given date is a leap year 
    public boolean isLeapYear(){
        return checkLeapYear(this.year); // passes given year to the checkLeapYear method
    }

    // method that returns a String representation of this date in the form year/month/day order
    public String toString() {
        String date = this.year + "/" + this.month + "/" + this.day;
        return date;
    }
}