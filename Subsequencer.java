package a09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program uses a recursive method to find all subsequences of a line
 * of numbers entered by the user. The program includes methods to prompt 
 * for user input of numbers, and a recursive method to find all subsequences
 * of the numbers entered by the user. These methods are described in further
 * detail in the code below.
 *
 * @author Riley OKeefe
 */
public class Subsequencer {

    public static final Scanner KBD = new Scanner(System.in);

    public static void main(String[] args) {
        introduction();
        prompt();
    }

    /**
     * This method prompts for user input of a line of numbers. If the 
     * line is empty the program terminates, else if any element in the
     * user input is not an integer the method prints out a message telling
     * the user what part of the input was wrong and skips over the incorrect
     * element.
     */
    public static void prompt() {
        String line = " ";
        List<Integer> orig = new ArrayList<>();

        while (!line.isEmpty()) {
            System.out.print("\nEnter more numbers >>>");
            line = KBD.nextLine();
            Scanner input = new Scanner(line);
            while (input.hasNext()) {
                if (input.hasNextInt()) {
                    orig.add(input.nextInt());
                } else if (!input.hasNextInt()) {
                    String notInt = input.next();
                    System.out.println("Skipped " + notInt 
                                                  + " -- not an int");
                    if (input.hasNextInt()) {
                        orig.add(input.nextInt());
                    }
                }
            }
            if (!orig.isEmpty()) {
                System.out.println("\nThe subsequences of " + orig 
                                                            + " are:");
                for (List<Integer> i : findSubsequences(orig)) {
                    System.out.println("\t- " + i);
                }
            } else if (line.isEmpty()) {
                System.out.println("\nGood-bye!");
            }

        }
    }

    /**
     * This method finds all subsequences of a line of numbers entered by
     * the user. If the given list is empty a list of the empty list is 
     * returned, otherwise the method follows these steps: 
     *  1. takes an element out of the given list. 
     *  2. finds all subsequences of the reduced list. 
     * For each of those subsequences in step 2: 
     *  3. they are added to the list of subsequences. 
     *  4. a copy is made for the subsequence. 
     *  5. the removed element in step 1 is added to the copy. 
     *  6. the modified copy is added to the list of subsequences. 
     *  7. finally the list of subsequences is returned.
     *
     * @param list the given list of integers
     * @return a list of the empty list if the given list is empty, OR a
     * list of the subsequences otherwise.
     */
    public static List<List<Integer>> findSubsequences(List<Integer> list) {
        List<List<Integer>> listOfList = new ArrayList<>();
        List<List<Integer>> restOf;
        List<Integer> emptyList = new ArrayList<>();

        if (list.isEmpty()) {
            listOfList.add(emptyList);
            return listOfList;
        } else {
            int removed = list.remove(0);
            
            restOf = findSubsequences(list);

            for (List<Integer> subsequence : restOf) {
                listOfList.add(subsequence);
                List<Integer> copy = new ArrayList<>(subsequence);
                copy.add(0, removed);
                listOfList.add(copy);
            }
            return listOfList;
        }

    }

    /**
     * This method prints out an introduction, which states what the program
     * does and how it works, along with the name of the author.
     */
    public static void introduction() {
        System.out.print("Subsequence Calculator\n"
                + "----------------------\n\n"
                + "By Riley OKeefe \n\n"
                + "This program calculates all subsequences "
                + "of a line of numbers you enter.\n"
                + "Enter an empty line to end the program.");
        System.out.println();
    }

}
