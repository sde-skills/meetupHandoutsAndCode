// package whatever; // don't place package name!

import java.io.*;
import java.util.*;

class MyCode {
	public static void main (String[] args) {
		System.out.println("Hello Java");
    final List<String> values = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    buildParenthisis(3, 0, 0, values, stack);
    System.out.println(values);
	}
  
  private static void buildParenthisis(int n, int startIndex, int endIndex, 
                    List<String> values, Stack<String> stack) {
      if (startIndex == n && endIndex == n) {
        values.add(stack.toString());
        return;
      }
      
      if (startIndex < n) {
        stack.push("(");
        buildParenthisis(n, startIndex + 1 , endIndex, values, stack);
        stack.pop();
      }
      
      if (endIndex < startIndex) {
        stack.push(")");
        buildParenthisis(n, startIndex, endIndex + 1, values, stack);
        stack.pop();
      }
  }
}

/*
(())

- (
     - ((
         - (()
            (()) --> 
  -()  
      - ()() -->       
      
      */ 
          
       //[2, 0, 0]
      
        






// Your last C/C++ code is saved below:
// #include <iostream>
// using namespace std;

// int main() {
// 	cout<<"Hello";
// 	return 0;
// }

/*

Question. 
We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.
We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.
For some 1 <= K <= 6, there is exactly one lowercase and one
 uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.
Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

// @ A . .  a
E.g. 1
@ . a . #
# # # . #
b . A . B

Output: 8

E.g. 2
@  .  .  a  A
.  .  B  #  .
.  .  .  .  b

Output: 6

int starIndex  =0
int endIndex = 0



while (endIndex < 0) {
  
  
}

Question 2: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]

n = 1
()

n = 2
(())  ()()
 
 
      (
    (   ) [((,   ()]
  ( )  ( )  [ (((, ((), ()(, ())   ]
   
*/
