package tests;

import org.junit.*;

import backbone.StringAttribute;
import basic.ExampleGrouping;
import basic.ExampleTournament;
import basic.ExampleUnit;
import static org.junit.Assert.*;

public class UnitTests {

    private ExampleTournament _t;
    private ExampleGrouping _g;
    private ExampleUnit _u;

    /**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
    	_t = new ExampleTournament();
    	_g = (ExampleGrouping) _t.getCategories().get(0);
    	_u = new ExampleUnit("P1", null);
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        _t = null;
        _g = null;
        _u = null;
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
     * Tests deleteFromGrouping and getMemberOf()
     */
    @Test
    public void test_members() {
    	_g.addMember(_u);
    	assertEquals(_u, _g.getMembers().get(0));
    	assertEquals(_g, _u.getMemberOf());
    	assertTrue(_u.deleteFromGrouping());
    	assertTrue(_g.getMembers().isEmpty());
    	
    }

    /**
     * Tests setAttribute()
     */
    @Test
    public void test_setAttribute() {
    	assertEquals(_u.getAttributes().get(0).toString(), _u.getName());
    	assertEquals(_u.getAttributes().get(0).toString(), "P1");
    	_u.setAttribute(new StringAttribute("Name", "P2"));
    	assertEquals(_u.getAttributes().get(0).toString(), _u.getName());
    	assertEquals(_u.getAttributes().get(0).toString(), "P2");
    	_u.setName("P3");
    	assertEquals(_u.getAttributes().get(0).toString(), _u.getName());
    	assertEquals(_u.getAttributes().get(0).toString(), "P3");
    }
}
