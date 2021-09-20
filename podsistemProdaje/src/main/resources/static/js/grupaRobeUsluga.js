function loadScript() {
    loadPdvCategories();
    loadPdvCategoriesForUpdate();
    callGroup(); 
}

function callGroup() {

    var pageNo = 0;
    var categoryIndex = $('#categoryIndex'); 
    var nmbSelect = $('#nmbSelect');
    var pageSize = nmbSelect.find(':selected').text();  

    $.ajax({
        url : "http://localhost:8086/api/grupaRobeUsluga/allSorted?pageNo=" + pageNo + "&pageSize=" + pageSize,  
        success: function(output,status, xhr) {
            categoryIndex.empty(); 
            $("#dataTableBody").empty(); 

            for(var j=0; j<xhr.getResponseHeader('totalPages'); j++) {
                categoryIndex.append(
                    `<li class="page-item ${pageNo==j? 'active': ''}">` + 
                    `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`
                )
            }
            for (i = 0; i < output.length; i++) {
                newRow = 
                    "<tr>" 
                        + "<td class=\"nazivGrupe\">" + output[i].nazivGrupe + "</td>"
                        + "<td class=\"idGrupe\" style:display:none>" + output[i].idGrupe + "</td>" +
                        
                    "</tr>"
                $("#dataTableBody").append(newRow);
            }

            nmbSelect.on('change',function (event) {
                event.preventDefault();
                pageSize = $(this).val();
                callGroup();
            });

            categoryIndex.on("click","a.page-link", function (event) {
                event.preventDefault();
                pageNo = $(this).attr("pageNo");
                callGroup();    
            });

            $(document).on("click", '#delete', function(event){
                event.preventDefault(); 
                var name = getGroupName();
                if(name!=null){
                    $('#deletePromptText').text("Brise se: " + name);
                    $('#deletePromptModal').modal('show');
                }
        
            });
            
            $(document).on("click", '.deletePromptClose', function(event){
                event.preventDefault();
                $('#deletePromptModal').modal('hide');
            });
        
            $(document).on("click", '#doDelete', function(event){
                event.preventDefault();
                deleteGroup();
                $('#deletePromptModal').modal('hide');
            });
             
        },
         error: function() {
             alert('doslo je do greske prilikom ucitavanja grupa');
         }
    });

    $(document).on("click", 'tr', function(event) {
        event.preventDefault(); 
		highlightRow(this);
	});

    $(document).on("click", '#refresh', function(event){
        event.preventDefault();
		callGroup();
	});
    
}

function resetSearch() {
    document.getElementById("collapseSearch").reset();
}

function toggleSearch() {
    var x = document.getElementById("collapseSearch");
        if (x.style.display === "none") {
             x.style.display = "block";
            } 
        else {
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

function highlightRow(row){
	if(!$(row).hasClass("header")){
  		$(".highlighted").removeClass("highlighted");
    	$(row).addClass("highlighted");
    }
}

function searchGroup() {

    var pageNo = 0; 
    var categoryIndex = $('#categoryIndex');
    var nmbSelect = $('#nmbSelect');
    var pageSize = nmbSelect.find(':selected').text(); 
    var searchButton = $('#doSearch');

    searchButton.on("click", function(event) {
        event.preventDefault();

        var searchByNameInput = $("#groupNameSearchInput");
        var searchByName = searchByNameInput.val(); 
        
        $.ajax({
            url:  "http://localhost:8086/api/grupaRobeUsluga/byName?pageNo=" + pageNo + "&pageSize=" + pageSize + "&name=" + searchByName
        }).then(
            function(output,status, xhr) {
                categoryIndex.empty();
                $("#dataTableBody").empty();
                
                for(var j=0; j<xhr.getResponseHeader('totalPages'); j++) {
                    categoryIndex.append(
                        `<li class="page-item ${pageNo==j? 'active': ''}">` + 
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`
                    )
                }
                for (i = 0; i < output.length; i++) {
                    newRow = 
                        "<tr>" 
                            + "<td class=\"nazivGrupe\">" + output[i].nazivGrupe + "</td>"
                            + "<td class=\"idGrupe\" style:display:none>" + output[i].idGrupe + "</td>" +
                            
                        "</tr>"
                    $("#dataTableBody").append(newRow);
                }

                nmbSelect.on('change',function (event) {
                    event.preventDefault();
                    pageSize = $(this).val();
                   callGroup(); 
                });
            
                categoryIndex.on("click","a.page-link", function (event) {
                    event.preventDefault();
                    pageNo = $(this).attr("pageNo");
                    callGroup();   
                });
            });
    });
}

function addGroup() {
    var groupNameInput = $('#groupNameInput'); 
    var groupName = groupNameInput.val();

    var categorySelect = $("#categorySelect"); 
    var pdvCategoryName = categorySelect.find(":selected").text();

   $.post("http://localhost:8086/api/grupaRobeUsluga/addGroup?groupName=" + groupName + "&pdvCategoryName=" + pdvCategoryName, function(data) {
        
    callGroup();
    groupNameInput.val("");
    categorySelect.val("");
    });
    
    return false; 
}

function loadPdvCategories() {
    $.ajax({
		url : "http://localhost:8086/api/pdvKategorije/all",
        success: function(data) {
                    
            $('#categorySelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));

			$.each(data, function (i, item) {
                    $('#categorySelect').append($('<option>', { 
			        value: item.idKategorije,
			        text : item.naziv
			    }));
			});
        }, 
        error: function(){
            alert("Ne mogu se ucitati kategorije");
        }
    });

}


function loadPdvCategoriesForUpdate() {
    $.ajax({
		url : "http://localhost:8086/api/pdvKategorije/all",
        success: function(data) {
                    
            $('#editCategorySelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));

			$.each(data, function (i, item) {
                    $('#editCategorySelect').append($('<option>', { 
			        value: item.idKategorije,
			        text : item.naziv
			    }));
			});
        }, 
        error: function(){
            alert("Ne mogu se ucitati kategorije");
        }
    });

}

function updateGroup(){
	var id = getGroupId();
	var editInputGroup = $('#editInputGroup');
    var groupName = editInputGroup.val();
    var editCategorySelect = $("#editCategorySelect"); 
    var pdvCategoryName = editCategorySelect.find(":selected").text();
		
	if(groupName == ''){
		alert("Nije ime uneseno");
	}

    //addGroup?groupName=" + groupName + "&pdvCategoryName=" + pdvCategoryName, function(data) {
    $.ajax({
        url: "http://localhost:8086/api/grupaRobeUsluga/updateGroup?id=" + id + "&groupName=" + groupName + "&pdvCategoryName=" + pdvCategoryName,
        type: 'PUT',
        success: function(result) {
           			
            callGroup();
            editInputGroup.val("");
        }
    });
        
	return false;
	
}

function getGroupId(){
	var row = $(".highlighted");
    var id = row.find(".idGrupe").html();
    if(id==undefined){
    	console.log("No entity selected!");
    	return null;
    }
    else{
    	return id;
    }  
}

function deleteGroup() {
    var id = getGroupId();
    $.ajax({
    	url: "http://localhost:8086/api/grupaRobeUsluga/deleteGroup/" + id,
    	type: "DELETE",
    	success: function(){
    		callGroup();
        }
	});
    
    return false; 
}

function getGroupName(){
	var row = $(".highlighted");
    var name = row.find(".nazivGrupe").html();
    if(name==undefined){
    	console.log("No entity selected!");
    	return null;
    }
    else{
    	return name;
    }  
}