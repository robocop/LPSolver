package fr.enslyon;

/**
 * Created by quentin on 25/03/15.
 */
public class DictionaryEntryException extends Exception {
    public DictionaryEntryException(String msg) {
        System.out.println("Error with a DictionaryEntry: " + msg);
    }
}
