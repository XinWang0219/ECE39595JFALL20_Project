
public class ObjectDisplayGrid {
    private int gameHeight;
    private int width;
    private int topHeight;
    
    public ObjectDisplayGrid(){
        
    }
            
    public void getObjectDisplayGrid(int _gameHeight, int _width, int _topHeight){
        gameHeight = _gameHeight;
        width = _width;
        topHeight = _topHeight;
    }
    
    public void setTopMessageHeight(int _topHeight){
        topHeight = _topHeight;
    }
    
    @Override
    public String toString(){
        String str = "ObjectDisplayGrid: \n";
        str += "    gameHeight: "+gameHeight + "\n";
        str += "    width: "+width + "\n";
        str += "    topHeight: "+topHeight + "\n";
        return str;
    }

}
