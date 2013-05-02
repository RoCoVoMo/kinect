package old.toBeDeleted;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import de.rocovomo.action.api.ActionProvider;
import de.rocovomo.jnect.adapter.api.AdapterProvider;
import de.rocovomo.jnect.adapter.api.RoCoVoMoAdapter;
import de.rocovomo.jnect.gesture.api.GestureProvider;
import de.rocovomo.jnect.gesture.api.RoCoVoMoGesture;
import de.rocovomo.jnect.kinect.ConnectorOld;
import de.rocovomo.jnect.kinect.api.IConnector;
import de.rocovomo.jnect.kinect.provider.KinectProvider;

public class KinectActivatorOld implements BundleActivator, ServiceListener {

	private static Logger logger = Logger.getLogger(KinectActivatorOld.class);

	private BundleContext context;

	private ServiceRegistration<?> serviceRegistration;

	private IConnector connector;

	private Map<ServiceReference<?>, ServiceRegistration<?>> registeredGestures = new HashMap<ServiceReference<?>, ServiceRegistration<?>>();

	private Map<ServiceReference<?>, ServiceRegistration<?>> registeredAdapters = new HashMap<ServiceReference<?>, ServiceRegistration<?>>();

	private KinectProvider provider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		this.context = bundleContext;
		logger.info("Starting " + this.context.getBundle().getSymbolicName());
		this.connector = new ConnectorOld();
		
		getActionReferences(bundleContext, null);
		getAdapterReferences(bundleContext, null);
		getGestureReferences(bundleContext, null);

		// this.connector.run();

		bundleContext.addServiceListener(this);

		this.provider = new KinectProvider();
		serviceRegistration = bundleContext.registerService(
				KinectProvider.class.getName(), this.provider,
				this.provider.getKinectProperties());
		logger.info("Registered " + this.provider.getConnector() + ":"
				+ this.provider.getKinectProperties().get("type"));
		logger.info("Started " + this.context.getBundle().getSymbolicName());
	}

	private void getActionReferences(BundleContext bundleContext, String filter)
			throws InvalidSyntaxException {
		ServiceReference<?>[] actionReferences = bundleContext
				.getAllServiceReferences(ActionProvider.class.getName(), filter);

		if (actionReferences != null) {
			for (ServiceReference<?> serviceReference : actionReferences) {
				ActionProvider action = (ActionProvider) this.context
						.getService(serviceReference);
				this.connector.connectAction(action);
			}
		}
	}

	private void getAdapterReferences(BundleContext bundleContext, String filter)
			throws InvalidSyntaxException {
		ServiceReference<?>[] adapterReferences = bundleContext
				.getAllServiceReferences(AdapterProvider.class.getName(),
						filter);

		if (adapterReferences != null) {
			for (ServiceReference<?> serviceReference : adapterReferences) {
				AdapterProvider adapter = (AdapterProvider) this.context
						.getService(serviceReference);
				this.connector.connectAdapter(adapter);
			}
		}
	}

	private void getGestureReferences(BundleContext bundleContext, String filter)
			throws InvalidSyntaxException {
		ServiceReference<?>[] gestureReferences = bundleContext
				.getAllServiceReferences(GestureProvider.class.getName(),
						filter);

		if (gestureReferences != null) {
			for (ServiceReference<?> serviceReference : gestureReferences) {
				GestureProvider gesture = (GestureProvider) this.context
						.getService(serviceReference);
				this.connector.connectGesture(gesture);
			}
		}
	}

	BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		logger.info("Stopping " + this.context.getBundle().getSymbolicName());
		bundleContext.removeServiceListener(this);
		this.connector.stop();
		logger.info("Unregistering " + this.provider.getConnector() + ":"
				+ this.provider.getKinectProperties().get("type"));
		serviceRegistration.unregister();
		logger.info("Unregistered " + this.provider.getConnector() + ":"
				+ this.provider.getKinectProperties().get("type"));
		logger.info("Stopped " + this.context.getBundle().getSymbolicName());
	}

	@Override
	public void serviceChanged(ServiceEvent serviceEvent) {
		ServiceReference<?> serviceReference = serviceEvent
				.getServiceReference();

		switch (serviceEvent.getType()) {
		case ServiceEvent.REGISTERED: {
			registerService(serviceReference);
			break;
		}
		case ServiceEvent.UNREGISTERING: {
			unregisterService(serviceReference);
			break;
		}
		default:
			// do nothing
		}
	}

	/**
	 * checks the ServiceReference for its type and calls its registration
	 * method
	 * 
	 * @param serviceReference
	 */
	private void registerService(ServiceReference<?> serviceReference) {
		Object serviceObject = context.getService(serviceReference);
		if (serviceObject instanceof ActionProvider) {
			this.connector.connectAction((ActionProvider) serviceObject);
		}
		if (serviceObject instanceof RoCoVoMoAdapter) {
			this.connector.connectAdapter((AdapterProvider) serviceObject);
		}
		if (serviceObject instanceof RoCoVoMoGesture) {
			this.connector.connectGesture((GestureProvider) serviceObject);
		}
	}

	/**
	 * checks the ServiceReference for its type and calls its de-registration
	 * method
	 * 
	 * @param serviceReference
	 */
	private void unregisterService(ServiceReference<?> serviceReference) {
		Object serviceObject = context.getService(serviceReference);

		if (serviceObject instanceof ActionProvider) {
			this.connector.disconnectAction((ActionProvider) serviceObject);
		}
		if (serviceObject instanceof RoCoVoMoAdapter) {
			this.connector.disconnectAdapter((AdapterProvider) serviceObject);
		}
		if (serviceObject instanceof RoCoVoMoGesture) {
			this.connector.disconnectGesture((GestureProvider) serviceObject);
		}
	}

}