package CPU.Com.Register;

import CPU.Com.ExecutableRegister;
import CPU.Com.anchor;

/**
 * 算术逻辑单元
 * @author WuyaMony
 *
 */
public class Alu extends ExecutableRegister{
	public static final int sig_ari_A = 0b11110;
	public static final int sig_ari_AC0 = 0b111101;
	public static final int sig_ari_AplusB = 0b10010;
	public static final int sig_ari_AminusB = 0b01100;
	public static final int sig_ari_AminusBC0 = 0b011001;
	public static final int sig_ari_Adec = 0b00000;
	public static final int sig_log_A = 0b11111;
	public static final int sig_log_B = 0b10101;
	public static final int sig_log_comA = 0b00001;
	public static final int sig_log_AandB = 0b11101;
	public static final int sig_log_AorB = 0b10111;
	public static final int sig_log_AeorB = 0b10011;
	
	public Alu() {
		inout=new anchor[4];//0选择A，1选择B，2PSW，3输出
		for (int i = 0; i < inout.length; i++) {
			inout[i]=new anchor();
			inout[i].owner=this;
		}
	}

	@Override
	public void signalProcess(anchor sig) {
		inout[2].wire[0].trans();//读入PSW信息
		switch (sig.data) {
		case sig_ari_A:
			break;
		case sig_ari_AC0:
			break;
		case sig_ari_AplusB:
			break;
		case sig_ari_AminusB:
			break;
		case sig_ari_Adec:
			break;
		case sig_log_A:
			break;
		case sig_log_B:
			break;
		case sig_log_comA:
			break;
		case sig_log_AandB:
			break;
		case sig_log_AorB:
			break;
		case sig_log_AeorB:
			break;
		default:
			
		}
	}

}
