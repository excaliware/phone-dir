package phonedir.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

/**
 * A model class which represents a single contact's details.
 */
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private Long id;
	
	@Column(name = "name")
	@Size(min = 1, max = 32)
	private String name;
	
	@Column(name = "phone")
	@Size(min = 1, max = 16)
	private String phone;
	
	public Contact() {
	}
	
	public Contact(String name, String phone) {
		
		this.name = name;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return String.format("{id: %d; name: %s; phone: %s}", id, name, phone);
	}
}
