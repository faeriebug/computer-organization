package CPU.Com.Register;

import CPU.Com.anchor;

public class MDReg extends Reg{
	public static final int sig_R=0b10;
	public static final int sig_W=0b11;
	
	public MDReg() {
		super(4);//0内总线入线，1内总线出线，2数据总线入线，3数据总线出线
	}
	
	@Override
	public void signalProcess(anchor sig) {
		switch (sig.data) {
		case sig_CP://从内总线读取数据
			//处理写入命令
			inout[0].wire[0].trans();
			break;
		case sig_R://从数据总线读取数据
			inout[0].wire[2].trans();
			break;
		case sig_W://将数据写入数据总线
			inout[0].wire[3].trans();
			break;
		default:
			System.out.println("Error");
		}
	}
	
}
