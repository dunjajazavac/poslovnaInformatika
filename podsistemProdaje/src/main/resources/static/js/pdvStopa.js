function getPDVStope(){
	dobaviPDVStope();
	dobaviPDVKategorije();
	dobaviPDVKategorije2();
	
	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		dodajPDVStopu();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityPDVStope());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doUpdate", function(event) {
		izmeniPDVStopu();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityPDVStope();
		if(name!=null){
			$('#deletePromptText').text("Obrisati pdv stopu: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiPDVStopu();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchPDVStopeByProcenat();
		searchPDVStope();
		$("#collapseSearch").collapse('toggle');
	});
	
	$(document).on("click", '#refresh', function(event){
		dobaviPDVStope();
	});
	
	$(document).on("click", '#doReset', function(event){
		var datumVazenjaInput = $('#datumSearchInput');
		datumVazenjaInput.val("");
		var procenatInput = $('#procenatSearchInput');
		procenatInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviPDVStope();
	});
}

function dobaviPDVStope() {
	var pageNo = 0; 
	var stopaPagination = $('#stopa-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/pdvstopa/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				stopaPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    stopaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					console.log(data[i].idStope)
					newRow = 
					"<tr>" 
						+ "<td class=\"pdvKategorija\">" + data[i].pdvKategorija.nazivKategorije + "</td>"
						+ "<td class=\"datumVazenja\">" + data[i].datumVazenja + "</td>"
						+ "<td class=\"procenat\">" + data[i].procenat + "</td>"
						+ "<td class=\"idStope\"  style:display:none>" + data[i].idStope + "</td>" +
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
	    dobaviPDVStope();
	});

	stopaPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviPDVStope();
	});
}

function searchPDVStope(){
	var pageNo = 0; 
	var stopaPagination = $('#stopa-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var datumVazenjaInput = $('#datumSearchInput');
		var datum_vazenja = datumVazenjaInput.val();
		console.log(datum_vazenja);
		$.ajax({
			url : "http://localhost:8086/api/pdvstopa/searchByDatumVazenja?pageNo=" + pageNo + "&pageSize=" + pageSize + "&datum_vazenja=" + datum_vazenja
		}).then(
				function(data, status, request) {
					console.log(data);
					stopaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    stopaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						console.log(data[i].idStope)
						newRow = 
						"<tr>" 
							+ "<td class=\"pdvKategorija\">" + data[i].pdvKategorija.nazivKategorije + "</td>"
							+ "<td class=\"datumVazenja\">" + data[i].datumVazenja + "</td>"
							+ "<td class=\"procenat\">" + data[i].procenat + "</td>"
							+ "<td class=\"idStope\"  style:display:none>" + data[i].idStope + "</td>" +
						"</tr>"
						$("#dataTableBody").append(newRow);
					}
				});
		nmbSelect.on('change',function (event) {
		    event.preventDefault();
		    pageSize = $(this).val();
		    dobaviPDVStope();
		});

		stopaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviPDVStope();
		});
	});
	
}

function searchPDVStopeByProcenat(){
	var pageNo = 0; 
	var stopaPagination = $('#stopa-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var procenatInput = $('#procenatSearchInput');
		var procenat = procenatInput.val();
		console.log(procenat);
		$.ajax({
			url : "http://localhost:8086/api/pdvstopa/searchByProcenat?pageNo=" + pageNo + "&pageSize=" + pageSize + "&procenat=" + procenat
		}).then(
				function(data, status, request) {
					console.log(data);
					stopaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    stopaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						console.log(data[i].idStope)
						newRow = 
						"<tr>" 
							+ "<td class=\"pdvKategorija\">" + data[i].pdvKategorija.nazivKategorije + "</td>"
							+ "<td class=\"datumVazenja\">" + data[i].datumVazenja + "</td>"
							+ "<td class=\"procenat\">" + data[i].procenat + "</td>"
							+ "<td class=\"idStope\"  style:display:none>" + data[i].idStope + "</td>" +
						"</tr>"
						$("#dataTableBody").append(newRow);
					}
				});
		nmbSelect.on('change',function (event) {
		    event.preventDefault();
		    pageSize = $(this).val();
		    dobaviPDVStope();
		});

		stopaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviPDVStope();
		});
	});
	
}

function dobaviPDVKategorije() {
	$.ajax({
		url : "http://localhost:8086/api/pdvKategorije/svi"
	}).then(
		function(data) {
			$("#kategorijaSelect").empty();
			$('#kategorijaSelect').append($('<option>', {
			    value: 1,
			    text: ""
			}));
			
			$.each(data, function (i,item) {
			    $('#kategorijaSelect').append($('<option>', { 
			        value: item.idKategorije,
			        text : item.nazivKategorije
			    }));
			});	
		}
	);
}

function dobaviPDVKategorije2() {
	$.ajax({
		url : "http://localhost:8086/api/pdvKategorije/svi"
	}).then(
		function(data) {
			$("#kategorijaIzmeniSelect").empty();
			$('#kategorijaIzmeniSelect').append($('<option>', {
			    value: 1,
			    text: ""
			}));
			
			$.each(data, function (i, item) {
			    $('#kategorijaIzmeniSelect').append($('<option>', { 
			        value: item.idKategorije,
			        text : item.nazivKategorije 
			    }));
			});	
		}
	);
}
function dodajPDVStopu(){
	var datumInput = $('#datumInput');
	var procenatInput = $('#procenatInput');
	var kategorijaSelect = $('#kategorijaSelect');
	
	$('#doAdd').on('click', function(event){
		var datum_vazenja = datumInput.val();
		var procenat = procenatInput.val();
		var pdvKategorija = kategorijaSelect.find(":selected").text();
		
		console.log('datum_vazenja: ' + datum_vazenja)
		console.log('procenat: ' + procenat);
		console.log('pdvKategorija: ' + pdvKategorija);
		
		if(datum_vazenja == '' || procenat == '' || pdvKategorija == null){
			alert("Niste uneli potrebne podatke.");
		}
		
		var params = {
			'datum_vazenja': datum_vazenja,
			'procenat': procenat,
			'pdvKategorija': pdvKategorija
		}
		$.post("http://localhost:8086/api/pdvstopa/dodajPDVStopu", params, function(data) {
			console.log('ispis...')
			
			alert('Dodata je nova PDV stopa')
			
			dobaviPDVStope();
			datumInput.val("");
			procenatInput.val("");
			kategorijaSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
}

function izmeniPDVStopu() {
	var id = getIdOfSelectedEntityPDVStope();
	console.log(id);
	
	var datumIzmeniInput = $('#datumIzmeniInput');
	var procenatIzmeniInput = $('#procenatIzmeniInput');
	var kategorijaIzmeniSelect = $('#kategorijaIzmeniSelect');
	
	$("#doUpdate").on("click", function(event) {
		var datum_vazenja = datumIzmeniInput.val();
		var procenat = procenatIzmeniInput.val();
		var pdvKategorija = kategorijaIzmeniSelect.find(":selected").text();
		
		console.log('datum_vazenja: ' + datum_vazenja);
		console.log('procenat: ' + procenat);
		console.log('pdvKategorija: ' + pdvKategorija);
		
		if(datum_vazenja == '' || procenat == '' || pdvKategorija == null){
			alert("Niste uneli potrebne podatke.");
		}
		
		var params = {
				'id': id,
				'datum_vazenja': datum_vazenja,
				'procenat': procenat,
				'pdvKategorija': pdvKategorija
		}
		$.post("http://localhost:8086/api/pdvstopa/izmeniPDVStopu/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena pdv stope');
			
			dobaviPDVStope();
			datumIzmeniInput.val("");
			procenatIzmeniInput.val("");
			kategorijaIzmeniSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
	});
}

function obrisiPDVStopu(){
	var id = getIdOfSelectedEntityPDVStope();
	console.log(id);
	$.ajax({
    	url: "http://localhost:8086/api/pdvstopa/obrisiPDVStopu/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviPDVStope();
        }
	});
}