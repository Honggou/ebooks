/*
 * File: ./REMOTETIME/_EXACTTIMEIMPLBASE.JAVA
 * From: .\C15\CORBA\EXACTTIME.IDL
 * Date: Mon Apr 24 05:32:56 2000
 *   By: C:\PROGTO~1\JAVA\BIN\IDLTOJ~1.EXE Java IDL 1.2 Aug 18 1998 16:25:34
 */

package remotetime;
public abstract class _ExactTimeImplBase extends org.omg.CORBA.DynamicImplementation implements remotetime.ExactTime {
    // Constructor
    public _ExactTimeImplBase() {
         super();
    }
    // Type strings for this class and its superclases
    private static final String _type_ids[] = {
        "IDL:remotetime/ExactTime:1.0"
    };

    public String[] _ids() { return (String[]) _type_ids.clone(); }

    private static java.util.Dictionary _methods = new java.util.Hashtable();
    static {
      _methods.put("getTime", new java.lang.Integer(0));
     }
    // DSI Dispatch call
    public void invoke(org.omg.CORBA.ServerRequest r) {
       switch (((java.lang.Integer) _methods.get(r.op_name())).intValue()) {
           case 0: // remotetime.ExactTime.getTime
              {
              org.omg.CORBA.NVList _list = _orb().create_list(0);
              r.params(_list);
              String ___result;
                            ___result = this.getTime();
              org.omg.CORBA.Any __result = _orb().create_any();
              __result.insert_string(___result);
              r.result(__result);
              }
              break;
            default:
              throw new org.omg.CORBA.BAD_OPERATION(0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
       }
 }
}
