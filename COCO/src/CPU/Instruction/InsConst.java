package CPU.Instruction;

import ModelMachine.GS;

/**
 * 指令格式规定
 * @author WuyaMony
 *
 */
public interface InsConst {
	/**寻址方式*/
	public static final String[] AddrMode = new String[] { "R", "(R)", "-(R)",
			"(R)+", "@(R)+", "X(R)", "SKP" };
	/**操作码*/
	public static final String[] Operand = new String[] { "MOV", "ADD", "SUB",
			"AND", "OR", "EOR", "COM", "NEG", "INC","DEC","SL","SR","JMP","RST","JSR","END"};
	/**寄存器*/
	public static final String[] Reg=new String[]{
		GS.n_R0,GS.n_R1,GS.n_R2,GS.n_R3
		,GS.n_SP,GS.n_PSW,GS.n_PC
	};
	/**操作码在指令中的起止位置*/
//	public static final int Op_start=0,Op_len=4;
	public static final int Op_start_m=0b1111000000000000,Op_start_p=12;
	/**目的地址寄存器在指令中的起止位置*/
//	public static final int Dt_rg=4,Dt_rg_len=3;
	public static final int Dt_rg_m=0b111000000000,Dt_rg_p=9;
	/**取目的地址寻址方式在指令中的起止位置*/
//	public static final int Dt_mode=7,Dt_mode_len=3;
	public static final int Dt_mode_m=0b111000000,Dt_mode_p=6;
	/**源操作数寄存器在指令中的起止位置*/
//	public static final int St_rg=10,St_rg_len=3;
	public static final int St_rg_m=0b111000,St_rg_p=3;
	/**取源操作数寻址方式在指令中的起止位置*/
//	public static final int St_mode=13,St_mode_len=3;
	public static final int St_mode_m=0b111,St_mode_p=0;
	/**转移条件在指令中的起止位置*/
	public static final int Jmp_ST_m=0b111111,Jmp_ST_p=0;
}
