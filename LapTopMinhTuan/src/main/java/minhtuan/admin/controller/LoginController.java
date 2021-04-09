package minhtuan.admin.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.mail.javamail.JavaMailSender;

import minhtuan.admin.service.AccountService;
import minhtuan.model.Admin;
import minhtuan.model.Customer;

@Controller
@Transactional
public class LoginController {

	@Autowired
	AccountService accountService;
	@Autowired
	ServletContext application;
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	HttpSession httpSession;

	// Form Login
	@RequestMapping(value = "admin", method = RequestMethod.GET)
	public String formLogin(ModelMap model) {
		model.addAttribute("loginForm", new Admin());
		return "admin/login";
	}

	// Login
	@RequestMapping(value = "admin", method = RequestMethod.POST)
	public String login(ModelMap model, @ModelAttribute("loginForm") Admin admin, HttpServletRequest request,
			HttpServletResponse response, BindingResult result) {
		if (admin.getUsername().trim().length() == 0) {
			result.rejectValue("username", "admin","Email không để trống!!!!");
		} else if (accountService.checkUsername(admin.getUsername()) == 0) {
			result.rejectValue("username","admin" ,"Email sai!!!");
		}
		if (admin.getPassword().trim().length() == 0) {
			result.rejectValue("password", "admin","Vui lòng nhập mật khẩu!!!");
		} else if (accountService.checkPassword(admin.getPassword()) == 0) {
			result.rejectValue("password", "admin","Sai mật khẩu");
		}

		if (result.hasErrors()) {
			return "admin/login";
		} else {
			Admin login = accountService.login(admin.getUsername(), admin.getPassword());
			if (login != null) {
				HttpSession session = request.getSession();
				session.setAttribute("account", login);
				return "redirect:/admin/home";
			}
		}
		return "admin/login";
	}

	// Mở form sửa mật khẩu
	@RequestMapping(value = "admin/change-pass/{username}.htm", method = RequestMethod.GET)
	public String formChangePass(ModelMap model, @PathVariable(value = "username") String username) {
		model.addAttribute("account", accountService.getIDAdmin(username));
		return "admin/account-changePass";
	}

	// Đổi mật khẩu
	@RequestMapping(value = "admin/change-pass/{username}.htm", method = RequestMethod.POST)
	public String changePass(ModelMap model, @PathVariable(value = "username") String username,
			@ModelAttribute(value = "account") Admin admin, BindingResult result,
			@RequestParam("password1") String password1, @RequestParam("password2") String password2) {
		if (password1.trim().length() == 0) {
			result.rejectValue("password", "error.password.null");
		} else if (password2.trim().length() == 0) {
			result.rejectValue("password", "error.password.null");
		}
		if (result.hasErrors()) {
			return "admin/account-changePass";
		} else {
			if (!password1.equals(password2)) {
				result.rejectValue("password", "error.password.error");
				return "admin/account-changePass";
			} else if (password1.equals(admin.getPassword()) == password2.equals(admin.getPassword())) {
				Admin account = accountService.getIDAdmin(username);
				account.setPassword(password2);
				accountService.updateAdmin(account);
			}
		}
		return "redirect:/admin/account";
	}

	// Form quên mật khẩu
	@RequestMapping(value = "admin/forgot.htm", method = RequestMethod.GET)
	public String showForgotFormAdmin(ModelMap model) {
		model.addAttribute("account", new Admin());
		model.addAttribute("message", "Vui lòng nhập email để lấy lại mật khẩu !");
		return "admin/forgot";
	}

	// Lấy lại mật khẩu
	@RequestMapping(value = "admin/forgot.htm", method = RequestMethod.POST)
	public String submitForgotFormAdmin(ModelMap model, @ModelAttribute("account") Admin account, BindingResult result) {
		String pass = "";
		if (account.getUsername().trim().length() == 0) {
			result.rejectValue("username", "account", "Vui lòng nhập email!!!");
			model.addAttribute("message", "Vui lòng nhập email để lấy lại mật khẩu !");
		}else
		{
			if(accountService.checkUsername(account.getUsername()) == 0)
			{
				result.rejectValue("username", "account", "Sai địa chỉ email!!!");
				model.addAttribute("message", "Vui lòng nhập email để lấy lại mật khẩu !");
			}else
			{
				Admin ad = new Admin();
				ad = accountService.forgot(account.getUsername());
				pass = ad.getPassword();
			}
		}
		if (result.hasErrors()) {
			return "admin/forgot";
		}else
		{
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);
				helper.setFrom("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
				helper.setTo(account.getUsername());
				helper.setReplyTo("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
				helper.setSubject("Nhận Lại Mật khẩu‹");
				helper.setText("Địa chỉ email: " + account.getUsername() + " <br>+ Mật khẩu của bạn là : "
						+ pass, true);

				mailSender.send(message);
				model.addAttribute("message", "Mật khẩu đã được gán qua email của bạn !");
			} catch (Exception ex) {
				model.addAttribute("message", ex.getMessage());
			}
		}
		model.addAttribute("account", new Admin());
		return "admin/forgot";		
	}

	// Logout
	@RequestMapping(value = "admin/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.removeAttribute("account");
		return "redirect:/admin/home";
	}

}
