package basaki;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class Json2Pojo {

	public static void main(String[] args) {
		JCodeModel codeModel = new JCodeModel();

		URL source = null;
		try {
			source = new URL("file:src/main/resources/schema.json");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GenerationConfig config = new DefaultGenerationConfig() {
		@Override
		public boolean isGenerateBuilders() { // set config option by overriding method
		return true;
		}
		};

		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		try {
			mapper.generate(codeModel, "Customer", "com.basaki.generated", source);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			codeModel.build(new File("src/main/resources"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
