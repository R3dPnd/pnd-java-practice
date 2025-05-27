package pnd.practice.questions.q2894;

public class DifferenceOfSums {
    public int differenceOfSums(int n, int m) {
        int sum = 0;
        int diff = 0;

        for( int i=1; i<=n; i++){
            if(i%m != 0){
                System.out.println(i);
                sum += i;
            }
            else{
                diff += i;
            }
        }
        return sum - diff;
    }
}
