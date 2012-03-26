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

public class DoubleOperandInstruction implements mInsResolver {
	private ST st = new ST();
	private DT dt = new DT();
	private NextInstruction ni = new NextInstruction();
	private Map<String, mInsResolver> ops;
	private static final String DR_NOT = "_DR_Not", DR = "_DR";
	private mInsResolver comm = new mInsResolver() {
		@Override
		public List<mIns> getMicroOrders(int instruction, int startT) {
			return Arrays.asList(new mIns("ET" + startT + "   MDR->M",
					new mOrder[] { new mOrder(GS.n_MAR, MAReg.sig_EMAR),
							new mOrder(GS.n_MDR, MDReg.sig_W),
							new mOrder(GS.n_MS, MStore.sig_W) }));
		}
	};
	private mInsResolver ADD_DR_Not = new mInsResolver() {
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns("ET" + startT
					+ "   C+D->MDR", new mOrder[] {
					new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
							.get(GS.n_C)]),
					new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
							.get(GS.n_D)]),
					new mOrder(GS.n_ALU, Alu.sig_ari_AplusB),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver ADD_DR = new mInsResolver() {
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   C+" + n_rg + "->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_C)]),
							new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_ari_AplusB),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver SUB_DR_Not = new mInsResolver() {
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns("ET" + startT
					+ "   C-D->MDR", new mOrder[] {
					new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
							.get(GS.n_C)]),
					new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
							.get(GS.n_D)]),
					new mOrder(GS.n_ALU, Alu.sig_ari_AminusB),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver SUB_DR = new mInsResolver() {
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   C-" + n_rg + "->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_C)]),
							new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_ari_AminusB),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver AND_DR_Not = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns("ET" + startT
					+ "   C^D->MDR", new mOrder[] {
					new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
							.get(GS.n_C)]),
					new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
							.get(GS.n_D)]),
					new mOrder(GS.n_ALU, Alu.sig_log_AandB),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver AND_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   C^" + n_rg + "->"
					+ reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_C)]),
							new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_log_AandB),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver OR_DR_Not = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns("ET" + startT
					+ "   C V D->MDR", new mOrder[] {
					new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
							.get(GS.n_C)]),
					new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
							.get(GS.n_D)]),
					new mOrder(GS.n_ALU, Alu.sig_log_AorB),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT + 1);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver OR_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   C V " + n_rg
					+ "->" + reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_C)]),
							new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_log_AorB),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};
	private mInsResolver EOR_DR_Not = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			List<mIns> m1 = Arrays.asList(new mIns("ET" + startT
					+ "   C EOR D->MDR", new mOrder[] {
					new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
							.get(GS.n_C)]),
					new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
							.get(GS.n_D)]),
					new mOrder(GS.n_ALU, Alu.sig_log_AeorB),
					new mOrder(GS.n_SHIFTER, Shift.sig_DM),
					new mOrder(GS.n_MDR, MDReg.sig_CP) }));
			List<mIns> m2 = comm.getMicroOrders(reg, startT);
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}
	};
	private mInsResolver EOR_DR = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("ET" + startT + "   C EOR " + n_rg
					+ "->" + reg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_C)]),
							new mOrder(GS.n_B, MulSel.sig_sel[ConLogic.B_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_log_AeorB),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
		}
	};

	public DoubleOperandInstruction() {
		ops = new HashMap<String, mInsResolver>(10);
		ops.put(InsConst.Operand[1] + DR_NOT, ADD_DR_Not);
		ops.put(InsConst.Operand[1] + DR, ADD_DR);
		ops.put(InsConst.Operand[2] + DR_NOT, SUB_DR_Not);
		ops.put(InsConst.Operand[2] + DR, SUB_DR);
		ops.put(InsConst.Operand[3] + DR_NOT, AND_DR_Not);
		ops.put(InsConst.Operand[3] + DR, AND_DR);
		ops.put(InsConst.Operand[4] + DR_NOT, OR_DR_Not);
		ops.put(InsConst.Operand[4] + DR, OR_DR);
		ops.put(InsConst.Operand[5] + DR_NOT, EOR_DR_Not);
		ops.put(InsConst.Operand[5] + DR, EOR_DR);
	}

	@Override
	public List<mIns> getMicroOrders(int ins, int startT) {
		List<mIns> m1 = st.getMicroOrders(ins, 0);// 转”取源操作数“
		List<mIns> m2 = dt.getMicroOrders(ins, 0);// 转”取目的地址“
		// 按op，dr分支
		int op = (ins & InsConst.Op_start_m) >>> InsConst.Op_start_p;// 操作码
		int addr_des = (ins & InsConst.Dt_mode_m) >>> InsConst.Dt_mode_p;// 寻目的地址方式
		String REG = InsConst.AddrMode[addr_des];
		ArrayList<mIns> m;
		String drFlag;
		List<mIns> m4;
		if (!REG.equals("R")) {// !DR
			List<mIns> m3 = Arrays
					.asList(new mIns("ET" + startT + "   M->MDR->D",
							new mOrder[] {
									new mOrder(GS.n_MAR, MAReg.sig_EMAR),
									new mOrder(GS.n_MS, MStore.sig_R),
									new mOrder(GS.n_MDR, MDReg.sig_R),
									new mOrder(GS.n_B,
											MulSel.sig_sel[ConLogic.B_con
													.get(GS.n_MDR)]),
									new mOrder(GS.n_ALU, Alu.sig_log_B),
									new mOrder(GS.n_SHIFTER, Shift.sig_DM),
									new mOrder(GS.n_D, Reg.sig_CP) }));
			m = new ArrayList<mIns>(m1.size() + m2.size() + m3.size());
			m.addAll(m1);
			m.addAll(m2);
			m.addAll(m3);
			drFlag = DR_NOT;
			m.addAll(m4 = ops.get(op + drFlag).getMicroOrders(ins, startT + 1));
			m.addAll(ni.getMicroOrders(ins, startT + 1 + m4.size()));
		} else {
			m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			drFlag = DR;
			int rg = (ins & InsConst.Dt_rg_m) >>> InsConst.Dt_rg_p;// 寄存器编号
			m.addAll(m4 = ops.get(op + drFlag).getMicroOrders(rg, startT));
			m.addAll(ni.getMicroOrders(0, startT + m4.size()));
		}
		return m;
	}

}
