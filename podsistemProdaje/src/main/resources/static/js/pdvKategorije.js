function getPdvCategories () {

    callPdvCategories(); 

}

function callPdvCategories() {
    var pageNo = 0; 
    var nmbSelect = $('#nmb-select');
    var pageSize = nmbSelect.find(':selected').text();  
    var categoryIndex = $('#categoryIndex');
    
    $.ajax({
		url : "http://localhost:8086/api/pdvKategorije/allSorted?pageNo=" + pageNo + "&pageSize=" + pageSize,
        success:  function(output,status, xhr) {
            categoryIndex.empty();
            $("#dataTableBody").empty();
            //console.log(output);
            for(var j=0; j<xhr.getResponseHeader('totalPages'); j++) {
                categoryIndex.append(
                    `<li class="page-item ${pageNo==j? 'active': ''}">` + 
                    `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`
                )
            }
            for (i = 0; i < output.length; i++) {
                newRow = 
                    "<tr>" 
                        + "<td class=\"nazivKategorije\">" + output[i].nazivKategorije + "</td>"
                        + "<td class=\"idKategorije\" style:display:none>" + output[i].idKategorije + "</td>" +
                        
                    "</tr>"
                $("#dataTableBody").append(newRow);

                console.log(newRow);
            }
        },
        error: function(){
            alert('Doslo je do greske priliom ucitavanja svih kategorija!');
        }
    })
}
	/*
    }).then( 
        function(data,xhr) {
          
            categoryIndex.empty();
            alert(xhr.getResponseHeader('total'));
            $("#dataTableBody").empty();
            //total postavljen u bekendu 
            for(var j=0; j<xhr.getResponseHeader('total'); j++) {
                categoryIndex.append(
                    `<li class="page-item ${pageNo==j? 'active': ''}">` + 
                    `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`
                )
            }
            for (i = 0; i < data.length; i++) {
                newRow = 
                    "<tr>" 
                        + "<td class=\"nazivKategorije\">" + data[i].nazivKategorije + "</td>"
                        + "<td class=\"idKategorije\" style:display:none>" + data[i].idKategorije + "</td>" +
                        
                    "</tr>"
                $("#dataTableBody").append(newRow);
                console.log(newRow);
            }
        }
    );
    

    $("#first").click(function(){
		goFirst()
	 });
	
	$("#next").click(function(){
		goNext()
	 });
	
	nmbSelect.on('change',function (event) {
	    event.preventDefault();
	    pageSize = $(this).val();
        callPdvCategories(); 
	});

	categoryIndex.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageNo");
        callPdvCategories(); 
	});
    */
