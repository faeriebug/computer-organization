package CPU.Com.Register;

import CPU.Com.Physics;
import CPU.Com.Port;
import CPU.Com.RegisterType;
import CPU.Com.Physics.PhyEventHandler;

public class MStore extends RegisterType{
	public static final int sig_R=0b01;
	public static final int sig_W=0b10;
	public int[] sData;
	private Port sig,in_addr,in_data,out_data;
	public MStore() {
		sData=new int[100];
		ports=new Port[4];
		sig=ports[0];
		in_addr=ports[1];
		in_data=ports[2];
		out_data=ports[3];
	}
	
	public PhyEventHandler sig_PortSetDataEventHandler = new PhyEventHandler() {
		@Override
		public void handle(Physics src) {
			if (((Port) src).equals(sig)) {
				switch (((Port) src).getData()) {
				case sig_R:
					out_data.setData(getData());
					break;
				case sig_W:
					setData();
					break;
				default:
					System.out.println("Error");
				}
			}
		}
	};
	
	public void setData(){
		this.sData[in_addr.getData()]=in_data.getData();
	}
	
	public int getData(){
		return this.sData[in_addr.getData()];
	}
}
