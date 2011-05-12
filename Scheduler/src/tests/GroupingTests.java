package tests;

import org.junit.*;

import basic.ExampleGrouping;
import basic.ExampleTournament;
import basic.ExampleUnit;
import static org.junit.Assert.*;

public class GroupingTests {

    private ExampleTournament _t;
    private ExampleGrouping _g;

    /**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
    	_t = new ExampleTournament();
    	_g = (ExampleGrouping) _t.getCategories().get(0);
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        _t = null;
        _g = null;
    }
    
    /**
     * Tests getBlank()
     */
    @Test
    public void test_getBlank() {
    	assertEquals("", _g.getBlank().getName());
    	assertEquals(_g, _g.getBlank().getMemberOf());
    	assertFalse(_g.getBlank() == _g.getBlank());
    }
    
    /**
     * Tests addMember()
     */
    @Test
    public void test_addMember() {
    	ExampleUnit p1 = new ExampleUnit("P1", null);
    	_g.addMember(p1);
    	assertEquals(p1, _g.getMembers().get(0));
    	assertEquals(_g, p1.getMemberOf());
    }

    /**
     * Tests deleteMember()
     */
    @Test
    public void test_deleteMember() {
    	ExampleUnit p1 = new ExampleUnit("P1", null);
    	_g.addMember(p1);
    	assertTrue(_g.deleteMember(p1));
    	assertTrue(_g.getMembers().isEmpty());
    }

    /**
     * Tests getDuplicate()
     */
    @Test
    public void test_getDuplicate() {
    	ExampleUnit p1 = new ExampleUnit("P1", null);
    	ExampleUnit p2 = new ExampleUnit("P1", null);
    	ExampleUnit p3 = new ExampleUnit("P3", null);
    	_g.addMember(p1);
    	assertTrue(_g.getDuplicate(p1) == null);
    	assertEquals(p1, _g.getDuplicate(p2));
    	assertTrue(_g.getDuplicate(p3) == null);
    	
    }
}
