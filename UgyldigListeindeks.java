public class UgyldigListeindeks extends RuntimeException {
    public UgyldigListeindeks (int indeks) {
        super("Ugyldig indeks: " + indeks);
    }
}