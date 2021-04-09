package minhtuan.admin.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minhtuan.model.Customer;

@Repository
public class CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;

	/*Thêm khách hàng*/
	public void insertCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		customer.setId("HB" + new Date().getTime());
		session.save(customer);
	}

	/*Sửa khách hàng*/
	public void updateCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.update(customer);
	}

	/*Xóa khách hàng*/
	public void deleteCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(customer);
	}

	/*Lấy id khách hàng*/
	public Customer getIDCustomer(String id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, id);
		return customer;
	}

	/*Lấy danh sách khách hàng*/
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomer() {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("FROM Customer").list();
		return list;
	}
	
	/*Kiểm tra username đã tồn tại chưa*/
	@SuppressWarnings("unchecked")
	public int checkUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("FROM Customer WHERE ID = '" + username + "'").list();
		return list.size();
	}
	
	/*Kiểm tra username đã được kích hoạt chưa*/
	@SuppressWarnings("unchecked")
	public int checkUsernameActivated(String username) {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("FROM Customer WHERE ID = '" + username + "'AND Activated = 1").list();
		return list.size();
	}

	/*Kiểm tra email đã tồn tại chưa*/
	@SuppressWarnings("unchecked")
	public int checkEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("FROM Customer WHERE Email = '" + email + "'").list();
		return list.size();
	}

	/*Kiểm tra mật khẩu*/
	@SuppressWarnings("unchecked")
	public int checkPassword(String password) {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("FROM Customer WHERE Password = '" + password + "'").list();
		return list.size();
	}

	/*Tìm kiếm*/
	@SuppressWarnings("unchecked")
	public List<Customer> searchCustomer(String search) {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session
				.createQuery("FROM Customer WHERE fullName LIKE '%" + search + "%' OR Email LIKE '%" + search
						+ "%' OR Address LIKE '%" + search + "%' OR numberPhone LIKE '%" + search + "%'")
				.list();
		return list;
	}

	/*Phân trang*/
	@SuppressWarnings("unchecked")
	public List<Customer> loadCustomerPage(String page) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Customer");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			result = Integer.parseInt(temp);
		}
		query.setFirstResult(result);
		query.setMaxResults(10);
		return query.list();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowCustomer() {
		Session session = sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("FROM Customer").list();
		int i = 0;
		for (Customer customer : list) {
			i++;
		}
		return i;
	}

}
