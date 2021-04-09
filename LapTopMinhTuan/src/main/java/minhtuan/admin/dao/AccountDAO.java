package minhtuan.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minhtuan.model.Admin;
import minhtuan.model.Customer;

@Repository
public class AccountDAO {

	@Autowired
	private SessionFactory sessionFactory;

	//Thêm tài khoản
	public void insertAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		session.save(admin);
	}

	//Sai tài khoản
	public void updateAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		session.update(admin);
	}

	//Xóa tài khoản
	public void deleteAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(admin);
	}

	//Lấy username
	public Admin getIDAdmin(String username) {
		Session session = sessionFactory.getCurrentSession();
		Admin admin = (Admin) session.get(Admin.class, username);
		return admin;
	}

	//Lấy danh sách admin
	@SuppressWarnings("unchecked")
	public List<Admin> getAllAdmin() {
		Session session = sessionFactory.getCurrentSession();
		List<Admin> list = session.createQuery("FROM Admin").list();
		return list;
	}


	 /* Kiểm tra username của admin */
	@SuppressWarnings("unchecked")
	public int checkUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		List<Admin> list = session.createQuery("FROM Admin WHERE username ='" + username + "'").list();
		return list.size();
	}

	/* Kiểm tra username của customer */
	@SuppressWarnings("unchecked")
	public int checkUsernameCustomer(String username) {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("FROM Customer WHERE id ='" + username + "'").list();
		return list.size();
	}
	
	 /*Kiểm tra password của admin*/
	@SuppressWarnings("unchecked")
	public int checkPassword(String password) {
		Session session = sessionFactory.getCurrentSession();
		List<Admin> list = session.createQuery("FROM Admin WHERE password ='" + password + "'").list();
		return list.size();
	}
	
	/*Kiểm tra password của customer*/
	@SuppressWarnings("unchecked")
	public int checkPasswordCustomer(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("FROM Customer WHERE id ='" + username + "' AND password ='" + password + "'").list();
		return list.size();
	}

	//Tìm kiếm
	@SuppressWarnings("unchecked")
	public List<Admin> searchAdmin(String search) {
		Session session = sessionFactory.getCurrentSession();
		List<Admin> list = session
				.createQuery("FROM Admin WHERE username LIKE '%" + search + "%' OR fullName LIKE '%" + search + "%'")
				.list();
		return list;
	}

	//Phân trang, page là số thứ trang
	@SuppressWarnings("unchecked")
	public List<Admin> loadAdminPage(String page) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Admin");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			result = Integer.parseInt(temp) - 10;
		}
		//Lấy kết quả bắt đầu từ result
		query.setFirstResult(result);
		//Lấy 10 kết quả đầu tiên
		query.setMaxResults(10);
		//Trả về danh sách 10 kết quả
		return query.list();
	}

	//Lấy số lượng tài khoản admin
	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowAdmin() {
		Session session = sessionFactory.getCurrentSession();
		List<Admin> list = session.createQuery("FROM Admin").list();
		int i = 0;
		for (Admin admin : list) {
			i++;
		}
		return i;
	}

	// ==> LOGIN

	/*Kiểm tra đăng nhập */
	public Admin login(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Admin WHERE username = :username and password = :password");
		query.setString("username", username);
		query.setString("password", password);
		Admin admin = (Admin) query.uniqueResult();
		return admin;
	}
	
	/*Kiểm tra đăng nhập user*/
	public Customer loginCustomer(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Customer WHERE id = :username and password = :password");
		query.setString("username", username);
		query.setString("password", password);
		Customer customer = (Customer) query.uniqueResult();
		return customer;
	}
	
	/*Lấy mật khẩu*/
	public Admin forgot(String username)
	{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Admin WHERE username = :username");
		query.setString("username", username);
		Admin admin = (Admin) query.uniqueResult();
		return admin;
	}
}
