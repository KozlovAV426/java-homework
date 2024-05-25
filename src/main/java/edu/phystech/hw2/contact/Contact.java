package edu.phystech.hw2.contact;

import java.util.regex.Pattern;

public record Contact(String username, String email) implements Comparable<Contact>{

 
    public static final String UNKNOWN_EMAIL = "unknown";
    
    public Contact {
        if (username.isBlank()) {
            throw new InvalidContactFieldException("username");
        }
        if (email != null && !email.isBlank()) {
            if (!Pattern.matches(".+@gmail\\.com", email)) {
                throw new InvalidContactFieldException("email");
            }
        } else {
            email = UNKNOWN_EMAIL;
        }
    }
    
    public Contact(String username) {
        this(username, null);
    }
    @Override
    public int compareTo(Contact o) {
        if (this.username.length() < o.username.length()) {
            return -1; 
        } else if (this.username.length() > o.username.length()) {
            return 1; 
        } else {
            return this.username.compareTo(o.username); 
    }
    
 }
}
