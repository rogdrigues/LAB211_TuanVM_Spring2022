/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0052;

import java.util.Scanner;

/**
 *
 * @author phong
 */
public class GetDataInput {

    public int getInt(String msg, int min, int max) {
        Scanner sc = new Scanner(System.in);
        double intInput;
        String input;

        System.out.println(msg);
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
                    return (int) intInput;
                }
                //User enter a string
            } catch (NumberFormatException StringInput) {
                System.err.println("Input cannot be a String.");
                continue;
            } catch (Exception RealNumber) {
                System.err.println("Input cannot be a real number.");
                continue;

            } catch (Error OutOfRange) {
                System.err.println("Input is out of range: [" + min + "], "
                        + "[" + max + "]");
                continue;
            }
        } while (true);
    }

    public String getString(String msg) {
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println(msg);
        //Loop until user enter string from the keyboard
        do {
            input = sc.nextLine().trim();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println("Input is empty.");
                continue;
            } /*[a-zA-Z ]+: Matches any single character from a-z, A-Z and space 
            character in brackets*/ else if (!input.matches("[a-zA-Z ]+")) {
                System.err.println("Input cannot be a number or special "
                        + "character");
                continue;
            } else {
                return input;
            }
        } while (true);
    }

    public float getFloat(String msg) {
        Scanner sc = new Scanner(System.in);
        float floatInput;
        String input;

        System.out.println(msg);
        //Loop until user enter a real number from the keyboard
        do {
            input = sc.nextLine().trim();

            //User didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.err.println("Input is empty.");
                continue;
            }
            try {
                floatInput = Float.parseFloat(input);
                return floatInput;
            } catch (NumberFormatException StringInput) {
                System.err.println("Input cannot be a String.");
                continue;
            }
        } while (true);
    }
}
