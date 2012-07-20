package CPU.Com.Register;

import CPU.Com.ExecutableRegister;
import CPU.Com.Port;

public class Shift extends ExecutableRegister{
	public static final int sig_DM = 0b01;
	public static final int sig_SL = 0b10;
	public static final int sig_SR = 0b11;

	public Shift() {
		inout = new Port[1];
		inout[0]=new Port(2);//0���ߣ�1����
		inout[0].owner=this;
	}

	@Override
	public void signalProcess(Port sig) {
		switch (sig.data) {
		case sig_DM:// ֱ��
			inout[0].wire[0].trans();
			inout[0].wire[1].trans();
			break;
		case sig_SL:// ����
			inout[0].wire[0].trans();
			inout[0].data<<=1;
			inout[0].wire[1].trans();
			break;
		case sig_SR:// ����
			inout[0].wire[0].trans();
			inout[0].data>>>=1;
			inout[0].wire[1].trans();
			break;
		default:

		}
	}

}
