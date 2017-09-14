package basaki;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

import basaki.data.Customer;

public class Pojo2Schema {

	public static void main(String[] args) {
//		try {
//			System.out.println(getJsonSchema());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
		      ObjectMapper mapper = new ObjectMapper();
		      JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(
		              mapper);
		      JsonSchema schema = schemaGen
		              .generateSchema(Customer.class);
		      String sch =  mapper.writerWithDefaultPrettyPrinter()
                      .writeValueAsString(schema);
		      System.out.println(
		              sch);
		      Writer writer = null;

		      try {
		          writer = new BufferedWriter(new OutputStreamWriter(
		                new FileOutputStream("src/main/resources/schema.json"), "utf-8"));
		          writer.write(sch);
		      } catch (IOException ex) {
		        // report
		      } finally {
		         try {writer.close();} catch (Exception ex) {/*ignore*/}
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}
	
	private static String getJsonSchema() throws IOException {
		return null;
//	    org.codehaus.jackson.map.ObjectMapper mapper = new org.codehaus.jackson.map.ObjectMapper();
//	    //There are other configuration options you can set.  This is the one I needed.
//	    //mapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, true);
//
//	    org.codehaus.jackson.schema.JsonSchema schema = mapper.generateJsonSchema(CustomerList.class);
//
//	    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
	}

}
