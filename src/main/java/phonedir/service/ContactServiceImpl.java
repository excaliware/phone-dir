package phonedir.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import phonedir.model.Contact;
import phonedir.repository.ContactRepository;


@Component
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	private final Log log = new SimpleLog("ContactService");
	
	public ContactServiceImpl() {
		
		System.out.println("ContactService()");
	}

	/**
	 * Saves the new contact in the database. 
	 */
	@Override
	public void save(Contact contact) {

		contactRepository.save(contact);
	}

	/**
	 * Gets a list of all contacts in the database.
	 */
	@Override
	public List list() {

		return contactRepository.findAll();
	}

	/**
	 * Returns a contact with a given id; if such does not exist, returns null.
	 */
	@Override
	public Contact findContactById(Long id) {
				
		return contactRepository.findById(id);
	}

	/**
	 * Updates the contact data in the database.
	 */
	@Override
	public void update(Contact contact) {
	
		contactRepository.update(contact);
	}

	/**
	 * Deletes a contact with a given id from the database.
	 */
	@Override
	public int delete(Long id) {

		return contactRepository.delete(id);
	}
	
	/**
	 * Deletes multiple contacts the ids of which are in the given array of ids.
	 */
	@Override
	public int delete(String[] ids) {
		
		return contactRepository.delete(ids);
	}

	/**
	 * Returns a list of contacts the names of whom correspond to the search query;
	 * or an empty list if no contact found. 
	 */
	@Override
	public List findContact(String query) {

		return contactRepository.find(query);
	}
}