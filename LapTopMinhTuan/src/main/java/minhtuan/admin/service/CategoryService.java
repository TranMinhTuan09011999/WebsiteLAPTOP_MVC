package minhtuan.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import minhtuan.admin.dao.CategoryDAO;
import minhtuan.model.Category;

@Service("categoryService")
@Transactional
public class CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	public void insertCategory(Category category) {
		categoryDAO.insertCategory(category);
	}

	public void updateCategory(Category category) {
		categoryDAO.updateCategory(category);
	}

	public void deleteCategory(Category category) {
		categoryDAO.deleteCategory(category);
	}

	public Category getIDCategory(Integer id) {
		return categoryDAO.getIDCategory(id);
	}

	public List<Category> getAllCategory() {
		return categoryDAO.getAllCategory();
	}

	public int checkNameCategory(String nameCategory) {
		return categoryDAO.checkNameCategory(nameCategory);
	}

	public List<Category> loadCategoryPage(String page) {
		return categoryDAO.loadCategoryPage(page);
	}

	public int getRowCategory() {
		return categoryDAO.getRowCategory();
	}

	public List<Category> searchCategory(String search) {
		return categoryDAO.searchCategory(search);
	}
}
