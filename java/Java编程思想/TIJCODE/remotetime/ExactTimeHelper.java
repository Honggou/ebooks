/*
 * File: ./REMOTETIME/EXACTTIMEHELPER.JAVA
 * From: .\C15\CORBA\EXACTTIME.IDL
 * Date: Mon Apr 24 05:32:56 2000
 *   By: C:\PROGTO~1\JAVA\BIN\IDLTOJ~1.EXE Java IDL 1.2 Aug 18 1998 16:25:34
 */

package remotetime;
public class ExactTimeHelper {
     // It is useless to have instances of this class
     private ExactTimeHelper() { }

    public static void write(org.omg.CORBA.portable.OutputStream out, remotetime.ExactTime that) {
        out.write_Object(that);
    }
    public static remotetime.ExactTime read(org.omg.CORBA.portable.InputStream in) {
        return remotetime.ExactTimeHelper.narrow(in.read_Object());
    }
   public static remotetime.ExactTime extract(org.omg.CORBA.Any a) {
     org.omg.CORBA.portable.InputStream in = a.create_input_stream();
     return read(in);
   }
   public static void insert(org.omg.CORBA.Any a, remotetime.ExactTime that) {
     org.omg.CORBA.portable.OutputStream out = a.create_output_stream();
     write(out, that);
     a.read_value(out.create_input_stream(), type());
   }
   private static org.omg.CORBA.TypeCode _tc;
   synchronized public static org.omg.CORBA.TypeCode type() {
          if (_tc == null)
             _tc = org.omg.CORBA.ORB.init().create_interface_tc(id(), "ExactTime");
      return _tc;
   }
   public static String id() {
       return "IDL:remotetime/ExactTime:1.0";
   }
   public static remotetime.ExactTime narrow(org.omg.CORBA.Object that)
	    throws org.omg.CORBA.BAD_PARAM {
        if (that == null)
            return null;
        if (that instanceof remotetime.ExactTime)
            return (remotetime.ExactTime) that;
	if (!that._is_a(id())) {
	    throw new org.omg.CORBA.BAD_PARAM();
	}
        org.omg.CORBA.portable.Delegate dup = ((org.omg.CORBA.portable.ObjectImpl)that)._get_delegate();
        remotetime.ExactTime result = new remotetime._ExactTimeStub(dup);
        return result;
   }
}
