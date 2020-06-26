package phonedir.repository;

import java.util.List;

import phonedir.model.Contact;

public interface ContactRepository {

	List findAll();

	Contact findById(Long id);

	void update(Contact contact);

	int delete(Long id);

	int delete(String[] ids);

	List find(String query);

	void save(Contact contact);

}