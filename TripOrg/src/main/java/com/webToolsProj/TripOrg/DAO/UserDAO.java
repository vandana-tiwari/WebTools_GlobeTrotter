package com.webToolsProj.TripOrg.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.Email;
import com.webToolsProj.TripOrg.POJO.Person;
import com.webToolsProj.TripOrg.POJO.User;


public class UserDAO extends DAO {

    public UserDAO() {
    }

    public Person get(String username)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Person where userName = :username");
            q.setString("username", username);
            Person person = (User) q.uniqueResult();
            commit();
            return person;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + username, e);
        }
    }

    public User create(String username, String password,String emailId, String firstName, String lastName)
            throws AdException {
        try {
            begin();
            System.out.println("inside DAO");
            
            Email email=new Email(emailId);
            User user=new User();
            
            user.setfName(firstName);
            user.setlName(lastName);
            user.setRole("User");
            user.setEmail(email);
            user.setUserName(username);
            user.setPassword(password);
            email.setPerson(user);
            
            getSession().save(user);
            
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(User user)
            throws AdException {
        try {
            begin();
            getSession().delete(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete user " + user.getfName(), e);
        }
    }
    
    public boolean userExists(String userName)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Person where userName = :userName");
            q.setString("userName", userName);
            System.out.println("pppooo");
            Person p = (Person)q.uniqueResult();
            System.out.println("ppp"+p);
            if(p == null){
            	return false;
            }else{
            	return true;
            }
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get userName user.getUserName() " + userName, e);
        }
    }
    
    public boolean emailExists(String email)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Email where emailId = :email");
            q.setString("email", email);
            Email e = (Email)q.uniqueResult();
            if(e == null){
            	return false;
            }else{
            	return true;
            }
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get userName " + email, e);
        }
    }
}