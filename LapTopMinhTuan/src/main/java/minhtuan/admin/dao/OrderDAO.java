package minhtuan.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minhtuan.model.Order;
import minhtuan.model.OrderDetail;

@Repository
public class OrderDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public void updateOrder(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.update(order);
	}
	
	public Order getIDOrder(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order) session.get(Order.class, id);
		return order;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getAllOrder() {
		Session session = sessionFactory.getCurrentSession();
		List<Order> list = session.createQuery("FROM Order").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Order> searchOrder(String search) {
		Session session = sessionFactory.getCurrentSession();
		List<Order> list = session.createQuery(
				"FROM Order WHERE customerID LIKE '%" + search + "%' OR ID LIKE '%" + search + "%' OR Amount LIKE '%"
						+ search + "%' OR Address LIKE '%" + search + "%' OR Receiver LIKE '%" + search + "%'")
				.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Order> filterStatus(String filter) {
		Session session = sessionFactory.getCurrentSession();
		List<Order> list = session.createQuery("FROM Order WHERE Status LIKE '%" + filter + "%'").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Order> loadOrderPage(String page) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Order");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			result = Integer.parseInt(temp) - 10;
		}
		query.setFirstResult(result);
		query.setMaxResults(10);
		return query.list();
	}

	@SuppressWarnings("unused")
	public int getRowOrder() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Order> list = session.createQuery("FROM Order").list();
		int i = 0;
		for (Order order : list) {
			i++;
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	public List<OrderDetail> getAllOrderDetailByID(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM OrderDetail WHERE order.id = '" + id + "' ");
		return query.list();
	}

}
