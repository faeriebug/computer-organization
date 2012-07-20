package CPU.Com.Register;

import CPU.Com.RegisterType;
import CPU.Com.Port;

/**
 * 
 * @author WuyaMony
 *
 */
public class innerBus extends RegisterType {
	public innerBus(int num){
		inout=new Port[1];
		inout[0]=new Port(num);
		inout[0].owner=this;
	}
}
