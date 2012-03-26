package CPU.Com.Register;

import CPU.Com.ExecutableRegister;
import CPU.Com.anchor;

public class Shift extends ExecutableRegister{
	public static final int sig_DM = 0b01;
	public static final int sig_SL = 0b10;
	public static final int sig_SR = 0b11;

	public Shift() {
		inout = new anchor[1];
		inout[0]=new anchor(2);//0入线，1出线
		inout[0].owner=this;
	}

	@Override
	public void signalProcess(anchor sig) {
		switch (sig.data) {
		case sig_DM:// 直传
			inout[0].wire[0].trans();
			inout[0].wire[1].trans();
			break;
		case sig_SL:// 左移
			inout[0].wire[0].trans();
			inout[0].data<<=1;
			inout[0].wire[1].trans();
			break;
		case sig_SR:// 右移
			inout[0].wire[0].trans();
			inout[0].data>>>=1;
			inout[0].wire[1].trans();
			break;
		default:

		}
	}

}
