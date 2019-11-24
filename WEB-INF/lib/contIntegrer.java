import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.unbescape.html.HtmlEscape;
public class contIntegrer {
    private File tmp = new File("/home/benoitlefebvre99/tomcat8/webapps/projetprogweb/");
    private String path = tmp.getAbsolutePath()+"/";
    private String index = this.path + "index.html";
    private String cookies = this.path + "cookies.html";
    private String navbar = this.path + "navbar.html";
    private String head = this.path + "head.html";
    private String sidebar = this.path + "sidebar.html";
    private String formAjoMagasin = this.path + "formAjoMagasin.html";
    private String formAjoGerant = this.path + "formAjoGerant.html";
    private String formAjoFournitures = this.path + "formAjoFournitures.html";
    private BufferedReader bf;
    private StringBuilder sb;
    private String resp, line;

    public contIntegrer(){
        this.resp = "";
    }

    public String getIndex(){
        try {
            FileReader fr = new FileReader(this.index);
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
    public String getCookies(){
        try {
            FileReader fr = new FileReader(this.cookies);
            this.bf = new BufferedReader(fr);
            this.sb = new StringBuilder();
            try {
                while((line = this.bf.readLine()) !=null)
                    this.resp= HtmlEscape.escapeHtml5(this.sb.append(line) +"") +" \n";
            }catch(IOException eio){
                return eio.getMessage();
            }
        }catch(FileNotFoundException e){
            return e.getMessage();
        }
        String results = this.resp;
        this.resp="";
        this.line="";
        return results;
    }
    public String getHead(){
        try {
            FileReader fr = new FileReader(this.head);
            this.bf = new BufferedReader(fr);
            this.sb = new StringBuilder();
            try {
                while((line = this.bf.readLine()) !=null)
                    this.resp=  HtmlEscape.escapeHtml5(this.sb.append(line)+"") +" \n";
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
    public String getNavbar(){
        try {
            FileReader fr = new FileReader(this.navbar);
            this.bf = new BufferedReader(fr);
            this.sb = new StringBuilder();
            try {
                while((line = this.bf.readLine()) !=null)
                    this.resp= HtmlEscape.escapeHtml5(this.sb.append(line)+"") +" \n";
            }catch(IOException eio){
                return eio.getMessage();
            }
        }catch(FileNotFoundException e){
            return e.getMessage();
        }
        String result = this.resp;
        this.resp="";
        this.line="";
        return result;
    }
    public String getSidebar(){
        try {
            FileReader fr = new FileReader(this.sidebar);
            this.bf = new BufferedReader(fr);
            this.sb = new StringBuilder();
            try {
                while((line = this.bf.readLine()) !=null)
                    this.resp= HtmlEscape.escapeHtml5(this.sb.append(line)+"") +" \n";
            }catch(IOException eio){
                return eio.getMessage();
            }
        }catch(FileNotFoundException e){
            return e.getMessage();
        }
        String result =this.resp;
        this.resp="";
        this.line="";
        return result;
    }

    public String getFormAjoMagasin(){
        try {
            FileReader fr = new FileReader(this.formAjoMagasin);
            this.bf = new BufferedReader(fr);
            this.sb = new StringBuilder();
            try {
                while((line = this.bf.readLine()) !=null)
                    this.resp= HtmlEscape.escapeHtml5(this.sb.append(line)+"") +" \n";
            }catch(IOException eio){
                return eio.getMessage();
            }
        }catch(FileNotFoundException e){
            return e.getMessage();
        }
        String result =this.resp;
        this.resp="";
        this.line="";
        return result;
    }

    public String getFormAjoGerant(){
        try {
            FileReader fr = new FileReader(this.formAjoGerant);
            this.bf = new BufferedReader(fr);
            this.sb = new StringBuilder();
            try {
                while((line = this.bf.readLine()) !=null)
                    this.resp= HtmlEscape.escapeHtml5(this.sb.append(line)+"") +" \n";
            }catch(IOException eio){
                return eio.getMessage();
            }
        }catch(FileNotFoundException e){
            return e.getMessage();
        }
        String result =this.resp;
        this.resp="";
        this.line="";
        return result;
    }

    public String getFormAjoFournitures(){
        try {
            FileReader fr = new FileReader(this.formAjoFournitures);
            this.bf = new BufferedReader(fr);
            this.sb = new StringBuilder();
            try {
                while((line = this.bf.readLine()) !=null)
                    this.resp= HtmlEscape.escapeHtml5(this.sb.append(line)+"") +" \n";
            }catch(IOException eio){
                return eio.getMessage();
            }
        }catch(FileNotFoundException e){
            return e.getMessage();
        }
        String result =this.resp;
        this.resp="";
        this.line="";
        return result;
    }
} 