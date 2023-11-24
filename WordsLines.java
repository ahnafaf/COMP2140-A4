class WordsLines {
    private Node front, back;

    WordsLines() {
	front = null;
	back = null;
    }

    /*****************
     * Public methods
     *****************/

    /*
     * PURPOSE: to add a word-line pair to the queue.
     * First, the queue is searched for the word. If it is already present in the queue,
     * the new line number will be added to the existing list of line numbers.
     * Otherwise, a new key-line pair is enqueued.
     * PARAMETERS: the word, and the line on which this word was found
     */
    public void addEntry(String word, int line) {
	Node currEntry = search(word);

	if (currEntry == null) {
	    // word has not previously appeared
	    enqueue(word, line);
	}
	else {
	    // word has previously appeared, so add new line number to existing queue
	    currEntry.lines.addLine(line);
	}
    }
    
    /*
     * PURPOSE: to print out all of the words and their line numbers in the queue.
     * Doing so empties the queue.
     */
    public void print() {
	Node currEntry;
	
	while ( !isEmpty() ) {
	    currEntry = dequeue();

	    String entry = "Uncommon word \"" + currEntry.word + "\" found on lines ";
	    entry += currEntry.lines.printToString();
	    
	    System.out.println(entry);	    
	}
    }
    
    /*****************
     * Private methods
     *****************/

    /*
     * PURPOSE: to add a new word-line pair to the back of the queue
     * PARAMETERS: the word, and the line on which this word was found
     */
    private void enqueue(String word, int line) {
	Node newNode = new Node(word, line, null);

	// add new entry to the back of the queue
	if ( !isEmpty() ) {
	    // the entry that used to be the back of the queue now links to this one
	    back.link = newNode;
	} else {
	    // the first node to enter becomes both front and back
	    front = newNode;
	}
	back = newNode;
    }
    
    /*
     * PURPOSE: to remove a word-line pair from the front of the queue and return it.
     * The pointer to the front of the line is updated to the new front entry. If the queue
     * is now empty, front and back are both updated to null.
     * RETURN: the node that was at the front of the queue.
     */
    private Node dequeue() {
	Node dequeueNode = front;
	if ( !isEmpty() ) {
	    if (front != back) {
		// there will still be something in the queue after this entry is removed
		front = front.link;
	    } else {
		// there was only one entry, and now it is gone
		front = null;
		back = null;
	    }
	}
	return dequeueNode;	
    }

    /*
     * PURPOSE: to search the queue for an entry that contains a given word
     * PARAMETER: the word to search for
     * RETURN: the node that contains this word, or null if the word is not found
     */
    private Node search(String word) {
	Node currEntry = front;
	boolean found = false;

	/*
	 * Use the underlying linked list structure to search the queue.
	 * Start from the front, and walk through the linked list until the word is
	 * found or the end of the list is reached.
	 */
	while ( !found && currEntry != null ) {
	    if ( currEntry.word.equals(word) ) {
		found = true;
	    }
	    else {
		currEntry = currEntry.link;
	    }
	}
	
	return currEntry;
    }
    
    /*
     * PURPOSE: to test whether the queue is empty
     * RETURN: true if the queue is empty, false otherwise
     */
    private boolean isEmpty() {
	return back == null;
    }
  
    /*
     * Inner class to hold each word and associated queue of line numbers
     */
    private class Node {
	public String word;
	public Lines lines;
	public Node link;
	
	Node(String newWord, int newLine, Node newLink) {
	    word = newWord;
	    
	    // create the queue and insert the first line number
	    lines = new Lines();
	    lines.addLine(newLine);
	    
	    link = newLink;
	}
    }

}
