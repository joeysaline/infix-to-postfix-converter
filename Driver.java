import java.util.*;
import java.io.*;
/**
 * Represents the main driver program
 * @author Joey Saline
 * @version 1.0
 */
public class Driver{

  public static void main(String[] args) throws IOException{
    
    String filename = "test-data.txt";
    BufferedReader br = new BufferedReader(new FileReader(filename));
    String buffer = br.readLine();
    while (buffer != null){
      
      ExpressionTree tree = new ExpressionTree(buffer);
      System.out.println(tree.toString() + "\n");
      buffer = br.readLine();
    }
    ExpressionTree tree1 = new ExpressionTree("( 1 + 2 )");
    ExpressionTree tree2 = new ExpressionTree("( 1 + 2 )");
    ExpressionTree tree3 = new ExpressionTree("( 4 + ( 7 * 3 ) * 389 )");
    System.out.println("tree1 is: " + tree1.getInfixExpression());
    System.out.println("tree2 is: " + tree2.getInfixExpression());
    System.out.println("tree3 is: " + tree3.getInfixExpression());
    System.out.println("tree1 is equal to tree2: " + tree1.equals(tree2));
    System.out.println("tree1 is equal to tree3: " + tree1.equals(tree3));
  }
}
