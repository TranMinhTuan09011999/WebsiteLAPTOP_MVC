package minhtuan.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import minhtuan.admin.dao.OrderDAO;
import minhtuan.model.Order;
import minhtuan.model.OrderDetail;

@Service("orderService")
@Transactional
public class OrderService {

	@Autowired
	OrderDAO orderDAO;

	public void updateOrder(Order order) {
		orderDAO.updateOrder(order);
	}

	public Order getIDOrder(Integer id) {
		return orderDAO.getIDOrder(id);
	}

	public List<Order> getAllOrder() {
		return orderDAO.getAllOrder();
	}

	public List<Order> searchOrder(String search) {
		return orderDAO.searchOrder(search);
	}

	public List<Order> filterStatus(String filter) {
		return orderDAO.filterStatus(filter);
	}

	public List<Order> loadOrderPage(String page) {
		return orderDAO.loadOrderPage(page);
	}

	public int getRowOrder() {
		return orderDAO.getRowOrder();
	}

	public List<OrderDetail> getAllOrderDetailByID(Integer id) {
		return orderDAO.getAllOrderDetailByID(id);
	}
}
