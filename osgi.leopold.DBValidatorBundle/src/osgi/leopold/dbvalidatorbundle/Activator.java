package osgi.leopold.dbvalidatorbundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.leopold.demo.service.user.Validator;
import org.leopold.demo.service.user.impl.DBValidatorImpl;

public class Activator implements BundleActivator {

	private ServiceRegistration serviceReg=null;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		serviceReg = bundleContext.registerService(Validator.class.getName(),
				new DBValidatorImpl(), null);
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
