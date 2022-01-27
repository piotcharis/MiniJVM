package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Add extends Instruction {

    public void execute(Simulator simulator) {
        // Erste zwei Elemente nehmen
        int element1 = simulator.getStack().pop();
        int element2 = simulator.getStack().pop();

        // Summe auf dem Stack legen
        simulator.getStack().push(element1 + element2);
    }
}
