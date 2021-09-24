function getStavkeOtpremnice() {
	dobaviStavkeOtpremnice();

	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	$(document).on("click", '#doAdd', function(event){
		//dodajStavkeOtpremnice();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityStavkaOtpremnice());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doUpdate", function(event) {
		izmeniStavkuOtpremnice();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityStavkaOtpremnice();
		if(name!=null){
			$('#deletePromptText').text("Obrisati stavku otpremnice sa rednim brojem proizv.: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiStavkuOtpremnice();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchByCena();
		searchByRedniBrojProizvoda();
		$("#collapseSearch").collapse('toggle');
	});
	
	$(document).on("click", '#refresh', function(event){
		dobaviStavkeOtpremnice();
	});
	
	$(document).on("click", '#doReset', function(event){
		var redniSearchInput = $('#redniSearchInput');
		redniSearchInput.val("");
		var cenaSearchInput = $('#cenaSearchInput');
		cenaSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviStavkeOtpremnice();
	});
}

function dobaviStavkeOtpremnice() {
	var pageNo = 0; 
	var stavkaOPagination = $('#stavkaO-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/stavkeotpremnice/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				stavkaOPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    stavkaOPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					newRow = 
						"<tr>" 
							+ "<td class=\"redniBrojProizvoda\">" + data[i].redniBrojProizvoda + "</td>"
							+ "<td class=\"jedinicaMere\">" + data[i].jedinicaMere + "</td>"
							+ "<td class=\"cena\">" + data[i].cena + "</td>"
							+ "<td class=\"kolicina\">" + data[i].kolicina + "</td>"
							+ "<td class=\"napomena\">" + data[i].napomena + "</td>"
							+ "<td class=\"otpremnica\">" + data[i].otpremnica.brojOtpremnice + "</td>"
							+ "<td class=\"roba\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
							+ "<td class=\"idStavkeOtpremnice\"  style:display:none>" + data[i].idStavkeOtpremnice + "</td>" 
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
	    dobaviStavkeOtpremnice();
	});

	stavkaOPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviStavkeOtpremnice();
	});
}

function searchByRedniBrojProizvoda(){
	var pageNo = 0; 
	var stavkaOPagination = $('#stavkaO-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var redniSearchInput = $('#redniSearchInput');
		var redni = redniSearchInput.val();
		console.log(redni);
		$.ajax({
			url : "http://localhost:8086/api/stavkeotpremnice/searchByRedniBrojProizvoda?pageNo=" + pageNo + "&pageSize=" + pageSize + "&redni=" + redni
		}).then(
				function(data, status, request) {
					console.log(data);
					stavkaOPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    stavkaOPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"redniBrojProizvoda\">" + data[i].redniBrojProizvoda + "</td>"
								+ "<td class=\"jedinicaMere\">" + data[i].jedinicaMere + "</td>"
								+ "<td class=\"cena\">" + data[i].cena + "</td>"
								+ "<td class=\"kolicina\">" + data[i].kolicina + "</td>"
								+ "<td class=\"napomena\">" + data[i].napomena + "</td>"
								+ "<td class=\"otpremnica\">" + data[i].otpremnica.brojOtpremnice + "</td>"
								+ "<td class=\"roba\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
								+ "<td class=\"idStavkeOtpremnice\"  style:display:none>" + data[i].idStavkeOtpremnice + "</td>" 
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
		    dobaviStavkeOtpremnice();
		});

		stavkaOPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviStavkeOtpremnice();
		});
	});
	
}

function searchByCena(){
	var pageNo = 0; 
	var stavkaOPagination = $('#stavkaO-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var cenaSearchInput = $('#cenaSearchInput');
		var cena = cenaSearchInput.val();
		console.log(cena);
		$.ajax({
			url : "http://localhost:8086/api/stavkeotpremnice/searchByCena?pageNo=" + pageNo + "&pageSize=" + pageSize + "&cena=" + cena
		}).then(
				function(data, status, request) {
					console.log(data);
					stavkaOPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    stavkaOPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"redniBrojProizvoda\">" + data[i].redniBrojProizvoda + "</td>"
								+ "<td class=\"jedinicaMere\">" + data[i].jedinicaMere + "</td>"
								+ "<td class=\"cena\">" + data[i].cena + "</td>"
								+ "<td class=\"kolicina\">" + data[i].kolicina + "</td>"
								+ "<td class=\"napomena\">" + data[i].napomena + "</td>"
								+ "<td class=\"otpremnica\">" + data[i].otpremnica.brojOtpremnice + "</td>"
								+ "<td class=\"roba\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
								+ "<td class=\"idStavkeOtpremnice\"  style:display:none>" + data[i].idStavkeOtpremnice + "</td>" 
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
		    dobaviStavkeOtpremnice();
		});

		stavkaOPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviStavkeOtpremnice();
		});
	});
}

function obrisiStavkuOtpremnice(){
	var id = getIdOfSelectedEntityStavkaOtpremnice();
	console.log(id);
	
	$.ajax({
    	url: "http://localhost:8086/api/stavkeotpremnice/obrisiStavkuOtpremnice/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviStavkeOtpremnice();
        }
	});
}