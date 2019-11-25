import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.unbescape.html.HtmlEscape;
public class contIntegrer {
    private File tmp = new File("/home/benoitlefebvre99/tomcat8/webapps/projetprogweb/");
    private String path = tmp.getAbsolutePath()+"/";
    private BufferedReader bf;
    private StringBuilder sb;
    private String resp, line;

    public contIntegrer(){
        this.resp = "";
    }

    public String getContent(fichiersInclude choix){
        try {
            FileReader fr = new FileReader(this.path + choix.getUrl());
            this.bf = new BufferedReader(fr);
            this.sb = new StringBuilder();
            try {
                while((line = this.bf.readLine()) !=null)
                    this.resp=  HtmlEscape.escapeHtml5(this.sb.append(line) +"") +" \n";
            }catch(IOException eio){
                return eio.getMessage();
            }
        }catch(FileNotFoundException e){
            return e.getMessage();
        }
        String results =this.resp;
        this.resp="";
        this.line="";
        return results;
    }
   
} 