package CPU.InstructionDecode;

import java.util.Arrays;
import java.util.List;

import CPU.Com.Register.Alu;
import CPU.Com.Register.ConLogic;
import CPU.Com.Register.MAReg;
import CPU.Com.Register.MulSel;
import CPU.Com.Register.Shift;
import ModelMachine.GS;

public class NextInstruction implements mInsResolver {

	@Override
	public List<mIns> getMicroOrders(int instruction, int startT) {
		return Arrays.asList(new mIns(
				"ET"+startT+"   PC->MAR",new mOrder[]{
						new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con.get(GS.n_PC)]),
						new mOrder(GS.n_ALU, Alu.sig_log_A),
						new mOrder(GS.n_SHIFTER, Shift.sig_DM),
						new mOrder(GS.n_MAR, MAReg.sig_CP),
		}));
	}

}
