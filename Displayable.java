//package ECE39595JFALL20_Project;
public class Displayable{

    private int visible;
    private int maxHit;
    private int hpMoves;
    private int Hp;
    private char t;     // type
    private int v;      // int value
    private int posX;
    private int posY;
    private int width;
    private int height;
    
    //add name property
    public String name;

    public Displayable(){

    }

    public void setInvisible(){
        visible = 0;
        //System.out.println("Visible: "+visible);
    }

    public void setVisible(){
        visible = 1;
        //System.out.println("Visible: "+visible);
    }

    public void setMaxHit(int _maxHit){
        //System.out.println("went into setMaxHit");
        maxHit = _maxHit;
        //System.out.println(maxHit);
    }

    public void setHpMove(int _hpMoves){
        hpMoves = _hpMoves;
        //System.out.println(hpMoves);
    }

    public void setHp(int _Hp){
        Hp = _Hp;
        //System.out.println(Hp);
    }

    public void setType(char _t){
        t = _t;
        //System.out.println(t);
    }

    public char getType(){
        return t;
    }

    public void setIntValue(int _v){
        v = _v;
        //System.out.println(v);
    }

    public void setPosX(int x){
        posX = x;
        //System.out.println(posX);
    }

    public int getPosX(){
        return posX;
    }

    public void setPosY(int y){
        posY = y;
        //System.out.println(posY);
    }

    public int getPosY(){
        return posY;
    }

    public int getWidth(){
        return width;
    }

    public void setWidth(int x){
        width = x;
        //System.out.println(width);
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int y){
        height = y;
        //System.out.println(height);
    }
    
    //adding name property
    public void setName(String _name){
        name = _name;
    }
    
    @Override
    public String toString(){
        String str = "Displayable: \n";
        str += "    visible: "+visible + "\n";
        str += "    maxHit: "+maxHit + "\n";
        str += "    hpMoves: "+hpMoves + "\n";
        str += "    Hp: "+Hp + "\n";
        str += "    t: "+t + "\n";
        str += "    v: "+v + "\n";
        str += "    posX: "+posX + "\n";
        str += "    posY: "+posY + "\n";
        str += "    width: "+width + "\n";
        str += "    height: "+height + "\n";
        
        return str;
    }
}