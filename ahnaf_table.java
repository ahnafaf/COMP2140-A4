public class ahnaf_table {
    private int TABLE_SIZE = 2097;
    Node root;

    public static void main(String[] args) {
        ahnaf_table table = new ahnaf_table();
        table.insert("Hello");
        table.insert("World");
        System.out.println("Inserted 'Hello' and 'World' into the table.");

        // Example search operations
        System.out.println("Searching for 'Hello': " + table.search("Hello")); // true
        System.out.println("Searching for 'NotExist': " + table.search("NotExist")); // false
    }

    class Node {
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

    public void insert(String word) {
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
                // If the hashKey is equal to current, you might want to either update the current node
                // or handle duplicates as per your requirements.
                return;
            }
        }
    }

    public boolean search(String word) {
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


    private int hash(String word) {
		final int HASHING_CONSTANT = 13; // a small prime number
		/*
		 * Use Horner's method to compute the polynomial hash function efficiently.
		 * Apply the modulo operation after each step.
		 */
		int lastCharIndex = word.length() - 1;
		int hash = (int) word.charAt(lastCharIndex) % TABLE_SIZE;
		for (int i = lastCharIndex - 1; i >= 0; i--) {
			hash *= HASHING_CONSTANT;
			hash += (int) word.charAt(i);
			hash %= TABLE_SIZE;
		}
	    return hash;
	}
}

