package CPU.Com.Register;


import CPU.Com.Physics;
import CPU.Com.Port;
import CPU.Com.RegisterType;
import CPU.Com.Physics.PhyEventHandler;

/**
 *
 * @author HuHaixiao
 *
 */
public class MulSel extends RegisterType{
	public static final int sig_0=0b000;
	public static final int sig_1=0b001;
	public static final int sig_2=0b010;
	public static final int sig_3=0b011;
	public static final int sig_4=0b100;
	public static final int sig_5=0b101;
	public static final int sig_6=0b110;
	public static final int sig_7=0b111;
	
	private Port sig,in0,in1,in2,in3,in4,in5,in6,in7,out;
	
	public PhyEventHandler sig_PortSetDataEventHandler = new PhyEventHandler() {
		@Override
		public void handle(Physics src) {
			if (((Port) src).equals(sig)) {
				switch (((Port) src).getData()) {
				case sig_0:
					setData(in0.getData());
					break;
				case sig_1:
					setData(in1.getData());
					break;
				case sig_2:
					setData(in2.getData());
					break;
				case sig_3:
					setData(in3.getData());
					break;
				case sig_4:
					setData(in4.getData());
					break;
				case sig_5:
					setData(in5.getData());
					break;
				case sig_6:
					setData(in6.getData());
					break;
				case sig_7:
					setData(in7.getData());
					break;
				default:
					System.out.println("Error");
				}
			}
		}
	};
	
	public MulSel() {
		ports=new Port[10];
		sig=ports[0];
		in0=ports[1];
		in1=ports[2];
		in2=ports[3];
		in3=ports[4];
		in4=ports[5];
		in5=ports[6];
		in6=ports[7];
		in7=ports[8];
		out=ports[9];
	}
}
