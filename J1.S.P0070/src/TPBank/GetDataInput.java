/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPBank;

import java.util.Scanner;

/**
 *
 * @author phong
 */
public class GetDataInput {

    public int getInt(String msg, String outRangeMsg, String inputEmpty,
            int min, int max) {
        Scanner sc = new Scanner(System.in);
        double intInput;
        String input;

        //Loop until user enter positive number in range of min and max
        do {
            System.out.print(msg);
            input = sc.nextLine().trim();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println(inputEmpty);
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

    public String getString(String msg, String formatRequireMsg,
            String inputError, String regex) {
        Scanner sc = new Scanner(System.in);
        String input;

        //Loop until user enter string from the keyboard
        do {
            System.out.print(msg);
            input = sc.nextLine().trim();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println(inputError);
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

    public String getPassword(String msg, String err, String inputEmptyErr) {
        Scanner sc = new Scanner(System.in);
        String input;

        //Loop until user enter a password that contains a character and number
        do {
            System.out.print(msg);
            input = sc.nextLine().trim();
            int countLetter = 0;
            int countDigit = 0;

            //Check if user didn't enter anything from keyboard
            if (input.isEmpty()) {
                System.err.println(inputEmptyErr);
                continue;
            }

            //Loop through user's input string from the first to the last characters
            for (int i = 0; i < input.length(); i++) {
                //Check if characters is letter then increase countLetter
                if (Character.isLetter(input.charAt(i))) {
                    countLetter++;
                }
                //Otherwise, increase countDigit if a character is a number
                if (Character.isDigit(input.charAt(i))) {
                    countDigit++;
                }
            }
            if (countDigit > 0 && countLetter > 0 && input.length()
                    >= 8 && input.length() <= 31) {
                break;
            } else {
                System.err.println(err);
            }
        } while (true);
        return input;
    }
}
