import javax.management.modelmbean.ModelMBeanAttributeInfo;
import java.lang.reflect.Constructor;
import java.util.Vector;

public class Constructors extends Modifer {
    protected String name_Constructor;
    protected Vector<Param> params = new Vector<>();
    public Constructors()
    {
    }
    public Constructors(boolean isPublic , boolean isPrivate , boolean isProtected , boolean isStactic ,String name_Constructor, Vector<Param> params)
    {
        super(isPublic, isProtected, isPrivate ,isStactic);
        this.name_Constructor=name_Constructor;
        this.params=params;
    }
    public void getConstructor(String s)
    {
        int length =s.length();
        if  (s.contains("public") )
        {
            isPublic=true;
            s = s.substring(s.indexOf("public") + 7 , length);
            length = s.length();
        }
        if (s.contains("private"))
        {
            isPrivate=true;
            s = s.substring(s.indexOf("private") +7 , length);
            s= s.trim();
            length = s.length();
        }
        if (s.contains("protected"))
        {
            isProtected=true;
            s = s.substring(s.indexOf("protected")+9 , length);
            s=s.trim();
            length = s.length();
        }
        if (s.contains("static"))
        {
            isStatic=true;
            s = s.substring(s.indexOf("static")+6 , length);
            s = s.trim();
            length = s.length();
        }
        int i = s.indexOf('(');
        int j = s.indexOf(')');
        String s_param;
        if (i==j-1)
            s_param="";
        else {
            s_param = s.substring(i + 1, j);
            getparam(s_param+",");
        }
        s = s.substring(0 , i);
        s=s.trim();
        setName_Constructor(s);
    }
    public void getparam(String s) {
        Vector<Param> arr = new Vector<>();

        while (!s.isEmpty()) {
            int i = s.indexOf(',');
            int length= s.length();
            Param a = new Param();
            a.getParam(s.substring(0, i));
            if (a==null)
                System.out.println("ccc");
            else params.add(a);
            s=s.substring(i+1, length);
            length=s.length();
        }
    }

    public void setName_Constructor(String name_Constructor) {
        this.name_Constructor = name_Constructor;
    }

    public void setParams(Vector<Param> params) {
        this.params = params;
    }

    public String getName_Constructor() {
        return name_Constructor;
    }

    public Vector<Param> getParams() {
        return params;
    }
    public String toString()
    {
        String s="";
        s=s+super.toString() + getName_Constructor() +"(";
        for (Param param:params)
        {
            s=s+param.toString()+ ",";
        }
        s=s.substring(0 , s.length()-1)+")";
        return s;
    }
    public static void main (String[] args)
    {
        String s= "public Circle (Point center, double radius ,String color, boolean filled)";
        Constructors a= new Constructors();
        a.getConstructor(s);
        System.out.println(a.toString());
    }
}
