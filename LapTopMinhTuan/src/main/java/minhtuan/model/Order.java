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
@Table(name = "Orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CustomerID", nullable = false)
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "OrderDate", nullable = false, length = 23)
	private Date orderDate;
	
	@Column(name = "Amount", nullable = false, precision = 53, scale = 0)
	private Long amount;
	
	@Column(name = "Receiver", nullable = false)
	private String receiver;
	
	@Column(name = "Address", nullable = false)
	private String address;
	
	@Column(name = "numberPhone", nullable = false)
	private String numberPhone;
	
	@Column(name = "Status", nullable = false)
	private Integer status;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
	private Collection<OrderDetail> orderDetail;

	public Order() {
	}

	public Order(Integer id, Customer customer, Date orderDate, Date requireDate, Long amount, String receiver,
			String address, String description, String numberPhone, Integer status) {
		this.id = id;
		this.customer = customer;
		this.orderDate = orderDate;
		this.amount = amount;
		this.receiver = receiver;
		this.address = address;
		this.numberPhone = numberPhone;
		this.status = status;
	}

	public Order(Integer id, Customer customer, Date orderDate, Long amount, String receiver,
			String address, String numberPhone, Integer status,
			Collection<OrderDetail> orderDetail) {
		this.id = id;
		this.customer = customer;
		this.orderDate = orderDate;
		this.amount = amount;
		this.receiver = receiver;
		this.address = address;
		this.numberPhone = numberPhone;
		this.status = status;
		this.orderDetail = orderDetail;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Long getAmount() {
		return this.amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumberPhone() {
		return this.numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Collection<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Collection<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
}
