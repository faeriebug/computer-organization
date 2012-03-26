package CPU.Com.Register;

import java.util.Arrays;

import CPU.Com.ExecutableRegister;
import CPU.Com.anchor;

/**
 * ��·ѡ����
 * @author HuHaixiao
 *
 */
public class MulSel extends ExecutableRegister{
	public static final int[] sig_sel=new int[]{0,1,2,3,4,5,6,7};
	
	public MulSel() {
		inout=new anchor[1];
		inout[0]=new anchor(9);//0~7�����������ݣ�8��������
		inout[0].owner=this;
	}
	
	@Override
	public void signalProcess(anchor sig) {
			int s=Arrays.binarySearch(sig_sel,sig.data);
			if(s>=0){
				inout[0].wire[s].trans();
			}
	}

}
