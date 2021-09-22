function getFakture() {
	dobaviFakture();
	dobaviNarudzbenice();
	dobaviRobu();

	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#kif', function(event){
		$('#addModalScrollable').modal('show');
	});
	$(document).on("click", '#doKIF', function(event){
		kreirajKIF();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityFaktura());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doUpdate", function(event) {
		kreirajOtpremnicu();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	//brisanje
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityFaktura();
		if(name!=null){
			$('#deletePromptText').text("Obrisati: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiFakturu();
		$('#deletePromptModal').modal('hide');
	});
	
	//storniranje
	$(document).on("click", '#storno', function(event){
		var name = getNameOfSelectedEntityFaktura();
		if(name!=null){
			$('#stornoPromptText').text("Storniraj: " + name);
			$('#stornoPromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.stornoPromptClose', function(event){
		$('#stornoPromptModal').modal('hide');
	});
	
	$(document).on("click", '#doStorniraj', function(event){
		stornirajFakturu();
		$('#stornoPromptModal').modal('hide');
	});
	
	//export u xml
	$(document).on("click", '#export', function(event){
		var name = getNameOfSelectedEntityFaktura();
		if(name!=null){
			$('#exportPromptText').text("Export fakture broj: " + name);
			$('#exportPromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.exportPromptClose', function(event){
		$('#exportPromptModal').modal('hide');
	});
	
	$(document).on("click", '#doExport', function(event){
		exportFakture();
		$('#exportPromptModal').modal('hide');
	});
	
	//report
	$(document).on("click", '#report', function(event){
		var name = getNameOfSelectedEntityFaktura();
		if(name!=null){
			$('#reportPromptText').text("Report fakture broj: " + name);
			$('#reportPromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.reportPromptClose', function(event){
		$('#reportPromptModal').modal('hide');
	});
	
	$(document).on("click", '#doReport', function(event){
		reportFakture();
		$('#reportPromptModal').modal('hide');
	});
	
	
	//pretraga
	$(document).on("click", '#search', function(event){
		searchByBrojFakture();
		searchByStatusFakture();
		$("#collapseSearch").collapse('toggle');
	});
	
	//refresh
	$(document).on("click", '#refresh', function(event){
		dobaviFakture();
	});
	
	//ponisti dugme
	$(document).on("click", '#doReset', function(event){
		var brojSearchInput = $('#brojSearchInput');
		brojSearchInput.val("");
		var statusSearchInput = $('#statusSearchInput');
		statusSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviFakture();
	});
}

function dobaviFakture() {
	var pageNo = 0; 
	var fakturaPagination = $('#faktura-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/fakture/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				fakturaPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    fakturaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					newRow = 
						"<tr>" 
							+ "<td class=\"brojFakture\">" + data[i].brojFakture + "</td>"
							+ "<td class=\"datumFakture\">" + new Date(data[i].datumFakture) + "</td>"
							+ "<td class=\"datumValute\">" + new Date(data[i].datumValute) + "</td>"
							+ "<td class=\"ukupnaOsnovica\">" + data[i].ukupnaOsnovica + "</td>"
							+ "<td class=\"ukupanPDV\">" + data[i].ukupanPDV + "</td>"
							+ "<td class=\"ukupanIznos\">" + data[i].ukupanIznos + "</td>"
							+ "<td class=\"statusFakture\">" + data[i].statusFakture + "</td>"
							+ "<td class=\"godina\">" + data[i].poslovnaGodina.godina + "</td>"
							+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
							+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
							+ "<td class=\"poslovniPartner\">" + data[i].poslovniPartner.nazivPoslovnogPartnera + "</td>"
							+ "<td class=\"idFakture\">" + data[i].idFakture + "</td>"
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
	    dobaviFakture();
	});

	fakturaPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviFakture();
	});
}

function searchByBrojFakture(){
	var pageNo = 0; 
	var fakturaPagination = $('#faktura-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var brojSearchInput = $('#brojSearchInput');
		var broj = brojSearchInput.val();
		console.log(broj);
		$.ajax({
			url : "http://localhost:8086/api/fakture/searchByBrojFakture?pageNo=" + pageNo + "&pageSize=" + pageSize + "&broj=" + broj
		}).then(
				function(data, status, request) {
					console.log(data);
					fakturaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    fakturaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"brojFakture\">" + data[i].brojFakture + "</td>"
								+ "<td class=\"datumFakture\">" + data[i].datumFakture + "</td>"
								+ "<td class=\"datumValute\">" + data[i].datumValute + "</td>"
								+ "<td class=\"ukupnaOsnovica\">" + data[i].ukupnaOsnovica + "</td>"
								+ "<td class=\"ukupanPDV\">" + data[i].ukupanPDV + "</td>"
								+ "<td class=\"ukupanIznos\">" + data[i].ukupanIznos + "</td>"
								+ "<td class=\"statusFakture\">" + data[i].statusFakture + "</td>"
								+ "<td class=\"godina\">" + data[i].poslovnaGodina.godina + "</td>"
								+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
								+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
								+ "<td class=\"poslovniPartner\">" + data[i].poslovniPartner.nazivPoslovnogPartnera + "</td>"
								+ "<td class=\"idFakture\">" + data[i].idFakture + "</td>"
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
		    dobaviFakture();
		});

		fakturaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviFakture();
		});
	});
}

function searchByStatusFakture(){
	var pageNo = 0; 
	var fakturaPagination = $('#faktura-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var statusSearchInput = $('#statusSearchInput');
		var status = statusSearchInput.val();
		console.log(status);
		$.ajax({
			url : "http://localhost:8086/api/fakture/searchByStatusFakture?pageNo=" + pageNo + "&pageSize=" + pageSize + "&status=" + status
		}).then(
				function(data, status, request) {
					console.log(data);
					fakturaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    fakturaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"brojFakture\">" + data[i].brojFakture + "</td>"
								+ "<td class=\"datumFakture\">" + data[i].datumFakture + "</td>"
								+ "<td class=\"datumValute\">" + data[i].datumValute + "</td>"
								+ "<td class=\"ukupnaOsnovica\">" + data[i].ukupnaOsnovica + "</td>"
								+ "<td class=\"ukupanPDV\">" + data[i].ukupanPDV + "</td>"
								+ "<td class=\"ukupanIznos\">" + data[i].ukupanIznos + "</td>"
								+ "<td class=\"statusFakture\">" + data[i].statusFakture + "</td>"
								+ "<td class=\"godina\">" + data[i].poslovnaGodina.godina + "</td>"
								+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
								+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
								+ "<td class=\"poslovniPartner\">" + data[i].poslovniPartner.nazivPoslovnogPartnera + "</td>"
								+ "<td class=\"idFakture\">" + data[i].idFakture + "</td>"
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
		    dobaviFakture();
		});

		fakturaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviFakture();
		});
	});
}

function dobaviNarudzbenice() {
	$.ajax({
		url : "http://localhost:8086/api/narudzbenica/all"
	}).then(
		function(data) {
			$("#narudzbenicaSelect").empty();
			$('#narudzbenicaSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#narudzbenicaSelect').append($('<option>', { 
			        value: item.idNarudzbenice,
			        text : item.brojNarudzbenice 
			    }));
			});	
		}
	);
}

function dobaviRobu() {
	$.ajax({
		url : "http://localhost:8086/api/robeUsluge/allMilica"
	}).then(
		function(data) {
			$("#robaSelect").empty();
			$('#robaSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#robaSelect').append($('<option>', { 
			        value: item.idRobeUsluge,
			        text : item.nazivRobeUsluge 
			    }));
			});	
		}
	);
}


function obrisiFakturu(){
	var id = getIdOfSelectedEntityFaktura();
	console.log(id);
	
	$.ajax({
    	url: "http://localhost:8086/api/fakture/obrisiFakturu/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviFakture();
        }
	});
}

function stornirajFakturu(){
	var id = getIdOfSelectedEntityFaktura();
	console.log(id);
	var broj_fakture = getNameOfSelectedEntityFaktura();
	
	$('#doStorniraj').on('click', function(event){
		console.log('broj_fakture: ' + broj_fakture);
		
		var params = {
				'broj_fakture': broj_fakture
		}
		
		$.post("http://localhost:8086/api/fakture/stornirajFakturu", params, function(data) {
			console.log('ispis...')
			
			alert('Stornirana je faktura')
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
}

function exportFakture(){
	var id = getIdOfSelectedEntityFaktura();
	console.log(id);
	var broj_fakture = getNameOfSelectedEntityFaktura();
	
	$('#doExport').on('click', function(event){
		console.log('broj_fakture: ' + broj_fakture);
		
		var params = {
				'broj_fakture': broj_fakture
		}
		
		$.post("http://localhost:8086/api/fakture/exportFakture", params, function(data) {
			console.log('ispis...')
			
			alert('Exportovana je faktura')
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
}

function reportFakture(){
	var id = getIdOfSelectedEntityFaktura();
	console.log(id);
	
	$('#doReport').on('click', function(event){
		console.log('id: ' + id);
		
		var params = {'id': id}
	
		$.post("http://localhost:8086/api/fakture/jasperReportFaktura", params, function(data) {
			console.log('ispis...')
			
			alert('Jasper report')
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
}

function kreirajKIF(){
	var formatSelect = $('#formatSelect');
	var format = formatSelect.find(":selected").text();
	
	$('#doKIF').on('click', function(event){
		console.log('format: ' + format);
		
		var params = {'format': format}
	
		$.post("http://localhost:8086/api/fakture/dnevnik", params, function(data) {
			console.log('ispis...')
			
			alert('Jasper report dnevnik')
			
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
	
}

function kreirajOtpremnicu(){
	var id = getIdOfSelectedEntityFaktura();
	console.log(id);
	
	//var brojOtpremniceInput = $('#brojOtpremniceInput');
	var datumIsporukeInput = $('#datumIsporukeInput');
	var prevoznikInput = $('#prevoznikInput');
	var narudzbenicaSelect = $('#narudzbenicaSelect');
	var redniBrojProizvodaInput = $('#redniBrojProizvodaInput');
	var jedinicaMereInput = $('#jedinicaMereInput');
	//var kolicinaInput = $('#kolicinaInput');
	var napomenaInput = $('#napomenaInput');
	var robaSelect = $('#robaSelect');
	
	$("#doUpdate").on("click", function(event) {
		//var broj_otpremnice = brojOtpremniceInput.val();
		var datum_isporuke = datumIsporukeInput.val();
		var prevoznik = prevoznikInput.val();
		var narudzbenica = narudzbenicaSelect.find(":selected").text();
		var redni_broj_proizvoda = redniBrojProizvodaInput.val();
		var jedinica_mere = jedinicaMereInput.val();
		//var kolicina = kolicinaInput.val();
		var napomena = napomenaInput.val();
		var roba = robaSelect.find(":selected").text();
		
		
		//console.log('broj_otpremnice: ' + broj_otpremnice);
		console.log('datum_isporuke: ' + datum_isporuke);
		console.log('prevoznik: ' + prevoznik);
		console.log('narudzbenica: ' + narudzbenica);
		console.log('redni_broj_proizvoda: ' + redni_broj_proizvoda);
		console.log('jedinica_mere: ' + jedinica_mere);
		//console.log('kolicina: ' + kolicina);
		console.log('napomena: ' + napomena);
		console.log('roba: ' + roba);
		
		if(datum_isporuke == '' || prevoznik == '' || narudzbenica == '' || redni_broj_proizvoda == ''  || roba == ''){
			alert("Niste uneli potrebne podatke.");
		}
		
		var params = {
				'id': id,
				//'broj_otpremnice': broj_otpremnice,
				'datum_isporuke': datum_isporuke,
				'prevoznik': prevoznik,
				'narudzbenica': narudzbenica,
				'redni_broj_proizvoda': redni_broj_proizvoda,
				'jedinica_mere': jedinica_mere,
				//'kolicina': kolicina,
				'napomena': napomena,
				'roba': roba
		}
		$.post("http://localhost:8086/api/fakture/kreirajOtpremnicu/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Kreirana je otpremnica na osnovu fakture');
			
			dobaviFakture();
			//brojOtpremniceInput.val("");
			datumIsporukeInput.val("");
			prevoznikInput.val("");
			narudzbenicaSelect.val("");
			redniBrojProizvodaInput.val("");
			jedinicaMereInput.val("");
			//kolicinaInput.val("");
			napomenaInput.val("");
			robaSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
		
	});
}