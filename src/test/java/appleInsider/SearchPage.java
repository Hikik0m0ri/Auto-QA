package appleInsider;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchPage {
    private final ElementsCollection articleTitles = $$x("//h2//a");

    /**
     * Возвращает href из 1 элемента поиска
     *
     * @return
     */
    public String getHrefFromFirstArticle() {
        return articleTitles.first().getAttribute("href");
    }
}
