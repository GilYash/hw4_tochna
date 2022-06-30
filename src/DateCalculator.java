/**
 * This class represents a calculator which generates new dates.
 */
public class DateCalculator {
    //Defines
    private static int JANUARY = 1;
    private static int FEBRUARY = 2;
    private static int MARCH = 3;
    private static int APRIL = 4;
    private static int MAY = 5;
    private static int JUNE = 6;
    private static int JULY = 7;
    private static int AUGUST = 8;
    private static int SEPTEMBER = 9;
    private static int OCTOBER = 10;
    private static int NOVEMBER = 11;
    private static int DECEMBER = 12;
    private static int YEAR = 365;
    private static int LEAP_YEAR = 366;

    /**
     * This method gets a date and a number and calculates the new date according to the number.
     * @param date - An object of Date type.
     * @param num - Number of days to be added to or subtracted from the given date.
     * @return - The new calculated date.
     */
    public static Date addToDate(Date date, int num) {
        int dateDay = date.getDay();
        int dateMonth = date.getMonth();
        int dateYear = date.getYear();
        int maxDayOfMonth;

        if (num == 0) { //Breaking condition of recursion.
            return date;

        } else if (num > 0) {
            maxDayOfMonth = getMaxDayOfMonth(dateMonth, isLeapYear(dateYear)); //Get the max days of current month.


            if(num >= YEAR){
                if (dateMonth > FEBRUARY) { //We passed February.
                    if (isLeapYear(dateYear+1)) {
                        return addToDate(new Date(dateDay, dateMonth, dateYear + 1), num - LEAP_YEAR);
                        //We passed February and it's a leap year

                    } else {
                        return addToDate(new Date(dateDay, dateMonth, dateYear + 1), num - YEAR); //We passed
                                                                                        // February and not a leap year
                    }
                }
                else if (dateMonth <= FEBRUARY){
                    if (isLeapYear(dateYear)) {
                        return addToDate(new Date(dateDay, dateMonth, dateYear + 1), num - LEAP_YEAR);}
                    }
                else{
                    return addToDate(new Date(dateDay, dateMonth, dateYear + 1), num - YEAR);
                }
            }
            if ((dateDay + 1) > maxDayOfMonth) {
                dateDay = 1;
                if (dateMonth == DECEMBER) {
                    dateMonth = JANUARY;
                    dateYear += 1;
                } else {
                    dateMonth += 1;
                }
            } else {
                dateDay += 1;
            }
            return addToDate(new Date(dateDay,dateMonth,dateYear), --num);
        }
        else {


            if(num <= -YEAR){
                if (dateMonth >= FEBRUARY) { //We passed February.
                    if (isLeapYear(dateYear)) {
                        return addToDate(new Date(dateDay, dateMonth, dateYear - 1), num + LEAP_YEAR);
                        //We passed February and it's a leap year
                    } else {
                        return addToDate(new Date(dateDay, dateMonth, dateYear - 1), num + YEAR);
                        //We passed February and not a leap year
                    }
                }
                else if (dateMonth < FEBRUARY){
                    if (isLeapYear(dateYear-1)) {
                        return addToDate(new Date(dateDay, dateMonth, dateYear - 1), num + LEAP_YEAR);}
                }
                else{
                    return addToDate(new Date(dateDay, dateMonth, dateYear - 1), num + YEAR);
                }
            }

            if((dateDay - 1) < 1){
                if(dateMonth == JANUARY){
                    dateYear-=1;
                    dateMonth = DECEMBER;
                    dateDay = 31;
                }
                else {
                    maxDayOfMonth = getMaxDayOfMonth(dateMonth - 1, isLeapYear(dateYear));
                    //Get the max days of last month.
                    dateDay = maxDayOfMonth;
                    dateMonth -= 1;
                }

            }
            else{
                dateDay -= 1;
            }
             return addToDate(new Date(dateDay,dateMonth,dateYear), ++num);
        }

    }

    /**
     * This method calculates the maximal day a date can get in a given month.
     * @param month - The current month.
     * @param leapYear - A boolean parameter indicating if the current year is a leap year or not.
     * @return - The value of the last day of the current month.
     */
    public static int getMaxDayOfMonth(int month, boolean leapYear) {
        int maxDayOfMonth=0;
        //Categorizing the months
        if ((month == JANUARY) || (month == MARCH) || (month == MAY) || (month == JULY)
                || (month == AUGUST) || (month == OCTOBER) || (month == DECEMBER)) {
            maxDayOfMonth = 31;
        }
        if ((month == APRIL) || (month == JUNE) || (month == SEPTEMBER) || (month == NOVEMBER)) {
            maxDayOfMonth = 30;
        }
        if (month == FEBRUARY) {
            if (leapYear) {
                maxDayOfMonth = 29;
            } else {
                maxDayOfMonth = 28; // February and not a leap year.
            }
        }
        return maxDayOfMonth;
    }

    /**
     * This method calculates if a given year is a leap year or not.
     * @param year - The current year.
     * @return - True if the input year is a leap year and False otherwise.
     */
    public static boolean isLeapYear(int year) {
        boolean leapYear;

        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            return leapYear = true;
        } else {
            return leapYear = false;
        }
    }
}