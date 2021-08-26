package com.poslovnaInformatika.podsistemProdaje.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.poslovnaInformatika.podsistemProdaje.model.Faktura;
import com.poslovnaInformatika.podsistemProdaje.repository.FakturaRepository;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import projekat.model.Narudzbenica;
import projekat.model.Otpremnica;
import projekat.model.RobaUsluga;
import projekat.model.StavkaOtpremnice;



@CrossOrigin
@RestController
@RequestMapping(value = "api/fakture")
@ControllerAdvice
public class FakturaController {
	@Autowired
	private FakturaRepository fakturaRepo;

	@GetMapping(path = "/all")
	public List<Faktura> getAll() {
		return fakturaRepo.findAll();
	}
	
	@GetMapping(path = "/p")
    public ResponseEntity<List<Faktura>> getAllFaktura(
                        @RequestParam Pageable page) 
    {
       
		Page<Faktura> fakture = fakturaRepo.findAll(page);
        HttpHeaders headers = new HttpHeaders();
        headers.set("total", String.valueOf(fakture.getTotalPages()));
        return ResponseEntity.ok().headers(headers).body(fakture.getContent());
    }
	
	
	@GetMapping(value = "/searchByBrojFakture")
	public ResponseEntity<List<Faktura>> searchByBrojFakture(@RequestParam("broj") String brojFaktureString,
			@RequestParam Pageable page) {
		
		int brojFakture = Integer.parseInt(brojFaktureString);
		
		Page<Faktura> fakture = fakturaRepo.findAllByBrojFakture(brojFakture, page);
		HttpHeaders headers = new HttpHeaders();
	    headers.set("total", String.valueOf(fakture.getTotalPages()));
	    return ResponseEntity.ok().headers(headers).body(fakture.getContent());
	}
	
	@GetMapping(value = "/searchByStatusFakture")
	public ResponseEntity<List<Faktura>> searchByStatusFakture(@RequestParam("status") String statusFakture,
			@RequestParam Pageable page){
		
		Page<Faktura> fakture = fakturaRepo.findAllByStatusFakture(statusFakture, page);
		HttpHeaders headers = new HttpHeaders();
	    headers.set("total", String.valueOf(fakture.getTotalPages()));
	    return ResponseEntity.ok().headers(headers).body(fakture.getContent());
	}
	
	@DeleteMapping(path = "/obrisiFakturu/{id}")
	public ResponseEntity<Void> obrisiFakturu(@PathVariable("id") long id) {
		
		Faktura faktura = fakturaRepo.findOne(id);
		
		if(faktura == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		fakturaRepo.deleteById(faktura.getIdFakture());
		
		System.out.println("Obrisana je faktura.");
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

public String jasperReportFaktura(String reportFormat) throws FileNotFoundException, JRException{
	    List<Faktura> fakture = new ArrayList<Faktura>();
	    fakture = fakturaRepo.findAll();
		String path = "C:\\Users\\Win10\\Downloads\\jasperfiles";
		File file = ResourceUtils.getFile("C:\\Users\\Win10\\git\\poslovnaInformatika\\podsistemProdaje\\src\\main\\resources\\faktureDnevnik.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fakture);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("Faktura", "Poslovna informatika");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\faktureDnevnik.pdf");
		}
		System.out.println("Report on: " + path);
		
		return path;
		
	}


//jasper report fakture sa stavkama, na osnovu izabrane fakture
	@SuppressWarnings("rawtypes")
	@PostMapping(path = "/jasperReportFaktura", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ResponseEntity jasperReport(@RequestParam("id") long id) {
		
		Faktura faktura = fakturaRepo.findOne(id);
	    if(faktura==null){
	       return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
	    
	    List<Faktura> fakture = new ArrayList<Faktura>();
	    List<StavkaFakture> stavkeFaktureOrig = new ArrayList<StavkaFakture>();
  	Connection conn = null;
  	PreparedStatement pstmt = null;
	    
	    try {

			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/pi", "root", "lozinka");
	    	
	    	String query = "SELECT f.id_fakture, f.broj_fakture, f.datum_fakture, f.datum_valute, f.status_fakture, f.ukupna_osnovica, f.ukupan_pdv, f.ukupan_iznos, s.id_stavke, s.kolicina, s.rabat, s.jedinicna_cena, s.iznos, s.pdv_stopa, s.osnovica_za_pdv, s.iznos_pdv, s.ukupan_iznos from faktura f, stavka_fakture s WHERE f.id_fakture = s.id_fakture AND f.id_fakture = " + id;
	    	
	    	pstmt = conn.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Faktura faktura2 = new Faktura();
				faktura2.setIdFakture(rset.getLong("id_fakture"));
				faktura2.setBrojFakture(rset.getInt("broj_fakture"));
				faktura2.setDatumFakture(rset.getDate("datum_fakture"));
				faktura2.setDatumValute(rset.getDate("datum_valute"));
				faktura2.setStatusFakture(rset.getString("status_fakture"));
				faktura2.setUkupnaOsnovica(rset.getDouble("ukupna_osnovica"));
				faktura2.setUkupanPDV(rset.getDouble("ukupan_pdv"));
				faktura2.setUkupanIznos(rset.getDouble("ukupan_iznos"));
				fakture.add(faktura2);
				
				StavkaFakture stavkaFakture2 = new StavkaFakture();
				stavkaFakture2.setIdStavke(rset.getLong("id_stavke"));
				stavkaFakture2.setKolicina(rset.getDouble("kolicina"));
				stavkaFakture2.setRabat(rset.getDouble("rabat"));
				stavkaFakture2.setJedinicnaCena(rset.getDouble("jedinicna_cena"));
				stavkaFakture2.setIznos(rset.getDouble("iznos"));
				stavkaFakture2.setPdvStopa(rset.getDouble("pdv_stopa"));
				stavkaFakture2.setOsnovicaZaPDV(rset.getDouble("osnovica_za_pdv"));
				stavkaFakture2.setIznosPDV(rset.getDouble("iznos_pdv"));
				stavkaFakture2.setUkupanIznos(rset.getDouble("ukupan_iznos"));
				stavkeFaktureOrig.add(stavkaFakture2);
				
			}
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    
	    List<StavkaFakture> stavkeFaktureOrig2 = stavkeFaktureOrig;
	    JRBeanCollectionDataSource stavkaFakture = new JRBeanCollectionDataSource(stavkeFaktureOrig2);
	    System.out.print(stavkeFaktureOrig2);
	    System.out.println(stavkaFakture.getData());
	    Map<String, Object> params  = new HashMap<>();
	    params.put("faktura", faktura);
	    params.put("stavkaFakture", stavkaFakture);
	    	
	    for (StavkaFakture stavkaFakture1 : stavkeFaktureOrig2) {
			System.out.println("id stavke fakture: " + stavkaFakture1.getIdStavke());
		}
	    	
	    try {	    		
	    	InputStream is = null;
	    	is = new FileInputStream(new File("C:\\Users\\Win10\\git\\poslovnaInformatika\\podsistemProdaje\\src\\main\\resources\\faktura.jrxml"));
	    	JasperDesign jasperDesign = JRXmlLoader.load(is);
	    	JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
	    	String path = "C:\\Users\\Win10\\Downloads\\jasperfiles\\fakturaSaStavkama.pdf";
	    	JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
	    	ByteArrayInputStream bis = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
	    	JasperExportManager.exportReportToPdfFile(jp, path);
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.add("Content-Disposition", "inline; filename=fakturaSaStavkama.pdf");
	    	return ResponseEntity
	    			.ok()
	    			.headers(headers)
	    			.contentType(MediaType.APPLICATION_PDF)
	    			.body(new InputStreamResource(bis));
	    }catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	//report svih faktura, dnevnik kif
		@PostMapping(value = "/dnevnik",  consumes = "application/x-www-form-urlencoded;charset=UTF-8")
		public String dnevnik(@RequestParam("format") String reportFormat) throws FileNotFoundException, JRException {

	        List<Faktura> fakture = new ArrayList<Faktura>();
	        fakture = fakturaRepo.findAll();
	        
			String path = "C:\\Users\\Win10\\Downloads\\jasperfiles";
			File file = ResourceUtils.getFile("C:\\Users\\Win10\\git\\poslovnaInformatika\\podsistemProdaje\\src\\main\\resources\\faktureDnevnik.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(fakture);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("Faktura", "Poslovna informatika");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			if(reportFormat.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\faktureDnevnik.pdf");
			}
			System.out.println("Report on: " + path);
			
	    	return path;
		}
		
		@PostMapping(path = "/stornirajFakturu")
		public ResponseEntity<Void> stornirajFakturu(@RequestParam("broj_fakture") String brojFakture) {
			
			int brojFaktureInt = Integer.parseInt(brojFakture);
			
			Faktura faktura1 = fakturaRepo.findByBrojFakture(brojFaktureInt);
			Faktura faktura = fakturaRepo.findOne(faktura1.getIdFakture());
			
			if(faktura != null) {
				faktura.setBrojFakture(faktura.getBrojFakture());
				faktura.setDatumFakture(faktura.getDatumFakture());
				faktura.setDatumValute(faktura.getDatumValute());
				faktura.setUkupnaOsnovica(faktura.getUkupnaOsnovica());
				faktura.setUkupanPDV(faktura.getUkupanPDV());
				faktura.setUkupanIznos(faktura.getUkupanIznos());
				faktura.setStatusFakture("S");
				faktura.setPoslovnaGodina(faktura.getPoslovnaGodina());
				faktura.setPoslovniPartner(faktura.getPoslovniPartner());
				faktura.setPreduzece(faktura.getPreduzece());
				fakturaRepo.save(faktura);
				
				System.out.println("Faktura stornirana");

				return new ResponseEntity<Void>(HttpStatus.OK);
				
			} else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			
			
		}
		//kreiraj otpremnicu na osnovu selektovane fakture
		@PostMapping(value = "/kreirajOtpremnicu", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
		public ResponseEntity<Void> kreirajOtpremnicu(@RequestParam("id") long id, 
				@RequestParam("datum_isporuke") String datumIsporukeString,
				@RequestParam("prevoznik") String prevoznik,
				@RequestParam("narudzbenica") String brojNarudzbeniceString,
				@RequestParam("redni_broj_proizvoda") String redniBrojProizvodaS,
				@RequestParam("jedinica_mere") String jedinicaMere,
				@RequestParam("napomena") String napomena,
				@RequestParam("roba") String roba) throws ParseException{
			
			Faktura faktura = fakturaRepo.findOne(id);
			
			RobaUsluga roba1 = robaUslugaRepo.findByNazivRobeUsluge(roba);
			RobaUsluga robaUsluga = robaUslugaRepo.findOne(roba1.getIdRobeUsluge());
			
			int brojNarudzbenice = Integer.parseInt(brojNarudzbeniceString);
			
			Narudzbenica narudzbenica1 = narudzbenicaRepo.findByBrojNarudzbenice(brojNarudzbenice);
			Narudzbenica narudzbenica = narudzbenicaRepo.findOne(narudzbenica1.getId());
			
			if(faktura == null) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			
			try {
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/pi", "root", "lozinka");
				
				String query = "SELECT kolicina, iznos FROM faktura, stavka_fakture WHERE faktura.id_fakture = stavka_fakture.id_fakture AND faktura.id_fakture = " + id;
				
				pstmt = conn.prepareStatement(query);
				ResultSet rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					int redniBrojProizvoda = Integer.parseInt(redniBrojProizvodaS);
					
					StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
					stavkaOtpremnice.setRedniBrojProizvoda(redniBrojProizvoda);
					stavkaOtpremnice.setJedinicaMere(jedinicaMere);
					
					String datum = datumIsporukeString;
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date date = formatter.parse(datum);
				    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
					
					Otpremnica otpremnica = new Otpremnica();
					otpremnica.setBrojOtpremnice(faktura.getBrojFakture());
					otpremnica.setKupac(faktura.getPoslovniPartner().getNazivPoslovnogPartnera());
					otpremnica.setAdresaIsporuke(faktura.getPoslovniPartner().getAdresa());
					otpremnica.setDatumIsporuke(sqlDate);
					otpremnica.setPrevoznik(prevoznik);
					otpremnica.setPotpisVozaca(false);
					otpremnica.setPrimioRobu(false);
					otpremnica.setFaktura(faktura);
					otpremnica.setNarudzbenica(narudzbenica);
					otpremnicaRepo.save(otpremnica);
					
				
					stavkaOtpremnice.setOtpremnica(otpremnica);
					stavkaOtpremnice.setCena(rset.getDouble("iznos"));
					stavkaOtpremnice.setKolicina(rset.getDouble("kolicina"));
					stavkaOtpremnice.setNapomena(napomena);
					stavkaOtpremnice.setRobaUsluga(robaUsluga);
					stavkaOtpremniceRepo.save(stavkaOtpremnice);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Kreirana otpremnica na osnovu fakture.");
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		@PostMapping(path = "/exportFakture")
		public ResponseEntity<Void> generateFakturaBrojFakture(@RequestParam("broj_fakture") String brojFaktureString){
			
			try {
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/pi", "root", "lozinka");
				
				String query = "SELECT * FROM faktura f, stavka_fakture s, poslovna_godina, poslovni_partner, preduzece, roba_usluga, naseljeno_mesto WHERE f.broj_fakture = ?"
						+ "AND f.id_godine = poslovna_godina.id_godine "
						+ "AND f.id_poslovnog_partnera = poslovni_partner.id_poslovnog_partnera "
						+ "AND f.id_preduzeca = preduzece.id_preduzeca "
						+ "AND f.id_fakture = s.id_fakture "
						+ "AND s.id_robe = roba_usluga.id_robe_usluge "
						+ "AND preduzece.id_mesta = naseljeno_mesto.id_mesta";
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, brojFaktureString);
				ResultSet rset = pstmt.executeQuery();
				
				String path = "C:\\Users\\Milica\\Downloads\\xml_export_fakture";
				
				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				
				Document doc = documentBuilder.newDocument();
				
				Element el = doc.createElement("fakturaExport");
				doc.appendChild(el);
				
				int iRows = 0;
				int stavkaRow = 0;
				int GodinaRow = 0;
				int PartnerRow = 0;
				int PreduzeceRow = 0;
				while((rset != null) && (rset.next())) {
					
					++iRows;
					Element sRows = doc.createElement("Faktura");
					
					Attr id = doc.createAttribute("id");
					id.setValue(String.valueOf(iRows));
					sRows.setAttributeNode(id);
					
					Element idFakture = doc.createElement("idFakture");
					idFakture.appendChild(doc.createTextNode(rset.getString("id_fakture")));
					sRows.appendChild(idFakture);
					
					Element brojFakture = doc.createElement("brojFakture");
					brojFakture.appendChild(doc.createTextNode(rset.getString("broj_fakture")));
					sRows.appendChild(brojFakture);
					
					Element datumFakture = doc.createElement("datumFakture");
					datumFakture.appendChild(doc.createTextNode(rset.getString("datum_fakture")));
					sRows.appendChild(datumFakture);
					
					Element datumValute = doc.createElement("datumValute");
					datumValute.appendChild(doc.createTextNode(rset.getString("datum_valute")));
					sRows.appendChild(datumValute);
					
					Element ukupnaOsnovica = doc.createElement("ukupnaOsnovica");
					ukupnaOsnovica.appendChild(doc.createTextNode(rset.getString("ukupna_osnovica")));
					sRows.appendChild(ukupnaOsnovica);
					
					Element ukupanPDV = doc.createElement("ukupanPDV");
					ukupanPDV.appendChild(doc.createTextNode(rset.getString("ukupan_pdv")));
					sRows.appendChild(ukupanPDV);
					
					Element ukupanIznos = doc.createElement("ukupan_iznos");
					ukupanIznos.appendChild(doc.createTextNode(rset.getString("ukupan_iznos")));
					sRows.appendChild(ukupanIznos);
					
					Element statusFakture = doc.createElement("statusFakture");
					statusFakture.appendChild(doc.createTextNode(rset.getString("status_fakture")));
					sRows.appendChild(statusFakture);
					
					++stavkaRow;
					Element stavkaElement = doc.createElement("stavkaFakture");
					
					Attr idStavke = doc.createAttribute("idStavke");
					idStavke.setValue(String.valueOf(stavkaRow));
					stavkaElement.setAttributeNode(idStavke);	
				
					Element idStavkeFakture = doc.createElement("idStavkeFakture");
					idStavkeFakture.appendChild(doc.createTextNode(rset.getString("id_stavke")));
					stavkaElement.appendChild(idStavkeFakture);
					
					Element kolicina = doc.createElement("kolicina");
					kolicina.appendChild(doc.createTextNode(rset.getString("kolicina")));
					stavkaElement.appendChild(kolicina);
					
					Element rabat = doc.createElement("rabat");
					rabat.appendChild(doc.createTextNode(rset.getString("rabat")));
					stavkaElement.appendChild(rabat);

					Element jedinicnaCena = doc.createElement("jedinicnaCena");
					jedinicnaCena.appendChild(doc.createTextNode(rset.getString("jedinicna_cena")));
					stavkaElement.appendChild(jedinicnaCena);
					
					Element pdvStopa = doc.createElement("pdvStopa");
					pdvStopa.appendChild(doc.createTextNode(rset.getString("pdv_stopa")));
					stavkaElement.appendChild(pdvStopa);
				
					Element osnovicaPDV = doc.createElement("osnovicaPDV");
					osnovicaPDV.appendChild(doc.createTextNode(rset.getString("osnovica_za_pdv")));
					stavkaElement.appendChild(osnovicaPDV);

					Element iznosPDV = doc.createElement("iznosPDV");
					iznosPDV.appendChild(doc.createTextNode(rset.getString("iznos_pdv")));
					stavkaElement.appendChild(iznosPDV);
					
					Element ukupanIznosStavke = doc.createElement("ukupanIznos");
					ukupanIznosStavke.appendChild(doc.createTextNode(rset.getString("ukupan_iznos")));
					stavkaElement.appendChild(ukupanIznosStavke);
					
					Element idRobeUsluge = doc.createElement("idRobeUsluge");
					idRobeUsluge.appendChild(doc.createTextNode(rset.getString("id_robe")));
					stavkaElement.appendChild(idRobeUsluge);
					
					Element nazivRobeUsluge = doc.createElement("nazivRobeUsluge");
					nazivRobeUsluge.appendChild(doc.createTextNode(rset.getString("naziv_robe_usluge")));
					stavkaElement.appendChild(nazivRobeUsluge);
					
					sRows.appendChild(stavkaElement);
					
					
					++GodinaRow;
					Element godinaElement = doc.createElement("godinaFakture");
					
					Attr idGodine = doc.createAttribute("idGodine");
					idGodine.setValue(String.valueOf(GodinaRow));
					godinaElement.setAttributeNode(idGodine);
					
					Element idPoslovnaGodina = doc.createElement("IdPoslovnaGodina");
					idPoslovnaGodina.appendChild(doc.createTextNode(rset.getString("id_godine")));
					godinaElement.appendChild(idPoslovnaGodina);
					
					Element godina = doc.createElement("godina");
					godina.appendChild(doc.createTextNode(rset.getString("godina")));
					godinaElement.appendChild(godina);
					
					Element zakljucena = doc.createElement("zakljucena");
					zakljucena.appendChild(doc.createTextNode(rset.getString("zakljucena")));
					godinaElement.appendChild(zakljucena);
					
					sRows.appendChild(godinaElement);
					
					++PartnerRow;
					Element partnerElement = doc.createElement("partner");
					
					Attr idPartnera = doc.createAttribute("idPartnera");
					idPartnera.setValue(String.valueOf(PartnerRow));
					partnerElement.setAttributeNode(idPartnera);
					
					Element poslovniPartner = doc.createElement("poslovniPartner");
					poslovniPartner.appendChild(doc.createTextNode(rset.getString("id_poslovnog_partnera")));
					partnerElement.appendChild(poslovniPartner);
					
					Element nazivPoslovnogPartnera = doc.createElement("nazivPoslovnogPartnera");
					nazivPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("naziv_poslovnog_partnera")));
					partnerElement.appendChild(nazivPoslovnogPartnera);
					
					Element adresaPoslovnogPartnera = doc.createElement("adresaPoslovnogPartnera");
					adresaPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("adresa")));
					partnerElement.appendChild(adresaPoslovnogPartnera);
					
					Element telefonPoslovnogPartnera = doc.createElement("telefonPoslovnogPartnera");
					telefonPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("telefon")));
					partnerElement.appendChild(telefonPoslovnogPartnera);
					
					Element faxPoslovnogPartnera = doc.createElement("faxPoslovnogPartnera");
					faxPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("fax")));
					partnerElement.appendChild(faxPoslovnogPartnera);
					
					Element emailPoslovnogPartnera = doc.createElement("emailPoslovnogPartnera");
					emailPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("email")));
					partnerElement.appendChild(emailPoslovnogPartnera);
					
					Element vrstaPoslovnogPartnera = doc.createElement("vrstaPoslovnogPartnera");
					vrstaPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("vrsta_partnera")));
					partnerElement.appendChild(vrstaPoslovnogPartnera);
					
					Element idMestaPoslovnogPartnera = doc.createElement("idMestaPoslovnogPartnera");
					idMestaPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("id_mesta")));
					partnerElement.appendChild(idMestaPoslovnogPartnera);
					
					Element nazivMestaPoslovnogPartnera = doc.createElement("nazivMestaPoslovnogPartnera");
					nazivMestaPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("naziv_mesta")));
					partnerElement.appendChild(nazivMestaPoslovnogPartnera);
					
					Element pttBrojMestaPoslovnogPartnera = doc.createElement("pttBrojMestaPoslovnogPartnera");
					pttBrojMestaPoslovnogPartnera.appendChild(doc.createTextNode(rset.getString("ptt_broj")));
					partnerElement.appendChild(pttBrojMestaPoslovnogPartnera);
					
					sRows.appendChild(partnerElement);
					
					++PreduzeceRow;
					Element preduzeceElement = doc.createElement("preduzece");
					
					Attr idPreduzeca = doc.createAttribute("idPreduzeca");
					idPreduzeca.setValue(String.valueOf(PreduzeceRow));
					preduzeceElement.setAttributeNode(idPreduzeca);
					
					Element preduzece = doc.createElement("preduzece");
					preduzece.appendChild(doc.createTextNode(rset.getString("id_preduzeca")));
					preduzeceElement.appendChild(preduzece);
					
					Element nazivPreduzeca = doc.createElement("nazivPreduzeca");
					nazivPreduzeca.appendChild(doc.createTextNode(rset.getString("naziv_preduzeca")));
					preduzeceElement.appendChild(nazivPreduzeca);
					
					Element adresaPreduzeca = doc.createElement("adresaPreduzeca");
					adresaPreduzeca.appendChild(doc.createTextNode(rset.getString("adresa_preduzeca")));
					preduzeceElement.appendChild(adresaPreduzeca);
					
					Element brojTelefonaPreduzeca = doc.createElement("brojTelefonaPreduzeca");
					brojTelefonaPreduzeca.appendChild(doc.createTextNode(rset.getString("broj_telefona")));
					preduzeceElement.appendChild(brojTelefonaPreduzeca);
					
					Element faxPreduzeca = doc.createElement("faxPreduzeca");
					faxPreduzeca.appendChild(doc.createTextNode(rset.getString("fax_preduzeca")));
					preduzeceElement.appendChild(faxPreduzeca);
					
					Element idMestaPreduzeca = doc.createElement("idMestaPreduzeca");
					idMestaPreduzeca.appendChild(doc.createTextNode(rset.getString("id_mesta")));
					preduzeceElement.appendChild(idMestaPreduzeca);
					
					Element nazivMestaPreduzeca = doc.createElement("nazivMestaPreduzeca");
					nazivMestaPreduzeca.appendChild(doc.createTextNode(rset.getString("naziv_mesta")));
					preduzeceElement.appendChild(nazivMestaPreduzeca);
					
					Element pttBrojMestaPreduzeca = doc.createElement("pttBrojMestaPreduzeca");
					pttBrojMestaPreduzeca.appendChild(doc.createTextNode(rset.getString("ptt_broj")));
					preduzeceElement.appendChild(pttBrojMestaPreduzeca);
					
					sRows.appendChild(preduzeceElement);
					
					
					el.appendChild(sRows);
				}
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(doc);
				StreamResult streamResult = new StreamResult(new File(path, "fakturaExport.xml").getPath());
				
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						 
				transformer.transform(domSource, streamResult);
						 
				System.out.println("XML file created!");
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<Void>(HttpStatus.OK);
			
		}
		
		@ExceptionHandler(value = ConstraintViolationException.class)
		public ResponseEntity<Void> handle() {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	

	

}
