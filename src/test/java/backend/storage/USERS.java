package backend.storage;

public enum USERS {
    EMAIL("eve.holt@reqres.in"),
    BADEMAIL("sydney@fife"),
    PASSWORD("pistol");

    private String user;

    USERS(String user) {
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
