public enum FichiersInclude {
    INDEX("index.html"),
    COOKIES("cookies.html"),
    NAVBAR("navbar.html"),
    HEAD("head.html"),
    SIDEBAR("sidebar.html"),
    ALERT("alert.html"),
    HELP("help.html");

    private String url = "";

    FichiersInclude(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
}