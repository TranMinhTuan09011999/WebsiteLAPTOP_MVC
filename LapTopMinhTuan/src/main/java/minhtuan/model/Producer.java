package minhtuan.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "Producers")
public class Producer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NameProducer", nullable = false)
	private String nameProducer;
	
	@Column(name = "Logo")
	private String logo;
	
	@Column(name = "Address", nullable = false)
	private String address;
	
	@Email
	@Column(name = "Email", nullable = false)
	private String email;
	
	@Column(name = "NumberPhone", nullable = false)
	private String numberPhone;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "producer")
	private Collection<Product> product;

	public Producer() {
	}

	public Producer(Integer id, String nameProducer, String address, String email, String numberPhone) {
		this.id = id;
		this.nameProducer = nameProducer;
		this.address = address;
		this.email = email;
		this.numberPhone = numberPhone;
	}

	public Producer(Integer id, String nameProducer, String logo, String address, String email, String numberPhone,
			Collection<Product> product) {
		this.id = id;
		this.nameProducer = nameProducer;
		this.logo = logo;
		this.address = address;
		this.email = email;
		this.numberPhone = numberPhone;
		this.product = product;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameProducer() {
		return this.nameProducer;
	}

	public void setNameProducer(String nameProducer) {
		this.nameProducer = nameProducer;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumberPhone() {
		return this.numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public Collection<Product> getProduct() {
		return product;
	}

	public void setProduct(Collection<Product> product) {
		this.product = product;
	}

}
