package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {

    private final NandGate nandRightTop;
    private final NandGate nandRightBot;
    private final NandGate nandTop;
    private final NandGate nandBot;
    private final NandGate nandLeft;

    public DeMux() {
        super("DeMux", 2, 2);

        nandRightTop = new NandGate();
        nandRightBot = new NandGate();
        nandTop = new NandGate();
        nandBot = new NandGate();
        nandLeft = new NandGate();


        nandRightTop.connect(0, nandTop);
        nandRightTop.connect(1, nandTop);

        nandRightBot.connect(0, nandBot);
        nandRightBot.connect(1, nandBot);


        nandTop.connect(1, nandLeft);


    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandRightTop.read();
        } else {
            return nandRightBot.read();
        }


    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }

        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandBot.connect(1, emitter);
                break;
            case 1:
                nandLeft.connect(0, emitter);
                nandLeft.connect(1, emitter);
                nandBot.connect(0, emitter);
        }


    }
}
