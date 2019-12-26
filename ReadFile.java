import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ReadFile{
    private List<Classs> classses = new ArrayList<Classs>();
    static File file = new File("C:\\Users\\thang\\eclipse-workspace\\DaHinh2\\src\\");
    static File[] files = file.listFiles();
    private static ArrayList<String> List_NameClass = new ArrayList<String>();

    public void setClassses(List<Classs> classses) {
        this.classses = classses;
    }

    public List<Classs> getClassses() {
        return classses;
    }

    public static void getNameClass()
    {

        for (File file1 :files)
        {
            String name= file1.getName();
            List_NameClass.add(name);
        }
    }
    void Show () throws IOException {
        for (String name1 : List_NameClass) {
            //System.out.println(name1);
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\thang\\eclipse-workspace\\DaHinh2\\src\\" + name1));
            int i = 0;
            while (!lines.get(i).contains("class") && !lines.get(i).contains("interface"))
                i++;
            i++;
            while (i < lines.size()) {
                if (lines.get(i).contains("{") && !lines.get(i).contains("}")) {
                    int check = 1;
                    i = i + 1;
                    while (check != 0) {
                        if (lines.get(i).contains("{"))
                            check++;
                        if (lines.get(i).contains("}"))
                            check--;
                        lines.remove(i);
                    }
                }
                else if (lines.get(i).contains("@Override") || lines.get(i).contains("//"))
                    lines.remove(i);
                else i++;
            }
            Classs a =new Classs();
            classses.add(a);
            a.getClasss(lines);
        }
    }
    public List<Classs> getListClass(){
    	return classses;
    }
    public static void main(String[] args) throws IOException {
        ReadFile a = new ReadFile();
        a.getNameClass();
        a.Show();
        for (Classs c : a.classses)
            System.out.println(c.getName_Class_extend());
    }
}
 /*   public Circle(){};
public Circle(double radius){
public  Circle (double radius, String color , boolean filled)
        {
public Circle (Point center, double radius ,String color, boolean filled)
        {*/