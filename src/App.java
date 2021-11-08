
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

class TUPLE< String,BigDecimal>
    {
        java.lang.String region;
        java.math.BigDecimal totalSum;

        TUPLE()
        {}

        TUPLE( java.lang.String region, java.math.BigDecimal totalSum)        
        {
            this.region = region;
            this.totalSum = totalSum;
        }
        public java.lang.String getRegion()
        {
            return this.region;
        }
        public java.math.BigDecimal getTotalSum()
        {
            return this.totalSum;
        }
        public void setTotalSum(java.math.BigDecimal sum)
        {
            this.totalSum = sum;
        }
        public void setRegion(java.lang.String region)
        {
            this.region = region;
        }
    }

public class App {

    public static void main(String[] args) throws Exception {
        

        // Starting the timer
        long startTime = System.currentTimeMillis();

        // Initializing all the arrays
        String[] country = new String[1000000];
        String[] region = new String[1000000];
        BigDecimal[] arr1 = new BigDecimal[1000000];
        BigDecimal[] arr2 = new BigDecimal[1000000];
        BigDecimal[] arr3 = new BigDecimal[1000000];
        BigDecimal[] arr4 = new BigDecimal[1000000];

         // READING Country file
        BufferedReader countryReader = new BufferedReader(new FileReader("Country.txt"));
        String line;

        int index = 0;
        while((line = countryReader.readLine()) != null)
        {
            country[index] = line;
            index++;
        }

         // READING Region file
        BufferedReader regionReader = new BufferedReader(new FileReader("Region.txt"));
        index = 0;

        while((line = regionReader.readLine()) != null)
        {
            region[index] = line;
            index++;
        }

         // READING 1st Array file
        BufferedReader array1Reader = new BufferedReader(new FileReader("Array1.txt"));
        index = 0;

        while((line = array1Reader.readLine()) != null)
        {
            BigDecimal bigDecimal = new BigDecimal(line);
            arr1[index] = bigDecimal;
            index++;
        }

         // READING 2nd Array file
        BufferedReader array2Reader = new BufferedReader(new FileReader("Array2.txt"));
        index = 0;

        while((line = array2Reader.readLine()) != null)
        {
            BigDecimal bigDecimal = new BigDecimal(line);
            arr2[index] = bigDecimal;
            index++;
        }

         // READING 3rd Array file
        BufferedReader array3Reader = new BufferedReader(new FileReader("Array3.txt"));
        index = 0;

        while((line = array3Reader.readLine()) != null)
        {
            BigDecimal bigDecimal = new BigDecimal(line);
            arr3[index] = bigDecimal;
            index++;
        }

        // READING 4th Array file
        BufferedReader array4Reader = new BufferedReader(new FileReader("Array4.txt"));
        index = 0;

        while((line = array4Reader.readLine()) != null)
        {
            BigDecimal bigDecimal = new BigDecimal(line);
            arr4[index] = bigDecimal;
            index++;
        }

        // expression which we want to perform on all arrays
        String expression = "a1 + a2 + a3 + a4";

        HashMap<String, TUPLE> map = new HashMap<>();

        // Stopping the timer
        long stopTime = System.currentTimeMillis(); 

        for(int i = 0 ; i < arr1.length; i++)
        {
            BigDecimal result = Calculation.calculate(expression,arr1[i],arr2[i],arr3[i],arr4[i],country[i],region[i]);
            
            // setting scale of BigDecimal as 2.
            BigDecimal big = result.setScale(2,RoundingMode.HALF_UP);

            // If map already contains the country as key
            if(map.containsKey(country[i]))
            {
                // add already contains sum + newly calculated sum.
                BigDecimal finalResult =  map.get(country[i]).getTotalSum().add(big);

                TUPLE re = map.get(country[i]);

                // Setting total sum.
                re.setTotalSum(finalResult);

                // storing newly calculated sum into map.
                map.put(country[i], re);
            }
            else
            {
                // If map does not contains country as key.
                TUPLE re = new TUPLE();

                // set newly calculated sum into value part.
                re.setTotalSum(big);

                // setting region.
                re.setRegion(region[i]);

                // storing newly calculated sum into map.
                map.put(country[i], re);
            }
            
        }

        // printing the map
        for (Map.Entry<String, TUPLE> e : map.entrySet())
        {
            System.out.println(e.getValue().getRegion() + "\t\t" +  e.getKey() + "\t\t" + e.getValue().getTotalSum());
        }

        System.out.println();
        
        // Stopping the timer
        long stopTime1 = System.currentTimeMillis();   
        
        System.out.println("Total Time : " + (stopTime1 - startTime) + " " + "ms"); 
    }  
    
}

