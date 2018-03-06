package pro.mdiakonov.tnews;

public class Constants {
    public static final int PAGE_SIZE = 10;
    //  Example: if you have 20 items on your page and want to
    // load next page when 15th item is visible, then you need to set your margin as 5.
    public static final int PAGINATION_MARGIN = 2;

    public static final long CONNECT_TIMEOUT_SEC = 30;
    public static final long READ_WRITE_TIMEOUT_SEC = 20;
    public static String BASE_URL = "https://api.tinkoff.ru/v1/";
}
