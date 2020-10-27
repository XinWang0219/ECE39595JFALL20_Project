package proj1;
public class Magic extends Displayable{
    public Magic(){
        super();
    }

    @Override
    public String toString( ) {
        String str = "Magic: \n";
        str += super.toString( );
        return str;
    }
}