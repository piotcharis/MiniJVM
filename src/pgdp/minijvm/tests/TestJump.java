package pgdp.minijvm.tests;

import org.junit.jupiter.api.*;
import pgdp.minijvm.Simulator;
import pgdp.minijvm.instructions.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestJump {
    private Simulator sim = new Simulator(10, new Instruction[0]);
    private Jump jump;

    /**
     * Normalfall, die Methode execute soll den programCounter auf 3 setzen.
     */
    @Test
    public void testJumpNormal() {
        jump = new Jump(3);
        jump.execute(sim);

        assertEquals(3, sim.getProgramCounter());
    }

    /**
     * Sonderfall, testet, ob die Methode richtig mit negativen Zahlen funktioniert.
     */
    @Test
    public void testJumpNegative() {
        sim.setProgramCounter(10);
        jump = new Jump(-6);
        jump.execute(sim);

        assertEquals(-6, sim.getProgramCounter());
    }
}
