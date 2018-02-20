package fr.etesting.etesting.service.implementation;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class XmlConverter {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public void convertFromObjectToXML(Object object, String filepath) throws IOException, JAXBException {
		try (FileOutputStream os = new FileOutputStream(filepath)) {
			marshaller.marshal(object, new StreamResult(os));
		} catch (Exception e) {
			throw e;
		}

	}

	public Object convertFromXMLToObject(MultipartFile xmlfile) throws IOException, JAXBException {
		try{
			return unmarshaller.unmarshal(new StreamSource(xmlfile.getInputStream()));
		} catch (Exception e) {
			throw e;
		}
	}
}