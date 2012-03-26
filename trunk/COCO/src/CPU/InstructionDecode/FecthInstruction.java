package CPU.InstructionDecode;

import java.util.Arrays;
import java.util.List;

import CPU.Com.Register.Alu;
import CPU.Com.Register.ConLogic;
import CPU.Com.Register.MAReg;
import CPU.Com.Register.MStore;
import CPU.Com.Register.MulSel;
import CPU.Com.Register.Reg;
import CPU.Com.Register.Shift;
import ModelMachine.GS;


public class FecthInstruction implements mInsResolver {
	@Override
	public  List<mIns> getMicroOrders(int instruction, int startT) {
		return Arrays.asList(new mIns[]{
				new mIns("FT0   M->IR",new mOrder[]{new mOrder(GS.n_MAR, MAReg.sig_EMAR),
						new mOrder(GS.n_MS, MStore.sig_R),
						new mOrder(GS.n_IR, Reg.sig_CP)}),
				new mIns("FT1   PC+1->PC",new mOrder[]{
						new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con.get(GS.n_PC)]),
						new mOrder(GS.n_ALU, Alu.sig_ari_AC0),
						new mOrder(GS.n_SHIFTER, Shift.sig_DM),
						new mOrder(GS.n_PC, Reg.sig_CP)})
		});
	}
}
