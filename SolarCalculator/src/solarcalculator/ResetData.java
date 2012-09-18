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

public class ResetData extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		response.sendRedirect("/index.jsp");
	}

}