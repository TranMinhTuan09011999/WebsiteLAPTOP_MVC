package minhtuan.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import minhtuan.model.Order;
import minhtuan.model.OrderDetail;
import minhtuan.model.Product;

//@Component dùng để đánh dấu biết Class Shopping card là 1 bean
@Component("cart")
@Transactional
//@Scope với value là session có nghĩa là class Shopping chỉ sống và tồn tại khi có session, nếu session timeout thì nó sẽ chết
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart {

	@Autowired
	SessionFactory sessionFactory;

	Map<Integer, Product> map = new HashMap<Integer, Product>();
	int count;
	Long amount;
	Collection<Product> items;

	public void add(Integer id, Integer quantity) {
		Product item = map.get(id);
		if (item == null) {
			Session session = sessionFactory.openSession();
			item = (Product) session.get(Product.class, id);
			Integer oldQuantity= item.getQuantity();
			item.setQuantity(quantity);
			map.put(id, item);
			
			/*Product newItem = (Product) session.load(Product.class, id);
			Integer sl = oldQuantity-quantity;
			newItem.setQuantity(sl);
			Session session1 = sessionFactory.openSession();
			Transaction t = session1.beginTransaction();
			try {
				session.update(newItem);
				t.commit();		
				System.out.println("update thành công");
			}catch(Exception e)
			{
				t.rollback();
				System.out.println("update thất bại");
			}finally {
				session.close();
			}*/
			
			
			
			
			
			/*Product newItem = (Product) session.get(Product.class, id);
			newItem.setQuantity(quantity);
			map.put(id, newItem);
			Product item1 = map.get(id);
			System.out.println("llll: " + item1.getQuantity());
			//item.setQuantity(oldQuantity-quantity);
			
			Product newItem1 = (Product) session.get(Product.class, id);
			System.out.println("llll: " + newItem1.getQuantity());
			
			/*Product newItem1 = new Product();
			newItem1 = (Product) session.get(Product.class, id);
			newItem1.setQuantity(quantity);*/
			
		} else {
			item.setQuantity(item.getQuantity() + quantity);
		}
		count = this.getCount();
		amount = this.getAmount();
		items = this.getItems();
	}

	public void remove(Integer id) {
		map.remove(id);
	}

	public void update(Integer id, int newQuantity) {
		Product item = map.get(id);
		item.setQuantity(newQuantity);
	}

	//Xóa tất cả các mặt hàng trong cart
	public void clear() {
		map.clear();
	}

	//Lấy tổng số lượng các mặt hàng
	public int getCount() {
		int count = 0;
		Collection<Product> items = map.values();
		for (Product p : items) {
			count += p.getQuantity();
		}
		return count;
	}

	//Tổng số tiền các mặt hàng
	public long getAmount() {
		long amount = 0;
		Collection<Product> items = map.values();
		for (Product p : items) {
			amount += p.getQuantity() * p.getUnitPrice() * (1 - p.getDiscount());
		}
		return amount;
	}

	//Lấy tất cả các sản phẩm trong giỏ
	public Collection<Product> getItems() {
		return map.values();
	}

	
	public void purchase(Order order) {
		Session session = sessionFactory.getCurrentSession();

		Collection<OrderDetail> details = new ArrayList<OrderDetail>();
		for (Product item : map.values()) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProduct(item);
			detail.setAmount(item.getUnitPrice());
			detail.setQuantity(item.getQuantity());
			detail.setDiscount(item.getDiscount());
			
			details.add(detail);
		}
		order.setOrderDetail(details);
		session.save(order);
	}
}
