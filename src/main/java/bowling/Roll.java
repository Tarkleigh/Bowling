package bowling;

public record Roll(int pinsHit) {

    public Roll(int pinsHit) {
        if (pinsHit < 0) {
            this.pinsHit = 0;
        } else if (pinsHit > 10) {
            this.pinsHit = 10;
        } else {
            this.pinsHit = pinsHit;
        }
    }
}