function getMesta() {
	dobaviMesta();

	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	$(document).on("click", '#doAdd', function(event){
		dodajMesto();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityMesto());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doUpdate", function(event) {
		izmeniMesto();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityMesto();
		if(name!=null){
			$('#deletePromptText').text("Obrisati: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiMesto();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchMestoByNaziv();
		searchMestoByPttBroj();
		$("#collapseSearch").collapse('toggle');
	});
	
	$(document).on("click", '#refresh', function(event){
		dobaviMesta();
	});
	
	$(document).on("click", '#doReset', function(event){
		var nazivSearchInput = $('#nazivMestaSearchInput');
		nazivSearchInput.val("");
		var pttBrojSearchInput = $('#pttBrojSearchInput');
		pttBrojSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviMesta();
	});
}

function dobaviMesta() {
	var pageNo = 0; 
	var mestoPagination = $('#mesto-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/mesto/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				mestoPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    mestoPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					newRow = 
						"<tr>" 
							+ "<td class=\"nazivMesta\">" + data[i].nazivMesta + "</td>"
							+ "<td class=\"pttBroj\">" + data[i].pttBroj + "</td>"
							+ "<td class=\"idMesta\"  style:display:none>" + data[i].idMesta + "</td>" +
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
	    dobaviMesta();
	});

	mestoPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviMesto();
	});
}

function searchMestoByNaziv(){
	var pageNo = 0; 
	var mestoPagination = $('#mesto-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var nazivSearchInput = $('#nazivMestaSearchInput');
		var naziv = nazivSearchInput.val();
		console.log(naziv);
		$.ajax({
			url : "http://localhost:8086/api/mesto/searchByNaziv?pageNo=" + pageNo + "&pageSize=" + pageSize + "&naziv=" + naziv
		}).then(
				function(data, status, request) {
					console.log(data);
					mestoPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    mestoPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"nazivMesta\">" + data[i].nazivMesta + "</td>"
								+ "<td class=\"pttBroj\">" + data[i].pttBroj + "</td>"
								+ "<td class=\"idMesta\"  style:display:none>" + data[i].idMesta + "</td>" +
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
		    dobaviMesta();
		});

		mestoPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviMesto();
		});
	});
}

function searchMestoByPttBroj(){
	var pageNo = 0; 
	var mestoPagination = $('#mesto-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var pttBrojSearchInput = $('#pttBrojSearchInput');
		var ptt_broj = pttBrojSearchInput.val();
		console.log(ptt_broj);
		$.ajax({
			url : "http://localhost:8086/api/mesto/searchByPttBroj?pageNo=" + pageNo + "&pageSize=" + pageSize + "&ptt_broj=" + ptt_broj 
		}).then(
				function(data, status, request) {
					console.log(data);
					mestoPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    mestoPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						newRow = 
							"<tr>" 
								+ "<td class=\"nazivMesta\">" + data[i].nazivMesta + "</td>"
								+ "<td class=\"pttBroj\">" + data[i].pttBroj + "</td>"
								+ "<td class=\"idMesta\"  style:display:none>" + data[i].idMesta + "</td>" +
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
		    dobaviMesta();
		});

		mestoPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviMesto();
		});
	});
}

function dodajMesto(){
	var nazivMestaInput = $('#nazivMestaInput');
	var pttBrojInput = $('#pttBrojInput');
	
	$('#doAdd').on('click', function(event){
		var naziv_mesta = nazivMestaInput.val();
		var ptt_broj = pttBrojInput.val();
		
		console.log('naziv_mesta: ' + naziv_mesta);
		console.log('ptt_broj: ' + ptt_broj);
		
		if(naziv_mesta == '' || ptt_broj == ''){
			alert("Niste uneli potrebne podatke.");
		}
		
		var params = {
			'naziv_mesta': naziv_mesta,
			'ptt_broj': ptt_broj	
		}
		$.post("http://localhost:8086/api/mesto/dodajMesto", params, function(data) {
			console.log('ispis...')
			
			alert('Dodata je novo mesto');
			
			dobaviMesta();
			nazivMestaInput.val("");
			pttBrojInput.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
	
}

function izmeniMesto() {
	var id = getIdOfSelectedEntityMesto();
	console.log(id);
	
	var nazivMestaIzmeniInput = $('#nazivMestaIzmeniInput');
	var pttBrojIzmeniInput = $('#pttBrojIzmeniInput');
	
	$("#doUpdate").on("click", function(event) {
		var naziv_mesta = nazivMestaIzmeniInput.val();
		var ptt_broj = pttBrojIzmeniInput.val();
		
		console.log('naziv_mesta: ' + naziv_mesta);
		console.log('ptt_broj: ' + ptt_broj);
		
		if(naziv_mesta == '' || ptt_broj == ''){
			alert("Niste uneli potrebne podatke.");
		}
		
		var params = {
				'id': id,
				'naziv_mesta': naziv_mesta,
				'ptt_broj': ptt_broj	
		}
		$.post("http://localhost:8086/api/mesto/izmeniMesto/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena naseljenog mesta');
			
			dobaviMesta();
			nazivMestaIzmeniInput.val("");
			pttBrojIzmeniInput.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
		
	});
	
}

function obrisiMesto(){
	var id = getIdOfSelectedEntityMesto();
	console.log(id);
	
	$.ajax({
    	url: "http://localhost:8086/api/mesto/obrisiMesto/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviMesta();
        }
	});
}