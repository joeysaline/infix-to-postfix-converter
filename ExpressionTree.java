import java.util.Stack;
import java.util.StringTokenizer;
/**
 * Represents an Expression Tree object using Node objects.
 * @author Joey Saline
 * @version 1.0
 */
public class ExpressionTree{
  /**
   * Fields
   */
  private String infixExpression; // The infix expression of this Expression Tree.
  private String postfixExpression; // The postfix expression of this Expression Tree.
  private Node root; // The reference to the root Node of this Expression Tree.
  /**
   * Constructs a default Expression Tree.
   */
  public ExpressionTree(){
   
    this.infixExpression = null;
    this.postfixExpression = null;
    this.root = null;
  }
  /**
   * Constructs an Expression Tree with one parameter.
   * @param expression The algebraic expression in infix notation.
   */
  public ExpressionTree(String expression){
    this.infixExpression = expression;
    this.postfixExpression = infixToPostfix(infixExpression);
    this.root = buildTree(postfixExpression);
  }
  /**
   * Gets the infix expression of this Expression Tree.
   * @return The infix expression of this Expression Tree.
   */
  public String getInfixExpression(){
    
    return this.infixExpression;
  }
  /**
   * Gets the postfix expression of this Expression Tree.
   * @return The postfix expression of this Expression Tree.
   */
  public String getPostfixExpression(){
    
    return this.postfixExpression;
  }
  /**
   * Gets the root of this Expression Tree.
   * @return The root Node of this Expression Tree.
   */
  public Node getRoot(){
    
    return this.root;
  }
  /**
   * Converts the infix expression to a postfix expression.
   * @param expression The infix expression of this Expression Tree.
   * @return postfixExpression The postfix expression of this Expression Tree.
   */
  public String infixToPostfix(String infixExpression){
    
    // Initializers
    Stack s = new Stack(); // The reference to a Stack object.
    StringTokenizer strTok = new StringTokenizer(infixExpression, " "); // The reference to a String Tokenizer delimited by spaces.
    String buffer; // Buffer to hold tokens.
    postfixExpression = "";
    // Main loop that will iterate through each token of the infix expression.
    while (strTok.hasMoreTokens()){
     
      // Store the token in the buffer.
      buffer = strTok.nextToken();
      //System.out.println("There are more tokens, next token: " + buffer);
      
      // Main algorithm using the buffer to convert to postfix
      // if operand
      if (isOperand(buffer)){
        
        postfixExpression += (buffer + " ");
        //System.out.println("An operand was appended.");
      }
      // if opening parenthesis
      else if (buffer.charAt(0) == '('){
        
        s.push(buffer);
        //System.out.println("Pushed: (");
      }
      // if closing parenthesis
      else if (buffer.charAt(0) == ')'){
       
        while (s.peek().toString().charAt(0) != '('){
         
          //System.out.println("Popped: " + s.peek().toString());
          postfixExpression += (s.pop() + " ");
        }
        // discard opening parenthesis
        s.pop();
        //System.out.println("Popped and discarded: (");
      }
      // if operator
      else {
       
        while (!s.isEmpty() && (s.peek().toString().charAt(0) != '(') && (checkPrecedence(buffer.charAt(0)) <= checkPrecedence(s.peek().toString().charAt(0)))){
        
          //System.out.println("Popped: " + s.peek().toString());
          postfixExpression += (s.pop() + " ");
        }
        //System.out.println("Pushed: " + buffer);
        s.push(buffer);
      }
    } // End of main loop
    // No more tokens remain
    // Pop all operators off stack
    //System.out.println("No more tokens. Begin popping stack.");
    while(!s.isEmpty()){
     
      //System.out.println("Popped: " + s.peek().toString());
      postfixExpression += (s.pop() + " ");
    }

    return postfixExpression;
  }
  /**
   * Determines if the item in question is an operand to be used in infix to postfix algorithm.
   * @param item The token from the infix expression.
   * @return A boolean value.
   */
  public boolean isOperand(String item){
   
    try {
      int integer = Integer.parseInt(item);
      return true;
    } catch (NumberFormatException e){
      return false;
    }
  }
  /**
   * Checks the precedence of the operator to be used in infix to postfix algorithm.
   * @param operator The operator from the infix expression.
   * @return An integer representing the precedence level of the operator.
   */
  public int checkPrecedence(char operator){
   
    switch (operator){
      case '*':
        return 3;
      case '/':
        return 3;
      case '+':
        return 2;
      case '-':
        return 2;
      default:
        return 1;
    }
  }
  /**
   * Builds the Node part of Expression Tree.
   * @param postfixExpression The postfix expression of this Expressoin Tree.
   * @return The reference to the root Node of this Expression Tree.
   */
  public Node buildTree(String postfixExpression){
    
    // Initializers
    Stack<Node> s = new Stack<Node>(); // The reference to a Stack object of type Node.
    StringTokenizer strTok = new StringTokenizer(postfixExpression, " "); // The reference to a String Tokenizer delimited by spaces.
    String buffer; // Buffer to hold tokens.
    Node rootNode = new Node();
    // buildtree
    while (strTok.hasMoreTokens()){
     
      buffer = strTok.nextToken();
      
      if (isOperand(buffer)){
        
        Node operandNode = new Node(buffer);
        s.push(operandNode);
      }
      else{
        
        Node operatorNode = new Node(buffer);
        Node tree2 = new Node();
        tree2 = s.pop();
        Node tree1 = new Node();
        tree1 = s.pop();
        operatorNode.setLeft(tree1);
        operatorNode.setRight(tree2);
        s.push(operatorNode);
        rootNode = operatorNode;
      }
    }
    
    return rootNode;
  }
  /**
   * Traverses the Expression Tree and prints the traversal in preorder notation.
   * @param root The root Node of this Expression Tree.
   * @return The preorder notation traversal of this Expression Tree.
  */
  public String preorderTraversal(Node root){
    
    String traversal = "";
    if (root == null){
     
      return traversal;
    }
    else {
     
      traversal += root.getElement();
      traversal += preorderTraversal(root.getLeft());
      traversal += preorderTraversal(root.getRight());
    }
    return traversal;
  }
  /**
   * Traverses the Expression Tree and prints the traversal in inorder notation.
   * @param root The root Node of this Expression Tree.
   * @return The inorder notation traversal of this Expression Tree.
   */
  public String inorderTraversal(Node root){
    
    String traversal = "";
    if (root == null){
     
      return traversal;
    }
    else {
     
      traversal += inorderTraversal(root.getLeft());
      traversal += root.getElement();
      traversal += inorderTraversal(root.getRight());
    }
    return traversal;
  }
  /**
   * Traverses the Expression Tree and prints the traversal in postorder notation.
   * @param root The root Node of this Expression Tree.
   * @return The postorder notation traversal of this Expression Tree.
   */
  public String postorderTraversal(Node root){
    
    String traversal = "";
    if (root == null){
     
      return traversal;
    }
    else {
      
      traversal += postorderTraversal(root.getLeft());
      traversal += postorderTraversal(root.getRight());
      traversal += root.getElement();
    }
    return traversal;
  }
  /**
   * Converts the Expression Tree into a String representation.
   * @return The String that represents this Expression Tree.
   */
  public String toString(){
   
    String treeString = "";
    treeString += getClass().getSimpleName();
    treeString += " : ";
    treeString += infixExpression;
    treeString += "\nTraversals\npreorder: ";
    treeString += preorderTraversal(this.root);
    treeString += "\ninorder: ";
    treeString += inorderTraversal(this.root);
    treeString += "\npostorder: ";
    treeString += postorderTraversal(this.root);
    return treeString;
  }
  /**
   * Determines if two Expression Trees are equal.
   * @param expressionTree The reference to an Expression Tree to be compared.
   * @return If the Expression Trees are equal or not.
   */
  public boolean equals(ExpressionTree expressionTree){
   
    if (this.root.equals(expressionTree.getRoot()))
      return true;
    else
      return false;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
}