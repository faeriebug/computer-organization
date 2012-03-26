package CPU.InstructionDecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class SingleOperandInstruction implements mInsResolver {
	private DT dt = new DT();
	private NextInstruction ni = new NextInstruction();
	private Map<String, mInsResolver> ops;
	private static final String DR_NOT = "_DR_Not", DR = "_DR";
	private mInsResolver comm = new mInsResolver() {
		@Override
		public List<mIns> getMicroOrders(int instruction, int startT) {
			return Arrays.asList(new mIns("ET" + startT + "   M->MDR->D",
					new mOrder[] { new mOrder(GS.n_MAR, MAReg.sig_EMAR),
							new mOrder(GS.n_MDR, MDReg.sig_W),
							new mOrder(GS.n_MS, MStore.sig_W) }));
		}
	};
	private mInsResolver COM_DR_Not = new mInsResolver() {
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns(
					"ET" + startT + "   !D->MDR", new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_D)]),
							new mOrder(GS.n_ALU, Alu.sig_log_comA),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver COM_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   !" + n_rg + "->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_log_comA),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver NEG_DR_Not = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns("ET" + startT
					+ "   !D+1->MDR", new mOrder[] {
					new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
							.get(GS.n_D)]),
					new mOrder(GS.n_ALU, Alu.sig_ari_AminusBC0),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver NEG_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   !" + reg + "->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_ari_AminusBC0),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver INC_DR_Not = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns("ET" + startT
					+ "   D+1->MDR", new mOrder[] {
					new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
							.get(GS.n_D)]),
					new mOrder(GS.n_ALU, Alu.sig_ari_AC0),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver INC_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   " + reg + "+1->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_log_comA),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver DEC_DR_Not = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {

			List<mIns> m1 = Arrays.asList(new mIns("ET" + startT
					+ "   D-1->MDR", new mOrder[] {
					new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
							.get(GS.n_D)]),
					new mOrder(GS.n_ALU, Alu.sig_ari_Adec),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver DEC_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   " + reg + "-1->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_ari_Adec),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver SL_DR_Not = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {

			List<mIns> m1 = Arrays.asList(new mIns(
					"ET" + startT + "   !D->MDR", new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_D)]),
							new mOrder(GS.n_ALU, Alu.sig_log_A),
							new mOrder(GS.n_SHIFTER, Shift.sig_SL),
							new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver SL_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   !" + reg + "->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_log_A),
							new mOrder(GS.n_SHIFTER, Shift.sig_SL),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver SR_DR_Not = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns(
					"ET" + startT + "   !D->MDR", new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_D)]),
							new mOrder(GS.n_ALU, Alu.sig_log_A),
							new mOrder(GS.n_SHIFTER, Shift.sig_SR),
							new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver SR_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   !" + reg + "->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_log_A),
							new mOrder(GS.n_SHIFTER, Shift.sig_SR),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};

	public SingleOperandInstruction() {
		ops = new HashMap<String, mInsResolver>(12);
		ops.put(InsConst.Operand[6] + DR_NOT, COM_DR_Not);
		ops.put(InsConst.Operand[6] + DR, COM_DR);
		ops.put(InsConst.Operand[7] + DR_NOT, NEG_DR_Not);
		ops.put(InsConst.Operand[7] + DR, NEG_DR);
		ops.put(InsConst.Operand[8] + DR_NOT, INC_DR_Not);
		ops.put(InsConst.Operand[8] + DR, INC_DR);
		ops.put(InsConst.Operand[9] + DR_NOT, DEC_DR_Not);
		ops.put(InsConst.Operand[9] + DR, DEC_DR);
		ops.put(InsConst.Operand[10] + DR_NOT, SL_DR_Not);
		ops.put(InsConst.Operand[10] + DR, SL_DR);
		ops.put(InsConst.Operand[11] + DR_NOT, SR_DR_Not);
		ops.put(InsConst.Operand[11] + DR, SR_DR);
	}

	@Override
	public List<mIns> getMicroOrders(int ins, int startT) {
		List<mIns> m1 = dt.getMicroOrders(ins, 0);// 转”取目的地址“
		// 按op，dr分支
		// 按op，dr分支
		int op = (ins & InsConst.Op_start_m) >>> InsConst.Op_start_p;// 操作码
		int addr_des = (ins & InsConst.Dt_mode_m) >>> InsConst.Dt_mode_p;// 寻目的地址方式
		String dr = InsConst.AddrMode[addr_des];
		String drFlag;
		List<mIns> m2, m4;
		ArrayList<mIns> m;
		if (dr.equals("R")) {// DR
			drFlag = DR;
			int rg = (ins & InsConst.Dt_rg_m) >>> InsConst.Dt_rg_p;// 寄存器编号
			m2 = ops.get(op + drFlag).getMicroOrders(rg, startT);
			m4 = ni.getMicroOrders(0, startT + m2.size());
			m = new ArrayList<mIns>(m1.size() + m2.size() + m4.size());
			m.addAll(m1);
			m.addAll(m2);
			m.addAll(m4);
		} else {// !DR
			List<mIns> m3 = Arrays.asList(new mIns("ET" + startT
					+ "   M->MDR->D", new mOrder[] {
					new mOrder(GS.n_MAR, MAReg.sig_EMAR),
					new mOrder(GS.n_MS, MStore.sig_R),
					new mOrder(GS.n_MDR, MDReg.sig_R),
					new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
							.get(GS.n_MDR)]),
					new mOrder(GS.n_ALU, Alu.sig_log_B),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_D, Reg.sig_CP) }));
			drFlag = DR_NOT;
			m2 = ops.get(op + drFlag).getMicroOrders(0, startT + 1 + m3.size());
			m4 = ni.getMicroOrders(0, startT + 1 + m2.size() + m3.size());
			m = new ArrayList<mIns>(m1.size() + m2.size() + m3.size()
					+ m4.size());
			m.addAll(m1);
			m.addAll(m2);
			m.addAll(m3);
			m.addAll(m4);
		}
		return m;
	}

}
