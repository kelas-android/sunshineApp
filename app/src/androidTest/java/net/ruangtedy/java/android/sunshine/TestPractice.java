package net.ruangtedy.java.android.sunshine;

import android.test.AndroidTestCase;

/**
 * Created by tedy.saputro on 20/12/2015.
 */
public class TestPractice extends AndroidTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testThatDemonstrateAssertion(){
        int a=5;
        int b=3;
        int c=5;
        int d=10;
        assertEquals("X should be Equal",a,c);
        assertTrue("Y should be true", d > a);
        assertFalse("Z should be false", a==b);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
