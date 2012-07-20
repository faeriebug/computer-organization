package CPU.Com;

/**
 * uniware for transmitting data
 * to use this object, src & des should be set,which is anchor type.
 * and implements the trans() event handler to fire something physically.
 * @author HuHaixiao
 *
 */
public class uniwire extends Physics{
	public static PhyEvent uniwireSetDataEvent=new PhyEvent() {
		@Override
		public String getName() {
			return "uniwireSetDataEvent";
		}
	};
	public PhyEventHandler portSetDataEventHandler=new PhyEventHandler() {
		@Override
		public void handle(Physics src) {
			setData(((Port)src).getData());
		}
	};
	private int data=0;
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
		FireEvent(uniwireSetDataEvent, uniwire.this);
	}
	
	
}
