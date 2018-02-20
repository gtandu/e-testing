package fr.etesting.etesting.web.controller;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.service.IQcmService;
import fr.etesting.etesting.service.implementation.XmlConverter;

@RestController
public class QcmController {
	
	public static final String URL_TO_CONVERT_XML = "/convertXml";

	@Autowired
	private XmlConverter xmlConverter;

	@Autowired
	private IQcmService qcmServiceImpl;

	@PostMapping(value = URL_TO_CONVERT_XML)
	public ResponseEntity<Object> convertXmlToObject(@RequestParam("xml") MultipartFile qcmXml)
			throws JAXBException, IOException, NoSuchFieldException, SecurityException {
		Object qcm = xmlConverter.convertFromXMLToObject(qcmXml);
		ObjectMapper xmlMapper = new XmlMapper();
		Qcm value = xmlMapper.readValue(xmlMapper.writeValueAsString(qcm), Qcm.class);
		qcmServiceImpl.saveQcm(value);
		return new ResponseEntity<>(value, HttpStatus.OK);

	}

}
