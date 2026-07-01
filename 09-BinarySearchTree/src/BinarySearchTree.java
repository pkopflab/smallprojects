/*
Sebastian Waldmann
Matrikelnummer: 22982943
*/

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree implements BSTInterface {

    class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    private TreeNode root;

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean exists(int value) {
        if (root == null) {
            return false;
        } else {
            return existsRecursive(root, value);
        }
    }

    private boolean existsRecursive(TreeNode node, int value) {
        if (node == null) {
            return false;
        } else if (value < node.value) {
            return existsRecursive(node.left, value);
        } else if (value > node.value) {
            return existsRecursive(node.right, value);
        } else {
            return true;
        }
    }

    @Override
    public void insert(int value) throws ElementExistsException {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            insertRecursive(root, value);
        }
    }

    private TreeNode insertRecursive(TreeNode node, int value) throws ElementExistsException {
        if (node == null) {
            node = new TreeNode(value);
        } else if (value < node.value) {
            node.left = insertRecursive(node.left, value);
            node.left.parent = node;
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
            node.right.parent = node;
        } else {
            throw new ElementExistsException(value + " already exists in the search tree");
        }
        return node;
    }

    @Override
    public void remove(int value) throws NoSuchElementException {
        if (!exists(value)) {
            throw new NoSuchElementException(value + " does not exists in the search tree");
        }
        removeRecursive(root, value);
    }

    private TreeNode removeRecursive(TreeNode node, int value) {
        if (node == null) {
            return node;
        }
        if (value < node.value) {
            node.left = removeRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = removeRecursive(node.right, value);
        } else {
            if (numChildren(node) < 2) {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }
            } else {
                node.value = minNode(node.right);
                node.right = removeRecursive(node.right, node.value);
            }
        }
        return node;
    }

    private int numChildren(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null) {
            if (node.right == null) {
                return 0;
            } else {
                return 1;
            }
        }
        if (node.right == null) {
            return 1;
        } else {
            return 2;
        }
    }

    private int minNode(TreeNode node) {
        if (node.left == null) {
            return node.value;
        }
        return minNode(node.left);
    }

    @Override
    public List<Integer> inOrderList() {
        List<Integer> inOrderList = new ArrayList<>();
        inOrderListRecursive(root, inOrderList);
        return inOrderList;
    }

    private void inOrderListRecursive(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inOrderListRecursive(node.left, list);
        list.add(node.value);
        inOrderListRecursive(node.right, list);
    }

    @Override
    public List<Integer> preOrderList() {
        List<Integer> preOrderList = new ArrayList<>();
        preOrderListRecursive(root, preOrderList);
        return preOrderList;
    }

    private void preOrderListRecursive(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.value);
        preOrderListRecursive(node.left, list);
        preOrderListRecursive(node.right, list);
    }

    @Override
    public List<Integer> postOrderList() {
        List<Integer> postOrderList = new ArrayList<>();
        postOrderListRecursive(root, postOrderList);
        return postOrderList;
    }

    private void postOrderListRecursive(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        postOrderListRecursive(node.left, list);
        postOrderListRecursive(node.right, list);
        list.add(node.value);
    }

    public static void main(String[] args) throws ElementExistsException {
        BinarySearchTree tree = new BinarySearchTree();

        System.out.println("false -> " + tree.exists(10));

        tree.insert(4);
        tree.insert(1);
        tree.insert(6);

        System.out.println("true -> " + tree.exists(4));
        System.out.println("true -> " + tree.exists(1));
        System.out.println("true -> " + tree.exists(6));

        for (Integer value : tree.inOrderList()) {
            System.out.print(value + " ");
        }

        System.out.println();

        for (Integer value : tree.preOrderList()) {
            System.out.print(value + " ");
        }

        System.out.println();
        for (Integer value : tree.postOrderList()) {
            System.out.print(value + " ");
        }

        System.out.println();

        tree.clear();

        System.out.println("false -> " + tree.exists(42));

        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(-6);

        try {
            tree.insert(6);
            System.out.println("FEHLER");

        } catch (ElementExistsException e) {
            System.out.println("ok -> " + e.getMessage());

        }
        tree.insert(-9);
        tree.insert(5);
        tree.insert(24);

        for (Integer value : tree.inOrderList()) {
            System.out.print(value + " ");

        }
        System.out.println();

        try {
            tree.remove(4711);
            System.out.println("FEHLER");

        } catch (NoSuchElementException e) {
            System.out.println("ok -> " + e.getMessage());

        }

        tree.remove(-6);

        System.out.println("false -> " + tree.exists(-6));

        for (Integer value : tree.inOrderList()) {
            System.out.print(value + " ");

        }

        System.out.println();

        tree.remove(-9);

        System.out.println("false -> " + tree.exists(-9));

        for (Integer value : tree.inOrderList()) {
            System.out.print(value + " ");

        }

        System.out.println();

        tree.remove(6);

        System.out.println("false -> " + tree.exists(6));

        for (Integer value : tree.inOrderList()) {
            System.out.print(value + " ");

        }

        System.out.println();

        tree.remove(4);

        System.out.println("false -> " + tree.exists(4));

        for (Integer value : tree.inOrderList()) {
            System.out.print(value + " ");
        }
    }

}
