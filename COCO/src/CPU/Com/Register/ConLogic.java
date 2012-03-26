package CPU.Com.Register;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import CPU.Com.ExecutableRegister;
import CPU.Com.anchor;
import CPU.InstructionDecode.FecthInstruction;
import CPU.InstructionDecode.NextInstruction;
import CPU.InstructionDecode.mIns;
import CPU.InstructionDecode.mInsResolverSelector;
import CPU.InstructionDecode.mOrder;
import ModelMachine.GS;

/**
 * 控制逻辑
 * @author WuyaMony
 *
 */
public class ConLogic extends ExecutableRegister{
	private mInsResolverSelector mirs=new mInsResolverSelector();
	private FecthInstruction fetch=new FecthInstruction();
	private NextInstruction next=new NextInstruction();
	public static final int sig_Start=0b1;
	public static final Map<String,Integer> CL_con=new HashMap<>();
	public static final Map<String,Integer> A_con=new HashMap<>();
	public static final Map<String,Integer> B_con=new HashMap<>();
	public ConLogic() {
		inout=new anchor[1];
		inout[0]=new anchor(18);//0入线，1~17出线
		inout[0].owner=this;
		CL_con.put(GS.n_R0, 1);
		CL_con.put(GS.n_R1, 2);
		CL_con.put(GS.n_R2, 3);
		CL_con.put(GS.n_R3, 4);
		CL_con.put(GS.n_C, 5);
		CL_con.put(GS.n_D, 6);
		CL_con.put(GS.n_MAR, 7);
		CL_con.put(GS.n_MDR, 8);
		CL_con.put(GS.n_IR, 9);
		CL_con.put(GS.n_PC, 10);
		CL_con.put(GS.n_SP, 11);
		CL_con.put(GS.n_PSW, 12);
		CL_con.put(GS.n_A, 13);
		CL_con.put(GS.n_B, 14);
		CL_con.put(GS.n_ALU, 15);
		CL_con.put(GS.n_SHIFTER, 16);
		CL_con.put(GS.n_MS, 17);
		A_con.put(GS.n_R0, 0);
		A_con.put(GS.n_R1, 1);
		A_con.put(GS.n_R2, 2);
		A_con.put(GS.n_R3, 3);
		A_con.put(GS.n_C, 4);
		A_con.put(GS.n_D, 5);
		A_con.put(GS.n_PC, 6);
		A_con.put(GS.n_SP, 7);
		B_con.put(GS.n_R0, 0);
		B_con.put(GS.n_R1, 1);
		B_con.put(GS.n_R2, 2);
		B_con.put(GS.n_R3, 3);
		B_con.put(GS.n_C, 4);
		B_con.put(GS.n_D, 5);
		B_con.put(GS.n_PSW, 6);
		B_con.put(GS.n_MDR, 7);
		
	}

	private void runsig(List<mIns> mins){
		for (mIns min:mins){
			for(mOrder mo:min.mOrders){
				System.out.println("morder: < "+mo.exreg+", "+mo.sig+" >");
				inout[0].data=mo.sig;
				inout[0].wire[CL_con.get(mo.exreg)].trans();
				
			}
		}
	}
	
	@Override
	public void signalProcess(anchor sig) {
		switch (sig.data) {
		case sig_Start://开始运行
			//取指周期
			while(true){
				//fetch
				List<mIns> mins=fetch.getMicroOrders(0, 0);
				runsig(mins);
				//run
				inout[0].wire[0].trans();//从IR获取指令
				mins=mirs.Decode(inout[0].data, 0);
				runsig(mins);
				//next
				mins=next.getMicroOrders(0, 0);
				runsig(mins);
			}
		}
	}

}
