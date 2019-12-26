
public class Modifer {
    protected boolean isPublic;
    protected boolean isPrivate;
    protected boolean isProtected;
    protected boolean isStatic;
    public Modifer()
    {
    }
    public Modifer(boolean isPublic , boolean isProtected , boolean isPrivate , boolean isStatic)
    {
        this.isPublic=isPublic;
        this.isPrivate=isPrivate;
        this.isProtected=isProtected;
        this.isStatic=isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String toString() {
        if (isPublic()) return"+";
        if (isProtected()) return "#";
        if (isPrivate()) return "-";
        return null;
    }
}

