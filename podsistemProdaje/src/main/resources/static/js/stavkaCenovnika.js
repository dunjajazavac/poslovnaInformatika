function getStavkeCenovnika(){
	dobaviStavkeCenovnika();
	dobaviCenovnik();
	dobaviRobu();
	dobaviCenovnik2();
	dobaviRobu2();
	
	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		dodajStavkuCenovnika();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityStavkaCenovnika());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doUpdate", function(event) {
		izmeniStavkuCenovnika();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityStavkaCenovnika();
		if(name!=null){
			$('#deletePromptText').text("Obrisati stavku cenovnika sa cenom: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiStavkuCenovnika();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchStavkaCenovnika();
		$("#collapseSearch").collapse('toggle');
	});
	
	$(document).on("click", '#refresh', function(event){
		dobaviStavkeCenovnika();
	});
	
	$(document).on("click", '#doReset', function(event){
		var cenaSearchInput = $('#cenaSearchInput');
		cenaSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviStavkeCenovnika();
	});
}

function dobaviStavkeCenovnika() {
	var pageNo = 0; 
	var stavkaCPagination = $('#stavkaC-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/stavkecenovnika/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				stavkaCPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    stavkaCPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					console.log(data[i].idStavke)
					newRow = 
					"<tr>" 
						+ "<td class=\"cena\">" + data[i].cena + "</td>"
						+ "<td class=\"cenovnik\">" + data[i].cenovnik.datumPocetkaVazenja + "</td>"
						+ "<td class=\"roba\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
						+ "<td class=\"idStavke\"  style:display:none>" + data[i].idStavke + "</td>" +
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
	    dobaviStavkeCenovnika();
	});

	stavkaCPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviStavkeCenovnika();
	});
}

function searchStavkaCenovnika(){
	var pageNo = 0; 
	var stavkaCPagination = $('#stavkaC-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){ 
		var cenaSearchInput = $('#cenaSearchInput');
		var cena = cenaSearchInput.val();
		console.log(cena);
		$.ajax({
			url : "http://localhost:8086/api/stavkecenovnika/searchByCena?pageNo=" + pageNo + "&pageSize=" + pageSize + "&cena=" + cena
		}).then(
				function(data, status, request) {
					console.log(data);
					stavkaCPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    stavkaCPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						console.log(data[i].idStavke)
						newRow = 
						"<tr>" 
							+ "<td class=\"cena\">" + data[i].cena + "</td>"
							+ "<td class=\"cenovnik\">" + data[i].cenovnik.datumPocetkaVazenja + "</td>"
							+ "<td class=\"roba\">" + data[i].robaUsluga.nazivRobeUsluge + "</td>"
							+ "<td class=\"idStavke\"  style:display:none>" + data[i].idStavke + "</td>" +
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
		    dobaviStavkeCenovnika();
		});

		stavkaCPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviStavkeCenovnika();
		});
	});
}

function dobaviCenovnik() {
	$.ajax({
		url : "http://localhost:8086/api/cenovnici/all"
	}).then(
		function(data) {
			$("#cenovnikSelect").empty();
			$('#cenovnikSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#cenovnikSelect').append($('<option>', { 
			        value: item.idCenovnika,
			        text : item.datumPocetkaVazenja
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

function dobaviCenovnik2() {
	$.ajax({
		url : "http://localhost:8086/api/cenovnici/all"
	}).then(
		function(data) {
			$("#cenovnikIzmeniSelect").empty();
			$('#cenovnikIzmeniSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#cenovnikIzmeniSelect').append($('<option>', { 
			        value: item.idCenovnika,
			        text : item.datumPocetkaVazenja
			    }));
			});	
		}
	);
}

function dobaviRobu2() {
	$.ajax({
		url : "http://localhost:8086/api/robeUsluge/allMilica"
	}).then(
		function(data) {
			$("#robaIzmeniSelect").empty();
			$('#robaIzmeniSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#robaIzmeniSelect').append($('<option>', { 
			        value: item.idRobeUsluge,
			        text : item.nazivRobeUsluge
			    }));
			});	
		}
	);
}
function dodajStavkuCenovnika(){
	var cenaInput = $('#cenaInput');
	var cenovnikSelect = $('#cenovnikSelect');
	var robaSelect = $('#robaSelect');
	
	$('#doAdd').on('click', function(event){
		var cena = cenaInput.val();
		var cenovnik = cenovnikSelect.find(":selected").text();
		var roba = robaSelect.find(":selected").text();
		
		console.log('cena: ' + cena)
		console.log('cenovnik: ' + cenovnik);
		console.log('roba: ' + roba);

		if(cena == null || cenovnik == null || roba == null){
			alert('Niste uneli potrebne podatke')
		}
		
		var params = {
			'cena': cena,
			'cenovnik': cenovnik,
			'roba': roba
		}
		$.post("http://localhost:8086/api/stavkecenovnika/dodajStavkuCenovnika", params, function(data) {
			console.log('ispis...')
			
			alert('Dodata je nova stavka cenovnika')
			
			dobaviStavkeCenovnika();
			cenaInput.val("");
			cenovnikSelect.val("");
			robaSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
}

function izmeniStavkuCenovnika() {
	var id = getIdOfSelectedEntityStavkaCenovnika();
	console.log(id);
	
	var cenaIzmeniInput = $('#cenaIzmeniInput');
	var cenovnikIzmeniSelect = $('#cenovnikIzmeniSelect');
	var robaIzmeniSelect = $('#robaIzmeniSelect');

	
	$("#doUpdate").on("click", function(event) {
		var cena = cenaIzmeniInput.val();
		var cenovnik = cenovnikIzmeniSelect.find(":selected").text();
		var roba = robaIzmeniSelect.find(":selected").text();
		
		console.log('cena: ' + cena)
		console.log('cenovnik: ' + cenovnik);
		console.log('roba: ' + roba);

		
		var params = {
				'id': id,
				'cena': cena,
				'cenovnik': cenovnik,
				'roba': roba
		}
		$.post("http://localhost:8086/api/stavkecenovnika/izmeniStavkuCenovnika/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena stavke cenovnika');
			
			dobaviStavkeCenovnika();
			cenaIzmeniInput.val("");
			cenovnikIzmeniSelect.val("");
			robaIzmeniSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
		
	});
}

function obrisiStavkuCenovnika(){
	var id = getIdOfSelectedEntityStavkaCenovnika();
	console.log(id);
	$.ajax({
    	url: "http://localhost:8086/api/stavkecenovnika/obrisiStavkuCenovnika/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviStavkeCenovnika();
        }
	});
}