package proj1;
public class Structure extends Displayable{
    public Structure(){
        super();
    }

    @Override
    public String toString( ) {
        String str = "Structure: \n";
        str += super.toString( );
        return str;
    }
}