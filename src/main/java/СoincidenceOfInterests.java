public class СoincidenceOfInterests {

    public int firstvariant(int a1, int b1, int c1, int a2, int b2, int c2){
        double a, b, c;
        if (a2 >= a1) {
            a = (double) a1 / a2;
        }
        else{
            a =  (double) a2 / a1;
        }
        if (b2 >= b1) {
            b =  (double) b1 / b2;
        }
        else{
            b = (double) b2 / b1;
        }
        if (c2 >= c1) {
            c = (double) c1 / c2;
        }
        else{
            c = (double) c2 / c1;
        }
        return ((int) (((a + b + c) / 3) * 100));
    }
}
