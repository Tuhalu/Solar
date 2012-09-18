package solarcalculator;

import java.io.*;

import javax.jdo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.appengine.api.users.*;
import com.google.appengine.api.datastore.KeyFactory.*;

import com.google.appengine.api.datastore.*;

public class CreateSystem extends HttpServlet {
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		
		boolean existing;
		
		PersistenceManager manager = PMF.get().getPersistenceManager();
		String installationType = request.getParameter("type");
		
		if (installationType == "new") {
			existing = false;
		} else {
			existing = true;
		}
		
		System system = new System(existing);
			
		try {
			manager.makePersistent(system);
        } finally {
        	manager.close();
        }
		
		response.sendRedirect("/calc_location.jsp?id=" + system.getId());
	}
	
}