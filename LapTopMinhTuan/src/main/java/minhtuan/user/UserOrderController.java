package minhtuan.user;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import minhtuan.admin.controller.MinhTuanController;
import minhtuan.model.Customer;
import minhtuan.model.Order;
import minhtuan.model.OrderDetail;
import minhtuan.model.Product;
import minhtuan.other.PagerModel;

@Controller
@RequestMapping("order")
public class UserOrderController extends MinhTuanController {

	@Autowired
	HttpSession httpSession;

	@Autowired
	JavaMailSender mailSender;

	// Show danh sách sản phẩm mua
	@RequestMapping("product")
	public String orderProduct(ModelMap model, HttpServletRequest request) {
		Session session = sessionFactory.getCurrentSession();
		//
		Customer user = (Customer) httpSession.getAttribute("user");
		String hqlCount = "SELECT COUNT(od.product) FROM OrderDetail od WHERE od.order.status=3 and od.order.customer.id=:cid";
		Long rowCount = (Long) session.createQuery(hqlCount).setParameter("cid", user.getId()).uniqueResult();
		PagerModel pager = new PagerModel();
		pager = PagerModel.getPager("acpager", 9, rowCount.intValue(), request);
		pager.setRowCount(rowCount.intValue());
		pager.navigate(request);
		//
		String hql = "SELECT DISTINCT od.product FROM OrderDetail od WHERE od.order.status=3 and od.order.customer.id=:cid";
		Query query = session.createQuery(hql);
		query.setParameter("cid", user.getId());
		query.setFirstResult(pager.getStartRow());
		query.setMaxResults(pager.getPageSize());
		List<Product> list = query.list();
		model.addAttribute("list", list);
		model.addAttribute("flat", "../order/product");
		return "user/product-list";
	}

	// Show danh sách mặt hàng
	@RequestMapping("list")
	public String listOrder(ModelMap model) {
		Session session = sessionFactory.getCurrentSession();
		Customer user = (Customer) httpSession.getAttribute("user");
		String hql = "FROM Order WHERE customer.id=:cid";
		Query query = session.createQuery(hql);
		query.setParameter("cid", user.getId());
		model.addAttribute("order", query.list());
		return "user/order-list";
	}

	// Show danh sách đặt hàng chi tiết
	@RequestMapping("detail/{id}")
	public String detailOrder(ModelMap model, @PathVariable("id") Integer id) {
		Session session = sessionFactory.getCurrentSession();

		Order order = new Order();
		order.setId(id);
		session.refresh(order);

		String hql = "FROM OrderDetail WHERE order.id=" + id;
		Query query = session.createQuery(hql);

		model.addAttribute("order", order);
		model.addAttribute("detail", query.list());

		return "user/order-detail";
	}

	// kiểm tra lại thông tin người dùng
	@RequestMapping("checkout")
	public String checkout(ModelMap model, HttpServletRequest request) {

		Customer user = (Customer) httpSession.getAttribute("user");
		Order order = new Order();
		order.setCustomer(user);
		order.setOrderDate(new Date());
		order.setAmount(cart.getAmount());
		order.setReceiver(user.getFullName());
		model.addAttribute("order", order);

		return "user/checkout";
	}

	// Đặt hàng
	@RequestMapping("purchase")
	public String purchase(ModelMap model, @ModelAttribute(value = "order") Order order, HttpServletRequest request,
			BindingResult errors) {
		if (order.getReceiver().trim().length() == 0) {
			errors.rejectValue("receiver","order", "Vui lòng nhập tên người nhận !");
		}
		if (order.getNumberPhone().trim().length() == 0) {
			errors.rejectValue("numberPhone","order", "Vui lòng nhập số điện thoại !");
		}
		if (order.getAddress().trim().length() == 0) {
			errors.rejectValue("address","order", "Vui lòng nhập địa chỉ !");
		}
		if (errors.hasErrors()) {
			return "user/checkout";
		} else {
			Customer user = (Customer) httpSession.getAttribute("user");
			String email = user.getEmail();
			System.out.println("id: " + order.getId());
			System.out.println("amount: " + order.getAmount());
			System.out.println("amount: " + order.getAmount());
			Session session = sessionFactory.getCurrentSession();
			try {
				session.save(order);
				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message, true);
					helper.setFrom("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
					helper.setTo(email);
					helper.setReplyTo("LapTopMinhTuan <tranminhtuannhj@gmail.com>");
					helper.setSubject("Thông tin đơn đặt hàng");
					String url = request.getRequestURL().toString().replace("purchase",
							"detail/" + order.getId() + ".htm");
					;
					helper.setText("Bạn đã đặt hàng ở LapTopMinhTuan thành công!!! "
							+ "<br> Xem đơn hàng tại: <a href='" + url + "'> Xem đơn hàng</a>", true);
					mailSender.send(message);
				} catch (Exception ex) {
					model.addAttribute("message", "Lỗi gửi mail");
				}

				for (Product p : cart.getItems()) {
					OrderDetail detail = new OrderDetail();
					detail.setOrder(order);
					detail.setProduct(p);
					detail.setAmount(p.getUnitPrice());
					detail.setQuantity(p.getQuantity());
					detail.setDiscount(p.getDiscount());
					session.save(detail);
					Product product = (Product) session.get(Product.class, p.getId());
					int newQuantity = product.getQuantity() - p.getQuantity();
					product.setQuantity(newQuantity);
					session.update(product);
				}
				cart.clear();
				return "redirect:/order/detail/" + order.getId();
			} catch (Exception e) {
				model.addAttribute("message", "Đặt hàng thất bại");
			}
		}
		return "user/checkout";
	}

	@RequestMapping("cannel-order/{id}")
	public String cannel(ModelMap model, @PathVariable("id") Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order) session.get(Order.class, id);
		order.setId(id);
		order.setStatus(2);
		session.update(order);
		model.addAttribute("order", order);
		return "redirect:/order/list";
	}
}
