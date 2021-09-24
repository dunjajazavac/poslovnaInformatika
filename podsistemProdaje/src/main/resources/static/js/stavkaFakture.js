function getStavkeFakture() {
	dobaviStavkeFakture();

	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	$(document).on("click", '#doAdd', function(event){
		//dodajStavkuFakture();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityStavkaFakture());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doUpdate", function(event) {
		//izmeniStavkuFakture();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityStavkaFakture();
		if(name!=null){
			$('#deletePromptText').text("Obrisati stavku fakture sa id: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiStavkuFakture();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchByIznos();
		$("#collapseSearch").collapse('toggle');
	});
	
	$(document).on("click", '#refresh', function(event){
		dobaviStavkeFakture();
	});
	
	$(document).on("click", '#doReset', function(event){
		var iznosSearchInput = $('#iznosSearchInput');
		iznosSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviStavkeFakture();
	});
}

function dobaviStavkeFakture() {
	var pageNo = 0; 
	var stavkaFPagination = $('#stavkaF-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/stavkefakture/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				stavkaFPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    stavkaFPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					console.log(data[i].idStavke)
					newRow = 
						"<tr>" 
							+ "<td class=\"kolicina\">" + data[i].kolicina + "</td>"
							+ "<td class=\"rabat\">" + data[i].rabat + "</td>"
							+ "<td class=\"jedinicnaCena\">" + data[i].jedinicnaCena + "</td>"
							+ "<td class=\"iznos\">" + data[i].iznos + "</td>"
							+ "<td class=\"pdvStopa\">" + data[i].pdvStopa + "</td>"
							+ "<td class=\"osnovicaZaPDV\">" + data[i].osnovicaZaPDV + "</td>"
							+ "<td class=\"iznosPDV\">" + data[i].iznosPDV + "</td>"
							+ "<td class=\"ukupanIznos\">" + data[i].ukupanIznos + "</td>"
							+ "<td class=\"faktura\">" + data[i].faktura.brojFakture + "</td>" 
							+ "<td class=\"robaUsluga\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
							+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
							+ "<td class=\"idStavke\">" + data[i].idStavke + "</td>"

						"</tr>"
					$("#dataTableBody").append(newRow);
				}
			});
	
	$("#first").click(function(){
		goFirst()
	 });
	
	$("#next").click(function(){
		goNext()
	 });
	
	nmbSelect.on('change',function (event) {
	    event.preventDefault();
	    pageSize = $(this).val();
	    dobaviStavkeFakture();
	});

	stavkaFPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviStavkeFakture();
	});
}


function searchByIznos(){
	var pageNo = 0; 
	var stavkaFPagination = $('#stavkaF-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var iznosSearchInput = $('#iznosSearchInput');
		var iznos = iznosSearchInput.val();
		console.log(iznos);
		$.ajax({
			url : "http://localhost:8086/api/stavkefakture/searchByIznos?pageNo=" + pageNo + "&pageSize=" + pageSize + "&iznos=" + iznos
		}).then(
				function(data, status, request) {
					console.log(data);
					stavkaFPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    stavkaFPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						console.log(data[i].idStavke)
						newRow = 
							"<tr>" 
								+ "<td class=\"kolicina\">" + data[i].kolicina + "</td>"
								+ "<td class=\"rabat\">" + data[i].rabat + "</td>"
								+ "<td class=\"jedinicnaCena\">" + data[i].jedinicnaCena + "</td>"
								+ "<td class=\"pdvStopa\">" + data[i].pdvStopa + "</td>"
								+ "<td class=\"osnovicaZaPDV\">" + data[i].osnovicaZaPDV + "</td>"
								+ "<td class=\"iznosPDV\">" + data[i].iznosPDV + "</td>"
								+ "<td class=\"ukupanIznos\">" + data[i].ukupanIznos + "</td>"
								+ "<td class=\"faktura\">" + data[i].faktura.brojFakture + "</td>" 
								+ "<td class=\"robaUsluga\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
								+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
								+ "<td class=\"idStavke\">" + data[i].idStavke + "</td>"

							"</tr>"
						$("#dataTableBody").append(newRow);
					}
				});
		
		$("#first").click(function(){
			goFirst()
		 });
		
		$("#next").click(function(){
			goNext()
		 });
		
		nmbSelect.on('change',function (event) {
		    event.preventDefault();
		    pageSize = $(this).val();
		    dobaviStavkeFakture();
		});

		stavkaFPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviStavkeFakture();
		});
	});
}

function obrisiStavkuFakture(){
	var id = getIdOfSelectedEntityStavkaFakture();
	console.log(id);
	
	$.ajax({
    	url: "http://localhost:8086/api/stavkefakture/obrisiStavkuFakture/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviStavkeFakture();
        }
	});
}