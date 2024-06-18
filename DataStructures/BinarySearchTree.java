public class BinarySearchTree {

    private Node root;

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        System.out.println(binarySearchTree.search(12));
        binarySearchTree.add(10);
        binarySearchTree.add(5);
        binarySearchTree.add(8);
        binarySearchTree.add(15);
        binarySearchTree.add(12);
        binarySearchTree.add(13);
        binarySearchTree.add(19);
        binarySearchTree.add(17);
        binarySearchTree.add(20);
        System.out.println(binarySearchTree.getRoot().id);
        System.out.println(binarySearchTree.search(5));
        System.out.println(binarySearchTree.search(10));
        System.out.println(binarySearchTree.search(12));
        binarySearchTree.delete(15);
        System.out.println(binarySearchTree.search(15));
    }

    // add
    public void add(int newValue) {
        if (this.root == null) {
            this.root = new Node();
            this.root.id = newValue;
        } else {
            recursivelyAdd(root, newValue);
        }

    }

    public Node recursivelyAdd(Node currentNode, int newValue) {
        if (currentNode == null) {
            currentNode = new Node();
            currentNode.id = newValue;
            return currentNode;
        }

        if (newValue <= currentNode.id) {
            // assign to left
            currentNode.leftChild = recursivelyAdd(currentNode.leftChild, newValue);
        } else {
            // assign to right
            currentNode.rightChild = recursivelyAdd(currentNode.rightChild, newValue);
        }
        return currentNode;
    }

    public boolean search(int value) {
        return recursiveSearch(root, value) != null;
    }

    public Node recursiveSearch(Node currentNode, int value) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.id == value) {
            return currentNode;
        }

        if (value < currentNode.id) {
            return recursiveSearch(currentNode.leftChild, value);
        } else if (value > currentNode.id) {
            return recursiveSearch(currentNode.rightChild, value);
        }
        return null;
    }

    public void delete(int value) {
        recursiveDelete(this.root, value);
    }

    public Node recursiveDelete(Node currentNode, int value) {
        if (currentNode == null) {
            return null;
        }

        if (currentNode.id != value) {
            if (value < currentNode.id) {
                currentNode.leftChild = recursiveDelete(currentNode.leftChild, value);
            } else if (value > currentNode.id) {
                currentNode.rightChild = recursiveDelete(currentNode.rightChild, value);
            }
        } else {
            if (currentNode.leftChild == null && currentNode.rightChild == null) {
                currentNode = null;
            } else if (currentNode.leftChild == null && currentNode.rightChild != null) {
                // make right child the current node
                currentNode = currentNode.rightChild;
            } else if (currentNode.leftChild != null && currentNode.rightChild == null) {
                // make left child the current node
                currentNode = currentNode.leftChild;
            } else {
                Node minimumNode = getMinimumNode(currentNode.rightChild);
                int temp = minimumNode.id;
                delete(temp); // delete the leaf node
                currentNode.id = temp;

            }
        }

        return currentNode;
    }

    public Node getMinimumNode(Node startNode) {
        if (startNode.leftChild == null) {
            return startNode;
        }
        return getMinimumNode(startNode.leftChild);
    }

    public Node getRoot() {
        return this.root;
    }
}

class Node {
    int id;
    Node leftChild;
    Node rightChild;
}