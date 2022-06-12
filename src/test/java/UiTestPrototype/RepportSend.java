package UiTestPrototype;

import UiTestPrototype.General.generalFunction;
import UiTestPrototype.Mailing.SendEmail;
import UiTestPrototype.configs.Constants;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.mail.EmailException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RepportSend extends generalFunction {

    private static String archivePath;
    private static List<String> staticSendToEmail;
    private static String staticProjectName;
    private static String staticSmtpHost;
    private static String staticSmtpPort;



    public RepportSend(){

    }

    public void executeTasks() throws IOException {
        propertySet();
        RepportSend.staticSendToEmail =Arrays.asList(prop.getProperty("Report.send.to").split(","));
        RepportSend.staticProjectName =prop.getProperty("Project.Front.Name");
        RepportSend.staticSmtpHost = prop.getProperty(Constants.MAIL_SMTP_HOST);
        RepportSend.staticSmtpPort = prop.getProperty(Constants.MAIL_SMTP_PORT);


        archivePath = compressRepportsFiles(Constants.Rapport_Path);

        try{
            if(!archivePath.equals(""))
            {
                SendEmail.sendingEmailWithAttachment(staticSendToEmail,"[Test-Auto]: "+ staticProjectName + "-" +getCurrentDateUsingFormat(Constants.PATTERN_DATE),
                        "Bonjour, \n \n Vouillez trouvez ci-joint la piece jointe de votre rapport qui vous avez lancé le :" + getCurrentDateUsingFormat(Constants.FULL_PATTERN_DATE)+" \n \n Bien Cordialement",
                        archivePath,staticSmtpHost,staticSmtpPort);
            }
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    public static String compressRepportsFiles(String pathDirReport) throws IOException {
        String archivePath = staticProjectName+ getCurrentDateUsingFormat(Constants.PATTERN_DATE)+".tar.gz";
        if(Files.exists((Paths.get(pathDirReport)))) {
            new RepportSend().createTarGZ(pathDirReport, archivePath);
            return archivePath;
        }
        return "";
    }
    public static String getCurrentDateUsingFormat(String formatDate){
        DateFormat dateFormat = new SimpleDateFormat(formatDate);
        return dateFormat.format(new Date());
    }

    public static void createTarGZ(String dirPath, String tarGzPath) throws IOException {
        FileOutputStream fOut;
        BufferedOutputStream bOut;
        GzipCompressorOutputStream gzOut;
        TarArchiveOutputStream tOut;
        fOut = new FileOutputStream(new File(tarGzPath));
        bOut = new BufferedOutputStream(fOut);
        gzOut = new GzipCompressorOutputStream(bOut);
        tOut = new TarArchiveOutputStream(gzOut);
        tOut.finish();
        tOut.close();;
        gzOut.close();;
        bOut.close();;
        fOut.close();
    }
    public static void main(String[] args){
        RepportSend exec = new RepportSend();
        try{
            exec.executeTasks();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
