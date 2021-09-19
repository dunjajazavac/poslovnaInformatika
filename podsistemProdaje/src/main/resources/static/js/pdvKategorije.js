function highlightRow(row){
	if(!$(row).hasClass("header")){
  		$(".highlighted").removeClass("highlighted");
    	$(row).addClass("highlighted");
    }
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

    $(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});

    
}
      
function searchPdvCategory() {
    var pageNo = 0; 
    var categoryIndex = $('#categoryIndex');
    var nmbSelect = $('#nmbSelect');
    var pageSize = nmbSelect.find(':selected').text(); 
    var searchButton = $('#doSearch');
    
    searchButton.on("click", function(event) {
        //alert('klik na dugme');
        event.preventDefault();
        var searchByNameInput= $("#categoryNameSearchInput");
        var searchByName = searchByNameInput.val();

        $.ajax({
            url : "http://localhost:8086/api/pdvKategorije/byName?pageNo=" + pageNo + "&pageSize=" + pageSize + "&name=" + searchByName
        }).then(
            function(output,status, xhr) {
                //alert('ime je ' + searchByName);
                //console.log('poslao se zahtjev');
                //console.log(searchByName); 
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
            });
            
    })
}

function reset() {
    document.getElementById("collapseSearch").reset();
}

function toggleSearch() {
    var x = document.getElementById("collapseSearch");
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
  }

function toggleAdd() {
    var x = document.getElementById("collapseAdd");
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
  }

function toggleUpdate() {
    var x = document.getElementById("collapseUpdate");
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
  }

  function addPdvCategory() {
    var categoryNameInput = $('#categoryNameInput');
    var name = categoryNameInput.val();
    
    if(name == ''){
        alert("Molim unesite ime kategorije! ");
    }
    
    $.post("http://localhost:8086/api/pdvKategorije/addCategory?name=" + name, function(data) {
        
        callPdvCategories();
        categoryNameInput.val("");
    });

	return false;
}

/*
function editPdvCategory() {
    var id = getId(); 
    var editInputCategoryName = $("#editInputCategoryName");
    var name = editInputCategoryName.val(); 

    if(name == '') {
        alert("Molim unesite ime kategorije!"); 
    }

    $.put("http://localhost:8086/api/pdvKategorije/updateCategory?name=" + name + "&id=" + id, function(data) {
        alert('prosla izmjena');
        alert('novo ime je ' + name);
        callPdvCategories(); 
        editInputCategoryName.val();
    });

    return false; 
}*/


function updatePdvCategory(){
	var id = getId();
	//alert(id); 
    //var name = getName(); 
    //alert(name);

	var editInputCategoryName = $('#editInputCategoryName');
    var name = editInputCategoryName.val();
		
    alert('pozvana update metoda ')
		
	
	if(name == ''){
		alert("Nije ime uneseno");
	}
		
	var params = {
			'id': id,
            'name' : name
	}

    $.ajax({
        url: "http://localhost:8086/api/pdvKategorije/updateCategory/" + id + '/' + name ,
        type: 'PUT',
        success: function(result) {
            alert('Izmijenjena pdv kategorija');			
            callPdvCategories();
            editInputCategoryName.val("");
        }
    });
        
	return false;
	
}


/*
function deletePdvCategory() {
    var id = getId(); 
    alert(id);
    $.ajax({
    	url: "http://localhost:8080/api/pdvKategorije/deleteCategory/" + id,
    	type: "DELETE",
    	success: function(){
    		callPdvCategories();
        }
	});
}
*/

function getId(){
	var row = $(".highlighted");
    var id = row.find(".idKategorije").html();
    if(id==undefined){
    	console.log("No entity selected!");
    	return null;
    }
    else{
    	return id;
    }  
}

function getName(){
	var row = $(".highlighted");
    var name = row.find(".nazivKategorije").html();
    if(name==undefined){
    	console.log("No entity selected!");
    	return null;
    }
    else{
    	return name;
    }  
}

