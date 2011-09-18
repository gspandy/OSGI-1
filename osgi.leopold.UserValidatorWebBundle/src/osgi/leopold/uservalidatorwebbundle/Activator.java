package osgi.leopold.uservalidatorwebbundle;

import javax.servlet.Servlet;

import org.leopold.demo.web.servlet.LoginServlet;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

public class Activator implements BundleActivator, ServiceListener {

	private BundleContext context;
	private Servlet servlet;
	private ServiceReference ref;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		servlet = new LoginServlet(context);
		registerServlet();
		bundleContext.addServiceListener(this,
				"(Objectclass=" + HttpService.class.getName() + ")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
	}

	@Override
	public void serviceChanged(ServiceEvent event) {
		switch (event.getType()) {
		case ServiceEvent.REGISTERED:
			registerServlet();
			break;

		case ServiceEvent.UNREGISTERING:
			unregisterServlet();
			break;
		}
	}

	private void registerServlet() {
		if (ref == null) {
			ref = context.getServiceReference(HttpService.class.getName());
		}

		if (ref != null) {
			try {
				HttpService http = (HttpService) context.getService(ref);
				http.registerServlet("/demo/login", servlet, null, null);
				http.registerResources("/demo/page", "page", null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void unregisterServlet() {
		if (ref != null) {
			try {
				HttpService http = (HttpService) context.getService(ref);
				http.unregister("/demo/login");
				http.unregister("/demo/page");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
