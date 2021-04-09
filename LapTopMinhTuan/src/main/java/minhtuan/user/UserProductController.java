package minhtuan.user;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import minhtuan.admin.controller.MinhTuanController;
import minhtuan.admin.service.ProductService;
import minhtuan.model.Product;

@Controller
@Transactional
@RequestMapping(value = "user/product")
public class UserProductController extends MinhTuanController {
	@Autowired
	ProductService productService;

	@Autowired
	ServletContext application;

	@Autowired
	JavaMailSender mailSender;

	// Tìm kiếm sản phẩm
	@RequestMapping("search-product.htm")
	public String searchProduct(ModelMap model, HttpServletRequest request, @RequestParam("") String search) {
		if (productService.getRowProductSearchName(search) > 9) {
			if (request.getParameter("page") == null) {
				model.addAttribute("list", productService.loadProductPageByName("1", search));
			} else {
				model.addAttribute("list", productService.loadProductPageByName(request.getParameter("page"), search));
			}
			// Lấy số trang hiện tại, ceil lấy giá trị nguyên lên. vd: 3.4 -> 4
			double paging = Math.ceil(Double.valueOf(productService.getRowProductSearchName(search)) / 9);
			model.addAttribute("rowCount", paging);
			model.addAttribute("flat", "AllProductsByName");
		}else
		{
			model.addAttribute("list", productService.searchByNameProduct(search));
		}
		return "user/product-list";
	}

	// Show danh sách sản phẩm
	@RequestMapping("list")
	public String list(ModelMap model, HttpServletRequest request) {
		if (productService.getRowProduct() > 9) {
			// nếu trên url chưa có param page, tức là hiện tại nó đang ở trang 1
			if (request.getParameter("page") == null) {
				model.addAttribute("list", productService.loadProductPage("1"));
			} else {
				model.addAttribute("list", productService.loadProductPage(request.getParameter("page")));
			}
			// Lấy số trang hiện tại, ceil lấy giá trị nguyên lên. vd: 3.4 -> 4
			double paging = Math.ceil(Double.valueOf(productService.getRowProduct()) / 9);
			model.addAttribute("rowCount", paging);
			model.addAttribute("flat", "AllProducts");
		} else {
			model.addAttribute("list", productService.getAllProduct());
		}
		return "user/product-list";
	}

	// Show danh sách sản phẩm theo danh mục
	@RequestMapping(value = "list-by-category/{id}.htm")
	public String listCategory(ModelMap model, @PathVariable("id") Integer id, HttpServletRequest request) {
		if (productService.getRowProductCategory(String.valueOf(id)) > 9) {
			// nếu trên url chưa có param page, tức là hiện tại nó đang ở trang 1
			if (request.getParameter("page") == null) {
				model.addAttribute("list", productService.loadProductCategoryPage("1", String.valueOf(id)));
			} else {
				System.out.println("aaa");
				model.addAttribute("list",
						productService.loadProductCategoryPage(request.getParameter("page"), String.valueOf(id)));
			}
			// Lấy số trang hiện tại, ceil lấy giá trị nguyên lên. vd: 3.4 -> 4
			double paging = Math.ceil(Double.valueOf(productService.getRowProductCategory(String.valueOf(id))) / 9);
			model.addAttribute("rowCount", paging);
			model.addAttribute("flat", "AllProductCategory");
		} else {
			model.addAttribute("list", productService.getProductByCategory(String.valueOf(id)));
		}
		return "user/product-list";
	}

	// Show danh sách sản phẩm theo từng producer
	@RequestMapping("list-by-producer/{id}.htm")
	public String listProducer(ModelMap model, @PathVariable("id") Integer id, HttpServletRequest request) {
		if (productService.getRowProductProducer(String.valueOf(id)) > 9) {
			// nếu trên url chưa có param page, tức là hiện tại nó đang ở trang 1
			if (request.getParameter("page") == null) {
				model.addAttribute("list", productService.loadProductProducerPage("1", String.valueOf(id)));
			} else {
				System.out.println("aaa");
				model.addAttribute("list",
						productService.loadProductProducerPage(request.getParameter("page"), String.valueOf(id)));
			}
			// Lấy số trang hiện tại, ceil lấy giá trị nguyên lên. vd: 3.4 -> 4
			double paging = Math.ceil(Double.valueOf(productService.getRowProductProducer(String.valueOf(id))) / 9);
			model.addAttribute("rowCount", paging);
			model.addAttribute("flat", "AllProductProducer");
		} else {
			model.addAttribute("list", productService.getProductByProducer(String.valueOf(id)));
		}
		return "user/product-list";
	}

	// Xem chi tiết
	@RequestMapping("detail/{id}.htm")
	public String detailProduct(ModelMap model, HttpServletResponse response, @PathVariable("id") Integer id,
			@ModelAttribute("product") Product product) {
		product.setId(id);
		Session session = sessionFactory.getCurrentSession();
		session.refresh(product);
		model.addAttribute("soLuong", "1");
		return "user/product-detail";
	}


	@RequestMapping("type/saleoff.htm")
	public String listSpecial(ModelMap model, HttpServletRequest request) {
		if (productService.getRowProductSaleOff() > 9) {
			// nếu trên url chưa có param page, tức là hiện tại nó đang ở trang 1
			if (request.getParameter("page") == null) {
				model.addAttribute("list", productService.loadProductSaleOffPage("1"));
			} else {
				model.addAttribute("list", productService.loadProductSaleOffPage(request.getParameter("page")));
			}
			// Lấy số trang hiện tại, ceil lấy giá trị nguyên lên. vd: 3.4 -> 4
			double paging = Math.ceil(Double.valueOf(productService.getRowProductSaleOff()) / 9);
			model.addAttribute("rowCount", paging);
			model.addAttribute("flat", "AllProductSaleOff");
		} else {
			model.addAttribute("list", productService.getAllProductSaleOff());
		}
		return "user/product-list";
	}

}
