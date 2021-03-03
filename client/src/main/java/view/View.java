package view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class View {
	private final static Logger logger=LoggerFactory.getLogger(View.class.getName());
	public void showResponseToClient(String response) {
		logger.info("{}",response);
	}

}
