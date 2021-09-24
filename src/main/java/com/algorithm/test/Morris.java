package com.algorithm.test;

/**
 * Morris遍历方法：给定一个节点Node  定义两个变量 当前节点cur 和左孩子的最右节点mostRight = cur.left
 * (1):   mostRight == null  cur = cur.right
 * (2): mostRight != null 当mostRight.right 不为空的情况下 mostRight = mostRight.right
 * （1）：mostRight.right = null  此时将mostRight.right = cur
 * (2): mostRight.right = cur  将mostRight.right = cur cur = cur.left
 * <p>
 * 文字描述：
 * 给定一个根节点node 让当前节点等于node节点
 * 先判断当前节点是否有左孩子节点 如果没有的话 cur = cur.right
 * 如果有的话将mostRight 设置为当前节点的左节点
 * 然后一步步判断mostRight节点是否有右节点 如果有右孩子节点 则 mostRight = mostRight.right 直到mostRight的右节点为空
 * 此时将mostRight的右节点设置为cur 接着遍历 当mostRight.right = cur的时候 cur = cur.left  mostRight.right = null
 *
 * @Description: morris遍历二叉树 时间复杂度O(N) 空间复杂度O(1)
 * @author: zyu
 * @date: 2021年07月21日 17:25
 */
public class Morris {

    public static class Node {
        public Node left;
        public Node right;
        public Integer value;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void MorrisTree(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    /***
     *
     用morris实现先序遍历
     */
    public static void MorrisPre(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    System.out.println(cur.value);
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else if (mostRight == null) {
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }

    /***
     * 用morris实现中序遍历
     * @param root
     */
    public static void MorrisIn(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }

    /***
     * 用morris实现后序遍历
     * @param root
     */
    public static void MorrisPos(Node root) {
        if (root == null) {
            return;
        }
        Node cur = root;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // 第二次到达该节点
                    mostRight.right = null;
                    //每个节点的左子树右边界 逆序打印
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        //逆序打印根节点的右边界
        printEdge(root);
    }

    /***
     * 打印当前最右边的逆序
     * @param cur
     */
    private static void printEdge(Node cur) {
        Node tail = reverseNode(cur);
        Node node = tail;
        while (node != null) {
            System.out.println(node.value);
            node = node.right;
        }
        reverseNode(tail);
    }

    /***
     * 单链表逆序
     * @param cur
     * @return
     */
    private static Node reverseNode(Node cur) {
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
//        root.right = node2;
//        node2.right = node3;
//        node3.right = node4;
//        node4.right = node5;
//        Node node = reverseNode(root);
//        while (node != null) {
//            System.out.println(node.value);
//            node = node.right;
//        }
//        printEdge(root);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        MorrisPos(root);
//        MorrisPre(root);
    }
}
