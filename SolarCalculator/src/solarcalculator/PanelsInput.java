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

public class PanelsInput extends HttpServlet {
	
	int cost,
		angle;
	
	float panelOutput,
		efficiencyLoss;
	
	boolean bracing;

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
			if (!validator.checkIntRange(cost, 500, 100000)) {
				errors.put("cost", "Your input for cost was abnormal");
			}
		}
		
		try {
			panelOutput = Float.parseFloat(request.getParameter("paneloutput"));
		} catch (NullPointerException e) {
			errors.put("paneloutput", "Panel output is a required field");
		} catch (NumberFormatException e) {
			errors.put("paneloutput", "Your input for panel output was invalid");
		}
		
		if (!errors.containsKey("paneloutput")) {
			if (!validator.checkFloatRange(panelOutput, 0.1f, 50)) {
				errors.put("paneloutput", "Your input for panel output was abnormal");
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
		
		try {
			angle = Integer.parseInt(request.getParameter("angle"));
		} catch (NullPointerException e) {
			errors.put("angle", "Angle is a required field");
		} catch (NumberFormatException e) {
			errors.put("angle", "Your input for angle was invalid");
		}
		
		if (!errors.containsKey("angle")) {
			if (!validator.checkIntRange(angle, 0, 360)) {
				errors.put("angle", "Your input for angle was abnormal");
			}
		}	
		
		if (request.getParameter("bracing") == "yes") {
			bracing = true;
		} else {
			bracing = false;
		}
		
		if (errors.isEmpty()) {
			saveSystem(id);
			response.sendRedirect("calc_inverter.jsp");
		} else {
			request.setAttribute("errors", errors);
			try {
				request.getRequestDispatcher("calc_panels.jsp").forward(request, response);
			} catch (ServletException e1) {
			}
			
		}
	}
	
	private void saveSystem(long id) {
		
		PersistenceManager manager = PMF.get().getPersistenceManager();
		System system = manager.getObjectById(System.class, id);
		
		try {
			system.setPanelCost(cost);
			system.setPanelOutput(panelOutput);
			system.setPanelEfficiencyLossPerYear(efficiencyLoss);
			system.setBraced(bracing);
			system.setPanelFacing(angle);
        } finally {
        	manager.close();
        }
		
	}

}
