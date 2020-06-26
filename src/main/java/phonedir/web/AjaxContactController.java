package phonedir.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.SimpleLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import phonedir.HibernateUtil;
import phonedir.service.ContactService;

@Controller
@EnableAutoConfiguration
public class AjaxContactController {

	@Autowired
	private ContactService contactService;

	private final Log log = new SimpleLog("AjaxContactController");
	
	public AjaxContactController() {
		
		System.out.println("AjaxContactController()");
	}
	
	/**
	 * Searches for records, the name of which matches the search query.
	 * Returns a list of found records (to be sent as JSON). 
	 * @param term the query string
	 */
	@RequestMapping(value = "api/searchContact", method = RequestMethod.GET)
	public @ResponseBody List ajaxSearchContact(@RequestParam String term) {
		
		if ("".equals(term))
			return new ArrayList();
		
		List foundContacts = contactService.findContact(term);
		log.info("ajaxSearchContact: " + foundContacts);
		
		return foundContacts;
	}
	
	/**
	 * Deletes the record with the given id.
	 */
	@RequestMapping(value = "/api/delete", method = RequestMethod.GET)
	public @ResponseBody String delete(@RequestParam(required = false) Long id, Model model) {

		log.info("Deleting ID: " + id);

		int nDeleted = contactService.delete(id);
		
		return nDeleted == 1 ? "ok" : "error";
	}
	
	/**
	 * Deletes all the selected records. The list of IDs to delete is passed as
	 * parameters in the query string.
	 */
	@RequestMapping(value = "/api/deleteSelected", method = RequestMethod.POST)
	public @ResponseBody String deleteSelected(HttpServletRequest request) {

		String[] ids = request.getParameterValues("selectedId");
		// Check whether no records have been selected.
		if (ids == null) {
			return "error";
		}

		int nDeleted = contactService.delete(ids);
		
		return nDeleted == ids.length ? "ok" : "error";
	}

}