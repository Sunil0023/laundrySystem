package com.sunil.customer.laundrySystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sunil.customer.laundrySystem.model.CSVData;

@SpringBootApplication
@RestController
public class FiledownloadApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(FiledownloadApplication.class, args);
//	}
	
	@RequestMapping(value="/sourceDir", method=RequestMethod.GET) 
	public ResponseEntity<Object> downloadFile() throws IOException  {
		FileWriter filewriter =  null;
		try {
		CSVData csv1 = new CSVData();
		csv1.setId("1");
		csv1.setName("Sunil");
		csv1.setNumber("660961");
		
		CSVData csv2 = new CSVData();
		csv2.setId("2");
		csv2.setName("Jiteneder");
		csv2.setNumber("234519");

		CSVData csv3 = new CSVData();
		csv3.setId("3");
		csv3.setName("Brhmaji");
		csv3.setNumber("235416");

		CSVData csv4 = new CSVData();
		csv4.setId("4");
		csv4.setName("TCS");
		csv4.setNumber("0000001");

		
		List<CSVData> csvDataList = new ArrayList<>();
		csvDataList.add(csv1);
		csvDataList.add(csv2);
		csvDataList.add(csv3);
		csvDataList.add(csv4);
		
		System.out.println("On Server Application Loading File....");
		System.out.println(csvDataList.toString());
		
		StringBuilder filecontent = new StringBuilder("Id, Name, Emp_Number\n");
		for(CSVData csv:csvDataList) {
			filecontent.append(csv.getId()).append(",").append(csv.getName()).append(",").append(csv.getNumber()).append("\n");
		}
		
		String filename = "F:\\Office Projects\\AMM New CR\\sourceDir\\Finaldata.csv";
		
		//To write some content in file
		filewriter = new FileWriter(filename);
		filewriter.write(filecontent.toString());
		filewriter.flush();
		
		File file = new File(filename);
		System.out.println("FileName: "+file.getName());
		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
//		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/json")).body(resource);
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		
		System.out.println("responseEntity on Server: "+responseEntity);
		
		return responseEntity;
		} catch (Exception e ) {
			return new ResponseEntity<>("error occurred", HttpStatus.INTERNAL_SERVER_ERROR);	
		} finally {
			if(filewriter!=null)
				filewriter.close();
		}
	}
}