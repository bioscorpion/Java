package Class2_Recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class _RecursionProblems {

    public static final void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static final void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void main(String [] args) {
        _RecursionProblems myRecursionProblems = new _RecursionProblems();
        //List<String> hello = myCRecursionProblems.driverLetterCase("t1D2");   // DONE.
        //myRecursionProblems.driverPrintDecimalStrings(2); // DONE.

        int[] myArray = {1, 2, 3, 4};
        /*swap(myArray, 1,2);
        for (int i : myArray) {
            System.out.println(i);
        }*/
        List<String> hello = myRecursionProblems.driverPowerset(myArray);// DONE.
        for (String s : hello) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println("hello.size() =  " + hello.size());

        String[] myArray2 = {"H", "A", "T"};
        //myRecursionProblems.driverGeneratePermutationsOrderMatters(myArray2); // DONE.

        String[] myArray3 = {"H", "A", "T", "T"};
        //myRecursionProblems.driverGeneratePermutationsWithDuplicates(myArray3); // DONE.

        //List<String> myNumbers = myRecursionProblems.driverBacktrackSubsetSize(4, 2); //DONE.
        //System.out.println(myNumbers);

        int[] myNumbers2 = {1, 2, 3, 4, 5};
        //myRecursionProblems.driverBacktrackSubsetSum(myNumbers2, 11); // DONE.
        //myRecursionProblems.driverBackTrackDiceRollSum(2, 11); // DONE.
        //myRecursionProblems.driverBackTrackGenerateParentheses(2); // DONE>
    }


    // Backtrack Problem - Generate Parentheses
    void driverBackTrackGenerateParentheses(int num) {
        helperBackTrackGenerateParentheses(num, num, new StringBuilder());
    }
    void helperBackTrackGenerateParentheses(int ropen, int rclose, StringBuilder soFar /* slate */) {
        //backtracking case
        if(ropen > rclose) return;

        // base case
        if(ropen == 0 && rclose == 0) {
            System.out.println(soFar.toString());
        }
        else {
            if (ropen > 0) {
                soFar.append("(");
                helperBackTrackGenerateParentheses(ropen - 1, rclose, soFar);
                soFar.deleteCharAt(soFar.length() - 1);
            }

            if (rclose > 0) {
                soFar.append(")");
                helperBackTrackGenerateParentheses(ropen, rclose - 1, soFar);
                soFar.deleteCharAt(soFar.length() - 1);
            }
        }
    }



    // Backtrack Problem - Dice Roll Sum ===> Subset not permutation.
    void driverBackTrackDiceRollSum(int numDice, int targetSum) {
        helperBackTrackDiceRollSum(numDice, targetSum, 0, new StringBuilder());
    }
    void helperBackTrackDiceRollSum(int numDice, int targetSum, int runningSum, StringBuilder soFar /* slate */) {
        // backtracking case 1
        if (runningSum > targetSum) return;

        // backtracking case 2 (specific optimization to this Dice Problem)
        // numDice * 6 + runningSum ===> Maximum Sum Value Can Have
        // numDice * 1 + runningSum ===> Minimum Sum Value Can Have
        if ((numDice * 6 + runningSum < targetSum) ||
                (numDice * 1 + runningSum > targetSum)) {
            return;
        }

        // base case
        if (numDice == 0) {
            if (targetSum == runningSum) {
                System.out.println(soFar.toString());
            }
        }
        // else case
        else {
            for (int val = 1; val <= 6; val++) {
                soFar.append(val);
                // This is where runningSum being set on next call to itself. Depth First Search (DFS).
                helperBackTrackDiceRollSum(numDice - 1, targetSum, runningSum + val, soFar);
                soFar.deleteCharAt(soFar.length() - 1);
            }
        }
    }


    // Backtrack Problem Subset Sum - Depth First Search Implementation (DFS). All these different methods in this file uses DFS.
    /* A thread-safe, mutable sequence of characters. A string buffer is like a String, but can be modified.
        At any point in time it contains some particular sequence of characters, but the length and content
        of the sequence can be changed through certain method calls. */
    void driverBacktrackSubsetSum(int[] numbers, int targetSum) { // ts = target sum
        helperBacktrackSubsetSum(numbers, 0, new StringBuffer(), targetSum, 0 );
    }
    void helperBacktrackSubsetSum(int[] numbers, int idx, StringBuffer soFar, int targetSum, int runningSum) {
        // backtracking case 1
        if (runningSum > targetSum) {
            return;
        }

        // backtracking case 2
        if (runningSum == targetSum) {
            System.out.println(soFar.toString()); // print the two numbers that add up to targetSum
            return;
        }

        // base case
        if (idx == numbers.length) {
            return;
        }
        else {
            helperBacktrackSubsetSum(numbers, idx + 1, soFar, targetSum, runningSum); // exclude
            soFar.append(numbers[idx]); // include
            helperBacktrackSubsetSum(numbers, idx + 1, soFar, targetSum, runningSum + numbers[idx]);
            soFar.deleteCharAt(soFar.length() - 1);
        }
    }


    // Backtrack Problem Subset Size Given two integers n and k, return all possible combinations of k numbers out of 1 ... n. https://leetcode.com/problems/combinations/
    List<String> driverBacktrackSubsetSize(int n, int k) { // ts = target sum
        //helperBacktrackSubsetSize(n, k, 0, new ArrayList<Integer>(), new ArrayList<Integer>() );

        ArrayList<String> coll  = new ArrayList<String>();
        helperBacktrackSubsetSize(n, k, 0, new StringBuffer(), coll);

        return coll;
    }
    //void helperBacktrackSubsetSize(int n, int k, int idx, List<Integer> soFar, List<Integer> coll) {
    void helperBacktrackSubsetSize(int n, int k, int idx, StringBuffer soFar, List<String> coll) {
        // backtracking case
        if (soFar.length() == k) {
            coll.add(soFar.toString());
            return;
        }

        // base case
        if (idx > n) {
            return;
        }
        else {
            helperBacktrackSubsetSize(n, k, idx + 1, soFar, coll); // exclude
            soFar.append(idx);      // include
            helperBacktrackSubsetSize(n, k, idx + 1, soFar, coll);
            soFar.deleteCharAt(soFar.length() - 1);
        }
    }



    // Generate Permutations With Duplicates
    void driverGeneratePermutationsWithDuplicates(String[] input) {
        helperGeneratePermutationsWithDuplicates(input, 0, new StringBuilder());
    }
    void helperGeneratePermutationsWithDuplicates(String[] input, int idx, StringBuilder soFar) {
        //base case
        if (idx == input.length) {
            System.out.println(soFar.toString());
        }
        else {
            Set<String> cache = new HashSet<String>();
            for (int i = idx ; i < input.length ; i++) {    // Covers all digits/characters at each level. At each level start index increases because there are less characters available.
                if (cache.contains(input[i])) {
                    continue;
                }
                cache.add(input[i]);

                swap(input, idx, i);
                soFar.append(input[idx]);
                //System.out.println("soFar=====" + soFar.toString() + " idx=====" + idx + " i=====" + i);    // educational purpose only
                helperGeneratePermutationsWithDuplicates(input, idx+1, soFar);  // Goes to the next level/node down the tree. Depth First Search
                soFar.deleteCharAt(soFar.length()-1);
                swap(input, idx, i);    // restore original state.
            }
        }
    }


    // Generate Permutations Order Matters
    void driverGeneratePermutationsOrderMatters(String[] input) {
        helperGeneratePermutationsOrderMatters(input, 0, new StringBuilder());
    }
    void helperGeneratePermutationsOrderMatters(String[] input, int idx, StringBuilder soFar) {
        //base case
        if (idx == input.length) {
            System.out.println(soFar.toString());
        }
        else {
            for (int i = idx ; i < input.length ; i++) {    // Covers all digits/characters at each level. At each level start index increases because there are less characters available.
                swap(input, idx, i);
                soFar.append(input[idx]);
                System.out.println("soFar=====" + soFar.toString() + " idx=====" + idx+ " i=====" + i);    // educational purpose only
                helperGeneratePermutationsOrderMatters(input, idx+1, soFar); // Goes to the next level/node down the tree. Depth First Search
                soFar.deleteCharAt(soFar.length()-1);
                swap(input, idx, i);    // restore original state.
            }
        }
    }


    // All Subsets aka Powerset
    List<String> driverPowerset(int[] nums) {
        List<String> coll = new ArrayList<>();
        helperPowerset(nums, 0, new StringBuilder(), coll);

        return coll;
    }
    void helperPowerset(int[] nums, int idx, StringBuilder soFar, List<String> coll) {
        // base
        if (idx == nums.length) {
            coll.add(soFar.toString());
        }
        else {
            // exclude
            helperPowerset(nums, idx+1, soFar, coll);
            // include
            soFar.append(nums[idx]);
            helperPowerset(nums, idx+1, soFar, coll);
            soFar.deleteCharAt(soFar.length() - 1);
        }
    }


    // Print Decimal Strings
    public void driverPrintDecimalStrings(int numDigit) {
        helperPrintDecimalStrings(numDigit, new StringBuilder());
    }
    public void helperPrintDecimalStrings (int numDigit, StringBuilder soFar) {
        // base case
        if (numDigit == 0) {
            System.out.println(soFar.toString());
        }
        else {
            for (int choice = 0 ; choice <=9 ; choice++) {
                soFar.append((choice));
                helperPrintDecimalStrings(numDigit - 1, soFar);
                soFar.deleteCharAt(soFar.length() - 1 );
            }
        }
    }


    // Letter Case Permutation
    public List<String> driverLetterCase(String input) {
        List<String> coll = new ArrayList<String>();
        helperLetterCase(input, 0, new StringBuilder(), coll);

        return coll;
    }
    // t1d2 (0....3)
    public void helperLetterCase(String input, int idx, StringBuilder soFar /* slate */, List<String> coll) {
        // base case
        if (idx == (input.length())) {
            coll.add(soFar.toString());
        }
        else {
            char currChar = input.charAt(idx);
            if (Character.isDigit(currChar)) {   // digit
                soFar.append((currChar));
                helperLetterCase(input, idx + 1, soFar, coll);
                soFar.deleteCharAt(soFar.length()-1);
            }
            else { // letter
                soFar.append(Character.toLowerCase(currChar));
                helperLetterCase(input, idx + 1, soFar, coll);
                soFar.deleteCharAt(soFar.length()-1);
                soFar.append(Character.toUpperCase(currChar));
                helperLetterCase(input, idx + 1, soFar, coll);
                soFar.deleteCharAt(soFar.length()-1);
            }
        }
    }

}
