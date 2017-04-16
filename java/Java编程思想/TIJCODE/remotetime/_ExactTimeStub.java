/*
 * File: ./REMOTETIME/_EXACTTIMESTUB.JAVA
 * From: .\C15\CORBA\EXACTTIME.IDL
 * Date: Mon Apr 24 05:32:56 2000
 *   By: C:\PROGTO~1\JAVA\BIN\IDLTOJ~1.EXE Java IDL 1.2 Aug 18 1998 16:25:34
 */

package remotetime;
public class _ExactTimeStub
	extends org.omg.CORBA.portable.ObjectImpl
    	implements remotetime.ExactTime {

    public _ExactTimeStub(org.omg.CORBA.portable.Delegate d) {
          super();
          _set_delegate(d);
    }

    private static final String _type_ids[] = {
        "IDL:remotetime/ExactTime:1.0"
    };

    public String[] _ids() { return (String[]) _type_ids.clone(); }

    //	IDL operations
    //	    Implementation of ::remotetime::ExactTime::getTime
    public String getTime()
 {
           org.omg.CORBA.Request r = _request("getTime");
           r.set_return_type(org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.tk_string));
           r.invoke();
           String __result;
           __result = r.return_value().extract_string();
           return __result;
   }

};
