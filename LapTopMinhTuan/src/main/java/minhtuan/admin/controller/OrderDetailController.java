package minhtuan.admin.controller;

import javax.servlet.http.HttpServletRequest;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import minhtuan.admin.service.OrderDetailService;
import minhtuan.admin.service.OrderService;
import minhtuan.model.OrderDetail;
import minhtuan.model.Order;

@Controller
@Transactional
public class OrderDetailController {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "admin/order", method = RequestMethod.GET)
	public String indexOrder(ModelMap model, HttpServletRequest request) {
		//Nếu chi tiết đơn đặt hàng lớn hơn 10 sản phẩm
		if (orderService.getRowOrder() > 10) {
			//nếu trên url chưa có param page, tức là hiện tại nó đang ở trang 1
			if (request.getParameter("page") == null) {
				model.addAttribute("listOrder", orderService.loadOrderPage("1"));
			} else {
				model.addAttribute("listOrder",
						orderService.loadOrderPage(request.getParameter("page")));
			}
			//Lấy số trang hiện tại, ceil lấy giá trị nguyên lên. vd: 3.4 -> 4
			double paging = Math.ceil(Double.valueOf(orderService.getRowOrder()) / 10);
			model.addAttribute("rowCount", paging);
		} else {
			model.addAttribute("listOrder", orderService.getAllOrder());
		}
		return "admin/orderDetail-home";
	}

	@RequestMapping("admin/delivery-order/{id}")
	public String delivery(ModelMap model, @PathVariable("id") Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order) session.get(Order.class, id);
		order.setId(id);
		order.setStatus(4);
		session.update(order);
		model.addAttribute("order", order);
		return "redirect:/admin/order";
	}
	
	@RequestMapping("admin/cancel/{id}")
	public String Cancel(ModelMap model, @PathVariable("id") Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order) session.get(Order.class, id);
		order.setId(id);
		order.setStatus(2);
		session.update(order);
		model.addAttribute("order", order);
		return "redirect:/admin/order";
	}
	
	//Trang chủ dành cho admin xem đặt hàng
	@RequestMapping(value = "admin/orderDetail/{id}", method = RequestMethod.GET)
	public String indexOrder1(ModelMap model, HttpServletRequest request, @PathVariable("id") Integer id) {
		//Nếu chi tiết đơn đặt hàng lớn hơn 10 sản phẩm
		if (orderDetailService.getRowOrderDetail(id) > 10) {
			//nếu trên url chưa có param page, tức là hiện tại nó đang ở trang 1
			if (request.getParameter("page") == null) {
				model.addAttribute("listOrderDetail", orderDetailService.loadOrderDetailPage("1",id));
			} else {
				model.addAttribute("listOrderDetail",
						orderDetailService.loadOrderDetailPage(request.getParameter("page"),id));
			}
			//Lấy số trang hiện tại, ceil lấy giá trị nguyên lên. vd: 3.4 -> 4
			double paging = Math.ceil(Double.valueOf(orderDetailService.getRowOrderDetail(id)) / 10);
			model.addAttribute("rowCount", paging);
		} else {
			model.addAttribute("listOrderDetail", orderDetailService.getAllOrderDetail(id));
		}
		return "admin/orderDetail-home1";
	}
	
	// Mở form sửa đặt hàng dành cho admin
	@RequestMapping(value = "admin/orderDetail/edit-orderDetail/{id}.htm", method = RequestMethod.GET)
	public String formEdit(ModelMap model, @PathVariable("id") Integer id) {
		model.addAttribute("orderDetail", orderDetailService.getIDOrderDetail(id));
		return "admin/orderDetail-edit";
	}

	// thực hiện sửa đặt hàng dành cho admin
	@RequestMapping(value = "admin/orderDetail/edit-orderDetail/{id}.htm", method = RequestMethod.POST)
	public String editOrder(ModelMap model, @PathVariable("id") Integer id,
			@ModelAttribute("orderDetail") OrderDetail orderDetail) {
		orderDetailService.updateOrderDetail(orderDetail);
		model.addAttribute("listOrderDetail", orderDetailService.getAllOrderDetail(id));
		return "redirect:/admin/order";
	}

	// Thực hiện xóa đặt hàng cho admin
	@RequestMapping(value = "admin/orderDetail/delete-orderDetail/{id}.htm", method = RequestMethod.GET)
	public String deleteOrder(ModelMap model, @PathVariable("id") Integer id,
			@ModelAttribute("orderDetail") OrderDetail orderDetail) {
		orderDetailService.deleteOrderDetail(orderDetail);
		model.addAttribute("listOrderDetail", orderDetailService.getAllOrderDetail(id));
		return "redirect:/admin/orderDetail/{id}";
	}

	// Thực hiện tìm kiếm admin
	@RequestMapping(value = "admin/orderDetail/search.htm", method = RequestMethod.GET)
	public String searchOrderDetail(ModelMap model, @RequestParam("search") String search) {
		model.addAttribute("listOrderDetail", orderDetailService.searchOrderDetail(search));
		return "admin/orderDetail-home1";
	}
	
	// Thực hiện tìm kiếm Order
	@RequestMapping(value = "admin/order/search.htm", method = RequestMethod.GET)
	public String searchOrder(ModelMap model, @RequestParam("search") String search) {
		model.addAttribute("listOrder", orderService.searchOrder(search));
		return "admin/orderDetail-home";
	}
}
