package minhtuan.admin.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import minhtuan.admin.dao.AccountDAO;
import minhtuan.model.Admin;
import minhtuan.model.Customer;

@Service("accountService")
@Repository
public class AccountService {

	@Autowired
	private AccountDAO accountDAO;

	//Thêm tài khoản
	public void insertAdmin(Admin admin) {
		accountDAO.insertAdmin(admin);
	}

	//Xóa tài khoản
	public void updateAdmin(Admin admin) {
		accountDAO.updateAdmin(admin);
	}

	//Xóa tài khoản
	public void deleteAdmin(Admin admin) {
		accountDAO.deleteAdmin(admin);
	}

	//Lấy username
	public Admin getIDAdmin(String username) {
		return accountDAO.getIDAdmin(username);
	}

	//Lấy danh sách tài khoản của admin
	public List<Admin> getAllAdmin() {
		return accountDAO.getAllAdmin();
	}

	/*Kiểm tra username*/
	public int checkUsername(String username) {
		return accountDAO.checkUsername(username);
	}
	
	/* Kiểm tra username của customer */
	public int checkUsernameCustomer(String username) {
		return accountDAO.checkUsernameCustomer(username);
	}
	
	/*Kiểm tra password */
	public int checkPassword(String password) {
		return accountDAO.checkPassword(password);
	}
	
	/*Kiểm tra password của customer*/
	public int checkPasswordCustomer(String username, String password) {
		return accountDAO.checkPasswordCustomer(username,password);
	}

	//tìm kiếm
	public List<Admin> searchAdmin(String search) {
		return accountDAO.searchAdmin(search);
	}

	//Phân trang
	public List<Admin> loadAdminPage(String page) {
		return accountDAO.loadAdminPage(page);
	}

	//Lấy số lượng admin thông qua accountDAO
	public int getRowAdmin() {
		return accountDAO.getRowAdmin();
	}

	// ==> LOGIN

	/*Kiểm tra login*/
	public Admin login(String username, String password) {
		return accountDAO.login(username, password);
	}
	
	/*Kiểm tra login user*/
	public Customer loginCustomer(String username, String password) {
		return accountDAO.loginCustomer(username, password);
	}
	
	public Admin forgot(String username)
	{
		return accountDAO.forgot(username);
	}
	
}
