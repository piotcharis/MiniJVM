package pgdp.minijvm.tests;

import org.junit.jupiter.api.*;
import pgdp.minijvm.Simulator;
import pgdp.minijvm.instructions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Diese Tests sind NICHT Teil der Aufgabe!!!
 */

public class TestAllOthersNotPartOfExercise {
    private Simulator sim = new Simulator(15, new Instruction[0]);

    public void resetSim() {
        sim = new Simulator(15, new Instruction[0]);
    }

    @Test
    public void testAnd() {
        resetSim();

        And and = new And();

        // true
        sim.getStack().push(3);
        sim.getStack().push(55);

        and.execute(sim);

        assertEquals(1, sim.getStack().pop());

        // false
        sim.getStack().push(3);
        sim.getStack().push(0);

        and.execute(sim);

        assertEquals(0, sim.getStack().pop());

        // false double 0
        sim.getStack().push(0);
        sim.getStack().push(0);

        and.execute(sim);

        assertEquals(0, sim.getStack().pop());
    }

    @Test
    public void testCallAndRet() {
        resetSim();
        Call call = new Call(1);
        Call call2 = new Call(2);
        Ret ret2 = new Ret();
        Ret ret = new Ret();

        sim.getStack().push(1);
        sim.getStack().push(2);
        sim.getStack().push(3);
        sim.getStack().push(4);

        sim.setProgramCounter(2);

        call.execute(sim);

        assertEquals(1, sim.getProgramCounter());
        assertEquals(2, sim.getStack().getValueAtIndex(4));
        assertEquals(0, sim.getStack().getValueAtIndex(5));

        sim.getStack().push(88);
        sim.getStack().push(78);
        sim.getStack().push(68);

        call2.execute(sim);

        sim.getStack().push(22);
        sim.getStack().push(23);

        sim.setProgramCounter(55);

        ret2.execute(sim);

        sim.getStack().push(43);

        ret.execute(sim);

        assertEquals(2, sim.getProgramCounter());
        assertEquals(0, sim.getStackOffset());
    }

    @Test
    public void testFJump() {
        resetSim();

        FJump fjump = new FJump(5);

        // true
        sim.setProgramCounter(1);
        sim.getStack().push(0);

        fjump.execute(sim);

        assertEquals(5, sim.getProgramCounter());

        // false
        sim.setProgramCounter(1);
        sim.getStack().push(10);

        fjump.execute(sim);

        assertEquals(1, sim.getProgramCounter());
    }

    @Test
    public void testHalt() {
        resetSim();

        Halt halt = new Halt();

        halt.execute(sim);

        assertTrue(sim.isHalted());
    }

    @Test
    public void testLoad() {
        resetSim();

        Load load = new Load(2);

        sim.getStack().push(1);
        sim.getStack().push(2);
        sim.getStack().push(3);
        sim.getStack().push(4);
        sim.getStack().push(5);

        load.execute(sim);

        assertEquals(3, sim.getStack().pop());
    }

    @Test
    public void testNot() {
        resetSim();

        Not not = new Not();

        // true
        sim.getStack().push(10);
        not.execute(sim);

        assertEquals(0, sim.getStack().pop());

        // false
        sim.getStack().push(0);
        not.execute(sim);

        assertEquals(1, sim.getStack().pop());
    }

    @Test
    public void testStore() {
        resetSim();
        Store store = new Store(3);

        sim.getStack().push(1);
        sim.getStack().push(2);
        sim.getStack().push(3);
        sim.getStack().push(4);
        sim.getStack().push(5);
        sim.getStack().push(6);
        sim.getStack().push(7);

        store.execute(sim);

        assertEquals(7, sim.getStack().getValueAtIndex(3));
    }

    @Test
    public void testSub() {
        resetSim();
        Sub sub = new Sub();

        // positive
        sim.getStack().push(10);
        sim.getStack().push(3);

        sub.execute(sim);

        assertEquals(7, sim.getStack().pop());

        // negative
        sim.getStack().push(3);
        sim.getStack().push(10);

        sub.execute(sim);

        assertEquals(-7, sim.getStack().pop());
    }
}
