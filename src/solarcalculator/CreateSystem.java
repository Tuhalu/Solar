package solarcalculator;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.appengine.api.users.*;


import com.google.appengine.api.datastore.*;

public class CreateSystem extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        resp.sendRedirect("/calc_location.jsp");
        
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
		
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        resp.sendRedirect("/calc_location.jsp");
        
	}
	
}
