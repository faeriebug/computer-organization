package CPU.Com.Register;

import CPU.Com.anchor;


public class MAReg extends Reg {
	public MAReg() {
		super(2);
	}

	public static final int sig_EMAR=0b10;
	
	@Override
	public void signalProcess(anchor sig) {
		switch (sig.data) {
		case sig_CP:
			//¥¶¿Ì–¥»Î√¸¡Ó
			inout[0].wire[0].trans();
			break;
		case sig_EMAR:
			inout[0].wire[1].trans();
			break;
		default:
			System.out.println("Error");
		}
	}
	
}
