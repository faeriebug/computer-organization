package CPU.Com.Register;

import CPU.Com.ExecutableRegister;
import CPU.Com.anchor;

public class MStore extends ExecutableRegister{
	public static final int sig_R=0b01;
	public static final int sig_W=0b10;
	public int[] sData;
	public MStore() {
		sData=new int[100];
		inout=new anchor[2];//0��ַ���ߣ�1��������
		inout[0]=new anchor();//0����
		inout[0].owner=this;
		inout[1]=new anchor(2);//0���ߣ�1����
		inout[1].owner=this;
	}
	
	public void setData(){
		this.sData[inout[0].data]=inout[1].data;
	}
	
	public void getData(){
		inout[1].data=this.sData[inout[0].data];
	}
	
	@Override
	public void signalProcess(anchor sig) {
		switch (sig.data) {
		case sig_R://�����ݴ�����������
			inout[0].wire[0].trans();//�ӵ�ַ���߻�ȡ��ַ
			this.getData();
			inout[1].wire[1].trans();//������������
			break;
		case sig_W://�����ݴ���������д������
			inout[0].wire[0].trans();//�ӵ�ַ���߻�ȡ��ַ
			inout[1].wire[0].trans();//���������߻�ȡ����
			this.setData();
			break;
		default:
			System.out.println("Error");
		}
	}

}
