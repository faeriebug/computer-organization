package CPU.InstructionDecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CPU.Com.Register.Alu;
import CPU.Com.Register.ConLogic;
import CPU.Com.Register.MAReg;
import CPU.Com.Register.MDReg;
import CPU.Com.Register.MStore;
import CPU.Com.Register.MulSel;
import CPU.Com.Register.Reg;
import CPU.Com.Register.Shift;
import CPU.Instruction.InsConst;
import ModelMachine.GS;

public class Move implements mInsResolver {
	private ST st = new ST();
	private DT dt = new DT();
	private NextInstruction ni = new NextInstruction();

	@Override
	public List<mIns> getMicroOrders(int ins, int startT) {
		List<mIns> m1 = st.getMicroOrders(ins, 0);// 转”取源操作数“
		List<mIns> m2 = dt.getMicroOrders(ins, 0);// 转”取目的地址“
		// 按op，dr分支
		int addr_des = (ins & InsConst.Dt_mode_m) >>> InsConst.Dt_mode_p;// 寻目的地址方式
		String REG = InsConst.AddrMode[addr_des];
		List<mIns> m3;
		if (REG.equals("R")) {// DR
			int rg = (ins & InsConst.Dt_rg_m) >>> InsConst.Dt_rg_p;// 寄存器编号
			String reg = InsConst.Reg[rg];
			m3 = Arrays.asList(new mIns("ET" + startT + "   C->" + reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_C)]),
							new mOrder(GS.n_ALU, Alu.sig_log_A),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(reg, Reg.sig_CP) }));
		} else {// !DR
			m3 = Arrays.asList(
					new mIns("ET" + startT + "   C->MDR", new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_C)]),
							new mOrder(GS.n_ALU, Alu.sig_log_A),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(GS.n_MDR, MDReg.sig_CP) }), new mIns(
							"ET" + (startT + 1) + "   MDR->M", new mOrder[] {
									new mOrder(GS.n_MAR, MAReg.sig_EMAR),
									new mOrder(GS.n_MDR, MDReg.sig_W),
									new mOrder(GS.n_MS, MStore.sig_W) }));
		}
		List<mIns> m4 = ni.getMicroOrders(0, m3.size());
		ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size()
				+ m3.size() + m4.size());
		m.addAll(m1);
		m.addAll(m2);
		m.addAll(m3);
		m.addAll(m4);
		return m;
	}

}
