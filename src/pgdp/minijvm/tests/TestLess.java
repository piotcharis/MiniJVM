package pgdp.minijvm.tests;

import org.junit.jupiter.api.*;
import pgdp.minijvm.Simulator;
import pgdp.minijvm.instructions.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestLess {
    private Simulator sim = new Simulator(10, new Instruction[0]);
    private Less less = new Less();

    /**
     * Testet den Fall, dass der zweite int im Stack kleiner als dem ersten ist
     * und somit soll die Methode execute eine 1 im Stack oben legen.
     */
    @Test
    public void testLessTrue() {
        sim.getStack().push(2);
        sim.getStack().push(5);

        less.execute(sim);
        assertEquals(1, sim.getStack().pop());
    }

    /**
     * Testet, ob die Methode execute richtig bei zwei gleichen ints im Stack eine 0 oben legt.
     */
    @Test
    public void testLessSameNumber() {
        sim = new Simulator(10, new Instruction[0]);
        sim.getStack().push(2);
        sim.getStack().push(2);

        less.execute(sim);

        assertEquals(0, sim.getStack().pop());
    }
}
