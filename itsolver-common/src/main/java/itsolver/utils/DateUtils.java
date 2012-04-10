package itsolver.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase con metodos de utilida que son comunes a varias clases
 * @author Rene Lopez Flores
 *
 */
public class DateUtils {
	/**
	 * Metodo que parse una cadena de texto a una fecha
	 * @param dateToProgram Cadena para convertir a un objeto de tipo fecha
	 * @return Objeto de tipo fecha
	 * @throws ParseException En caso de que ocurra una exepcion
	 */
	public static Date getDate(String dateToProgram) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("es_MX", "MX"));
		return dateFormat.parse(dateToProgram);
	}
	
	public static long currentTimeMinutes(long startTimeMillis) {
		
		long currentTimeMillis = System.currentTimeMillis();		
		long difMillis = currentTimeMillis - startTimeMillis;		
		long seconds = difMillis/1000;		
		long minutes = seconds/60;	
		
		return minutes;
	}
}
