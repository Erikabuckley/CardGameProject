package game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CardTest.class,
    CardTest.class,
    DeckTest.class,
    PlayerTest.class
})
public class AllTestsSuite {
    // This class remains empty, it is only used as a holder for the above annotations
}