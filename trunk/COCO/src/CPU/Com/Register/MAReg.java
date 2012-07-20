package CPU.Com.Register;

import CPU.Com.Physics;
import CPU.Com.Port;
import CPU.Com.Physics.PhyEventHandler;


public class MAReg extends Reg {
	public MAReg() {
		super(3);
	}

	public static final int sig_EMAR=0b10;
	public PhyEventHandler sig_PortSetDataEventHandler = new PhyEventHandler() {
		@Override
		public void handle(Physics src) {
			if (((Port) src).equals(sig)) {
				switch (((Port) src).getData()) {
				case sig_CP:
					setData(in.getData());
					break;
				case sig_EMAR:
					out.setData(getData());
					break;
				default:
					System.out.println("Error");
				}
			}
		}
	};
}
