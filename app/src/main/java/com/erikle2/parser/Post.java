package com.erikle2.parser;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Erik on 2015-08-03.
 */
@ParseClassName("post")
public class Post extends ParseObject {

    // Ensure that your subclass has a public default constructor
    public Post() {
        super();
    }

    // Add a constructor that contains core properties
    public Post(String title, String text) {
        super();
        setTitle(title);
        setText(text);
    }

    public void setTitle(String value){
        put("title", value);
    }
    public void setText(String value){
        put("text", value);
    }

}
