package CPU.Com;
/**
 * signal input or output port. bitsize is free. you should maintain the size
 * youself.
 * 
 * @author WuyaMony
 * 
 */
public class Port extends Physics{
	public static PhyEvent portSetDataEvent = new PhyEvent() {
		@Override
		public String getName() {
			return "portSetDataEvent";
		}
	};
	public  PhyEventHandler uniwireSetDataEventHandler=new PhyEventHandler() {
		@Override
		public void handle(Physics src) {
			setData(((uniwire)src).getData());
		}
	};
	/** signal for transmitting */
	private int data = 0;
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
		FireEvent(portSetDataEvent, this);
	}
}
