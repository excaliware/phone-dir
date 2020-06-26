"use strict";

(function() {

	jQuery(document).ready(function() {

		/* Handle the scroll-to-the-top link. */
		$("#scroll-to-top").click(function(event) {
			window.scrollTo(0, 0);
			// event.preventDefault();
			return false;
		});

		/* Select/deselect all checkboxes. */
		$("input#select-all").change(function() {

			var checkboxes = $("#index td").find("input[type='checkbox']");

			if (this.checked)
				checkboxes.prop("checked", true);
			else
				checkboxes.prop("checked", false);
		});
	}); // end $(document).ready()

})();