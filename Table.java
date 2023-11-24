import java.io.*;
import java.util.*;

class Table {
    private final int TABLE_SIZE = 2017;
    private Entry[] table;
    
    Table(String fileName) {
	int hashValue;
	String word;
	
	/*
	 * Each entry in the table is the head of a linked list.
	 * Initialize all such entries to null.
	 */
	table = new Entry[TABLE_SIZE];
	for (int i=0; i<TABLE_SIZE; i++) {
	    table[i] = null;
	}
	
	try {
	    Scanner theFile = new Scanner( new File(fileName) );
	    
	    // each line contains 1 word
	    while ( theFile.hasNextLine() ) {
		word = theFile.nextLine();
		
		// before hashing, convert to lower case
		word = word.toLowerCase();

		// skip any blank lines, which are read as empty strings
		if (word.length() > 0) {
		    hashValue = hash(word);

		    // insert at the head of the linked list at this index
		    table[hashValue] = new Entry( word, table[hashValue] );
		}
	    }
	    
	    theFile.close();
	}	
	catch(FileNotFoundException ex) {
	    System.out.println( "Could not find " + fileName + ". Check the file name and try again." );
	}
    }

    /*****************
     * Public methods
     *****************/

    /*
     * PURPOSE: to determine whether the table contains a given word
     * PARAMETER: the word to search for
     * RETURN: true if the word is present in the table, false otherwise.
     */
    public boolean search(String word) {
	boolean found = false;

	String hashWord = word.toLowerCase();

	// only search for nonempty words
	if (hashWord.length() > 0) {
	    int hashValue = hash(hashWord);
	    Entry currEntry = table[hashValue];

	    /*
	     * The table entry is the head of a linked list.  Walk forward
	     * through this linked list until the word is found or the 
	     * end of the list is reached.
	     */
	    while ( !found && currEntry != null ) {
		if ( hashWord.equals(currEntry.word) ) {
		    found = true;
		}
		else {
		    currEntry = currEntry.link;
		}
	    }
	}
	
	return found;
    }

    /*****************
     * Private methods
     *****************/
    
    /*
     * PURPOSE: to apply the polynomial hash code to a given string, modulo
     * the size of the hash table
     * PARAMETER: the string to be hashed
     * RETURN: an integer between 0 and TABLE_SIZE-1 inclusive
     */
    private int hash(String word) {
	final int HASHING_CONSTANT = 13; // a small prime number
	
	/*
	 * Use Horner's method to compute the polynomial hash function efficiently.
	 * Apply the modulo operation after each step.
	 */
	int lastCharIndex = word.length()-1;
	int hash = (int)word.charAt(lastCharIndex) % TABLE_SIZE;
	for (int i = lastCharIndex-1; i >= 0; i--) {
	    hash *= HASHING_CONSTANT;
	    hash += (int)word.charAt(i);
	    hash %= TABLE_SIZE;
	}

	return hash;
    }

    /*
     * Inner class for linked-list implementation of separate chaining
     */
    private class Entry {
	public String word;
	public Entry link;
	
	Entry(String newWord, Entry newLink) {
	    word = newWord;
	    link = newLink;
	}
    }
    
}
