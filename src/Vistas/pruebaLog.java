package Vistas;


import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;
 




public class pruebaLog {
	private final static Logger log = Logger.getLogger(pruebaLog.class);
	public static void main(String[] args) {
		PropertyConfigurator.configure("log.log4j.properties");
		 
		log.warn("un warning");
		log.error("un error");



	}

}
