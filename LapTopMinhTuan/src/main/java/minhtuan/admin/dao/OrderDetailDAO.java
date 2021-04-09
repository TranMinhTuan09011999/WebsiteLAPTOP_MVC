package minhtuan.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minhtuan.model.OrderDetail;

@Repository
public class OrderDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void updateOrderDetail(OrderDetail order) {
		Session session = sessionFactory.getCurrentSession();
		session.update(order);
	}

	//Xóa đặt hàng
	public void deleteOrderDetail(OrderDetail order) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(order);
	}

	public OrderDetail getIDOrderDetail(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		OrderDetail order = (OrderDetail) session.get(OrderDetail.class, id);
		return order;
	}

	@SuppressWarnings("unchecked")
	public List<OrderDetail> getAllOrderDetail(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderDetail> list = session.createQuery("FROM OrderDetail WHERE order.id = '"+ id +"' ").list();
		return list;
	}

	//Tìm kiếm
	@SuppressWarnings("unchecked")
	public List<OrderDetail> searchOrderDetail(String search) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderDetail> list = session
				.createQuery("FROM OrderDetail WHERE amount LIKE '%" + search + "%' OR order.receiver LIKE '%" + search
						+ "%' OR order.address LIKE '%" + search + "%' OR order.customer.fullName LIKE '%" + search
						+ "%' OR product.nameProduct LIKE '%" + search + "%' OR quantity LIKE '%" + search + "%'")
				.list();
		return list;
	}

	//Phân trang
	@SuppressWarnings("unchecked")
	public List<OrderDetail> loadOrderDetailPage(String page, Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM OrderDetail WHERE order.id = '"+ id +"' ");
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
	
	//Lấy số lượng hàng đặt
	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowOrderDetail(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		List<OrderDetail> list = session.createQuery("FROM OrderDetail WHERE order.id = '"+ id + "' ").list();
		int i = 0;
		for (OrderDetail order : list) {
			i++;
		}
		return i;
	}
}
