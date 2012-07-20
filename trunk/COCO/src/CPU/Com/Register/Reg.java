package CPU.Com.Register;

import CPU.Com.Physics.PhyEventHandler;
import CPU.Com.Physics;
import CPU.Com.Port;
import CPU.Com.RegisterType;

public class Reg extends RegisterType {
	public static final int sig_CP = 0b1;
	protected Port sig, in, out;
	public PhyEventHandler sig_PortSetDataEventHandler = new PhyEventHandler() {
		@Override
		public void handle(Physics src) {
			if (((Port) src).equals(sig)) {
				switch (((Port) src).getData()) {
				case sig_CP:
					setData(in.getData());
					break;
				default:
					System.out.println("Error");
				}
			}
		}
	};

	public Reg(int num) {
		if (num < 3) {
			System.err.println("parameter error!");
		}
		ports = new Port[num];
		sig = ports[0];
		in = ports[1];
		out = ports[2];
	}
}
