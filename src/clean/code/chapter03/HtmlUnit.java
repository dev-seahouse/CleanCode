package clean.code.chapter03;

import clean.code.added.to.make.code.build.*;

public class HtmlUnit {
    public static String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        return new SetupTearDownSurrounder(pageData, includeSuiteSetup).surround();
    }

    private static class SetupTearDownSurrounder {
        private PageData pageData;
        private boolean includeSuiteSetup;
        private WikiPage wikiPage;
        private String content;
        private PageCrawler crawler;

        public SetupTearDownSurrounder(PageData pageData, boolean includeSuiteSetup) {
            this.pageData = pageData;
            this.includeSuiteSetup = includeSuiteSetup;
            wikiPage = pageData.getWikiPage();
            content = "";
            crawler = wikiPage.getPageCrawler();
        }

        public String surround() {
            if (isTestPage())
                surroundPageWithSetupsAndTearDowns();
            return pageData.getHtml();
        }

        private void surroundPageWithSetupsAndTearDowns() {
            content += includeSetups();
            content += (pageData.getContent() + "\n");
            content += includeTearDowns();
            pageData.setContent(content);
        }

        private boolean isTestPage() {
            return pageData.hasAttribute("Test");
        }

        private String includeTearDowns() {
            String teardowns= "";
            content+=includeIfInherited("teardown ", "TearDown");
            if (includeSuiteSetup)
               content+= includeIfInherited("teardown ", SuiteResponder.SUITE_TEARDOWN_NAME);
            return teardowns;
        }

        private String includeSetups() {
            String setups = "";
            if (includeSuiteSetup)
                content += includeIfInherited("setup ", SuiteResponder.SUITE_SETUP_NAME);
            includeIfInherited("setup ", "SetUp");
            return setups;
        }

        private String includeIfInherited(String mode, String pageName) {
            WikiPage page = PageCrawlerImpl.getInheritedPage(pageName, wikiPage);
            if (page != null) return(includePage(mode, page));
            return "";
        }

        private String includePage(String mode, WikiPage page) {
            WikiPagePath pagePath = crawler.getFullPath(page);
            String pagePathName = PathParser.render(pagePath);
            return String.format("!include -%s .%s\n",mode, pagePathName);
        }
    }
}