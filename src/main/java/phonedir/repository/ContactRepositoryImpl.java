package phonedir.repository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import phonedir.HibernateUtil;
import phonedir.model.Contact;

@Repository
public class ContactRepositoryImpl implements ContactRepository {
	
	/** Hibernate session */
	private final SessionFactory sessionFactory;

	private final Log log = new SimpleLog("ContactRepository");

	/**
	 * Creates a single instance of the session factory.
	 * Populate the DB with sample data.
	 */
	public ContactRepositoryImpl() {
		
		System.out.println("ContactRepository()");
		sessionFactory = HibernateUtil.getSessionFactory();
		// TODO: Remove sample data.
		createSampleData();
	}

	/**
	 * Gets a list of all contacts in the database.
	 */
	@Override
	public List findAll() {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List contacts = session.createQuery("from Contact contact order by contact.name").list();

		session.getTransaction().commit();
		session.close();
		return contacts;
	}

	/**
	 * Returns a contact with a given id; if such does not exist, returns null.
	 */
	@Override
	public Contact findById(Long id) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Contact contact = (Contact) session.createQuery("from Contact where id=" + id).uniqueResult();

		session.getTransaction().commit();
		session.close();
		return contact;
	}

	/**
	 * Updates the contact data in the database.
	 */
	@Override
	public void update(Contact contact) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(contact);

		session.getTransaction().commit();
		session.close();		
	}

	/**
	 * Deletes a contact with a given id from the database.
	 */
	@Override
	public int delete(Long id) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		int nDeleted = session.createQuery("delete from Contact where id=" + id).executeUpdate();

		session.getTransaction().commit();
		session.close();		
		
		return nDeleted;
	}

	/**
	 * Deletes multiple contacts the ids of which are in the given array of ids.
	 */
	@Override
	public int delete(String[] ids) {
		
		int nDeleted = 0;
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// Delete the selected records.
		for (String id : ids) {
			log.info("Deleting ID: " + id);
			if (session.createQuery("delete from Contact where id=" + id).executeUpdate() == 1)
				nDeleted++;
			
		}
		session.getTransaction().commit();
		session.close();		
		return nDeleted;
	}

	/**
	 * Returns a list of contacts the names of whom correspond to the search query;
	 * or an empty list if no contact found. 
	 */
	@Override
	public List find(String query) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List contacts = session.createQuery(String.format(
				"from Contact contact where lower(name) like lower('%%%s%%')"
				+ " order by contact.name", query))
				.list();

		session.getTransaction().commit();
		session.close();
		return contacts;
	}

	/**
	 * Saves the new contact in the database. 
	 */
	@Override
	public void save(Contact contact) {
	
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(contact);

		session.getTransaction().commit();
		session.close();	
	}
	
	/**
	 * Creates a few sample records in the database.
	 */
	public void createSampleData() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(new Contact("John", "5552311230"));
		session.save(new Contact("Mary", "5552311232"));
		session.save(new Contact("Sam", "5552311236"));
		session.save(new Contact("Tom", "5552311237"));
		session.save(new Contact("George", "5552311235"));

		session.getTransaction().commit();
		session.close();		
	}
}
