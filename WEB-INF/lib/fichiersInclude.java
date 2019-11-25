public enum fichiersInclude {
    INDEX("index.html"),
    COOKIES("cookies.html"),
    NAVBAR("navbar.html"),
    HEAD("head.html"),
    SIDEBAR("sidebar.html"),
    FORMAJOMAGASIN("formAjoMagasin.html"),
    FORMAJOGERANT("formAjoGerant.html"),
    FORMAJOFOURNITURES("formAjoFournitures.html"),
    HELP("help.html");

    private String url = "";

    fichiersInclude(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
}