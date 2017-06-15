package com.webToolsProj.TripOrg.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.Email;
import com.webToolsProj.TripOrg.POJO.Host;
import com.webToolsProj.TripOrg.POJO.Person;



public class HostDAO extends DAO {

    public HostDAO() {
    }

    public Host get(String username)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Person where userName = :username");
            q.setString("username", username);
            Person p = (Person)q.uniqueResult();
            Query qH = getSession().createQuery("from Host where personID = :personID");
            qH.setLong("personID", p.getPersonID());            
            Host host = (Host) qH.uniqueResult();
            commit();
            return host;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get user " + username, e);
        }
    }

    public Host create(String username, String password,String emailId, String firstName, String lastName, long govtID, String idType)
            throws AdException {
        try {
            begin();
            System.out.println("inside DAO");
            
            Email email=new Email(emailId);
            Host host=new Host(govtID,idType);
            
            host.setfName(firstName);
            host.setlName(lastName);
            host.setRole("Host");
            host.setEmail(email);
            host.setUserName(username);
            host.setPassword(password);
            email.setPerson(host);
            
            getSession().save(host);
            
            commit();
            return host;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }

    public void delete(Host host)
            throws AdException {
        try {
            begin();
            getSession().delete(host);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete user " + host.getfName(), e);
        }
    }
    
    public boolean hostExists(String userName)
            throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Person where userName = :userName");
            q.setString("userName", userName);
            Person p = (Person)q.uniqueResult();
            if(p == null){
            	return false;
            }else{
            	return true;
            }
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not get userName " + userName, e);
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