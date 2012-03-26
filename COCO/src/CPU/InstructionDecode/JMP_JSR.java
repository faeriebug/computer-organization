package CPU.InstructionDecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CPU.Com.Register.Alu;
import CPU.Com.Register.ConLogic;
import CPU.Com.Register.MulSel;
import CPU.Com.Register.Reg;
import CPU.Com.Register.Shift;
import CPU.Instruction.InsConst;
import ModelMachine.GS;

public class JMP_JSR implements mInsResolver {
	private ST stt = new ST();
	private NextInstruction ni = new NextInstruction();
	private PopDown pd = new PopDown();

	@Override
	public List<mIns> getMicroOrders(int ins, int startT) {
		// String
		// jmpST=instruction.substring(Instruction.Jmp_ST_start,Instruction.Jmp_ST_len);
		// int ijmpST=Integer.valueOf(jmpST, 2);
		// int ipsw=Integer.valueOf(psw, 2);
		// int st=ijmpST & 31;//ijmpST & 01111
		// int re=st&ipsw;
		// boolean JP=(jmpST.charAt(0)-'0'==re);
		boolean JP = true;
		ArrayList<mIns> m = new ArrayList<mIns>();
		int reg = (ins & InsConst.Dt_rg_m) >>> InsConst.Dt_rg_p;// 寄存器编号
		if (!JP) {// 转移不成功
			if (InsConst.Reg[reg].equals("PC")) {// 指定pc
				m.addAll(Arrays.asList(new mIns("ET" + startT + "   PC+1->PC",
						new mOrder[] {
								new mOrder(GS.n_A,
										MulSel.sig_sel[ConLogic.A_con
												.get(GS.n_PC)]),
								new mOrder(GS.n_ALU, Alu.sig_ari_AC0),
								new mOrder(GS.n_SHIFTER, Shift.sig_SL),
								new mOrder(GS.n_PC, Reg.sig_CP) })));
			} else {// 直接跳转
				return m;
			}
		} else {
			m.addAll(stt.getMicroOrders(ins, 0));

			// 按JMP，JSR分支
			int opn = (ins & InsConst.Op_start_m) >>> InsConst.Op_start_p;// 操作码
			String op = InsConst.Operand[opn];
			List<mIns> m1 = null;
			if (op.equals("JSR")) {
				m1 = pd.getMicroOrders(ins, 0);
				m.addAll(m1);
			}

			m.addAll(Arrays.asList(new mIns("ET" + (m1 == null ? 0 : m1.size())
					+ "   C->PC", new mOrder[] {
					new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
							.get(GS.n_C)]),
					new mOrder(GS.n_ALU, Alu.sig_log_A),
					new mOrder(GS.n_SHIFTER, Shift.sig_SL),
					new mOrder(GS.n_PC, Reg.sig_CP) })));
			List<mIns> m2 = ni.getMicroOrders(0, (m1 == null ? 0 : m1.size()) + 1);
			m.addAll(m2);
		}
		return m;
	}

}
