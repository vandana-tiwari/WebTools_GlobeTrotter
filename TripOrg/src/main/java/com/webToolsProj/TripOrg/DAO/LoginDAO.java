package com.webToolsProj.TripOrg.DAO;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.webToolsProj.TripOrg.Exception.AdException;
import com.webToolsProj.TripOrg.POJO.Email;
import com.webToolsProj.TripOrg.POJO.Person;

public class LoginDAO extends DAO {

	public LoginDAO() {
	}

	public Person login(String userName, String password) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from Person where userName = :userName and password= :password");
			q.setString("userName", userName);
			q.setString("password", password);
			Person p = (Person) q.uniqueResult();

			if (p != null) {
				System.out.println("PersonID" + p.getPersonID());
				System.out.println("PersonID" + p.getfName());
				System.out.println("PersonID" + p.getUserName());
				System.out.println("PersonID" + p.getPassword());
				System.out.println("PersonID" + p.getlName());
				System.out.println("PersonID" + p.getRole());

				return p;
				
			}
			commit();

		} catch (HibernateException e) {
			throw new AdException("Could not login user " + userName, e);

		}
		return null;
		// return success;

	}

	public void logout(HttpSession session) {
		session.invalidate();
	}

	public boolean update(String userName, String oldPassword, String newPassword) {
		begin();
		
		Query q = getSession().createQuery(
				"Update Person p set p.password=:newPassword where p.userName=:userName and p.password=:oldPassword");
		q.setString("userName", userName);
		q.setString("oldPassword", oldPassword);
		q.setString("newPassword", newPassword);
		int result = q.executeUpdate();
		commit();
		System.out.println("result" + result);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	public String forgotPassword(String email) {
		begin();
		Query q = getSession().createQuery("from Email where emailid = :email");
		q.setString("email", email);
		Email e = (Email) q.uniqueResult();
		
		if (e != null) {
			long id = e.getId();
			System.out.println("iddddddddddd"+id);
			String newPassword = UUID.randomUUID().toString();
			System.out.println("newwwwwww" + newPassword);
			Query upQ = getSession().createQuery("Update Person p set p.password=:newPassword where p.personID= :id");
			upQ.setString("newPassword", newPassword);
			upQ.setLong("id", id);
			int result = upQ.executeUpdate();
			commit();
			System.out.println("result" + result);
			if (result == 1) {
				return newPassword;
			} else {
				return "null";
			}
		}else{
			return null;
		}

	}


}
