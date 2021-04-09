package minhtuan.admin.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import minhtuan.admin.dao.CustomerDAO;
import minhtuan.model.Customer;

@Service("customerService")
@Transactional
public class CustomerService {

	@Autowired
	CustomerDAO customerDAO;

	public void insertCustomer(Customer customer) {
		customerDAO.insertCustomer(customer);
	}

	public void updateCustomer(Customer customer) {
		customerDAO.updateCustomer(customer);
	}

	public void deleteCustomer(Customer customer) {
		customerDAO.deleteCustomer(customer);
	}

	public Customer getIDCustomer(String id) {
		return customerDAO.getIDCustomer(id);
	}

	/*Lấy danh sách khách hàng*/
	public List<Customer> getAllCustomer() {
		return customerDAO.getAllCustomer();
	}

	/*Kiếm tra username đã tồn tại chưa*/
	public int checkUsername(String username) {
		return customerDAO.checkUsername(username);
	}
	
	/*Kiểm tra username đã được kích hoạt chưa*/
	public int checkUsernameActivated(String username) {
		return customerDAO.checkUsernameActivated(username);
	}
	
	/*Kiếm tra email đã tồn tại chưa*/
	public int checkEmail(String email) {
		return customerDAO.checkEmail(email);
	}

	/*Kiểm tra mật khẩu*/
	public int checkPassword(String password) {
		return customerDAO.checkPassword(password);
	}

	/*Tìm kiếm*/
	public List<Customer> searchCustomer(String search) {
		return customerDAO.searchCustomer(search);
	}

	/*Phân trang*/
	public List<Customer> loadCustomerPage(String page) {
		return customerDAO.loadCustomerPage(page);
	}

	public int getRowCustomer() {
		return customerDAO.getRowCustomer();
	}
}
