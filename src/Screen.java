public class Screen {
    protected static Screen uniqueInstance;
    protected int x;

    private Screen() {
        x=0;
    }
       
   
    public static Screen instance() {
        if(uniqueInstance==null) {
            uniqueInstance= new Screen();
        }
        return uniqueInstance; 
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }


}
