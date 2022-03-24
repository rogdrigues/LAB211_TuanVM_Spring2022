/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0011;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author phong
 */
public class Convert {

    public static void main(String[] args) {
        //1. Required user choose the base number input
        int inputBase = enterInputBase();
        //2. Required user choose the base number output
        int outputBase = enterOutputBase(inputBase);
        //3. Required user enter the input value
        String inputValue = enterInputValue(inputBase);
        //4. Change base number from input to output after inputValue is valid
        String outputValue = convertBase(inputBase, outputBase, inputValue);
        //5. Display output value
        displayOutputValue(outputValue);
    }

    public static int enterInputBase() {
        Scanner sc = new Scanner(System.in);
        String input;
        double inputInteger;

        System.out.println("Enter input base: ");

        //Loop until user enter a base number with 2, 10 or 16
        do {
            input = sc.nextLine().trim();
            //If user don't input anything from keyboard
            if (input.isEmpty()) {
                System.err.println("Input is empty!");
                continue;
            }
            try {
                inputInteger = Double.parseDouble(input);

                //User enter a real number
                if ((int) inputInteger != inputInteger) {
                    throw new Error();
                }
                //User enter a number that is a base 2, 10 or 16
                if ((int) inputInteger == 2 || (int) inputInteger == 10
                        || (int) inputInteger == 16) {
                    return (int) inputInteger;
                } else {
                    throw new Exception();
                }
            } catch (NumberFormatException stringException) {
                System.err.println("Base cannnot be a string. Base must be 2, 10"
                        + " or 16. Please re-enter.");
                continue;
            } catch (Exception inputBaseException) {
                System.err.println("Base must be 2, 10 or 16. Please re-enter.");
                continue;
            } catch (Error realNumberException) {
                System.err.println("Base cannot be a realNumber. Base must be 2,"
                        + " 10 or 16. Please re-enter.");
                continue;
            }
        } while (true);
    }

    public static int enterOutputBase(int inputBase) {
        Scanner sc = new Scanner(System.in);
        String input;
        double inputInteger;

        System.out.println("Enter output base: ");

        /*Loop until user enter a base number with 2, 10 or 16 and diffrent than 
        input base*/
        do {
            input = sc.nextLine().trim();
            //If user don't input anything from keyboard            
            if (input.isEmpty()) {
                System.err.println("Ouput base's input is empty!");
                continue;
            }
            try {
                inputInteger = Double.parseDouble(input);

                //User enter a real number
                if ((int) inputInteger != inputInteger) {
                    throw new Error();
                }
                //User enter a number that duplicated with inputBase
                if ((int) inputInteger == inputBase) {
                    System.err.println(inputBase + " is input base. Please enter"
                            + "another output base.");
                    continue;
                }
                //User enter a number that is a base 2, 10 or 16
                if ((int) inputInteger == 2 || (int) inputInteger == 10
                        || (int) inputInteger == 16) {
                    return (int) inputInteger;
                } else {
                    throw new Exception();
                }
            } catch (NumberFormatException stringException) {
                System.err.println("Base cannnot be a string. Base must be 2, 10"
                        + " or 16. Please re-enter.");
                continue;
            } catch (Exception inputBaseException) {
                System.err.println("Base must be 2, 10 or 16. Please re-enter.");
                continue;
            } catch (Error realNumberException) {
                System.err.println("Base cannot be a realNumber. Base must be 2,"
                        + " 10 or 16. Please re-enter.");
                continue;
            }
        } while (true);
    }

    public static String enterInputValue(int inputBase) {
        Scanner sc = new Scanner(System.in);
        String inputValue;
        String regexBase = "";

        switch (inputBase) {
            case 2:
                /*Accept inputValue is a digit in the range 0 to 1 at 
                unlimited length*/
                /*Regex:
                [^]: Matches the beginning of the line
                [0-1]: Input matches a character in the range "0" to "1"
                [+]: Matches 1 or more
                [$]: Matches the end of the line
                */
                regexBase = "^[0-1]+$";
                break;
            case 10:
                //Accept inputValue is a digit from 0 to 9 at unlimited length
                /*Regex:
                [^]: Matches the beginning of the line
                [0-9]: Input matches a character in the range "0" to "9"
                [+]: Matches 1 or more
                [$]: Matches the end of the line
                */
                regexBase = "^[0-9]+$";
                break;
            case 16:
                /*Accept inputValue is a character in both uppercase and lowercase
                (from a-f and A-F) and a digit from 0 to 9 at unlimited length*/
                /*Regex
                [^]: Matches the beginning of the line
                [a-f]: Input matches a character in the range "a" to "f"
                [A-F]: Input matches a character in the range "A" to "F"
                [0-9]: Input matches a character in the range "0" to "9"
                */
                regexBase = "^[a-fA-F0-9]+$";
                break;
        }

        System.out.println("Enter value you want to change base: ");

        /*Loop until user enter a correct format inputValue dependent of 
        input base number*/
        do {
            inputValue = sc.nextLine().trim();
            //If user don't input anything from keyboard
            if (inputValue.isEmpty()) {
                System.err.println("Input is empty!");
                continue;
            } else {
                //Check if user's input value is a correct format by regex
                if (inputValue.matches(regexBase)) {
                    return inputValue.toUpperCase();
                } else {
                    System.err.println("Input value form is incorrect!");
                    continue;
                }
            }
        } while (true);
    }

    public static String convertBase(int inputBase, int outputBase,
            String inputValue) {
        String outputValue = "";

        //If input base is decimal (10) then convert from decimal to outputBase
        if (inputBase == 10) {
            outputValue = convertDecToOther(outputBase, inputValue);
        }//If output base is decimal (10), then convert from inputBase to decimal
        else if (outputBase == 10) {
            outputValue = convertOtherToDec(inputBase, inputValue);
        }/*If input base is binary (2) and output base is hexademical (16),
        convert from inputValue to decimal then convert to hexademicala*/ 
        else if (inputBase == 2) {
            outputValue = convertOtherToDec(2, inputValue);
            outputValue = convertDecToOther(16, outputValue);
        }/*Otherwise, convert input value with is hexademical to decimal then 
        convert result to binary*/ else {
            outputValue = convertOtherToDec(16, inputValue);
            outputValue = convertDecToOther(2, outputValue);
        }
        return outputValue;
    }

    public static String convertDecToOther(int outputBase, String inputValue) {
        BigInteger decimalValue = new BigInteger(inputValue);
        BigInteger zeroNumber = new BigInteger("0");
        BigInteger outputbase = new BigInteger(Integer.toString(outputBase));
        StringBuilder result = new StringBuilder();
        final String valueHex = "0123456789ABCDEF";

        //If input value is 0 then return output value is 0 
        if (decimalValue.equals(zeroNumber)) {
            return "0";
        }

        /*Loop until the decimal is smaller than base. After that, the decimal
        is divided by base equals to 0*/
        do {
            /*
            Step by step:
            Step 1: Take remainder of inputValue as(decimalValue % outputbase)
            Step 2: Check index of remainder in valueHex
            Step 3: Add remainder after check with hexValue to result
            Step 4: update decimal = decimal / outputbase
            */
            BigInteger remainder = decimalValue.remainder(outputbase);
            char hexValue = valueHex.charAt(remainder.intValue());
            result.append(hexValue);
            decimalValue = decimalValue.divide(outputbase);
        } while (!decimalValue.equals(zeroNumber));
        return result.reverse().toString();
    }

    public static String convertOtherToDec(int inputBase, String inputValue) {
        BigInteger result = new BigInteger("0");
        BigInteger baseInput = new BigInteger("0");
        BigInteger zeroNumber = new BigInteger("0");
        final String valueHex = "0123456789ABCDEF";

        //If input value is 0 then return output value is 0 
        if (inputValue.equals(zeroNumber)) {
            return "0";
        }

        /*Check if user want to convert from binary (2) to decimal(10) or 
        hexademical(16) to decimal(10)*/
        if (inputBase == 2) {
            baseInput = new BigInteger("2");
        } else {
            baseInput = new BigInteger("16");
        }

        /*Loop through every characters in inputValue from the last one to the
        first one*/
        for (int i = inputValue.length() - 1; i >= 0; i--) {
            /*
            Step by step:
            Step 1: Take one characters from the end of input value, then 
            convert to String
            Step 2: Make a BigInteger from the String converted after step 1
            Step 3: Multiply that BigInteger with the base^exponent (exponent 
            from the last index of inputValue to the first one)
            Step 4: Add all calculate of all step above to the result
            */
            String hexValue = valueHex.indexOf(inputValue.charAt(i)) + "";
            BigInteger position = new BigInteger(hexValue);
            BigInteger addValue = position.multiply(
                    baseInput.pow(inputValue.length() - 1 - i));
            result = result.add(addValue);
        }
        return result.toString();
    }

    public static void displayOutputValue(String outputValue) {
        System.out.println(outputValue);
    }
}
