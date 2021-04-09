package minhtuan.admin.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import minhtuan.model.Producer;

@Repository
public class ProducerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void insertProducer(Producer producer) {
		Session session = sessionFactory.getCurrentSession();
		session.save(producer);
	}

	public void upadateProducer(Producer producer) {
		Session session = sessionFactory.getCurrentSession();
		session.update(producer);
	}

	public void deleteProducer(Producer producer) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(producer);
	}

	public Producer getIDProducer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Producer producer = (Producer) session.get(Producer.class, id);
		return producer;
	}

	@SuppressWarnings("unchecked")
	public List<Producer> getAllProducer() {
		Session session = sessionFactory.getCurrentSession();
		List<Producer> list = session.createQuery("FROM Producer").list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public int checkNameProducer(String nameProducer) {
		Session session = sessionFactory.getCurrentSession();
		List<Producer> list = session.createQuery("FROM Producer WHERE nameProducer ='" + nameProducer + "'").list();
		return list.size();
	}

	@SuppressWarnings("unchecked")
	public List<Producer> searchProducer(String search) {
		Session session = sessionFactory.getCurrentSession();
		List<Producer> list = session
				.createQuery("FROM Producer WHERE nameProducer LIKE '%" + search + "%' OR address LIKE '%" + search
						+ "%' OR email LIKE '%" + search + "%' OR numberPhone LIKE '%" + search + "%'")
				.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Producer> loadProducerPage(String page) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Producer");
		int result = 0;
		if (page.equals("1")) {
			result = 0;
		} else {
			String temp = String.valueOf(page) + "0";
			result = Integer.parseInt(temp) - 10;
		}
		query.setFirstResult(result);
		query.setMaxResults(10);
		return query.list();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public int getRowProducer() {
		Session session = sessionFactory.getCurrentSession();
		List<Producer> list = session.createQuery("FROM Producer").list();
		int i = 0;
		for (Producer producer : list) {
			i++;
		}
		return i;
	}
}
