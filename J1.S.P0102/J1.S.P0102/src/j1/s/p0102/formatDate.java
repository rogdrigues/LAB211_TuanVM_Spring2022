/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0102;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author phong
 */
public class formatDate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Step 1. User enter date with date format [dd/mm/yyyy] from keyboard
        String dateInput = enterInputDate();
        //Step 2: Check user input date with correct date format exist 
        boolean checkDateExist = dateExist(dateInput);
        //Step 3. Determine day of week with input date is exist
        dayOfWeek(dateInput, checkDateExist);
    }

    public static String enterInputDate() {
        Scanner sc = new Scanner(System.in);
        String inputDate;

        //Loop until user enter date with format [dd/MM/yyyy]
        do {
            System.out.print("Please enter date with format [dd/mm/yyyy]: ");

            inputDate = sc.nextLine().trim();
            //If user don't input anything from keyboard
            if (inputDate.isEmpty()) {
                System.err.println("Input is empty. Please try again");
                continue;
            }
            //Check if input date was exactly a correct format date
            /*Regex
                \\d{2}: the number have 2 digit number
                [/]: input contain a character '/'
                \\d{4}: the number must have 4 digit number
             */
            if (!inputDate.matches("\\d{2}[/]\\d{2}[/]\\d{4}")) {
                System.err.println("Input is wrong format date.");
            } else {
                return inputDate;
            }
        } while (true);
    }

    public static boolean dateExist(String inputDate) {
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            /*If user enter the date like 30/02/2021 (Only 28 days).It will  
                be reported wrong format and must be re-entered*/
            formatDate.setLenient(false);
            formatDate.parse(inputDate);
        } catch (ParseException dateFormatExpcetion) {
            System.err.println("Input date doesn't existed. Please try again");
            return false;
        }
        return true;
    }

    public static void dayOfWeek(String inputDate, boolean checkDateExist) {
        //If date is not exist, then re-enter date
        if (checkDateExist == false) {
            inputDate = enterInputDate();
        }
        SimpleDateFormat formatDay = new SimpleDateFormat("EEEE");
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = formatDate.parse(inputDate);
        } catch (ParseException dateFormatExpection) {
            System.err.println("Input date invalid. Please try again");
        }

        String getDayofWeek = formatDay.format(date);
        System.out.println("Your day is " + getDayofWeek);
    }
}
