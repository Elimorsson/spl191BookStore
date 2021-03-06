package bgu.spl.mics.application.services;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.AskForCarEvent;
import bgu.spl.mics.application.messages.ReleaseVehicle;
import bgu.spl.mics.application.messages.TerminateBroadcast;
import bgu.spl.mics.application.passiveObjects.DeliveryVehicle;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ResourceService is in charge of the store resources - the delivery vehicles.
 * Holds a reference to the {@link ResourcesHolder} singleton of the store.
 * This class may not hold references for objects which it is not responsible for:
 * {@link MoneyRegister}, {@link Inventory}.
 * 
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class ResourceService extends MicroService{
	private ResourcesHolder resourcesHolder;
	private AtomicInteger canStartTime;

	public ResourceService(int id, AtomicInteger canStartTime) {
		super("ResourcesService "+id);
		resourcesHolder= ResourcesHolder.getInstance();
		this.canStartTime=canStartTime;
	}

	@Override
	protected void initialize() {

		subscribeEvent(AskForCarEvent.class, event ->{
			Future<DeliveryVehicle> future = resourcesHolder.acquireVehicle();
			complete(event,future);
		});

		subscribeEvent(ReleaseVehicle.class, event ->{
			resourcesHolder.releaseVehicle(event.getDeliveryVehicle());
			complete(event,true);
		});

		subscribeBroadcast(TerminateBroadcast.class, b ->{
			resourcesHolder.releaseVehicle(null);
			this.terminate();
		});
		canStartTime.incrementAndGet();
		
	}

}
