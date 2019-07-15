import java.math.BigInteger;
import java.util.Random;

public class DiffiHellman {

    private BigInteger privA, privB, pubA, pubB, p, g, sA, sB;
    private int bitLength = 2048;  //bit lenghts for variables
    private int certainty = 20;



    public void run(){
         rand(); //rand initial values
         getPublicKeys();
         sA = getS(pubA, privB);
         sB = getS(pubB, privA);
         System.out.println(sA);
         System.out.println(sB);

    }

    private void rand(){
        Random rnd = new Random();
        p = new BigInteger(bitLength,  certainty,  rnd);
        privA = new BigInteger(bitLength, rnd);
        privB = new BigInteger(bitLength, rnd);
        g = new BigInteger(bitLength, rnd);
    }

    private void getPublicKeys(){
        pubA = powerModulo(privA, g, p);
        pubB = powerModulo(privB, g, p);


    }

    private BigInteger powerModulo(BigInteger power, BigInteger base, BigInteger mod ){
            String privBinaryString = toBinaryAndReverse(power);
            BigInteger a =  base;
            BigInteger x = new BigInteger("1");
            for(int i=0; i<privBinaryString.length(); ++i){

                if(privBinaryString.charAt(i) == '1') {
                    x = x.multiply(a);
                    x = x.mod(mod);

                }

                a = a.pow(2);
                a = a.mod(mod);

            }


        return x;
    }

    private String toBinaryAndReverse(BigInteger number){

        StringBuilder input1 = new StringBuilder();
        String privBinaryString = number.toString(2);
        input1.append( privBinaryString);
        input1 =  input1.reverse();
        privBinaryString = input1.toString();


        return privBinaryString;
    }

    private BigInteger getS(BigInteger pub, BigInteger priv){
            BigInteger s ;
            s = powerModulo(priv, pub, p);
        return s;
    }
}
