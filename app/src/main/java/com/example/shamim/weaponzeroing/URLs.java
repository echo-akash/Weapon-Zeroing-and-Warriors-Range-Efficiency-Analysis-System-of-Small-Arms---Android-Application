package com.example.shamim.weaponzeroing;

public class URLs {

    private static final String ROOT_URL = "http://192.168.43.239/_test/Api.php?apicall=";
    private static final String GP_URL = "http://192.168.43.239/_test/gp_details.php?id=";
    private static final String SOSN_URL = "http://192.168.43.239/_test/sosn_details.php?id=";
    private static final String FIRER_URL = "http://192.168.43.239/_test/firer_details.php?id=";

    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN= ROOT_URL + "login";
    public static final String URL_PRODUCT= ROOT_URL + "gpdetails";
    public static final String URL_SOSN= SOSN_URL + Profile.ba;
    public static final String URL_GP= GP_URL + Profile.ba;
    public static final String URL_FIRER= FIRER_URL + Profile.ba;


}
