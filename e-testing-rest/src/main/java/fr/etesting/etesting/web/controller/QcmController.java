package fr.etesting.etesting.web.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Qcm", description="Operations pertaining to qcm in E-Testing")
public class QcmController {

	public static final String URL_TO_CONVERT_XML_TO_QCM = "/convertToQcm";

	public static final String URL_TO_CONVERT_QCM_TO_XML = "/convertToXml/{id}";
	
	public static final String URL_QCM_BY_ID = "/qcm/{id}";
	
	public static final String URL_QCM = "/qcm";
	
	public static final String URL_QUESTION_REPONSE = QcmController.URL_QCM_BY_ID+"/questionReponse";

	public static final String URL_QUESTION_REPONSE_BY_ID = QcmController.URL_QCM_BY_ID+"/questionReponse/{idQr}";

	public static final String URL_REPONSE = QcmController.URL_QUESTION_REPONSE_BY_ID+"/reponse";

	public static final String URL_REPONSE_BY_ID = QcmController.URL_QUESTION_REPONSE_BY_ID+"/reponse/{idReponse}";


	@Autowired
	private XmlConverter xmlConverter;

	@Autowired
	private IQcmService qcmServiceImpl;
	
	@ApiOperation(value = "Correct a cm")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully correct qcm")
    })
	@PostMapping(value = URL_QCM_BY_ID)
	public ResponseEntity<Qcm> correctQcm(@PathVariable(value = "id") Long idQcm, Principal principal,
			@RequestBody Qcm qcmToCorrect) {

		Qcm qcmCorrige = qcmServiceImpl.correctQcm(idQcm, qcmToCorrect, principal.getName());
		return new ResponseEntity<>(qcmCorrige, HttpStatus.OK);

	}
	
	@ApiOperation(value = "Retrieve a qcm")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve a qcm"),
            @ApiResponse(code = 404, message = "Qcm not found")
            
    })
	@GetMapping(value = URL_QCM_BY_ID)
	public ResponseEntity<Qcm> getQcm(@PathVariable(value = "id") Long idQcm){
		try {
			Qcm qcm = qcmServiceImpl.findQcmById(idQcm);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Save a qcm")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully save a qcm")
    })
	@PutMapping(value = URL_QCM_BY_ID)
	public ResponseEntity<Qcm> savedQcm(@RequestBody Qcm qcm){
		Qcm qcmUpdated = qcmServiceImpl.updatePts(qcm);
		Qcm saveQcm = qcmServiceImpl.saveQcm(qcmUpdated);
		return new ResponseEntity<>(saveQcm, HttpStatus.CREATED);
		
	}
	
	@ApiOperation(value = "Retrieve all qcm")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve all qcm")
    })
	@GetMapping(value = URL_QCM)
	public ResponseEntity<List<Qcm>> getAllQcm(){
		return new ResponseEntity<>(qcmServiceImpl.findAllQcm(), HttpStatus.OK);
	}

	@ApiOperation(value = "Convert an xml to Qcm object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully convert to Qcm")
    })
	@PostMapping(value = URL_QCM + "/xml")
	public ResponseEntity<Qcm> convertXmlToObject(@RequestParam("file") MultipartFile qcmXml)
			throws JAXBException, IOException, NoSuchFieldException, SecurityException {
		Object qcm = xmlConverter.convertFromXMLToObject(qcmXml);
		ObjectMapper xmlMapper = new XmlMapper();
		Qcm value = xmlMapper.readValue(xmlMapper.writeValueAsString(qcm), Qcm.class);
		qcmServiceImpl.saveQcm(value);
		return new ResponseEntity<>(value, HttpStatus.OK);

	}

	@ApiOperation(value = "Export Qcm to Xml")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully export to xml"),
            @ApiResponse(code = 404, message = "Qcm not found")
    })
	@GetMapping(value = URL_QCM_BY_ID + "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Qcm> exportQcmToXml(@PathVariable(value = "id") Long idQcm) {
		try {
			Qcm qcm = qcmServiceImpl.findQcmById(idQcm);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "Add questionReponse to Qcm")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully add questionReponse to Qcm"),
            @ApiResponse(code = 404, message = "Qcm not found")
    })
	@PutMapping(URL_QUESTION_REPONSE)
	public ResponseEntity<Qcm> addQuestionReponse(@PathVariable(value = "id") Long idQcm) {
		Qcm qcm;
		try {
			qcm = qcmServiceImpl.findQcmById(idQcm);
			qcm = qcmServiceImpl.addQuestionReponse(qcm);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Delete questionReponse from Qcm")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete questionReponse from Qcm"),
            @ApiResponse(code = 404, message = "Qcm not found")
    })
	@DeleteMapping(URL_QUESTION_REPONSE_BY_ID)
	public ResponseEntity<Qcm> deleteQuestionReponse(@PathVariable(value = "id") Long idQcm, @PathVariable(value = "idQr") Long idQr) {
		Qcm qcm;
		try {
			qcm = qcmServiceImpl.findQcmById(idQcm);
			qcm = qcmServiceImpl.deleteQuestionReponse(qcm, idQr);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Add reponse to Qcm")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully add reponse to Qcm"),
            @ApiResponse(code = 404, message = "Qcm not found")
    })
	@PutMapping(URL_REPONSE)
	public ResponseEntity<Qcm> addReponse(@PathVariable(value = "id") Long idQcm, @PathVariable(value = "idQr") Long idQr) {
		Qcm qcm;
		try {
			qcm = qcmServiceImpl.findQcmById(idQcm);
			qcm = qcmServiceImpl.addReponse(qcm, idQr);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Delete reponse from Qcm")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete reponse from Qcm"),
            @ApiResponse(code = 404, message = "Qcm not found")
    })
	@DeleteMapping(URL_REPONSE_BY_ID)
	public ResponseEntity<Qcm> deleteReponse(@PathVariable(value = "id") Long idQcm, @PathVariable(value = "idQr") Long idQr, @PathVariable(value = "idReponse") Long idReponse) {
		Qcm qcm;
		try {
			qcm = qcmServiceImpl.findQcmById(idQcm);
			qcm = qcmServiceImpl.deleteReponse(qcm, idQr, idReponse);
			return new ResponseEntity<>(qcm, HttpStatus.OK);
		} catch (QcmNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
