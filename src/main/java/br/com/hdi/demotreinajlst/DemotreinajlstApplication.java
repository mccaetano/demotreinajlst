package br.com.hdi.demotreinajlst;

import br.com.hdi.jslt.utils.CustomFunctionsUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Function;
import com.schibsted.spt.data.jslt.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class DemotreinajlstApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemotreinajlstApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			log.info("Iniciando Aplicação");
			JsonNode jsonInput = new ObjectMapper().readTree("{ \"mensagem\": \"texto\" }");
			log.info("Json de Input: " + jsonInput.toPrettyString());
			String transform = " { \"id\": random(), *: .}";
			log.info("template: " + transform);


			List<Function> functions = CustomFunctionsUtil.loadCustomFunctions();
			Expression jslt = Parser.compileString(transform, functions);
			JsonNode output = jslt.apply(jsonInput);
			log.info("Json Transformado: " + output.toPrettyString());
		} catch(JsonProcessingException | InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
			log.error(ex.getMessage(), ex);
		}
	}
}
