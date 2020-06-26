package phonedir.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import phonedir.model.Contact;
import phonedir.service.ContactService;

@Controller
@EnableAutoConfiguration
public class ContactController {

	@Autowired
	private ContactService contactService;

	private final Log log = new SimpleLog("ContactController");
	
	public ContactController() {
		
		System.out.println("ContactController()");
	}

	/**
	 * Lists all the records stored in the database.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		
		List contacts = contactService.list();
		
		model.addAttribute("contacts", contacts);
		return "list";
	}

	/**
	 * Gets the details of the Contact with the given id.
	 */
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
	public String contact(@PathVariable("id") Long id, Model model) {

		Contact contact = contactService.findContactById(id);
		
		model.addAttribute("contact", contact);
		return "contact";
	}

	/**
	 * Creates a new model instance and passes it to the 'create' veiw.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		// TODO: Remove the sample data.
		Contact contact = new Contact("John Smith", "5551231111");
		model.addAttribute("contact", contact );
		return "create";
	}

	/**
	 * Validates the submitted form data; if it is valid, saves it to the DB.
	 * Then, redirects to the details page of the newly created record.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute Contact contact, BindingResult result) {

		if (result.hasErrors()) {
			log.info("Validation failed: " + result);
			return "create";
		}

		contactService.save(contact);

		return "redirect:/contact/" + contact.getId();
	}

	/**
	 * Fetches the record with the given id, and passes it to the 'update' veiw.
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	String update(@PathVariable("id") Long id, Model model) {

		Contact contact = contactService.findContactById(id);

		model.addAttribute("contact", contact);

		return "update";
	}

	/**
	 * Validates the submitted form data; if it is valid, updates the record in
	 * the DB. Then, redirects to the details page of the updated record.
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute Contact contact, BindingResult result,
			@PathVariable("id") Long id) {

		if (result.hasErrors()) {
			log.info("Validation failed: " + result);
			return "update";
		}
		
		contact.setId(id);
		contactService.update(contact);
		
		return "redirect:/contact/" + contact.getId();
	}

	/**
	 * Deletes the record with the given id. Redirects to the index page.
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id, Model model) {

		log.info("Deleting ID: " + id);

		contactService.delete(id);
		
		return "redirect:/";
	}

	/**
	 * Deletes all the selected records. The list of IDs to delete is passed as
	 * parameters in the query string. Redirects to the index page.
	 */
	@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
	public String deleteSelected(HttpServletRequest request) {

		String[] ids = request.getParameterValues("selectedId");
		// Check whether no records have been selected.
		if (ids == null) {
			return "redirect:/";
		}

		contactService.delete(ids);
		
		return "redirect:/";
	}

	/**
	 * Searches for records, the name of which matches the search query.
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam(required = true) String query, Model model) {

		if (query == null)
			return "list";

		List contacts = contactService.findContact(query);
		
		model.addAttribute("contacts", contacts);
		return "list";
	}
}