package CPU.InstructionDecode;

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
import ModelMachine.GS;

/**
 * ѹջ
 * 
 * @author HuHaixiao
 * 
 */
public class PopDown implements mInsResolver {

	@Override
	public List<mIns> getMicroOrders(int instruction, int startT) {
		return Arrays.asList(
				new mIns("ET" + startT + "   SP-1->SP", new mOrder[] {
						new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
								.get(GS.n_SP)]),
						new mOrder(GS.n_ALU, Alu.sig_ari_Adec),
						new mOrder(GS.n_SHIFTER, Shift.sig_DM),
						new mOrder(GS.n_SP, Reg.sig_CP) }),
				new mIns("ET" + (startT + 1) + "   SP->MAR", new mOrder[] {
						new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
								.get(GS.n_SP)]),
						new mOrder(GS.n_ALU, Alu.sig_log_A),
						new mOrder(GS.n_SHIFTER, Shift.sig_DM),
						new mOrder(GS.n_MAR, MAReg.sig_CP) }),
				new mIns("ET" + (startT + 2) + "   PC->MDR", new mOrder[] {
						new mOrder(GS.n_A, MulSel.sig_sel[ConLogic.A_con
								.get(GS.n_PC)]),
						new mOrder(GS.n_ALU, Alu.sig_log_A),
						new mOrder(GS.n_SHIFTER, Shift.sig_DM),
						new mOrder(GS.n_MDR, MDReg.sig_CP) }), new mIns("ET"
						+ (startT + 3) + "   MDR->M", new mOrder[] {
						new mOrder(GS.n_MAR, MAReg.sig_EMAR),
						new mOrder(GS.n_MDR, MDReg.sig_W),
						new mOrder(GS.n_MS, MStore.sig_W) }));
	}

}
