package bubblesort;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author phong
 */
public class BubbleSort {

    public static void main(String[] args) {
        //Step 1. Users input the positive number size of array
        int size_array = InputPositiveNumber();
        //Step 2. Create array by generate random integer in range input
        int[] array = GenArray(size_array);
//        int[] array = {5, 1, 12, -5, 16};  //Test Case
        //Step 3. Display unsorted array
        Display("Unsorted array: ", array);
        //Step 4. BubbleSort to sort array
        BubbleSort(array);
        //Step 5. Display sorted array
        Display("Sorted array: ", array);
    }

    public static int InputPositiveNumber() {
        Scanner sc = new Scanner(System.in);
        double size_array;
        String input;

        //Loop until user enter positive number
        do {
            System.out.println("Enter number of array: ");
            input = sc.nextLine().trim();
            //Users didn't enter anything from the keyboard
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter again.");
                continue;
            }
            try {
                size_array = Double.parseDouble(input);
                //Users enter a number that less than zero.
                if (size_array <= 0) {
                    throw new Error();
                }
                //Users enter a real number. If not, return a integer number.
                if ((int) size_array != size_array) {
                    throw new Exception();
                } else {
                    return (int) size_array;
                }
                //User enter a string from the keyboard.
            } catch (NumberFormatException StringInput) {
                System.out.println("Input cannot be a String. Please enter"
                        + " again.");
                continue;
            } catch (Error NegativeNumber) {
                System.out.println("Input cannot less than zero. Please enter"
                        + " again.");
                continue;
            } catch (Exception RealNumber) {
                System.out.println("Input cannot be a real number. Please "
                        + "enter again.");
                continue;
            }
        } while (true);
    }

    public static int[] GenArray(int size_array) {
        int[] array = new int[size_array];
        Random rd = new Random();

        //Loop use to generate random any integer elements in range
        for (int i = 0; i < size_array; i++) {
            array[i] = rd.nextInt(size_array);
        }
        return array;
    }

    public static void Display(String msg, int[] array) {
        int arraySize = array.length;
        System.out.print(msg);
        System.out.print("[");

        for (int i = 0; i < arraySize; i++) {
            System.out.print(array[i]);
            //if this element is not the last then print ','
            if (i < arraySize - 1) {
                System.out.print(", ");
            }
        }
        //Close square brackets after display unsorted or sorted
        if (msg.equals("Unsorted array: ")) {
            System.out.println("]");
        } else {
            System.out.print("]");
        }
    }

    public static void BubbleSort(int[] array) {
        int arrayLength = array.length;
        boolean check = true;

//        DisplayArrayTestCase(array, "unsorted\n");
        /*.First loop use to accessed from first element to the element 
        before the last element from an array. After each loop, one element is 
        sorted.*/
        for (int i = 0; i < arrayLength - 1; i++) {
            /*Second loop use to accessed first unsorted element to the element 
            stand before the last unsorted element*/
            for (int j = 0; j < arrayLength - i - 1; j++) {
                /*If current element greater than next element then swap 
                these two numbers*/
                if (array[j] > array[j + 1]) {
                    //Display each step in sorting process in test case
//                    if (check) {
//                        Display("", array);
//                        System.out.println("    " + array[j] + " > "
//                                + array[j + 1] + ", " + "swap");
//                    }
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                } //else if (check) {
                    //Display each step in sorting process in test case
//                    Display("", array);
//                    System.out.println("    " + array[j] + " < "
//                            + array[j + 1] + ", " + "ok");
//                }
            }
            //Print newline after each loop
//            System.out.println();
        }
//        DisplayArrayTestCase(array, "sorted");
    }

//    public static void DisplayArrayTestCase(int[] array, String msg) {
//        Display("", array);
//        System.out.println("    " + msg);
//    }
}
