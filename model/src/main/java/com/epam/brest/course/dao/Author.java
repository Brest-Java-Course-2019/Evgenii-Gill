package com.epam.brest.course.dao;

/**
 * The {@code Author} - this is one of the entities in project.
 * Have some simple methods.
 *
 * @author Evgenii Gill
 */

public class Author {

    /**
     * The value is used for identification in data base.
     */
    private Integer authorId;

    /**
     * First name of author.
     */
    private String firstName;

    /**
     * Last name of author.
     */
    private String lastName;

    /**
     * Initializes a newly created {@code Author} object.
     */
    public Author() {
    }

    /**
     * Initializes a newly created {@code Author} object.
     *
     * @param testFirstName
     * @param testLastName
     */
    public Author(String testFirstName, String testLastName) {

    }

    /**
     * Initializes a newly created {@code Author} object
     * and the appropriate fields are set.
     *
     * @param authorId  is author's ID.
     * @param firstName is author's first name.
     * @param lastName  is author's last name.
     *                  A {@code Author}
     */
    public Author(Integer authorId, String firstName, String lastName) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return Returns the author's ID.
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * Sets the author's ID to the <code>authorId</code>
     *
     * @param authorId the new author's ID.
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    /**
     * @return Returns the first name of author.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the author's first name to the <code>firstName</code>.
     *
     * @param firstName the new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Returns the last name of author.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the author's last name to the <code>lastName</code>.
     *
     * @param lastName the new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return a string representation of the author.
     */
    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
