package minhtuan.user;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import minhtuan.model.Category;
import minhtuan.model.Producer;
import minhtuan.model.Product;
import minhtuan.other.ShoppingCart;

@Controller
@Transactional
@RequestMapping(value = "user")
public class UserHomeContoller {

	@Autowired
	JavaMailSender mailSender;
	@Autowired
	SessionFactory sessionFactory;

	@RequestMapping(value = "about")
	public String userAbout() {
		return "user/about";
	}

	@RequestMapping(value = "home")
	public String userHome() {
		return "user/index";
	}

	@RequestMapping(value = "contact")
	public String userContact() {
		return "user/contact";
	}

	@Autowired
	protected ShoppingCart cart;

	@ModelAttribute("cart")
	public ShoppingCart getShoppingCart() {
		return cart;
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("category")
	public List<Category> getCategory() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Category").list();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("producer")
	public List<Producer> getProducer() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Producer").list();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("saleOffProducts")
	public List<Product> get9SaleOffProducts() {
		Session session = sessionFactory.getCurrentSession();
		//Tìm tất cả các sản phẩm có status = 1 và discount > 0 được sắp thứ tự giảm dần theo discount
		String hql = "FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC";
		Query query = session.createQuery(hql);
		//query sẽ chỉ chứa 9 giá trị max đầu tiên
		query.setMaxResults(9);
		return query.list();
	}
}
