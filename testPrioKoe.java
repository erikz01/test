public class testPrioKoe {
    
    public static void main(String[] args) {
        
        Prioritetskoe<Integer> pk = new Prioritetskoe<>();

        pk.leggTil(2);
        pk.leggTil(5);
        pk.leggTil(3);
        pk.leggTil(7);

        System.out.println(pk);

    }


}
