package com.mycompany.backend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserSession 
{
    private static UserSession session;
    private final StringProperty username = new SimpleStringProperty();

    private UserSession() {}

    public static UserSession getSession() 
    {
        if (session == null) 
        {
            session = new UserSession();
        }
        return session;
    }

    public String getUsername() 
    {
        return username.get();
    }

    public void setUsername(String username) 
    {
        this.username.set(username);
    }

    public StringProperty usernameProperty() 
    {
        return username;
    }

    public void clearSession() 
    {
        this.username.set(null);
    }
}
