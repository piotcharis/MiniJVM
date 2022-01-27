package pgdp.minijvm.instructions;

import pgdp.minijvm.Simulator;

public class Less extends Instruction {

    public void execute(Simulator simulator) {
        // Erste zwei Elemente nehmen
        int element1 = simulator.getStack().pop();
        int element2 = simulator.getStack().pop();

        // Vergleichen, ob das untere Element kleiner als das obere ist und
        // das Ergebnis in 0(false) oder 1(true) zurückgeben.
        if (element2 < element1) {
            simulator.getStack().push(1);
        } else {
            simulator.getStack().push(0);
        }
    }
}
