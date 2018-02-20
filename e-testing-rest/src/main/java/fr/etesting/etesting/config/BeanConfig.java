package fr.etesting.etesting.config;

import javax.annotation.Resource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.etesting.etesting.model.Qcm;
import fr.etesting.etesting.service.implementation.XmlConverter;

@SuppressWarnings("deprecation")
@Configuration
public class BeanConfig {

	@Resource
	ConfigurableApplicationContext appContext;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	XmlConverter xmlConverter() {
		XmlConverter xmlConverter = new XmlConverter();
		xmlConverter.setMarshaller(castorMarshaller());
		xmlConverter.setUnmarshaller(castorMarshaller());
		return xmlConverter;
	}

	@Bean
	public CastorMarshaller castorMarshaller() {
		CastorMarshaller castorMarshaller = new CastorMarshaller();
		castorMarshaller.setMappingLocation(appContext.getResource("classpath:mapping.xml"));
		castorMarshaller.supports(Qcm.class);
		return castorMarshaller;
	}

}
