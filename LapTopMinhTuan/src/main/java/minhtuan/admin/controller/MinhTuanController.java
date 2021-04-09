package minhtuan.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import minhtuan.other.ShoppingCart;
import minhtuan.model.Product;
import minhtuan.model.Category;
import minhtuan.model.Producer;

@Controller
@Transactional
public class MinhTuanController {

	@Autowired
	protected SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@ModelAttribute("saleOffProducts")
	public List<Product> get9SaleOffProducts() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(9);
		return query.list();
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
	@RequestMapping(value = "admin/home")
	public String adminHome() {
		return "admin/index";
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("productRevenue")
	public List<Object[]> getProRevenue() {
		String hql = "SELECT " + " d.product.nameProduct, " + " SUM(d.quantity*d.amount*(1-d.discount)) "
				+ " FROM OrderDetail d " + " WHERE d.order.status = 4 " + " GROUP BY d.product.nameProduct";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("categoryRevenue")
	public List<Object[]> getCateRevenue() {
		String hql = "SELECT " + " d.product.category.nameCategory, " + " SUM(d.quantity*d.amount*(1-d.discount)) "
				+ " FROM OrderDetail d " + " WHERE d.order.status = 4 " + " GROUP BY d.product.category.nameCategory";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("producerRevenue")
	public List<Object[]> getSupRevenue() {
		String hql = "SELECT " + " d.product.producer.nameProducer, " + " SUM(d.quantity*d.amount*(1-d.discount)) "
				+ " FROM OrderDetail d " + " WHERE d.order.status = 4 " + " GROUP BY d.product.producer.nameProducer";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("yearRevenue")
	public List<Object[]> getYearRevenue() {
		String hql = "SELECT " + " YEAR(d.order.orderDate), " + " SUM(d.quantity*d.amount*(1-d.discount)) "
				+ " FROM OrderDetail d " + " WHERE d.order.status = 4 " + " GROUP BY YEAR(d.order.orderDate)";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}


	@Autowired
	protected ShoppingCart cart;

	@ModelAttribute("cart")
	public ShoppingCart getShoppingCart() {
		return cart;
	}

}
