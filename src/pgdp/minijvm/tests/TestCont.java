package pgdp.minijvm.tests;

import org.junit.jupiter.api.*;
import pgdp.minijvm.Simulator;
import pgdp.minijvm.instructions.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestCont {
    private Simulator sim = new Simulator(10, new Instruction[0]);
    private Const constI;

    /**
     * Testet den Normalfall, dass der int 5 oben im Stack addiert werden soll.
     */
    @Test
    public void testConstNormal() {
        sim.getStack().push(10);
        constI = new Const(5);
        constI.execute(sim);
        assertEquals(5, sim.getStack().pop());
    }

    /**
     * Testet, ob die Methode execute in Const mit dem maximalen Integer richtig funktioniert.
     */
    @Test
    public void testConstIntegerMax() {
        constI = new Const(Integer.MAX_VALUE);
        constI.execute(sim);
        assertEquals(Integer.MAX_VALUE, sim.getStack().pop());
    }
}
