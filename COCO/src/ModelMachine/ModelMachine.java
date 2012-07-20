package ModelMachine;

import CPU.Com.Port;
import CPU.Com.uniwire;
import CPU.Com.Register.Alu;
import CPU.Com.Register.innerBus;
import CPU.Com.Register.ConLogic;
import CPU.Com.Register.MAReg;
import CPU.Com.Register.MDReg;
import CPU.Com.Register.MStore;
import CPU.Com.Register.MulSel;
import CPU.Com.Register.Reg;
import CPU.Com.Register.Shift;

public class ModelMachine {
	Alu ALU;
	ConLogic CL;
	MAReg MAR;
	MDReg MDR;
	MStore MS;
	MulSel A, B;
	Reg R0, R1, R2, R3, C, D, IR, PC, SP, PSW;
	Shift SH;
	innerBus IB, DB, AB,CB;

	public ModelMachine() {
		ALU = new Alu();ALU.name=GS.n_ALU;
		CL = new ConLogic();CL.name=GS.n_CL;
		MAR = new MAReg();MAR.name=GS.n_MAR;
		MDR = new MDReg();MDR.name=GS.n_MDR;
		MS = new MStore();MS.name=GS.n_MS;
		A = new MulSel();A.name=GS.n_A;
		B = new MulSel();B.name=GS.n_B;
		R0 = new Reg(3);R0.name=GS.n_R0;
		R1 = new Reg(3);R1.name=GS.n_R1;
		R2 = new Reg(3);R2.name=GS.n_R2;
		R3 = new Reg(3);R3.name=GS.n_R3;
		C = new Reg(3);C.name=GS.n_C;
		D = new Reg(3);D.name=GS.n_D;
		IR = new Reg(2);IR.name=GS.n_IR;
		PC = new Reg(2);PC.name=GS.n_PC;
		SP = new Reg(2);SP.name=GS.n_SP;
		PSW = new Reg(3);PSW.name=GS.n_PSW;
		SH = new Shift();SH.name=GS.n_SHIFTER;
		IB = new innerBus(12);IB.name=GS.n_IB;// 0���ߣ�1~11����
		DB = new innerBus(5);DB.name=GS.n_DB;// 0MDR���ߣ�1MS���ߣ�2MDR���ߣ�3IR���ߣ�4MS����
		AB = new innerBus(2);AB.name=GS.n_AB;// 0���ߣ�1����
		CB=new innerBus(5);CB.name=GS.n_CB;
		// A->ALU
		uniwire a_alu = new uniwire(A.inout[0], ALU.inout[0]);
		ALU.inout[0].wire[0] =
				A.inout[0].wire[8] = a_alu;
		// B->ALU
		uniwire b_alu = new uniwire(B.inout[0], ALU.inout[1]);
		ALU.inout[1].wire[0] = B.inout[0].wire[8] = b_alu;
		// PSW->ALU
		uniwire psw_alu = new uniwire(PSW.inout[0], ALU.inout[2]);
		ALU.inout[2].wire[0] = PSW.inout[0].wire[1] = psw_alu;
		// ALU->SH
		uniwire alu_SH = new uniwire(ALU.inout[3], SH.inout[0]);
		ALU.inout[3].wire[0] = SH.inout[0].wire[0] = alu_SH;
		// SH->IB
		uniwire SH_IB = new uniwire(SH.inout[0], IB.inout[0]);
		SH.inout[0].wire[1] = IB.inout[0].wire[0] = SH_IB;
		// IB->R0
		uniwire IB_R0 = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[1] = R0.inout[0].wire[0] = IB_R0;
		// IB->R1
		uniwire IB_R1 = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[2] = R1.inout[0].wire[0] = IB_R1;
		// IB->R2
		uniwire IB_R2 = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[3] = R2.inout[0].wire[0] = IB_R2;
		// IB->R3
		uniwire IB_R3 = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[4] = R3.inout[0].wire[0] = IB_R3;
		// IB->C
		uniwire IB_C = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[5] = C.inout[0].wire[0] = IB_C;
		// IB->D
		uniwire IB_D = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[6] = D.inout[0].wire[0] = IB_D;
		// IB->MAR
		uniwire IB_MAR = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[7] = MAR.inout[0].wire[0] = IB_MAR;
		// IB->MDR
		uniwire IB_MDR = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[8] = MDR.inout[0].wire[0] = IB_MDR;
		// IB->PC
		uniwire IB_PC = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[9] = PC.inout[0].wire[0] = IB_PC;
		// IB->SP
		uniwire IB_SP = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[10] = SP.inout[0].wire[0] = IB_SP;
		// IB->PSW
		uniwire IB_PSW = new uniwire(IB.inout[0], R0.inout[0]);
		IB.inout[0].wire[11] = PSW.inout[0].wire[0] = IB_PSW;
		// R0->A
		uniwire R0_A = new uniwire(R0.inout[0], A.inout[0]);
		R0.inout[0].wire[1] = A.inout[0].wire[0] = R0_A;
		// R1->A
		uniwire R1_A = new uniwire(R1.inout[0], A.inout[0]);
		R1.inout[0].wire[1] = A.inout[0].wire[1] = R1_A;
		// R2->A
		uniwire R2_A = new uniwire(R2.inout[0], A.inout[0]);
		R2.inout[0].wire[1] = A.inout[0].wire[2] = R2_A;
		// R3->A
		uniwire R3_A = new uniwire(R3.inout[0], A.inout[0]);
		R3.inout[0].wire[1] = A.inout[0].wire[3] = R3_A;
		// C->A
		uniwire C_A = new uniwire(C.inout[0], A.inout[0]);
		C.inout[0].wire[1] = A.inout[0].wire[4] = C_A;
		// D->A
		uniwire D_A = new uniwire(D.inout[0], A.inout[0]);
		D.inout[0].wire[1] = A.inout[0].wire[5] = D_A;
		// PC->A
		uniwire PC_A = new uniwire(PC.inout[0], A.inout[0]);
		PC.inout[0].wire[1] = A.inout[0].wire[6] = PC_A;
		// SP->A
		uniwire SP_A = new uniwire(SP.inout[0], A.inout[0]);
		SP.inout[0].wire[1] = A.inout[0].wire[7] = SP_A;
		// R0->B
		uniwire R0_B = new uniwire(R0.inout[0], B.inout[0]);
		R0.inout[0].wire[2] = B.inout[0].wire[0] = R0_B;
		// R1->B
		uniwire R1_B = new uniwire(R1.inout[0], B.inout[0]);
		R1.inout[0].wire[2] = B.inout[0].wire[1] = R1_B;
		// R2->B
		uniwire R2_B = new uniwire(R2.inout[0], B.inout[0]);
		R2.inout[0].wire[2] = B.inout[0].wire[2] = R2_B;
		// R3->B
		uniwire R3_B = new uniwire(R3.inout[0], B.inout[0]);
		R3.inout[0].wire[2] = B.inout[0].wire[3] = R3_B;
		// C->B
		uniwire C_B = new uniwire(C.inout[0], B.inout[0]);
		C.inout[0].wire[2] = B.inout[0].wire[4] = C_B;
		// D->B
		uniwire D_B = new uniwire(D.inout[0], B.inout[0]);
		D.inout[0].wire[2] = B.inout[0].wire[5] = D_B;
		// PSW->B
		uniwire PSW_B = new uniwire(PSW.inout[0], B.inout[0]);
		PSW.inout[0].wire[2] = B.inout[0].wire[6] = PSW_B;
		// MDR->B
		uniwire MDR_B = new uniwire(MDR.inout[0], B.inout[0]);
		MDR.inout[0].wire[1] = B.inout[0].wire[7] = MDR_B;
		// MAR->AB
		uniwire MAR_AB = new uniwire(MAR.inout[0], AB.inout[0]);
		MAR.inout[0].wire[1] = AB.inout[0].wire[0] = MAR_AB;
		// MDR->DB
		uniwire MDR_DB = new uniwire(MDR.inout[0], DB.inout[0]);
		MDR.inout[0].wire[3] = DB.inout[0].wire[0] = MDR_DB;
		// DB->MDR
		uniwire DB_MDR = new uniwire(DB.inout[0], MDR.inout[0]);
		DB.inout[0].wire[2] = MDR.inout[0].wire[2] = DB_MDR;
		// DB->IR
		uniwire DB_IR = new uniwire(DB.inout[0], IR.inout[0]);
		DB.inout[0].wire[3] = IR.inout[0].wire[0] = DB_IR;
		// IR->CL
		uniwire IR_CL = new uniwire(IR.inout[0], CL.inout[0]);
		IR.inout[0].wire[1] = CL.inout[0].wire[0] = IR_CL;
		// AB->MS
		uniwire AB_MS = new uniwire(AB.inout[0], MS.inout[0]);
		AB.inout[0].wire[1] = MS.inout[0].wire[0] = AB_MS;
		// DB->MS
		uniwire DB_MS = new uniwire(DB.inout[0], MS.inout[1]);
		DB.inout[0].wire[4] = MS.inout[1].wire[0] = DB_MS;
		// MS->DB
		uniwire MS_DB = new uniwire(MS.inout[1], DB.inout[0]);
		MS.inout[1].wire[1] = DB.inout[0].wire[1] = MS_DB;
		// CL->R0
		uniwire CL_R0 = new uniwire(CL.inout[0], R0.sig);
		CL.inout[0].wire[1] = R0.sig.wire[0] = CL_R0;
		// CL->R1
		uniwire CL_R1 = new uniwire(CL.inout[0], R1.sig);
		CL.inout[0].wire[2] = R1.sig.wire[0] = CL_R1;
		// CL->R2
		uniwire CL_R2 = new uniwire(CL.inout[0], R2.sig);
		CL.inout[0].wire[3] = R2.sig.wire[0] = CL_R2;
		// CL->R3
		uniwire CL_R3 = new uniwire(CL.inout[0], R3.sig);
		CL.inout[0].wire[4] = R3.sig.wire[0] = CL_R3;
		// CL->C
		uniwire CL_C = new uniwire(CL.inout[0], C.sig);
		CL.inout[0].wire[5] = C.sig.wire[0] = CL_C;
		// CL->D
		uniwire CL_D = new uniwire(CL.inout[0], D.sig);
		CL.inout[0].wire[6] = D.sig.wire[0] = CL_D;
		// CL->MAR
		uniwire CL_MAR = new uniwire(CL.inout[0], MAR.sig);
		CL.inout[0].wire[7] = MAR.sig.wire[0] = CL_MAR;
		// CL->MDR
		uniwire CL_MDR = new uniwire(CL.inout[0], MDR.sig);
		CL.inout[0].wire[8] = MDR.sig.wire[0] = CL_MDR;
		// CL->IR
		uniwire CL_IR = new uniwire(CL.inout[0], IR.sig);
		CL.inout[0].wire[9] = IR.sig.wire[0] = CL_IR;
		// CL->PC
		uniwire CL_PC = new uniwire(CL.inout[0], PC.sig);
		CL.inout[0].wire[10] = PC.sig.wire[0] = CL_PC;
		// CL->SP
		uniwire CL_SP = new uniwire(CL.inout[0], SP.sig);
		CL.inout[0].wire[11] = SP.sig.wire[0] = CL_SP;
		// CL->PSW
		uniwire CL_PSW = new uniwire(CL.inout[0], PSW.sig);
		CL.inout[0].wire[12] = PSW.sig.wire[0] = CL_PSW;
		// CL->A
		uniwire CL_A = new uniwire(CL.inout[0], A.sig);
		CL.inout[0].wire[13] = A.sig.wire[0] = CL_A;
		// CL->B
		uniwire CL_B = new uniwire(CL.inout[0], B.sig);
		CL.inout[0].wire[14] = B.sig.wire[0] = CL_B;
		// CL->ALU
		uniwire CL_ALU = new uniwire(CL.inout[0], ALU.sig);
		CL.inout[0].wire[15] = ALU.sig.wire[0] = CL_ALU;
		// CL->SH
		uniwire CL_SH = new uniwire(CL.inout[0], SH.sig);
		CL.inout[0].wire[16] = SH.sig.wire[0] = CL_SH;
		// CL->MS
		uniwire CL_MS = new uniwire(CL.inout[0], MS.sig);
		CL.inout[0].wire[17] = MS.sig.wire[0] = CL_MS;
	}

	public void StartMachine() {
		Port sig = new Port();
		sig.data = ConLogic.sig_Start;
		CL.signalProcess(sig);
	}
	
	public static void main(String[] arg) {
		ModelMachine mm = new ModelMachine();
		mm.StartMachine();
	}
}
