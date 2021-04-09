package minhtuan.admin.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import minhtuan.admin.service.AccountService;
import minhtuan.model.Admin;

@Controller
@Transactional
@RequestMapping(value = "admin/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	JavaMailSender mailSender;

	// Trang chủ
	//Request để lấy giá trị page ở trang trước
	@RequestMapping(method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {
		if (accountService.getRowAdmin() > 10) {
			//Lúc vào thì nó sẽ load trang đầu tiên
			if (request.getParameter("page") == null) {
				model.addAttribute("listAccount", accountService.loadAdminPage("1"));
			} else {
				model.addAttribute("listAccount", accountService.loadAdminPage(request.getParameter("page")));
			}
			//Math.ceil trả về số nguyên nhỏ nhất lớn hơn hoặc bằng
			double paging = Math.ceil(Double.valueOf(accountService.getRowAdmin()) / 10);
			model.addAttribute("rowCount", paging);
		} else {
			model.addAttribute("listAccount", accountService.getAllAdmin());
		}
		return "admin/account-home";
	}

	// Mở form thêm tài khoản
	@RequestMapping(value = "add-account.htm", method = RequestMethod.GET)
	public String formAdd(ModelMap model) {
		model.addAttribute("account", new Admin());
		return "admin/account-add";
	}

	// Lưu tài khoản
	@RequestMapping(value = "save-account.htm", method = RequestMethod.POST)
	public String saveAccount(ModelMap model, @ModelAttribute("account") @Validated Admin admin, BindingResult result) {
		if (admin.getUsername().trim().length() == 0) {
			result.rejectValue("username", "error.account.email");
		} else if (accountService.checkUsername(admin.getUsername()) > 0) {
			result.rejectValue("username", "message.email");
		}
		if (admin.getPassword().trim().length() == 0) {
			result.rejectValue("password", "error.account.password");
		}
		if (admin.getFullName().trim().length() == 0) {
			result.rejectValue("fullName", "error.account.fullName");
		}
		if (result.hasErrors()) {
			return "admin/account-add";
		} else {
			try {
				MimeMessage massage = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(massage, true);
				helper.setFrom("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
				helper.setTo(admin.getUsername());
				helper.setReplyTo("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
				helper.setSubject("Welcome: " + admin.getFullName());
				String text = "Bạn đã đăng ký admin thành công";
				helper.setText(text, true);
				mailSender.send(massage);
			} catch (Exception ex) {
				model.addAttribute("message", "Lỗi gởi mail!!!!");
				return "admin/account-add";
			}
			accountService.insertAdmin(admin);
		}	
		return "redirect:/admin/account";
	}

	//Mở form sửa tài khoản
		@RequestMapping(value = "edit-account/{username}.htm", method = RequestMethod.GET)
		public String formEdit(ModelMap model, @PathVariable("username") String username) {
			model.addAttribute("account", accountService.getIDAdmin(username));
			return "admin/account-edit";
		}
	
	// Sửa dữ liệu
	@RequestMapping(value = "edit-account/{username}.htm", method = RequestMethod.POST)
	public String editAccount(ModelMap model, @PathVariable("username") String username,
			@ModelAttribute("account") Admin admin, BindingResult result,
			@RequestParam("fullname") String fullname, HttpServletRequest request) {
		if (fullname.trim().length() == 0) {
			result.rejectValue("fullname1", "Vui lòng nhập tên mới");
		}
		if (result.hasErrors()) {
			return "admin/account-edit";
		}else
		{
			Admin account = accountService.getIDAdmin(username);
			account.setFullName(fullname);
			accountService.updateAdmin(account);
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
		}
		
		return "redirect:/admin/account";
	}

	// Xóa dữ liệu
	@RequestMapping(value = "delete-account/{username}.htm", method = RequestMethod.GET)
	public String deleteAccount(ModelMap model, @PathVariable("username") String username,
			@ModelAttribute(value = "account") Admin admin) {
		accountService.deleteAdmin(admin);
		return "redirect:/admin/account";
	}

	// Tìm kiếm, @RequestParam("search") lấy giá trị của ô input tìm kiếm thông qua name 
	@RequestMapping(value = "search.htm", method = RequestMethod.GET)
	public String searchAccount(ModelMap model, @RequestParam("search") String search) {
		model.addAttribute("listAccount", accountService.searchAdmin(search));
		return "admin/account-home";
	}

}
