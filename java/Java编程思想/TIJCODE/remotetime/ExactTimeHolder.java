/*
 * File: ./REMOTETIME/EXACTTIMEHOLDER.JAVA
 * From: .\C15\CORBA\EXACTTIME.IDL
 * Date: Mon Apr 24 05:32:56 2000
 *   By: C:\PROGTO~1\JAVA\BIN\IDLTOJ~1.EXE Java IDL 1.2 Aug 18 1998 16:25:34
 */

package remotetime;
public final class ExactTimeHolder
     implements org.omg.CORBA.portable.Streamable{
    //	instance variable 
    public remotetime.ExactTime value;
    //	constructors 
    public ExactTimeHolder() {
	this(null);
    }
    public ExactTimeHolder(remotetime.ExactTime __arg) {
	value = __arg;
    }

    public void _write(org.omg.CORBA.portable.OutputStream out) {
        remotetime.ExactTimeHelper.write(out, value);
    }

    public void _read(org.omg.CORBA.portable.InputStream in) {
        value = remotetime.ExactTimeHelper.read(in);
    }

    public org.omg.CORBA.TypeCode _type() {
        return remotetime.ExactTimeHelper.type();
    }
}
