package minhtuan.user;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import minhtuan.admin.controller.MinhTuanController;
import minhtuan.admin.service.AccountService;
import minhtuan.admin.service.CustomerService;
import minhtuan.model.Admin;
import minhtuan.model.Customer;

@Controller
@Transactional
@RequestMapping(value = "user")
public class UserAccountController extends MinhTuanController {

	// đối tượng kiểu ServletContext dùng để chứa thông tin cho toàn bộ website
	@Autowired
	ServletContext application;
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	HttpSession httpSession;
	@Autowired
	CustomerService customerService;
	@Autowired
	AccountService accountService;

	// Mở form quên mật khẩu
	@RequestMapping(value = "forgot", method = RequestMethod.GET)
	public String formForgot(ModelMap model) {
		model.addAttribute("user", new Customer());
		return "user/forgot";
	}

	// Xác nhận lấy lại mật khẩu
	@RequestMapping(value = "forgot", method = RequestMethod.POST)
	public String forgot(ModelMap model, @ModelAttribute("user") Customer user, BindingResult errors) {
		Customer user1 = new Customer();
		user1.setId(user.getId());
		user1.setEmail(user.getEmail());
		String email = user.getEmail();
		
		if (user.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "user", "Vui lòng nhập email!!!");
		}else
		{
			Session session = sessionFactory.getCurrentSession();
			try {			
				session.refresh(user1);
				if (email.equals(user1.getEmail()) == false) {
					errors.rejectValue("email", "user", "Email không đúng!!!");
				}
			} catch (Exception e) {
				errors.rejectValue("id", "Tài khoản người dùng không tồn tại !");
			}
		}
		if (user.getId().trim().length() == 0) {
			errors.rejectValue("id", "user", "Vui lòng nhập tên người dùng !");
		}
		if (errors.hasErrors()) {
			return "user/forgot";
		} else {
			try {
				Session session = sessionFactory.getCurrentSession();
				session.refresh(user1);
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setFrom("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
				helper.setTo(email);
				helper.setReplyTo("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
				helper.setSubject("Nhập lại mật khẩu người dùng");
				helper.setText("Địa chỉ email: " + user1.getEmail() + "<br> + Tài khoản cá nhân: " + user1.getId()
						+ "<br> + mật khẩu cá nhân là : " + user1.getPassword(), true);
				mailSender.send(message);
				model.addAttribute("message", "Mật khẩu đã được gởi đến email cá nhân !");
			} catch (Exception ex) {
				model.addAttribute("message", ex.getMessage());
			}
			model.addAttribute("user", new Customer());
		}
		return "user/forgot";
	}

	// Mở form thay đổi thông tin cá nhân
	// @PathVariable("id") lấy id từ sessionScope.user.id từ giao diện header.jsp
	@RequestMapping(value = "change-info/{id}.htm", method = RequestMethod.GET)
	public String formChangeInfo(ModelMap model, @PathVariable(value = "id") String id) {
		Session session = sessionFactory.getCurrentSession();
		Customer user = (Customer) session.get(Customer.class, id);
		model.addAttribute("user", user);
		return "user/changeInfo";
	}

	// Thực hiện thay đổi thông tin cá nhân
	@RequestMapping(value = "change-info/{id}.htm", method = RequestMethod.POST)
	public String changeInfo(ModelMap model, @ModelAttribute("user") Customer user,BindingResult errors, @PathVariable(value = "id") String id,
			 @RequestParam("file_image") MultipartFile photo) {
		if (user.getFullName().trim().length() == 0) {
			errors.rejectValue("fullName","user", "Vui lòng nhập tên đầy đủ !");
		}
		if (user.getBirthday() == null) {
			errors.rejectValue("birthday","user", "Vui lòng nhập ngày sinh !");
		}
		if (user.getNumberPhone().trim().length() == 0) {
			errors.rejectValue("numberPhone","user", "Vui lòng nhập số điện thoại !");
		}
		if (user.getAddress().trim().length() == 0) {
			errors.rejectValue("address","user", "Vui lòng nhập địa chỉ !");
		}	
		if (errors.hasErrors()) {
			return "user/changeInfo";
		} else {
			try {
				if (!photo.isEmpty()) {
					// Lấy tên ảnh
					String filePhoto = photo.getOriginalFilename();
					// Lấy đường dẫn ảnh trong project
					String path = application.getRealPath("/images/customers/" + filePhoto);
					System.out.println("path: " + path);
					// Chuyển thành đối tượng File rồi chuyển đổi cho đối tượng photo
					photo.transferTo(new File(path));
					user.setPhoto(filePhoto);
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(user);
				t.commit();
				model.addAttribute("message", "Cập nhật thành công!");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Cập nhật thất bại!");
			} finally {
				session.close();
			}
		}
		return "user/changeInfo";
	}

	// mở form thay đổi mật khẩu
	@RequestMapping(value = "change-pass/{id}.htm", method = RequestMethod.GET)
	public String formChangePass(ModelMap model, @PathVariable("id") String id) {
		Session session = sessionFactory.getCurrentSession();
		// Tìm Customer bằng id
		Customer user = (Customer) session.get(Customer.class, id);
		model.addAttribute("user", user);
		return "user/changePass";
	}

	// Thực hiện thay đổi mật khẩu
	// @PathVariable("id") lấy id từ sessionScope.user.id từ giao diện
	// changePass.jsp
	// @RequestParam("password1") lấy gía trị của input có name là password1 trong
	// giao diện changePass.jsp
	@RequestMapping(value = "change-pass/{id}.htm", method = RequestMethod.POST)
	public String changePass(ModelMap model, @PathVariable("id") String id, @ModelAttribute("user") Customer user,
			@RequestParam("password1") String password1, @RequestParam("password2") String password2, BindingResult errors) {
		Customer oldPass = (Customer) httpSession.getAttribute("user");
		if (user.getPassword().trim().length() == 0) {
			errors.rejectValue("password","user", "Vui lòng nhập mật khẩu !");
		}else{
			if (!oldPass.getPassword().equals(user.getPassword()))
			{
				errors.rejectValue("password","user", "Mật khẩu cũ không chính xác !");
			}
		}
		if(errors.hasErrors())
		{
			return "user/changePass";
		}
		else
		{
			// lấy đối tượng Customer từ user của httpSession
			if (oldPass.getPassword().equals(user.getPassword())) {
				if(password1.trim().length() == 0)
				{
					model.addAttribute("message1", "Vui lòng nhập mật khẩu mới!!!");
				}else
				{
					if (!password1.equals(password2)) {
						model.addAttribute("message2", "Xác nhận mật khẩu không đúng!!!");
					} else if (password1.equals(password2)) {
						oldPass.setPassword(password1);
						Session session = sessionFactory.openSession();
						Transaction t = session.beginTransaction();
						try {
							session.update(oldPass);
							t.commit();
							model.addAttribute("message", "Đổi mật khẩu thành công!");
						} catch (Exception e) {
							t.rollback();
							model.addAttribute("message", "Đổi mật khẩu thất bại!");
						} finally {
							session.close();
						}
					}
				}			
			}
		}		
		return "user/changePass";
	}

	// Mở form đăng nhập
	@RequestMapping(method = RequestMethod.GET)
	public String formLogin(ModelMap model) {
		Customer user = new Customer();
		model.addAttribute("user", user);
		return "user/login";
	}

	// Thực hiện đăng nhập
	@RequestMapping(method = RequestMethod.POST)
	public String login(ModelMap model, @ModelAttribute("user") Customer user,HttpServletRequest request, BindingResult errors,
			@RequestParam(defaultValue = "false") boolean remember) {
		if (user.getId().trim().length() == 0) {
			errors.rejectValue("id","user", "Vui lòng nhập tên người dùng !");
		}else if(accountService.checkUsernameCustomer(user.getId()) == 0){
			errors.rejectValue("id","user", "Tài khoản người dùng không tồn tại !");
		}
		if (user.getPassword().trim().length() == 0) {
			errors.rejectValue("password","user", "Vui lòng nhập mật khẩu !");
		}else if(accountService.checkPasswordCustomer(user.getId(), user.getPassword()) == 0){
			errors.rejectValue("password","user", "Mật khẩu không chính xác !");
		}
		if(errors.hasErrors())
		{
			return "user/login";
		} else {
			if(customerService.checkUsernameActivated(user.getId()) == 0){
				model.addAttribute("message", "Tài khoản chưa được kích hoạt!");
				return "user/login";
			}else {
				Customer login = accountService.loginCustomer(user.getId(), user.getPassword());
				if (login != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", login);
					return "user/index";
				}
			}
			
		}
		return "user/login";
	}

	// Mở form đăng kí
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String formRegister(ModelMap model) {
		model.addAttribute("register", new Customer());
		return "user/register";
	}

	// Thực hiện đăng ký
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(@ModelAttribute("register") Customer customer, ModelMap model, BindingResult errors,
			@RequestParam(value = "file_image", required = false) MultipartFile photo, HttpServletRequest request) {
		if (customer.getFullName().trim().length() == 0) {
			errors.rejectValue("fullName", "register", "Vui lòng nhập họ tên!!!");
		}
		if (customer.getId().trim().length() == 0) {
			errors.rejectValue("id", "register", "Vui lòng nhập tên tài khoản!!!");
		} else if (customerService.checkUsername(customer.getId()) > 0) {
			errors.rejectValue("id", "register", "Tên tài khoản đã tồn tại!!!");
		}
		if (customer.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "register", "Vui lòng nhập mật khẩu!!!");
		}
		if (customer.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "register", "Vui lòng nhập email!!!");
		} else if (customerService.checkEmail(customer.getEmail()) > 0) {
			errors.rejectValue("email", "register", "Email đã tồn tại!!!");
		}
		if (customer.getBirthday() == null) {
			errors.rejectValue("birthday", "register", "Vui lòng nhập ngày sinh nhật!!!");
		}
		if (customer.getNumberPhone().trim().length() == 0) {
			errors.rejectValue("numberPhone", "register", "Vui lòng nhập số điện thoại!!!");
		}
		if (customer.getGender() == 0) {
			errors.rejectValue("gender", "register", "Vui lòng chọn giới tính!!!!");
		}
		if (customer.getAddress().trim().length() == 0) {
			errors.rejectValue("address", "register", "Vui lòng nhập địa chỉ!!!");
		}
		try {
			if (!photo.isEmpty()) {
				// Lấy tên ảnh
				String filePhoto = photo.getOriginalFilename();
				// Lấy đường dẫn ảnh trong project
				String path = application.getRealPath("/images/customers/" + filePhoto);
				System.out.println("path");
				// Chuyển thành đối tượng File rồi chuyển đổi cho đối tượng photo
				photo.transferTo(new File(path));
				customer.setPhoto(filePhoto);
			}
			else
			{
				errors.rejectValue("photo", "register", "Vui lòng chọn ảnh!!!");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (errors.hasErrors()) {
			return "user/register";
		} else {
			try {
				MimeMessage massage = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(massage, true);
				helper.setFrom("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
				helper.setTo(customer.getEmail());
				helper.setReplyTo("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
				helper.setSubject("Welcome: " + customer.getFullName());
				String url = request.getRequestURL().toString().replace("register",
						"activate/" + customer.getId() + ".htm");
				String text = "Xin chào: " + customer.getFullName()
						+ "<br> Bạn đã đăng kí tài khoản thành công tại LapTopMinhTuan"
						+ "<br> Hãy click vào đường dẫn này <a href='" + url
						+ "'> Kích hoạt </a> để kích hoạt tài khoản của bạn !";
				helper.setText(text, true);
				mailSender.send(massage);
			} catch (Exception ex) {
				model.addAttribute("message", "Lỗi gửi mail");
				return "user/register";
			}
			Session session = sessionFactory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(customer);
				t.commit();
				model.addAttribute("message", "Đăng ký thành công!");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Đăng ký thất bại!");
			} finally {
				session.close();
			}
		}
		return "user/register";
	}

	// Kích hoạt tài khoản
	@RequestMapping(value = "activate/{id}.htm", method = RequestMethod.GET)
	public String activate(ModelMap model, @PathVariable("id") String id) {
		Session session = sessionFactory.getCurrentSession();
		Customer user = (Customer) session.load(Customer.class, id);
		user.setActivated(true);
		session.update(user);
		return "redirect:/user";
	}

	// Đăng xuất
	@RequestMapping("logout")
	public String logout(ModelMap model) {
		httpSession.removeAttribute("user");
		cart.clear();
		return "redirect:/user";
	}
}
