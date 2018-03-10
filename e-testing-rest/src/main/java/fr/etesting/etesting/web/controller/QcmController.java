package fr.etesting.etesting.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import fr.etesting.etesting.exception.QcmNotFoundException;
import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.service.IQcmService;
import fr.etesting.etesting.service.implementation.XmlConverter;

@RestController
public class QcmController {

	public static final String URL_TO_CONVERT_XML_TO_QCM = "/convertToQcm";

	public static final String URL_TO_CONVERT_QCM_TO_XML = "/convertToXml/{id}";
	
	public static final String URL_QCM_BY_ID = "/qcm/{id}";
	
	public static final String URL_QCM = "/qcm";

	@Autowired
	private XmlConverter xmlConverter;

	@Autowired
	private IQcmService qcmServiceImpl;
	
	@PostMapping(value = URL_QCM_BY_ID)
	public ResponseEntity<Qcm> correctQcm(@PathVariable(value = "id") Long idQcm, Principal principal,
			@RequestBody Qcm qcmToCorrect) {

		Qcm qcmCorrige = qcmServiceImpl.correctQcm(idQcm, qcmToCorrect, principal.getName());
		return new ResponseEntity<>(qcmCorrige, HttpStatus.OK);

	}
	
	@GetMapping(value = URL_QCM_BY_ID)
	public ResponseEntity<Qcm> getQcm(@PathVariable(value = "id") Long idQcm){
		try {
			Qcm qcm = qcmServiceImpl.findQcmById(idQcm);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping(value = URL_QCM_BY_ID)
	public ResponseEntity<Qcm> savedQcm(@RequestBody Qcm qcm){
		Qcm qcmUpdated = qcmServiceImpl.updatePts(qcm);
		Qcm saveQcm = qcmServiceImpl.saveQcm(qcmUpdated);
		return new ResponseEntity<>(saveQcm, HttpStatus.CREATED);
		
	}
	
	@PatchMapping(URL_QCM_BY_ID)
	public ResponseEntity<Qcm> resetQcm(@PathVariable(value = "id") Long idQcm){
		try {
			Qcm qcm = qcmServiceImpl.findQcmById(idQcm);
			qcm = qcmServiceImpl.resetQcm(qcm);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = URL_QCM)
	public ResponseEntity<List<Qcm>> getAllQcm(){
		return new ResponseEntity<>(qcmServiceImpl.findAllQcm(), HttpStatus.OK);
	}

	@PostMapping(value = URL_QCM + "/xml")
	public ResponseEntity<Qcm> convertXmlToObject(@RequestParam("file") MultipartFile qcmXml)
			throws JAXBException, IOException, NoSuchFieldException, SecurityException {
		Object qcm = xmlConverter.convertFromXMLToObject(qcmXml);
		ObjectMapper xmlMapper = new XmlMapper();
		Qcm value = xmlMapper.readValue(xmlMapper.writeValueAsString(qcm), Qcm.class);
		qcmServiceImpl.saveQcm(value);
		return new ResponseEntity<>(value, HttpStatus.OK);

	}

	@GetMapping(value = URL_QCM_BY_ID + "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Qcm> convertQcmToXml(@PathVariable(value = "id") Long idQcm) {
		try {
			Qcm qcm = qcmServiceImpl.findQcmById(idQcm);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
