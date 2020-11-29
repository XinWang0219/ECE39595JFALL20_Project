//package ECE39595JFALL20_Project;
public class Action {
    String msg;
    int v;
    char c;

    public Action(){
        
    }
    
    public void setMessage(String _msg){
        msg = _msg;
        //System.out.println(msg);
    }
    
    public void setIntValue(int _v){
        v = _v;
        //System.out.println("Action setIntValue: "+v);
    }
    
    public void setCharValue(char _c){
        c = _c;
        //System.out.println("Action setCharValue: "+c);
    }
    
    public char getCharValue() {
    	return c;
    }
    
    public int getIntValue() {
    	return v;
    }
    
    public String getMessage() {
    	return msg;
    }

    @Override
    public String toString(){
        String str = "Action: \n";
        str += "    Message: "+msg;
        str += "    IntValue: "+v;
        str += "    CharValue: "+c;
        return str;
    }
}

