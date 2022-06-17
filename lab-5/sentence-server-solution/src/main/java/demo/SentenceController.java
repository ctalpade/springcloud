package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;



@Controller
public class SentenceController {

	//	This is referencing the RestTemplate we defined earlier:
	@Autowired  RestTemplate template;
	
	/**
	 * Display a small list of Sentences to the caller:
	 */
	@GetMapping("/sentence")
	public @ResponseBody String getSentence() {
		System.out.println("=============== Inside getSentence =================");
	  return 
		"<h3>Some Sentences</h3><br/>" +	  
		buildSentence() + "<br/><br/>" +
		buildSentence() + "<br/><br/>" +
		buildSentence() + "<br/><br/>" +
		buildSentence() + "<br/><br/>" +
		buildSentence() + "<br/><br/>"
		;
	}

	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public String buildSentence() {
		String sentence = "There was a problem assembling the sentence!";
		try{
			System.out.println("$$$$$$$$$$$$$$$$$ Will try to reach the service using eureka");
			 
			sentence =  
				String.format("%s %s %s %s %s .",
					getWord("LAB-4-SUBJECT"),
					getWord("LAB-4-VERB"),
					getWord("LAB-4-ARTICLE"),
					getWord("LAB-4-ADJECTIVE"),
					getWord("LAB-4-NOUN") );
					
		} catch ( Exception e ) {
			System.out.println(e);
			e.printStackTrace();
		}
		return sentence;
	}
	
	/**
	 * Obtain a random word for a given part of speech, where the part 
	 * of speech is indicated by the given service / client ID:
	 */
	public String getWord(String service) {
		String url = "http://" + service;
		System.out.println("================== URL =============== "+url);
		return template.getForObject(url, String.class);
	
	}

}
