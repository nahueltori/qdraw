package interpreter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QDrawController{
	
	private QDraw qdraw;

	public QDrawController(){
		qdraw = new QDraw();
	}
	
    @RequestMapping(method = RequestMethod.POST, value = "/interpret")
	public String interpret(@RequestBody String programa) {
		return qdraw.tokenize(programa);
	}



}