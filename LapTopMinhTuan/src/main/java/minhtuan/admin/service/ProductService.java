package minhtuan.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import minhtuan.admin.dao.ProductDAO;
import minhtuan.model.Product;

@Service("productService")
@Transactional
public class ProductService {

	@Autowired
	private ProductDAO productDAO;

	//thêm sản phẩm
	public void insertProduct(Product product) {
		productDAO.insertProduct(product);
	}

	//Cập nhật sản phẩm
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}

	//Xóa sản phẩm
	public void deleteProduct(Product product) {
		productDAO.deleteProduct(product);
	}

	//Lấy sản phẩm theo id
	public Product getIDProduct(int id) {
		return productDAO.getIDProduct(id);
	}

	//Lấy tất cả các sản phẩm
	public List<Product> getAllProduct() {
		return productDAO.getAllProduct();
	}

	//Tìm kiếm sản phẩm
	public List<Product> searchProduct(String search) {
		return productDAO.searchProduct(search);
	}

	//Tìm kiếm theo cả 3
	public List<Product> searchBy3(String search, String idCategory, String idProducer) {
		return productDAO.searchBy3(search, idCategory, idProducer);
	}

	//lấy sản phẩm theo tên hãng
	public List<Product> getProductByProducer(String idProducer) {
		return productDAO.getProductByProducer(idProducer);
	}

	//Lấy sản phẩm theo danh mục
	public List<Product> getProductByCategory(String idCategory) {
		return productDAO.getProductByCategory(idCategory);
	}

	//Lấy danh sách sản phẩm theo tên hãng và danh mục
	public List<Product> getProducerAndCategory(String idCategory, String idProducer) {
		return productDAO.getProducerAndCategory(idProducer, idCategory);
	}

	//Phân trang
	public List<Product> loadProductPage(String page) {
		return productDAO.loadProductPage(page);
	}

	public int getRowProduct() {
		return productDAO.getRowProduct();
	}
	
	//Phân trang sản phẩm theo category
	public List<Product> loadProductCategoryPage(String page, String id) {
		return productDAO.loadProductCategoryPage(page,id);
	}

	public int getRowProductCategory(String id) {
		return productDAO.getRowProductCategory(id);
	}
	
	//Phân trang sản phẩm theo producer
	public List<Product> loadProductProducerPage(String page, String id) {
		return productDAO.loadProductProducerPage(page,id);
	}

	public int getRowProductProducer(String id) {
		return productDAO.getRowProductProducer(id);
	}
	
	//Phân trang theo sản phẩm giảm giá
	public List<Product> loadProductSaleOffPage(String page) {
		return productDAO.loadProductSaleOffPage(page);
	}

	public int getRowProductSaleOff() {
		return productDAO.getRowProductSaleOff();
	}
	
	//Lấy tất cả các sản phẩm
	public List<Product> getAllProductSaleOff() {
		return productDAO.getSaleOff();
	}
	/*--------------------------- Tìm kiếm tất cả các sản phẩm theo tên ----------------*/
	//Lấy tất cả các sản phẩm search được
	public int getRowProductSearchName(String search) {
		return productDAO.getRowProductSearchName(search);
	}
	// Tìm kiếm sản phẩm theo tên sản phẩm
	public List<Product> searchByNameProduct(String search){
		return productDAO.searchByNameProduct(search);
	}
	// Phân trang tìm kiếm sản phẩm theo tên
	public List<Product> loadProductPageByName(String page, String search){
		return productDAO.loadProductPageByName(page, search);
	}
	
}
