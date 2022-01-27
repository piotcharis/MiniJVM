package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Sub extends Instruction {

    public void execute(Simulator simulator) {
        // Erste zwei Elemente nehmen.
        int element1 = simulator.getStack().pop();
        int element2 = simulator.getStack().pop();

        // Das 2. Element minus dem ersten auf dem Stack legen.
        simulator.getStack().push(element2 - element1);
    }
}
