package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import hist.hist;
import shot.CvScalar;
import shot.HistData;
import shot.ShotCut;

import javax.imageio.ImageIO;

import static java.lang.Math.sin;

/**
 * Created by fshuai on 2017/5/19.
 */
public class HistTest {
    public static void main(String[] args) throws IOException {
        BufferedImage img=ImageIO.read(new File("f:/image/0048.jpg"));
        BufferedImage preImg=ImageIO.read(new File("f:/image/0049.jpg"));
        //preImg.setData(img.getData());
        hist h=new hist(img);
        hist preh=new hist(preImg);
        HistData curHistData=h.getHist();
        HistData preHistData=preh.getHist();
        ShotCut shot=new ShotCut();
        //System.out.println(shot.calFrameHistDiff(preHistData,curHistData));
        for(int i=0;i<16;i++){
            System.out.println("h["+i+"]:"+curHistData.h[i]);
        }
        CvScalar result=shot.extractDomainColor(curHistData);
        System.out.println("maincolor:"+result.val[0]+" "+result.val[1]+" "+result.val[2]);
        System.out.println("maincolorrate"+shot.calDomainColorRate(img,curHistData));
        System.out.println("colorDiff:"+shot.calDomainColorDiff(img,curHistData,preImg,preHistData));
        shot.setPath("f:/image/",342);
        shot.shotDetection();
        for(int i=0;i<shot.getCutInfo().size();i++){
            System.out.println("cutinfo:"+shot.getCutInfo().get(i));
        }
    }
}
