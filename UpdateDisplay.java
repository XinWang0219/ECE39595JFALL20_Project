import asciiPanel.AsciiPanel;

//package ECE39595JFALL20_Project;
//package proj1;
public class UpdateDisplay extends CreatureAction{
    private Creature owner;
    private String name;
//    private ObjectDisplayGrid displayGrid = null;
    
    public UpdateDisplay(String _name, Creature _owner){
        //System.out.println("CreatureAction UpdateDisplay-> name:"+name+"; owner: "+owner.name);
        owner = _owner;
        name = _name;
        
    }
    
    public void run() {
    	ObjectDisplayGrid.updateDisplay();
    }

    @Override
    public String toString( ) {
        String str = "UpdateDisplay: \n";
        str += super.toString( );
        str += "   name: " + name + "\n";
        str += "   owner: " + owner.name + "\n";
        return str;
    }
}
