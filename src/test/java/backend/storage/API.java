package backend.storage;

public enum API {
    REGISTER("api/register");

    private String api;

    API (String api){
        this.api=api;
    }

    public String getApi() {
        return api;
    }

    @Override
    public String toString() {
        return "API{" +
                "api='" + api + '\'' +
                '}';
    }
}
