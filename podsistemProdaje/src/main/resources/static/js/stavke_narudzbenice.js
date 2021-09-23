function getStavkeNarudzbenice() {
	dobaviStavkeNarudzbenice();
	dobaviNarudzbenice();
	dobaviRobu();

	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	$(document).on("click", '#doAdd', function(event){
		dodajStavkuNarudzbenice();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityStavkaNarudzbenice());
		$('#updateModalScrollable').modal('show');
	});
	
//	$(document).on("click", "#doUpdate", function(event) {
//		//izmeniStavkuNarudzbenice();
//		$('#updateModalScrollable').modal('hide');
//	});
//	
//	$(document).on("click", '.updateModalClose', function(event) {
//		$('#updateModalScrollable').modal('hide');
//	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityStavkaNarudzbenice();
		if(name!=null){
			$('#deletePromptText').text("Obrisati stavku narudzbenice: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiStavkuNarudzbenice();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchByCena();
		$("#collapseSearch").collapse('toggle');
	});
	
	
	$(document).on("click", '#refresh', function(event){
		dobaviStavkeNarudzbenice();
	});
	
	$(document).on("click", '#doReset', function(event){
		var cenaSearchInput = $('#cenaSearchInput');
		cenaSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviStavkeNarudzbenice();
	});
}

function dobaviStavkeNarudzbenice() {
	var pageNo = 0; 
	var stavkaNPagination = $('#stavkaN-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/stavkenarudzbenice/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				stavkaNPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    stavkaNPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					newRow = 
						"<tr>" 
							+ "<td class=\"jedinicaMere\">" + data[i].jedinicaMere + "</td>"
							+ "<td class=\"kolicina\">" + data[i].kolicina + "</td>"
							+ "<td class=\"cena\">" + data[i].cena + "</td>"
							+ "<td class=\"iznos\">" + data[i].iznos + "</td>"
							+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
							+ "<td class=\"robaUsluga\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
							+ "<td class=\"idStavkeNarudzbenice\"  style:display:none>" + data[i].id + "</td>" 
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
	    dobaviStavkeNarudzbenice();
	});

	stavkaNPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviStavkeNarudzbenice();
	});
}

function searchByCena(){
	var pageNo = 0; 
	var stavkaNPagination = $('#stavkaN-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var cenaSearchInput = $('#cenaSearchInput');
		var cena = cenaSearchInput.val();
		console.log(cena);
		$.ajax({
			url : "http://localhost:8086/api/stavkenarudzbenice/searchByCena?pageNo=" + pageNo + "&pageSize=" + pageSize + "&cena=" + cena
		}).then(
				function(data, status, request) {
					console.log(data);
					stavkaNPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    stavkaNPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"jedinicaMere\">" + data[i].jedinicaMere + "</td>"
								+ "<td class=\"kolicina\">" + data[i].kolicina + "</td>"
								+ "<td class=\"cena\">" + data[i].cena + "</td>"
								+ "<td class=\"iznos\">" + data[i].iznos + "</td>"
								+ "<td class=\"narudzbenica\">" + data[i].narudzbenica.brojNarudzbenice + "</td>"
								+ "<td class=\"robaUsluga\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
								+ "<td class=\"idStavkeNarudzbenice\"  style:display:none>" + data[i].id + "</td>" 
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
		    dobaviStavkeNarudzbenice();
		});

		stavkaNPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviStavkeNarudzbenice();
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

function dodajStavkuNarudzbenice(){
	var jedinicaMereInput = $('#jedinicaMereInput');
	var kolicinaInput = $('#kolicinaInput');
	var cenaInput = $('#cenaInput');
	//var iznosInput = $('#iznosInput');
	var narudzbenicaSelect = $('#narudzbenicaSelect');
	var robaSelect = $('#robaSelect');
	
	$('#doAdd').on('click', function(event){
		var jedinica_mere = jedinicaMereInput.val();
		var kolicina = kolicinaInput.val();
		var cena = cenaInput.val();
		var narudzbenica = narudzbenicaSelect.find(":selected").text();
		var roba = robaSelect.find(":selected").text();
		
		console.log('jedinica_mere: ' + jedinica_mere);
		console.log('kolicina: ' + kolicina);
		console.log('cena: ' + cena);
		console.log('narudzbenica: ' + narudzbenica);
		console.log('roba: ' + roba);
		
		if(jedinica_mere == '' || kolicina == '' || cena == '' || narudzbenica == '' || roba == ''){
			alert("Niste uneli potrebne podatke.");
		}
		
		var params = {
			'jedinica_mere': jedinica_mere,
			'kolicina': kolicina,
			'cena': cena,
			'narudzbenica': narudzbenica,
			'roba': roba
		}
		$.post("http://localhost:8086/api/stavkenarudzbenice/dodajStavkuNarudzbenice", params, function(data) {
			console.log('ispis...')
			
			alert('Dodata je nova stavka narudzbenice')
			
			dobaviStavkeNarudzbenice();
			jedinicaMereInput.val("");
			kolicinaInput.val("");
			cenaInput.val("");
			narudzbenicaSelect.val("");
			robaSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
}

function obrisiStavkuNarudzbenice(){
	var id = getIdOfSelectedEntityStavkaNarudzbenice();
	console.log(id);
	
	$.ajax({
    	url: "http://localhost:8086/api/stavkenarudzbenice/obrisiStavkuNarudzbenice/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviStavkeNarudzbenice();
        }
	});
}