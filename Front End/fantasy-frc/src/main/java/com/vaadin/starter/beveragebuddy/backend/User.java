package com.vaadin.starter.beveragebuddy.backend;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a beverage category.
 */
public class User implements Serializable {

    private Long id = null;

    private String name = "";
    private String password = "";
    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(User other) {
        Objects.requireNonNull(other);
        this.name = other.getName();
        this.id = other.getId();
        this.password = other.getPassword();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value name
     *
     * @param name
     *            new value of name
     */

    /**
     * Sets the value name
     *
     * @param name
     *            new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        // Must use getters instead of direct member access,
        // to make it work with proxy objects generated by the view model
        return "Category{" + getId() + ":" + getName() + '}';
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        }
        return getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        Category other = (Category) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}