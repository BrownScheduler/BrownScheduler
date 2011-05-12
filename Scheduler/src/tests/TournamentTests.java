package tests;

import org.junit.*;

import exception.InvalidRoundException;

import backbone.Round;
import backbone.Unit;
import backbone.UnitAttribute;
import basic.ExampleTournament;
import basic.ExampleUnit;
import static org.junit.Assert.*;

public class TournamentTests {

    private ExampleTournament _t;

    /**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
    	_t = new ExampleTournament();
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        _t = null;
    }
    
    /**
     * Tests createNextRound(boolean suppressWarnings)
     */
    @Test
    public void test_createNextRound() {
    	_t.getCategories().get(0).addMember(new ExampleUnit("P1", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P2", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P3", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P4", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P5", _t.getCategories().get(0)));
    	Round r = null;
    	try {
			r = _t.createNextRound(false);
		} catch (InvalidRoundException e) {
			assertEquals(1, 0);
		}
		assertEquals(r, _t.getRounds().get(0));
		assertEquals("P1", ((UnitAttribute) r.getPairings().get(0).getAttributes().get(0)).getAttribute().getName());
		assertEquals("P2", ((UnitAttribute) r.getPairings().get(0).getAttributes().get(1)).getAttribute().getName());
		assertEquals("P3", ((UnitAttribute) r.getPairings().get(1).getAttributes().get(0)).getAttribute().getName());
		assertEquals("P4", ((UnitAttribute) r.getPairings().get(1).getAttributes().get(1)).getAttribute().getName());
    }  
    
    /**
     * Tests getNew()
     */
    @Test
    public void test_getNew() {
    	_t.getCategories().get(0).addMember(new ExampleUnit("P1", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P2", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P3", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P4", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P5", _t.getCategories().get(0)));
    	try {
			Round r = _t.createNextRound(false);
		} catch (InvalidRoundException e) {
			assertEquals(1, 0);
		}
    	ExampleTournament t2 = (ExampleTournament) _t.getNew();
    	assertFalse(_t == t2);
		assertEquals(1, t2.getCategories().size());
    	assertTrue(t2.getCategories().get(0).getMembers().isEmpty());
    	assertTrue(t2.getRounds().isEmpty());
    }   
    
    /**
     * Tests getCategories()
     */
    @Test
    public void test_getCategories() {
    	assertEquals("Example Units", _t.getCategories().get(0).getName());
		assertEquals(1, _t.getCategories().size());
    	assertTrue(_t.getCategories().get(0).getMembers().isEmpty());
    	_t.getCategories().get(0).addMember(new ExampleUnit("P1", _t.getCategories().get(0)));
    	assertEquals("P1", ((Unit) _t.getCategories().get(0).getMembers().get(0)).getName());
    }
    
    /**
     * Tests getRounds()
     */
    @Test
    public void test_getRounds() {
    	assertTrue(_t.getRounds().isEmpty());
    	_t.getCategories().get(0).addMember(new ExampleUnit("P1", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P2", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P3", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P4", _t.getCategories().get(0)));
    	_t.getCategories().get(0).addMember(new ExampleUnit("P5", _t.getCategories().get(0)));
    	try {
			Round r = _t.createNextRound(false);
		} catch (InvalidRoundException e) {
			assertEquals(1, 0);
	    	assertEquals(2, _t.getRounds().get(0).getMembers().size());
		}
    }

}
