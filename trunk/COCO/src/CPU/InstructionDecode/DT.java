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

/**
 * 取目的地址
 * 
 * @author HuHaixiao
 * 
 */
public class DT implements mInsResolver {
	private mInsResolver[] addrs;
	private mInsResolver addr_R = new mInsResolver() {// R型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			return Arrays.asList(new mIns("", new mOrder[] {// 寄存器寻址，不做任何动作
					}));
		}
	};

	private mInsResolver addr_pRp = new mInsResolver() {// (R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			return Arrays.asList(new mIns("DT" + startT + "   " + n_rg
					+ "->MAR",
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_log_A),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(GS.n_MAR, MAReg.sig_CP) }));
		}
	};

	private mInsResolver addr_mpRp = new mInsResolver() {// -(R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			List<mIns> m1 = Arrays.asList(new mIns("DT" + startT + "   " + reg
					+ "-1->" + n_rg,
					new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(n_rg)]),
							new mOrder(GS.n_ALU, Alu.sig_ari_Adec),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(n_rg, Reg.sig_CP) }));
			List<mIns> m2 = (addr_pRp.getMicroOrders(reg, startT + 1));
			ArrayList<mIns> m = new ArrayList<mIns>(m1.size() + m2.size());
			m.addAll(m1);
			m.addAll(m2);
			return m;
		}

	};
	private mInsResolver addr_IpRp = new mInsResolver() {// (R)+型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			List<mIns> m = Arrays.asList(
					new mIns("DT" + startT + "   " + reg + "->MAR",
							new mOrder[] {
									new mOrder(GS.n_A,
											MulSel.sig_sel[ConLogic.A_con
													.get(n_rg)]),
									new mOrder(GS.n_ALU, Alu.sig_log_A),
									new mOrder(GS.n_SHIFTER, Shift.sig_DM),
									new mOrder(GS.n_MAR, MAReg.sig_CP) }),
					new mIns("DT" + (startT + 1) + "   " + reg + "+1->" + reg,
							new mOrder[] {
									new mOrder(GS.n_A,
											MulSel.sig_sel[ConLogic.A_con
													.get(n_rg)]),
									new mOrder(GS.n_ALU, Alu.sig_ari_AC0),
									new mOrder(GS.n_SHIFTER, Shift.sig_DM),
									new mOrder(n_rg, Reg.sig_CP) }));
			return m;
		}
	};
	private mInsResolver addr_apRp = new mInsResolver() {// @(R)+型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			List<mIns> m = Arrays.asList(
					new mIns("DT" + startT + "   " + n_rg + "->MAR",
							new mOrder[] {
									new mOrder(GS.n_A,
											MulSel.sig_sel[ConLogic.A_con
													.get(n_rg)]),
									new mOrder(GS.n_ALU, Alu.sig_log_A),
									new mOrder(GS.n_SHIFTER, Shift.sig_DM),
									new mOrder(GS.n_MAR, MAReg.sig_CP) }),
					new mIns("DT" + (startT + 1) + "   " + reg + "+1->" + reg,
							new mOrder[] {
									new mOrder(GS.n_A,
											MulSel.sig_sel[ConLogic.A_con
													.get(n_rg)]),
									new mOrder(GS.n_ALU, Alu.sig_ari_AC0),
									new mOrder(GS.n_SHIFTER, Shift.sig_DM),
									new mOrder(n_rg, Reg.sig_CP) }),
					new mIns("DT" + (startT + 2) + "   M->MDR->MAR",
							new mOrder[] {
									new mOrder(GS.n_MAR, MAReg.sig_EMAR),
									new mOrder(GS.n_MS, MStore.sig_R),
									new mOrder(GS.n_MDR, MDReg.sig_R),
									new mOrder(GS.n_B,
											MulSel.sig_sel[ConLogic.B_con
													.get(GS.n_MDR)]),
									new mOrder(GS.n_ALU, Alu.sig_log_B),
									new mOrder(GS.n_SHIFTER, Shift.sig_DM),
									new mOrder(GS.n_MAR, MAReg.sig_CP) }));
			return m;
		}
	};
	private mInsResolver addr_XpRp = new mInsResolver() {// X(R)型
		@Override
		public List<mIns> getMicroOrders(int reg, int startT) {
			String n_rg = InsConst.Reg[reg];
			List<mIns> m = Arrays.asList(
					new mIns("DT" + (startT) + "   PC->MAR", new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_PC)]),
							new mOrder(GS.n_ALU, Alu.sig_ari_A),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(GS.n_MAR, MAReg.sig_CP) }),
					new mIns("DT" + (startT + 1) + "   PC+1->PC", new mOrder[] {
							new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
									.get(GS.n_PC)]),
							new mOrder(GS.n_ALU, Alu.sig_ari_AC0),
							new mOrder(GS.n_SHIFTER, Shift.sig_DM),
							new mOrder(GS.n_PC, MAReg.sig_CP) }),
					new mIns("DT" + (startT + 2) + "   M->MDR->D",
							new mOrder[] {
									new mOrder(GS.n_MAR, MAReg.sig_EMAR),
									new mOrder(GS.n_MS, MStore.sig_R),
									new mOrder(GS.n_MDR, MDReg.sig_R),
									new mOrder(GS.n_B,
											MulSel.sig_sel[ConLogic.B_con
													.get(GS.n_MDR)]),
									new mOrder(GS.n_ALU, Alu.sig_log_B),
									new mOrder(GS.n_SHIFTER, Shift.sig_DM),
									new mOrder(GS.n_D, MAReg.sig_CP) }),
					new mIns("DT" + (startT + 3) + "   D+" + reg + "->MAR",
							new mOrder[] {
									new mOrder(GS.n_A,
											MulSel.sig_sel[ConLogic.A_con
													.get(GS.n_D)]),
									new mOrder(GS.n_B,
											MulSel.sig_sel[ConLogic.B_con
													.get(n_rg)]),
									new mOrder(GS.n_ALU, Alu.sig_ari_AplusB),
									new mOrder(GS.n_SHIFTER, Shift.sig_DM),
									new mOrder(GS.n_MAR, MAReg.sig_CP) }));
			return m;
		}
	};

	public DT() {
		addrs = new mInsResolver[] { addr_R, addr_pRp, addr_mpRp, addr_IpRp,
				addr_apRp, addr_XpRp };
	}

	@Override
	public List<mIns> getMicroOrders(int ins, int startT) {// 参数确定取址方式
		int rg = (ins & InsConst.Dt_rg_m) >>> InsConst.Dt_rg_p;// 寄存器编号
		int addr = (ins & InsConst.Dt_mode_m) >>> InsConst.Dt_mode_p;// 寻址方式
		return addrs[addr].getMicroOrders(rg, startT);
	}

}
