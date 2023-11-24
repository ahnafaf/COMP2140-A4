import java.io.*;
import java.util.*;

public class A3Solution {
    public static void main( String args[] ) {
	if (args.length > 1) {
	    Table commonWords = new Table( args[0] );
	    WordsLines uncommonWords = new WordsLines();

	    int countUncommon = 0;

	    int lineNum = 0;
	    String currLine;
	    String[] words;
	    
	    try {
		Scanner theFile = new Scanner( new File(args[1]) );
		
		while ( theFile.hasNextLine() ) {
		    currLine = theFile.nextLine();
		    lineNum++;
		    
		    words = currLine.split( "\\s+" ); // split on spaces
		    for (int i=0; i<words.length; i++) {
			// remove leading and trailing non-letter characters from the word
			String cleanedWord = cleanWord(words[i]);
			
			// only proceed if the result is nonempty
			if ( cleanedWord.length() > 0 && !commonWords.search(cleanedWord) ) {
			    uncommonWords.addEntry(cleanedWord, lineNum);
			    countUncommon++;
			}
		    }
		}
		
		theFile.close();
	    }
	    
	    catch(FileNotFoundException ex) {
		System.out.println( "Could not find " + args[1] + ". Check the file name and try again." );
	    }

	    if (countUncommon > 0) {
		System.out.println( "The uncommon words in this document are:" );
		uncommonWords.print();
	    }
	    else {
		System.out.println("No uncommon words found.");
	    }
	}
	else {
	    System.out.println( "Enter two file names.  The first contains the words list, and the second contains the document to be processed." );
	}
    }

    /*
     * PURPOSE: to remove leading and trailing non-letter characters from a string
     * PARAMETER: the string to be cleaned
     * RETURN: the substring that results when non-letters are removed
     * from the front and back of the input
     */
    static private String cleanWord(String word) {
	int startIndex = 0;
	int endIndex = word.length();
	
	// remove non-letter characters from start and end
	while ( startIndex < word.length() && ! Character.isLetter(word.charAt(startIndex)) ) {
	    startIndex++;
	}
	while ( endIndex > startIndex && !Character.isLetter(word.charAt(endIndex-1)) ) {
	    endIndex--;
	}

	// if no characters are left, return the empty string
	String cleanedWord = "";
	
	if (startIndex < endIndex) {
	    cleanedWord = word.substring(startIndex, endIndex);
	}

	return cleanedWord;
    }
}
