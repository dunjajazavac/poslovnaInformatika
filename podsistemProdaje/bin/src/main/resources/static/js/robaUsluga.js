function loadScript() {
    callGoods(); 
    loadGroups();
    loadUnitsOfMeasure();
    loadGroupsForUpdate(); 
    loadUnitsOfMeasureForUpdate();
}

function callGoods() {
    var pageNo = 0;
    var categoryIndex = $('#categoryIndex'); 
    var nmbSelect = $('#nmbSelect');
    var pageSize = nmbSelect.find(':selected').text();  

    $.ajax({
        url : "http://localhost:8086/api/robeUsluge/allSorted?pageNo=" + pageNo + "&pageSize=" + pageSize,  
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
                        + "<td class=\"nazivRobeUsluge\">" + output[i].nazivRobeUsluge + "</td>"
                        + "<td class=\"idRobeUsluge\" style:display:none>" + output[i].idRobeUsluge + "</td>" +
                        
                    "</tr>"
                $("#dataTableBody").append(newRow);
            }

            nmbSelect.on('change',function (event) {
                event.preventDefault();
                pageSize = $(this).val();
                callGoods();
            });

            categoryIndex.on("click","a.page-link", function (event) {
                event.preventDefault();
                pageNo = $(this).attr("pageNo");
                callGoods();    
            });

            $(document).on("click", '#delete', function(event){
                event.preventDefault(); 
                var name = getGoodName();
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
                deleteGood();
                $('#deletePromptModal').modal('hide');
            });
             
            
        },
         error: function() {
             alert('doslo je do greske prilikom ucitavanja roba');
         }
    });

    $(document).on("click", 'tr', function(event) {
        event.preventDefault(); 
		highlightRow(this);
	});

    $(document).on("click", '#refresh', function(event){
        event.preventDefault();
		callGoods();
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

        var searchByNameInput = $("#goodNameSearchInput");
        var searchByName = searchByNameInput.val(); 
        
        $.ajax({
            url:  "http://localhost:8086/api/robeUsluge/byName?pageNo=" + pageNo + "&pageSize=" + pageSize + "&name=" + searchByName
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
                            + "<td class=\"nazivRobeUsluge\">" + output[i].nazivRobeUsluge + "</td>"
                            + "<td class=\"idRobeUsluge\" style:display:none>" + output[i].idRobeUsluge + "</td>" +
                            
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

function addGoods() {
    var goodNameInput = $('#goodNameInput'); 
    var goodName = goodNameInput.val();

    var descInput = $('#descInput'); 
    var desc = descInput.val();

    var goodsSelect = $("#goodsSelect"); 
    var goods = goodsSelect.find(":selected").text();

    var unitOfMeasureSelect = $("#unitOfMeasureSelect"); 
    var unitOfMeasure = unitOfMeasureSelect.find(":selected").text();

    var groupSelect = $("#groupSelect"); 
    var group = groupSelect.find(":selected").text();

   $.post("http://localhost:8086/api/robeUsluge/addGoodsService?name=" + goodName + "&desc=" + desc + "&goods=" + goods + "&measureName=" + unitOfMeasure + "&groupName=" + group, function(data) {
        
    callGoods();
    goodNameInput.val("");
    descInput.val(""); 
    goodsSelect.val(""); 
    unitOfMeasureSelect.val("");
    groupSelect.val("");

    });
    
    return false; 
}

function loadUnitsOfMeasure() {
    $.ajax({
		url : "http://localhost:8086/api/jedinicaMere/all",
        success: function(data) {
                    
            $('#unitOfMeasureSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));

			$.each(data, function (i, item) {
                    $('#unitOfMeasureSelect').append($('<option>', { 
			        value: item.idJediniceMere,
			        text : item.nazivJediniceMere
			    }));
			});
        }, 
        error: function(){
            alert("Ne mogu se ucitati jedinice mjere!");
        }
    });

}

function loadUnitsOfMeasureForUpdate() {
    $.ajax({
		url : "http://localhost:8086/api/jedinicaMere/all",
        success: function(data) {
                    
            $('#unitOfMeasureSelectEdit').append($('<option>', {
			    value: 1,
			    text: ''
			}));

			$.each(data, function (i, item) {
                    $('#unitOfMeasureSelectEdit').append($('<option>', { 
			        value: item.idJediniceMere,
			        text : item.nazivJediniceMere
			    }));
			});
        }, 
        error: function(){
            alert("Ne mogu se ucitati jedinice mjere!");
        }
    });

}

function loadGroups() {
    $.ajax({
		url : "http://localhost:8086/api/grupaRobeUsluga/all",
        success: function(data) {
                    
            $('#groupSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));

			$.each(data, function (i, item) {
                    $('#groupSelect').append($('<option>', { 
			        value: item.idGrupe,
			        text : item.nazivGrupe
			    }));
			});
        }, 
        error: function(){
            alert("Ne mogu se ucitati grupe!");
        }
    });
}

function loadGroupsForUpdate() {
    $.ajax({
		url : "http://localhost:8086/api/grupaRobeUsluga/all",
        success: function(data) {
                    
            $('#groupSelectEdit').append($('<option>', {
			    value: 1,
			    text: ''
			}));

			$.each(data, function (i, item) {
                    $('#groupSelectEdit').append($('<option>', { 
			        value: item.idGrupe,
			        text : item.nazivGrupe
			    }));
			});
        }, 
        error: function(){
            alert("Ne mogu se ucitati grupe!");
        }
    });
}

function updateGood(){
	var id = getGoodId(); 
	
    var goodNameEdit = $('#goodNameEdit'); 
    var goodName = goodNameEdit.val();

    var descEdit = $('#descEdit'); 
    var desc = descEdit.val();

    var goodsSelectEdit = $("#goodsSelectEdit"); 
    var goods = goodsSelectEdit.find(":selected").text();

    var unitOfMeasureSelectEdit = $("#unitOfMeasureSelectEdit"); 
    var unitOfMeasure = unitOfMeasureSelectEdit.find(":selected").text();

    var groupSelectEdit = $("#groupSelectEdit"); 
    var group = groupSelectEdit.find(":selected").text();
		
	
    $.ajax({
        url: "http://localhost:8086/api/robeUsluge/updateGoodsService?id=" + id + "&name=" + goodName + "&desc=" + desc + "&goods=" + goods + "&measureName=" + unitOfMeasure + "&groupName=" + group,
        type: 'PUT',
        success: function(result) {
           			
        callGoods();
        goodNameEdit.val("");
        descEdit.val(""); 
        goodsSelectEdit.val(""); 
        unitOfMeasureSelectEdit.val("");
        groupSelectEdit.val("");
        }
    });
        
	return false;
	
}

function getGoodName(){
	var row = $(".highlighted");
    var name = row.find(".nazivRobeUsluge").html();
    if(name==undefined){
    	console.log("No entity selected!");
    	return null;
    }
    else{
    	return name;
    }  
}

function getGoodId(){
	var row = $(".highlighted");
    var id = row.find(".idRobeUsluge").html();
    if(id==undefined){
    	console.log("No entity selected!");
    	return null;
    }
    else{
    	return id;
    }  
}

function deleteGood() {
    var id = getGoodId();
    $.ajax({
    	url: "http://localhost:8086/api/robeUsluge/deleteGoodsService/" + id,
    	type: "DELETE",
    	success: function(){
    		callGoods();
        }
	});
    
    return false; 
}

