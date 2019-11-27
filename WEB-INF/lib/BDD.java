
public class BDD {
    private String ip = "172.16.73.128";
    private String login = "tc";
    private String password = "tc";

    public BDD() {

    }

    public String getUrl(){
        return "jdbc:postgresql://"+this.ip+"/progweb";
    }

    public String getLogin(){
        return this.login;
    }

    public String getPassword(){
        return this.password;
    }
}