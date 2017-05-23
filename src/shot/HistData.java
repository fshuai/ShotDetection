package shot;

/**
 * Created by fshuai on 2017/4/25.
 */
public class HistData {
    public static final int HISTBINS=16;
    public int[] h;
    public int[] s;
    public int[] v;

    public HistData(){
        h=new int[HISTBINS];
        s=new int[HISTBINS];
        v=new int[HISTBINS];
    }
}
