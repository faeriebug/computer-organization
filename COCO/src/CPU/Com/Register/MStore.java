package CPU.Com.Register;

import CPU.Com.ExecutableRegister;
import CPU.Com.anchor;

public class MStore extends ExecutableRegister{
	public static final int sig_R=0b01;
	public static final int sig_W=0b10;
	public int[] sData;
	public MStore() {
		sData=new int[100];
		inout=new anchor[2];//0地址总线；1数据总线
		inout[0]=new anchor();//0入线
		inout[0].owner=this;
		inout[1]=new anchor(2);//0入线，1出线
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
		case sig_R://将数据传入数据总线
			inout[0].wire[0].trans();//从地址总线获取地址
			this.getData();
			inout[1].wire[1].trans();//送入数据总线
			break;
		case sig_W://将数据从数据总线写入主存
			inout[0].wire[0].trans();//从地址总线获取地址
			inout[1].wire[0].trans();//从数据总线获取数据
			this.setData();
			break;
		default:
			System.out.println("Error");
		}
	}

}
