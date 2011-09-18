package org.leopold.demo.web.servlet;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leopold.demo.service.user.Validator;
import org.osgi.framework.BundleContext;
import org.osgi.service.http.HttpService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionId = 1;

	private Validator validator = null;
	private HttpService http = null;
	private BundleContext context;

	public LoginServlet(BundleContext context) {
		this.context = context;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public void unsetValidator(Validator validator) {
		if (this.validator != validator)
			return;
		this.validator = null;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setHttp() {
		this.http = http;
		try {
			http.registerServlet("/demo/login", this, null, null);
			http.registerResources("/demo/page", "page", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unsetHttpService(HttpService http) {
		this.http.unregister("/demo/login");
		this.http.unregister("/demo/page");
		this.http = null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("userpass");
		response.setContentType("text/html");
		ServletOutputStream output = response.getOutputStream();
		if (validator == null) {
			output.println("No usable validator service");
			output.flush();
			output.close();
			return;
		}

		boolean result = false;
		result = validator.validator(username, password);
		if (result) {
			output.println("Loing successfully");
		} else {
			output.println("Login fail , please check username or password");
			output.close();
			return;
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		doPost(request, response);
	}
}
