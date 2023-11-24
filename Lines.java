public class Lines {
    
    private Node front, back;
    
    Lines() {
	front = null;
	back = null;
    }
    
    /*****************
     * Public methods
     *****************/

    /*
     * PURPOSE: to add a new line to the back of the queue
     * PARAMETER: the line to be added
     */
    public void addLine(int line) {
	Node newNode = new Node(line, null);

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
     * PURPOSE: to create a string that contains all of the entries in the queue
     * from front to back, separated by spaces. Doing so empties the queue.
     * RETURN: the string containing the list of entries
     */
    public String printToString() {
	String contents = "";
	int line;
	
	while ( !isEmpty() ) {
	    line = dequeue().line;
	    contents += line + " ";
	}

	return contents;
    }

    /*****************
     * Private methods
     *****************/

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
     * PURPOSE: to test whether the queue is empty
     * RETURN: true if the queue is empty, false otherwise
     */
    private boolean isEmpty() {
	return back == null;
    }

    /*
     * Inner class to hold each line number
     */
    private class Node {
	public int line;
	public Node link;
	
	Node(int newLine, Node newLink) {
	    line = newLine;
	    link = newLink;
	}
    }

}
