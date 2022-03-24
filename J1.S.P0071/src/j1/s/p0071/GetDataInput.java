/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0071;

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

        //Loop until user enter positive number in range of min and max
        do {
            System.out.print(msg);
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

    public String getString(String msg, String formatRequireMsg, String regex) {
        Scanner sc = new Scanner(System.in);
        String input;

        //Loop until user enter string from the keyboard
        do {
            System.out.print(msg);
            input = sc.nextLine().trim();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println("Input is empty.");
            } else {
                //If regex is empty
                if (regex.isEmpty()) {
                    break;
                }//If input matches regex then break to return string
                else if (input.matches(regex)) {
                    break;
                }//Otherwise, re-input if user's input doesn't matches with regex
                else {
                    System.err.println(formatRequireMsg);
                }
            }
        } while (true);
        return input;
    }

    public String getDate(String msg) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat;
        String input, resultDate;
        Date date;

        //Loop until user enter date with format 
        do {
            System.out.print(msg);
            input = sc.nextLine();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println("Input is empty.");
                continue;
            }

            //Check if input date was exactly a correct format date
            /*Regex
                \\d{1,2}: the number have 1 or 2 digit number
                [-]: input contain a character '-'
                \\d{4}: the number must have 4 digit number
             */
            if (!input.matches("\\d{1,2}[-]\\d{1,2}[-]\\d{4}")) {
                System.err.println("Input is wrong format date.");
                continue;
            }
            try {
                dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                /*If user enter the date like 30/02/2021 (Only 28 days).
                It will be reported wrong and must be re-entered*/
                dateFormat.setLenient(false);
                date = dateFormat.parse(input);
                resultDate = dateFormat.format(date);
                break;
            } catch (ParseException dateFormatException) {
                System.err.println("Input date is not existed!");
            }
        } while (true);
        return resultDate;
    }

    public double getDouble(String msg, String outRangeMsg, double min, double max) {
        String input;
        double doubleInput;

        //Loop until user enter a real number in range
        do {
            /*
            [\\d]: Input matches a character in range [0-9]
            [+]: Matches 1 or more
            [^]: Matches the beginning of the line
            [$]: Matches the end of the line
            ([50]): Input matches one character "5" or "0"
            [.]: contains a dot
             */
            System.out.print(msg);
            input = getString("", "Start time must in format h.0 or h.5!",
                    "^(\\d+[.]([50]))$");
            //Users didn't enter anything from the keyboard            
            if (input.isEmpty()) {
                System.err.println("Input is empty");
                continue;
            }
            try {
                doubleInput = Double.parseDouble(input);

                //User enter a real number out of range
                if (doubleInput < min || doubleInput > max) {
                    System.err.println(outRangeMsg);
                } else {
                    return doubleInput;
                }
            } catch (NumberFormatException StringException) {
                System.err.println("Input cannot be a String or special "
                        + "character!");
                continue;
            }
        } while (true);
    }

    String GetTaskType(String msg) {
        int tasktypeID = getInt(msg, "Task Type must be in range [1-4]", 1, 4);
        String result = "";
        switch (tasktypeID) {
            case 1:
                result = "Code";
                break;
            case 2:
                result = "Test";
                break;
            case 3:
                result = "Design";
                break;
            case 4:
                result = "Review";
                break;
        }
        return result;
    }
}
