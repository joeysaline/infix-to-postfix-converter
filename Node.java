/**
 * Represents a Node object using generic type.
 * 
 * @author Joey Saline
 * @version 1.2
 */
public class Node<T> {
  /**
   * Fields
   */
  private T element; // The element of this node.
  private Node left; // The left child node of this node.
  private Node right; // The right child node of this node.

  /**
   * Constructs a default Node.
   */
  public Node() {

    this.element = null;
    this.left = null;
    this.right = null;
  }

  /**
   * Constructs a new Node with one parameter.
   * 
   * @param element The element of this node.
   */
  public Node(T element) {

    this.element = element;
    this.left = null;
    this.right = null;
  }

  /**
   * Sets the element of this node.
   */
  public void setElement(T element) {

    this.element = element;
  }

  /**
   * Gets the element of this node.
   * 
   * @return The element of this node.
   */
  public T getElement() {

    return element;
  }

  /**
   * Sets the left child of this node.
   */
  public void setLeft(Node left) {

    this.left = left;
  }

  /**
   * Gets the left child of this node.
   * 
   * @return The left child of this node.
   */
  public Node getLeft() {

    return left;
  }

  /**
   * Sets the right child of this node.
   */
  public void setRight(Node right) {

    this.right = right;
  }

  /**
   * Gets the right child of this node.
   * 
   * @return The right child of this node.
   */
  public Node getRight() {

    return right;
  }

  /**
   * Converts a Node into a String representation.
   * 
   * @param root The reference to a Node object.
   * @return The element of the root Node of type String.
   */
  public String toString(Node root) {

    String nodeString = "This node contains: ";
    return nodeString += root.getElement();
  }

  /**
   * Checks to see if two Nodes are equal.
   * 
   * @param node The reference to a Node.
   * @return If the Node instance is equal to this Node.
   */
  public boolean equals(Node<T> node) {

    // base case
    if (node == null)
      return false;
    // test equivalency
    else {

      // test element
      try {
        if (!this.element.equals(node.getElement()))
          return false;
      } catch (NullPointerException e) {
        if (node.getElement() != null)
          return false;
      }

      // element is equal recursively test previous and next nodes
      // test previous
      try {
        if (!this.prev.equals(node.getPrev()))
          return false;
      } catch (NullPointerException e) {
        if (node.getPrev() != null)
          return false;
      }
      // test next
      try {
        if (!this.next.equals(node.getNext()))
          return false;
      } catch (NullPointerException e) {
        if (node.getNext() != null)
          return false;
      }
      return true;
    }
  }
}