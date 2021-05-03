import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The suite for running the tests for starship USS Discovery's lockers,
 * both the regular lockers and the storage.
 */
@RunWith(Suite. class)
@Suite.SuiteClasses({
        LockerTest. class,
        LongTermTest. class
})

public class SpaceshipDepositoryTest {
}