package minhtuan.model;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CategoryID", nullable = false)
	private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ProducerID", nullable = false)
	private Producer producer;
	
	@Column(name = "NameProduct", nullable = false)
	private String nameProduct;
	
	@Column(name = "Photo")
	private String photo;
	
	@Column(name = "Quantity", nullable = false)
	private Integer quantity;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "ProductDate", nullable = false, length = 23)
	private Date productDate;
	
	@Column(name = "UnitBrief", nullable = false)
	private String unitBrief;
	
	@Column(name = "UnitPrice", nullable = false, precision = 53, scale = 0)
	private Long unitPrice;
	
	@Column(name = "Discount", precision = 53, scale = 0)
	private Double discount;
	
	@Column(name = "Description")
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
	private Collection<OrderDetail> orderDetail;

	public Product() {
	}

	public Product(Integer id, Category category, Producer producer, String nameProduct, Integer quantity,
			Date productDate, String unitBrief, Long unitPrice) {
		this.id = id;
		this.category = category;
		this.producer = producer;
		this.nameProduct = nameProduct;
		this.quantity = quantity;
		this.productDate = productDate;
		this.unitBrief = unitBrief;
		this.unitPrice = unitPrice;
	}

	public Product(Integer id, Category category, Producer producer, String nameProduct, String photo, Integer quantity,
			Date productDate, String unitBrief, Long unitPrice, Double discount, String description, Collection<OrderDetail> orderDetail) {
		this.id = id;
		this.category = category;
		this.producer = producer;
		this.nameProduct = nameProduct;
		this.photo = photo;
		this.quantity = quantity;
		this.productDate = productDate;
		this.unitBrief = unitBrief;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.description = description;
		this.orderDetail = orderDetail;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Producer getProducer() {
		return this.producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public String getNameProduct() {
		return this.nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getProductDate() {
		return this.productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getUnitBrief() {
		return this.unitBrief;
	}

	public void setUnitBrief(String unitBrief) {
		this.unitBrief = unitBrief;
	}

	public Long getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Collection<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

}
