package pro.mdiakonov.tnews;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContentResolver;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import pro.mdiakonov.tnews.view.NewsListActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
//todo
public class NewsListActivityInstrumentedTest extends InstrumentationTestCase {
    @Rule
    public ActivityTestRule<NewsListActivity> mActivityRule =
            new ActivityTestRule<>(NewsListActivity.class, true, false);
    private MockWebServer server;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Constants.BASE_URL = server.url("/").toString();
    }

    @Test
    public void testErrorRowShowsWhenError() throws Exception {
        MockResponse response = new MockResponse()
                .setResponseCode(500)
                .setBodyDelay(60, TimeUnit.SECONDS)
                .setBody("");

        server.enqueue(response);


        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

/*        onView(ViewMatchers.withId(R.id.list))
                .perform(RecyclerViewActions.scrollToPosition(10));*/
        //onView(withText(R.string.connection_error)).check(matches(isDisplayed()));
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
