package hist;

import shot.CvScalar;
import shot.HistData;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by fshuai on 2017/5/3.
 */
public class hist {
    private BufferedImage srcImg;
    //private ArrayList<HSV> hsvlist;

    public hist(BufferedImage img){
        srcImg=img;
        //hsvlist=new ArrayList<HSV>();
    }

    public ArrayList<HSV> RGB2HSV(){
        int width=srcImg.getWidth();
        int height=srcImg.getHeight();
        ArrayList<HSV> result=new ArrayList<HSV>();
        int[] pix=srcImg.getRGB(0,0,width,height,null,0,width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int c = pix[x + y * width];
                int R = (c >> 16) & 0xFF;
                int G = (c >> 8) & 0xFF;
                int B = (c >> 0) & 0xFF;
                float[] value=Color.RGBtoHSB(R,G,B,null);
                HSV hsv=new HSV(value[0]*360,value[1]*255,value[2]*255);
                result.add(hsv);
            }
        }
        return result;
    }
    public HistData getHist(){
        HistData result=new HistData();
        ArrayList<HSV> re=RGB2HSV();
        int len=re.size();
        for(int i=0;i<len;i++){
            result.h[(int)(re.get(i).h/22.5)]++;
            result.s[(int)(re.get(i).s/15.93751)]++;
            result.v[(int)(re.get(i).v/15.93751)]++;
        }
        return result;
    }

    public CvScalar HSV2Scalar(ArrayList<HSV> in){
        return null;
    }

    /*public static void main(String[] args) throws IOException {
        BufferedImage img=ImageIO.read(new File("f:/image/test01.jpg"));
        hist hst=new hist(img);
        ArrayList<HSV> re=hst.RGB2HSV();
        HistData result=new HistData();
        int len=re.size();
        System.out.println("size:"+len);
        float max_h=re.get(0).h;
        for(int i=0;i<len;i++){
            if(max_h<re.get(i).h){
                max_h=re.get(i).h;
            }
            result.h[(int)(re.get(i).h/22.5)]++;
            result.s[(int)(re.get(i).s/15.93751)]++;
            result.v[(int)(re.get(i).v/15.93751)]++;
        }
        for(int i=0;i<16;i++){
            System.out.println("v["+i+"]:"+result.v[i]);
        }
    }*/
}
