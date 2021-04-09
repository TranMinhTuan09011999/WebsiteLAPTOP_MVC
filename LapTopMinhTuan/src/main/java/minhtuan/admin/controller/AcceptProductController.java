package minhtuan.admin.controller;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import minhtuan.admin.service.ProductService;
import minhtuan.model.Admin;
import minhtuan.model.Product;

@Controller
@Transactional
@RequestMapping(value = "admin/product")
public class AcceptProductController extends MinhTuanController {

	@Autowired
	ProductService productService;
	@Autowired
	ServletContext application;

	// Trang chủ
	@RequestMapping(method = RequestMethod.GET)
	public String viewIndex(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("listProduct", productService.getAllProduct());
		model.addAttribute("listCategory", getCategory());
		model.addAttribute("listProducer", getProducer());
		if (productService.getRowProduct() > 10) {
			if (request.getParameter("page") == null) {
				model.addAttribute("listProduct", productService.loadProductPage("1"));
			} else {
				model.addAttribute("listProduct", productService.loadProductPage(request.getParameter("page")));
			}
			double paging = Math.ceil(Double.valueOf(productService.getRowProduct()) / 10);
			model.addAttribute("rowCount", paging);
		} else {
			model.addAttribute("listProduct", productService.getAllProduct());
			model.addAttribute("listCategory", getCategory());
			model.addAttribute("listProducer", getProducer());
		}
		return "admin/product-home";
	}

	// Form sửa hàng hóa
	@RequestMapping(value = "edit-product/{id}.htm", method = RequestMethod.GET)
	public String fromEdit(ModelMap model, @PathVariable(value = "id") Integer id) {
		model.addAttribute("product", productService.getIDProduct(id));
		return "admin/product-edit";
	}

	// Sửa hàng hóa
	@RequestMapping(value = "edit-product/{id}.htm", method = RequestMethod.POST)
	public String editProduct(ModelMap model, @ModelAttribute(value = "product") Product product,
			@PathVariable("id") Integer id,@RequestParam(value = "file_image", required = false) MultipartFile photo, BindingResult result ) {
		if (product.getProductDate() == null) {
			result.rejectValue("productDate", "DateTimeFormat");
		}
		if (product.getNameProduct().trim().length() == 0) {
			result.rejectValue("nameProduct", "error.product.nameProduct");
		}
		if (product.getQuantity() == null) {
			result.rejectValue("quantity", "error.product.quantity");
		}
		if (product.getUnitBrief().trim().length() == 0) {
			result.rejectValue("unitBrief", "error.product.unitBrief");
		}
		if (product.getUnitPrice() == null) {
			result.rejectValue("unitPrice", "error.product.unitPrice");
		}	
		try {
			if (!photo.isEmpty()) {
				//Lấy tên ảnh
				String filePhoto = photo.getOriginalFilename();
				//Lấy đường dẫn ảnh trong project
				String path = application.getRealPath("/images/products/" + filePhoto);
				//Chuyển thành đối tượng File rồi chuyển đổi cho đối tượng photo
				photo.transferTo(new File(path));
				product.setPhoto(filePhoto);
			}else
			{
				result.rejectValue("photo", "error.product.photo");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (result.hasErrors()) {
			return "admin/product-edit";
		} else {
			productService.updateProduct(product);
			model.addAttribute("listProduct", productService.getAllProduct());
		}
		return "redirect:/admin/product";
	}

	// Thay đổi hình ảnh trong xem chi tiết
	@RequestMapping(value = "changePhoto-detail/{id}.htm", method = RequestMethod.POST)
	public String detailPhoto(ModelMap model, @PathVariable("id") Integer id,
			@ModelAttribute(value = "product") Product product, @RequestParam("file_image") MultipartFile photo,
			BindingResult result) {
		try {
			if (!photo.getOriginalFilename().endsWith(".jpg") && !photo.getOriginalFilename().endsWith(".png")
					&& !photo.getOriginalFilename().endsWith(".jpeg") && !photo.getOriginalFilename().endsWith(".JPG")
					&& !photo.getOriginalFilename().endsWith(".PNG")
					&& !photo.getOriginalFilename().endsWith(".JPEG")) {
				result.rejectValue("photo", "message.photo.format");
			} else if (!photo.isEmpty()) {
				String filePhoto = photo.getOriginalFilename();
				String photoPath = application.getRealPath("/images/products/" + filePhoto);
				photo.transferTo(new File(photoPath));
				product.setPhoto(filePhoto);
			}
			productService.updateProduct(product);
			model.addAttribute("category", getCategory());
			model.addAttribute("producer", getProducer());
		} catch (Exception e) {
		}
		return "admin/product-detail";
	}

	// xác nhận hàng hóa
	@RequestMapping(value = "accept-product/{id}.htm")
	public String acceptProduct(ModelMap model, @PathVariable("id") Integer id,
			@ModelAttribute("product") Product product) {
		product = productService.getIDProduct(id);
		productService.updateProduct(product);
		model.addAttribute("listProduct", productService.getAllProduct());
		return "redirect:/admin/product";
	}

	//Hủy sản phẩm
	@RequestMapping(value = "cannel-product/{id}.htm", method = RequestMethod.GET)
	public String deleteProduct(ModelMap model, @PathVariable("id") Integer id,
			@ModelAttribute(value = "product") Product product) {
		productService.deleteProduct(product);
		model.addAttribute("listProduct", productService.getAllProduct());
		return "redirect:/admin/product";
	}

	// Xem chi tiết sản phẩm
	@RequestMapping(value = "detail/{id}.htm", method = RequestMethod.GET)
	public String detailProduct(ModelMap model, @PathVariable("id") Integer id) {
		model.addAttribute("product", productService.getIDProduct(id));
		return "admin/product-detail";
	}

	// Lọc trạng thái
	@RequestMapping(value = "filterStatus.htm", method = RequestMethod.GET)
	public String filterStatus(ModelMap model, @RequestParam("filter") String filter) {
		model.addAttribute("listCategory", getCategory());
		model.addAttribute("listProducer", getProducer());
		return "admin/product-home";
	}

	// Tìm kiếm
	@RequestMapping(value = "filter.htm", method = RequestMethod.GET)
	public String filterProduct(ModelMap model, @RequestParam("SP") String bySP, @RequestParam("DM") String byDM,
			@RequestParam("HSX") String byHSX) {
		if (byDM.equals("") && bySP.equals("") && byHSX.equals("")) {
			model.addAttribute("listProduct", productService.getAllProduct());
		} else if (bySP.equals("") && byHSX.equals("")) {
			model.addAttribute("listProduct", productService.getProductByCategory(byDM));
		} else if (bySP.equals("") && byDM.equals("")) {
			model.addAttribute("listProduct", productService.getProductByProducer(byHSX));
		} else if (bySP.equals("")) {
			model.addAttribute("listProduct", productService.getProducerAndCategory(byDM, byHSX));
		} else {
			model.addAttribute("listProduct", productService.searchBy3(bySP, byDM, byHSX));
		}
		model.addAttribute("listCategory", getCategory());
		model.addAttribute("listProducer", getProducer());
		return "admin/product-home";
	}
	//thêm sản phẩm
	@RequestMapping(value = "add-product", method = RequestMethod.GET)
	public String formAdd(ModelMap model) {
		model.addAttribute("product", new Product());
		return "admin/product-add";
	}
	
	//Thêm hàng hóa
		@RequestMapping(value = "add-product", method = RequestMethod.POST)
		public String addProduct( @ModelAttribute(value = "product") Product product,ModelMap model, BindingResult errors, @RequestParam(value = "file_image", required = false) MultipartFile photo, HttpServletRequest request) {
			Session session = sessionFactory.getCurrentSession();
						
			if (product.getNameProduct().trim().length() == 0) {
				errors.rejectValue("nameProduct", "error.product.nameProduct");
			}
			if (product.getQuantity() == null) {
				errors.rejectValue("quantity", "error.product.quantity");
			}
			if (product.getUnitBrief().trim().length() == 0) {
				errors.rejectValue("unitBrief", "error.product.unitBrief");
			}
			if (product.getProductDate() == null) {
				errors.rejectValue("productDate", "DateTimeFormat");
			}
			if (product.getUnitPrice() == null) {
				errors.rejectValue("unitPrice", "error.product.unitPrice");
			}
			try {
				if (!photo.isEmpty()) {
					System.out.println("aaaaaaaaaaaaaaa");
					//Lấy tên ảnh
					String filePhoto = photo.getOriginalFilename();
					//Lấy đường dẫn ảnh trong project
					String path = application.getRealPath("/images/products/" + filePhoto);
					//Chuyển thành đối tượng File rồi chuyển đổi cho đối tượng photo
					photo.transferTo(new File(path));
					product.setPhoto(filePhoto);
				}else
				{
					errors.rejectValue("photo", "error.product.photo");
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
			if(errors.hasErrors())
			{
				return "admin/product-add";
			}else
			{
				Session session1 = sessionFactory.openSession();
				Transaction t = session1.beginTransaction();
				try {
					session.save(product);
					t.commit();
					model.addAttribute("message", "Đăng ký thành công!");
				} catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Đăng ký thất bại!");
				} finally {
					session1.close();
				}			
			}
			return "redirect:/admin/product";
		}
	
}
