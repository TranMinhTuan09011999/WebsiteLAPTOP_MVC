package minhtuan.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import minhtuan.admin.dao.OrderDetailDAO;
import minhtuan.model.OrderDetail;

@Service("orderDetailService")
@Transactional
public class OrderDetailService {

	@Autowired
	OrderDetailDAO orderDetailDAO;

	//Sửa đặt hàng
	public void updateOrderDetail(OrderDetail order) {
		orderDetailDAO.updateOrderDetail(order);
	}

	//xóa đặt hàng
	public void deleteOrderDetail(OrderDetail order) {
		orderDetailDAO.deleteOrderDetail(order);
	}

	//Lấy id đặt hàng
	public OrderDetail getIDOrderDetail(Integer id) {
		return orderDetailDAO.getIDOrderDetail(id);
	}

	public List<OrderDetail> getAllOrderDetail(Integer id) {
		return orderDetailDAO.getAllOrderDetail(id);
	}

	//Tìm kiếm hàng đặt
	public List<OrderDetail> searchOrderDetail(String search) {
		return orderDetailDAO.searchOrderDetail(search);
	}

	//Phân trang
	public List<OrderDetail> loadOrderDetailPage(String page, Integer id) {
		return orderDetailDAO.loadOrderDetailPage(page, id);
	}

	//lấy số lượng hàng đặt
	public int getRowOrderDetail(Integer id) {
		return orderDetailDAO.getRowOrderDetail(id);
	}
}
