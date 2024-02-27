//Ajax Call
GET: $(document).ready(
		function() {

			// GET REQUEST   //requestId. Specify the same requestId in the HTML
			$("#accessportal").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET     //responseId
			function ajaxGet() {
				$.ajax({
					url : "http://localhost:8080/access",
					success : function(result) {
						$("#apiResponse").html(result);
					}
				});
			}
		})
