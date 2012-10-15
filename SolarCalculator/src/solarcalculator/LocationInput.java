package solarcalculator;

import java.io.*;

import javax.jdo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.appengine.api.users.*;
import com.google.appengine.api.datastore.KeyFactory.*;
import java.lang.Float;
import java.util.HashMap;
import java.util.Map;


import com.google.appengine.api.datastore.*;
import com.google.appengine.api.images.Image.Format;

public class LocationInput extends HttpServlet {
	
	float usage,
		hourly,
		longitude,
		latitude,
		sunlight;
		

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		
		Map<String, String> errors = new HashMap<String, String>();
		HttpSession session = request.getSession(false);
		Validation validator = new Validation();
		long id = Long.parseLong((String) session.getAttribute("user"));
		
		try {
			usage = Float.parseFloat(request.getParameter("usage"));
		} catch (NullPointerException e) {
			errors.put("usage", "Daily usage is a required field");
		} catch (NumberFormatException e) {
			errors.put("usage", "Your input for daily usage was invalid");
		}
		
		if (!errors.containsKey("usage")) {
			if (!validator.checkFloatRange(usage, 1, 1000)) {
				errors.put("usage", "Your input for daily usage was abnormal");
			}
		}
		
		try {
			hourly = Float.parseFloat(request.getParameter("hourly"));
		} catch (NullPointerException e) {
			errors.put("hourly", "Daytime hourly usage is a required field");
		} catch (NumberFormatException e) {
			errors.put("hourly", "Your input for daytime hourly usagee was invalid");
		}
		
		if (!errors.containsKey("hourly")) {
			if (!validator.checkFloatRange(hourly, 1, 1000)) {
				errors.put("hourly", "Your input for daytime hourly usage was abnormal");
			}
		}
		
		try {
			sunlight = Float.parseFloat(request.getParameter("sunlight"));
		} catch (NullPointerException e) {
			errors.put("sunlight", "Hours of sunlight is a required field");
		} catch (NumberFormatException e) {
			errors.put("sunlight", "Your input for hours of sunlight was invalid");
		}
		
		if (!errors.containsKey("sunlight")) {
			if (!validator.checkFloatRange(sunlight, 1, 12)) {
				errors.put("sunlight", "Your input for hours of sunlight was abnormal");
			}
		}
		
		try {
			longitude = Float.parseFloat(request.getParameter("longitude"));
		} catch (NullPointerException e) {
			errors.put("longitude", "Longitude is a required field");
		} catch (NumberFormatException e) {
			errors.put("longitude", "Your input for longitude was invalid");
		}
		
		if (!errors.containsKey("longitude")) {
			if (!validator.checkFloatRange(longitude, 0, 200)) {
				errors.put("longitude", "Your input for longitude was abnormal");
			}
		}
		
		try {
			latitude = Float.parseFloat(request.getParameter("latitude"));
		} catch (NullPointerException e) {
			errors.put("latitude", "Latitude is a required field");
		} catch (NumberFormatException e) {
			errors.put("latitude", "Your input for latitude was invalid");
		}
		
		if (!errors.containsKey("latitude")) {
			if (!validator.checkFloatRange(latitude, 0, 200)) {
				errors.put("latitude", "Your input for latitude was abnormal");
			}
		}
		
		if (errors.isEmpty()) {
			saveSystem(id);
			response.sendRedirect("calc_panels.jsp");
		} else {
			request.setAttribute("errors", errors);
			try {
				request.getRequestDispatcher("calc_location.jsp").forward(request, response);
			} catch (ServletException e1) {
			}
			
		}
		
	}
	
	private void saveSystem(long id) {
		
		PersistenceManager manager = PMF.get().getPersistenceManager();
		System system = manager.getObjectById(System.class, id);
		
		try {
			system.setDailyUsage(usage);
			system.setDayTimeHourlyUsage(hourly);
			system.setAverageSunlight(sunlight);
			system.setLongitude(longitude);
			system.setLatitude(latitude);
        } finally {
        	manager.close();
        }
	}

}