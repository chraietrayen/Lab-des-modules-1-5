package com.exemple.helloworld.model;

/**
 * A simple model class representing a greeting message.
 */
public class Greeting {

    private final long id;
    private final String content;

    /**
     * Constructs a new Greeting with the given id and content.
     *
     * @param id      the unique identifier of the greeting
     * @param content the message content of the greeting
     */
    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    /**
     * Returns the unique identifier of the greeting.
     *
     * @return the id of the greeting
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the message content of the greeting.
     *
     * @return the content of the greeting
     */
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
