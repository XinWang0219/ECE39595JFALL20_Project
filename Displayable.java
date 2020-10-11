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
        System.out.println("Visible: "+visible);
    }

    public void setVisible(){
        visible = 1;
        System.out.println("Visible: "+visible);
    }

    public void setMaxHit(int _maxHit){
        System.out.println("went into setMaxHit");
        maxHit = _maxHit;
        System.out.println(maxHit);
    }

    public void setHpMove(int _hpMoves){
        hpMoves = _hpMoves;
        System.out.println(hpMoves);
    }

    public void setHp(int _Hp){
        Hp = _Hp;
        System.out.println(Hp);
    }

    public void setType(char _t){
        t = _t;
        System.out.println(t);
    }

    public void setIntValue(int _v){
        v = _v;
        System.out.println(v);
    }

    public void setPosX(int x){
        posX = x;
        System.out.println(posX);
    }

    public void setPosY(int y){
        posY = y;
        System.out.println(posY);
    }

    public void setWidth(int x){
        width = x;
        System.out.println(width);
    }

    public void setHeight(int y){
        height = y;
        System.out.println(height);
    }
    
    //adding name property
    public void setName(String _name){
        name = _name;
    }
    
    @Override
    public String toString(){
        String str = "Displayable: \n";
        str += "    visible: "+visible;
        str += "    maxHit: "+maxHit;
        str += "    hpMoves: "+hpMoves;
        str += "    Hp: "+Hp;
        str += "    t: "+t;
        str += "    v: "+v;
        str += "    posX"+posX;
        str += "    posY"+posY;
        str += "    width"+width;
        str += "    height"+height;
        
        return str;
    }
}