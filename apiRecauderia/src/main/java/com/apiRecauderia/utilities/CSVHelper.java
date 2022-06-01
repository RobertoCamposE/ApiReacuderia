package com.apiRecauderia.utilities;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.apiRecauderia.dto.FruitRequest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "id", "clave", "descripcion", "precio" };

	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (TYPE.equals(file.getContentType())
	    		|| file.getContentType().equals("application/vnd.ms-excel")) {
	      return true;
	    }

	    return false;
	  }

	  public static List<FruitRequest> csvToFruits(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<FruitRequest> fruitst = new ArrayList<>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	    	  FruitRequest fruit = new FruitRequest(
	              Integer.parseInt(csvRecord.get("id")),
	              csvRecord.get("clave"),
	              csvRecord.get("nombre"),
	              Double.parseDouble(csvRecord.get("precio"))
	            );

	    	  fruitst.add(fruit);
	      }

	      return fruitst;
	    } catch (IOException e) {
	      throw new RuntimeException("Error al parsear el archivo: " + e.getMessage());
	    }
	  }

	  public static ByteArrayInputStream fruitsToCSV(List<FruitRequest> fruits) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (FruitRequest fruit : fruits) {
	        List<String> data = Arrays.asList(
	              String.valueOf(fruit.getId()),
	              fruit.getClave(),
	              fruit.getNombre(),
	              String.valueOf(fruit.getPrecio())
	            );

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("Error al importar la informacion a archivo CSV: " + e.getMessage());
	    }
	  }
	}