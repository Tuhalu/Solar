package com.thepackage.solarcalc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.CookieSpecBase;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HttpFactory {
	
	private static Cookie sessionCookie;
	private static String sessionID;
	private static Boolean allowRedirects;
	private static HttpResponse response;
	private static CookieStore cookieStore;
	
	public synchronized static void HttpFactory() {
		allowRedirects = true;
		cookieStore = new BasicCookieStore();
	}
	
	private InputStream getInputStreamFromUrl(String url, List<NameValuePair> values) {
		InputStream content = null;
		try {
          	HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost("http://solar-power-calculator.appspot.com/"+url);
			httpclient.getParams().setParameter("http.protocol.handle-redirects", allowRedirects);
			values.add(new BasicNameValuePair("user", sessionID));
			httpPost.setEntity(new UrlEncodedFormEntity(values));
			
			CookieSpecBase cookieSpecBase = new BrowserCompatSpec();
			HttpContext httpContext = new BasicHttpContext();
			httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
			
			if (url == "calc_results.jsp") {
				httpPost.setHeader("Host","solar-power-calculator.appspot.com");
				httpPost.setHeader("Connection","keep-alive");
				httpPost.setHeader("Cache-Control","max-age=0");
				httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				httpPost.setHeader("Referer", "http://solar-power-calculator.appspot.com/inverterinput");
				httpPost.setHeader("Accept-Encoding", "gzip,deflate,sdch");
				httpPost.setHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n");
			}
			
			if (sessionCookie != null) {
				List<Cookie> cookies = new ArrayList<Cookie>();
				cookies.add(new BasicClientCookie("JSESSIONID", sessionID));
				List<Header> cookieHeader = cookieSpecBase.formatCookies(cookies);
				httpPost.setHeader((Header) cookieHeader.get(0));
			}
			
			response = httpclient.execute(httpPost, httpContext);
			
			Header[] allHeaders = response.getAllHeaders();
			for (Header header : allHeaders) {
				if (header.getName().equalsIgnoreCase("Set-Cookie")) {
					sessionCookie = new BasicClientCookie(header.getName(), header.getValue());					
				}
			}
			content = response.getEntity().getContent();
		    return content;
	    } catch (Exception e) {
	      	e.printStackTrace();
	    }
		return null;
	}
	
	public String getStringFromUrl(String url, List<NameValuePair> values) throws Exception {
		InputStream content = getInputStreamFromUrl(url, values);
		BufferedReader rd = new BufferedReader(new InputStreamReader(content), 4096);
		String line;
		StringBuilder sb =  new StringBuilder();
		while ((line = rd.readLine()) != null) {
				sb.append(line);
		}
		rd.close();
		return sb.toString();
	}
	
	public String getSessionID() {
		String[] fields = sessionCookie.getValue().split(";\\s*");
		String[] values = fields[0].split("=\\s*");
		if (sessionID == null) {
			sessionID = values[1];
		}
		
		return sessionID;
	}
	
	public String getResponseCode() {
		if (response != null) {
			int status = response.getStatusLine().getStatusCode();
			response = null;
			return Integer.toString(status);			
		} else {			
			return "(Connection)";
		}
		
	}
	
	public String getStringResponse(String url) throws Exception {
        BufferedReader in = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://solar-power-calculator.appspot.com/"+url);
            request.setHeader("Host","solar-power-calculator.appspot.com");
            request.setHeader("Connection","keep-alive");
            request.setHeader("Cache-Control","max-age=0");
            request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            request.setHeader("Referer", "http://solar-power-calculator.appspot.com/inverterinput");
            request.setHeader("Accept-Encoding", "gzip,deflate,sdch");
            request.setHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n");
            
            HttpContext httpContext = new BasicHttpContext();
            httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
            
            getSessionID();
            
            List<NameValuePair> values = new ArrayList<NameValuePair>();
            values.add(new BasicNameValuePair("user", sessionID));
            
            CookieSpecBase cookieSpecBase = new BrowserCompatSpec();
			List<Cookie> cookies = new ArrayList<Cookie>();
			cookies.add(new BasicClientCookie("JSESSIONID", sessionID));
			cookies.add(new BasicClientCookie("user", sessionID));
			List<Header> cookieHeader = cookieSpecBase.formatCookies(cookies);
			request.setHeader((Header) cookieHeader.get(0));
			
            response = client.execute(request, httpContext);
            
            in = new BufferedReader
            (new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            return sb.toString();
            } finally {
            if (in != null) {
                try {
                    in.close();
                    } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	public void allowRedirects(Boolean bool) {
		allowRedirects = bool;
	}
}