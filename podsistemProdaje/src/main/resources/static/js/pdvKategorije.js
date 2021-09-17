function getPdvCategories () {

    callPdvCategories(); 

    $(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});

    $(document).on("click", '#search', function(event){
		searchPdvCategory();
		$("#collapseSearch").collapse('toggle');
	});

    $(document).on("click", '#refresh', function(event){
		callPdvCategories();
	});
	
	$(document).on("click", '#doReset', function(event){
		var nazivSearchInput = $('#categoryNameSearchInput');
		nazivSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		callPdvCategories();
	});

}

function callPdvCategories() {
    var pageNo = 0; 
    var categoryIndex = $('#categoryIndex');
    var nmbSelect = $('#nmbSelect');
    var pageSize = nmbSelect.find(':selected').text();  
    
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

            nmbSelect.on('change',function (event) {
                event.preventDefault();
                pageSize = $(this).val();
                //console.log('kad klikne, to je ' + pageSize);
               callPdvCategories(); 
                //alert('pozvano');
                //alert('poziv padajuceg menija ////////////////');
            });
        
            categoryIndex.on("click","a.page-link", function (event) {
                event.preventDefault();
                pageNo = $(this).attr("pageNo");
                //console.log("poziv elementa //////////////////////////////")
                callPdvCategories();
                //alert('pozvano2');    
            });
    
            $("#first").click(function(){
                goFirst()
             });
            
            $("#next").click(function(){
                goNext()
             });
        },
        error: function(){
            alert('Doslo je do greske priliom ucitavanja svih kategorija!');
        }
    })
}
    
    
function searchPdvCategory() {
    var pageNo = 0; 
    var categoryIndex = $('#categoryIndex');
    var nmbSelect = $('#nmbSelect');
    var pageSize = nmbSelect.find(':selected').text();  
    $("#doSearch").on("click", function(event) {
        var searchByNameInput= $("categoryNameSearchInput");
        var searchByName = searchByNameInput.val();
    });

    $.ajax({
		url : "http://localhost:8086/api/pdvKategorije/allSorted?pageNo=" + pageNo + "&pageSize=" + pageSize + "&name=" + searchByName,
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

            nmbSelect.on('change',function (event) {
                event.preventDefault();
                pageSize = $(this).val();
                //console.log('kad klikne, to je ' + pageSize);
               callPdvCategories(); 
            });
        
            categoryIndex.on("click","a.page-link", function (event) {
                event.preventDefault();
                pageNo = $(this).attr("pageNo");
                callPdvCategories();   
            });
    
            $("#first").click(function(){
                goFirst()
             });
            
            $("#next").click(function(){
                goNext()
             });
        },
        error: function(){
            alert('Doslo je do greske priliom ucitavanja kategorije!');
        }
    })
}
  
       
