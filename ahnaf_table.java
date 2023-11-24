//-------------------------------------------
// NAME: Ahnaf Ahsan
// STUDENT NUMBER: 7966487
// COURSE: COMP 2140, SECTION: A02
// INSTRUCTOR: A. Maghdoust
// ASSIGNMENT: assignment 4
// QUESTION: question 1
//
// REMARKS: Table ADT using BST
//-------------------------------------------

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Table {
    static private int TABLE_SIZE = 2097;
    static Node root;

    static class Node {
        String word;
        int hashKey;
        Node left;
        Node right;

        Node(String word, int hashKey) {
            this.word = word;
            this.hashKey = hashKey;
            this.left = this.right = null;
        }
    }

    Table(String fileName) {
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] array1 = data.split(" ");
                String str = array1.toString();
                // Check if word is empty
                if (data.length() == 0) {
                    continue;
                }
                for (int i = 0; i < array1.length; i++) {
                    insert(array1[i]);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void insert(String word) {
        assert word != null;
        int hashKey = hash(word);
        Node newNode = new Node(word, hashKey);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (hashKey < current.hashKey) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else if (hashKey > current.hashKey) {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            } else {
                // If the hashKey is equal to current, you might want to either update the
                // current node
                // or handle duplicates as per your requirements.
                return;
            }
        }
    }

    public static boolean search(String word) {
        assert word != null;
        int hashKey = hash(word);
        Node current = root;

        while (current != null) {
            if (hashKey < current.hashKey) {
                current = current.left;
            } else if (hashKey > current.hashKey) {
                current = current.right;
            } else {
                return true; // hashKey found
            }
        }

        return false; // hashKey not found
    }

//-------------------------------------------------------
// hash
//
// PURPOSE: hash a string to an integer
// PARAMETERS:
// String word: the string to be hashed
// RETURN:
// int: the hash value of the string
//-------------------------------------------------------

    private static int hash(String word) {
        assert word != null;
        final int HASHING_CONSTANT = 13; // a small prime number
        int lastCharIndex = word.length() - 1;
        int hash = (int) word.charAt(lastCharIndex) % TABLE_SIZE;
        for (int i = lastCharIndex - 1; i >= 0; i--) {
            hash *= HASHING_CONSTANT;
            hash += (int) word.charAt(i);
            hash %= TABLE_SIZE;
        }
        return hash;
    }

    private final static String SPACE = "    "; // four spaces for indentation

    public void print() {
        printSideways(root, 0);
    }

    private static void printSideways(Node current, int level) {
        if (current == null) {
            return;
        }
        printSideways(current.right, level + 1);
        System.out.println(String.format("%s%s", SPACE.repeat(level), current.word));
        printSideways(current.left, level + 1);
    }
}