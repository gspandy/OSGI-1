package osgi.leopold.configfilevalidatorbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.leopold.demo.service.user.Validator;
import org.leopold.demo.service.user.impl.ConfigFileValidatorImpl;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceReg;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		serviceReg = bundleContext.registerService(Validator.class.getName(),
				new ConfigFileValidatorImpl(), null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		if (serviceReg!=null){
			serviceReg.unregister();
		}
	}

}
