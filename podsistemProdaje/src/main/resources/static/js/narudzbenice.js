function getNarudzbenice() {
	dobaviNarudzbenice();
	dobaviPreduzece();
	dobaviPoslovnogPartnera();
	dobaviGodine();
	dobaviRobu();

	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	$(document).on("click", '#doAdd', function(event){
		dodajNarudzbenicu();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#kreirajFakturu', function(event){
		console.log(getIdOfSelectedEntityNarudzbenica());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doKreirajFakturu", function(event) {
		kreirajFakturu();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityNarudzbenica();
		if(name!=null){
			$('#deletePromptText').text("Obrisati narudzbenicu sa brojem: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiNarudzbenicu();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchByBrojNarudzbenice();
		$("#collapseSearch").collapse('toggle');
	});
	
	$(document).on("click", '#refresh', function(event){
		dobaviNarudzbenice();
	});
	
	$(document).on("click", '#doReset', function(event){
		var brojSearchInput = $('#brojSearchInput');
		brojSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviNarudzbenice();
	});
}

function dobaviNarudzbenice() {
	var pageNo = 0; 
	var narudzbenicaPagination = $('#narudzbenica-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/narudzbenica/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				narudzbenicaPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    narudzbenicaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					newRow = 
						"<tr>" 
							+ "<td class=\"brojNarudzbenice\">" + data[i].brojNarudzbenice + "</td>"
							+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
							+ "<td class=\"poslovniPartner\">" + data[i].poslovniPartner.nazivPoslovnogPartnera + "</td>"
							+ "<td class=\"godina\">" + data[i].poslovnaGodina.godina + "</td>"
							+ "<td class=\"idNarudzbenice\"  style:display:none>" + data[i].id + "</td>" 
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
	    dobaviNarudzbenice();
	});

	narudzbenicaPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviNarudzbenice();
	});
}

function searchByBrojNarudzbenice(){
	var pageNo = 0; 
	var narudzbenicaPagination = $('#narudzbenica-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){ 
		var brojSearchInput = $('#brojSearchInput');
		var broj = brojSearchInput.val();
		console.log(broj);
		$.ajax({
			url : "http://localhost:8086/api/narudzbenica/searchByBrojNarudzbenice?pageNo=" + pageNo + "&pageSize=" + pageSize + "&broj=" + broj 
		}).then(
				function(data, status, request) {
					console.log(data);
					narudzbenicaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    narudzbenicaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"brojNarudzbenice\">" + data[i].brojNarudzbenice + "</td>"
								+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
								+ "<td class=\"poslovniPartner\">" + data[i].poslovniPartner.nazivPoslovnogPartnera + "</td>"
								+ "<td class=\"godina\">" + data[i].poslovnaGodina.godina + "</td>"
								+ "<td class=\"idNarudzbenice\"  style:display:none>" + data[i].id + "</td>" 
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
		    dobaviNarudzbenice();
		});

		narudzbenicaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviNarudzbenice();
		});
	});
}

function dobaviPreduzece() {
	$.ajax({
		url : "http://localhost:8086/api/preduzece/all"
	}).then(
		function(data) {
			$("#preduzeceSelect").empty();
			$('#preduzeceSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#preduzeceSelect').append($('<option>', { 
			        value: item.idPreduzeca,
			        text : item.nazivPreduzeca
			    }));
			});	
		}
	);
}

function dobaviPoslovnogPartnera() {
	$.ajax({
		url : "http://localhost:8086/api/partner/allMilica"
	}).then(
		function(data) {
			$("#poslovniPartnerSelect").empty();
			$('#poslovniPartnerSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#poslovniPartnerSelect').append($('<option>', { 
			        value: item.idPoslovnogPartnera,
			        text : item.nazivPoslovnogPartnera 
			    }));
			});	
		}
	);
}

function dobaviGodine() {
	$.ajax({
		url : "http://localhost:8086/api/poslovnegodine/all"
	}).then(
		function(data) {
			$("#godinaSelect").empty();
			$('#godinaSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#godinaSelect').append($('<option>', { 
			        value: item.idGodine,
			        text : item.godina 
			    }));
			});	
		}
	);
}

function dodajNarudzbenicu(){
	var brojNarudzbeniceInput = $('#brojNarudzbeniceInput');
	var preduzeceSelect = $('#preduzeceSelect');
	var poslovniPartnerSelect = $('#poslovniPartnerSelect');
	var godinaSelect = $('#godinaSelect');
	
	$('#doAdd').on('click', function(event){
		var broj_narudzbenice = brojNarudzbeniceInput.val();
		var preduzece = preduzeceSelect.find(":selected").text();
		var poslovni_partner = poslovniPartnerSelect.find(":selected").text();
		var godina = godinaSelect.find(":selected").text();
		
		console.log('broj_narudzbenice: ' + broj_narudzbenice);
		console.log('preduzece: ' + preduzece);
		console.log('poslovni_partner: ' + poslovni_partner);
		console.log('godina: ' + godina);
		
		if(broj_narudzbenice == '' || preduzece == '' || poslovni_partner == '' || godina == ''){
			alert("Niste uneli potrebne podatke.");
		}
		
		var params = {
			'broj_narudzbenice': broj_narudzbenice,
			'preduzece': preduzece,
			'poslovni_partner': poslovni_partner,
			'godina': godina
		}
		$.post("http://localhost:8086/api/narudzbenica/dodajNarudzbenicu", params, function(data) {
			console.log('ispis...')
			
			alert('Dodata je nova narudzbenica')
			
			dobaviNarudzbenice();
			brojNarudzbeniceInput.val("");
			preduzeceSelect.val("");
			poslovniPartnerSelect.val("");
			godinaSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
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

function kreirajFakturu(){
	var id = getIdOfSelectedEntityNarudzbenica();
	console.log(id);
	
	var datumValuteInput = $('#datumValuteInput');
	var rabatInput = $('#rabatInput');
	var pdvStopaInput = $('#pdvStopaInput');
	//var brojFaktureInput = $('#brojFaktureInput');
	var robaSelect = $('#robaSelect');
	
	$('#doKreirajFakturu').on('click', function(event){
		var datum_valute = datumValuteInput.val();
		var rabat = rabatInput.val();
		var pdv_stopa = pdvStopaInput.val();
		//var broj_fakture = brojFaktureInput.val();
		var roba = robaSelect.find(":selected").text();
		
		console.log('datum_valute: ' + datum_valute);
		console.log('rabat: ' + rabat);
		console.log('pdv_stopa: ' + pdv_stopa);
		//console.log('broj_fakture:' + broj_fakture)
		console.log('roba: ' + roba);
		
		if(datum_valute == '' || rabat == '' || pdv_stopa == '' || roba == ''){
			alert("Niste uneli potrebne podatke.");
		}
		
		var params = {
			'id': id,
			'datum_valute': datum_valute,
			'rabat': rabat,
			'pdv_stopa': pdv_stopa,
			'roba': roba
		}
		$.post("http://localhost:8086/api/narudzbenica/kreirajFakturu", params, function(data) {
			console.log('ispis...')
			
			alert('Dodata je nova faktura')
			
			dobaviNarudzbenice();
			datumValuteInput.val("");
			rabatInput.val("");
			pdvStopaInput.val("");
			//brojFaktureInput.val("");
			robaSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
}

function obrisiNarudzbenicu(){
	var id = getIdOfSelectedEntityNarudzbenica();
	console.log(id);
	
	$.ajax({
    	url: "http://localhost:8086/api/narudzbenica/obrisiNarudzbenicu/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviNarudzbenice();
        }
	});
}