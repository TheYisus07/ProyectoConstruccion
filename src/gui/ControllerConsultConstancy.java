/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import VO.ArchivosVO;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author INNOVA TEC
 */
public class ControllerConsultConstancy implements Initializable {
    private ControllerConsultConstancyList controllerConsultConstancyList;
    
    @FXML
    private ImageView ImagePDF;
    
    @FXML
    private Label TypeConstancy;
    
    public void getConstancyRecognitionTypeSelected(ControllerConsultConstancyList controllerConsultConstancyList, String constancyRecognitionType){
        TypeConstancy.setText(constancyRecognitionType + "");
        this.controllerConsultConstancyList = controllerConsultConstancyList;
    }
    
    /*public void readPDf(String rutaPDF){
        try {
            File file = new File(rutaPDF);
            byte[] bi = null;
            ArrayList<ArchivosVO> ar = new ArrayList<ArchivosVO>();
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            PDFFile pdffile = new PDFFile(buf);
            int numpag = pdffile.getNumPages();
            ByteArrayOutputStream baos = null;
            for (int i = 1; i <= numpag; i++) {
                PDFPage page = pdffile.getPage(i);
                Image MyImage =  new Image(getClass().getResourceAsStream("C:\\Users\\INNOVA TEC\\Documents\\PDFPrueba\\dsfg.pdf"));
                ImagePDF.setImage(MyImage);
                /*  create new image
                Rectangle rect = new Rectangle(0, 0,
                        (int) page.getBBox().getWidth(),
                        (int) page.getBBox().getHeight());
                Image img = page.getImage(
                        rect.width * 2, rect.height * 2, //width & height
                        rect, // clip rect
                        null, // null for the ImageObserver
                        true, // fill background with white
                        true // block until drawing is done
                );

                //BufferedImage bufferedImage = new BufferedImage(rect.width * 2, rect.height * 2, BufferedImage.TYPE_INT_RGB);
                //Graphics g = bufferedImage.createGraphics();
                //g.drawImage(img, 0, 0, null);
                //g.dispose();

                baos = new ByteArrayOutputStream();
                //ImageIO.write(bufferedImage, "jpg", Base64.getEncoder().wrap(baos));
                //---------------------------------------

                //---------------------------------------
                //ImageIO.write(bufferedImage, "PNG", baos);
                bi = baos.toByteArray();

                ArchivosVO po = new ArchivosVO();
                po.setIdArchivos(i);
                po.setArchivos(bi);
                ar.add(po);
                //-----

            }
            baos.close();
            buf.clear();
            channel.close();
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControllerConsultConstancy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerConsultConstancy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
