/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0105;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author phong
 */
public class GetDataInput {

    public int getInt(String msg, String outRangeMsg, int min, int max) {
        Scanner sc = new Scanner(System.in);
        double intInput;
        String input;

        if (msg.isEmpty()) {
            System.out.print(msg);
        } else {
            System.out.println(msg);
        }
        //Loop until user enter positive number in range of min and max
        do {
            input = sc.nextLine().trim();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println("Input is empty.");
                continue;
            }
            try {
                intInput = Double.parseDouble(input);
                //User enter input out of range
                if (intInput < min || intInput > max) {
                    throw new Error();
                }
                //User enter a real number
                if ((int) intInput != intInput) {
                    throw new Exception();
                } else {
                    break;
                }
                //User enter a string
            } catch (NumberFormatException StringInput) {
                System.err.println("Input cannot be a String.");
            } catch (Exception RealNumber) {
                System.err.println("Input cannot be a real number.");

            } catch (Error OutOfRange) {
                System.err.println(outRangeMsg);
            }
        } while (true);
        return (int) intInput;
    }

    public String getString(String msg) {
        Scanner sc = new Scanner(System.in);
        String input;

        if (msg.isEmpty()) {
            System.out.print(msg);
        } else {
            System.out.println(msg);
        }
        //Loop until user enter string from the keyboard
        do {
            input = sc.nextLine().trim();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println("Input is empty.");
            } /*[a-zA-Z ]+: Matches any single character from a-z, A-Z and space 
            character in brackets at unlimited times impossible*/ 
            else if (!input.matches("[a-zA-Z ]+")) {
                System.err.println("Input cannot be a number or special "
                        + "character");
            } else {
                break;
            }
        } while (true);
        return input;
    }

    public Date getDate(String msg) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat;
        String input;
        Date date;

        if (msg.isEmpty()) {
            System.out.print(msg);
        } else {
            System.out.println(msg);
        }

        //Loop until user enter date with format 
        do {
            input = sc.nextLine();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println("Input is empty.");
                continue;
            }

            //Check if input date was exactly a correct format date
            /*Regex
                \\d{1,2}: the number have 1 or 2 digit number
                [/]: input contain a character '/'
                \\d{4}: the number must have 4 digit number
             */
            if (!input.matches("\\d{1,2}[/]\\d{1,2}[/]\\d{4}")) {
                System.err.println("Input is wrong format date.");
                continue;
            }
            try {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                /*If user enter the date like 30/02/2021 (Only 28 days).
                It will be reported wrong and must be re-entered*/
                dateFormat.setLenient(false);
                date = dateFormat.parse(input);
                break;
            } catch (ParseException dateFormatException) {
                System.err.println("Input date is not existed!");
            }
        } while (true);
        return date;
    }
}
