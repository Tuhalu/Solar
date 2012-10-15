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

public class InverterInput extends HttpServlet {
	
	int cost;

	float efficiency,
		efficiencyLoss;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		
		Map<String, String> errors = new HashMap<String, String>();
		HttpSession session = request.getSession(false);
		Validation validator = new Validation();
		long id = Long.parseLong((String) session.getAttribute("user"));
		
		try {
			cost = Integer.parseInt(request.getParameter("cost"));
		} catch (NullPointerException e) {
			errors.put("cost", "Cost is a required field");
		} catch (NumberFormatException e) {
			errors.put("cost", "Your input for cost usage was invalid");
		}
		
		if (!errors.containsKey("cost")) {
			if (!validator.checkIntRange(cost, 100, 100000)) {
				errors.put("cost", "Your input for cost was abnormal");
			}
		}
		
		try {
			efficiency = Float.parseFloat(request.getParameter("efficiency")) / 100f;
		} catch (NullPointerException e) {
			errors.put("efficiency", "Efficiency is a required field");
		} catch (NumberFormatException e) {
			errors.put("efficiency", "Your input for efficiency was invalid");
		}
		
		if (!errors.containsKey("efficiency")) {
			if (!validator.checkFloatRange(efficiency, 0.4f, 1)) {
				errors.put("efficiency", "Your input for efficiency was abnormal");
			}
		}
		
		try {
			efficiencyLoss = Float.parseFloat(request.getParameter("efficiencyloss")) / 100f;
		} catch (NullPointerException e) {
			errors.put("efficiencyloss", "Efficiency loss is a required field");
		} catch (NumberFormatException e) {
			errors.put("efficiencyloss", "Your input for efficiency loss was invalid");
		}
		
		if (!errors.containsKey("efficiencyloss")) {
			if (!validator.checkFloatRange(efficiencyLoss, 0, 0.1f)) {
				errors.put("efficiencyloss", "Your input for efficiency loss was abnormal");
			}
		}
		
		if (errors.isEmpty()) {
			saveSystem(id);
			response.sendRedirect("calc_results.jsp");
		} else {
			request.setAttribute("errors", errors);
			try {
				request.getRequestDispatcher("calc_inverter.jsp").forward(request, response);
			} catch (ServletException e1) {
			}
			
		}
		
	}
	
	private void saveSystem(long id) {
		
		PersistenceManager manager = PMF.get().getPersistenceManager();
		System system = manager.getObjectById(System.class, id);
		
		try {
			system.setInverterCost(cost);
			system.setInverterEfficiency(efficiency);
			system.setInverterEfficiencyLossPerYear(efficiencyLoss);
        } finally {
        	manager.close();
        }
		
	}

}