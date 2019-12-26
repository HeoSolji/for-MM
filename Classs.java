import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Classs extends Modifer{
    private String name;
    private String name_Class_extend ;
    private Vector<String> name_Class_Implement = new Vector<>();
    private boolean isAbstraction;
    private boolean isExtend;
    private boolean isImplement;
    private boolean isInterface;
    private Vector<Method> methods= new Vector<>();
    private Vector<Field> fields =new Vector<>();
    public Classs()
    {
    }
    public Classs(String name ,boolean isPublic, boolean isPrivate , boolean isProtected , boolean isStatic,  boolean isAbstraction , boolean isExtend , boolean isImplement , Vector<Method> methods , Vector<Field> fields )
    {
        super(isPublic , isProtected , isPrivate , isStatic);
        this.name= name;
        this.isAbstraction= isAbstraction;
        this.isExtend= isExtend;
        this.isImplement=isImplement;
        this.methods= methods;
        this.fields=fields;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbstraction(boolean abstraction) {
        isAbstraction = abstraction;
    }

    public void setExtend(boolean extend) {
        isExtend = extend;
    }

    public void setImplement(boolean implement) {
        isImplement = implement;
    }

    public void setMethods(Vector<Method> methods) {
        this.methods = methods;
    }

    public void setFields(Vector<Field> fields) {
        this.fields = fields;
    }
    public String getClassName() {
        return name;
    }
    public boolean getImplement() {
    	return isImplement;
    }
    public String getName() {
        if (isAbstraction)
            return name+"<abstract>";
        else if     (isInterface)
                return name+"<interface>";
        else
            return name;
    }

    public void setName_Class_extend(String name_Class_extend) {
        this.name_Class_extend = name_Class_extend;
    }

    public void setName_Class_Implement(Vector<String> name_Class_Implement) {
        this.name_Class_Implement = name_Class_Implement;
    }
    public Vector<String> getName_Class_Implement() {
        return name_Class_Implement;
    }

    public boolean isAbstraction() {
        return isAbstraction;
    }

    public boolean isExtend() {
        return isExtend;
    }

    public boolean isImplement() {
        return isImplement;
    }

    public Vector<Field> getFields() {
        return fields;
    }

    public Vector<Method> getMethods() {
        return methods;
    }

    public String getName_Class_extend() {
        return name_Class_extend;
    }

    public void getNameClasss(String s)
    {
        if (s.contains("interface"))
        {
            isInterface = true;
            s= s.replace("interface" , "");
            s = s.trim();
        }
        if (s.contains("{"))
            s = s.substring(0 , s.indexOf("{"));
        s = s.replace("class" , "");
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
        if (s.contains("abstract")) {
            isAbstraction = true;
            s = s.replace("abstract" , "");
            s = s.trim();
            length = s.length();
        }
        if (s.contains("implements"))
        {
            isImplement=true;
            String s_implement =   s.substring(s.indexOf("implements") +10, length);
            //s= s.substring(0 , s.indexOf("implements"));
            s = s.replace(s_implement , "");
            s_implement = s_implement.trim() + ",";
            while (!s_implement.isEmpty())
            {
                String name =s_implement.substring(0 , s_implement.indexOf(","));
                name=name.trim();
                name_Class_Implement.add(name);
                s_implement=s_implement.substring(s_implement.indexOf(",")+1 , s_implement.length());
            }

            for (String sss : name_Class_Implement)
                System.out.println(sss);
        }
        if (s.contains("extends"))
        {
            isExtend=true;
            String s_extends = s.substring(s.indexOf("extends")+7 , s.length());
            s = s.substring(0 , s.indexOf("extends"));
            s_extends = s_extends.trim();
            setName_Class_extend(s_extends.substring(0, s_extends.length()));
        }
        s= s.trim();
        name = s.substring(s.indexOf(" ")+1 , s.length());

    }
    public void getClasss(List<String> lines)
    {
        int i =0;
        String name_class;
        while (!lines.get(i).contains("class") && !lines.get(i).contains("interface"))
            i++;
        getNameClasss(lines.get(i));
        for (int index = i ;index<lines.size()-1;index++)
        {
            if ((lines.get(index).contains("(")) && !lines.get(index).contains("="))
            {
                Method new_method = new Method();
                new_method.getMethod(lines.get(index));
                methods.add(new_method);
            }
            else if (lines.get(index).contains(";") && !lines.get(index).contains("{") && !lines.get(index).contains("abstract"))
            {
                //System.out.println(lines.get(index));
                Field newfield = new Field();
                newfield.getField(lines.get(index));
                fields.add(newfield);
            }
        }
    }
    public String toString(){
        String result="";
        result = result+getName() +'\n';
        result = result+ "isAbstact : " + isAbstraction()+ '\n';
        result = result + "Class extends : " +  getName_Class_extend() + '\n';
        for (Field field :fields)
            result = result+ field.toString()+'\n';
        for (Method method :methods) {
            result = result + method.toString() + '\n';
        }
        return result;
    }
    public void show(List<String>  l)
    {
        for (String s : l)
        {
            System.out.println(s);
        }
    }
    public List<String> getStringField()
    {
        List<String> list = new ArrayList<>();
        for (Field f :fields)
        {
            list.add(f.toString());
        }
        return  list;
    }
    public List<String> getStringFields()
    {
        List<String> list = new ArrayList<>();
        for (Field f :fields)
        {
            list.add(f.toString());
        }
        return  list;
    }

    public List<String> getStringMethods()
    {
        List<String> list = new ArrayList<>();
        for (Method f :methods)
        {
            list.add(f.toString());
        }
        return  list;
    }
    public static void main(String[] args)
    {
        Classs a = new Classs();
        a.getNameClasss("public interface hinh");
        System.out.println(a.toString());
    }

}