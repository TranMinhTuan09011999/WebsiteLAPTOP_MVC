package minhtuan.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import minhtuan.admin.dao.ProducerDAO;
import minhtuan.model.Producer;

@Service("producerService")
@Transactional
public class ProducerService {
	@Autowired
	private ProducerDAO producerDAO;

	public void insertProducer(Producer producer) {
		producerDAO.insertProducer(producer);
	}

	public void updateProducer(Producer producer) {
		producerDAO.upadateProducer(producer);
	}

	public void deleteProducer(Producer producer) {
		producerDAO.deleteProducer(producer);
	}

	public Producer getIDProducer(int id) {
		return producerDAO.getIDProducer(id);
	}

	public List<Producer> getAllProducer() {
		return producerDAO.getAllProducer();
	}

	public int checkNameProducer(String nameProducer) {
		return producerDAO.checkNameProducer(nameProducer);
	}

	public List<Producer> searchProducer(String search) {
		return producerDAO.searchProducer(search);
	}

	public List<Producer> loadProducerPage(String page) {
		return producerDAO.loadProducerPage(page);
	}

	public int getRowProducer() {
		return producerDAO.getRowProducer();
	}
}
