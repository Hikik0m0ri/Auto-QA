package backend.storage;

public enum USER {
    EMAIL("eve.holt@reqres.in"),
    BAD_EMAIL("sydney@fife"),
    PASSWORD("pistol");

    private String user;

    USER(String user) {
        this.user = user;
    }

    public String getUserData() {
        return user;
    }

    @Override
    public String toString() {
        return "USERS{" +
                "user='" + user + '\'' +
                '}';
    }
}
