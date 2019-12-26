import java.util.*;
public class ClassObj {
	public String ClassName;
	public List <String> stringFields;
	public List <String> stringMethods;
	public List <Field> listFields;
	public List <Method> listMethods;
	public String ClassExtend;
	public String name;
	public boolean isExtend;
	public boolean isImplement;
	public Vector<String> vectorImplement= new Vector<String>();
	public ClassObj(String _ClassName, List<String> _fields,List<String> _methods,String _ClassExtend,boolean _isExtend,String _name,boolean _isImplement,Vector<String>_vectorImplement) {
		ClassName= _ClassName;
		stringFields =_fields;
		stringMethods= _methods;
		ClassExtend=_ClassExtend;
		isExtend=_isExtend;
		name= _name;
		isImplement=_isImplement;
		vectorImplement=_vectorImplement;
	}
//	public String to_String() {
//		return ClassName;
//	}
//	public void setStringforClassObj() {
//		for(Field f: listFields) {
//			stringFields.add(f.toString());
//		}
//		for(Method m: listMethods) {
//			stringMethods.add(m.toString());
//		}
//	}
}
