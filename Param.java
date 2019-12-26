public class Param {
    public String nameParam;
    public String typeParam;
    public Param() {

    }
    public Param(String nameParam , String typeParam)
    {
        this.nameParam= nameParam;
        this.typeParam= typeParam;
    }

    public String getNameParam() {
        return nameParam;
    }

    public void setNameParam(String nameParam) {
        this.nameParam = nameParam;
    }

    public String getTypeParam() {
        return typeParam;
    }

    public void setTypeParam(String typeParam) {
        this.typeParam = typeParam;
    }
    public void getParam(String s)
    {
        int i = s.indexOf(' ');
        this.setTypeParam(s.substring(0 , i));
        this.setNameParam(s.substring( i+1 , s.length()));
    }
    public String toString()
    {
        return this.getTypeParam() + " : "+  this.getNameParam();
    }
    public static void main (String[] args)
    {
        Param aaa = new Param("String" , "a ");
        aaa.getParam("String aa");
        System.out.println(aaa.toString());
    }
}
