package CPU.Com.Register;

import CPU.Com.anchor;

public class MDReg extends Reg{
	public static final int sig_R=0b10;
	public static final int sig_W=0b11;
	
	public MDReg() {
		super(4);//0���������ߣ�1�����߳��ߣ�2�����������ߣ�3�������߳���
	}
	
	@Override
	public void signalProcess(anchor sig) {
		switch (sig.data) {
		case sig_CP://�������߶�ȡ����
			//����д������
			inout[0].wire[0].trans();
			break;
		case sig_R://���������߶�ȡ����
			inout[0].wire[2].trans();
			break;
		case sig_W://������д����������
			inout[0].wire[3].trans();
			break;
		default:
			System.out.println("Error");
		}
	}
	
}
