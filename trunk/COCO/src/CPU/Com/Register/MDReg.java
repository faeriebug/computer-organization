package CPU.Com.Register;

import CPU.Com.Physics;
import CPU.Com.Port;
import CPU.Com.Physics.PhyEventHandler;

public class MDReg extends Reg{
	public static final int sig_R=0b10;
	public static final int sig_W=0b11;
	private Port in_MS;//data from Main Store;
	public MDReg() {
		super(4);//
		in_MS=ports[3];
	}
	
	public PhyEventHandler sig_PortSetDataEventHandler = new PhyEventHandler() {
		@Override
		public void handle(Physics src) {
			if (((Port) src).equals(sig)) {
				switch (((Port) src).getData()) {
				case sig_CP:
					setData(in.getData());
					break;
				case sig_R://read from Main Store
					setData(in_MS.getData());
					break;
				case sig_W:
					out.setData(getData());
					break;
				default:
					System.out.println("Error");
				}
			}
		}
	};
}
