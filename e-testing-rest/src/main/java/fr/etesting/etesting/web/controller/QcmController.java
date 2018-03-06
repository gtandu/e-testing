package fr.etesting.etesting.web.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@Autowired
	private XmlConverter xmlConverter;

	@Autowired
	private IQcmService qcmServiceImpl;
	
	@GetMapping(value = URL_QCM_BY_ID)
	public ResponseEntity<Qcm> getQcm(@PathVariable(value = "id") Long idQcm){
		try {
			Qcm qcm = qcmServiceImpl.findQcmById(idQcm);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = URL_QCM_BY_ID)
	public ResponseEntity<Qcm> savetQcm(Qcm qcm){
		Qcm saveQcm = qcmServiceImpl.saveQcm(qcm);
		return new ResponseEntity<>(saveQcm, HttpStatus.OK);
		
	}

	@PostMapping(value = URL_TO_CONVERT_XML_TO_QCM)
	public ResponseEntity<Qcm> convertXmlToObject(@RequestParam("file") MultipartFile qcmXml)
			throws JAXBException, IOException, NoSuchFieldException, SecurityException {
		Object qcm = xmlConverter.convertFromXMLToObject(qcmXml);
		ObjectMapper xmlMapper = new XmlMapper();
		Qcm value = xmlMapper.readValue(xmlMapper.writeValueAsString(qcm), Qcm.class);
		qcmServiceImpl.saveQcm(value);
		return new ResponseEntity<>(value, HttpStatus.OK);

	}

	@GetMapping(value = URL_TO_CONVERT_QCM_TO_XML, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Qcm> convertQcmToXml(@PathVariable(value = "id") Long idQcm) {
		try {
			Qcm qcm = qcmServiceImpl.findQcmById(idQcm);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
