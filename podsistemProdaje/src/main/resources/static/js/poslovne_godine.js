function getGodine(){
	dobaviGodine();
	dobaviPreduzeca();
	dobaviPreduzeca2();
	
	$(document).on("click", 'tr', function(event) {
		highlightRow(this);
	});
	
	$(document).on("click", '#add', function(event){
		$('#addModalScrollable').modal('show');
	});
	
	$(document).on("click", '#doAdd', function(event){
		dodajGodinu();
		$('#addModalScrollable').modal('hide');				
	});
	
	$(document).on("click", '.addModalClose', function(event){
		$('#addModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#edit', function(event){
		console.log(getIdOfSelectedEntityGodina());
		$('#updateModalScrollable').modal('show');
	});
	
	$(document).on("click", "#doUpdate", function(event) {
		izmeniGodinu();
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '.updateModalClose', function(event) {
		$('#updateModalScrollable').modal('hide');
	});
	
	$(document).on("click", '#delete', function(event){
		var name = getNameOfSelectedEntityGodina();
		if(name!=null){
			$('#deletePromptText').text("Obrisati godinu: " + name);
			$('#deletePromptModal').modal('show');
		}

	});
	
	$(document).on("click", '.deletePromptClose', function(event){
		$('#deletePromptModal').modal('hide');
	});
	
	$(document).on("click", '#doDelete', function(event){
		obrisiGodinu();
		$('#deletePromptModal').modal('hide');
	});
	
	
	$(document).on("click", '#search', function(event){
		searchGodine();
		$("#collapseSearch").collapse('toggle');
	});
	
	$(document).on("click", '#refresh', function(event){
		dobaviGodine();
	});
	
	$(document).on("click", '#doReset', function(event){
		var godinaSearchInput = $('#godinaSearchInput');
		godinaSearchInput.val("");
		$("#collapseSearch").collapse('toggle');
		dobaviGodine();
	});
}

function dobaviGodine() {
	var pageNo = 0; 
	var godinaPagination = $('#godina-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$.ajax({
		url : "http://localhost:8086/api/poslovnegodine/p?pageNo=" + pageNo + "&pageSize=" + pageSize
	}).then(
			function(data, status, request) {
				console.log(data);
				godinaPagination.empty();
				$("#dataTableBody").empty();
				console.log(request.getResponseHeader('total'));
				for(var j=0; j<request.getResponseHeader('total'); j++){
                    godinaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
                }
				for (i = 0; i < data.length; i++) {
					console.log(data[i].idGodine)
					newRow = 
					"<tr>" 
						+ "<td class=\"godina\">" + data[i].godina + "</td>"
						+ "<td class=\"zakljucena\">" + data[i].zakljucena + "</td>"
						+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
						+ "<td class=\"idGodine\"  style:display:none>" + data[i].idGodine + "</td>" +
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
	    dobaviGodine();
	});

	godinaPagination.on("click","a.page-link", function (event) {
	    event.preventDefault();
	    pageNo = $(this).attr("pageno");
	    dobaviGodine();
	});
}

function searchGodine(){
	var pageNo = 0; 
	var godinaPagination = $('#godina-page');
	var nmbSelect = $('#nmb-select');
	var pageSize = nmbSelect.find(":selected").text();
	$('#doSearch').on('click', function(event){
		var godinaSearchInput = $('#godinaSearchInput');
		var godina = godinaSearchInput.val();
		console.log(godina);
		$.ajax({
			url : "http://localhost:8086/api/poslovnegodine/searchByGodina?pageNo=" + pageNo + "&pageSize=" + pageSize + "&godina=" + godina
		}).then(
				function(data, status, request) {
					console.log(data);
					godinaPagination.empty();
					$("#dataTableBody").empty();
					console.log(request.getResponseHeader('total'));
					for(var j=0; j<request.getResponseHeader('total'); j++){
	                    godinaPagination.append(`<li class="page-item  ${pageNo==j? 'active':''}">` +
	                        `<${pageNo==j? 'span':'a'} class="page-link" pageNo="${j}">${j+1}</${pageNo==j? 'span':'a'}></li>`);
	                }
					for (i = 0; i < data.length; i++) {
						console.log(data[i].idGodine)
						newRow = 
						"<tr>" 
							+ "<td class=\"godina\">" + data[i].godina + "</td>"
							+ "<td class=\"zakljucena\">" + data[i].zakljucena + "</td>"
							+ "<td class=\"preduzece\">" + data[i].preduzece.nazivPreduzeca + "</td>"
							+ "<td class=\"idGodine\"  style:display:none>" + data[i].idGodine + "</td>" +
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
		    dobaviGodine();
		});

		godinaPagination.on("click","a.page-link", function (event) {
		    event.preventDefault();
		    pageNo = $(this).attr("pageno");
		    dobaviGodine();
		});
	});
}

function dobaviPreduzeca() {
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

function dobaviPreduzeca2() {
	$.ajax({
		url : "http://localhost:8086/api/preduzece/all"
	}).then(
		function(data) {
			$("#preduzeceIzmeniSelect").empty();
			$('#preduzeceIzmeniSelect').append($('<option>', {
			    value: 1,
			    text: ''
			}));
			
			$.each(data, function (i, item) {
			    $('#preduzeceIzmeniSelect').append($('<option>', { 
			        value: item.idPreduzeca,
			        text : item.nazivPreduzeca
			    }));
			});	
		}
	);
}
function dodajGodinu(){
	var godinaInput = $('#godinaInput');
	var zakljucenaSelect = $('#zakljucenaSelect');
	var preduzeceSelect = $('#preduzeceSelect');
	
	$('#doAdd').on('click', function(event){
		var godina = godinaInput.val();
		var zakljucena = zakljucenaSelect.find(":selected").text();
		var preduzece = preduzeceSelect.find(":selected").text();
		
		console.log('godina: ' + godina)
		console.log('zakljucena: ' + zakljucena);
		console.log('preduzece: ' + preduzece);
		
		var params = {
			'godina': godina,
			'zakljucena': zakljucena,
			'preduzece': preduzece
		}
		$.post("http://localhost:8086/api/poslovnegodine/dodajGodinu", params, function(data) {
			console.log('ispis...')
			
			alert('Dodata je nova poslovna godina')
			
			dobaviGodine();
			godinaInput.val("");
			zakljucenaSelect.val("");
			preduzeceSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
	});
}

function izmeniGodinu(){
	var id = getIdOfSelectedEntityGodina();
	console.log(id);
	
	var godinaIzmeniInput = $('#godinaIzmeniInput');
	var zakljucenaIzmeniSelect = $('#zakljucenaIzmeniSelect');
	var preduzeceIzmeniSelect = $('#preduzeceIzmeniSelect');
	
	
	$("#doUpdate").on("click", function(event) {
		var godina = godinaIzmeniInput.val();
		var zakljucena = zakljucenaIzmeniSelect.find(":selected").text();
		var preduzece = preduzeceIzmeniSelect.find(":selected").text();
		
		console.log('godina: ' + godina)
		console.log('zakljucena: ' + zakljucena);
		console.log('preduzece: ' + preduzece);
		
		var params = {
				'id': id,
				'godina': godina,
				'zakljucena': zakljucena,
				'preduzece': preduzece
		}
		$.post("http://localhost:8086/api/poslovnegodine/izmeniGodinu/", params, function(data) {
			console.log('ispis...')
			console.log(data);
			
			alert('Izmena poslovne godine');
			
			dobaviGodine();
			godinaIzmeniInput.val("");
			zakljucenaIzmeniSelect.val("");
			preduzeceIzmeniSelect.val("");
		});
		console.log('slanje poruke');
		event.preventDefault();
		return false;
		
		
	});
}

function obrisiGodinu(){
	var id = getIdOfSelectedEntityGodina();
	console.log(id);
	$.ajax({
    	url: "http://localhost:8086/api/poslovnegodine/obrisiGodinu/" + id,
    	type: "DELETE",
    	success: function(){
    		dobaviGodine();
        }
	});
}