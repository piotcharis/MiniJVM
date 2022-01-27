package pgdp.minijvm.tests;

import org.junit.jupiter.api.Test;
import pgdp.minijvm.Simulator;
import pgdp.minijvm.instructions.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testet einen Normalfall für Add, wobei die ersten 2 ints in der Stack 1 und 2 sind und
 * die Methode execute in add soll ganz oben in der Stack die Summe 3 speichern.
 */
public class TestAdd {
    private Simulator sim = new Simulator(10, new Instruction[0]);

    private Add add = new Add();

    @Test
    public void testAddNormal() {
        sim.getStack().push(2);
        sim.getStack().push(1);

        add.execute(sim);
        assertEquals(3, sim.getStack().pop());
    }

    /**
     * Testet den Sonderfall, dass die Summe der zwei ersten Ints in der Stack gleich den
     * maximalen Betrag der Integer ist.
     */
    @Test
    public void testAddMaxInt() {
        sim.getStack().push(1073741823);
        sim.getStack().push(1073741824);

        add.execute(sim);
        assertEquals(Integer.MAX_VALUE, sim.getStack().pop());
    }

}
