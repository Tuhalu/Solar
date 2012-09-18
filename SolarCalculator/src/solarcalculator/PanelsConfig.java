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

public class PanelsConfig extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		PersistenceManager manager = PMF.get().getPersistenceManager();
		
		long id = Long.parseLong(request.getParameter("id"));
		int facing = Integer.parseInt(request.getParameter("facing"));
		int angle = Integer.parseInt(request.getParameter("angle"));
		
		boolean bracing;
		if (request.getParameter("bracing") == "yes") {
			bracing = true;
		} else {
			bracing = false;
		}
		
		
		System system = manager.getObjectById(System.class, id);
		
		try {
			system.setPanelFacing(facing);
			system.setRoofAngle(angle);
			system.setBraced(bracing);
        } finally {
        	manager.close();
        }
		
		response.sendRedirect("/calc_inverter.jsp?id=" + system.getId());
	}

}