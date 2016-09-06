package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostsCreator;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Transitions;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_AUTO)
@Test(groups = "discussions-reporting-posts")
public class ReportingPostTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";

  private final AtomicReference<String> postId = new AtomicReference<>();

  private final AtomicReference<String> userId = new AtomicReference<>();

  // Anonymous user on mobile

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    assertThatReportPostOptionIsNotAvailable(postsListPage.getPost());
  }

  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage postDetailsPage = new PostDetailsPage().openDefaultPost();
    assertThatReportPostOptionIsNotAvailable(postDetailsPage.getPost());
  }


  @Test(groups = "discussions-anonUserMobileReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotReportPostOnUserPostsPage() {
    UserPostsPage userPostsPage = new UserPostsPage().openDefaultUserPage();
    assertThatReportPostOptionIsNotAvailable(userPostsPage.getPost());
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();

    Assertion.assertTrue(postsListPage.getPost().getReportedPosts().isEmpty(),
        "Anonymous user should not see reported posts.");
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnPostDetailsPage() {
    PostDetailsPage postDetailsPage = new PostDetailsPage().open(postId.get());

    Assertion.assertTrue(postDetailsPage.getPost().getReportedPosts().isEmpty(),
        "Anonymous user should not see reported posts.");
  }

  @Test(groups = "discussions-anonUserMobileReporting",
      dependsOnMethods = "userOnMobileCanReportPostOnUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileCanNotSeeReportedPostOnUserPostsPage() {
    UserPostsPage userPostsPage = new UserPostsPage().open(userId.get());

    Assertion.assertTrue(userPostsPage.getPost().getReportedPosts().isEmpty(),
        "Anonymous user should not see reported posts.");
  }

  // Anonymous user on desktop

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    assertThatReportPostOptionIsNotAvailable(postsListPage.getPost());
  }

  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnPostDetailsPage() {
    PostDetailsPage postDetailsPage = new PostDetailsPage().openDefaultPost();
    assertThatReportPostOptionIsNotAvailable(postDetailsPage.getPost());
  }


  @Test(groups = "discussions-anonUserDesktopReporting")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopCanNotReportPostOnUserPostsPage() {
    UserPostsPage userPostsPage = new UserPostsPage().openDefaultUserPage();
    assertThatReportPostOptionIsNotAvailable(userPostsPage.getPost());
  }

  // User on mobile

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    assertThatAddedPostCanBeReportedOnPostsListPage(postsListPage, postsCreator);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnPostDetailsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    assertThatAddedPostCanBeReportedOnPostDetailsPage(postsListPage, postsCreator);
  }

  @Test(groups = "discussions-loggedInUsersMobileReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileCanReportPostOnUserPostsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorMobile();

    assertThatAddedPostCanBeReportedOnUserPostsPage(postsListPage, postsCreator);
  }

  // User on desktop

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostsListPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    assertThatAddedPostCanBeReportedOnPostsListPage(postsListPage, postsCreator);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnPostDetailsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    assertThatAddedPostCanBeReportedOnPostDetailsPage(postsListPage, postsCreator);
  }

  @Test(groups = "discussions-loggedInUsersDesktopReporting")
  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void userOnDesktopCanReportPostOnUserPostsPage() {
    PostsListPage postsListPage = new PostsListPage().open();
    PostsCreator postsCreator = postsListPage.getPostsCreatorDesktop();

    assertThatAddedPostCanBeReportedOnUserPostsPage(postsListPage, postsCreator);
  }

  private void assertThatReportPostOptionIsNotAvailable(final Post post) {
    boolean actual = post.getTheNewestPost()
        .clickMoreOptions()
        .hasReportPostOption();

    Assertion.assertFalse(actual);
  }

  private void assertThatAddedPostCanBeReportedOnPostsListPage(
      final PostsListPage postsListPage, final PostsCreator postsCreator) {
    PostEntity postEntity = createAndGetNewPost(postsListPage, postsCreator);
    reportPost(postEntity);

    assertThatPostIsReported(postEntity);
    assertThatReportPostOptionIsNotPresent(postEntity);
  }

  private PostEntity createAndGetNewPost(final PostsListPage postsListPage, final PostsCreator postsCreator) {
    PostEntity.Data postEntityData = postsCreator.click()
        .closeGuidelinesMessage()
        .addPostWithRandomData();

    return postsListPage.getPost()
        .waitForPostToAppearWith(postEntityData.getDescription())
        .getTheNewestPost();
  }

  private void reportPost(final PostEntity postEntity) {
    postEntity.clickMoreOptions()
        .clickReportPostOption();
  }

  private void assertThatPostIsReported(final PostEntity postEntity) {
    Assertion.assertTrue(postEntity.hasTopNote(),
        "Post should have top note. (Note with reporter should appear at the top of post.)");
    Assertion.assertTrue(postEntity.isReported(),
        "Post should be reported.");
  }

  private void assertThatReportPostOptionIsNotPresent(final PostEntity postEntity) {
    Assertion.assertFalse(postEntity.clickMoreOptions().hasReportPostOption(),
        "There should be no 'Report post' option in 'More options' on post already reported by user.");
  }

  private void assertThatAddedPostCanBeReportedOnPostDetailsPage(
      final PostsListPage postsListPage, final PostsCreator postsCreator) {
    createAndGetNewPost(postsListPage, postsCreator).click();

    new Transitions(driver).waitForPostDetailsPageTransition();

    postId.set(PostDetailsPage.extractPostIdFrom(driver.getCurrentUrl()));

    PostEntity postEntity = new PostDetailsPage().getPost().getTheNewestPost();
    reportPost(postEntity);

    assertThatPostIsReported(postEntity);
    assertThatReportPostOptionIsNotPresent(postEntity);
  }

  private void assertThatAddedPostCanBeReportedOnUserPostsPage(
      final PostsListPage postsListPage, final PostsCreator postsCreator) {
    createAndGetNewPost(postsListPage, postsCreator)
        .clickMoreOptions()
        .clickViewAllPostsByOption();

    new Transitions(driver).waitForUserPostsPageTransition();

    userId.set(UserPostsPage.extractUserIdFrom(driver.getCurrentUrl()));

    PostEntity postEntity = new UserPostsPage().getPost().getTheNewestPost();
    reportPost(postEntity);

    assertThatPostIsReported(postEntity);
    assertThatReportPostOptionIsNotPresent(postEntity);
  }
}
