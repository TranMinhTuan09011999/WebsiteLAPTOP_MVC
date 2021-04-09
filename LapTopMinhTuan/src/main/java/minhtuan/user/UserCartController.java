package minhtuan.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import minhtuan.admin.controller.MinhTuanController;
import minhtuan.model.Product;
import minhtuan.other.Json;

@Controller
@Transactional
@RequestMapping("shopping-cart")
public class UserCartController extends MinhTuanController {

	
	@RequestMapping("index")
	public String index() {
		return "user/shopping-cart";
	}

	// Thêm sản phẩm vào giỏ hàng
	//@PathVariable Integer id lấy id của p bên giao diện index.jsp
	@RequestMapping(value="add/{id}")
	public String addToCart(@PathVariable Integer id,HttpServletRequest request) {
		Integer quantity2 = Integer.parseInt(request.getParameter("soLuong"));
		System.out.println("Số lương: " + quantity2);
		cart.add(id,quantity2);
		return "redirect:/shopping-cart/index"; /*redirect để chuyển trực đến controller có url shopping-cart/index */
	}

	
	@RequestMapping("remove/{id}")
	public String removeFromCart(@PathVariable Integer id) {
		cart.remove(id);
		return "redirect:/shopping-cart/index";
	}


	// Xóa tất cả product trong giỏ hàng
	@RequestMapping("clear")
	public String clearCart() {
		cart.clear();
		return "user/shopping-cart";
	}

	// cập nhập lại giỏ hàng
	@RequestMapping(value="update")
	public String updateCart(HttpServletRequest request) {
		for (Product p : cart.getItems()) {
			String id = p.getId().toString();
			int newQuantity = Integer.parseInt(request.getParameter(id));
			p.setQuantity(newQuantity);
		}
		return "user/shopping-cart";
	}
}
