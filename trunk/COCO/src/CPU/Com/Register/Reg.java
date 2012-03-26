package CPU.Com.Register;

import CPU.Com.ExecutableRegister;
import CPU.Com.anchor;

public class Reg extends ExecutableRegister{
	public static final int sig_CP = 0b1;
	
	public Reg(int num) {
		inout=new anchor[1];
		inout[0]=new anchor(num);//0入线，1出线
		inout[0].owner=this;
	}
	

	@Override
	public void signalProcess(anchor sig) {
		switch (sig.data) {
		case sig_CP:
			//处理写入命令
			inout[0].wire[0].trans();
			break;
		default:
			System.out.println("Error");
		}
	}
}
