package appleInsider;

public enum URL {
    BASEURL("https://appleinsider.ru/");

    private String url;

    URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "URL{" +
                "url='" + url + '\'' +
                '}';
    }
}
