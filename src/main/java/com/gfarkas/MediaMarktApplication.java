package com.gfarkas;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfarkas.dto.MediaMarktDto;
import com.gfarkas.service.MassUploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class MediaMarktApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaMarktApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(MassUploadService massUploadService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<MediaMarktDto> typeReference = new TypeReference<>() {};
			Resource resource = new ClassPathResource("json/sample_data.json");
			InputStream inputStream = resource.getInputStream();
			try {
				MediaMarktDto mediaMarktDto = mapper.readValue(inputStream, typeReference);
				massUploadService.create(mediaMarktDto);
				System.out.println("Products saved");
			} catch (IOException ioException) {
				System.out.println("Unable to save products: " + ioException.getMessage());
			}
		};
	}
}
