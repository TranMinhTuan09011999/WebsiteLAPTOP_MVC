package minhtuan.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minhtuan.model.Product;

@Repository
public class ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// Thêm sản phẩm
	public void insertProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.save(product);
	}

	// Sửa sản phẩm
	public void updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.update(product);
	}

	// xóa sản phẩm
	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
	}

	// Lấy sản phẩm theo id
	public Product getIDProduct(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class, id);
		return product;
	}

	// Lấy danh sách sản phẩm
	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct() {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("FROM Product").list();
		return list;
	}

	// Tìm kiếm sản phẩm
	@SuppressWarnings("unchecked")
	public List<Product> searchProduct(String search) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("FROM Product WHERE nameProduct LIKE '%" + search
				+ "%' OR Quantity LIKE '%" + search + "%' OR Description LIKE '%" + search + "%' OR UnitBrief LIKE '%"
				+ search + "%' OR UnitPrice LIKE '%" + search + "%'").list();
		return list;
	}

	// Lấy danh sách sản phẩm theo danh mục
	@SuppressWarnings("unchecked")
	public List<Product> getProductByCategory(String idCategory) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("FROM Product WHERE category.id = '" + idCategory + "'").list();
		return list;
	}

	// Tìm kiếm theo tên hãng
	@SuppressWarnings("unchecked")
	public List<Product> getProductByProducer(String idProducer) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("FROM Product WHERE producer.id = '" + idProducer + "'").list();
		return list;
	}

	// Lấy danh sách sản phẩm theo tên hãng và danh mục
	@SuppressWarnings("unchecked")
	public List<Product> getProducerAndCategory(String idProducer, String idCategory) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session
				.createQuery(
						"FROM Product WHERE producer.id = '" + idProducer + "' AND  category.id ='" + idCategory + "'")
				.list();
		return list;
	}

	// Lấy danh sách sản phẩm theo giảm giá
	@SuppressWarnings("unchecked")
	public List<Product> getSaleOff() {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session
				.createQuery("FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC ").list();
		return list;
	}

	// Tìm kiếm theo cả 3
	@SuppressWarnings("unchecked")
	public List<Product> searchBy3(String search, String idCategory, String idProducer) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("FROM Product WHERE nameProduct LIKE '%" + search
				+ "%' OR Quantity LIKE '%" + search + "%' OR Description LIKE '%" + search + "%' OR UnitBrief LIKE '%"
				+ search + "%' OR UnitPrice LIKE '%" + search + "%'" + "AND category.id = '" + idCategory + "'"
				+ "AND producer.id = '" + idProducer + "'").list();
		return list;
	}

	// Phân trang
	@SuppressWarnings("unchecked")
	public List<Product> loadProductPage(String page) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Product");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			int p = Integer.valueOf(page);
			int p1 = p % 10 - 1;
			result = Integer.parseInt(temp) - 10 - p1;
		}
		query.setFirstResult(result);
		query.setMaxResults(9);
		return query.list();
	}

	// Phân trang theo từng category
	@SuppressWarnings("unchecked")
	public List<Product> loadProductCategoryPage(String page, String id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Product WHERE category.id = '" + id + "'");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			int p = Integer.valueOf(page);
			int p1 = p % 10 - 1;
			result = Integer.parseInt(temp) - 10 - p1;

		}
		query.setFirstResult(result);
		query.setMaxResults(9);
		return query.list();
	}

	// Phân trang theo từng producer
	@SuppressWarnings("unchecked")
	public List<Product> loadProductProducerPage(String page, String id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Product WHERE producer.id = '" + id + "'");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			int p = Integer.valueOf(page);
			int p1 = p % 10 - 1;
			result = Integer.parseInt(temp) - 10 - p1;

		}
		query.setFirstResult(result);
		query.setMaxResults(9);
		return query.list();
	}

	// Phân trang theo sản phẩm giảm giá
	@SuppressWarnings("unchecked")
	public List<Product> loadProductSaleOffPage(String page) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC ");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			int p = Integer.valueOf(page);
			int p1 = p % 10 - 1;
			result = Integer.parseInt(temp) - 10 - p1;

		}
		query.setFirstResult(result);
		query.setMaxResults(9);
		return query.list();
	}

	// Số lượng sản phẩm theo category
	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowProductCategory(String id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Product WHERE category.id = '" + id + "'");
		List<Product> list = query.list();
		int i = 0;
		for (Product product : list) {
			i++;
		}
		return i;
	}

	// Số lượng sản phẩm theo producer
	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowProductProducer(String id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Product WHERE producer.id = '" + id + "'");
		List<Product> list = query.list();
		int i = 0;
		for (Product product : list) {
			i++;
		}
		return i;
	}

	// Số lượng sản phẩm theo giảm giá
	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowProductSaleOff() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Product p WHERE p.discount > 0 ORDER BY p.discount DESC ");
		List<Product> list = query.list();
		int i = 0;
		for (Product product : list) {
			i++;
		}
		return i;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowProduct() {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("FROM Product").list();
		int i = 0;
		for (Product product : list) {
			i++;
		}
		return i;
	}

	/*---------------------------------- Tìm kiếm sản phẩm theo tên -----------------------------------*/
	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowProductSearchName(String search) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session
				.createQuery("FROM Product p WHERE p.nameProduct LIKE '%" + search + "%'").list();
		int i = 0;
		for (Product product : list) {
			i++;
		}
		return i;
	}

	// Tìm kiếm sản phẩm theo tên sản phẩm
	@SuppressWarnings("unchecked")
	public List<Product> searchByNameProduct(String search) {
		Session session = sessionFactory.getCurrentSession();
		List<Product> list = session
				.createQuery("FROM Product p WHERE p.nameProduct LIKE '%" + search + "%'").list();
		return list;
	}

	// Phân trang tìm kiếm sản phẩm theo tên
	@SuppressWarnings("unchecked")
	public List<Product> loadProductPageByName(String page, String search) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("FROM Product p WHERE p.nameProduct LIKE '%" + search + "%'");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			int p = Integer.valueOf(page);
			int p1 = p % 10 - 1;
			result = Integer.parseInt(temp) - 10 - p1;
		}
		query.setFirstResult(result);
		query.setMaxResults(9);
		return query.list();
	}

}
