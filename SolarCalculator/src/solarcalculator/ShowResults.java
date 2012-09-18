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

public class ShowResults extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		PersistenceManager manager = PMF.get().getPersistenceManager();
		
		long id = Long.parseLong(request.getParameter("id"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		float efficiency = Float.parseFloat(request.getParameter("efficiency"));
		float efficiencyLoss = Float.parseFloat(request.getParameter("efficiencyloss"));
		
		
		System system = manager.getObjectById(System.class, id);
		
		try {
			system.setInverterCost(cost);
			system.setInverterEfficiency(efficiency);
			system.setInverterEfficiencyLossPerYear(efficiencyLoss);
			SimpleResults results = new SimpleResults(system);
        } finally {
        	manager.close();
        }
		
		response.sendRedirect("/results?=" + system.getId());
	}

}