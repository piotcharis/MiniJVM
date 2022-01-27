package pgdp.minijvm.tests;

import org.junit.jupiter.api.*;
import pgdp.minijvm.Simulator;
import pgdp.minijvm.instructions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAlloc {
    private Simulator sim = new Simulator(5, new Instruction[0]);
    private Alloc alloc;

    public void fillStack() {
        for (int i = 0; i < 5; i++) {
            sim.getStack().push(i);
        }
    }

    /**
     * Sonderfall, der Stack ist leer und d.h. dass der StackPointer -1 ist.
     * Die Methode execute mit 3 als Parameter soll den Stackpointer zu 2 wechseln
     */
    @Test
    public void testAllocEmptyStack() {
        sim = new Simulator(5, new Instruction[0]);
        alloc = new Alloc(3);
        alloc.execute(sim);

        assertEquals(2, sim.getStack().getStackPointer());
    }

    /**
     * Normalfall, der Stackpointer soll um 3 subtrahiert werden
     */
    @Test
    public void testAllocNormal() {
        fillStack();
        alloc = new Alloc(-3);
        alloc.execute(sim);

        assertEquals(1, sim.getStack().getStackPointer());
    }
}
