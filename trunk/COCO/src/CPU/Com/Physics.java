package CPU.Com;

import java.util.List;
import java.util.Map;

public class Physics {
	public static interface PhyEvent{
		public String getName();
	}
	public static interface PhyEventHandler{
		/**
		 * source that fires this event.
		 * @param src
		 */
		public void handle(Physics src);
	}
	/**Map<EventType,Map<source,List<listener>>>*/
	private static Map<PhyEvent, Map<Physics,List<PhyEventHandler>>> eventList;
	
	protected void FireEvent(PhyEvent pe,Physics source){
		Map<Physics,List<PhyEventHandler>> re=eventList.get(pe.getName());
		for (PhyEventHandler des : re.get(source)) {
			des.handle(source);
		}
	}
}
