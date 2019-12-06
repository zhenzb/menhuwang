package com.handongkeji.util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
/**
 * @Classname QRCodeGenerator
 * @Date 2019/9/17 18:32
 * @Created Mr.Chen
 * ____           _
 * / ___| __ ___   _(_)_ __
 * | |  _ / _` \ \ / / | '_ \
 * | |_| | (_| |\ V /| | | | |
 * \____|\__,_| \_/ |_|_| |_|
 */
public class QRCodeGenerator {

    private static final String iPhoneQR_CODE_IMAGE_PATH = "/qingyun/qingyunupload/qingyun/common/iPhoneQRCode.png";

    private static final String AndroidQR_CODE_IMAGE_PATH = "/qingyun/qingyunupload/qingyun/common/AndroidQRCode.png";

    public static void generateQRCodeImage(String text, int width, int height, int type) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        String filePath="";
        if(type==1){
            filePath= iPhoneQR_CODE_IMAGE_PATH;
        }else {
            filePath= AndroidQR_CODE_IMAGE_PATH;
        }
        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    public static void main(String[] args) {
        try {
            generateQRCodeImage("This is my first QR Code", 350, 350, 1);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }

    }

}
