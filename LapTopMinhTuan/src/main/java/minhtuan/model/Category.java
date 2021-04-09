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

@Entity
@Table(name = "Categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NameCategory", nullable = false)
	private String nameCategory;
	
	@Column(name = "NameCategoryEN", nullable = false)
	private String nameCategoryEN;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
	private Collection<Product> product;

	public Category() {
	}

	public Category(Integer id, String nameCategory, String nameCategoryEN) {
		this.id = id;
		this.nameCategory = nameCategory;
		this.nameCategoryEN = nameCategoryEN;
	}

	public Category(Integer id, String nameCategory, String nameCategoryEN, Collection<Product> product) {
		this.id = id;
		this.nameCategory = nameCategory;
		this.nameCategoryEN = nameCategoryEN;
		this.product = product;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameCategory() {
		return this.nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getNameCategoryEN() {
		return this.nameCategoryEN;
	}

	public void setNameCategoryEN(String nameCategoryEN) {
		this.nameCategoryEN = nameCategoryEN;
	}

	public Collection<Product> getProduct() {
		return product;
	}

	public void setProduct(Collection<Product> product) {
		this.product = product;
	}

}
