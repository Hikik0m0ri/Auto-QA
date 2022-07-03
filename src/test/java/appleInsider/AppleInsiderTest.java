package appleInsider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppleInsiderTest extends BaseTest {
    private final static String SEARCH_STRING = "Чем iPhone 13 отличается от iPhone 12";
    private final static String EXPECTED_WORD = "iphone-12";

    @Test
    public void checkHref() {

        Assertions.assertTrue(new MainPage(URL.BASEURL.getUrl())
                .search(SEARCH_STRING)
                .getHrefFromFirstArticle()
                .contains(EXPECTED_WORD));
    }
}
