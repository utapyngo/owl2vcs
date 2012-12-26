package owl2vcs.changeset;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MutableChangeSetTest {

    /**
     * Tests if a newly created MutableChangeSet is empty.
     */
    @Test
    public void testIsEmpty() {
        MutableChangeSet target = new MutableChangeSet();
        boolean expected = true;
        boolean actual = target.isEmpty();
        assertEquals(expected, actual);
    }

}
