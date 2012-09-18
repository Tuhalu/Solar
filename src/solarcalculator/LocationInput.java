package solarcalculator;

import java.io.*;

import javax.jdo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.appengine.api.users.*;
import com.google.appengine.api.datastore.KeyFactory.*;
import java.lang.Float;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.images.Image.Format;

public class LocationInput extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		
		PersistenceManager manager = PMF.get().getPersistenceManager();
		
		long id = Long.parseLong(request.getParameter("id"));
		float latitude = Float.parseFloat(request.getParameter("latitude"));
		float longitude = Float.parseFloat(request.getParameter("longitude"));
		float dailyUsage = Float.parseFloat(request.getParameter("longitude"));
		float dayTimeHourlyUsage = Float.parseFloat(request.getParameter("hourly"));
		float averageSunlight = Float.parseFloat(request.getParameter("sunlight"));
		
		
		System system = manager.getObjectById(System.class, id);
		
		try {
			system.setLatitude(latitude);
			system.setLongitude(longitude);
			system.setDailyUsage(dailyUsage);
			system.setDayTimeHourlyUsage(dayTimeHourlyUsage);
			system.setAverageSunlight(averageSunlight);
        } finally {
        	manager.close();
        }
		
		response.sendRedirect("/calc_panels.jsp?id=" + system.getId());
	}

}