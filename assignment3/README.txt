written.pdf
        Has the written part of the hw assignment


ExpressionTree.java
        Class ExpressionTree where I make a stack from theclass MyStack, check if the node is an operator or operand.  If operand then create a one node tree and push onto stack, if operetor then pop two trees from stack, form a new tree whose root is operator and children are the popped trees.  Also an error check is that if you cant pop the stack twize tehn the user input an incorrect postfix expression.
        Eval method evaluates the expressionTree
        Postfix, prefix, and infix recursively produce the expressions from the tree.
        There is an ExpressionNodes class which stores the element, the left node and right node.


MyStack.java
        Used from the solutions to HW2


Problem1.java
        Puts in postfix string and makes an ExpressionTree from it.  Evaluates it using eval method.  Then uses the tree and gets the postfix, infix, and prefix expression from it.


AvlTree.java
        Edited the class so that it isnt of type AnyType, instead of type String.  Also updated all methods so that LinkedList<Integer> y was included as a parameter, becasue in the AvlNode class I needed to add LinkedList<Integer> theOther as an argument.  Method rep calls the method replace on the String x, which is the word, and Integer z which is the line number.  It finds the node that you need to update, then updates the list to add the line number to the linked list (if it isn’t a repeat).  Also in the contains method I pulled the current node that equals the node that is already there, and set it to accessNode.  This was what I use in the returnList function I made to call getList() on (a method inside AvlNode that converts the linked list to an array and to string so it can print) 


UnderFlowException.java
        Included, not edited.


Problem2.java
        Main method opens the file and reads it, uses a while loop to go line by line while there still are lines. In loop splits the string and uses method “clean” which removes punctuation and capitalization.  Then uses for loop to call indexWord method adds the word to the tree. Then it calls printIndex() which prints each node word with the list of line numbers.
**Problem2 takes awhile to run since there are so many words in the frank.txt file, I shortened it down and I am not sure why it is not running 100% correctly.  It seems like the contains method sometimes works and sometimes does not work which is strange.


I included the frank.txt in my folder, it needs to be in the same folder as the project but not in src.
