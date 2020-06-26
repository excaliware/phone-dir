"use strict";
/* Implementation of Ajax features */

jQuery(document).ready(function() {
	
	/* Implement the real-time search suggestions feature. */
	$("#query").autocomplete({
		source : "/api/searchContact",
		minLength : 1,
		select : function(event, ui) {
			/* Show the contact details. */
			window.open("/contact/" + ui.item.id, "_self");
		},
		response : function(event, ui) {
			/* Add the 'value' and 'label' properties for jQuery UI autocomplete. */
			$.each(ui.content, function() {
				this.value = this.name;
				this.label = this.name;
			});
		}
	});

	/* Do not submit an empty search query. */
	$("#search-form").submit(function() {

		var query = $("#query").val();
		if (query == "") {
			return false;
		}
		/* Submit the search form. */
		return true;
	});

	/* Delete a record. */
	$("#index a[href^='/delete']").click(function(event) {

		event.preventDefault();
		var row = $(this).parent().parent();
		var id = $(this).attr("href").split("/").pop();

		/* Ask for a confirmation before deleting. */
		$("#dialog-confirm span:last").text("Delete this record?");
		$("#dialog-confirm").dialog({
			title : "Delete",
			resizable : false,
			modal : true,
			buttons : {
				Ok : function() {
					/* Delete the record. */
					$.ajax({
						url : "/api/delete",
						type : "GET",
						dataType : "text",
						data : "id=" + id,
						success : function(response) {
							if (response == "ok") {
								/* If it is a contact details page, redirect to the index page. */
								if (window.location.href.split("/").splice(-2, 1) == "contact")
									window.location.href = "/";

								/* Delete the record row from the index table. */
								row.hide("slow", function() {
									this.remove();
								});
							} else {
								showMessage("Error", "Could not delete the record");
							}
						}
					});
					$(this).dialog("close");
				},
				Cancel : function() {
					$(this).dialog("close");
				}
				}
		});
	});

	/* Delete the selected records. */
	$("#delete-selected-form").submit(function(event) {
		event.preventDefault();
		if ($("#index td input:checked").length == 0)
			return false;

		var rows = $("#index td input:checked").parent().parent();
		var query = $(this).serialize();

		/* Ask for a confirmation before deleting. */
		$("#dialog-confirm span:last").text("Delete the selected records?");
		$("#dialog-confirm").dialog({
			title : "Delete selected",
			resizable : false,
			modal : true,
			buttons : {
				Ok : function() {
					/* Delete the selected records. */
					$.post("/api/deleteSelected",query, function(response) {
						if (response == "ok") {
							rows.hide("slow", function() {
								this.remove();
							});
						} else {
							showMessage("Error", "Could not delete the records");
						}
					});
					$(this).dialog("close");
				},
				Cancel : function() {
					$(this).dialog("close");
				}
			}
		});
	});

}); // end (document).ready()

/* Show a message in a modal dialog. */
function showMessage(title, message) {
	$("#dialog-message span:last").text(message);
	$("#dialog-message").dialog({
		modal : true,
		title : title,
		buttons : {
			Ok : function() {
				$(this).dialog("close");
			}
		}
	});
}
