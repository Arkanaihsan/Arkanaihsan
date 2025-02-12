package com.mycompany.backend;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class UserService
{
    private FileIO fileIO = new FileIO();
    private String filename;

    //if no arg passing through constructor, default to Users.txt
    public UserService() {
        this.filename = "Users.txt";
    }

    //for testing purposes
    public UserService(String filename) {
        this.filename = filename;
    }

    //get all user
    public List<User> getAllUsers()
    {
        List<User> userList = new ArrayList<>();

        //add all users to the arraylist to be returned
        try
        {
            List<String> users = fileIO.readFile(filename);
            for (String user : users)
            {
                String[] data = user.split(",");
                userList.add(new User(data[0], data[1], data[2]));
            }

            //done
            return userList;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }

    //get user by username
    public User getUserByUsername(String username)
    {
        List<User> users = getAllUsers();

        for (User user : users)
        {
            if (user.getUsername().equals(username))
            {
                return user;
            }
        }
        //user doesnt exists
        return null;
    }

    //login
    public ServiceResult userLogin(String loginInfo, String password)
    {
        //read user class frm json file
        try 
        {
            List<String> userList = fileIO.readFile(filename);

            //loop through the list
            for (String userLine : userList)
            {
                String[] userInfo = userLine.split(",");
                //check if the given username/email exists in the list
                if (userInfo[0].equals(loginInfo) || userInfo[1].equals(loginInfo))
                {
                    //then check password
                    if (userInfo[2].equals(password))
                    {
                        //login successful
                        return new ServiceResult(userInfo[0], "Logged in successfully. Welcome " + userInfo[0] + ".");
                    }
                    //wrong password
                    else
                    {
                        return new ServiceResult(null, "Incorrect Password.");
                    }
                }
            }
            //login unsuccessful
            return new ServiceResult(null, "User not found. Please sign up as a new user.");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }

    //sign up
    public ServiceResult userSignUp(String username, String email, String password)
    {
        if (username == null || email == null || password == null)
        {
            return new ServiceResult(false, "Info incomplete. Please fill in all of your info.");
        }
        else
        {
            try 
            {
                //get all user to check if user already exists
                List<String> users = fileIO.readFile(filename);
    
                for (String userLine : users)
                {
                    String[] userInfo = userLine.split(",");
                    if (userInfo[0].equals(username) || userInfo[1].equals(email))
                    {
                        return new ServiceResult(false, "Username/Email already exists. Please try again.");
                    }
                }
                
                //if no matching existing users, then we can create an account
                User newUser = new User(username, email, password);
                fileIO.appendFile(filename, newUser);
                return new ServiceResult(true, "Account created successfully. Please login.");
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            catch (URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    //edit profile
    public ServiceResult userEdit(String username, String email, String password)
    {
        if (username == null || email == null || password == null)
        {
            return new ServiceResult(false, "Info incomplete. Please fill in all of your info.");
        }
        else
        {
            User updatedUser = new User(username, email, password);
            try 
            {
                fileIO.editFile(filename, updatedUser, username);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            catch (URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
    
            return new ServiceResult(true, "Your changes has been saved.");
        }
    }

    //delete user
    public boolean userDelete(String username)
    {
        //delete user
        try
        {
            fileIO.deleteLineFile(filename, username);

            //done 
            return true;
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }

        //delete all diary entries of the user (TODO)
    }
}
