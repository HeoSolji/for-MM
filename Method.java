import java.util.*;

public class Method extends Modifer{
    private String returntype;
    private String nameMethod;
    private boolean isAbstract;
    private Vector<Param> param = new Vector<>();
    int index =0;
    public Method()
    {

    }
    public Method(boolean isPublic , boolean isPrivate , boolean isProtected , boolean isStactic ,String returntype,String nameMethod)
    {
        super(isPublic , isProtected , isStactic ,isPrivate);
        this.nameMethod= nameMethod;
        this.returntype= returntype;
    }
    public Method(Vector<Param> param)
    {
        this.param=param;
    }
    public void getMethod(String s)
    {
        int length =s.length();
        if  (s.contains("public") )
        {
            isPublic=true;
            s = s.replace("public" ,"");
            length = s.length();
        }
        if (s.contains("private"))
        {
            isPrivate=true;
            s = s.replace("private" , "");
            s= s.trim();
            length = s.length();
        }
        if (s.contains("protected"))
        {
            isProtected=true;
            s = s.replace("protected" , "");
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
        if (s.contains("abstract")) {
            isAbstract = true;
            s = s.replace("abstract" , "");
            s = s.trim();
            length = s.length();
        }
        int i = s.indexOf('(');
        int j = s.indexOf(')');
        if (i==j-1)
        {
        }

        else {
            String s_param = s.substring(i + 1, j);
            s = s.substring(0 , i);
            getparam(s_param + ",");
        }
        s = s.substring(0 , i);
        s = s.trim();
            if (s.contains(" ")) {
                setReturntype(s.substring(0, s.indexOf(" ")));
                setNameMethod(s.substring(s.indexOf(" ") + 1, s . length()));
            }
            else {
                setNameMethod(s);
                setReturntype(null);
            }
    }
    public String toString() {
        String s ="";
        s = s + super.toString() + getNameMethod() + "(";
        if (!param.isEmpty()) {
            for (Param param1 : param) {
                s = s + param1.toString() + ",";
            }
            String s1 = s.substring(0, s.length() - 1);
            s = s1;
        }
        else s = s;
        if (getReturntype()== null) s= s+ ")";
                else  s= s+")" + " : " +getReturntype();
        return s;
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
            else param.add(a);
            s=s.substring(i+1, length);
            s = s.trim();
        }
    }

    public void setReturntype(String returntype){
        this.returntype = returntype;
    }

    public void setNameMethod(String nameMethod) {
        this.nameMethod = nameMethod;
    }

    public String getNameMethod() {
        return nameMethod;
    }

    public String getReturntype() {
        return returntype;
    }


    public static void main (String []  args)
    {
        String s = "    float tinhChuVi();";
        String s1 ="String a,String b,String c,";
        Method a = new Method();
        a.getMethod(s);
        System.out.println(a.toString());
    }

}