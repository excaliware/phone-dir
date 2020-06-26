package phonedir.service;

import java.util.List;

import phonedir.model.Contact;

public interface ContactService {

	void save(Contact contact);

	List list();

	Contact findContactById(Long id);

	void update(Contact contact);

	int delete(Long id);

	int delete(String[] ids);

	List findContact(String query);

}