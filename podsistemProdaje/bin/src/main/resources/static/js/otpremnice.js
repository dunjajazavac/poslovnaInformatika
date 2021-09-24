function getOtpremnice() {
	dobaviOtpremnice();

	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	$(document).on("click", '#doAdd', function(event){
		//dodajOtpremnicu();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityOtpremnica());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doUpdate", function(event) {
		//izmeniOtpremnicu();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityOtpremnica();
		if(name!=null){
			$('#deletePromptText').text("Obrisati otpremnicu sa rednim brojem: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiOtpremnicu();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchByBrojOtpremnice();
		searchByKupac();
		searchByPrevoznik();
		$("#collapseSearch").collapse('toggle');
	});
	
	$(document).on("click", '#refresh', function(event){
		dobaviOtpremnice();
	});
	
	$(document).on("click", '#doReset', function(event){
		var brojOtpremniceInput = $('#brojSearchInput');
		brojOtpremniceInput.val("");
		var kupacInput = $('#kupacSearchInput');
		kupacInput.val("");
		var prevoznikInput = $('#prevoznikSearchInput');
		prevoznikInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviOtpremnice();
	});
}

function dobaviOtpremnice() {
	var pageNo = 0; 
	var otpremnicaPagination = $('#otpremnica-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/otpremnice/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				otpremnicaPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    otpremnicaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					newRow = 
						"<tr>" 
							+ "<td class=\"brojOtpremnice\">" + data[i].brojOtpremnice + "</td>"
							+ "<td class=\"kupac\">" + data[i].kupac + "</td>"
							+ "<td class=\"adresaIsporuke\">" + data[i].adresaIsporuke + "</td>"
							+ "<td class=\"datumIsporuke\">" + data[i].datumIsporuke + "</td>"
							+ "<td class=\"prevoznik\">" + data[i].prevoznik + "</td>"
							+ "<td class=\"potpisVozaca\">" + data[i].potpisVozaca + "</td>"
							+ "<td class=\"primioRobu\">" + data[i].primioRobu + "</td>"
							+ "<td class=\"faktura\">" + data[i].faktura.brojFakture + "</td>"
							+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
							+ "<td class=\"idOtpremnice\"  style:display:none>" + data[i].idOtpremnice + "</td>" 
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
	    dobaviOtpremnice();
	});

	otpremnicaPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviOtpremnice();
	});
}

function searchByBrojOtpremnice(){
	var pageNo = 0; 
	var otpremnicaPagination = $('#otpremnica-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$("#doSearch").on("click", function(event) {
		var brojOtpremniceInput = $('#brojSearchInput');
		var broj = brojOtpremniceInput.val();
		$.ajax({
			url : "http://localhost:8086/api/otpremnice/searchByBrojOtpremnice?pageNo=" + pageNo + "&pageSize=" + pageSize + "&broj=" + broj
		}).then(
				function(data, status, request) {
					console.log(data);
					otpremnicaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    otpremnicaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"brojOtpremnice\">" + data[i].brojOtpremnice + "</td>"
								+ "<td class=\"kupac\">" + data[i].kupac + "</td>"
								+ "<td class=\"adresaIsporuke\">" + data[i].adresaIsporuke + "</td>"
								+ "<td class=\"datumIsporuke\">" + data[i].datumIsporuke + "</td>"
								+ "<td class=\"prevoznik\">" + data[i].prevoznik + "</td>"
								+ "<td class=\"potpisVozaca\">" + data[i].potpisVozaca + "</td>"
								+ "<td class=\"primioRobu\">" + data[i].primioRobu + "</td>"
								+ "<td class=\"faktura\">" + data[i].faktura.brojFakture + "</td>"
								+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
								+ "<td class=\"idOtpremnice\"  style:display:none>" + data[i].idOtpremnice + "</td>" 
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
		    dobaviOtpremnice();
		});

		otpremnicaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviOtpremnice();
		});
	});
}

function searchByKupac(){
	var pageNo = 0; 
	var otpremnicaPagination = $('#otpremnica-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$("#doSearch").on("click", function(event) {
		var kupacInput = $('#kupacSearchInput');
		var kupac = kupacInput.val();
		$.ajax({
			url : "http://localhost:8086/api/otpremnice/searchByKupac?pageNo=" + pageNo + "&pageSize=" + pageSize + "&kupac=" + kupac
		}).then(
				function(data, status, request) {
					console.log(data);
					otpremnicaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    otpremnicaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"brojOtpremnice\">" + data[i].brojOtpremnice + "</td>"
								+ "<td class=\"kupac\">" + data[i].kupac + "</td>"
								+ "<td class=\"adresaIsporuke\">" + data[i].adresaIsporuke + "</td>"
								+ "<td class=\"datumIsporuke\">" + data[i].datumIsporuke + "</td>"
								+ "<td class=\"prevoznik\">" + data[i].prevoznik + "</td>"
								+ "<td class=\"potpisVozaca\">" + data[i].potpisVozaca + "</td>"
								+ "<td class=\"primioRobu\">" + data[i].primioRobu + "</td>"
								+ "<td class=\"faktura\">" + data[i].faktura.brojFakture + "</td>"
								+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
								+ "<td class=\"idOtpremnice\"  style:display:none>" + data[i].idOtpremnice + "</td>" 
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
		    dobaviOtpremnice();
		});

		otpremnicaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviOtpremnice();
		});
	});
}

function searchByPrevoznik(){
	var pageNo = 0; 
	var otpremnicaPagination = $('#otpremnica-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$("#doSearch").on("click", function(event) {
		var prevoznikInput = $('#prevoznikSearchInput');
		var prevoznik = prevoznikInput.val();
		$.ajax({
			url : "http://localhost:8086/api/otpremnice/searchByPrevoznik?pageNo=" + pageNo + "&pageSize=" + pageSize + "&prevoznik=" + prevoznik
		}).then(
				function(data, status, request) {
					console.log(data);
					otpremnicaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    otpremnicaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"brojOtpremnice\">" + data[i].brojOtpremnice + "</td>"
								+ "<td class=\"kupac\">" + data[i].kupac + "</td>"
								+ "<td class=\"adresaIsporuke\">" + data[i].adresaIsporuke + "</td>"
								+ "<td class=\"datumIsporuke\">" + data[i].datumIsporuke + "</td>"
								+ "<td class=\"prevoznik\">" + data[i].prevoznik + "</td>"
								+ "<td class=\"potpisVozaca\">" + data[i].potpisVozaca + "</td>"
								+ "<td class=\"primioRobu\">" + data[i].primioRobu + "</td>"
								+ "<td class=\"faktura\">" + data[i].faktura.brojFakture + "</td>"
								+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
								+ "<td class=\"idOtpremnice\"  style:display:none>" + data[i].idOtpremnice + "</td>" 
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
		    dobaviOtpremnice();
		});

		otpremnicaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviOtpremnice();
		});
	});
}

function obrisiOtpremnicu(){
	var id = getIdOfSelectedEntityOtpremnica();
	console.log(id);
	
	$.ajax({
    	url: "http://localhost:8086/api/otpremnice/obrisiOtpremnicu/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviOtpremnice();
        }
	});
}